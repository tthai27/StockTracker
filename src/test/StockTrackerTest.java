package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.stocktracker.controller.StockTrackerController;
import com.stocktracker.service.StockTrackerService;

public class StockTrackerTest {
	private AnnotationConfigApplicationContext context = null;
	@Before
	public void setUp() throws Exception {
		context = new AnnotationConfigApplicationContext(StockTrackerController.class);
	}
	
	@After
	public void tearDown() throws Exception {
		context.close();
	}

	@Test
	public void test() {
		StockTrackerService app = context.getBean(StockTrackerService.class);
		System.out.println(app.getSymbolInfoService("ACAD"));
	}
}
