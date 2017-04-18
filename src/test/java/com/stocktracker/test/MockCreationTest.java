package com.stocktracker.test;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stocktracker.model.StockTrackerModel;
import com.stocktracker.service.StockTrackerService;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
public class MockCreationTest {
		@Mock
		private StockTrackerService stockTrackerService;
	
		@Mock
	    private StockTrackerModel stockTrackerModel;
		
	    @Before
	    public void setupMock() {
	    	MockitoAnnotations.initMocks(this);
	    }
	    @Test
	    public void testMockCreation(){
	        assertNotNull(stockTrackerModel);
	        assertNotNull(stockTrackerService);
	    }
	    
	    @Test
	    public void testGetSymbolInfoService(){
	       when(stockTrackerService.getSymbolInfoService("ACAD")).thenReturn(stockTrackerModel);
	    }
}
