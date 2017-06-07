package com.alcord.amazon;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import com.alcord.enums.DeviceType;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.services.s3.model.Region;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClient;
import com.amazonaws.services.securitytoken.model.Credentials;
import com.amazonaws.services.securitytoken.model.GetSessionTokenRequest;
import com.amazonaws.services.sns.AmazonSNSClient;

/**
 * This class is a client for interacting with Amazon SNS bucket resources.
 *
 * @author AR
 */
@Component
@EnableConfigurationProperties(AmazonProperties.class)
public class AmazonSNSService {

	@Autowired
	AmazonProperties amazonProperties;
	
    private Credentials sessionCredentials;
    
	public AmazonSNSService() {

	}

    /**
     * Gets an Amazon SNS client from basic session credentials
     *
     * @return an authenticated Amazon SNS client
     */
    public AmazonSNSClient getAmazonSNSClient() {
    	//create a new SNS client and set endpoint
    	AWSCredentials credentials = new BasicAWSCredentials(amazonProperties.getAws().getAccessKeyId(), amazonProperties.getAws().getAccessKeySecret());
    	AmazonSNSClient snsClient = new AmazonSNSClient(credentials);	
    	snsClient.setRegion(Region.AP_Mumbai.toAWSRegion());
    	
        // Create a new SNS client using the basic session credentials of the service instance
        return snsClient;
    }

    /**
     * Get the basic session credentials for the template's configured IAM authentication keys
     *
     * @return a {@link BasicSessionCredentials} instance with a valid authenticated session token
     */
    private BasicSessionCredentials getBasicSessionCredentials() {

        // Create a new session token if the session is expired or not initialized
        if (sessionCredentials == null || sessionCredentials.getExpiration().before(new Date()))
            sessionCredentials = getSessionCredentials();

        // Create basic session credentials using the generated session token
        return new BasicSessionCredentials(sessionCredentials.getAccessKeyId(),
                sessionCredentials.getSecretAccessKey(),
                sessionCredentials.getSessionToken());
    }

    /**
     * Creates a new session credential that is valid for 12 hours
     *
     * @return an authenticated {@link Credentials} for the new session token
     */
    private Credentials getSessionCredentials() {
        // Create a new session with the user credentials for the service instance
        AWSSecurityTokenServiceClient stsClient =
                new AWSSecurityTokenServiceClient(new BasicAWSCredentials(amazonProperties.getAws().getAccessKeyId(), amazonProperties.getAws().getAccessKeySecret()));

        // Start a new session for managing a service instance's bucket
        GetSessionTokenRequest getSessionTokenRequest =
                new GetSessionTokenRequest().withDurationSeconds(43200);

        // Get the session token for the service instance's bucket
        sessionCredentials = stsClient.getSessionToken(getSessionTokenRequest).getCredentials();

        return sessionCredentials;
    }
    
    public String applicationEndPointARN(DeviceType deviceType){
    	String endPoint;
    	switch (deviceType) {
		case Android:
			endPoint = amazonProperties.getSns().getAndroidApplicationArn();
			break;
		case iOS:
			endPoint = amazonProperties.getSns().getIosApplicationArn();
			break;
		default:
			endPoint = amazonProperties.getSns().getAndroidApplicationArn();
			break;
		}
    	return endPoint;
    }
}
