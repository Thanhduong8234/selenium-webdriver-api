package homework;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

public class Topic_07_TextBoxAreaBox {

	WebDriver driver;
	WebDriverWait wait;
	String projectPath = System.getProperty("user.dir");
	Random rand = new Random();
	int random = rand.nextInt();

	String username = "mngr427333";
	String password = "ajEqyga";
	String customerName = "customername";
	String gender = "male";
	String dateOfBirth = "06161996";
	String address = "address";
	String city = "danang";
	String state = "mientrung";
	String pin = "123456";
	String phone = "0123456789";
	String email = random + "@gmail.com";
	String customerPassword = "123456";

	@Test
	public void TC01_ResolveTextboxTextArea() {
		driver.get("http://demo.guru99.com/v4");
		WebElement inp_userid = driver.findElement(By.xpath("//input[@name='uid']"));
		WebElement inp_pass = driver.findElement(By.xpath("//input[@name='password']"));
		WebElement btn_login = driver.findElement(By.xpath("//input[@name='btnLogin']"));

		inp_userid.sendKeys(username);
		inp_pass.sendKeys(password);
		btn_login.click();

		WebElement li_newcustomer = driver.findElement(By.xpath("//a[text()='New Customer']"));
		li_newcustomer.click();
		WebElement inp_name = driver.findElement(By.name("name"));
		WebElement chk_rad1 = driver.findElement(By.name("rad1"));
		WebElement inp_dob = driver.findElement(By.name("dob"));
		WebElement inp_addr = driver.findElement(By.name("addr"));
		WebElement inp_city = driver.findElement(By.name("city"));
		WebElement inp_state = driver.findElement(By.name("state"));
		WebElement inp_pin = driver.findElement(By.name("pinno"));
		WebElement inp_phone = driver.findElement(By.name("telephoneno"));
		WebElement inp_email = driver.findElement(By.name("emailid"));
		WebElement inp_customerPassword = driver.findElement(By.name("password"));
		WebElement btn_submit = driver.findElement(By.name("sub"));

		inp_name.sendKeys(customerName);
		chk_rad1.click();
		inp_dob.sendKeys(dateOfBirth);
		inp_addr.sendKeys(address);
		inp_city.sendKeys(city);
		inp_state.sendKeys(state);
		inp_pin.sendKeys(pin);
		inp_phone.sendKeys(phone);
		inp_email.sendKeys(email);
		inp_customerPassword.sendKeys(customerPassword);
		btn_submit.click();

		WebElement td_customerid = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td"));

		String customerid = td_customerid.getText();

		WebElement lnk_editcustomer = driver.findElement(By.xpath("//a[text()='Edit Customer']"));
		lnk_editcustomer.click();

		WebElement inp_customerid = driver.findElement(By.name("cusid"));
		WebElement btn_edtsumit = driver.findElement(By.name("AccSubmit"));
		inp_customerid.sendKeys(customerid);
		btn_edtsumit.click();

		WebElement inp_gender = driver.findElement(By.name("gender"));
		inp_name = driver.findElement(By.name("name"));
		assertTrue(
				customerName.equals(inp_name.getAttribute("value")) && "male".equals(inp_gender.getAttribute("value")));

	}

