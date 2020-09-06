package com.jmslistener.jmslistenerdemo.mailsend;

import java.util.List;
import org.springframework.stereotype.Component;

import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import com.covid.corona.dto.EmailMsg;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class MailSend {
	@Value("${com.covid.corona.smtpHost}")
	private String smtpHost;
	
	@Value("${com.covid.corona.smtpPort}")
	private Integer smtpPort;
	
	@Value("${com.covid.corona.senderUserName}")
	private String senderUserName;
	
	@Value("${com.covid.corona.senderPassword}")
	private String senderPassword;
	
	@Value("${mail.smtp.starttls.enable}")
	private String smtpStarttls;
	
	@Value("${spring.mail.properties.mail.smtp.auth}")
	private String smtpAuth;
	
	@Value("${spring.mail.properties.mail.smtp.connectiontimeout}")
	private String smtpConnectiontimeout;
	
	@Value("${spring.mail.properties.mail.smtp.timeout}")
	private String smtpTimeout;
	
	@Value("${spring.mail.properties.mail.smtp.writetimeout}")
	private String smtpWriteTimeout;
	
	@Autowired
	JavaMailSender mailSenderr;
	
	
	public void sendEmail(EmailMsg emailVerificationEmailMsg) {
		try {
			
			List<String> listOfEmails=emailVerificationEmailMsg.getListOfEmails();
			String emailBody="Confirmed cases="+emailVerificationEmailMsg.getTotalDto().getConfirmed()+" Active Cases="+emailVerificationEmailMsg.getTotalDto().getActive()+" Recovered Cases="+emailVerificationEmailMsg.getTotalDto().getRecovered()+" Deceased Cases="+emailVerificationEmailMsg.getTotalDto().getDeceased();					
					
			for(String email:listOfEmails)
			{
			MimeMessage message = mailSenderr.createMimeMessage();
						MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setReplyTo(senderUserName);
			helper.setTo(email);
			helper.setFrom(senderUserName);
			helper.setText(emailBody, true);
			helper.setSubject("CovidUpdate");
			mailSenderr.send(message);
			}
		} catch (Exception e) {
			
		} 
	}
}
