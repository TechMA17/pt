package com.citi.posttradeanalyzer.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.citi.posttradeanalyzer.model.OrderSingle;

/*
 * @author Mengying
 * 
 */

@ApplicationScoped
public class BizLogic {

	@Inject
	private EntityManager em;

	public String getErrorMessage() {
		return "invalid option";
	}

	public void getClientCost() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> criteria = cb.createTupleQuery();
		Root<OrderSingle> root = criteria.from(OrderSingle.class);

		Join<Object, Object> reports = root.join("executionReports");
		Predicate p1 = cb.equal(reports.get("id").get("execType"), "2");

		criteria.select(cb.tuple(root.get("CIOrdId"), root.get("account"), root.get("price")));
		criteria.where(cb.and(p1));

		List<Tuple> results = em.createQuery(criteria).getResultList();
		for (Tuple result : results) {
			System.out.println(result.get(0) + ", " + result.get(1) + ", " + result.get(2));
		}
	}
	
	public void getCommision() {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> criteria = cb.createTupleQuery();
		Root<OrderSingle> root = criteria.from(OrderSingle.class);

		Join<Object, Object> reports = root.join("executionReports");
		Predicate p1 = cb.equal(reports.get("id").get("execType"), "2");

		criteria.select(cb.tuple(root.get("CIOrdId"), root.get("account"), root.get("price")));
		criteria.where(cb.and(p1));

		List<Tuple> results = em.createQuery(criteria).getResultList();
		for (Tuple result : results) {
			System.out.println(result.get(0) + ", " + result.get(1) + ", " + result.get(2));
		}
	}
}
