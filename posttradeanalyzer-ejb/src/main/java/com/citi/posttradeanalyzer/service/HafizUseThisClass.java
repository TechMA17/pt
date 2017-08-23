package com.citi.posttradeanalyzer.service;

import java.sql.Timestamp;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.citi.posttradeanalyzer.data.QueryEngine;

/**
 * @author Mengying
 */

@Stateless
public class HafizUseThisClass {

	@Inject
	DataInsertor insertor;

	@Inject
	QueryEngine qe;

	@Inject
	ViewGenerator vg;

	public void createObjectAndInsertIntoDB() {

		// create new OrderSingle object and insert into db
		// insertor.createNewOrderSingle("0707070", 100, "lmy", "4", 890,
		// Timestamp.valueOf("2011-10-02 18:48:05.123"), "4", 0, "Apple");
		// System.out.println("\n");

		/*
		 * // create new ExecutionReport object and insert into db
		 * insertor.createNewExecutionReport(BigDecimal.valueOf(10.00), 0,
		 * BigDecimal.valueOf(10.00), 0, 0, "6",
		 * Timestamp.valueOf("2011-10-02 18:48:05.123"), "5", "2", "dataInsertorTest");
		 * 
		 * //create views vg.createViews();
		 * 
		 */
		// testing

		//vg.createViews();

		/*
		 * qe.findAllOrderSingle(); System.out.println("\n\n");
		 * 
		 * qe.findOrderSingleByKey("dataInsertorTest"); System.out.println("\n\n");
		 * 
		 * qe.findAllExectutionReports(); System.out.println("\n\n");
		 */

		/*
		 * qe.findExecutionReportByCIOrdId("20170808-1053241626");
		 * System.out.println("\n\n");
		 * 
		 * 
		 * qe.findOrderSingleReports("20170808-1053241626"); System.out.println("\n\n");
		 * 
		 * 
		 * qe.displayCompletedOrderView(); System.out.println("\n\n");
		 * 
		 * qe.displayCanceledTradeView();
		 */
	}
}
