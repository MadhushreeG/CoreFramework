package frameworkcore.webdriverFactory;

import java.lang.reflect.InvocationTargetException;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverManager {
	
	private static Logger logger = LoggerFactory.getLogger(DriverManager.class);
	private static ThreadLocal<WebDriver> Webdriver = new ThreadLocal<WebDriver>();
	
		public static void setDriver(String PlatformName) throws InvocationTargetException, IllegalAccessException {
			
	        switch (PlatformName) {
	            case "Chrome":
	            	logger.info("Running Test on Chrome");
	            	setWebDriver(ChromeDriverManager.createDriver());
	            	//driver = getDriver();
	                break;
	                
	            case "Firefox":
	            	logger.info("Running Test on FireFox");
	                //driver = FirefoxDriverManager.createDriver();
	                break;
	                
	            case "IE":
	            	logger.info("Running Test on IE");
	                //driver = IEDriverManager.createDriver();
	                break;
	                
	            case "Safari":    
	            	logger.info("Running Test on Safari");
	                //driver = SafariDriverManager.createDriver();
	                break;
	                
	            case "Android":
	            	logger.info("Running Test on Safari");
	                //driver = AndroidDriverManager.createDriver();
	                break;
	            	
	            case "iOS":
	            	logger.info("Running Test on Safari");
	                //driver = iOSDriverManager.createDriver();
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
	    	Webdriver.set(driver);
	    }
		
		public static void quitDriver(){
			if (null != Webdriver.get()) {
				Webdriver.get().quit();
	        }
		}
}
