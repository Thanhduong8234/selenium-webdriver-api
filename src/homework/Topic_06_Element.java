package homework;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

public class Topic_06_Element {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@Test
	public void TC01_ElementDisplayed() {			
//		Check point #1: 
		WebElement lbl_Email1 = driver.findElement(By.xpath("//legend[text()='Your basic info']//following-sibling::label[text()='Email:']"));
		assertTrue(lbl_Email1.isDisplayed());
		
		WebElement lbl_AgeUnder18 = driver.findElement(By.xpath("//legend[text()='Your basic info']//following-sibling::label[text()='Age:']//following-sibling::label[text()='Under 18']"));
		assertTrue(lbl_AgeUnder18.isDisplayed());
		
		WebElement lbl_Education = driver.findElement(By.xpath("//legend[text()='Your profile']//following-sibling::label[text()='Education:']"));
		assertTrue(lbl_Education.isDisplayed());
		
//		Check point #2:
		WebElement h5_User5 = driver.findElement(By.xpath("//legend[text()='Hover']//following-sibling::div//h5[text()='Name: User5']"));
		assertFalse(h5_User5.isDisplayed());
		
	}
	
	

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", projectPath +"/browserDrivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
	}

	@AfterMethod
	public void afterMethod() {
		driver.close();
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
