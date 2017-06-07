package com.alcord.service;

import com.alcord.enums.DeviceType;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;

public interface PushNotificationService {

	String registerDeviceWithSNS(String token, DeviceType deviceType);

	PublishResult publishRequestToToken(String token, DeviceType deviceType, PublishRequest publishRequest);
	
}
