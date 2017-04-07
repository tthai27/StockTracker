package com.stocktracker.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.stocktracker.model.StockTrackerModel;
import com.stocktracker.service.StockTrackerService;

@Controller
public class StockTrackerController {
//	https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quote%20where%20symbol%20in%20(%22ACAD%22)&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=

	
	@Autowired
	StockTrackerService stockTrackerService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String defaultPage() {
		System.out.println("defaultPage");
		return "index";
	}
	
	@RequestMapping("/home.do")
	public ModelAndView redirectHome() {
		System.out.println("IN HOME");
		return new ModelAndView("home");
	}
	
	@RequestMapping(value="/getSymbolInfo.do", method={RequestMethod.POST})
	@ResponseBody
	public  String getSymbolInfo(@RequestParam(value = "symbol") String symbol) {
		System.out.println("getSymbolInfo " + symbol);
		StockTrackerModel model = stockTrackerService.getSymbolInfoService(symbol);
		
		Gson gson = new Gson();
		String json = gson.toJson(model);
		System.out.println(json);
		return json;
	}
	


	@RequestMapping(value="/getWatchList.do", method={RequestMethod.GET})
	@ResponseBody
	public String getWatchList(HttpServletRequest request) {
		System.out.println("getWatchList");
		List<StockTrackerModel> watchList = stockTrackerService.getWatchListInSessionService(request);
		if(watchList.size() > 0){
			watchList = stockTrackerService.getWatchListService(request,watchList);
		}
		Gson gson = new Gson();
		String json = gson.toJson(watchList);
		return json;
	}
	
	@RequestMapping(value="/addWatchList.do", method={RequestMethod.POST})
	@ResponseBody
	public String addWatchList(HttpServletRequest request, @RequestParam(value = "symbol") String symbol,
			@RequestParam(value = "shares") int shares) {
		//getwatchlist from session
		List<StockTrackerModel> watchList  = stockTrackerService.getWatchListInSessionService(request);
		 watchList = stockTrackerService.addWatchListService(watchList, symbol, shares);
		stockTrackerService.setWatchListInSessionService(request, watchList);
		return "1";
	}
	
	@RequestMapping(value="/removeWatchList.do", method={RequestMethod.POST})
	@ResponseBody
	public String removeWatchList(HttpServletRequest request, @RequestParam(value = "symbol") String symbol) {
		List<StockTrackerModel> watchList  = stockTrackerService.getWatchListInSessionService(request);
		watchList = stockTrackerService.removeWatchListService(watchList, symbol);
		stockTrackerService.setWatchListInSessionService(request, watchList);
		return "1";
	}
}
