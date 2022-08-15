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

public class Topic_09_HandleButton {
// Test Based
	WebDriver driver;
	WebDriverWait wait;
	Actions actions;
	Random random;
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
	public void TC01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");

		By frame = By.cssSelector("iframe#preview-notification-frame");
		By by_xPopupBy = By.cssSelector("img#NC_IMAGE1");

		// Close pop up
		if (driver.findElement(frame).isDisplayed()) {
			driver.switchTo().frame(driver.findElement(frame));
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(by_xPopupBy)));
			driver.findElement(by_xPopupBy).click();
			driver.switchTo().defaultContent();

		}

		switchTab("Đăng nhập");

		By brn_login = By.xpath("//div[contains(@class,'popup-login')]//button[@title='Đăng nhập']");

		// disabled
		assertFalse(isElementEnable(brn_login));

		driver.findElement(By.cssSelector("#login_username")).sendKeys("testemail@gmail.com");
		driver.findElement(By.cssSelector("#login_password")).sendKeys("testemail@gmail.com");

		// enabled
		assertTrue(isElementEnable(brn_login));

		// get rgbacolor
		String rgbaColor = driver.findElement(brn_login).getCssValue("background-color");

		// rgba > hexa
		String hexaColor = Color.fromString(rgbaColor).asHex();
		System.out.println(hexaColor);
		assertEquals("#c92127", hexaColor);

	}

//	@Test
	public void TC02_DefaultCheckboxOrRadioButton() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		By by_loadingDemo = By.cssSelector("div.kd-loader-wrap>span");

		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(by_loadingDemo)));

		By by_dualZoneAirConditioning = By.cssSelector("input#eq5");

		assertTrue(clickCheckbox(by_dualZoneAirConditioning));
		assertFalse(clickCheckbox(by_dualZoneAirConditioning));

		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");

		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(by_loadingDemo)));

		By by_2Petrol147kW = By.cssSelector("input#engine3");
		By by_acceptCookie = By.cssSelector("button#onetrust-accept-btn-handler");
		driver.findElement(by_acceptCookie).click();

		assertTrue(clickRadioButton(by_2Petrol147kW));

	}

	@Test
	private void TC03_CustomCheckboxOrRadioButton() {
		driver.get("https://material.angular.io/components/radio/examples");

		String winter = "//input[@value='Winter']";
		String spring = "//input[@value='Spring']";
		String summer = "//input[@value='Summer']";
		String autumn = "//input[@value='Autumn']";
		String radio = "span[@class='mat-radio-container']";

		assertTrue(clickCustomCheckbox1(autumn, radio));
		assertTrue(clickCustomCheckbox1(spring, radio));
		assertTrue(clickCustomCheckbox1(summer, radio));
		assertTrue(clickCustomCheckbox1(winter, radio));

		driver.get("https://material.angular.io/components/checkbox/examples");

		By by_checked = By.cssSelector("#mat-checkbox-1");
		By by_indeterminate = By.cssSelector("#mat-checkbox-2");

		assertTrue(clickCustomCheckbox2(by_checked));
		assertTrue(clickCustomCheckbox2(by_indeterminate));
		assertFalse(clickCustomCheckbox2(by_checked));
		assertFalse(clickCustomCheckbox2(by_indeterminate));
	}

// Function

	public boolean clickCustomCheckbox2(By by) {
		WebElement element = driver.findElement(by);
		element.click();
		return isCustomCB2Selected(by);
	}

	public boolean isCustomCB2Selected(By by) {
		WebElement element = driver.findElement(by);
		return element.getAttribute("class").contains("mat-checkbox-checked");
	}

	public boolean clickCustomCheckbox1(String str1, String str2) {
		WebElement element = driver.findElement(By.xpath(str1));
		WebElement radio = driver.findElement(By.xpath(str1 + "//parent::" + str2));
		if (!element.isSelected()) {
			radio.click();
			return true;
		} else {
			return false;
		}

	}

	public boolean clickRadioButton(By by) {
		WebElement element = driver.findElement(by);
		if (!element.isSelected()) {
			element.click();
			return true;
		}
		return false;
	}

	public boolean clickCheckbox(By by) {
		WebElement element = driver.findElement(by);
		element.click();
		return element.isSelected();
	}

	public String[] split(String splitString) {

		String str = splitString;
		String[] arrStrings = str.split(",");
		return arrStrings;
	}

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

	public boolean isElementEnable(By by) {
		WebElement element = driver.findElement(by);
		if (element.isEnabled()) {
			System.out.println("Element is Enabled: " + by);
		} else {
			System.out.println("Element is Disabled: " + by);
		}

		return element.isEnabled();
	}

	public void switchTab(String tabName) {
		String str = String.format("//ul[@id='popup-login-tab_list']//a[contains(text(),'%s')]", tabName);
		WebElement tab = driver.findElement(By.xpath(str));
		tab.click();
	}
}
