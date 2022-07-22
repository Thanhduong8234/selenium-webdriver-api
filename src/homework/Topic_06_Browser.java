package homework;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

public class Topic_06_Browser {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@Test
	public void TC01_VerifyUrl() {	
//		Step #1: Click on My Account
		WebElement lnk_MyAccount = driver.findElement(By.xpath("//div[@class='footer-container']//a[text()='My Account']"));
		lnk_MyAccount.click();
		
//		Check point #1: Verify Page URL
		String pageUrl = driver.getCurrentUrl();
		assertTrue(pageUrl.equals("http://live.techpanda.org/index.php/customer/account/login/"),"#1 - "+pageUrl);
		
//		Step #2: Click on Create an account button
		WebElement btn_CreateAnAccount = driver.findElement(By.xpath("//div[@class='buttons-set']//a[@title='Create an Account']"));
		btn_CreateAnAccount.click();
		
//		Check point #2: Verify Page URL
		pageUrl = driver.getCurrentUrl();
		assertTrue(pageUrl.equals("http://live.techpanda.org/index.php/customer/account/create/"),"#2 - "+pageUrl);
		
		
	}
	
	@Test
	public void TC02_VerifyTitle() {
//		Step #1: Click on My Account
		WebElement lnk_MyAccount = driver.findElement(By.xpath("//div[@class='footer-container']//a[text()='My Account']"));
		lnk_MyAccount.click();
		
//		Check point #1: Verify Title
		assertTrue(driver.getTitle().equals("Customer Login"),"#1 - "+ driver.getTitle());
		
//		Step #2: Click on Create an account button
		WebElement btn_CreateAnAccount = driver.findElement(By.xpath("//div[@class='buttons-set']//a[@title='Create an Account']"));
		btn_CreateAnAccount.click();
		
//		Check point #2: Verify title
		assertTrue(driver.getTitle().equals("Create New Customer Account"),"#1 - "+ driver.getTitle());
		
	}
	
	@Test
	public void TC03_NavigateFunction() {
//		Step #1: Click on My Account
		WebElement lnk_MyAccount = driver.findElement(By.xpath("//div[@class='footer-container']//a[text()='My Account']"));
		lnk_MyAccount.click();
		
//		Step #2: Click on Create an account button
		WebElement btn_CreateAnAccount = driver.findElement(By.xpath("//div[@class='buttons-set']//a[@title='Create an Account']"));
		btn_CreateAnAccount.click();
		
//		Check point #1: Verify Page URL
		assertTrue(driver.getCurrentUrl().equals("http://live.techpanda.org/index.php/customer/account/create/"),"#2 - "+ driver.getCurrentUrl());
		
//		Step #3: Back to Login page
		driver.navigate().back();
		
//		Check point #2: Verify Page URL
		assertTrue(driver.getCurrentUrl().equals("http://live.techpanda.org/index.php/customer/account/login/"),"#2 - "+ driver.getCurrentUrl());

//		Step #4: Back to Login page
		driver.navigate().forward();
		
//		Check point #3: Verify title
		assertTrue(driver.getTitle().equals("Create New Customer Account"),"#1 - "+ driver.getTitle());
	}
	
	@Test
	public void TC04_GetPageSourceCode() {
//		Step #1: Click on My Account
		WebElement lnk_MyAccount = driver.findElement(By.xpath("//div[@class='footer-container']//a[text()='My Account']"));
		lnk_MyAccount.click();
		
//		Check point #1: Verify text in Login page
		assertTrue(driver.getPageSource().contains("Login or Create an Account"));
		
//		Step #2: Click on Create an account button
		WebElement btn_CreateAnAccount = driver.findElement(By.xpath("//div[@class='buttons-set']//a[@title='Create an Account']"));
		btn_CreateAnAccount.click();
		
//		Check point #1: Verify text in Register page
		assertTrue(driver.getPageSource().contains("Create an Account"));
		
	}

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", projectPath +"/browserDrivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		
		driver.get("http://live.techpanda.org/");
		
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
