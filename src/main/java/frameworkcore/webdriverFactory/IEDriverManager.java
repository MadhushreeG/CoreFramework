/**
 * This project is using Spring 
 */
package frameworkcore.webdriverFactory;

import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * @author dtiwa1
 *
 */
public class IEDriverManager  {
	
	private static WebDriver driver;
	private static InternetExplorerOptions options;
    
	public static WebDriver createDriver() {
        
		System.setProperty("webdriver.ie.driver",System.getProperty("user.dir") + "/src/main/resources/Drivers/IEDriverServer.exe");
        driver = new InternetExplorerDriver(GetOptions());
        return driver;
    }
    
    private static InternetExplorerOptions GetOptions() {
    	
    	options = new InternetExplorerOptions ();
		options.introduceFlakinessByIgnoringSecurityDomains();
		options.enableNativeEvents();
		options.enablePersistentHovering();
		options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
		DesiredCapabilities IECapabilities = DesiredCapabilities.internetExplorer();
		IECapabilities.setPlatform(Platform.WINDOWS);
    	
    	options.merge(IECapabilities);
    	return options;
       
    }

}