	@Test
	public void TC02_ResolveTextboxTextArea() {
		driver.get("https://opensource-demo.orangehrmlive.com/");

		WebElement inp_username = driver.findElement(By.id("txtUsername"));
		WebElement inp_password = driver.findElement(By.id("txtPassword"));
		WebElement btn_login = driver.findElement(By.id("btnLogin"));

		inp_username.sendKeys("Admin");
		inp_password.sendKeys("admin123");
		btn_login.click();

		WebElement lnk_PIM = driver.findElement(By.id("menu_pim_viewPimModule"));
		WebElement lnk_addemployee = driver.findElement(By.id("menu_pim_addEmployee"));
		Actions actions = new Actions(driver);
		actions.moveToElement(lnk_PIM).perform();
		actions.moveToElement(lnk_addemployee).click().perform();

		WebElement inp_fname = driver.findElement(By.id("firstName"));
		WebElement inp_lname = driver.findElement(By.id("lastName"));
		WebElement inp_eid = driver.findElement(By.id("employeeId"));
		WebElement btn_save = driver.findElement(By.id("btnSave"));

		String fname = "fisrt Name";
		String lname = "last Name";
		String eid = inp_eid.getAttribute("value");
		System.out.println(eid);

		inp_fname.sendKeys(fname);
		inp_lname.sendKeys(lname);
		btn_save.click();

		WebElement inp_empfname = driver.findElement(By.id("personal_txtEmpFirstName"));
		WebElement inp_emplname = driver.findElement(By.id("personal_txtEmpLastName"));
		WebElement inp_empid = driver.findElement(By.id("personal_txtEmployeeId"));
		WebElement btn_edit = driver.findElement(By.id("btnSave"));

		assertTrue(inp_empfname.getAttribute("value").equals(fname));
		assertTrue(inp_emplname.getAttribute("value").equals(lname));
		assertTrue(inp_empid.getAttribute("value").equals(eid));

		btn_edit.click();
		String newfname = "Steven";
		String newlname = "Gerrard";

		assertEquals(inp_empfname.isEnabled(), inp_emplname.isEnabled());

		inp_empfname.clear();
		inp_emplname.clear();
		inp_empfname.sendKeys(newfname);
		inp_emplname.sendKeys(newlname);
		btn_edit.click();

		inp_empfname = driver.findElement(By.id("personal_txtEmpFirstName"));
		inp_emplname = driver.findElement(By.id("personal_txtEmpLastName"));
		inp_empid = driver.findElement(By.id("personal_txtEmployeeId"));

		assertTrue(inp_empfname.getAttribute("value").equals(newfname)
				&& inp_emplname.getAttribute("value").equals(newlname));
		assertFalse(inp_empfname.isEnabled());
		assertFalse(inp_emplname.isEnabled());
		assertFalse(inp_empid.isEnabled());

		WebElement lnk_immigration = driver.findElement(By.xpath("//a[text()='Immigration']"));

		lnk_immigration.click();
		WebElement btn_add = driver.findElement(By.id("btnAdd"));

		btn_add.click();
		String numbers = "774703475";
		String comment = "79 Madeira Way\n" + "Madeira Beach\n" + "FL 33708 USA";
		WebElement inp_number = driver.findElement(By.id("immigration_number"));
		WebElement area_comment = driver.findElement(By.id("immigration_comments"));

		inp_number.sendKeys(numbers);
		area_comment.sendKeys(comment);
		WebElement btn_imsave = driver.findElement(By.id("btnSave"));
		btn_imsave.click();

		WebElement lnk_passport = driver.findElement(By.linkText("Passport"));
		lnk_passport.click();

		inp_number = driver.findElement(By.id("immigration_number"));
		area_comment = driver.findElement(By.id("immigration_comments"));

		assertEquals(numbers, inp_number.getAttribute("value"));
		assertEquals(comment, area_comment.getAttribute("value"));

	}

