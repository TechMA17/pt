package com.citi.posttradeanalyzer.service;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;

public class Receiver implements MessageListener {
	
	@Resource(mappedName = "java:jboss/exported/jms/queue/Fix")
	private Queue fixQ;
	
	@Resource(mappedName="java:jboss/exported/jms/RemoteConnectionFactory")
	private QueueConnectionFactory queueConnectionFactory;
	
	private QueueConnection queueConnection;
	private QueueSession queueSession;
	private QueueReceiver queueReceiver;
	private boolean quit = false;
	
	@Inject 
	Parser ps;
	
	@PostConstruct
	private void init() throws JMSException {
		queueConnection = queueConnectionFactory.createQueueConnection(MyConstants.JMS_USERNAME, MyConstants.JMS_PASSWORD);
		queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		queueReceiver = queueSession.createReceiver(fixQ);
		queueReceiver.setMessageListener(this);
		queueConnection.start();
	}
	public void startListen() throws Exception {
		System.out.println("Receiver is ready to receive messages");
		synchronized (this) {
			while (!quit) {
				try {
					wait();
				} 
				catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
		}
		close();
	}

	public void onMessage(Message msg) {
		
		try {
			String msgText;
			if (msg instanceof TextMessage) {
				msgText = ((TextMessage) msg).getText();
			} 
			else {
				msgText = msg.toString();
			}
			
			System.out.printf("\n<Msg_Receiver>%s", msgText);
			
			if (msgText.equalsIgnoreCase("quit")) {
				synchronized (this) {
					quit = true;
					this.notifyAll();
				}
			}
			ps.parseFix(msgText);
		} 
		catch (JMSException ex) {
			ex.printStackTrace();
		}
	}

	@PreDestroy
	private void close() throws JMSException {
		queueReceiver.close();
		queueSession.close();
		queueConnection.close();
	}
}