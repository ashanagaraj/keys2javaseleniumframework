package in.keys2javaselenium.newtour1.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class CommonLibrary {
	public static String getPropertyValue(String key, String filename) throws IOException{
		FileInputStream fs= new FileInputStream("\\TestData\\"+filename+".properties");
		Properties p = new Properties();
		p.load(fs);
		return p.getProperty(key);
	}
}
