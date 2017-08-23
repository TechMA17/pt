package com.citi.posttradeanalyzer.service;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PersistenceException;

import com.citi.posttradeanalyzer.data.QueryEngine;
import com.citi.posttradeanalyzer.model.ExecutionReport;
import com.citi.posttradeanalyzer.model.ExecutionReportPK;
import com.citi.posttradeanalyzer.model.OrderSingle;

/**
 * @author Mengying
 */

@Stateless
public class DataInsertor {

	@Inject
	OrderSingleCreator creator;

	@Inject
	QueryEngine qe;

	public void createNewOrderSingle(String CIOrdId, long orderQty, String account, String orderType, long price,
			Timestamp sendingTime, String timeInForce, int side, String symbol) {
		
		OrderSingle order = qe.findOrderSingleByKey(CIOrdId);
		if (order == null) {
			order = new OrderSingle();
			order.setCIOrdId(CIOrdId);
			order.setOrderQty(orderQty);
			order.setAccount(account);
			order.setOrderType(orderType);
			order.setPrice(price);
			order.setSendingTime(sendingTime);
			order.setTimeInForce(timeInForce);
			order.setSide(side);
			order.setSymbol(symbol);
			order.setExecutionReports(order.getExecutionReports());
			System.out.println(order);
			creator.createNewOrderSingle(order);
		}
	}

	public void createNewExecutionReport(String ordStatus, Timestamp transactTime, String execType, String orderId, String CIOrdId) {

		ExecutionReport report = new ExecutionReport();
		report.setOrdStatus(ordStatus);
		report.setTransactTime(transactTime);

		ExecutionReportPK keyPair = new ExecutionReportPK();
		keyPair.setExecType(execType);
		keyPair.setOrderId(orderId);
		report.setId(keyPair);

		OrderSingle order = qe.findOrderSingleByKey(CIOrdId);
		if (order == null) {
			throw new IllegalStateException("Base table no corresponding entry!");
		}
		report.setOrderSingle(order);
		creator.createNewExecutionReport(report);
	}

}
