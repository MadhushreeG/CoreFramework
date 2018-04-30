/**
 *
 */
package corepackage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlSuite.ParallelMode;
import org.testng.xml.XmlTest;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import frameworkcore.excelReader.GetTestCaseInfo;

/**
 * @author dtiwa1
 *
 */
public class RunWithTestNG {
	
	private static Logger logger = LoggerFactory.getLogger(RunWithTestNG.class);
	private static HashMap<String, String> TestsBrowserList ;
	private static HashMap<String, HashMap<String, String>> TestsParamList ;
	private static Multimap<String, String> TestsClassesList = ArrayListMultimap.create();
	private static Multimap<String, String> ClassesMethodList = ArrayListMultimap.create();
	private static Hashtable<String, String> TestNGConfig = new Hashtable<String, String>();
	
	
	public static void ExecuteTests(){
		
		GetTestCaseInfo.GetTestCaseInformation();
		TestsBrowserList = GetTestCaseInfo.getTestsBrowserList();
		TestsClassesList = GetTestCaseInfo.getTestsClassesList();
		ClassesMethodList = GetTestCaseInfo.getClassesMethodList();
		TestsParamList = GetTestCaseInfo.getTestsParamList();
		TestNGConfig = GetTestCaseInfo.getTestNGConfig();
		List<XmlSuite> suites = new ArrayList<XmlSuite>();
		
		logger.info("Generating TestNG.xml file");
		
		XmlSuite suite = new XmlSuite();
		suite.setName(TestNGConfig.get("AutomationSuiteName"));
		
		if(TestNGConfig.get("ParallelMode").toString().equalsIgnoreCase("True"))
			suite.setParallel(XmlSuite.ParallelMode.TESTS);
		else
			suite.setParallel(XmlSuite.ParallelMode.NONE);
		
		//suite.setVerbose(Integer.parseInt(TestNGConfig.get("ParallelMode")));
		
		suite.setThreadCount(Integer.parseInt(TestNGConfig.get("ThreadCount")));
		//suite.addListener("frameworkcore.ReportingClass.ListenersImpl");
		
		for (String key : TestsBrowserList.keySet()){
			ArrayList<XmlClass> classes = new ArrayList<XmlClass>();
			XmlTest test = new XmlTest(suite);
			test.setName(key.toString());
			HashMap<String,String> browserinfo = new HashMap<String,String>();
			browserinfo.put("BrowserName", TestsBrowserList.get(key).toString());
			test.setParameters(browserinfo);
			SetParameters(test);
			Collection<String> classvalues =  TestsClassesList.get(key);
			for(String testclass : classvalues){
				XmlClass testClass = new XmlClass();
		        testClass.setName(testclass);
		        Collection<String> methodvalues =  ClassesMethodList.get(testclass);
		        ArrayList<XmlInclude> methodsToRun = new ArrayList<XmlInclude>();
		        for(String method: methodvalues){
		        	if(method.toLowerCase().contains("tag")||method.toLowerCase().contains("ignore"))
		        		
		        		logger.info("Method  " + method + "  will not run" );
		        	else
		        		methodsToRun.add(new XmlInclude(method));
		        }
		        testClass.setIncludedMethods(methodsToRun);
		        
		        classes.add(testClass);
			}
			test.setXmlClasses(classes);
		}
		
		suites.add(suite);
		/*List<XmlSuite> suites = new ArrayList<XmlSuite>();
		suites.add(suite);*/
		
		 FileWriter writer;
		try {
			writer = new FileWriter(new File("TestNG.xml"));
			 writer.write(suite.toXml());
		        writer.flush();
		        writer.close();
		        logger.info("TestNG file Generated Successfully");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		TestNG tng = new TestNG();
		tng.setXmlSuites(suites);
		tng.setParallel(ParallelMode.TESTS);
		tng.run();
		
	}
	
	private static void SetParameters(XmlTest test){
		//System.out.println(TestsParamList.get(test.getName());
		 for (Map.Entry<String,String> entry : TestsParamList.get(test.getName()).entrySet()){
			 test.addParameter(entry.getKey(), entry.getValue());
		 }
	}
}
