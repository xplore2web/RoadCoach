package com.alcord.google;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class auto-configures a {@link GoogleMapsService} bean.
 *
 * @author AR
 */
@Configuration
@ConditionalOnMissingBean(GoogleMapsService.class)
@EnableConfigurationProperties(GoogleProperties.class)
public class GoogleMapsAutoConfiguration {

    @Bean
    GoogleMapsService googleMapsService() {
		return new GoogleMapsService();
    }
}
