package com.citi.posttradeanalyzer.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.math.BigInteger;


/**
 * The persistent class for the tradevolumeview database table.
 * 
 */
@Entity
@NamedQuery(name="TradeVolumeView.findAll", query="SELECT t FROM TradeVolumeView t")
public class TradeVolumeView implements Serializable {
	private static final long serialVersionUID = 1L;

	private String CIOrdId;
	private long newtime;
	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;
	private double sumQty;
	private String symbol;

	public TradeVolumeView() {
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

	public double getSumQty() {
		return this.sumQty;
	}

	public void setSumQty(double sumQty) {
		this.sumQty = sumQty;
	}

	public String getSymbol() {
		return this.symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		return "TradeVolumeView [CIOrdId=" + CIOrdId + ", newtime=" + newtime + ", startTime=" + startTime + ", sumQty="
				+ sumQty + ", symbol=" + symbol + "]";
	}
}