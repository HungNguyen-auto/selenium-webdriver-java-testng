package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Popup_Dialog {
	WebDriver driver; // library , component cua selenium

	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Fixed_Popup() {
		driver.get("https://ngoaingu24h.vn/");
		
		driver.findElement(By.cssSelector("button.login_")).click();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.cssSelector("div#modal-login-v1>div")).isDisplayed());
		
		driver.findElement(By.cssSelector("input#account-input")).sendKeys("henry.nguyen");
		driver.findElement(By.cssSelector("input#password-input")).sendKeys("1234aa");
		driver.findElement(By.cssSelector("button.btn-login-v1")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("div#modal-login-v1 div.error-login-panel ")).getText(), "Tài khoản không tồn tại!");
		sleepInSecond(2);
		driver.findElement(By.cssSelector("div#modal-login-v1 button.close")).click();
		sleepInSecond(2);
		Assert.assertFalse(driver.findElement(By.cssSelector("div#modal-login-v1>div")).isDisplayed());
	}

	@Test
	public void TC_02_Random_In_DOM() {
		driver.get("https://testproject.io/");
		sleepInSecond(10);
		WebElement popup = driver.findElement(By.cssSelector("article.exit-popup"));
		if (popup.isDisplayed()) {
			driver.findElement(By.cssSelector("article.exit-popup>button")).click();
			System.out.println("There is a popup appears!");
			sleepInSecond(2);
		} else {
			System.out.println("Popup is not displayed");
		}
		
		driver.findElement(By.xpath("//input[@name='your-email']")).sendKeys("Selenium");
		driver.findElement(By.xpath("//input[@value='Create an Account']")).click();
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The e-mail address entered is invalid.']")).isDisplayed());
		
	}

	@Test
	public void TC_03_Random_Not_In_DOM() {
		driver.get("https://shopee.vn/");
		sleepInSecond(5);
		
		List<WebElement> popup = driver.findElements(By.cssSelector("div.shopee-popup img"));
		//nếu ko tìm thấy element sẽ trả về null list (size = 0) và ko đánh fail testcase
 		//WebElement popup = driver.findElement(By.cssSelector("div.shopee-popup img"));
		
		if (popup.size() > 0 && popup.get(0).isDisplayed()) {
			driver.findElement(By.cssSelector("div.shopee-popup__close-btn")).click();
			System.out.println("There is a popup appears!");
			sleepInSecond(2);
		} else {
			System.out.println("Popup is not displayed");
		}
		
		driver.findElement(By.cssSelector("input.shopee-searchbar-input__input")).sendKeys("coldbrew");
		driver.findElement(By.cssSelector("div.shopee-searchbar>button")).click();
		sleepInSecond(3);
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