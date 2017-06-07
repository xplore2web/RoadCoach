package com.alcord.beans;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationLocalStorage {
	
	@Bean
	public Map<String, String> platformEndpointARNMap() {
	    final Map<String, String> myMap = new HashMap<>();
	    return myMap;
	}

}
