package utilities;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WebDriverFactory {
	private static OptionsManager optionsManager = new OptionsManager();
	private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
	static ThreadConfig config = ThreadContextHolder.getThreadContext(); 
	public static final String GRID_URL = "http://11.12.13.14:4444/wd/hub";
	
	private WebDriverFactory(){}
	
	public static synchronized void setDriver(String browser, String executionMode) throws UnsupportedBrowserException, MalformedURLException {
		config.setBrowser(browser);
		config.setExecutionMode(executionMode);
		
		if("chrome".equalsIgnoreCase(browser)) {
			if("grid".equalsIgnoreCase(executionMode)) {
				tlDriver.set(new RemoteWebDriver(new URL(GRID_URL), optionsManager.getChromeOptions()));
			}
			else {//local execution
				tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			}
				
		}
		else if("firefox".equalsIgnoreCase(browser)) {
			if("grid".equalsIgnoreCase(executionMode)) {
				tlDriver.set(new RemoteWebDriver(new URL(GRID_URL), optionsManager.getFirefoxOptions()));
			}
			else {//local execution
				tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			}
		}
		else if("edge".equalsIgnoreCase(browser)) {
			if("grid".equalsIgnoreCase(executionMode)) {
				tlDriver.set(new RemoteWebDriver(new URL(GRID_URL), optionsManager.getEdgeOptions()));
			}
			else {//local execution
				tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			}
		}
		else{
			throw new UnsupportedBrowserException();
		}
	}
	
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
	
	public static synchronized void removeDriver() {
		tlDriver.get().quit();
		tlDriver.remove();
	}
	
	public static synchronized void restartDriver() throws MalformedURLException, UnsupportedBrowserException {
		tlDriver.get().quit();
		tlDriver.remove();
		setDriver(config.getBrowser(), config.getExecutionMode());
	}
}
