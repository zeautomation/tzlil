package AutomationCourseTzlil.Automation;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class BasicTests {

	public WebDriverWrapper driverWrapper;

	@Before
	public void before() {
		driverWrapper = new WebDriverWrapper(); // create the wrapper instance
		GenericPageObject.setWebDriver(driverWrapper);
	}

	// @Test
	// public void login() {
	//
	// driverWrapper.openURL(SUTURL); //SUT - system under test
	//
	// System.out.println("finding username field");
	//
	// WebElement element = driverWrapper.getElementByType("username", "id");
	//
	// element.sendKeys("user 1");
	//
	// System.out.println("finding password field");
	//
	// WebElement element1 = driverWrapper.getElementByType("password", "id");
	//
	// element1.sendKeys("1234");
	//
	// WebElement element2 =
	// driverWrapper.getElementByType("//button[text()='submit']", "xpath");
	//
	// element2.click();
	//
	// }

	@After
	public void After() throws InterruptedException {
		driverWrapper.printConsoleLogs();

		Thread.sleep(5000);
		driverWrapper.close();
	}

}
