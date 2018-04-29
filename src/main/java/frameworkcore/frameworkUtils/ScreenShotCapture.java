/**
 * This project is using Spring 
 */
package frameworkcore.frameworkUtils;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;

/**
 * @author dtiwa1
 *
 */
public class ScreenShotCapture {
	
	public static String TakeScreenShot(){
		
		String ImagePath = System.getProperty("user.dir") +"/Reporting/Screenshots/" + GetCurrentTimeStamp() + "_ErrorScreenshot.png";
		try{
		BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		ImageIO.write(image, "png", new File(ImagePath));
		return ImagePath;
		}catch(Exception e){
			return "";
		}
	}
	
	private static String GetCurrentTimeStamp(){
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}

}
