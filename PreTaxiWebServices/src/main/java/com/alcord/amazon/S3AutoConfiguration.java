package com.alcord.amazon;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class auto-configures a {@link AmazonS3Service} bean.
 *
 * @author AR
 */
@Configuration
@ConditionalOnMissingBean(AmazonS3Service.class)
@EnableConfigurationProperties(AmazonProperties.class)
public class S3AutoConfiguration {

    @Bean
    AmazonS3Service amazonS3Service() {
		return new AmazonS3Service();
    }
}
