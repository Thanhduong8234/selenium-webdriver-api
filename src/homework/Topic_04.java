package homework;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_04 {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		// #1: Go to Register page
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	}

	@BeforeTest
	public void beforeTest() {

	}

	@AfterTest
	public void afterTest() {

	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

	@Test
	public void TC01_RegisterWithEmptyData() {

		// #2: Click on Click on "Đăng Ký" button
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtCEmail-error")));

		// #3: Verify error messages each field
		WebElement lbl_ErrorUsername = driver.findElement(By.xpath("//*[@id='txtFirstname-error']"));
		WebElement lbl_ErrorEmail = driver.findElement(By.xpath("//*[@id='txtEmail-error']"));
		WebElement lbl_ErrorCEmail = driver.findElement(By.xpath("//*[@id='txtCEmail-error']"));
		WebElement lbl_ErrorPassword = driver.findElement(By.xpath("//*[@id='txtPassword-error']"));
		WebElement lbl_ErrorRePasword = driver.findElement(By.xpath("//*[@id='txtCPassword-error']"));
		WebElement lbl_ErrorPhoneNumbers = driver.findElement(By.xpath("//*[@id='txtPhone-error']"));

		String ErrorUsername = "Vui lòng nhập họ tên";
		String ErrorEmail = "Vui lòng nhập email";
		String ErrorCEmail = "Vui lòng nhập lại địa chỉ email";
		String ErrorPassword = "Vui lòng nhập mật khẩu";
		String ErrorCPassword = "Vui lòng nhập lại mật khẩu";
		String ErrorPhoneNummber = "Vui lòng nhập số điện thoại.";

		assertTrue(lbl_ErrorUsername.getText().equals(ErrorUsername), "Fail by Username");
		assertTrue(lbl_ErrorEmail.getText().equals(ErrorEmail), "Fail by Email");
		assertTrue(lbl_ErrorCEmail.getText().equals(ErrorCEmail), "Fail by CEmail");
		assertTrue(lbl_ErrorPassword.getText().equals(ErrorPassword), "Fail by Password");
		assertTrue(lbl_ErrorRePasword.getText().equals(ErrorCPassword), "Fail by CPassword");
		assertTrue(lbl_ErrorPhoneNumbers.getText().equals(ErrorPhoneNummber), "Fail by PhoneNumbers");

	}

	@Test
	public void TC02_RegisterWithInvalidEmail() {

		// #2: Fill data except Email & CEmail
		WebElement inp_UserName = driver.findElement(By.xpath("//*[@id='txtFirstname']"));
		WebElement inp_Password = driver.findElement(By.xpath("//*[@id='txtPassword']"));
		WebElement inp_CPassword = driver.findElement(By.xpath("//*[@id='txtCPassword']"));
		WebElement inp_PhoneNumbers = driver.findElement(By.xpath("//*[@id='txtPhone']"));

		String ErrorEmail = "Vui lòng nhập email";
		String ErrorCEmail = "Vui lòng nhập lại địa chỉ email";

		inp_UserName.sendKeys("username");
		inp_Password.sendKeys("password");
		inp_CPassword.sendKeys("password");
		inp_PhoneNumbers.sendKeys("0123456789");

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='txtCEmail-error']")));
		WebElement lbl_ErrorEmail = driver.findElement(By.xpath("//*[@id='txtEmail-error']"));
		WebElement lbl_ErrorCEmail = driver.findElement(By.xpath("//*[@id='txtCEmail-error']"));

		assertTrue(lbl_ErrorEmail.getText().equals(ErrorEmail), "Fail by Email");
		assertTrue(lbl_ErrorCEmail.getText().equals(ErrorCEmail), "Fail by CEmail");

	}

	@Test
	public void TC03_RegisterWithIncorrectEmail() {

		// #2: Fill data & invalid CEmail
		WebElement inp_UserName = driver.findElement(By.xpath("//*[@id='txtFirstname']"));
		WebElement inp_Password = driver.findElement(By.xpath("//*[@id='txtPassword']"));
		WebElement inp_Email = driver.findElement(By.xpath("//*[@id='txtEmail']"));
		WebElement inp_CEmail = driver.findElement(By.xpath("//*[@id='txtCEmail']"));
		WebElement inp_CPassword = driver.findElement(By.xpath("//*[@id='txtCPassword']"));
		WebElement inp_PhoneNumbers = driver.findElement(By.xpath("//*[@id='txtPhone']"));

		String ErrorCEmail = "Email nhập lại không đúng";

		inp_UserName.sendKeys("username");
		inp_Email.sendKeys("username@gmail.com");
		inp_CEmail.sendKeys("Invalidemail@gmail.com");
		inp_Password.sendKeys("password");
		inp_CPassword.sendKeys("password");
		inp_PhoneNumbers.sendKeys("0123456789");

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='txtCEmail-error']")));

		// Check point
		WebElement lbl_ErrorCEmail = driver.findElement(By.xpath("//*[@id='txtCEmail-error']"));
		assertTrue(lbl_ErrorCEmail.getText().equals(ErrorCEmail));

	}

	@Test
	public void TC04_RegisterWithShortPassword() {

		// #2: Fill data & short password
		WebElement inp_UserName = driver.findElement(By.xpath("//*[@id='txtFirstname']"));
		WebElement inp_Password = driver.findElement(By.xpath("//*[@id='txtPassword']"));
		WebElement inp_Email = driver.findElement(By.xpath("//*[@id='txtEmail']"));
		WebElement inp_CEmail = driver.findElement(By.xpath("//*[@id='txtCEmail']"));
		WebElement inp_CPassword = driver.findElement(By.xpath("//*[@id='txtCPassword']"));
		WebElement inp_PhoneNumbers = driver.findElement(By.xpath("//*[@id='txtPhone']"));

		String ErrorPassword = "Mật khẩu phải có ít nhất 6 ký tự";

		inp_UserName.sendKeys("username");
		inp_Email.sendKeys("username@gmail.com");
		inp_CEmail.sendKeys("username@gmail.com");
		inp_Password.sendKeys("passw");
		inp_CPassword.sendKeys("passw");
		inp_PhoneNumbers.sendKeys("0123456789");

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='txtCPassword-error']")));

		// Check point
		WebElement lbl_ErrorPassword = driver.findElement(By.xpath("//*[@id='txtPassword-error']"));
		WebElement lbl_ErrorRePasword = driver.findElement(By.xpath("//*[@id='txtCPassword-error']"));

		assertTrue(lbl_ErrorPassword.getText().equals(ErrorPassword));
		assertTrue(lbl_ErrorRePasword.getText().equals(ErrorPassword));

	}

	@Test
	public void TC05_RegisterIncorrectCPassword() {

		// #2: Fill data & short password
		WebElement inp_UserName = driver.findElement(By.xpath("//*[@id='txtFirstname']"));
		WebElement inp_Password = driver.findElement(By.xpath("//*[@id='txtPassword']"));
		WebElement inp_Email = driver.findElement(By.xpath("//*[@id='txtEmail']"));
		WebElement inp_CEmail = driver.findElement(By.xpath("//*[@id='txtCEmail']"));
		WebElement inp_CPassword = driver.findElement(By.xpath("//*[@id='txtCPassword']"));
		WebElement inp_PhoneNumbers = driver.findElement(By.xpath("//*[@id='txtPhone']"));

		String ErrorCPassword = "Mật khẩu bạn nhập không khớp";

		inp_UserName.sendKeys("username");
		inp_Email.sendKeys("username@gmail.com");
		inp_CEmail.sendKeys("username@gmail.com");
		inp_Password.sendKeys("password");
		inp_CPassword.sendKeys("invalidpassword");
		inp_PhoneNumbers.sendKeys("0123456789");

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='txtCPassword-error']")));

		// Check point
		WebElement lbl_ErrorRePasword = driver.findElement(By.xpath("//*[@id='txtCPassword-error']"));

		assertTrue(lbl_ErrorRePasword.getText().equals(ErrorCPassword));

	}

	@Test
	public void TC06_RegisterInvalidPhoneNumbers() {

		// #2: Fill data & short password
		WebElement inp_UserName = driver.findElement(By.xpath("//*[@id='txtFirstname']"));
		WebElement inp_Password = driver.findElement(By.xpath("//*[@id='txtPassword']"));
		WebElement inp_Email = driver.findElement(By.xpath("//*[@id='txtEmail']"));
		WebElement inp_CEmail = driver.findElement(By.xpath("//*[@id='txtCEmail']"));
		WebElement inp_CPassword = driver.findElement(By.xpath("//*[@id='txtCPassword']"));
		WebElement inp_PhoneNumbers = driver.findElement(By.xpath("//*[@id='txtPhone']"));

		String ErrorPhoneNumbers01 = "Số điện thoại phải từ 10-11 số.";
		String ErrorPhoneNumbers02 = "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019";

		inp_UserName.sendKeys("username");
		inp_Email.sendKeys("username@gmail.com");
		inp_CEmail.sendKeys("username@gmail.com");
		inp_Password.sendKeys("password");
		inp_CPassword.sendKeys("password");
		inp_PhoneNumbers.sendKeys("0123456"); // Phone Numbers Less than 10 digits

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='txtPhone-error']")));

		// Check point 1
		WebElement lbl_ErrorPhoneNumbers = driver.findElement(By.xpath("//*[@id='txtPhone-error']"));

		assertTrue(lbl_ErrorPhoneNumbers.getText().equals(ErrorPhoneNumbers01), lbl_ErrorPhoneNumbers.getText() + "-1");

		inp_PhoneNumbers.clear();
		inp_PhoneNumbers.sendKeys("123456789"); // Invalid Type phone numbers
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		// Check point 2
		assertTrue(lbl_ErrorPhoneNumbers.getText().equals(ErrorPhoneNumbers02), lbl_ErrorPhoneNumbers.getText() + "-2");

	}

}
