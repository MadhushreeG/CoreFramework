/**
 * This project is using Spring 
 */
package frameworkcore.excelReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;


/**
 * @author dtiwa1
 *
 */
public class GetTestCaseInfo {
	
	private static Logger logger = LoggerFactory.getLogger(GetTestCaseInfo.class);
	private static HashMap<String, String> TestsBrowserList = new HashMap<String, String>() ;
	private static HashMap<String, HashMap<String, String>> TestsParamList = new HashMap<String, HashMap<String, String>>();
	private static Multimap<String, String> TestsClassesList = LinkedHashMultimap.create();
	private static Multimap<String, String> ClassesMethodList = LinkedHashMultimap.create();
	private static Hashtable<String, String> TestNGConfig = new Hashtable<String, String>();
	  	
	public static void GetTestCaseInformation() {
		
		String TestName=null, ClassName=null, TestCaseName=null, TestBrowserName=null;
		String TestCaseWorkbookName="TestCaseDriverSheet.xlsx";
		Row row=null;
		XSSFWorkbook workbook=null;
		FileInputStream file=null;
		
		try{
			if(file==null)
				file = new FileInputStream(new File(System.getProperty("user.dir") + "/"+ TestCaseWorkbookName));	    
		
			workbook = new XSSFWorkbook(file);
			Sheet configsheet = workbook.getSheet("Configuration");
			row = configsheet.getRow(1);
		}catch(IOException e){
			e.printStackTrace();
		}
		
		logger.info("Parallel mode is " + row.getCell(0).toString());
		logger.info("Threadcount is " + row.getCell(1).toString());
		logger.info("Verbose mode is " + row.getCell(2).toString());
		logger.info("Preserve Order mode is " + row.getCell(3).toString());
		logger.info("Suite Name is " + row.getCell(3).toString());
		
		try{
		TestNGConfig.put("Parallel", row.getCell(0).toString());
		TestNGConfig.put("ThreadCount", row.getCell(1).toString());
		TestNGConfig.put("Verbose", row.getCell(2).toString());
		TestNGConfig.put("PreserveOrder", row.getCell(3).toString());
		TestNGConfig.put("AutomationSuiteName", row.getCell(4).toString());
		}catch(NullPointerException e){
			logger.error("A cell seems to be blank. Please provide value to the cell");
		}
		
		Sheet TestCasessheet = workbook.getSheet("TestCaseInfo");
		for (int iNodeLoop = 1; iNodeLoop <= TestCasessheet.getLastRowNum(); iNodeLoop++) 
		{
			row = TestCasessheet.getRow(iNodeLoop);
			String TestCaseExecuteFlag = row.getCell(5).getStringCellValue();
			
			if (TestCaseExecuteFlag.equalsIgnoreCase("Y")) {
				
				try{
					TestName = row.getCell(1).getStringCellValue();
					ClassName = row.getCell(2).getStringCellValue();
					TestCaseName = row.getCell(3).getStringCellValue();
					TestBrowserName = row.getCell(4).getStringCellValue();
					//if(TestBrowserName.equalsIgnoreCase("Android") || TestBrowserName.equalsIgnoreCase("IOS")){
						HashMap<String, String> ColumnValueMap = new HashMap<String, String>();
						for(int iColumnLoop=6;iColumnLoop<=row.getLastCellNum()-1;iColumnLoop++){
							String cellValue = row.getCell(iColumnLoop, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
							String columnmName = TestCasessheet.getRow(0).getCell(iColumnLoop).getStringCellValue();
								if(!cellValue.isEmpty())
								ColumnValueMap.put(columnmName, cellValue);
							}
						TestsParamList.put(TestName, ColumnValueMap);
						//}
					
				}catch(NullPointerException e){
					logger.error("A cell seems to be blank. Please provide value to the cell");
				}
				
				TestsBrowserList.put(TestName, TestBrowserName);
				ClassesMethodList.put(ClassName, TestCaseName);
				TestsClassesList.put(TestName, ClassName);
				
				}
		}
		
		try {
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		  
	  }


	public static HashMap<String, String> getTestsBrowserList() {
		return TestsBrowserList;
	}
	

	public static Multimap<String, String> getTestsClassesList() {
		return TestsClassesList;
	}


	public static Multimap<String, String> getClassesMethodList() {
		return ClassesMethodList;
	}
	
	
	public static Hashtable<String, String> getTestNGConfig() {
		return TestNGConfig;
	}
	
	public static HashMap<String, HashMap<String, String>> getTestsParamList() {
		return TestsParamList;
	}


}
