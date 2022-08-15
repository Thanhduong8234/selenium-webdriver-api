package homework;

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.plaf.basic.BasicSplitPaneDivider;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.Color;

public class Topic_09_HandleButton2 {
// Test Based
	WebDriver driver;
	WebDriverWait wait;
	Actions actions;
	Random random;
	Alert alert;
	JavascriptExecutor jsExecutor;

	Thread thread;

	String projectPath = System.getProperty("user.dir");

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver.exe");
		driver = new ChromeDriver();

		wait = new WebDriverWait(driver, 10);

		jsExecutor = (JavascriptExecutor) driver;

		actions = new Actions(driver);

		random = new Random();

		thread = new Thread();

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//		driver.manage().window().maximize();
	}

//	@AfterMethod
	public void afterMethod() {
		driver.close();
	}

//	@AfterClass
	public void afterClass() {
		driver.quit();
	}

// Test Case
//	@Test
	public void TC04_CustomCB() {

		driver.get(
				"https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");

		WebElement cb_cantho = driver.findElement(By.cssSelector("#i14"));

		cb_cantho.click();
		assertTrue(cb_cantho.getAttribute("aria-checked").contains("true"));

	}

//	@Test
	public void TC0567_AcceptAlert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		WebElement jsAlert = driver.findElement(By.cssSelector("button[onclick='jsAlert()']"));
		WebElement jsCAlert = driver.findElement(By.cssSelector("button[onclick='jsConfirm()']"));
		WebElement jsPAlert = driver.findElement(By.cssSelector("button[onclick='jsPrompt()']"));

//		 alert = driver.switchTo().alert();

//		jsAlert.click();
//		assertEquals("I am a JS Alert", alert.getText());
//		alert.dismiss();

//		jsCAlert.click();
//		
//		alert = driver.switchTo().alert();
//		
//		alert.dismiss();
//		WebElement result = driver.findElement(By.cssSelector("#result"));
//		assertEquals(result.getText(), "You clicked: Cancel");

		String text = "abc";

		jsPAlert.click();
		alert = driver.switchTo().alert();
		alert.sendKeys(text);
		alert.accept();
		WebElement result = driver.findElement(By.cssSelector("#result"));
		assertEquals(result.getText(), "You entered: " + text);

	}

//	@Test
	private void TC08_AuthenticationAlert() {

		String username = "admin";
		String password = "admin";
		driver.get("http://" + username + ":" + password + "@the-internet.herokuapp.com/basic_auth");
		WebElement result = driver.findElement(By.tagName("p"));

		assertTrue(result.getText().contains("Congratulations! You must have the proper credentials."));

	}

	@Test
	private void TC09_AuthenticationAlertAutoIT() {

		String username = "admin";
		String password = "admin";
		driver.get("http://the-internet.herokuapp.com/basic_auth");

		alert = driver.switchTo().alert();
		alert.sendKeys(username);
		alert.sendKeys("(TAB)");
		alert.sendKeys(password);
		alert.accept();
		WebElement result = driver.findElement(By.tagName("p"));

		
		assertTrue(result.getText().contains("Congratulations! You must have the proper credentials."));

	}

// Function

	public void sleep(int milisecond) {
		try {
			thread.sleep(milisecond);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void scrollToElement(String cssLocator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.cssSelector(cssLocator)));
	}

}
