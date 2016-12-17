package in.keys2javaselenium.newtour1.pageobjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	private WebDriver driver = null;
	public HomePage(WebDriver driver){
		this.driver = driver;
	}
	public String getTitle(){
		return driver.getTitle();
	}
	//below are different methods to find REGISTER Link
	//we go for below 2 when few elements come up after page loads..
	public void clickRegisterLink1(){
		WebDriverWait ww = new WebDriverWait(driver,50);
		ww.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(), 'REGISTER')]"))).click();		
	}
	
	public void clickRegisterLink2(){
		FluentWait<WebDriver> fw = new FluentWait(driver);
		fw.withTimeout(120, TimeUnit.SECONDS);
		fw.pollingEvery(5, TimeUnit.SECONDS);
		
		WebElement reglink1 = fw.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(), 'REGISTER')]")));
		reglink1.click();
	}
	//we go for below when we selelct some element then another element will come up.
	public void clickRegisterLink3(){
		WebElement reglink1 = driver.findElement(By.xpath("//a[contains(text(), 'REGISTER')]"));
		reglink1.click();
		
	}
	//we go for this when we find all the elements in the UI when the page loads.
	@FindBy(xpath="//a[contains(text(), 'REGISTER')]")
	private WebElement reg1;
	public void clickRegisterLink4(){
		reg1.click();
	}
	@FindBy(how=How.XPATH, using="//a[contains(text(), 'REGISTER')]")
	private WebElement reg2;
	public void clickRegisterLink5(){
		reg2.click();
	}
}
