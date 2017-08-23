package com.citi.posttradeanalyzer.model;

import java.io.Serializable;
import javax.persistence.*;


@Embeddable
public class ExecutionReportPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String orderId;

	private String execType;

	public ExecutionReportPK() {
	}
	public String getOrderId() {
		return this.orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getExecType() {
		return this.execType;
	}
	public void setExecType(String execType) {
		this.execType = execType;
	}
	
	@Override
	public String toString() {
		return "ExecutionReportPK [orderId=" + orderId + ", execType=" + execType + "]";
	}
	
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ExecutionReportPK)) {
			return false;
		}
		ExecutionReportPK castOther = (ExecutionReportPK)other;
		return 
			this.orderId.equals(castOther.orderId)
			&& this.execType.equals(castOther.execType);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.orderId.hashCode();
		hash = hash * prime + this.execType.hashCode();
		
		return hash;
	}
}