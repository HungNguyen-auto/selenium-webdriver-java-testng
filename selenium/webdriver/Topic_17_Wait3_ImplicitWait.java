package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Wait3_ImplicitWait {
	WebDriver driver; // library , component cua selenium
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

	}

	// dont set implicitwait
	@Test
	public void TC_01_Implicit_Wait_0() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

		driver.findElement(By.xpath("//button")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']")).isDisplayed());

		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']")).getText(), "Hello World!");

	}

	// set implicitwait = 2
	@Test
	public void TC_02_Implicit_Wait_2() {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

		driver.findElement(By.xpath("//button")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']")).isDisplayed());

		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']")).getText(), "Hello World!");
	}

	// set implicitwait = 6
	@Test
	public void TC_03_Implicit_Wait_5() {
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

		driver.findElement(By.xpath("//button")).click();

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