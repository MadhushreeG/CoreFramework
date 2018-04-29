/**
 * This project is using Spring 
 */
package frameworkcore.webdriverFactory;

import java.io.File;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.AndroidServerFlag;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

/**
 * @author dtiwa1
 *
 */
public class iOSDriverManager  {
	
	private static WebDriver driver;
	private static AppiumDriverLocalService driverLocalService;
    
	public static WebDriver createDriver() {
		
		driverLocalService = startAppiumServer("8080","1111","1212");
        driver = new IOSDriver(driverLocalService, null);
        return driver;
    }
    
    private static DesiredCapabilities GetCapabilities() {
    	
		DesiredCapabilities IOSCapabilities = DesiredCapabilities.iphone();
		IOSCapabilities.setPlatform(Platform.WINDOWS);
    	
    	
    	return IOSCapabilities;
       
    }
    
    public static AppiumDriverLocalService startAppiumServer(String port, String bootstrapport, String IPAddressOfHub){
    	
    	String jSonFileName = "C:\\json.json";
    	
    	    driverLocalService = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
    	    	.withLogFile(new File(System.getProperty("user.dir") + "/Reporting/Logs/AppiumLogs.log"))
    	    	.withIPAddress("127.0.0.1")
    	    	.usingPort(Integer.parseInt(port))
				.withAppiumJS(new File("C:/Program Files (x86)/Appium/node_modules/appium/bin/appium.js"))
				.usingDriverExecutable(new File("C:/Program Files (x86)/Appium/node.exe"))
				.withArgument(AndroidServerFlag.BOOTSTRAP_PORT_NUMBER,bootstrapport)
				.withArgument(GeneralServerFlag.SESSION_OVERRIDE)
				.withArgument(GeneralServerFlag.LOG_LEVEL, "debug")
				.withArgument(GeneralServerFlag.LOG_TIMESTAMP)
				.withArgument(GeneralServerFlag.CONFIGURATION_FILE, System.getProperty("user.dir") + "/src/main/resources/" + "Grid/" + jSonFileName));
    		driverLocalService.start();
    		return driverLocalService;
    }
 
    //this method is run after each junit class completes execution
    @AfterTest
    public static void stopServer() {
    	
    	try{
        	driver.quit();
    		driverLocalService.stop();
    	}catch(Exception e){
    		
    	}
    }

}
