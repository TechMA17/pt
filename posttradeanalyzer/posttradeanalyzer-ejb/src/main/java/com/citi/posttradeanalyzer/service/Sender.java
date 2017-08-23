package com.citi.posttradeanalyzer.service;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.enterprise.context.Dependent;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;

@Dependent
public class Sender {

	@Resource(mappedName = "java:jboss/exported/jms/queue/Fix")
	private Queue fixQ;
	
	@Resource(mappedName = "java:jboss/exported/jms/RemoteConnectionFactory")
	private QueueConnectionFactory queueConnectionFactory;

	private QueueConnection queueConnection;
	private QueueSession queueSession;
	private QueueSender queueSender;

	@PostConstruct
	public void init() throws JMSException {
		queueConnection = queueConnectionFactory.createQueueConnection(MyConstants.JMS_USERNAME,
				MyConstants.JMS_PASSWORD);
		queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		queueSender = queueSession.createSender(fixQ);
		queueConnection.start();
	}

	public void sendMessages(String msg) throws JMSException {
		TextMessage aMessage = queueSession.createTextMessage(msg);
		queueSender.send(aMessage);
	}

	@PreDestroy
	public void close() throws JMSException {
		queueSender.close();
		queueSession.close();
		queueConnection.close();
	}
}