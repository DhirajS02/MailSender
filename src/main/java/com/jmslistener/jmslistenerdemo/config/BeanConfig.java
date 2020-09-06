package com.jmslistener.jmslistenerdemo.config;

import java.util.Properties;
import java.util.concurrent.Executor;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.jmslistener.jmslistenerdemo.listener.ActiveMqMessageListener;

@Configuration
@EnableAsync
@EnableJms
public class BeanConfig {

	@Value("${com.covid.corona.brokerurl.maxconsumers}")
	private int maxConcurrentConsumers;

	@Value("${com.covid.corona.brokerurl}")
	private String brokerURL;
	
	@Value("${com.covid.corona.queueName.corePoolSize}")
	private Integer corePoolSize;
	
	@Value("${com.covid.corona.queueName.maxPoolSize}")
	private Integer maxPoolSize;
	
	@Value("${com.covid.corona.queueName.queueCapacity}")
	private Integer queueCapacity;
	
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
	
	

	
	 @Bean
	    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
	            DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
	            factory.setConnectionFactory(connectionFactory());
	            factory.setConcurrency("1-1");
	            return factory;
	    }

	@Bean("activeMQconnectionFactory")
	public ActiveMQConnectionFactory connectionFactory() {
		ActiveMQConnectionFactory amq = new ActiveMQConnectionFactory();
		amq.setBrokerURL(brokerURL);
		amq.setTrustAllPackages(true);
		return amq;
	}

	@Bean(name = "mailSenderr")
	public JavaMailSender JmsMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(smtpHost);
		mailSender.setPort(smtpPort);
		mailSender.setUsername(senderUserName);
		mailSender.setPassword(senderPassword);

		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", smtpStarttls);
		props.put("spring.mail.properties.mail.smtp.auth", smtpAuth);
		props.put("spring.mail.properties.mail.smtp.connectiontimeout", smtpConnectiontimeout);
		props.put("spring.mail.properties.mail.smtp.timeout", smtpTimeout);
		props.put("spring.mail.properties.mail.smtp.writetimeout", smtpWriteTimeout);
		mailSender.setJavaMailProperties(props);
		return mailSender;

	}

}
