package AutomationCourseTzlil.Automation;

public class GenericPageObject {

	//here we should set the instance of WebDriverWrapper
	public static WebDriverWrapper driverWrapper;

	public static void setWebDriver(WebDriverWrapper _driverWrapper) {
		driverWrapper = _driverWrapper;
	}

}
