package com.alcord.google;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration property group for Amazon S3 and AWS
 *
 * @author AR
 */
@Configuration
@ConfigurationProperties(prefix = "google")
public class GoogleProperties {

    @NestedConfigurationProperty
	private Maps maps;

    /**
     *
     * @return a property group for AWS configurations
     */
	public Maps getMaps() {
		return maps;
    }

    /**
     *
     * @param aws is a property group for AWS configurations
     */
	public void setMaps(Maps maps) {
		this.maps = maps;
    }


	/**
	 * A property group for Google Maps Distance API configurations
	 */
	public static class Maps {

		private String distanceMatrixKey;

		public String getDistanceMatrixKey() {
			return distanceMatrixKey;
		}

		public void setDistanceMatrixKey(String distanceMatrixKey) {
			this.distanceMatrixKey = distanceMatrixKey;
		}


		@Override
        public String toString() {
			return "GoogleMaps{" + "distanceMatrixKey='" + distanceMatrixKey + '\'' +
                    '}';
        }
    }
}
