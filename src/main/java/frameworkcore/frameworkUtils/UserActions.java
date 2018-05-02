/**
 * Core Framework
 * Author : Deepak Tiwari
 * Creation Date : 27 Apr 2018
 * Modified Date : 
 * Modified By : 
 */
package frameworkcore.frameworkUtils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import frameworkcore.webdriverFactory.DriverManager;

/**
 * This is a utility class which provides common user actions
 * This class contains user actions from Selenium, Javascript and Actions libraries
 */
public class UserActions {

	private static Logger logger = LoggerFactory.getLogger(UserActions.class);
	static WebDriver driver = null;
	static JavascriptExecutor js = null;

	/**
	 * The below contructor gets the driver instance and instantiates the Javascript executor object
	 */
	public UserActions() {
		driver = DriverManager.getDriver();
		js = (JavascriptExecutor) driver;
	}

	
	public static void ConfirmAlert() {
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	public static void DismissAlert() {
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}

	public static String GetAlertText() {
		Alert alert = driver.switchTo().alert();
		return alert.getText();
	}

	public static void SendTextToAlert(String text) {
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(text);
	}

	public static void SetText(WebElement element, String TextToEnter) {
		element.clear();
		element.sendKeys(TextToEnter);
	}

	public static void ClickElementJS(WebElement element) {
		js.executeScript("arguments[0].click();", element);
	}

	public static void SendTextJS() {
		js.executeScript("document.getElementById('some id').value='someValue';");
		js.executeScript("document.getElementById('Email').value='SoftwareTestingMaterial.com';");
	}

	public static String GetAttributeOfElement(WebElement element, String AttributeName) {
		return element.getAttribute(AttributeName);
	}

	public static void ScrollPageByPixelsJS(int xCoordinate, int yCoordinate) {
		js.executeScript("window.scrollBy(" + xCoordinate + "," + yCoordinate + ")");
	}

	public static String GetElementText(WebElement element) {

		String ElementText = null;
		try {
			if (element.isDisplayed()) {
				logger.info(element.getAttribute("innerHTML") + "  Element is Present and displayed");
				if (null != GetAttributeOfElement(element, "innerHTML"))
					ElementText = GetAttributeOfElement(element, "innerHTML");
				else if (null != GetAttributeOfElement(element, "innerText"))
					ElementText = GetAttributeOfElement(element, "innerText");
				else if (null != element.getText())
					ElementText = element.getText();
			} else
				logger.info(element.getAttribute("innerHTML") + "  Element is Present but hidden");
		} catch (Exception e) {
			logger.info(element.getAttribute("innerHTML") + "  Element is NOT Present");
		}
		return ElementText;
	}

	public static boolean VerifyElementIsPresent(WebElement element) {

		try {
			if (element.isDisplayed())
				logger.info(element.getAttribute("innerHTML") + "  Element is Present and displayed");
			else
				logger.info(element.getAttribute("innerHTML") + "  Element is Present but hidden");

			return true;
		} catch (Exception e) {
			logger.info(element.getAttribute("innerHTML") + "  Element is NOT Present");
			return false;
		}
	}

	public static boolean VerifyElementIsDisplayed(WebElement element) {

		try {
			if (element.isDisplayed()) {
				logger.info(element.getAttribute("innerHTML") + "  Element is Present and displayed");
				return true;
			} else {
				logger.info(element.getAttribute("innerHTML") + "  Element is Present but hidden");
				return false;
			}
		} catch (Exception e) {
			logger.info(element.getAttribute("innerHTML") + "  Element is NOT Present");
			return false;
		}
	}

}
