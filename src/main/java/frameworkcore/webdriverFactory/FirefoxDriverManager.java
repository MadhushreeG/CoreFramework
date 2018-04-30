/**
 * This project is using Spring 
 */
package frameworkcore.webdriverFactory;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author dtiwa1
 *
 */
public class FirefoxDriverManager  {
	
	private static Logger logger = LoggerFactory.getLogger(ChromeDriverManager.class);
	private static WebDriver driver;
	private static FirefoxOptions options;
    
	public static WebDriver createDriver() {
		logger.info("Starting FirefoxDriver");
		System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir") + "/src/main/resources/Drivers/geckodriver");
        driver = new FirefoxDriver(GetOptions());
        return driver;
    }
    
    private static FirefoxOptions GetOptions() {
    	
    	logger.info("Setting Firefox Options");
    	options = new FirefoxOptions();
		options.addArguments("--disable-extensions");
		options.addArguments("test-type");
		options.addArguments("start-maximized");
		options.addArguments("disable-infobars");
		options.setAcceptInsecureCerts(true);
		options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
    	return options;
       
    }

}
