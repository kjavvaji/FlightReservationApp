package com.sindhu.flightReservation.util;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {

	@Value("${com.sindhu.flightReservation.email.subject}")
	private String Email_Subject;

	@Value("${com.sindhu.flightReservation.email.body}")
	private String Email_Body;

	@Autowired
	JavaMailSender sender;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailUtil.class);
	
	public void sendItinerary(String toAddress, String filePath) {
		LOGGER.info("Inside sendItinerary()");
		MimeMessage message = sender.createMimeMessage();
		try {
			
			MimeMessageHelper messageHelper = new MimeMessageHelper(message,true);
			messageHelper.setTo(toAddress);
			messageHelper.setSubject(Email_Subject);
			messageHelper.setText(Email_Body);
			messageHelper.addAttachment("Itinerary", new File(filePath));
			sender.send(message);
			
		} catch (MessagingException e) {
			LOGGER.error("Error Inside sendItinerary() :"+e);
		}
	}
	
}
