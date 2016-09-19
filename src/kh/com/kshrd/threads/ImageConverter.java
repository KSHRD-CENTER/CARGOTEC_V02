package kh.com.kshrd.threads;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IMOperation;
import org.im4java.process.ProcessStarter;

/***
 *	Before you can use this, Please install the following program in the link
 *  link: https://drive.google.com/open?id=0B2qgoi8RBa0QQTdoQ3NIY3RZeW8
 *
 */
public class ImageConverter {
	
	static{
		String myPath="C:\\Program Files (x86)\\ImageMagick-6.7.5-Q8";
    	ProcessStarter.setGlobalSearchPath(myPath);
	}

	public static void convertEMF(String emfPath, String output) {
		try {
			System.out.println("[=>>Convert EMF to PNG...]");
			IMOperation img = new IMOperation();
			img.addImage();
			img.addImage();
			ConvertCmd convert = new ConvertCmd();
			convert.run(img, emfPath, output);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("[=>>Make sure you already install this program(ImageMagick-6.7.5-Q8). If not, please download and install it via this link: https://drive.google.com/open?id=0B2qgoi8RBa0QQTdoQ3NIY3RZeW8]");
			
		}
	}

}
