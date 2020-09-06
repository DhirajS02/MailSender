package com.jmslistener.jmslistenerdemo.listener;

import javax.jms.JMSException;
//import javax.jms.Message;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import com.covid.corona.dto.EmailMsg;
import com.jmslistener.jmslistenerdemo.mailsend.MailSend;

import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
//import com.covid.corona.dto.EmailMsg;

//import ca.alea.ctr.common.dto.EmailVerificationEmailMsg;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class ActiveMqMessageListener
{
    @Autowired
    MailSend mailSender;
	private static final String destination = "mailupdateQueue";

	/*@Override
	public void onMessage(Message message) {
		 if (message instanceof ObjectMessage) {
	          
			EmailMsg emailVerificationEmailMsg = null;
	              ObjectMessage objMsg = (ObjectMessage)message;
	              
	              try {
	            	  emailVerificationEmailMsg = (EmailMsg)objMsg.getObject();
	            	  System.out.println(emailVerificationEmailMsg.getListOfEmails().get(0));
	             // System.out.println("received: " + text);
	                //String active=emailVerificationEmailMsg.getTotalDto().getActive();
	                //System.out.println("active is "+active);
	            	  MailSend mailSend=new MailSend();
	            	  mailSend.sendEmail(emailVerificationEmailMsg);
	          } catch (JMSException e) {
	              e.printStackTrace();
	          }
	      }
		
	}*/
	@JmsListener(destination = destination)
    public void receiveMessage(final Message<EmailMsg> emailVerificationEmailMsg) throws JMSException {
         
        EmailMsg response = emailVerificationEmailMsg.getPayload();
        mailSender.sendEmail(response);
    }

}

