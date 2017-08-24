package com.citi.posttradeanalyzer.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigInteger;



@Entity
@Table(name="completedorders")
@NamedQuery(name="CompletedOrder.findAll", query="SELECT c FROM CompletedOrder c")
public class CompletedOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	private String CIOrdID;
	private long orderQty;
	private long price;
	private int side;
	private String symbol;
	private Timestamp transactTime;

	public CompletedOrder() {
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

	public Timestamp getTransactTime() {
		return this.transactTime;
	}

	public void setTransactTime(Timestamp transactTime) {
		this.transactTime = transactTime;
	}

	@Override
	public String toString() {
		return "CompletedOrder [CIOrdID=" + CIOrdID + ", orderQty=" + orderQty + ", price=" + price + ", side=" + side
				+ ", symbol=" + symbol + ", transactTime=" + transactTime + "]";
	}
}