package utilities;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WebDriverUtility {
	
	private WebDriverUtility() {}
	
	public static void initiateDriver(String browser, String executionMode) throws MalformedURLException, UnsupportedBrowserException {
		WebDriverFactory.setDriver(browser, executionMode);
	}

	public static void launchApplication(String url) {
		WebDriverFactory.getDriver().get(url);
	}

	public static String getText(String locator) {
		return WebDriverFactory.getDriver().findElement(getByLocator(locator)).getText();
	}
	
	public static void clickElement(String locator) {
		WebDriverFactory.getDriver().findElement(getByLocator(locator)).click();
	}
	
	public static void setText(String locator, String text) {
		WebElement element = WebDriverFactory.getDriver().findElement(getByLocator(locator));
		element.clear();
		element.sendKeys(text);
	}
	
	static By getByLocator(String locatorText) {
		By locator = null;
		String[] locatorDetails = locatorText.split(";");
		String locatorType = locatorDetails[0];
		switch(locatorType) {
			case "id":
				locator = By.id(locatorDetails[1]);
				break;
			case "xpath":
				locator = By.xpath(locatorDetails[1]);
				break;
			case "name":
				locator = By.name(locatorDetails[1]);
				break;
			case "linktext":
				locator = By.linkText(locatorDetails[1]);
				break;
			case "css":
				locator = By.cssSelector(locatorDetails[1]);
				break;
			case "classname":
				locator = By.className(locatorDetails[1]);
				break;
			case "partiallinktext":
				locator = By.partialLinkText(locatorDetails[1]);
				break;
			case "tagname":
				locator = By.tagName(locatorDetails[1]);
				break;
			default:
		}
		return locator;
	}
	
	public static void terminateDriver() {
		WebDriverFactory.removeDriver();
	}
}
