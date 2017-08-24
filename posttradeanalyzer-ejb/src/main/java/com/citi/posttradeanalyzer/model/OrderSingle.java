package com.citi.posttradeanalyzer.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * @author Mengying
 */

@Entity
@NamedQuery(name="OrderSingle.findAll", query="SELECT o FROM OrderSingle o")
public class OrderSingle implements Serializable {
	private static final long serialVersionUID = 1L;


	private String CIOrdId;
	private String account;
	private long orderQty;
	private String orderType;
	private long price;
	private Timestamp sendingTime;
	private int side;
	private String symbol;
	private String timeInForce;
	
	private List<ExecutionReport> executionReports = new ArrayList<>();

	public OrderSingle() {
	}
	
	@Id
	public String getCIOrdId() {
		return this.CIOrdId;
	}

	public void setCIOrdId(String CIOrdId) {
		this.CIOrdId = CIOrdId;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public long getOrderQty() {
		return this.orderQty;
	}

	public void setOrderQty(long orderQty) {
		this.orderQty = orderQty;
	}

	public String getOrderType() {
		return this.orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public long getPrice() {
		return this.price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public Timestamp getSendingTime() {
		return this.sendingTime;
	}

	public void setSendingTime(Timestamp sendingTime) {
		this.sendingTime = sendingTime;
	}

	public int getSide() {
		return this.side;
	}

	public void setSide(int side) {
		this.side = side;
	}

	public String getSymbol() {
		return this.symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getTimeInForce() {
		return this.timeInForce;
	}

	public void setTimeInForce(String timeInForce) {
		this.timeInForce = timeInForce;
	}
	
	@OneToMany(mappedBy="orderSingle")
	public List<ExecutionReport> getExecutionReports() {
		return this.executionReports;
	}

	public void setExecutionReports(List<ExecutionReport> executionReports) {
		this.executionReports = executionReports;
	}

	public ExecutionReport addExecutionReport(ExecutionReport executionReport) {
		getExecutionReports().add(executionReport);
		executionReport.setOrderSingle(this);

		return executionReport;
	}

	public ExecutionReport removeExecutionReport(ExecutionReport executionReport) {
		getExecutionReports().remove(executionReport);
		executionReport.setOrderSingle(null);

		return executionReport;
	}

	@Override
	public String toString() {
		return "OrderSingle [CIOrdId=" + CIOrdId + ", account=" + account + ", orderQty=" + orderQty + ", orderType="
				+ orderType + ", price=" + price + ", sendingTime=" + sendingTime + ", side=" + side + ", symbol="
				+ symbol + ", timeInForce=" + timeInForce + "]";
	}
}