	@Test
	public void TC03_HTMLDropdown() {
		driver.get("https://demo.nopcommerce.com/register");

		WebElement lnk_register = driver.findElement(By.linkText("Register"));
		lnk_register.click();

		WebElement inp_fname = driver.findElement(By.id("FirstName"));
		WebElement inp_lname = driver.findElement(By.id("LastName"));
		WebElement inp_email = driver.findElement(By.id("Email"));
		WebElement inp_password = driver.findElement(By.id("Password"));
		WebElement inp_cfpassword = driver.findElement(By.id("ConfirmPassword"));
		WebElement btn_register = driver.findElement(By.id("register-button"));

		Select drp_day = new Select(driver.findElement(By.name("DateOfBirthDay")));
		Select drp_month = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		Select drp_year = new Select(driver.findElement(By.name("DateOfBirthYear")));

		String fname = "Haley";
		String lname = "Queen";
		String pass = "12345678";
		String day = "1";
		String month = "May";
		String year = "1980";

		inp_fname.sendKeys(fname);
		inp_lname.sendKeys(lname);
		inp_email.sendKeys(email);
		inp_password.sendKeys(pass);
		inp_cfpassword.sendKeys(pass);

		drp_day.selectByVisibleText(day);
		assertEquals(32, drp_day.getOptions().size());
		drp_month.selectByVisibleText(month);
		assertEquals(13, drp_month.getOptions().size());
		drp_year.selectByVisibleText(year);
		assertEquals(112, drp_year.getOptions().size());

		btn_register.click();

		WebElement lb_registrationComplete = driver.findElement(By.className("result"));
		assertEquals("Your registration completed", lb_registrationComplete.getText());

		WebElement lnk_myaccount = driver.findElement(By.linkText("My account"));

		lnk_myaccount.click();

		inp_fname = driver.findElement(By.id("FirstName"));
		inp_lname = driver.findElement(By.id("LastName"));
		inp_email = driver.findElement(By.id("Email"));
		drp_day = new Select(driver.findElement(By.name("DateOfBirthDay")));
		drp_month = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		drp_year = new Select(driver.findElement(By.name("DateOfBirthYear")));

		assertEquals(fname, inp_fname.getAttribute("value"));
		assertEquals(lname, inp_lname.getAttribute("value"));
		assertEquals(email, inp_email.getAttribute("value"));
		assertEquals(day, drp_day.getFirstSelectedOption().getText());
		assertEquals(month, drp_month.getFirstSelectedOption().getText());
		assertEquals(year, drp_year.getFirstSelectedOption().getText());

	}

	@Test
	public void TC04_HTMLDropdown2() {

		driver.get("https://www.rode.com/wheretobuy");

		Select drpSelectCountry = new Select(driver.findElement(By.id("country")));

		assertFalse(drpSelectCountry.isMultiple());

		drpSelectCountry.selectByVisibleText("Vietnam");

		assertEquals("Vietnam", drpSelectCountry.getFirstSelectedOption().getText());

		wait = new WebDriverWait(driver, 10);

		wait.until(ExpectedConditions
				.visibilityOfAllElements(driver.findElements(By.cssSelector("[class='align-self-stretch']"))));

		List<WebElement> storesList = driver.findElements(By.cssSelector("div[class='align-self-stretch'] h4"));
		assertEquals(39, storesList.size());

		for (WebElement store : storesList) {
			System.out.println(store.getText());
		}

	}

	@Test
	public void TC05_HTMLDropdown3() {

		driver.get("https://applitools.com/automating-tests-chrome-devtools-recorder-webinar/");
		WebElement inp_wemail = driver.findElement(By.cssSelector("#Email"));
		WebElement inp_fname = driver.findElement(By.cssSelector("#FirstName"));
		WebElement inp_lname = driver.findElement(By.cssSelector("#LastName"));
		WebElement inp_companyname = driver.findElement(By.cssSelector("#Company"));

		Select drp_jobSelect = new Select(driver.findElement(By.cssSelector("#Person_Role__c")));
		Select drp_framWorkSelect = new Select(driver.findElement(By.cssSelector("#Test_Framework__c")));
		Select drp_countrySelect = new Select(driver.findElement(By.cssSelector("#Self_Report_Country__c")));

		inp_wemail.sendKeys("fakeemail");
		inp_fname.sendKeys("fakefname");
		inp_lname.sendKeys("fakelname");
		inp_companyname.sendKeys("fakecompany");

		drp_jobSelect.selectByIndex(5);
		drp_countrySelect.selectByIndex(5);
		drp_framWorkSelect.selectByIndex(5);

	}

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();

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
