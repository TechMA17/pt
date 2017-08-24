package com.citi.posttradeanalyzer.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.math.BigInteger;

/**
 * 
 * @author Mengying
 *
 */


@Entity
@NamedQuery(name="ExecutionReport.findAll", query="SELECT e FROM ExecutionReport e")
public class ExecutionReport implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private ExecutionReportPK id;
	private BigDecimal avgPx;
	private long cumQty;
	private BigDecimal lastPx;
	private long lastShares;
	private long leavesQty;
	private String ordStatus;
	private Timestamp transactTime;


	private OrderSingle orderSingle;

	public ExecutionReport() {
	}
	
	@EmbeddedId
	public ExecutionReportPK getId() {
		return this.id;
	}

	public void setId(ExecutionReportPK id) {
		this.id = id;
	}

	public BigDecimal getAvgPx() {
		return this.avgPx;
	}

	public void setAvgPx(BigDecimal avgPx) {
		this.avgPx = avgPx;
	}

	public long getCumQty() {
		return this.cumQty;
	}

	public void setCumQty(long cumQty) {
		this.cumQty = cumQty;
	}

	public BigDecimal getLastPx() {
		return this.lastPx;
	}

	public void setLastPx(BigDecimal lastPx) {
		this.lastPx = lastPx;
	}

	public long getLastShares() {
		return this.lastShares;
	}

	public void setLastShares(long lastShares) {
		this.lastShares = lastShares;
	}

	public long getLeavesQty() {
		return this.leavesQty;
	}

	public void setLeavesQty(long leavesQty) {
		this.leavesQty = leavesQty;
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
	
	@ManyToOne
	@JoinColumn(name="CIOrdId")
	public OrderSingle getOrderSingle() {
		return this.orderSingle;
	}

	public void setOrderSingle(OrderSingle orderSingle) {
		this.orderSingle = orderSingle;
	}

	@Override
	public String toString() {
		return "ExecutionReport [id=" + id + ", avgPx=" + avgPx + ", cumQty=" + cumQty + ", lastPx=" + lastPx
				+ ", lastShares=" + lastShares + ", leavesQty=" + leavesQty + ", ordStatus=" + ordStatus
				+ ", transactTime=" + transactTime + ", orderSingle=" + orderSingle + "]";
	}
}