/**
 * Core Framework
 * Author : Deepak Tiwari
 * Creation Date : 27 Apr 2018
 * Modified Date : 
 * Modified By : 
 */
package frameworkcore.webdriverFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class manages the instantiation of the drivers based on the value provided by the TestNG.xml
 */
public class DriverManager {
	
	private static Logger logger = LoggerFactory.getLogger(DriverManager.class);
	private static ThreadLocal<WebDriver> Webdriver = new ThreadLocal<WebDriver>();
	
		public static void setDriver(String PlatformName) throws InvocationTargetException, IllegalAccessException {
			
	        switch (PlatformName) {
	            case "Chrome":
	            	setWebDriver(ChromeDriverManager.createDriver());
	                break;
	                
	            case "Firefox":
	            	setWebDriver(FirefoxDriverManager.createDriver());
	                break;
	                
	            case "IE":
	            	setWebDriver(IEDriverManager.createDriver());
	                break;
	                
	            case "Safari":    
	            	setWebDriver(SafariDriverManager.createDriver());
	                break;
	                
	            case "Android":
	            	setWebDriver(AndroidDriverManager.createDriver());
	                break;
	            	
	            case "iOS":
	            	setWebDriver(iOSDriverManager.createDriver());
	                break;
	                
	            case "RemoteChrome":
	            	logger.info("Running Test on Chrome Remotely");
	                
	            default:
	            	logger.info("No browser specified. Defaulting to Chrome");
	            	setWebDriver(ChromeDriverManager.createDriver());
	                break;
	        }
	    }
		
		public static WebDriver getDriver() {
	        return Webdriver.get();
	    }
	 
	    static void setWebDriver(WebDriver driver) {
	    	SetDriverConfiguration(driver);
	    	Webdriver.set(driver);
	    }
	    
	    private static void SetDriverConfiguration(WebDriver driver) {
	    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
	    }
		
		public static void quitDriver(){
			if (null != Webdriver.get()) {
				Webdriver.get().quit();
	        }
		}
}
