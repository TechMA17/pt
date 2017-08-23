package com.citi.posttradeanalyzer.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;


@Entity
@NamedQuery(name="ExecutionReport.findAll", query="SELECT e FROM ExecutionReport e")
public class ExecutionReport implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ExecutionReportPK id;
	private String ordStatus;
	private Timestamp transactTime;
	
	@ManyToOne
	@JoinColumn(name="CIOrdId")
	private OrderSingle orderSingle;

	public ExecutionReport() {
	}

	public ExecutionReportPK getId() {
		return this.id;
	}

	public void setId(ExecutionReportPK id) {
		this.id = id;
	}

	public String getOrdStatus() {
		return this.ordStatus;
	}

	public void setOrdStatus(String ordStatus) {
		this.ordStatus = ordStatus;
	}

	public Timestamp getTransactTime() {
		return this.transactTime;
	}

	public void setTransactTime(Timestamp transactTime) {
		this.transactTime = transactTime;
	}
	
	public OrderSingle getOrderSingle() {
		return this.orderSingle;
	}

	public void setOrderSingle(OrderSingle orderSingle) {
		this.orderSingle = orderSingle;
	}

	@Override
	public String toString() {
		return "ExecutionReport [id=" + id + ", ordStatus=" + ordStatus + ", transactTime=" + transactTime
				+ ", orderSingle=" + orderSingle + "]";
	}
}