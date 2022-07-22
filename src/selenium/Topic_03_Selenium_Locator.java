package selenium;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic_03_Selenium_Locator {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
 
	@BeforeClass
	public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		driver.get("https://demo.guru99.com/test/login.html");
	}
	
	@Test
	public void TC_01() {
		
		
	}
	
	@Test
	public void TC_02_Id() {
		//Selenium locator
		driver.findElement(By.id("SubmitLogin")).click();
		
		//Verify Login success
		assertTrue(driver.findElement(By.tagName("h3")).getText().equals("Successfully Logged in..."));
		
		
	}
	
	@Test
	public void TC_03_Tagname() {
		driver.navigate().back();
		driver.findElement(By.tagName("button")).click();

		//Verify Login success
		assertTrue(driver.findElement(By.tagName("h3")).getText().equals("Successfully Logged in..."));
	
		
	}
	
	@Test
	public void TC_04_Name() {
		driver.navigate().back();
			
		driver.findElement(By.name("SubmitLogin")).click();
			
		//Verify Login success
		assertTrue(driver.findElement(By.tagName("h3")).getText().equals("Successfully Logged in..."));
						
	}
	
	@Test
	public void TC_05_List() {
		driver.navigate().back();
		//Show all link on the page
		List<WebElement> loginPageLinksElements = driver.findElements(By.tagName("a"));
		
		for (WebElement webElement : loginPageLinksElements) {
			System.out.println(webElement.getText());
		}
		
		
	}
	
	@Test
	public void TC_06_LinkText() {
		boolean result = driver.findElement(By.linkText("Forgot your password?")).isDisplayed();
		
		assertTrue(result);
		
	}
	
	@Test
	public void TC_07_PartialLinkText() {
		boolean result = driver.findElement(By.partialLinkText("Forgot your")).isDisplayed();
		
		assertTrue(result);
	}
	
	@Test
	public void TC_08_Css() {
		driver.findElement(By.cssSelector("#email")).sendKeys("123456678");
		driver.findElement(By.cssSelector("#passwd")).sendKeys("456546546");
		
	}
	
	@Test
	public void TC_09_Xpath() {
		driver.findElement(By.xpath("//button[@type='submit']"));
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
