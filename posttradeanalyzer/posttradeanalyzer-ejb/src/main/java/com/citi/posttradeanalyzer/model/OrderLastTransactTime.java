package com.citi.posttradeanalyzer.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the orderlasttransacttime database table.
 * 
 */
@Entity
@NamedQuery(name="OrderLastTransactTime.findAll", query="SELECT o FROM OrderLastTransactTime o")
public class OrderLastTransactTime implements Serializable {
	private static final long serialVersionUID = 1L;

	private String CIOrdId;
	private String transactTime;

	public OrderLastTransactTime() {
	}

	@Id
	public String getCIOrdId() {
		return this.CIOrdId;
	}

	public void setCIOrdId(String CIOrdId) {
		this.CIOrdId = CIOrdId;
	}

	public String getTransactTime() {
		return this.transactTime;
	}

	public void setTransactTime(String transactTime) {
		this.transactTime = transactTime;
	}

	@Override
	public String toString() {
		return "OrderLastTransactTime [CIOrdId=" + CIOrdId + ", transactTime=" + transactTime + "]";
	}
}