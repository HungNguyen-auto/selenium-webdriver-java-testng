package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Frame_IFrame {
	WebDriver driver; // library , component cua selenium
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Iframe() {
		driver.get("https://kyna.vn/");

		scrollToBottomPage();

		driver.switchTo().frame(driver.findElement(By.cssSelector("div.fanpage iframe")));

		Assert.assertEquals(
				driver.findElement(By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div")).getText(),
				"168K lượt thích");

		// switch ve parent page
		driver.switchTo().defaultContent();

		driver.switchTo().frame(driver.findElement(By.cssSelector("#cs_chat_iframe")));

		driver.findElement(By.cssSelector("div.button_bar")).click();

		sleepInSecond(2);

		driver.findElement(By.cssSelector("input.submit")).click();

		sleepInSecond(2);

		Assert.assertEquals(driver.findElement(By.cssSelector("input.input_name + div")).getText(),
				"Tên của bạn chưa được nhập");

		Assert.assertEquals(
				driver.findElement(By.xpath("//select[@id='serviceSelect']//following-sibling::div")).getText(),
				"Bạn chưa chọn dịch vụ hỗ trợ");

		Assert.assertEquals(
				driver.findElement(By.xpath("//textarea[@name='message']/following-sibling::div")).getText(),
				"Tin nhắn chưa được nhập");

		driver.switchTo().defaultContent();

		driver.findElement(By.cssSelector("#live-search-bar")).sendKeys("Marketing");

		driver.findElement(By.cssSelector("button.search-button")).click();

		List<WebElement> allCourses = driver.findElements(By.cssSelector("div.content h4"));

		Assert.assertEquals(allCourses.size(), 15);

		for (WebElement course : allCourses) {
			System.out.println(course.getText());
			Assert.assertTrue(course.getText().toLowerCase().contains("marketing"));
		}
	}

	@Test
	public void TC_02_Frame() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");

		driver.switchTo().frame(driver.findElement(By.name("login_page")));

		driver.findElement(By.cssSelector("input.input_password")).sendKeys("1234");

		driver.findElement(By.xpath("//table[@class='lForm']//img[@alt='continue']")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//input[@name='fldPassword']")).isDisplayed());

		driver.switchTo().defaultContent();

		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='footer']")));

		driver.findElement(By.xpath("//p[@class='footer']/a[text()='Terms and Conditions']")).click();

		sleepInSecond(2);

	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
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