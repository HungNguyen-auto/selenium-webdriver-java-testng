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

public class Topic_17_Wait2_FindElement {
	WebDriver driver; // library , component cua selenium
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// driver.manage().window().maximize();
		driver.get("https://www.facebook.com/");
	}

	@Test
	public void TC_01_FindElement() {
		driver.get("https://www.facebook.com/");

		// Có duy nhất 1 element - ko cần chờ hết timeout của implicit - tương tác với
		// element luôn
		WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='email']"));
		emailTextbox.sendKeys("automa@fb.com");

		// Ko có element nào hết - chờ hết timeout của implicit - cứ mỗi 500ms sẽ tìm
		// lại 1 lần
		// Khi nào hết timeout của implicit sẽ đánh fail testcase và throw exception:
		// NoSuchElementException
		// driver.findElement(By.id("address")).sendKeys("VN");

		driver.findElement(By.id("pass")).sendKeys("1234aa");

		// Tìm thấy nhiều hơn 1 element - ko cần chờ hết timeout của implicit
		// Nó sẽ lấy element đầu tiên để thao tác
		System.out.println(driver.findElement(By.xpath("//input")).getAttribute("name"));
		System.out.println(driver.findElement(By.xpath("//input")).getAttribute("type"));
		System.out.println(driver.findElement(By.xpath("//input")).getAttribute("value"));
	}

	@Test
	public void TC_02_FindElements() {
		driver.navigate().refresh();
		// List<WebElement> allLinks = driver.findElements(By.xpath("//a"));

		driver.findElements(By.id("email")).get(0).sendKeys("facebook@gmail.com");

		System.out.println(driver.findElements(By.id("email")).size());

		// Ko có element nào hết - chờ hết timeout của implicit - cứ mỗi 500ms sẽ tìm
		// lại 1 lần
		// Khi nào hết timeout của implicit thì ko đánh fail testcase
		// Trả về empty list
		// => cần kiểm tra 1 element ko xuất hiện và ko có trong DOM
		System.out.println(driver.findElements(By.id("address")).size());

		driver.findElements(By.id("pass")).get(0).sendKeys("1234aa");

		// Nhiều hơn 1 element - ko cần chờ hết timeout
		// Lưu hết tất cả các element vào trong list
		List<WebElement> footerLinks = driver.findElements(By.cssSelector("ul.pageFooterLinkList a"));
		System.out.println(footerLinks.size());
		for (WebElement link : footerLinks) {
			System.out.println(link.getText());
		}
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