package com.stocktracker.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.stocktracker.model.StockTrackerModel;

public interface StockTrackerService {
	public List<StockTrackerModel> getWatchListService(HttpServletRequest request, List<StockTrackerModel> watchList);
	public List<StockTrackerModel> addWatchListService(List<StockTrackerModel> watchList, String symbol, int shares);
	public List<StockTrackerModel> removeWatchListService(List<StockTrackerModel> watchList, String symbol);
	
	public void setWatchListInSessionService(HttpServletRequest request, List<StockTrackerModel> watchList);
	public  List<StockTrackerModel> getWatchListInSessionService(HttpServletRequest request);

	public StockTrackerModel getSymbolInfoService(String symbol);
	

}
