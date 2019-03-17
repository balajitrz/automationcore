package utilities;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	
	public ChromeOptions getChromeOptions() {
		System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\drivers\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		return options;
	}
	
	public FirefoxOptions getFirefoxOptions() {
		System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\drivers\\geckodriver.exe");
		FirefoxOptions options = new FirefoxOptions();
		options.addArguments("--start-maximized");
		return options;
	}
	
	public EdgeOptions getEdgeOptions() {
		System.setProperty("webdriver.edge.driver", "src\\main\\resources\\drivers\\MicrosoftWebDriver.exe");
		EdgeOptions options = new EdgeOptions();
		return options;
	}
}
