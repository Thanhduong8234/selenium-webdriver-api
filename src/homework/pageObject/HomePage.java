package homework.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import homework.Common.common;

public class HomePage extends common {
	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	
	//locator
	WebElement lnk_myaccount = driver.findElement(By.xpath("//div[@class='footer-container']//a[text()='My Account']"));

}
