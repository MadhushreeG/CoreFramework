/**
 * This project is using Spring 
 */
package frameworkcore.excelReader;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author dtiwa1
 *
 */
public class InputDataReaderImpl {
	
	private static HashMap<String, HashMap<String, HashMap<String, String>>> InputData = new HashMap<String, HashMap<String, HashMap<String, String>>>();
	
	public static void ReadExcelInputData(){
		
		String TestCaseWorkbookName = "InputData.xlsx";
		int iterator;
		String SheetName, cellValue;
		Row row=null;
		XSSFWorkbook workbook=null;
		FileInputStream file=null;
		
		try{
			if(file==null)
				file = new FileInputStream(new File(System.getProperty("user.dir") + "/src/test/resources/inputdata/" + TestCaseWorkbookName));	    	
				workbook = new XSSFWorkbook(file);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		for (iterator=0; iterator<workbook.getNumberOfSheets(); iterator++) {
			SheetName = workbook.getSheetName(iterator) ;
			Sheet Sheet = workbook.getSheet(SheetName);
			HashMap<String, HashMap<String, String>> TestCaseMap = new HashMap<String, HashMap<String, String>>();
			
			for (int iRowLoop = 1; iRowLoop <= Sheet.getLastRowNum(); iRowLoop++) 
			{
				HashMap<String, String> ColumnValueMap = new HashMap<String, String>();
				row = Sheet.getRow(iRowLoop);
				for(int iColumnLoop=1;iColumnLoop<=row.getLastCellNum()-1;iColumnLoop++){
					
					String columnmName = Sheet.getRow(0).getCell(iColumnLoop).getStringCellValue();
					cellValue = row.getCell(iColumnLoop, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
					if(!cellValue.isEmpty())
						ColumnValueMap.put(columnmName, cellValue);
				}
				String TestCaseName = row.getCell(0).getStringCellValue();
				TestCaseMap.put(TestCaseName, ColumnValueMap);	
			}
			InputData.put(SheetName, TestCaseMap);
		}
		
	}
	
	public static String GetDataValue(String param){
		
		String[] arr = param.split("\\.");
		return InputData.get(arr[0]).get(arr[1]).get(arr[2]);
	}
}
