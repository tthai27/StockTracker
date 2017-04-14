package com.stocktracker.model;

public class StockTrackerModel {
	
	
	private String companyName;
	private String symbol;
	private double lastTradePrice;
	private int sharesOwned;
	private double marketValue;
	private double change;
	private String daysRange;
	private double lastChangeArrow;
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getSymbol() {
		return symbol;
	}
	@Override
	public String toString() {
		return "StockTrackerModel [ companyName=" + companyName + ", symbol=" + symbol
				+ ", lastTradePrice=" + lastTradePrice + ", sharesOwned=" + sharesOwned + ", marketValue=" + marketValue
				+ "]";
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public double getLastTradePrice() {
		return lastTradePrice;
	}
	public void setLastTradePrice(double lastTradePrice) {
		lastTradePrice = Math.round(lastTradePrice*100.0)/100.0;
		this.lastTradePrice = lastTradePrice;
	}
	public int getSharesOwned() {
		return sharesOwned;
	}
	public void setSharesOwned(int sharesOwned) {
		this.sharesOwned = sharesOwned;
	}
	public double getMarketValue() {
		return marketValue;
	}
	public void setMarketValue(double marketValue) {
		marketValue = Math.round(marketValue*100.0)/100.0;
		this.marketValue = marketValue;
	}
	public double getChange() {
		return change;
	}
	public void setChange(double change) {
		this.change = change;
	}

	public double getLastChangeArrow() {
		return lastChangeArrow;
	}
	public void setLastChangeArrow(double lastChangeArrow) {
		this.lastChangeArrow = lastChangeArrow;
	}
	public String getDaysRange() {
		return daysRange;
	}
	public void setDaysRange(String daysRange) {
		this.daysRange = daysRange;
	}

}
