/**
 * Core Framework
 * Author : Deepak Tiwari
 * Creation Date : 27 Apr 2018
 * Modified Date : 
 * Modified By : 
 */
package frameworkcore.webdriverFactory;

import java.lang.reflect.InvocationTargetException;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This instantiates the ChromeDriver 
 */
class ChromeDriverManager  {
	
	private static Logger logger = LoggerFactory.getLogger(ChromeDriverManager.class);
	private static WebDriver driver=null;
	private static ChromeOptions options;
    
	static WebDriver createDriver() throws InvocationTargetException, IllegalAccessException{
		
		logger.info("Starting ChromeDriver");
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "/src/main/resources/Drivers/chromedriver");
		driver = new ChromeDriver(GetOptions());
        return driver;
    }
    
	/**
	 * @author deepaktiwari
	 *
	 */
    private static ChromeOptions GetOptions() {
    	
    	logger.info("Setting Chrome Options");
    	options = new ChromeOptions();
		options.addArguments("--disable-extensions");
		options.addArguments("test-type");
		options.addArguments("start-maximized");
		options.addArguments("disable-infobars");
		options.setAcceptInsecureCerts(true);
		options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
		//options.addArguments("--silent");
		//options.setHeadless(true);
		//options.addArguments("--disable-gpu");
    	
		//DesiredCapabilities ChromeCapabilities = DesiredCapabilities.chrome();
    	//ChromeCapabilities.setPlatform(Platform.WINDOWS);
    	
    	//options.merge(ChromeCapabilities);
    	return options;
       
    }

}
