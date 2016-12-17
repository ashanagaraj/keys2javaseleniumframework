package in.keys2javaselenium.newtour1.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ConfirmationPage {
	private WebDriver driver = null;
	public ConfirmationPage(WebDriver d){
		this.driver = d;
	}
	public String getConfirmationMessage(){
		String str = driver.findElement(By.xpath("//table[@width='492']/tbody/tr[3]/td/p/font/b")).getText();
		return str;
	}
}
