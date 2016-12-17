package in.keys2javaselenium.newtour1.pageobjects;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class RegisterPage {
	private WebDriver driver = null;
	
	public RegisterPage(WebDriver driver){
		this.driver = driver;
	}
	public void setFirstName(String fn){
		driver.findElement(By.name("firstName")).sendKeys(fn);
		
	}
	public void enterlastname(String ln) {
		driver.findElement(By.name("lastName")).sendKeys(ln);
	}
	public void phonename(String ph) {
		driver.findElement(By.name("phone")).sendKeys(ph);
	}

	public void emailname(String email) {
		driver.findElement(By.id("userName")).sendKeys(email);
	}
	public void clickSubmit(){
		driver.findElement(By.xpath("//input[@name='register']")).click();
	}
	
}
