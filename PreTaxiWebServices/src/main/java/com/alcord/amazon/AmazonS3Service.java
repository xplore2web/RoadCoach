package com.alcord.amazon;

import java.io.File;
import java.io.InputStream;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectMetadataRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClient;
import com.amazonaws.services.securitytoken.model.Credentials;
import com.amazonaws.services.securitytoken.model.GetSessionTokenRequest;

/**
 * This class is a client for interacting with Amazon S3 bucket resources.
 *
 * @author AR
 */
@Component
public class AmazonS3Service {

	private static String awsURL = ".s3.amazonaws.com/";

	@Autowired
	AmazonProperties amazonProperties;
	
    private Credentials sessionCredentials;
    
	public AmazonS3Service() {

	}

	public AmazonS3Service(AmazonProperties amazonProperties) {
		this.amazonProperties = amazonProperties;
    }


    /**
     * Save a file using authenticated session credentials
     *
     * @param key  is the name of the file to save in the bucket
     * @param file is the file that will be saved
     * @return an instance of {@link PutObjectResult} containing the result of the save operation
     */
    public PutObjectResult save(String key, File file) {
		return getAmazonS3Client().putObject(new PutObjectRequest(getDefaultBucket(), key, file));
    }

	public PutObjectResult save(String key, InputStream input, ObjectMetadata metadata) {
		return save(key, input, metadata, CannedAccessControlList.PublicRead);
	}

	public PutObjectResult save(String fileName, InputStream inputStream, ObjectMetadata objectMetadata,
			CannedAccessControlList cannedAcl) {
		return getAmazonS3Client().putObject(
				new PutObjectRequest(getDefaultBucket(), fileName, inputStream, objectMetadata)
						.withCannedAcl(cannedAcl));

	}

    /**
     * Get a file using the authenticated session credentials
     *
     * @param key is the key of the file in the bucket that should be retrieved
     * @return an instance of {@link S3Object} containing the file from S3
     */
    public S3Object get(String key) {
		return getAmazonS3Client().getObject(getDefaultBucket(), key);
    }

    /**
     * Gets an Amazon S3 client from basic session credentials
     *
     * @return an authenticated Amazon S3 client
     */
    public AmazonS3 getAmazonS3Client() {
		AWSCredentials credentials = new BasicAWSCredentials(amazonProperties.getAws().getAccessKeyId(),
				amazonProperties.getAws().getAccessKeySecret());
		return AmazonS3ClientBuilder.standard().withRegion(Regions.AP_SOUTH_1)
				.withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
    }

	public String awsURI(String fileName) {
		return new StringBuilder().append("https://").append(amazonProperties.getS3().getDefaultBucket()).append(awsURL)
				.append(fileName)
				.toString();
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

	public String getDefaultBucket() {
		return amazonProperties.getS3().getDefaultBucket();
	}

	public ObjectMetadata getObjectMetadata(String key) {
		return getAmazonS3Client().getObjectMetadata(new GetObjectMetadataRequest(getDefaultBucket(), key));
	}

	public ObjectListing listObjects(ListObjectsRequest listObjectRequest) {
		return getAmazonS3Client().listObjects(listObjectRequest);
	}

}
