package com.citi.posttradeanalyzer.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigInteger;


@Entity
@Table(name="canceledtrades")
@NamedQuery(name="CanceledTrade.findAll", query="SELECT c FROM CanceledTrade c")
public class CanceledTrade implements Serializable {
	private static final long serialVersionUID = 1L;

	private String CIOrdID;
	private long orderQty;
	private int side;
	private String symbol;
	private Timestamp transactTime;

	public CanceledTrade() {
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
		return "CanceledTrade [CIOrdID=" + CIOrdID + ", orderQty=" + orderQty + ", side=" + side + ", symbol=" + symbol
				+ ", transactTime=" + transactTime + "]";
	}
}