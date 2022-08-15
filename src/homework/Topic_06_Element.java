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
		WebElement inp_Email = driver.findElement(By.xpath("//input[@id='mail']"));
		WebElement rad_under18 = driver.findElement(By.xpath("//input[@id='under_18']"));
		WebElement inp_Edu = driver.findElement(By.xpath("//textarea[@id='edu']"));
		WebElement h5_User5 = driver
				.findElement(By.xpath("//legend[text()='Hover']//following-sibling::div//h5[text()='Name: User5']"));

		String value = "Automation Testing";
		if (inp_Email.isDisplayed() && inp_Edu.isDisplayed()) {
			inp_Email.sendKeys(value);
			inp_Edu.sendKeys(value);
		}

		if (rad_under18.isDisplayed()) {
			rad_under18.click();
		}

		if (h5_User5.isDisplayed()) {
			System.out.println("Element is displayed");
		} else {
			System.out.println("Element isn't displayed");
		}

	}

	@Test
	public void TC02_ElementEnabled_Disabled() {
		WebElement inp_email = driver.findElement(By.xpath("//input[@id='mail']"));
		WebElement rad_under18 = driver.findElement(By.xpath("//input[@id='under_18']"));
		WebElement area_edu = driver.findElement(By.xpath("//textarea[@id='edu']"));
		WebElement sel_job1 = driver.findElement(By.xpath("//select[@id='job1']"));
		WebElement sel_job2 = driver.findElement(By.xpath("//select[@id='job2']"));
		WebElement sel_job3 = driver.findElement(By.xpath("//select[@id='job3']"));
		WebElement chk_development = driver.findElement(By.xpath("//input[@id='development']"));
		WebElement chk_disable = driver.findElement(By.xpath("//input[@id='check-disbaled']"));
		WebElement sli_slider01 = driver.findElement(By.xpath("//input[@id='slider-1']"));
		WebElement sli_slider02 = driver.findElement(By.xpath("//input[@id='slider-2']"));
		WebElement inp_password = driver.findElement(By.xpath("//input[@id='disable_password']"));
		WebElement rad_disable = driver.findElement(By.xpath("//input[@id='radio-disabled']"));
		WebElement area_bio = driver.findElement(By.xpath("//textarea[@id='bio']"));

		boolean boo_email = inp_email.isEnabled();
		boolean boo_under18 = rad_under18.isEnabled();
		boolean boo_edu = area_edu.isEnabled();
		boolean boo_job1 = sel_job1.isEnabled();
		boolean boo_job2 = sel_job2.isEnabled();
		boolean boo_development = chk_development.isEnabled();
		boolean boo_slider01 = sli_slider01.isEnabled();
		boolean enable_element[] = { boo_email, boo_under18, boo_edu, boo_job1, boo_job2, boo_development,
				boo_slider01 };

//		Step 1: Check enable element
		for (boolean element : enable_element) {
			assertTrue(element);
		}

//		Step 2: Check disable element
		assertFalse(inp_password.isEnabled() && rad_disable.isEnabled() && area_bio.isEnabled() && sel_job3.isEnabled()
				&& chk_disable.isEnabled() && sli_slider02.isEnabled());

	}

	@Test
	private void TC03_CheckSelected() {
		WebElement rad_under18 = driver.findElement(By.xpath("//input[@id='under_18']"));
		WebElement chk_java = driver.findElement(By.xpath("//input[@id='java']"));
		
//		Step 1 : Click/check
		rad_under18.click();
		chk_java.click();
//		Step 2		
		if(rad_under18.isSelected()) {
			System.out.println("Radio under 18 is selected");
			
		}

		if(chk_java.isSelected()) {
			System.out.println("Checkbox Java is selected");
			
		}
//		Step 3
		chk_java.click();
		
//		Step 4
		if(!chk_java.isSelected()) {
			System.out.println("Checkbox Java is not selected");
			
		}
	}
	
	@Test
	public void TC04_Register() {
//		Step 1: Navigate to mailchimp
		driver.get("https://login.mailchimp.com/signup/");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		WebElement inp_email= driver.findElement(By.xpath("//input[@id='email']"));
		WebElement inp_username = driver.findElement(By.xpath("//input[@id='new_username']"));
		WebElement inp_password = driver.findElement(By.xpath("//input[@id='new_password']"));
		
		WebElement li_messageLower = driver.findElement(By.xpath("//span[text()='One lowercase character']//parent::li"));
		WebElement li_messageUper = driver.findElement(By.xpath("//span[text()='One uppercase character']//parent::li"));
		WebElement li_messageNumber = driver.findElement(By.xpath("//span[text()='One number']//parent::li"));
		WebElement li_messageSpecial = driver.findElement(By.xpath("//span[text()='One special character']//parent::li"));
		WebElement li_message8Digit = driver.findElement(By.xpath("//span[text()='8 characters minimum']//parent::li"));
		
//		Step 2: Fill valid email
		String validEmail = "example@email.com";
		inp_email.sendKeys(validEmail);
		
//		Step 3: Type each valid key
//		#1 One number
		inp_password.sendKeys("1");
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		assertTrue(li_messageNumber.getAttribute("class").contains("number-char completed"),"Fail #1" + li_messageNumber.getAttribute("class"));
		inp_password.clear();
		
//		#2 One Lower character
		inp_password.sendKeys("a");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		assertTrue(li_messageLower.getAttribute("class").contains("lowercase-char completed"),"Fail #2"+ li_messageNumber.getAttribute("class"));
		inp_password.clear();
		
//		#3 One Upper character
		inp_password.sendKeys("B");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		assertTrue(li_messageUper.getAttribute("class").contains("uppercase-char completed"),"Fail #3"+ li_messageNumber.getAttribute("class"));
		inp_password.clear();
		
//		#4 One Special character
		inp_password.sendKeys("@");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		assertTrue(li_messageSpecial.getAttribute("class").contains("special-char completed"),"Fail #4"+ li_messageNumber.getAttribute("class"));
		inp_password.clear();
		
//		#5 More than 8 digits
		inp_password.sendKeys("1234567890");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		assertTrue(li_message8Digit.getAttribute("class").contains("8-char completed"),"Fail #5"+ li_messageNumber.getAttribute("class"));
		inp_password.clear();
		
		
	}

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver.exe");
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
