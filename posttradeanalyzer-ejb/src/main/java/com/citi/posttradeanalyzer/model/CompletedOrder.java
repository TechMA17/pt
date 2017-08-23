package com.citi.posttradeanalyzer.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigInteger;


/**
 * The persistent class for the completedorders database table.
 * 
 */
@Entity
@Table(name="completedorders")
@NamedQuery(name="CompletedOrder.findAll", query="SELECT c FROM CompletedOrder c")
public class CompletedOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	private String account;
	private long amount;
	private String CIOrdID;
	private long orderQty;
	private Timestamp originalTime;
	private long price;
	private int side;
	private String symbol;
	private String timeInForce;
	private String transactTime;

	public CompletedOrder() {
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public long getAmount() {
		return this.amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}
	
	@Id
	public String getCIOrdID() {
		return this.CIOrdID;
	}

	public void setCIOrdID(String CIOrdID) {
		this.CIOrdID = CIOrdID;
	}

	public long getOrderQty() {
		return this.orderQty;
	}

	public void setOrderQty(long orderQty) {
		this.orderQty = orderQty;
	}

	public Timestamp getOriginalTime() {
		return this.originalTime;
	}

	public void setOriginalTime(Timestamp originalTime) {
		this.originalTime = originalTime;
	}

	public long getPrice() {
		return this.price;
	}

	public void setPrice(long price) {
		this.price = price;
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

	public String getTransactTime() {
		return this.transactTime;
	}

	public void setTransactTime(String transactTime) {
		this.transactTime = transactTime;
	}

	@Override
	public String toString() {
		return "CompletedOrder [account=" + account + ", amount=" + amount + ", CIOrdID=" + CIOrdID + ", orderQty="
				+ orderQty + ", originalTime=" + originalTime + ", price=" + price + ", side=" + side + ", symbol="
				+ symbol + ", timeInForce=" + timeInForce + ", transactTime=" + transactTime + "]";
	}
}