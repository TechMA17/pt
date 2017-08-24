package com.citi.posttradeanalyzer.data;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.citi.posttradeanalyzer.model.CanceledTrade;
import com.citi.posttradeanalyzer.model.CompletedOrder;
import com.citi.posttradeanalyzer.model.ExecutionReport;
import com.citi.posttradeanalyzer.model.OrderSingle;

/**
 * 
 * @author Mengying
 *
 */
@ApplicationScoped
public class QueryEngine {

	@Inject
	EntityManager em;

	public List<OrderSingle> findAllOrderSingle() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<OrderSingle> criteria = cb.createQuery(OrderSingle.class);
		Root<OrderSingle> order = criteria.from(OrderSingle.class);

		criteria.select(order);
		List<OrderSingle> orders = new ArrayList<>();
		orders = em.createQuery(criteria).getResultList();

		for (OrderSingle orderSingle : orders) {
			System.out.println(orderSingle);
		}
		
		return orders;
	}

	public OrderSingle findOrderSingleByKey(String CIOrdId) {
		OrderSingle order = em.find(OrderSingle.class, CIOrdId);
		System.out.println(order);
		return order;
	}
	
	public List<ExecutionReport> findOrderSingleReports(String CIOrdId) {
		OrderSingle order = findOrderSingleByKey("dataInsertorTest");
		System.out.println(order.getExecutionReports());
		return order.getExecutionReports();
	}

	public List<ExecutionReport> findAllExectutionReports() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ExecutionReport> criteria = cb.createQuery(ExecutionReport.class);
		Root<ExecutionReport> report = criteria.from(ExecutionReport.class);

		criteria.select(report);
		List<ExecutionReport> reports = new ArrayList<>();
		reports = em.createQuery(criteria).getResultList();

		for (ExecutionReport r : reports) {
			System.out.println(r);
		}
		return reports;
	}

	public List<ExecutionReport> findExecutionReportByCIOrdId(String CIOrdId) {
		Query query = em.createQuery("select e from ExecutionReport e where CIOrdId = :id");
		query.setParameter("id", CIOrdId);
		
		List<ExecutionReport> list = query.getResultList();
		for (ExecutionReport r : list) {
			System.out.println(r);
		}
		return list;
	}
	
	public void displayCompletedOrderView() {
		TypedQuery<CompletedOrder> query = em.createQuery("select c from CompletedOrder c",
				CompletedOrder.class);

		List<CompletedOrder> completedOrders = new ArrayList<>();

		completedOrders = query.getResultList();
		for (CompletedOrder completedOrder : completedOrders) {
			System.out.println(completedOrder);
		}
	}
	
	public void displayCanceledTradeView() {
		TypedQuery<CanceledTrade> query = em.createQuery("select c from CanceledTrade c",
				CanceledTrade.class);

		List<CanceledTrade> canceledOrders = new ArrayList<>();

		canceledOrders = query.getResultList();
		for (CanceledTrade canceledOrder : canceledOrders) {
			System.out.println(canceledOrder);
		}
	}
}
