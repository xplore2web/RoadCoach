package com.alcord.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alcord.amazon.AmazonSESService;
import com.alcord.exception.ProcessFailed;
import com.alcord.model.Account;
import com.alcord.service.AccountService;
import com.alcord.service.EmailProviderService;
import com.alcord.utility.StringUtility;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.amazonaws.services.simpleemail.model.SendEmailResult;

@Service
@Transactional
public class EmailProviderServiceImpl extends BaseServiceImpl implements EmailProviderService {

	@Autowired
	AccountService accountService;
	@Autowired
	AmazonSESService amazonSESService;

	static final String FROM = "ar@alcordtechnologies.com";
	static final String TO = "syed.ilyas@alcordtechnologies.com";
	static final String BODY = "This email was sent through Amazon SES by using the AWS SDK for Java.";
	static final String SUBJECT = "Amazon SES test (AWS SDK for Java)";

	@Override
	public SendEmailResult sendWelcomeEmail(UUID accountId) throws ProcessFailed {
		Account account = accountService.getAccountById(accountId);

		if(StringUtility.isEmpty(account.getEmailAddress())) {
			throw new ProcessFailed("No email address present");
		}
		// Construct an object to contain the recipient address.
		Destination destination = new Destination().withToAddresses(new String[] { TO });

		// Create the subject and body of the message.
		Content subject = new Content().withData(SUBJECT);
		Content textBody = new Content().withData(BODY);
		Body body = new Body().withText(textBody);

		// Create a message with the specified subject and body.
		Message message = new Message().withSubject(subject).withBody(body);


		// Assemble the email.
		SendEmailRequest sendEmailRequest = new SendEmailRequest().withSource(FROM).withDestination(destination)
				.withMessage(message);

		try {
			System.out.println("Attempting to send an email through Amazon SES by using the AWS SDK for Java...");

			return amazonSESService.getAmazonSESClient().sendEmail(sendEmailRequest);

		} catch (Exception ex) {
			loggingUtility.log("The email was not sent.", ex);
			throw new ProcessFailed(ex.getMessage());
		}
	}
	
	@Override
	public SendEmailResult sendEmail(String toEmailAddress, String subject, String body) throws ProcessFailed {
		
		// Construct an object to contain the recipient address.
		Destination destination = new Destination().withToAddresses(new String[] { toEmailAddress });

		// Create the subject and body of the message.
		Content emailSubject = new Content().withData(subject);
		Content textBody = new Content().withData(body);
		Body emailBody = new Body().withText(textBody);

		// Create a message with the specified subject and body.
		Message message = new Message().withSubject(emailSubject).withBody(emailBody);


		// Assemble the email.
		SendEmailRequest sendEmailRequest = new SendEmailRequest().withSource(FROM).withDestination(destination)
				.withMessage(message);

		try {
			System.out.println("Attempting to send an email through Amazon SES by using the AWS SDK for Java...");

			return amazonSESService.getAmazonSESClient().sendEmail(sendEmailRequest);

		} catch (Exception ex) {
			loggingUtility.log("The email was not sent.", ex);
			throw new ProcessFailed(ex.getMessage());
		}
	}
	
	@Override
	public void sendDriverPasswordEmail(String password, String toEmailAddress) {
		
		String subject = "Welcome to nammataxi";
		
		String body = "Your password is: "+ password;
		
		SendEmailResult emailResult = sendEmail(toEmailAddress, subject, body);
		
	}
	
	

}
