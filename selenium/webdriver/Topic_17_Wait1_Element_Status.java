package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Wait1_Element_Status {
	WebDriver driver; // library , component cua selenium
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 15);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	// @Test
	public void TC_01_Visible_Displayed() {
		driver.get("https://www.facebook.com/");

		// Wait cho 1 element hiển thị
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']")));

		// Verify 1 element hiển thị
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='email']")).isDisplayed());

	}

	// @Test
	public void TC_02_Invisible_Undisplayed() {
		driver.get("https://www.facebook.com/");

		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Tạo tài khoản mới']")));
		driver.findElement(By.xpath("//a[text()='Tạo tài khoản mới']")).click();
		// Không có trên UI và có trong DOM
		explicitWait.until(
				ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));

		driver.findElement(By.xpath("//div[text()='Đăng ký']/parent::div/preceding-sibling::img")).click();
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Tạo tài khoản mới']")));

		// Không có trên UI và ko có trong DOM
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//form[@id='reg']]")));

	}

	// @Test
	public void TC_03_Presence() {
		driver.get("https://www.facebook.com/");

		// Hiển thị trên UI và có trong DOM
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='email']")));

		driver.findElement(By.xpath("//a[text()='Tạo tài khoản mới']")).click();
		// Không hiển thị trên UI và vẫn có có trong DOM
		explicitWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));

		driver.findElement(By.xpath("//div[text()='Đăng ký']/parent::div/preceding-sibling::img")).click();

	}

	//@Test
	public void TC_04_Staleness() {
		driver.get("https://www.facebook.com/");

		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Tạo tài khoản mới']")));
		driver.findElement(By.xpath("//a[text()='Tạo tài khoản mới']")).click();

		// Tìm form element để apply stalenessOf
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='reg']")));
		WebElement formRegister = driver.findElement(By.xpath("//form[@id='reg']"));

		
		driver.findElement(By.xpath("//div[text()='Đăng ký']/parent::div/preceding-sibling::img")).click();
		
		// Wait register form staleness (tại thời điểm này mình mong muốn nó ko còn trong DOM nữa)
		explicitWait.until(ExpectedConditions.stalenessOf(formRegister));

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