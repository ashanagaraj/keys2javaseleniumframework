package in.keys2javaselenium.newtour1.testrunner;

import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import in.keys2javaselenium.newtour1.pageobjects.ConfirmationPage;
import in.keys2javaselenium.newtour1.pageobjects.HomePage;
import in.keys2javaselenium.newtour1.pageobjects.RegisterPage;
import in.keys2javaselenium.newtour1.utility.BrowserSelection;
import in.keys2javaselenium.newtour1.utility.ExcelReader;
import in.keys2javaselenium.newtour1.utility.NewToursListener;

@Listeners(NewToursListener.class)
public class NewToursRunner {
	private static WebDriver driver = null;
	HomePage hp = null;
	RegisterPage rp = null;
	ConfirmationPage cp = null;
	
	@Parameters({"bn", "url"})
	@BeforeMethod
	public void init(@Optional("firefox") String bname, @Optional("http://newtours.demoaut.com/") String url) throws MalformedURLException{
		driver = BrowserSelection.getbrowser(bname);
		BrowserSelection.openURL(url);
		hp = PageFactory.initElements(driver, HomePage.class);
	}
	@Test(priority=10, dataProvider="newtours", dataProviderClass=ExcelReader.class)
	public void verifyHomePage(String pageTitle, String rownum) throws Throwable{
		String testResult = "fail";
		String errorMsg = "";
		try{
			String actualPageTitle = hp.getTitle();
			
			System.out.println(pageTitle);
			System.out.println(actualPageTitle);
			System.out.println(rownum);
			Assert.assertEquals(actualPageTitle, pageTitle);
			testResult="pass";
		}catch(Throwable e){
			errorMsg = e.getMessage();// to get the error message
			throw e;
			
		}
		finally{
			ExcelReader.writeXL(testResult, new Integer(rownum), 16);
			ExcelReader.writeXL(errorMsg, new Integer(rownum), 17);
		}
		
		//or we can use below way also
//		Assert.assertTrue(actualPageTitle.equals(pageTitle));
	}
	
	@Test(priority=20, dataProvider="newtours", dataProviderClass=ExcelReader.class)
	public void verifyRegistrationProcess(String data1, String data2, String data3, String data4, String data5, String data6, String data7, 
			String data8, String data9, String data10, String data11, String data12, String data13, String rownum){
		
		hp.clickRegisterLink1();
		rp = PageFactory.initElements(driver, RegisterPage.class);
		
		System.out.println(rownum);
		rp.setFirstName(data1);
		rp.enterlastname(data2);
		rp.phonename(data3);
		rp.emailname(data4);
		rp.clickSubmit();
		
		ConfirmationPage cp = new ConfirmationPage(driver);
		String ActualConfirmMsg = cp.getConfirmationMessage();
		
		System.out.println(ActualConfirmMsg);
		
		Assert.assertTrue(data13.contains(ActualConfirmMsg));
		
		
	}
		
	@AfterMethod
	public void closeBrowsers(){
		driver.close();
	}
}
