package com.citi.posttradeanalyzer.data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.citi.posttradeanalyzer.model.CanceledTrade;
import com.citi.posttradeanalyzer.model.CompletedOrder;
import com.citi.posttradeanalyzer.model.ExecutionReport;
import com.citi.posttradeanalyzer.model.OrderLastTransactTime;
import com.citi.posttradeanalyzer.model.OrderSingle;
import com.citi.posttradeanalyzer.model.TradeVolumeView;

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
		OrderSingle order = findOrderSingleByKey(CIOrdId);
		System.out.println(order);
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
		TypedQuery<CompletedOrder> query = em.createQuery("select c from CompletedOrder c", CompletedOrder.class);

		List<CompletedOrder> completedOrders = new ArrayList<>();

		completedOrders = query.getResultList();
		for (CompletedOrder completedOrder : completedOrders) {
			System.out.println(completedOrder);
		}
	}

	public void displayCanceledTradeView() {
		TypedQuery<CanceledTrade> query = em.createQuery("select c from CanceledTrade c", CanceledTrade.class);

		List<CanceledTrade> canceledOrders = new ArrayList<>();

		canceledOrders = query.getResultList();
		for (CanceledTrade canceledOrder : canceledOrders) {
			System.out.println(canceledOrder);
		}
	}

	public HashMap<String, String> getNumberOfIOCOrder() {
		System.out.println("inside getNumberOfIOCOrder");

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> query = cb.createTupleQuery();

		Root<OrderLastTransactTime> root = query.from(OrderLastTransactTime.class);

		query.select(cb.tuple(cb.count(root), root.get("transactTime")));
		query.groupBy(root.get("transactTime"));

		List<Tuple> results = em.createQuery(query).getResultList();

		HashMap<String, String> map = new HashMap<>();
		for (Tuple result : results) {
			map.put(String.valueOf(result.get(1)), String.valueOf(result.get(0)));
		}
		return map;
	}

	public HashMap<String, String> getNumberOfIOCFilledOrder() {
		System.out.println("inside getNumberOfIOCFilledOrder");

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> query = cb.createTupleQuery();
		Root<CompletedOrder> root = query.from(CompletedOrder.class);

		query.select(cb.tuple(cb.count(root), root.get("transactTime")));
		Predicate p1 = cb.equal(root.get("timeInForce"), "0");

		query.groupBy(root.get("transactTime"));
		query.where(cb.and(p1));

		List<Tuple> results = em.createQuery(query).getResultList();

		HashMap<String, String> map = new HashMap<>();

		for (Tuple result : results) {
			map.put(String.valueOf(result.get(1)), String.valueOf(result.get(0)));
		}
		return map;
	}

	// [ [time, amount] ordered by time asc
	public ArrayList<ArrayList<String>> getCommissionAndTime() {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> query = cb.createTupleQuery();
		Root<CompletedOrder> root = query.from(CompletedOrder.class);

		query.select(cb.tuple(cb.sum(root.<Long>get("amount")), root.get("transactTime")));
		query.groupBy(root.get("transactTime"));
		query.orderBy(cb.asc(root.get("transactTime")));
		List<Tuple> results = em.createQuery(query).getResultList();

		ArrayList<ArrayList<String>> arr = new ArrayList<ArrayList<String>>();
		ArrayList<String> innerArr = new ArrayList<>();

		for (Tuple result : results) {
			innerArr = new ArrayList<>();
			innerArr.add((String) result.get(1));
			BigDecimal amount = new BigDecimal(String.valueOf(result.get(0)));
			BigDecimal earnedAmount = amount.multiply(new BigDecimal("0.02"));
			innerArr.add(String.valueOf(earnedAmount));
			arr.add(innerArr);
		}
		return arr;
	}

	// [ [account, cost] ]
	public ArrayList<ArrayList<String>> getClientCost() {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> query = cb.createTupleQuery();
		Root<CompletedOrder> root = query.from(CompletedOrder.class);

		query.select(cb.tuple(cb.sum(root.<Long>get("amount")), root.get("account")));
		query.groupBy(root.get("account"));
		List<Tuple> results = em.createQuery(query).getResultList();

		ArrayList<ArrayList<String>> arr = new ArrayList<ArrayList<String>>();
		ArrayList<String> innerArr = new ArrayList<>();

		for (Tuple result : results) {
			innerArr = new ArrayList<>();
			innerArr.add((String) result.get(1));
			BigDecimal amount = new BigDecimal(String.valueOf(result.get(0)));
			BigDecimal cost = amount.multiply(new BigDecimal("0.02"));
			innerArr.add(String.valueOf(cost));
			arr.add(innerArr);
		}
		return arr;
	}

	public ArrayList<ArrayList<String>> getSamplePricePerMinute(String symbol) {
		return null;
	}

	public ArrayList<ArrayList<String>> getVwampValueEveryFiveMins(String symbol) {
		return null;
	}

	public double getTotalCommission() {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = cb.createQuery(Long.class);
		Root<CompletedOrder> root = query.from(CompletedOrder.class);
		query.select(cb.sum(root.<Long>get("amount")));

		Long amount = em.createQuery(query).getSingleResult();
		BigDecimal amt = new BigDecimal(String.valueOf(amount));
		BigDecimal commission = amt.multiply(new BigDecimal("0.02"));
		return commission.doubleValue();
	}

	public long getTotalNumberOfCanceledTrades() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = cb.createQuery(Long.class);
		Root<CanceledTrade> root = query.from(CanceledTrade.class);

		query.select(cb.count(root));
		long num = em.createQuery(query).getSingleResult();
		return num;
	}

	public long getTotalNumberOfCompletedOrders() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = cb.createQuery(Long.class);
		Root<CompletedOrder> root = query.from(CompletedOrder.class);

		query.select(cb.count(root));
		long num = em.createQuery(query).getSingleResult();
		return num;
	}

	public long getTotalNumberOfOrders() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = cb.createQuery(Long.class);
		Root<OrderSingle> root = query.from(OrderSingle.class);

		query.select(cb.count(root.get("CIOrdId")));
		long num = em.createQuery(query).getSingleResult();
		return num;
	}

	public ArrayList<String> getSymbols() {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<String> query = cb.createQuery(String.class);
		Root<OrderSingle> root = query.from(OrderSingle.class);

		query.select(root.<String>get("symbol")).distinct(true);
		ArrayList<String> symbols = new ArrayList<>();
		List<String> results = em.createQuery(query).getResultList();
		for (String symbol : results) {
			symbols.add(symbol);
		}
		return symbols;
	}
	
	//[ [time, sumQty] ]
	public ArrayList<ArrayList<String>> getTradeVolumeByFiveMinInterval(String symbol) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> query = cb.createTupleQuery();
		Root<TradeVolumeView> root = query.from(TradeVolumeView.class);
		
		query.select(cb.tuple(root.get("sumQty"), root.get("startTime")));
		Predicate p1 = cb.equal(root.get("symbol"), symbol);
		query.where(cb.and(p1));
		
		List<Tuple> results = em.createQuery(query).getResultList();
		ArrayList<ArrayList<String>> arr = new ArrayList<ArrayList<String>>();
		ArrayList<String> innerArr = new ArrayList<>();
		
		for (Tuple result : results) {
			innerArr = new ArrayList<>();
			innerArr.add(String.valueOf(result.get(1)));
			innerArr.add(String.valueOf(result.get(0)));
			arr.add(innerArr);
		}
		return arr;
	}
}
