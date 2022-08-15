package homework;

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
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

public class Topic_07_CustomDropdown {
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

		jsExecutor = (JavascriptExecutor) driver;

		actions = new Actions(driver);

		random = new Random();

		thread = new Thread();

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
// Test Case

	@Test
	public void TC01_CustomDropdownJQuery() {

		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");

		By numberBy = By.cssSelector("#number-button");
		By speedBy = By.cssSelector("#speed-button");
		By salutationBy = By.cssSelector("#salutation-button");
		By numberList = By.cssSelector("#number-menu div");
		By speedList = By.cssSelector("#speed-menu div");
		By salutationList = By.cssSelector("#salutation-menu div");

		assertTrue(selectDropdown(speedBy, speedList, "Slower"));
		assertTrue(selectDropdown(numberBy, numberList, "4"));
		assertTrue(selectDropdown(salutationBy, salutationList, "Mr."));
		assertTrue(selectDropdown(speedBy, speedList, "Slow"));
		assertTrue(selectDropdown(numberBy, numberList, "9"));
		assertTrue(selectDropdown(salutationBy, salutationList, "Mrs."));
		assertTrue(selectDropdown(speedBy, speedList, "Medium"));
		assertTrue(selectDropdown(numberBy, numberList, "13"));
		assertTrue(selectDropdown(salutationBy, salutationList, "Dr."));
		assertTrue(selectDropdown(speedBy, speedList, "Fast"));
		assertTrue(selectDropdown(numberBy, numberList, "17"));
		assertTrue(selectDropdown(salutationBy, salutationList, "Prof."));
		assertTrue(selectDropdown(speedBy, speedList, "Faster"));
		assertTrue(selectDropdown(numberBy, numberList, "19"));
		assertTrue(selectDropdown(salutationBy, salutationList, "Other"));

	}

	@Test
	public void TC02_CustomdropdownJQuery02() {
		driver.get("https://www.honda.com.vn/o-to/du-toan-chi-phi");

		By selectCarBy = By.cssSelector("div.dropdown");
		By optionCarBy = By.cssSelector("div[class='dropdown-menu show'] a");

		WebElement btnX = driver.findElement(By.cssSelector("i[class='fa fa-times x-cookie']"));

		btnX.click();

		Select provinceSelect = new Select(driver.findElement(By.cssSelector("#province")));
		Select stateSelect = new Select(driver.findElement(By.cssSelector("#registration_fee")));

		scrollToElement("div.carousel-item");

		sleep(3000);
		assertTrue(selectDropdown(selectCarBy, optionCarBy,
				"HR-V RS (Đỏ cá tính/ trắng ngọc quý phái/ trắng bạc thời trang)"));

		scrollToElement("div.container");
		sleep(3000);
		provinceSelect.selectByVisibleText("Thừa Thiên Huế");
		assertEquals("Thừa Thiên Huế", provinceSelect.getFirstSelectedOption().getText());

		stateSelect.selectByVisibleText("Khu vực II");
		assertEquals("Khu vực II", stateSelect.getFirstSelectedOption().getText());

	}

	@Test
	public void TC03_CustomdropdownReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

		By selectFriend = By.cssSelector("div[role='listbox']");
		By optionFriend = By.cssSelector("div.item span");

