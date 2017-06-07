package com.alcord.amazon;

import org.springframework.beans.factory.annotation.Autowired;
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
@ConditionalOnMissingBean(AmazonSNSService.class)
@EnableConfigurationProperties(AmazonProperties.class)
public class SNSAutoConfiguration {

	@Autowired
	private AmazonProperties amazonProperties;

    @Bean
    AmazonSNSService amazonSNSService() {
		return new AmazonSNSService();
    }
}
