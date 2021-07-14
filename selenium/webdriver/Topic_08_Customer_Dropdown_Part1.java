package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Customer_Dropdown_Part1 {
	WebDriver driver; // library , component cua selenium
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

		explicitWait = new WebDriverWait(driver, 15);

		// ép kiểu tường minh
		jsExecutor = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

		String parentXpath = "//span[@id='number-button']";
		String childXpath = "//ul[@id='number-menu']//div";
		String expected = "19";
		selectItemInCustomDropdown(parentXpath, childXpath, expected);
		// verify
		Assert.assertEquals(driver
				.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text']")).getText(),
				expected);
	}

	@Test
	public void TC_02_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

		String parentXpath = "//i[@class='dropdown icon']";
		String childXpath = "//span[@class='text']";
		String expected = "Justen Kitsune";

		selectItemInCustomDropdown(parentXpath, childXpath, expected);
		// verify
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), expected);
	}

	@Test
	public void TC_03_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");

		String parentXpath = "//li[@class='dropdown-toggle']";
		String childXpath = "//ul[@class='dropdown-menu']//a";
		String expected = "Second Option";

		selectItemInCustomDropdown(parentXpath, childXpath, expected);
		// verify
		Assert.assertEquals(driver.findElement(By.xpath(parentXpath)).getText(), expected);

	}

	@Test
	public void TC_04_Angular() {

		driver.get("https://valor-software.com/ng2-select/");

		selectItemInCustomDropdown("//tab[@heading='Single']//i[@class='caret pull-right']",
				"//tab[@heading='Single']//a[@class='dropdown-item']/div", "Amsterdam");
		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(By.xpath(
				"//h3[text()='Select a single city']/following-sibling::ng-select//span[contains(@class,'ui-select-allow-clear')]"))
				.getText(), "Amsterdam");

	}

	@Test
	public void TC_04_Editable_Dropdown_01() {

		driver.get("https://valor-software.com/ng2-select/");

		enterAndselectItemInCustomDropdown("//tab[@heading='Single']//i[@class='caret pull-right']",
				"//tab[@heading='Single']//input", "//tab[@heading='Single']//a[@class='dropdown-item']/div",
				"Stockholm");
		sleepInSecond(3);
		enterAndselectItemInCustomDropdown("//tab[@heading='Single']//i[@class='caret pull-right']",
				"//tab[@heading='Single']//input", "//tab[@heading='Single']//a[@class='dropdown-item']/div",
				"Amsterdam");
	}

	@Test
	public void TC_04_Editable_Dropdown_02() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

		enterAndTabItemInCustomDropdown("//input[@class='search']", "//div[@role='listbox']//span", "Algeria");
		sleepInSecond(2);
		enterAndTabItemInCustomDropdown("//input[@class='search']", "//div[@role='listbox']//span", "Afghanistan");
	}

	public void selectItemInCustomDropdown(String parentXpath, String childXpath, String expectedText) {
		// Click vào element để xổ tất cả các items ra
		driver.findElement(By.xpath(parentXpath)).click();
		sleepInSecond(1);

		// Chờ cho tất cả các item được load ra thành công
		List<WebElement> allItems = explicitWait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
		System.out.println("so items: " + allItems.size());
		// Tìm cái item cần chọn: duyệt qua từng item, get text của item đó ra check
		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedText)) {
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);
				item.click();
				break;
			}
		}

	}

	public void enterAndselectItemInCustomDropdown(String parentXpath, String textboxXpath, String childXpath,
			String expectedText) {
		driver.findElement(By.xpath(parentXpath)).click();
		sleepInSecond(1);

		driver.findElement(By.xpath(textboxXpath)).sendKeys(expectedText);
		sleepInSecond(1);
		// Chờ cho tất cả các item được load ra thành công
		List<WebElement> allItems = explicitWait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
		System.out.println("so items: " + allItems.size());
		// Tìm cái item cần chọn: duyệt qua từng item, get text của item đó ra check
		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedText)) {
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);
				item.click();
				break;
			}
		}

	}

	public void enterAndTabItemInCustomDropdown(String textboxXpath, String childXpath, String expectedText) {

		driver.findElement(By.xpath(textboxXpath)).sendKeys(expectedText);
		sleepInSecond(1);
		driver.findElement(By.xpath(textboxXpath)).sendKeys(Keys.TAB);
		// Chờ cho tất cả các item được load ra thành công
		List<WebElement> allItems = explicitWait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
		System.out.println("so items: " + allItems.size());
		// Tìm cái item cần chọn: duyệt qua từng item, get text của item đó ra check
		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedText)) {
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);
				item.click();
				break;
			}
		}

	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}