package webdriver;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_XPath_Css_Part_II_Technical {
	
	WebDriver driver; // library , component cua selenium

	String fname = "henry";

	String lname = "nguyen";

	String pass = "1234aa";

	String uuid = UUID.randomUUID().toString().substring(0, 5);

	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		sleepInSecond(5);
		driver.manage().window().maximize();
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

	}

	@Test
	public void TC_01_Login_Empty_Email_Password() {

		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.id("pass")).sendKeys("");
		driver.findElement(By.name("send")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText(),
				"This is a required field.");

		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText(),
				"This is a required field.");
	}

	@Test
	public void TC_02_Invalid_Email() {
		driver.navigate().refresh();

		sleepInSecond(3);

		driver.findElement(By.id("email")).sendKeys("23213131@213123.213213");

		driver.findElement(By.name("send")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText(),
				"Please enter a valid email address. For example johndoe@domain.com.");

	}

	@Test
	public void TC_03_Invalid_Password() {
		driver.navigate().refresh();

		sleepInSecond(3);

		driver.findElement(By.id("pass")).sendKeys("123");

		driver.findElement(By.name("send")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText(),
				"Please enter 6 or more characters without leading or trailing spaces.");
	}

	@Test
	public void TC_04_Incorrect_Password() {
		driver.navigate().refresh();

		sleepInSecond(3);

		driver.findElement(By.id("email")).sendKeys("henry@gmail.com");

		driver.findElement(By.id("pass")).sendKeys("1234aaAA");

		driver.findElement(By.name("send")).click();

		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(),
				"Invalid login or password.");
	}

	@Test
	public void TC_05_Create_New_Account() {

		driver.navigate().refresh();

		sleepInSecond(3);

		driver.findElement(By.xpath("//a[@class='button']")).click();

		sleepInSecond(3);

		driver.findElement(By.id("firstname")).sendKeys(fname);

		driver.findElement(By.id("lastname")).sendKeys(lname);

		driver.findElement(By.id("email_address")).sendKeys(uuid + "@gmail.com");

		driver.findElement(By.id("password")).sendKeys(pass);

		driver.findElement(By.id("confirmation")).sendKeys(pass);

		driver.findElement(By.xpath("//button[@title='Register']")).click();

		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),
				"Thank you for registering with Main Website Store.");

		Assert.assertTrue(
				driver.findElement(By.xpath("//p[contains(text(),'" + fname + "') and not(@class='welcome-msg')]"))
						.isDisplayed());

		Assert.assertTrue(
				driver.findElement(By.xpath("//p[contains(text(),'" + lname + "') and not(@class='welcome-msg')]"))
						.isDisplayed());

		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(string(),'" + uuid + "')]")).isDisplayed());

		sleepInSecond(5);

		driver.findElement(By.xpath("//div[@class='skip-links']//span[text()='Account']")).click();

		driver.findElement(By.xpath("//a[@title='Log Out']")).click();

		sleepInSecond(10);

		Assert.assertEquals(driver.getTitle(), "Home page");
	}

	@Test
	public void TC_06_Login_New_Created_Account() {

		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		driver.findElement(By.id("email")).sendKeys(uuid + "@gmail.com");

		driver.findElement(By.id("pass")).sendKeys(pass);

		driver.findElement(By.name("send")).click();

		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='page-title']/h1")).getText(), "MY DASHBOARD");

		Assert.assertTrue(
				driver.findElement(By.xpath("//p[contains(text(),'" + fname + "') and not(@class='welcome-msg')]"))
						.isDisplayed());

		Assert.assertTrue(
				driver.findElement(By.xpath("//p[contains(text(),'" + lname + "') and not(@class='welcome-msg')]"))
						.isDisplayed());

		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(.,'" + uuid + "')]")).isDisplayed());

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