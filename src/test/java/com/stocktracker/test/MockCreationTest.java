package com.stocktracker.test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.stocktracker.model.StockTrackerModel;
import com.stocktracker.model.User;
import com.stocktracker.service.StockTrackerService;
import com.stocktracker.service.UserService;

import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MockCreationTest {
	@Mock
	private UserService userService;
	
	@Mock
	private StockTrackerService stockTrackerService;
	
	@Mock
	private User user;
	
	@Mock
	private StockTrackerModel model;
	
	@Before
	public void setupMock(){
//		user = mock(User.class);
//		userService = mock(UserService.class);
		MockitoAnnotations.initMocks(this);
		
	}
	
	@Test
	public void testMockCreation(){
		assertNotNull(user);
		assertNotNull(userService);
	}
	
	@Test
	public void testUserCount(){
		when(userService.getUsersCount()).thenReturn(1);
		assertEquals(1,userService.getUsersCount());
	}
	
	@Test
	public void testStock(){
		String ticker = "ACAD";
		when(stockTrackerService.getSymbolInfoService(ticker)).thenReturn(model);
		verify(stockTrackerService).getSymbolInfoService(ticker);
		InOrder  order = inOrder();
		order.verify(stockTrackerService).getSymbolInfoService(ticker);
		//order.verify(stockTrackerService).addWatchListService(watchList, symbol, shares)
		//assertEquals(stockTrackerService.getSymbolInfoService(ticker).getCompanyName(),model.getCompanyName());
	}
}

