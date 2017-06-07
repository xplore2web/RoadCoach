package com.alcord.amazon;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class auto-configures a {@link AmazonSESService} bean.
 *
 * @author AR
 */
@Configuration
@ConditionalOnMissingBean(AmazonSESService.class)
@EnableConfigurationProperties(AmazonProperties.class)
public class SESAutoConfiguration {

    @Bean
	AmazonSESService amazonSESService() {
		return new AmazonSESService();
    }
}
