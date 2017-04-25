package com.stocktracker.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stocktracker.mappers.StockTrackerMapper;
import com.stocktracker.model.StockTrackerModel;
import com.stocktracker.service.StockTrackerService;

@Service
public class StockTrackerServiceImpl implements StockTrackerService{
	
	@Autowired
	StockTrackerMapper stockTrackerMapper;

	@Override
	public List<StockTrackerModel> getWatchListService(HttpServletRequest request, List<StockTrackerModel> watchList) {
		// TODO Auto-generated method stub
		//get updated info
		for(int i=0;i<watchList.size();i++){
			double previousPrice = watchList.get(i).getLastTradePrice();
			StockTrackerModel stockInfo = getSymbolInfoService(watchList.get(i).getSymbol());
			
			double lastTradePrice = stockInfo.getLastTradePrice();
			int sharesOwned = watchList.get(i).getSharesOwned();
			watchList.get(i).setSymbol(stockInfo.getSymbol().toUpperCase());
			watchList.get(i).setCompanyName(stockInfo.getCompanyName());
			watchList.get(i).setLastTradePrice(lastTradePrice);
			watchList.get(i).setChange(stockInfo.getChange());
			watchList.get(i).setDaysRange(stockInfo.getDaysRange());
			watchList.get(i).setSharesOwned(sharesOwned);
			watchList.get(i).setMarketValue(stockInfo.getLastTradePrice() * sharesOwned);
			
			int change =0;
			if(lastTradePrice < previousPrice){
				change = -1;
			}else if(lastTradePrice > previousPrice){ 
				change = 1;
			}
			watchList.get(i).setLastChangeArrow(change);
		}
		
		return watchList;
	}

	@Override
	public List<StockTrackerModel> addWatchListService(List<StockTrackerModel> watchList, String symbol,int shares) {
		// TODO Auto-generated method stub
		for(int i=0;i<watchList.size();i++){
			if (symbol.equalsIgnoreCase(watchList.get(i).getSymbol())){
				return watchList;
			}
		}
		
		StockTrackerModel model = getSymbolInfoService(symbol);
		model.setSharesOwned(shares);
		model.setMarketValue(model.getLastTradePrice() * shares);
		watchList.add(model);
		
		
		return watchList;
	}
	
	@Override
	public List<StockTrackerModel> removeWatchListService(List<StockTrackerModel> watchList, String symbol) {
		// TODO Auto-generated method stub
		
		for(int i=0;i<watchList.size();i++){
			String ticker = watchList.get(i).getSymbol();
			if(symbol.equalsIgnoreCase(ticker)){
				watchList.remove(i);
				break;
			}
		}
		
		return watchList;
	}
	
	@Override
	public  List<StockTrackerModel> getWatchListInSessionService(HttpServletRequest request) {
		// TODO Auto-generated method stub
		 HttpSession session = request.getSession();
		 List<StockTrackerModel> watchList = new ArrayList<StockTrackerModel>();
		 if(null != session.getAttribute("watchList")){
			 watchList = (List<StockTrackerModel>) session.getAttribute("watchList");
		 }
	
		return watchList;
	}
	@Override
	public void setWatchListInSessionService(HttpServletRequest request,List<StockTrackerModel> watchList){
		HttpSession session = request.getSession();
		//watchList = getWatchListService(request);
		session.setAttribute("watchList", watchList);
	}

	@Override
	public StockTrackerModel getSymbolInfoService(String symbol) {
		//GET ALL TICKER INFO
		// TODO Auto-generated method stub
		//call api
		System.out.println("getSymbolInfoService "+ symbol);
		StockTrackerModel model = new StockTrackerModel();
		boolean home = false;
		if(home){
			
		
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("https://query.yahooapis.com/v1/public/yql?");
					sb.append("q=select%20*%20from%20yahoo.finance.quote%20where%20symbol%20in%20(%22"+symbol+"%22)");
					sb.append("&format=json");
					sb.append("&diagnostics=true");
					sb.append("&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys");
					sb.append("&callback=");
			URL url=new URL(sb.toString());  
			URLConnection urlcon=url.openConnection(); 
			InputStream stream=urlcon.getInputStream();
			String jsonString = getResponseBodyJSON(stream);
			JSONParser parser = new JSONParser();
			JSONObject json = new JSONObject();
			json = (JSONObject) parser.parse(jsonString);
			json = (JSONObject) parser.parse(json.get("query").toString());
			json = (JSONObject) parser.parse(json.get("results").toString());
			json = (JSONObject) parser.parse(json.get("quote").toString());
			System.out.println(json.toString());
			
			model.setCompanyName(json.get("Name").toString());
			model.setSymbol(json.get("Symbol").toString());
			model.setChange(Double.parseDouble(json.get("Change").toString()));
			model.setLastTradePrice( Double.parseDouble(json.get("LastTradePriceOnly").toString()));
			model.setDaysRange(json.get("DaysRange").toString());
		//	System.out.println(json.get("Symbol") + " "+ json.get("LastTradePriceOnly")) ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		}else{
			if(symbol.equalsIgnoreCase("ACAD")){
				model.setCompanyName("ACADIA Pharmaceuticals Inc.");
				model.setSymbol("ACAD");
				model.setLastTradePrice(33.40);
				model.setDaysRange("33.01 - 35.00");
			}else if(symbol.equalsIgnoreCase("IDRA")){
				model.setCompanyName("Idera Pharam");
				model.setSymbol("IDRA");
				model.setLastTradePrice(2.01);
				model.setDaysRange("2.01 - 2.10");
			}else if(symbol.equalsIgnoreCase("KITE")){
				model.setCompanyName("KITE Pharam");
				model.setSymbol("KITE");
				model.setLastTradePrice(79.52);
				model.setDaysRange("70.01 - 70.10");
			}
		}

	
		return model;
	}
	
	public String getResponseBodyJSON(InputStream stream)
			throws Exception{
		StringBuffer sb = new StringBuffer();
		int i;  
		while((i=stream.read())!=-1){ 
			char c = (char)i;
			sb.append(Character.toString(c));
		//	System.out.print((char)i);  
		}
		return sb.toString();
	}






	

		
	
}
