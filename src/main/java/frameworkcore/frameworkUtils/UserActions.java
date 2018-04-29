package frameworkcore.frameworkUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author dtiwa1
 *
 */
public class UserActions {
	
	static WebDriver driver = new ChromeDriver();
	static JavascriptExecutor js = (JavascriptExecutor) driver; 
	
	public static void ClickElement(){
		
		driver.findElement(By.name("abc")).click();
		
	}
	
	public static void ClickElementJS(){
		
		js.executeScript("arguments[0].click();", driver.findElement(By.name("abc")));
		
	}
	
	public static void SendTextJS(){
		
		js.executeScript("document.getElementById('some id').value='someValue';");
        js.executeScript("document.getElementById('Email').value='SoftwareTestingMaterial.com';");
	}
	
	public static String GetAttributeOfElement(String AttributeName){
		
		return driver.findElement(By.id("abc")).getAttribute(AttributeName);
	}
	
	
	public static String GetInnerTextOfElementJS(){
		
		return js.executeScript("return document.documentElement.innerText;").toString();
	}
	
	public static void ScrollPageByPixelsJS(int xCoordinate, int yCoordinate){
		
		js.executeScript("window.scrollBy(" + xCoordinate + "," + yCoordinate + ")");
	}
	
	

}
