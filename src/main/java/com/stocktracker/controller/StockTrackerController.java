package com.stocktracker.controller;

import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.AmazonSQSException;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.ListQueuesResult;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.google.gson.Gson;
import com.stocktracker.model.StockTrackerModel;
import com.stocktracker.service.StockTrackerService;

@Controller
public class StockTrackerController {
	
	Logger logger =  Logger.getLogger(StockTrackerController.class);   
	@Autowired
	StockTrackerService stockTrackerService;
	

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String defaultPage() {
		logger.info("defaultPage");//aasd
		
		return "index";
	}
	
	@RequestMapping("/home.do")
	public ModelAndView redirectHome() {
		System.out.println("IN HOME");
		return new ModelAndView("home");
	}
	
	@RequestMapping(value="/getSymbolInfo.do", method={RequestMethod.GET})
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
//		gson.fromJson("", StockTrackerModel.class);
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
