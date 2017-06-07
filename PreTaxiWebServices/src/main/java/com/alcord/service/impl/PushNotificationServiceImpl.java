package com.alcord.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alcord.amazon.AmazonSNSService;
import com.alcord.beans.ApplicationLocalStorage;
import com.alcord.enums.DeviceType;
import com.alcord.service.PushNotificationService;
import com.amazonaws.services.sns.model.CreatePlatformEndpointRequest;
import com.amazonaws.services.sns.model.CreatePlatformEndpointResult;
import com.amazonaws.services.sns.model.GetEndpointAttributesRequest;
import com.amazonaws.services.sns.model.GetEndpointAttributesResult;
import com.amazonaws.services.sns.model.InvalidParameterException;
import com.amazonaws.services.sns.model.NotFoundException;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sns.model.SetEndpointAttributesRequest;

@Service
@Transactional
public class PushNotificationServiceImpl extends BaseServiceImpl implements PushNotificationService {

	@Autowired
	private AmazonSNSService amazonSNSService;
	
	@Autowired
	private ApplicationLocalStorage applicationLocalStorage;

	@Override
	public String registerDeviceWithSNS(String token, DeviceType deviceType) {

        String endpointArn = retrieveEndpointArn(token);

        boolean updateNeeded = false;
        boolean createNeeded = (null == endpointArn);

        if (createNeeded) {
            // No endpoint ARN is stored; need to call CreateEndpoint
            endpointArn = createEndpoint(token, deviceType);
            createNeeded = false;
        }

        System.out.println("Retrieving endpoint data...");
        // Look up the endpoint and make sure the data in it is current, even if
        // it was just created
        try {
            GetEndpointAttributesRequest geaReq = 
                    new GetEndpointAttributesRequest()
                    .withEndpointArn(endpointArn);
            GetEndpointAttributesResult geaRes = 
            		amazonSNSService.getAmazonSNSClient().getEndpointAttributes(geaReq);

            updateNeeded = !geaRes.getAttributes().get("Token").equals(token)
            || !geaRes.getAttributes().get("Enabled").equalsIgnoreCase("true");

        } catch (NotFoundException ex) {
			loggingUtility.error(ex);
            // we had a stored ARN, but the endpoint associated with it
            // disappeared. Recreate it.
            createNeeded = true;
        }

        if (createNeeded) {
            createEndpoint(token, deviceType);
        }

        System.out.println("updateNeeded=" + updateNeeded);

        if (updateNeeded) {
            // endpoint is out of sync with the current data;
            // update the token and enable it.
            System.out.println("Updating endpoint " + endpointArn);
            Map attribs = new HashMap();
            attribs.put("Token", token);
            attribs.put("Enabled", "true");
            SetEndpointAttributesRequest saeReq = 
                    new SetEndpointAttributesRequest()
                    .withEndpointArn(endpointArn).
                    withAttributes(attribs);
            amazonSNSService.getAmazonSNSClient().setEndpointAttributes(saeReq);
        }
        
        return endpointArn;
    }

	private String applicationEndPointARN(DeviceType deviceType){		
		return amazonSNSService.applicationEndPointARN(deviceType);
	}
	
    /**
     * @param deviceType 
     * @return never null
     * */
    private String createEndpoint(String token, DeviceType deviceType ) {

        String endpointArn = null;
        try {
            System.out.println("Creating endpoint with token " + token);
            CreatePlatformEndpointRequest cpeReq = 
                    new CreatePlatformEndpointRequest()
                    .withPlatformApplicationArn(amazonSNSService.applicationEndPointARN(deviceType))
                    .withToken(token);
            CreatePlatformEndpointResult cpeRes = amazonSNSService.getAmazonSNSClient()
                    .createPlatformEndpoint(cpeReq);
            endpointArn = cpeRes.getEndpointArn();
        } catch (InvalidParameterException ex) {
			loggingUtility.error(ex);
            String message = ex.getErrorMessage();
            System.out.println("Exception message: " + message);
            Pattern p = Pattern
                    .compile(".*Endpoint (arn:aws:sns[^ ]+) already exists " +
                            "with the same Token.*");
            Matcher m = p.matcher(message);
            if (m.matches()) {
                // the endpoint already exists for this token, but with
                // additional custom data that
                // CreateEndpoint doesn't want to overwrite. Just use the
                // existing endpoint.
                endpointArn = m.group(1);
            } else {
                // rethrow exception, the input is actually bad
                throw ex;
            }
        }
        storeEndpointArn(token, endpointArn);
        return endpointArn;
    }

    /**
     * @return the arn the app was registered under previously, or null if no
     *         endpoint arn is stored
     */
	@Cacheable("retrieveEndpointArn")
    private String retrieveEndpointArn(String token) {
    	String arnStorage = null;
    	System.out.println("Looking for token "+token + " in map");
    	if (applicationLocalStorage.platformEndpointARNMap().containsKey(token)) {
    		System.out.println(token + " present in map");
			arnStorage = applicationLocalStorage.platformEndpointARNMap().get(token);
		}
        return arnStorage;
    }

    /**
     * Stores the endpoint arn in permanent storage for look up next time
     * */
    private void storeEndpointArn(String token, String endpointArn) {
    	System.out.println("Storing token in map");
    	applicationLocalStorage.platformEndpointARNMap().put(token, endpointArn);
    }
    
    @Override
    public PublishResult publishRequestToToken(String token, DeviceType deviceType, PublishRequest publishRequest) {
    	String platformEndpointARN = registerDeviceWithSNS(token, deviceType);
//		publishRequest.setTargetArn("arn:aws:sns:ap-south-1:818862955266:endpoint/GCM/TestApp/ac338195-1b87-3521-bd98-b7867a83ff27");
    	publishRequest.setTargetArn(platformEndpointARN);
		return amazonSNSService.getAmazonSNSClient().publish(publishRequest);
    }
}
