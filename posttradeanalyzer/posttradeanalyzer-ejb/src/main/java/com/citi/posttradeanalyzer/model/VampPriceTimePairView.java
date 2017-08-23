package com.citi.posttradeanalyzer.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.math.BigInteger;


/**
 * The persistent class for the vamppricetimepairview database table.
 * 
 */
@Entity
@NamedQuery(name="VampPriceTimePairView.findAll", query="SELECT v FROM VampPriceTimePairView v")
public class VampPriceTimePairView implements Serializable {
	private static final long serialVersionUID = 1L;

	private String CIOrdId;
	private long newtime;
	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;
	private String symbol;
	private double totalPrice;

	public VampPriceTimePairView() {
	}
	
	@Id
	public String getCIOrdId() {
		return this.CIOrdId;
	}

	public void setCIOrdId(String CIOrdId) {
		this.CIOrdId = CIOrdId;
	}

	public long getNewtime() {
		return this.newtime;
	}

	public void setNewtime(long newtime) {
		this.newtime = newtime;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getSymbol() {
		return this.symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public double getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "VampPriceTimePairView [CIOrdId=" + CIOrdId + ", newtime=" + newtime + ", startTime=" + startTime
				+ ", symbol=" + symbol + ", totalPrice=" + totalPrice + "]";
	}

}