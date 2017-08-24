package com.citi.posttradeanalyzer.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

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
		
    	OrderSingle order = new OrderSingle();
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
    	creator.createNewOrderSingle(order);
	}
	
	public void createNewExecutionReport(BigDecimal avgPx, long cumQty, BigDecimal lastPx, long lastShares, long leavesQty,
		String ordStatus, Timestamp transactTime, String execType, String orderId, String CIOrdId) {
			
			ExecutionReport report = new ExecutionReport();
			report.setAvgPx(avgPx);
			report.setCumQty(cumQty);
			report.setLastPx(lastPx);
	    	report.setLastShares(lastShares);
	    	report.setLeavesQty(leavesQty);
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
