package AutomationCourseTzlil.Automation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PageObject {
	
	private static final String SUTURL = "https://www.vroom.com/";
	private static final String SEARCH_BTN = "//*[@class='search-btn-text']";
	
	
	public static class VroomHomePage extends GenericPageObject implements ILoadable {
		
		public static void VroomOpenURL() {
			driverWrapper.openURL(SUTURL);
		}
		
		public static void search(String text) {
			WebElement search = driverWrapper.getElementByType("hero-search-input", "id");
			
			if (search !=null) {
				search.sendKeys(text);
			}
			else {
				System.out.println("Element not found");
			}
		}
		
		public static void click() {
			WebElement clickOnSearchButton = driverWrapper.getElementByType(SEARCH_BTN, "xpath");
			clickOnSearchButton.click();
		}

		
		public void isPageLoaded() {
			//driverWrapper.getElementByType(value, "xpath")		
		}
		
		public static void getNumberOfCarsFromSearch() {
			driverWrapper.findListOfElement("//div[@class='car-image-container']", "xpath");
			              
		}
		
	}
	
	
	public static class VroomSearchResults extends GenericPageObject {
		
		
		
		private static void clickOnPriceMenu() {
			WebElement clickOnPriceItem = driverWrapper.getElementByType("//h4[contains(text(),'price')]", "xpath");
			clickOnPriceItem.click();
		}
		
		
		private static void clickOnPriceFilter() {
			WebElement clickOnPriceFilterAbove40K = driverWrapper.getElementByType("//div[contains(text(),'Above $40K')]", "xpath");
			clickOnPriceFilterAbove40K.click();
		}
		
		public static void clickAndFilter() {
			clickOnPriceMenu();
			clickOnPriceFilter();
		}
		
		public static Integer getHighestPrice() throws InterruptedException {
			//Thread.sleep(1000);
			ArrayList<Integer> prices = new ArrayList<>();
			if (driverWrapper.checkIfElementDisapper("//div[@class='middle']/h3[contains(text(),'Loading...')]")) {
				List<WebElement> carsPricesElements = 
						driverWrapper.findListOfElement("//article[@class='catalog-car-item ar-item']//div[@class='price']","xpath");
				
				for(WebElement carElement : carsPricesElements) {
					prices.add(Integer.parseInt(carElement.getText().replaceAll("[^\\d.]", "")));
				}
			}
			Collections.sort(prices);	
			return prices.get(prices.size()-1);
		}

		
		
	}
	

}
