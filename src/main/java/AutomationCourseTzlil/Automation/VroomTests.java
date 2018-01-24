package AutomationCourseTzlil.Automation;

import org.junit.Assert;
import org.junit.Test;

import AutomationCourseTzlil.Automation.PageObject.VroomHomePage;
import AutomationCourseTzlil.Automation.PageObject.VroomSearchResults;

//Application programming interface
public class VroomTests extends BasicTests {
	
//	@Test
//	public void search() {
//		
//		VroomHomePage.VroomOpenURL();
//		VroomHomePage.search("kia");
//		VroomHomePage.click();
//		VroomHomePage.getNumberOfCarsFromSearch();
//		driverWrapper.printScreen();
//		
//	} 
	
	@Test
	public void checkCarPrice() throws InterruptedException {
		
		//Homepage page
		VroomHomePage.VroomOpenURL();
		VroomHomePage.search("bmw");
		VroomHomePage.click();
		
		//Results page
		VroomSearchResults.clickAndFilter();
		Integer highestPrice = VroomSearchResults.getHighestPrice();
		
		Assert.assertTrue("The price is lower than 300,000", highestPrice>300000 );
	} 

}