		selectDropdown(selectFriend, optionFriend, "Jenny Hess");
		selectDropdown(selectFriend, optionFriend, "Justen Kitsune");
	}

	@Test
	public void TC04_CustomdropdownVueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");

		By selectFriend = By.cssSelector("div.btn-group");
		By optionFriend = By.cssSelector("ul.dropdown-menu>li>a");

		selectDropdown(selectFriend, optionFriend, "First Option");
		selectDropdown(selectFriend, optionFriend, "Second Option");
		selectDropdown(selectFriend, optionFriend, "Third Option");
	}

	@Test
	public void TC05_CustomdropdownVueJS2() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

		By selectFriend = By.cssSelector("div[role='combobox']");
		By optionFriend = By.cssSelector("div[role='listbox']>div>span");

		selectDropdown(selectFriend, optionFriend, "Aland Islands");
		selectDropdown(selectFriend, optionFriend, "Australia");
		selectDropdown(selectFriend, optionFriend, "Benin");
	}

	@Test
	public void TC06_OptionalAngular() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");

		By by_selectInjection = By.xpath("//label[contains(text(),' Đăng kí mũi tiêm thứ ')]/parent::div//ng-select");
		By by_optionInjection = By.cssSelector("[class='ng-dropdown-panel-items scroll-host'] div.ng-option");
		By by_selectGender = By.xpath("//label[contains(text(),'Giới tính')]/parent::div//ng-select");
		By by_optionGender = By.cssSelector("[class='ng-dropdown-panel-items scroll-host'] div.ng-option");
		By by_selectProvince = By.xpath("//label[contains(text(),'Tỉnh/Thành phố')]/parent::div//ng-select");
		By by_optionProvince = By.cssSelector("[class='ng-dropdown-panel-items scroll-host'] div.ng-option");
		By by_selectDistrict = By.xpath("//label[contains(text(),'Quận/Huyện')]/parent::div//ng-select");
		By by_optionDistrict = By.cssSelector("[class='ng-dropdown-panel-items scroll-host'] div.ng-option");
		By by_selectWard = By.xpath("//label[contains(text(),'Xã/Phường')]/parent::div//ng-select");
		By by_optionWard = By.cssSelector("[class='ng-dropdown-panel-items scroll-host'] div.ng-option");
		By by_selectEthnic = By.xpath("//label[contains(text(),'Dân tộc')]/parent::div//ng-select");
		By by_optionEthnic = By.cssSelector("[class='ng-dropdown-panel-items scroll-host'] div.ng-option");
		By by_selectShift = By.xpath("//label[contains(text(),'Buổi tiêm mong muốn')]/parent::div//ng-select");
		By by_optionShift = By.cssSelector("[class='ng-dropdown-panel-items scroll-host'] div.ng-option");

		sleep(3000);

		selectDropdown(by_selectInjection, by_optionInjection, "Mũi tiêm tiếp theo");
		fillInput("Họ và tên", "Đào Thị Kim Oanh");
		fillInput("Ngày sinh", "16061998");
		selectDropdown(by_selectGender, by_optionGender, "Nữ");
		fillInput("Số điện thoại", "0327609231");
		fillInput("Email", "a0327609231@gmail.com");
		fillInput("CCCD/Mã định danh công dân", "9999999999");
		fillInput("Số thẻ BHYT", "9999999999");
		fillInput("Nghề nghiệp", "Manual Tester");
		fillInput("Đơn vị công tác", "OpenWeb Tech");
		fillInput("Địa chỉ hiện tại", "Đà Nẽng");
		selectDropdown(by_selectProvince, by_optionProvince, "Thành phố Đà Nẵng");
		selectDropdown(by_selectDistrict, by_optionDistrict, "Quận Ngũ Hành Sơn");
		selectDropdown(by_selectWard, by_optionWard, "Phường Mỹ An");
		selectDropdown(by_selectEthnic, by_optionEthnic, "Kinh");

		scrollToElement("[formcontrolname='guardianFullName']");
		sleep(3000);
//		fillInput("Họ và tên người giám hộ", "Anh Thành");

//		fillInput("Số điện thoại người giám hộ", "0919815423");
		fillInput("Ngày muốn được tiêm (dự kiến)", "30/10/2022");
		selectDropdown(by_selectShift, by_optionShift, "Cả ngày");

	}

	@Test
	public void TC07_MultilpleSelect() {
		driver.get("https://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");

		WebElement monthMSelect = driver.findElement(By.xpath("//hr/preceding-sibling::div//button"));
		WebElement monthMSelected = driver.findElement(By.xpath("//hr/preceding-sibling::div//button/span"));
		List<WebElement> monthOptions = driver
				.findElements(By.xpath("//hr/preceding-sibling::div//button//following-sibling::div//input"));

		monthMSelect.click();

		String string = "1,2,3";
		for (String item : multiSelect(monthOptions, string, monthMSelected)) {
			System.out.println(item);
		}

	}

// Function
	public ArrayList<String> multiSelect(List<WebElement> selectList, String options, WebElement button) {
		String[] str = split(options);
		ArrayList<String> strContain = new ArrayList<String>();
		int i = 0;
		for (WebElement option : selectList) {
			System.out.println(option.getText());
			if (options.equals(option.getAttribute("data-name"))) {
				option.click();
				strContain = new ArrayList<String>();
				strContain.add(button.getText());
				break;
			} else if (i >= str.length) {
				strContain = new ArrayList<String>();
				strContain.add(button.getText());
				break;
			} else if (option.getText().equals(str[i]) || option.getAttribute("value").equals(str[i])) {
				option.click();
				i++;
			}
			if (i <= 3 && "selected".equals(option.getAttribute("class"))) {
				strContain.add(option.getText());
			}
		}

		return strContain;
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

	public String fillInput(String fieldName, String value) {
		String string = String.format("//label[contains(text(),'%s')]/parent::div//input", fieldName);

		WebElement fiedNameElement = driver.findElement(By.xpath(string));
		actions.moveToElement(fiedNameElement).perform();
		fiedNameElement.sendKeys(value);
		return fiedNameElement.getText();
	}

	public void scrollToElement(String cssLocator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.cssSelector(cssLocator)));
	}

	public boolean selectDropdown(By dropdown, By List, String selectValue) {

		WebElement drpSelect = driver.findElement(dropdown);
		drpSelect.click();

		List<WebElement> options = driver.findElements(List);

		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(dropdown));

		for (WebElement option : options) {
			System.out.println(option.getText());
		}

		for (WebElement option : options) {
			if (!option.getText().equals(selectValue)) {
				actions.moveToElement(option).perform();
				actions.moveToElement(option).perform();
				actions.sendKeys(Keys.DOWN);
			} else {
				actions.moveToElement(option).perform();
				option.click();
				return selectValue.contains(driver.findElement(dropdown).getText());
			}

		}

		return false;
	}

}
