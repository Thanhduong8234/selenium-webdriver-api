package homework;

import static org.testng.Assert.assertTrue;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_10_UserInteractions {

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

//	@AfterTest
	public void afterTest() {

	}

//	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

// Test

//	@Test
	public void TC01_HoverToElement() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		WebElement age = driver.findElement(By.cssSelector("#age"));

		actions.moveToElement(age).perform();

		WebElement tootlTip = driver.findElement(By.cssSelector(".ui-tooltip-content"));

		assertTrue(tootlTip.isDisplayed());

	}

//	@Test
	public void TC02_HoverToElement() {
		driver.get("http://www.myntra.com/");
		WebElement nav_Kids = driver.findElement(By.xpath("//div[@class='desktop-navLink']//*[text()='Kids']"));

		actions.moveToElement(nav_Kids).perform();

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
				By.xpath("//div[@class='desktop-navLink']//*[text()='Home & Bath']")));

		WebElement kidsMenu_HomenBath = driver
				.findElement(By.xpath("//div[@class='desktop-navLink']//*[text()='Home & Bath']"));

		kidsMenu_HomenBath.click();

		assertTrue(driver.getTitle().contains("Kids Home Bath"));

	}

//	@Test
	public void TC03_HoverToElement() {
		driver.manage().window().maximize();
		driver.get("https://www.fahasa.com/");
		driver.switchTo().frame(driver.findElement(By.cssSelector(".st_preview_frame_modal")));
		driver.findElement(By.cssSelector("#NC_IMAGE1")).click();
		driver.switchTo().parentFrame();

		WebElement menu_Main = driver.findElement(By.cssSelector(".icon_menu"));

		actions.moveToElement(menu_Main).perform();

		WebElement menu_STN = driver.findElement(By.partialLinkText("Sách Trong Nước"));

		actions.moveToElement(menu_STN).perform();

		WebElement lnk_KNS = driver.findElement(By.partialLinkText("Kỹ Năng Sống"));

		lnk_KNS.click();

		assertTrue(driver.getTitle().contains("Kỹ năng sống"));
	}

	@Test
	private void TC04_05_ClickAndHold() {
		driver.get("https://automationfc.github.io/jquery-selectable/");

		By listNumber = By.xpath("//*[@id='selectable']/li");

		String selectNumbers = "1,12,7,5,3,9,11";

		List<WebElement> list = slectManyNumbers(listNumber, selectNumbers);

		assertTrue(isSlectedItems(list, selectNumbers));

	}

// Function
	public boolean isSlectedItems(List<WebElement> elements, String string) {
		int[] str = split(string);
		int i = 0;

		for (WebElement item : elements) {
			if (i >= str.length) {
				break;
			}
			if (item.getText().contains(String.valueOf(str[i]))
					&& !item.getAttribute("class").contains("ui-selected")) {
				return false;
			} else if (item.getAttribute("class").contains("ui-selected")) {
				System.out.println("Selected: " + item.getText());
				i++;
			}

		}
		return true;
	}

	public List<WebElement> slectManyNumbers(By by, String string) {
		List<WebElement> list = driver.findElements(by);
		int[] str = bubbleSort(split(string));
		int i = 0;

		for (WebElement item : list) {
			if (i >= str.length) {
				break;
			}
			actions.keyDown(Keys.CONTROL).perform();
			if (item.getText().contains(String.valueOf(str[i]))) {
				item.click();
				i++;
			}
			actions.keyUp(Keys.CONTROL).perform();

		}
		return driver.findElements(by);
	}

	public int[] split(String splitString) {

		String[] arrStrings = splitString.split(",");
		int[] integers = new int[arrStrings.length];
		int i;
		for (i = 0; i <= arrStrings.length-1; i++) {
			integers[i] = Integer.valueOf(arrStrings[i]);
		}		
		return integers;
	}
	//"1,12,7,5,3,9,11";
	public int[] bubbleSort(int arr[]) {
		int n = arr.length;
		int[] array = new int[n];
		for(int i = 0; i <= n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++)
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
			array[i] = arr[i];
			System.out.println(array[i]);
		}
		return array;
	}

	public void sleep(int second) {
		int milisecond = second * 1000;
		try {
			Thread.sleep(milisecond);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void scrollToElement(String cssLocator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.cssSelector(cssLocator)));
	}

}
