package com.citi.posttradeanalyzer.rest;

public class MockOrderData {
	
	private String sendingTime;
	
	private long clOrdID;
	private String account;
	private int ordQty;
	private String ordType;
	private long sharePrice;
	private int buySell;
	private String stockSymbol;
	private String timeInForce;
	
	
	
	public MockOrderData() {}
	
	public MockOrderData(String sendingTime, long clOrdID, String account, int ordQty, String ordType, long sharePrice,
			int buySell, String stockSymbol, String timeInForce) {

		this.sendingTime = sendingTime;
		this.clOrdID = clOrdID;
		this.account = account;
		this.ordQty = ordQty;
		this.ordType = ordType;
		this.sharePrice = sharePrice;
		this.buySell = buySell;
		this.stockSymbol = stockSymbol;
		this.timeInForce = timeInForce;
	}

	public String getSendingTime() {
		return sendingTime;
	}

	public void setSendingTime(String sendingTime) {
		this.sendingTime = sendingTime;
	}

	public long getClOrdID() {
		return clOrdID;
	}

	public void setClOrdID(long clOrdID) {
		this.clOrdID = clOrdID;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public int getOrdQty() {
		return ordQty;
	}

	public void setOrdQty(int ordQty) {
		this.ordQty = ordQty;
	}

	public String getOrdType() {
		return ordType;
	}

	public void setOrdType(String ordType) {
		this.ordType = ordType;
	}

	public long getSharePrice() {
		return sharePrice;
	}

	public void setSharePrice(long sharePrice) {
		this.sharePrice = sharePrice;
	}

	public int getBuySell() {
		return buySell;
	}

	public void setBuySell(int buySell) {
		this.buySell = buySell;
	}

	public String getStockSymbol() {
		return stockSymbol;
	}

	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	public String getTimeInForce() {
		return timeInForce;
	}

	public void setTimeInForce(String timeInForce) {
		this.timeInForce = timeInForce;
	}

	@Override
	public String toString() {
		return String.format(
				"OrderSingle [sendingTime=%s, clOrdID=%s, account=%s, ordQty=%s, ordType=%s, sharePrice=%s, buySell=%s, stockSymbol=%s, timeInForce=%s]",
				sendingTime, clOrdID, account, ordQty, ordType, sharePrice, buySell, stockSymbol, timeInForce);
	}
	
	

	

	
	
	
	
	
}
