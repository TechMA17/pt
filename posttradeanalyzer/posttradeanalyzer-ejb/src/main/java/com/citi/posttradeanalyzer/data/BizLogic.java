package com.citi.posttradeanalyzer.data;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.citi.posttradeanalyzer.model.Member;
import com.citi.posttradeanalyzer.model.OrderSingle;

/*
 * @author Mengying
 * 
 */


@ApplicationScoped
public class BizLogic {
	
	@Inject
	private EntityManager em;
	
	public int getTotalNumOfOrders() {
		return 1992;
	}
	
	public void displayOrderSingle() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<OrderSingle> criteria = cb.createQuery(OrderSingle.class);
        Root<OrderSingle> order = criteria.from(OrderSingle.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
        criteria.select(order);
        System.out.println(em.createQuery(criteria).getResultList());
	}

}
