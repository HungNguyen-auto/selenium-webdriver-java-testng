package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class Topic_03_Selenium_Locator {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	}

	@Test
	public void TC_01_ID() {
		driver.findElement(By.id("send2")).click();

		// Verify blank email error
		Assert.assertTrue(driver.findElement(By.id("advice-required-entry-email")).isDisplayed());
	}

	@Test
	public void TC_02_Class() {
		driver.navigate().refresh();

		driver.findElement(By.className("validate-password")).sendKeys("1234aaAA");

	}

	@Test
	public void TC_03_Name() {
		driver.navigate().refresh();

		driver.findElement(By.name("send")).click();

		Assert.assertTrue(driver.findElement(By.id("advice-required-entry-email")).isDisplayed());
	}

	@Test
	public void TC_04_Tagname() {
		driver.navigate().refresh();
		// Hiển thị tất cả đường link tại màn hình sau đó getText ra
		List<WebElement> loginPageLinks = driver.findElements(By.tagName("a"));

		for (WebElement webElement : loginPageLinks) {
			System.out.println(webElement.getText());
		}
	}

	@Test
	public void TC_05_LinkText() {
		driver.navigate().refresh();

		driver.findElement(By.linkText("Forgot Your Password?")).click();

		Assert.assertTrue(driver.findElement(By.id("email_address")).isDisplayed());
	}

	@Test
	public void TC_06_PartialLinkText() {
		driver.findElement(By.partialLinkText("Back to")).click();

		Assert.assertTrue(driver.findElement(By.id("email")).isDisplayed());

	}

	@Test
	public void TC_07_Css() {
		driver.navigate().refresh();
		
		driver.findElement(By.cssSelector("#email")).sendKeys("nguyenhh2703@gmail.com");
		
		driver.findElement(By.cssSelector("#pass")).sendKeys("1234aaAA");
	}

	@Test
	public void TC_08_Xpath() {
		driver.navigate().refresh();
		
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("hungnh2703@gmail.com");
		
		driver.findElement(By.xpath("//label[contains(text(),'Password')]/following-sibling::div/input")).sendKeys("1234aa");
	}

	@AfterClass
	public void AfterClass() {
		//driver.quit();
	}
}
