package frameworkcore.objectRepository;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ObjectRepository {
	
	InputStream input = null;
	static Properties prop = new Properties();
    
	public ObjectRepository (String PropFileName){
		
		try {
			input = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/" + "PropertyFiles/" + PropFileName);
			prop.load(input);		// load a properties file
		}catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static WebElement GetObject(WebDriver driver, String ObjectName, String ReplaceString){
		
		String Objectproperties = prop.getProperty(ObjectName);
		String[] ObjProperties = Objectproperties.split("\\|");
		String ObjectLocatortype = ObjProperties[0];
		String ObjectLocatorValue = ObjProperties[1];
		if(ReplaceString!=null)
			ObjectLocatorValue = ObjectLocatorValue.replace("Placeholder", ReplaceString);
		
		switch (ObjectLocatortype) {
			case "id":
		        	return driver.findElement(By.id(ObjectLocatorValue));
			case "className":
				return driver.findElement(By.className(ObjectLocatorValue));
			case "LinkText":
				return driver.findElement(By.linkText(ObjectLocatorValue));
			case "PartialLinkText":
				return driver.findElement(By.partialLinkText(ObjectLocatorValue));
			case "name":
				return driver.findElement(By.name(ObjectLocatorValue));
			case "css":
				return driver.findElement(By.cssSelector(ObjectLocatorValue));
			case "xpath":  
					ObjectLocatorValue = ObjectLocatorValue.replace("PlaceHolder", ReplaceString);
					return driver.findElement(By.xpath(ObjectLocatorValue));
	        default:
	            return null;
		}
	}
	
	public WebElement GetObject(WebDriver driver, String ObjectName){
		
		return GetObject(driver,ObjectName, null);
	}
}
