package webdriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Wait4_Static {
	WebDriver driver; // library , component cua selenium
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	
	}

	@Test
	public void TC_01_Less() {
		
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

		driver.findElement(By.xpath("//button")).click();
		// Ít hơn thời gian để 1 step tiếp theo được ready
		// Thiếu timeout -> Fail
		sleepInSecond(1);
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']")).isDisplayed());

		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']")).getText(), "Hello World!");
	}

	@Test
	public void TC_02_Enough() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

		driver.findElement(By.xpath("//button")).click();
		// đủ thời gian => passed
		sleepInSecond(6);
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']")).isDisplayed());

		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']")).getText(), "Hello World!");
	}

	@Test
	public void TC_03_Redundant() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

		driver.findElement(By.xpath("//button")).click();
		// dư thời gian chờ => Passed
		sleepInSecond(10);
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']")).isDisplayed());

		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']")).getText(), "Hello World!");
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