/**
 * Core Framework
 * Author : Deepak Tiwari
 * Creation Date : 27 Apr 2018
 * Modified Date : 
 * Modified By : 
 */
package frameworkcore.frameworkUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The below class reads the Framework specific properties
 */
public class PropertyFileReader {
	
	private static Logger logger = LoggerFactory.getLogger(PropertyFileReader.class);
	
	public static HashMap<String, String> ReadPropertyFile() throws IOException{
		
		logger.info("Reading Property File");
	        Properties prop = new Properties();
	        Map<String,String> map = new HashMap<String,String>();

	            FileInputStream inputStream = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/propertyFiles/FrameworkConfiguration.properties");
	            prop.load(inputStream);
	        
	        for (final Entry<Object, Object> entry : prop.entrySet()) {
	            map.put((String) entry.getKey(), (String) entry.getValue());
	        }
	        return (HashMap<String, String>) map;
	    }
}
