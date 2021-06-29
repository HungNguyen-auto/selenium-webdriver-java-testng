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

public class Topic_02_Xpath_Css {
	WebDriver driver; //library , component cua selenium    

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		//thời gian chờ cho element được tìm thấy trong 1 khoảng time
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		//mở website
		driver.get("https://www.nopcommerce.com/en/register?returnUrl=%2Fen%2Fdemo");
	}

	@Test
	public void TC_01() {
		//nhập giá trị vào username textbox
		//driver.findElement(By.)
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

}