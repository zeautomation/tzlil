package AutomationCourseTzlil.Automation;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class MySupermarket {

	private static final String SUTURL = "https://www.mysupermarket.co.il/";
	WebDriverWrapper driverWrapper;

	@Before
	public void before() {
		driverWrapper = new WebDriverWrapper();

	}

	@Test
	public void searchForItem() throws InterruptedException {

		driverWrapper.openURL(SUTURL);

		driverWrapper.getElementByType("(//div[@class='StartButton']/a)[1]", "xpath").click();
		
		driverWrapper.getElementByType("//a[contains(@class,'close ui-corner')]", "xpath").click();
		
		driverWrapper.getElementByType("txtfind", "id").sendKeys("עוגיות");

		driverWrapper.getElementByType("btnFind", "id").click();

		if (driverWrapper.findListOfElement("(//*[@id=\"NgMspProductCell\"]/div[2])[1]", "xpath").size() == 0) {
			Assert.fail("No results");
			//  //ul[@id='ULProductList0']/li
		}
	}
	
	@After
	public void After() {
		driverWrapper.close();
	}
}
