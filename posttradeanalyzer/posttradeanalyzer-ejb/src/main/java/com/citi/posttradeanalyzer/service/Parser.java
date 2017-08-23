package com.citi.posttradeanalyzer.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.citi.posttradeanalyzer.data.QueryEngine;

import quickfix.ConfigError;
import quickfix.DataDictionary;
import quickfix.DefaultMessageFactory;
import quickfix.FieldNotFound;
import quickfix.InvalidMessage;
import quickfix.Message;
import quickfix.MessageFactory;
import quickfix.MessageUtils;
import quickfix.field.Account;
import quickfix.field.ClOrdID;
import quickfix.field.ExecType;
import quickfix.field.MsgType;
import quickfix.field.OrdStatus;
import quickfix.field.OrdType;
import quickfix.field.OrderID;
import quickfix.field.OrderQty;
import quickfix.field.Price;
import quickfix.field.SendingTime;
import quickfix.field.Side;
import quickfix.field.Symbol;
import quickfix.field.TimeInForce;
import quickfix.field.TransactTime;

@Stateless
public class Parser {

	@Inject
	DataInsertor insertor;
	@Inject
	QueryEngine qe;
	@Inject
	ViewGenerator vg;
	Map<String,String> reports = new HashMap<String,String>();
	
	public void parseFix(String fixMsg) {

		MessageFactory messageFactory = new DefaultMessageFactory();
		DataDictionary dataDictionary = null;
		Message message = null;
		
		ClassLoader classLoader = getClass().getClassLoader();
		try {
			dataDictionary = new DataDictionary(classLoader.getResource("FIX42.xml").getFile());
		} catch (ConfigError e) {
			e.printStackTrace();
		}

		if (fixMsg.equals("quit")) {
			System.out.println("Completed Reading Queue: Creating Views");
			vg.createViews();
		} else {
			try {
				message = MessageUtils.parse(messageFactory, dataDictionary, fixMsg);
			} catch (InvalidMessage e) {
				System.err.println("Message does not conform to FIX42 Standards");
			}
			try {
				if (message.getHeader().getField(new MsgType()).getValue().equals("D")) {
					System.out.println("Parse Order");
					String id = message.getField(new ClOrdID()).getValue();
					//System.out.println(id);
					long orderQty = (long) message.getField(new OrderQty()).getValue();
					//System.out.println("ordQty " + orderQty);
					String account = message.getField(new Account()).getValue();
					//System.out.println("account " + account);
					String ordType = String.valueOf(message.getField(new OrdType()).getValue());
					//System.out.println("ordType " + ordType);
					long price = (long) message.getField(new Price()).getValue();
					//System.out.println("price " + price);
					int side = message.getField(new Side()).getValue();
					//System.out.println("side " + side);
					String symbol = message.getField(new Symbol()).getValue();
					//System.out.println("symbol " + symbol);
					String timeInForce = String.valueOf(message.getField(new TimeInForce()).getValue());
					//System.out.println("timeInForce " + timeInForce);
					long sendingTime = message.getHeader().getField(new SendingTime()).getValue().getTime();
					Timestamp st = new Timestamp(sendingTime);
					//System.out.println("sendingTime " + sendingTime + " " + st);

					insertor.createNewOrderSingle(id, orderQty, account, ordType, price, st, timeInForce, side, symbol);
					
					for(Iterator<Map.Entry<String,String>>it=reports.entrySet().iterator();it.hasNext();){
						Map.Entry<String, String> entry = it.next();
						if(entry.getKey().equals(id))
						{
							parseFix(entry.getValue());
							it.remove();
						}
					}
					
				} else if (message.getHeader().getField(new MsgType()).getValue().equals("8")) {
					System.out.println("Parse Report");
					
					String id = message.getField(new ClOrdID()).getValue();
					//System.out.println(id);
					
					if(qe.findOrderSingleByKey(id)==null) {
						reports.put(id, message.toString());
						System.out.println("Added Report to Pending");
					}else {
						
						String ordStatus = String.valueOf(message.getField(new OrdStatus()).getValue());
						//System.out.println("ordStatus " + ordStatus);
						String orderId = message.getField(new OrderID()).getValue();
						//System.out.println("orderId " + orderId);
						String execType =  String.valueOf(message.getField(new ExecType()).getValue());
						//System.out.println("execType " + execType);
						long transactTime = message.getField(new TransactTime()).getValue().getTime();
						Timestamp tt = new Timestamp(transactTime);
						//System.out.println("transactTime " + transactTime + " " + tt);
						insertor.createNewExecutionReport(ordStatus, tt, execType, orderId, id);
					}
				}
			} catch (FieldNotFound e1) {
			}
		}

	}

}
