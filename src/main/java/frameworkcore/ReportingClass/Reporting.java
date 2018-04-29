package frameworkcore.ReportingClass;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Reporting {
  
	private static ExtentReports extentReports = null;
	private static Logger logger = LoggerFactory.getLogger(Reporting.class);
	
	
	/**
	 * @return the extentReports
	 */
	public synchronized static ExtentReports getExtentReports(String ReportName){
		
		if(extentReports == null){
			ExtentReports extentReports = new ExtentReports() ;
			  String reportspath = System.getProperty("user.dir") + "/Reporting";
			  String ScreenShotPath =  System.getProperty("user.dir") + "/Reporting/Screenshots";
				
				File reportdir = new File(reportspath);
				if(!reportdir.exists())
					reportdir.mkdirs();
				
				File snapshotdir = new File(ScreenShotPath);
				if(!snapshotdir.exists())
					snapshotdir.mkdirs();
			  
			  ExtentHtmlReporter htmlReporter =  new ExtentHtmlReporter(reportspath + "/" + ReportName + "_AutomationReport_" + GetCurrentTimeStamp() + ".html");
			  htmlReporter.setAppendExisting(true);
			  extentReports.attachReporter(htmlReporter);
			  extentReports.config();
			  return extentReports;
			  }
			
		else
			return extentReports;
	}
	
	private static String GetCurrentTimeStamp(){
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}
	
public synchronized static String CaptureScreenShot(WebDriver driver) {
		
	String dest = "";
		
	try{
			
			TakesScreenshot ts = (TakesScreenshot)driver;
	       File source = ts.getScreenshotAs(OutputType.FILE);
	        dest = System.getProperty("user.dir") +"/Reporting/Screenshots/" + GetCurrentTimeStamp() + "_ErrorScreenshot.png";
	        File destination = new File(dest);
	        FileUtils.copyFile(source, destination); 
	        return dest;
		}catch(Exception e){
			logger.error(e.getMessage().toString());
		}
		return dest;
	}

	public synchronized static void FlushReport() { 
		extentReports.flush();
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//parentTest = extentReports.createTest("New Test");
	  //childTest = parentTest.createNode("Starting Test1");
	  //extentReports.flush();
	  //ExtentTest feature = extentReports.createTest(Feature.class, "Refund item");
	  /*ExtentTest scenario = feature.createNode(Scenario.class, "Jeff returns a faulty microwave");
	  scenario.createNode(Given.class, "Jeff has bought a microwave for $100").pass("pass");
	  scenario.createNode(And.class, "he has a receipt").pass("pass");
	  scenario.createNode(When.class, "he returns the microwave").pass("pass");
	  scenario.createNode(Then.class, "Jeff should be refunded $100").fail("fail");*/
  
	/*public static void ScenarioNode(String scenarioName){
		logger.info("");
		logger.info("|--------------------------------------------------------------------------------------------------------------|");
		logger.info("|------     Running scenario  " + scenarioName +  "    -----|");
		logger.info("|--------------------------------------------------------------------------------------------------------------|");
		logger.info("");
		
		parentTest = extentReports.createTest("Running scenario " + scenarioName);
	}
	
	public static void Stepnode(String StepName){
		logger.info("Running Step  " + StepName);
		childTest = parentTest.createNode("Running Step " + StepName);
	}
	
	public static void LogScenarioStatus(String ScenarioStatus, String Message) {

			if (ScenarioStatus.equalsIgnoreCase("Pass"))
				{
					logger.info("|--------------------------------------------------------------------------------------------------------------|");
					logger.info("|-------     Scenario  " + Message + "      -------|");
					logger.info("|--------------------------------------------------------------------------------------------------------------|");
					parentTest.pass(Message);
					logger.info("");
					logger.info(Message);
				}
			else{
					logger.info("|--------------------------------------------------------------------------------------------------------------|");
					logger.info("|-------     Scenario  " + Message + "      -------|");
					logger.info("|--------------------------------------------------------------------------------------------------------------|");
					parentTest.fail(Message);
					logger.info("");
					logger.info(Message);
				}
	}
	
	public static void LogStepStatus(String StepStatus, String Message) {

			if (StepStatus.equalsIgnoreCase("Pass")){
				childTest.log(Status.PASS, Message);
				logger.info(Message);
			}
			else if (StepStatus.equalsIgnoreCase("Info")){
				childTest.log(Status.INFO, Message);
				logger.info(Message);
			}
			else if (StepStatus.equalsIgnoreCase("Error")){
				childTest.log(Status.ERROR, Message + Reporting.CaptureScreenShot());
				logger.info(Message);
			}
			else if (StepStatus.equalsIgnoreCase("Fail")){
				childTest.log(Status.FAIL, Message + "  " + "<a href='" + Reporting.CaptureScreenShot() + "  " + "'>Error Link</a>");
				logger.info(Message);
			}
	}
	
	public static void FlushReport(){
		extentReports.flush();
	}
  
	
	public static String CaptureScreenShot() {
		
		
		try{
			TakesScreenshot ts = (TakesScreenshot)driver;
	        File source = ts.getScreenshotAs(OutputType.FILE);
	        String dest = System.getProperty("user.dir") +"/Reporting/ErrorScreenshots/" + GetCurrentTimeStamp() + "_ErrorScreenshot.png";
	        File destination = new File(dest);
	        FileUtils.copyFile(source, destination); 
	        return dest;
		}catch(Exception e){
			logger.info("Something went wrong while capturing screenshot");
			return "";
		}
		
	}*/
  