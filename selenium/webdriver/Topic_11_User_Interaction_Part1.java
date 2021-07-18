package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_User_Interaction_Part1 {
	WebDriver driver; // library , component cua selenium
	Actions action;

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		action = new Actions(driver);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Hover() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");

		action.moveToElement(driver.findElement(By.id("age"))).perform();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText(),
				"We ask for your age only for statistical purposes.");

		driver.get("https://www.myntra.com/");
		action.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navLink']/a[text()='Kids']")))
				.perform();
		sleepInSecond(2);
		driver.findElement(By.xpath("//a[text()='Home & Bath']")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb']")).getText(),
				"Kids Home Bath");
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.myntra.com/kids-home-bath");
		driver.get("https://vietnam.atalink.com/");

		action.moveToElement(driver.findElement(By.xpath(
				"//div[text()='NGÀNH HÀNG NỔI BẬT']/following-sibling::div//div[text()='Hệ Thống CNTT & Thiết Bị Văn Phòng']")))
				.perform();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='sc-pIgJL gStWW']//div[text()='Laptop & PC']"))
				.isDisplayed());

	}

	@Test
	public void TC_02_Click_And_Hold() {
		driver.get("https://automationfc.github.io/jquery-selectable/");

		List<WebElement> numberSelecions = driver.findElements(By.cssSelector("#selectable>li"));

		System.out.println(numberSelecions.size());

		// click and hold
		action.clickAndHold(numberSelecions.get(0)).moveToElement(numberSelecions.get(3)).release().perform();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElements(By.xpath("//li[contains(@class, 'ui-selected')]")).size(), 4);
	}

	@Test
	public void TC_03_Click_Ctrl() {
		driver.get("https://automationfc.github.io/jquery-selectable/");

		List<WebElement> numberSelecions = driver.findElements(By.cssSelector("#selectable>li"));

		// Nhan phiom Ctrl xuong roi chon cac element > nha phim Ctrl ra
		action.keyDown(Keys.CONTROL).perform();
		action.click(numberSelecions.get(0));
		action.click(numberSelecions.get(2));
		action.click(numberSelecions.get(5));
		action.click(numberSelecions.get(10));
		action.keyUp(Keys.CONTROL).perform();

		sleepInSecond(2);
		Assert.assertEquals(driver.findElements(By.xpath("//li[contains(@class, 'ui-selected')]")).size(), 4);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
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