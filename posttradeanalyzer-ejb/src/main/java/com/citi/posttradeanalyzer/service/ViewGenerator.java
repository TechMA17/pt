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
		createOrderLastTransactTimeView();
	}

	public void createCompletedOrderView() {
		Query query = em.createNativeQuery("CREATE OR REPLACE VIEW CompletedOrders AS ("
				+ "	SELECT o.CIOrdID, o.account, o.price * o.orderQty as amount, o.price, o.side, o.symbol, o.orderQty, o.timeInForce, e.transactTime as originalTime, DATE_FORMAT(e.transactTime, '%Y-%m-%d %H:%i') as transactTime"
				+ "	from OrderSingle o" + "	join ExecutionReport e on o.CIOrdID = e.CIOrdID"
				+ "	where execType = 2 and ordStatus = 2)");

		query.executeUpdate();
	}

	public void createCanceledTradeView() {
		Query query = em.createNativeQuery("CREATE OR REPLACE VIEW CanceledTrades AS "
				+ "	(SELECT o.CIOrdID, o.side, o.symbol, o.orderQty, DATE_FORMAT(e.transactTime, '%Y-%m-%d %H:%i') as transactTime"
				+ "	from OrderSingle o" + "	join ExecutionReport e on o.CIOrdID = e.CIOrdID"
				+ "	where execType = 4 and ordStatus = 4)");

		query.executeUpdate();
	}

	public void createOrderLastTransactTimeView() {
		Query query = em.createNativeQuery("CREATE OR REPLACE VIEW OrderLastTransactTime AS "
				+ "(select distinct o.CIOrdId, DATE_FORMAT(e.transactTime, '%Y-%m-%d %H:%i') as transactTime from ordersingle o join executionReport e on o.CIOrdId = e.CIOrdId "
				+ "where o.timeInForce = 0 and e.transactTime in (select max(e1.transactTime) from executionReport e1 where e1.CIOrdId = o.CIOrdId))");

		query.executeUpdate();
		System.out.println("created view");
	}
}
