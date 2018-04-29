/**
 * This project is using Spring 
 */
package frameworkcore.frameworkUtils;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author dtiwa1
 *
 */
public class PropertyFileReader {
	
	private static Logger logger = LoggerFactory.getLogger(PropertyFileReader.class);
	
	public static HashMap<String, String> ReadPropertyFile(){
		
	        Properties prop = new Properties();
	        Map<String,String> map = new HashMap<String,String>();
	        try
	        {
	            FileInputStream inputStream = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/propertyFiles/FrameworkConfiguration.properties");
	            prop.load(inputStream);
	        }
	        catch (Exception e) {
	        	logger.error(e.getMessage());

	        }
	        for (final Entry<Object, Object> entry : prop.entrySet()) {
	            map.put((String) entry.getKey(), (String) entry.getValue());
	        }
	        return (HashMap<String, String>) map;
	    }
}
