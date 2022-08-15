package homework;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

public class Topic_06_Login {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Random rand = new Random();
	int random = rand.nextInt();

	String fname = "automation";
	String lname = "Test" + random;
	String email = "thanh.tat.duong8234+" + fname + lname + "@gmail.com";
	String password = "123456";

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

//	@Test
	public void TC01_LoginWithEmptyEmailAndPassword() {
//		Step #1: Go to "http://live.techpanda.org/"
		driver.get("http://live.techpanda.org/");

//		Step #2: Click on My Account 
		WebElement lnk_myaccount = driver
				.findElement(By.xpath("//div[@class='footer-container']//a[text()='My Account']"));
		lnk_myaccount.click();

//		Step #3: keep username & password blank
		WebElement inp_username = driver.findElement(By.xpath("//input[@id='email']"));
		WebElement inp_password = driver.findElement(By.xpath("//input[@id='pass']"));

		inp_username.clear();
		inp_password.clear();
//		Step #4: Click on Login button
		WebElement btn_Login = driver.findElement(By.xpath("//button[@id='send2']"));
		btn_Login.click();

//		Check point
		WebElement emailMessage = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']"));
		WebElement passwordMessage = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']"));

		assertTrue(emailMessage.isDisplayed() && passwordMessage.isDisplayed());
	}

//	@Test
	public void TC02_LoginWithInvalidEmail() {
//		Step #1: Go to "http://live.techpanda.org/"
		driver.get("http://live.techpanda.org/");
//		Step #2: Click on My Account 
		WebElement lnk_myaccount = driver
				.findElement(By.xpath("//div[@class='footer-container']//a[text()='My Account']"));
		lnk_myaccount.click();

//		Step #3: 
		WebElement inp_username = driver.findElement(By.xpath("//input[@id='email']"));
		WebElement inp_password = driver.findElement(By.xpath("//input[@id='pass']"));

		inp_username.sendKeys("123434234@12312.123123");
		inp_password.sendKeys("123456");
//		Step #4: Click on Login button
		WebElement btn_Login = driver.findElement(By.xpath("//button[@id='send2']"));
		btn_Login.click();

		WebElement emailMessage = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']"));

		assertTrue(
				emailMessage.getText().equals("Please enter a valid email address. For example johndoe@domain.com."));
	}

//	@Test
	public void TC03_LoginWithInvalidPassword() {
//		Step #1: Go to "http://live.techpanda.org/"
		driver.get("http://live.techpanda.org/");
//		Step #2: Click on My Account 
		WebElement lnk_myaccount = driver
				.findElement(By.xpath("//div[@class='footer-container']//a[text()='My Account']"));
		lnk_myaccount.click();

//		Step #3: 
		WebElement inp_username = driver.findElement(By.xpath("//input[@id='email']"));
		WebElement inp_password = driver.findElement(By.xpath("//input[@id='pass']"));

		inp_username.sendKeys("automation@gmail.com");
		inp_password.sendKeys("123");
//		Step #4: Click on Login button
		WebElement btn_Login = driver.findElement(By.xpath("//button[@id='send2']"));
		btn_Login.click();

		WebElement passwordMessage = driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']"));

		assertTrue(passwordMessage.getText()
				.equals("Please enter 6 or more characters without leading or trailing spaces."));
	}

//	@Test
	public void TC04_LoginWithInvalidEmailorPassword() {
//		Step #1: Go to "http://live.techpanda.org/"
		driver.get("http://live.techpanda.org/");
//		Step #2: Click on My Account 
		WebElement lnk_myaccount = driver
				.findElement(By.xpath("//div[@class='footer-container']//a[text()='My Account']"));
		lnk_myaccount.click();

//		Step #3: 
		WebElement inp_username = driver.findElement(By.xpath("//input[@id='email']"));
		WebElement inp_password = driver.findElement(By.xpath("//input[@id='pass']"));

		inp_username.sendKeys("automation@gmail.com");
		inp_password.sendKeys("123123121");
//		Step #4: Click on Login button
		WebElement btn_Login = driver.findElement(By.xpath("//button[@id='send2']"));
		btn_Login.click();

		WebElement alertMessage = driver.findElement(By.xpath("//li[@class='error-msg']//span"));
		assertTrue(alertMessage.getText().equals("Invalid login or password."));
	}

	@Test
	public void TC05_CreateANewAccount() {
//		Step #1: Go to "http://live.techpanda.org/"
		driver.get("http://live.techpanda.org/");
//		Step #2: Click on My Account 
		WebElement lnk_myaccount = driver
				.findElement(By.xpath("//div[@class='footer-container']//a[text()='My Account']"));
		lnk_myaccount.click();
//		Step #3: Click on Create An Account
		WebElement btn_createaccount = driver.findElement(By.xpath("//a[@class='button']"));
		btn_createaccount.click();
//		Step #4: Fill valid data to all fields to Create account
		WebElement inp_firstname = driver.findElement(By.xpath("//input[@id='firstname']"));
		WebElement inp_lastname = driver.findElement(By.xpath("//input[@id='lastname']"));
		WebElement inp_email = driver.findElement(By.xpath("//input[@id='email_address']"));
		WebElement inp_password = driver.findElement(By.xpath("//input[@id='password']"));
		WebElement inp_cfpassword = driver.findElement(By.xpath("//input[@id='confirmation']"));
		WebElement btn_resgister = driver.findElement(By.xpath("//button[@title='Register']"));

		System.out.println(email);

		inp_firstname.sendKeys(fname);
		inp_lastname.sendKeys(lname);
		inp_email.sendKeys(email);
		inp_password.sendKeys(password);
		inp_cfpassword.sendKeys(password);
		btn_resgister.click();

		WebElement successMessage = driver.findElement(By.xpath("//ul[@class='messages']//span"));
		WebElement hello_name = driver.findElement(By.xpath("//*[@class='hello']//strong"));
		WebElement contact_information = driver.findElement(By.xpath("//*[@class='box-content']//p"));

		System.out.println(hello_name.getText());

		assertTrue(successMessage.getText().equals("Thank you for registering with Main Website Store.")
				&& hello_name.getText().equals("Hello, " + fname + " " + lname + "!")
				&& contact_information.getText().contains(fname + " " + lname)
				&& contact_information.getText().contains(email));
	}

	@Test
	public void TC06_LoginWithvalidEmailnPassword() {
//		Step #1: Go to "http://live.techpanda.org/"
		driver.get("http://live.techpanda.org/");
//		Step #2: Click on My Account 
		WebElement lnk_myaccount = driver
				.findElement(By.xpath("//div[@class='footer-container']//a[text()='My Account']"));
		lnk_myaccount.click();

//		Step #3: 
		WebElement inp_username = driver.findElement(By.xpath("//input[@id='email']"));
		WebElement inp_password = driver.findElement(By.xpath("//input[@id='pass']"));

		inp_username.sendKeys(email);
		inp_password.sendKeys(password);
//		Step #4: Click on Login button
		WebElement btn_Login = driver.findElement(By.xpath("//button[@id='send2']"));
		btn_Login.click();
		
		WebElement hello_name = driver.findElement(By.xpath("//*[@class='hello']//strong"));
		WebElement contact_information = driver.findElement(By.xpath("//*[@class='box-content']//p"));

		System.out.println(hello_name.getText());

		assertTrue( hello_name.getText().equals("Hello, " + fname + " " + lname + "!")
				&& contact_information.getText().contains(fname + " " + lname)
				&& contact_information.getText().contains(email));

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
