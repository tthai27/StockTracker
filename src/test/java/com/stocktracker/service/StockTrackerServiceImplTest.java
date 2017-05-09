//package com.stocktracker.service;
//
//import static org.junit.Assert.assertEquals;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.stocktracker.model.StockTrackerModel;
//
//
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {StockTrackerTest.class})
//public class StockTrackerServiceImplTest {
//	
//	private StockTrackerService stockTrackerService;
//	
//	@Autowired
//	public void setStockTrackerService(StockTrackerService stockTrackerService){
//		this.stockTrackerService = stockTrackerService;
//	}
//	
//	@Test
//	public void testGetSymbolInfoService(){
//		String ticker = "ACAD";
//		StockTrackerModel model = stockTrackerService.getSymbolInfoService(ticker);
//		assertEquals(model.getCompanyName(),"ACADIA Pharmaceuticals Inc.");
//		assertEquals(model.getSymbol(),"ACAD");
//	}
//}
