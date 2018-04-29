/**
 * This project is using Spring 
 */
package frameworkcore.exceptionsPkg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import frameworkcore.ReportingClass.Reporting;

/**
 * @author dtiwa1
 *
 */
public class ElementNotFoundException extends Exception {
	
	private static Logger logger = LoggerFactory.getLogger(ElementNotFoundException.class);

	public ElementNotFoundException(String message){
		super(message);
		
	}
	
	

}
