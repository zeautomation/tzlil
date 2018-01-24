package AutomationCourseTzlil.Automation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.rmi.server.RemoteRef;
import java.util.List;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverWrapper {
	// Here we build api (memshak!!!) for the selenium api (So we don't speak
	// directly to selenium)

	RemoteWebDriver remoteWebDriver; // new object - type of RemoteWebDriver

	public WebDriverWrapper() { // constructor
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();

		LoggingPreferences loggingPreferences = new LoggingPreferences();
		loggingPreferences.enable(LogType.BROWSER, Level.ALL);
		capabilities.setCapability(CapabilityType.LOGGING_PREFS, loggingPreferences);

		remoteWebDriver = new RemoteWebDriver(capabilities);
		// remoteWebDriver = new ChromeDriver(capabilities);
	}

	public void openURL(String url) {
		remoteWebDriver.get(url);
	}

	public WebElement getElementByType(String value, String type) {
		WebElement element = null;
		try {
			WebDriverWait driverWait = new WebDriverWait(remoteWebDriver, 10, 1000);
			if (type.equals("xpath")) {
				element = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(value)));
			} else if (type.equals("id")) {
				element = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(value)));
			} else if (type.equals("class")) {
				element = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.className(value)));
			}
		} catch (TimeoutException e) {
			System.out.println("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return element;
	}

	// public WebElement getElementByTypeBenny(By value, String type) {
	// WebElement element = null;
	// try {
	// WebDriverWait driverWait = new WebDriverWait(remoteWebDriver, 10, 1000);
	// if (type.equals("xpath")) {
	// element =
	// driverWait.until(ExpectedConditions.visibilityOfElementLocated(value));
	// } else if (type.equals("id")) {
	// element =
	// driverWait.until(ExpectedConditions.visibilityOfElementLocated(value));
	// } else if (type.equals("class")) {
	// element =
	// driverWait.until(ExpectedConditions.visibilityOfElementLocated(value));
	// }
	// } catch (TimeoutException e) {
	// System.out.println("");
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return element;
	// }

	public List<WebElement> findListOfElement(String value, String type) {
		List<WebElement> element = null;
		try {
			WebDriverWait driverWait = new WebDriverWait(remoteWebDriver, 10, 1000);
			if (type.equals("xpath")) {
				element = driverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(value)));
			} else if (type.equals("id")) {
				element = driverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(value)));
			} else if (type.equals("class")) {
				element = driverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className(value)));
			}
		} catch (TimeoutException e) {
			System.out.println("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return element;
	}

	public boolean checkIfElementDisapper(String value) {
		WebDriverWait driverWait = new WebDriverWait(remoteWebDriver, 10, 1000);
		return driverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(value)));
	}

	// public void dragAndDrop (String xpathFrom, String xpathTo) {
	// Actions actions = new Actions (remoteWebDriver);
	// actions.click(get_Element(By.xpath(xpathFrom)))
	// }

	public void printScreen() {

		WebDriver augmentedDriver = new Augmenter().augment(remoteWebDriver);
		File screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);

		copyFile(screenshot, System.getProperty("user.dir") + "/files/scr/" + screenshot.getName());
	}

	public static void copyFile(File source, String destinationPath) {
		try {
			InputStream in = new FileInputStream(source);
			try {
				OutputStream out = new FileOutputStream(new File(destinationPath));
				try {
					// Transfer bytes from in to out
					byte[] buf = new byte[1024];
					int len;
					while ((len = in.read(buf)) > 0) {
						out.write(buf, 0, len);
					}
				} finally {
					out.close();
				}
			} finally {
				in.close();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public LogEntries getConsoleLogs() {
		return remoteWebDriver.manage().logs().get(LogType.BROWSER);
	}
	
	public void printConsoleLogs() {
		List<LogEntry> consoleLogEntries = getConsoleLogs().getAll();
		
		for(int i = 0; i<consoleLogEntries.size(); i++) {
			
			LogEntry entry = consoleLogEntries.get(i);
			System.out.println(entry.getMessage()+ ": "+ entry.getTimestamp());
		}
	}
	
	public void addCookie(String cookieName, String Value) {
		Cookie cookie = new Cookie(cookieName, Value);
		
		remoteWebDriver.manage().addCookie((cookie));
	}

	public void deleteCookies() {
		//Deleting all cookies from the browser 
		remoteWebDriver.manage().deleteAllCookies();
	}

	
	//To ask from Omer the method. 
//	public void clearLocalStorage() {
//		runJavaScripts(String.format("window.localStorgae.clear();"));
//	}
	
	
	public void close() {
		remoteWebDriver.quit();
	}
}
