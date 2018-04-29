/**
 * This project is using Spring 
 */
package frameworkcore.webdriverFactory;

import java.io.File;

import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * @author dtiwa1
 *
 */
public class FirefoxDriverManager  {
	
	private static WebDriver driver;
	private static FirefoxOptions options;
    
	public static WebDriver createDriver() {
		System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir") + "/src/main/resources/Drivers/geckodriver.exe");
        //driver = new FirefoxDriver(GetOptions());
		driver = new FirefoxDriver();
        return driver;
    }
    
    private static FirefoxOptions GetOptions() {
    	
    	options = new FirefoxOptions();
    	options.setBinary(System.getProperty("user.dir") + "/src/main/resources/Drivers/" + "geckodriver.exe");
		//options.addArguments("--silent");
		options.addArguments("--disable-extensions");
		options.addArguments("test-type");
		options.addArguments("start-maximized");
		options.addArguments("disable-infobars");
		options.setAcceptInsecureCerts(true);
		options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
		//options.setHeadless(true);
		//options.addArguments("--disable-gpu");
    	
		//DesiredCapabilities ChromeCapabilities = DesiredCapabilities.firefox();
    	//ChromeCapabilities.setPlatform(Platform.WINDOWS);
    	
    	//options.merge(ChromeCapabilities);
    	return options;
       
    }

}
