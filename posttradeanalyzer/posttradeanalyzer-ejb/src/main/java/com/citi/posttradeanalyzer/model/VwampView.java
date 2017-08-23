package com.citi.posttradeanalyzer.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.math.BigInteger;


/**
 * The persistent class for the vwampview database table.
 * 
 */
@Entity
@NamedQuery(name="VwampView.findAll", query="SELECT v FROM VwampView v")
public class VwampView implements Serializable {
	private static final long serialVersionUID = 1L;

	private String CIOrdId;
	private long newtime;
	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;
	private long sumProduct;
	private String symbol;
	private long totalQty;

	public VwampView() {
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

	public long getSumProduct() {
		return this.sumProduct;
	}

	public void setSumProduct(long sumProduct) {
		this.sumProduct = sumProduct;
	}

	public String getSymbol() {
		return this.symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public long getTotalQty() {
		return this.totalQty;
	}

	public void setTotalQty(long totalQty) {
		this.totalQty = totalQty;
	}

}