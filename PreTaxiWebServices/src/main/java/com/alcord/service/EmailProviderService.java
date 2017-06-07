package com.alcord.service;

import java.util.UUID;

import com.alcord.exception.ProcessFailed;
import com.amazonaws.services.simpleemail.model.SendEmailResult;

public interface EmailProviderService {

	public SendEmailResult sendWelcomeEmail(UUID accountId);
	
	public SendEmailResult sendEmail(String toEmailAddress, String subject, String body) throws ProcessFailed;
	
	public void sendDriverPasswordEmail(String password, String toEmailAddress) throws ProcessFailed;

}
