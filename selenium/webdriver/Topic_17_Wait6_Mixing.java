package webdriver;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Wait6_Mixing {
	WebDriver driver; // library , component cua selenium
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

	}

	// @Test
	public void TC_01_Element_Found() {

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 15);
		driver.get("https://www.facebook.com/");

		showDateTimeNow("Start explicit: ");
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[@id='email']")));
		showDateTimeNow("End explicit: ");

		showDateTimeNow("Start implicit: ");
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("henrynguyen@gmail.com");
		showDateTimeNow("End implicit: ");
	}

	// @Test
	public void TC_02_Not_Found_Implicit() {

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get("https://www.facebook.com/");

		showDateTimeNow("Start implicit: ");

		try {
			driver.findElement(By.xpath("//input[@id='email123']")).sendKeys("henrynguyen@gmail.com");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		showDateTimeNow("End implicit: ");
	}

	//@Test
	public void TC_03_Not_Found_Implicit_Explicit() {

		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 8);
		driver.get("https://www.facebook.com/");
		
		// FindElement trước rồi mới apply điều kiện
		// nên implicit sẽ ảnh hưởng vào các step dùng explicit
		showDateTimeNow("Start explicit: ");
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email123']")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		showDateTimeNow("End explicit: ");
		
	}

	@Test
	public void TC_04_Not_Found_Explicit_Param_By() {
		explicitWait = new WebDriverWait(driver, 5);
		driver.get("https://www.facebook.com/");
		
		// FindElement trước rồi mới apply điều kiện
		// nên implicit sẽ ảnh hưởng vào các step dùng explicit
		showDateTimeNow("Start explicit (By): ");
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email123']")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		showDateTimeNow("End explicit: (By)");
	}

	@Test
	public void TC_05_Not_Found_Explicit_Param_WebElement() {
		explicitWait = new WebDriverWait(driver, 5);
		driver.get("https://www.facebook.com/");
		
		// FindElement trước rồi mới apply điều kiện
		// nên implicit sẽ ảnh hưởng vào các step dùng explicit
		showDateTimeNow("Start explicit (WebElement): ");
		try {
			explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@id='email123']"))));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		showDateTimeNow("End explicit (WebElement): ");
	}

	public void showDateTimeNow(String status) {

		Date date = new Date();
		System.out.println("----------" + status + date.toString() + "-----------");

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