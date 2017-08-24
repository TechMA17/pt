package com.citi.posttradeanalyzer.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * @author Mengying
 */

@Stateless
public class ViewGenerator {

	@Inject
	EntityManager em;

	public void createViews() {
		createCompletedOrderView();
		createCanceledTradeView();
	}

	public void createCompletedOrderView() {
		Query query = em.createNativeQuery("CREATE OR REPLACE VIEW CompletedOrders AS ("
				+ "	SELECT o.CIOrdID, o.price, o.side, o.symbol, o.orderQty, e.TransactTime" + "	from OrderSingle o"
				+ "	join ExecutionReport e on o.CIOrdID = e.CIOrdID" + "	where execType = 2 and ordStatus = 2)");

		query.executeUpdate();
	}
	
	public void createCanceledTradeView() {
		Query query = em.createNativeQuery("CREATE OR REPLACE VIEW CanceledTrades AS "
				+ "	(SELECT o.CIOrdID, o.side, o.symbol, o.orderQty, e.TransactTime" + "	from OrderSingle o"
				+ "	join ExecutionReport e on o.CIOrdID = e.CIOrdID" + "	where execType = 4 and ordStatus = 4)");

		query.executeUpdate();
	}
}
