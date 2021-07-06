package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Element_Method {
	WebDriver driver; // library , component cua selenium

	@BeforeClass
	public void beforeClass() {
		// System.setProperty("webdriver.firefox.driver",
		// ".\\browserDrivers\\geckodriver.exe"); //driver cua trinh duyet, executeable
		// file, cau noi giua selenium webdriver va browser
		// driver = new FirefoxDriver();

		driver = new FirefoxDriver();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.manage().window().maximize();

		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
	}

	@Test
	public void TC_01_Web_Element() {

		driver.findElement(By.id(""));

		driver.findElements(By.id(""));

		// nếu chỉ thao tác với element 1 lần thì ko cần khai báo biến
		driver.findElement(By.id("small-searchterms")).sendKeys("Apple");

		// nếu cần thao tác với 1 element nhiều lần thì nên khai báo biến
		WebElement searchTextbox = driver.findElement(By.id("small-searchterms"));

		searchTextbox.clear();
		searchTextbox.sendKeys("Win11");
		searchTextbox.getAttribute("value");

		// đếm số lượng element thỏa điều kiện
		List<WebElement> textboxes = driver
				.findElements(By.xpath("//div[@class='inputs']/input[not(@type='checkbox')]"));

		// verify có 6 textbox tại form register
		Assert.assertEquals(textboxes.size(), 6);
		
		WebElement singleElement = driver.findElement(By.className(""));

		//Textbox, textare, editable dropdown
		searchTextbox.clear();
		searchTextbox.sendKeys("");
		
		// button, link, radio, checkbox, custom dropdown
		singleElement.click();
		
		singleElement.getAttribute("placeholder");
		
		// Lấy ra các giá trị các thuộc tính css - dùng để test GUI: font, size, background
		singleElement = driver.findElement(By.cssSelector(".search-box-button"));
		
		singleElement.getCssValue("background-color");
		
		// Lấy ra tọa độ của element
		singleElement.getLocation();
		
		// Lấy ra kích thước của element (width -high)
		singleElement.getSize();
		
		// location + size
		singleElement.getRect();
		
		//chụp ảnh màn hình rồi đưa vào html report
		singleElement.getScreenshotAs(OutputType.FILE);
		
		//lấy thẻ html cuối cùng trong xpath
		//từ element ko biết tagname. lấy ra tagname để truyền vào cho 1 locator khác
		singleElement.getTagName();
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