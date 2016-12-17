package in.keys2javaselenium.newtour1.utility;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class BrowserSelection {
	private static WebDriver driver = null;
	
	public static WebDriver getbrowser(String browser){
		
		if(browser.equalsIgnoreCase("firefox")){
			
			driver = new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("chrome")){
			driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("IE")){
			driver = new InternetExplorerDriver();
		}
		else if(browser.equalsIgnoreCase("phantom")){
			driver = new PhantomJSDriver();
		}
			
		return driver;
	}
	
	//for sel grid: for running in different m/c (client)
	//code written considerd as server
public static WebDriver getGridWebbrowser(String browser) throws MalformedURLException{
		
		if(browser.equalsIgnoreCase("firefox")){
			
			DesiredCapabilities capability = DesiredCapabilities.firefox();
			capability.setBrowserName("firefox");
			capability.setPlatform(Platform.WINDOWS);
			
			driver = new RemoteWebDriver(new URL("http://localhost:5566/wd/hub"), capability);
			
		}
		else if(browser.equalsIgnoreCase("chrome")){
			DesiredCapabilities capability = DesiredCapabilities.firefox();
			capability.setBrowserName("chrome");
			capability.setPlatform(Platform.WINDOWS);
			
			driver = new RemoteWebDriver(new URL("http://localhost:5566/wd/hub"), capability);
		}
		else if(browser.equalsIgnoreCase("IE")){
			DesiredCapabilities capability = DesiredCapabilities.firefox();
			capability.setBrowserName("IE");
			capability.setPlatform(Platform.WINDOWS);
			
			driver = new RemoteWebDriver(new URL("http://localhost:5566/wd/hub"), capability);
		}
		else if(browser.equalsIgnoreCase("phantom")){
			
		}
			
		return driver;
	}
	
	public static void openURL(String url){
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	public static String getCurrentDate(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-YYYY_hh-mm-ss");
		String str = sdf.format(date);
		return str;
	}

	public static void getScreenshot() throws IOException{
		String destination = "C:\\Temp\\"+getCurrentDate()+""+driver.getTitle().replace(":", "_").replace("\\", "_")+".png";
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(destination));
	}
	
}
