package com.alcord.amazon;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClient;
import com.amazonaws.services.securitytoken.model.Credentials;
import com.amazonaws.services.securitytoken.model.GetSessionTokenRequest;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;

/**
 * This class is a client for interacting with Amazon SES bucket resources.
 *
 * @author AR
 */
@Component
public class AmazonSESService {

	@Autowired
	AmazonProperties amazonProperties;

    private Credentials sessionCredentials;
    
	public AmazonSESService() {

	}

	public AmazonSESService(AmazonProperties amazonProperties) {
		this.amazonProperties = amazonProperties;
    }

    /**
	 * Gets an Amazon SES client from basic session credentials
	 *
	 * @return an authenticated Amazon SES client
	 */
	public AmazonSimpleEmailService getAmazonSESClient() {
		AWSCredentials credentials = new BasicAWSCredentials(amazonProperties.getAws().getAccessKeyId(),
				amazonProperties.getAws().getAccessKeySecret());
		System.out.println(amazonProperties.getAws().toString());
		Region REGION = Region.getRegion(Regions.US_EAST_1);
		AmazonSimpleEmailService service = AmazonSimpleEmailServiceClient.builder()
				.withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withRegion(REGION.getName()).build();
		System.out.println(service);
		return service;
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
				new AWSSecurityTokenServiceClient(new BasicAWSCredentials(amazonProperties.getAws().getAccessKeyId(),
						amazonProperties.getAws().getAccessKeySecret()));

        // Start a new session for managing a service instance's bucket
        GetSessionTokenRequest getSessionTokenRequest =
                new GetSessionTokenRequest().withDurationSeconds(43200);

        // Get the session token for the service instance's bucket
        sessionCredentials = stsClient.getSessionToken(getSessionTokenRequest).getCredentials();

        return sessionCredentials;
    }
}
