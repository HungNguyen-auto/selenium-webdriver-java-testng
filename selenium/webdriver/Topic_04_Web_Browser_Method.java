package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Web_Browser_Method {
	WebDriver driver; // library , component cua selenium

	@BeforeClass
	public void beforeClass() {
		// System.setProperty("webdriver.firefox.driver",
		// ".\\browserDrivers\\geckodriver.exe"); //driver cua trinh duyet, executeable
		// file, cau noi giua selenium webdriver va browser
		// driver = new FirefoxDriver();

		driver = new FirefoxDriver();

		// chờ cho element được tìm thấy trong xx thời gian
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//back về page trước đó
		driver.navigate().back();
		
		driver.quit();
		
		driver.close();
		
		driver.switchTo().alert();
		
	}

	@Test
	public void TC_01_ID() {

	}

	@Test
	public void TC_02() {

	}

	@Test
	public void TC_03() {

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