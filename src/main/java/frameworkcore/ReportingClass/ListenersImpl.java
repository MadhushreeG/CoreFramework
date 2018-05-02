/**
 * Core Framework
 * Author : Deepak Tiwari
 * Creation Date : 27 Apr 2018
 * Modified Date : 
 * Modified By : 
 */
package frameworkcore.ReportingClass;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

/**
 * This is TestNG Listener class
 */
public class ListenersImpl implements ITestListener, IReporter, ISuiteListener{

	ExtentReports reporter=null;
	public static  ExtentTest parentTest;
	private static Logger logger = LoggerFactory.getLogger(ListenersImpl.class);
	
	@Override
	public void onTestStart(ITestResult result) {
		
		reporter = Reporting.getExtentReports("Test");
		logger.info("Starting method  " + result.getName());
		parentTest = reporter.createTest(result.getName());
	}


	@Override
	public void onTestSuccess(ITestResult result) {

		logger.info(result.getName() + " method is Passed");
		parentTest.pass(MarkupHelper.createLabel(result.getName() + " Test is Passed", ExtentColor.GREEN));
		reporter.flush();
		
	}


	@Override
	public void onTestFailure(ITestResult result) {

		logger.info(result.getName() + " method is Failed");
		parentTest.fail(MarkupHelper.createLabel(result.getName() + " method is Failed", ExtentColor.RED));
		reporter.flush();
	}


	@Override
	public void onTestSkipped(ITestResult result) {

		logger.info(result.getName() + "  method is Skipped");
		parentTest.skip(MarkupHelper.createLabel(result.getName() + " method is Skipped", ExtentColor.GREY));
		reporter.flush();
	}


	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
		
	}


	@Override
	public void onStart(ITestContext context) {
		//logger.info(context.getName() + " Test is Started");
	}


	@Override
	public void onFinish(ITestContext context) {
		//logger.info(context.getName() + " Test is Finished");
	}


	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onStart(ISuite suite) {
		
		logger.info("Starting Suite " + suite.getName());
		//Reporting.getreporter().flush();
	}


	@Override
	public void onFinish(ISuite suite) {
		logger.info("Finishing Suite " + suite.getName());
		
	}
}
