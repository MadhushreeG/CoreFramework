/**
 * Core Framework
 * Author : Deepak Tiwari
 * Creation Date : 27 Apr 2018
 * Modified Date : 
 * Modified By : 
 */
package frameworkcore.webdriverFactory;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

public class SafariDriverManager  {
	
	private static WebDriver driver;
	private static SafariOptions options;
    
	public static WebDriver createDriver() {
        
        driver = new SafariDriver(GetOptions());
        return driver;
    }
    
    private static SafariOptions GetOptions() {
    	
    	options = new SafariOptions();
		options.setCapability("platform", "Windows");
    	
		DesiredCapabilities SafariCapabilities = DesiredCapabilities.safari();
		SafariCapabilities.setPlatform(Platform.WINDOWS);
    	
    	options.merge(SafariCapabilities);
    	return options;
       
    }

}
