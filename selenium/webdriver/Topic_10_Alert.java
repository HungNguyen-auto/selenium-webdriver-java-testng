package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Alert {
	WebDriver driver; // library , component cua selenium
	WebDriverWait explicitwait;
	Alert alert;

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		explicitwait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Accept_Alert_1() {
		driver.get("http://demo.guru99.com/v4/index.php");

		driver.findElement(By.name("btnLogin")).click();

		// wait for alert appears then switch to alert
		alert = explicitwait.until(ExpectedConditions.alertIsPresent());
		sleepInSecond(3);
		Assert.assertEquals(alert.getText(), "User or Password is not valid");

		// accept
		alert.accept();

	}

	@Test
	public void TC_01_Accept_Alert_2() {

		driver.get("https://automationfc.github.io/basic-form/");

		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		alert = explicitwait.until(ExpectedConditions.alertIsPresent());
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		alert.accept();

	}

	@Test
	public void TC_02_Confirm_Alert() {

		driver.get("https://automationfc.github.io/basic-form/");

		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		alert = explicitwait.until(ExpectedConditions.alertIsPresent());
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		alert.dismiss();

		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked: Cancel");
	}

	@Test
	public void TC_03_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/");
		String content = "I'm automation tester!";
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		alert = explicitwait.until(ExpectedConditions.alertIsPresent());
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		alert.sendKeys(content);
		alert.accept();

		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You entered: " + content);
	}

	@Test
	public void TC_04_Authen_Alert() {
		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");

		Assert.assertTrue(driver
				.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]"))
				.isDisplayed());

	}

	@Test
	public void TC_05_Authen_Alert() {
		driver.get("http://the-internet.herokuapp.com");
		String username = "admin";
		String password = "admin";

		String urlAuth = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		System.out.println(urlAuth);
		// http://the-internet.herokuapp.com/basic_auth
		passValueToUrl(urlAuth, username, password);

		Assert.assertTrue(driver
				.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]"))
				.isDisplayed());
	}

	public void passValueToUrl(String url, String username, String password) {
		String[] urlAuth = url.split("//");
		url = urlAuth[0] + "//" + username + ":" + password + "@" + urlAuth[1];

		driver.get(url);
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