package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.naming.SizeLimitExceededException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Windows_Tabs {
	WebDriver driver; // library , component cua selenium
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {

		// System.setProperty("webdriver.chrome.driver",
		// ".\\browserDrivers\\chromedriver.exe");
		// driver = new ChromeDriver();
		System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Windows() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		String parentID = driver.getWindowHandle();

		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSecond(2);
		switchToWindowByTitle("Facebook - Đăng nhập hoặc đăng ký");

		// switch ve parent
		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		sleepInSecond(2);
		driver.findElement(By.cssSelector("#mail")).sendKeys("henry");

		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		sleepInSecond(2);
		switchToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");

		driver.findElement(By.xpath("//input[@data-view-id='main_search_form_input']")).sendKeys("Lazada");
		driver.findElement(By.xpath("//button[@data-view-id='main_search_form_button']")).click();

		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		driver.findElement(By.xpath("//a[text()='LAZADA']")).click();
		sleepInSecond(2);
		switchToWindowByTitle("Shopping online - Buy online on Lazada.vn");

		driver.findElement(By.xpath("//input[@type='search']")).sendKeys("Shopee");
		driver.findElement(By.xpath("//div[@class='lzd-nav-search']//button")).click();

		closeAllWindowButParent(parentID);
		driver.findElement(By.id("edu")).sendKeys("tesst");
	}

	@Test
	public void TC_02_Kyna() {

		driver.get("https://kyna.vn/");

		String parentID = driver.getWindowHandle();

		jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		driver.findElement(By.xpath("//div[@id='k-footer']//a[@href='https://www.facebook.com/kyna.vn']")).click();
		sleepInSecond(2);
		switchToWindowByTitle("Kyna.vn - Trang chủ | Facebook");
		Assert.assertTrue(driver.findElement(By.xpath("//h1/a/span[text()='Kyna.vn']")).isDisplayed());

		switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
		driver.findElement(By.xpath("//div[@id='k-footer']//a[@href='https://www.youtube.com/user/kynavn']")).click();
		sleepInSecond(2);
		switchToWindowByTitle("Kyna.vn - YouTube");
		sleepInSecond(2);
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.youtube.com/user/kynavn");

		switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
		driver.findElement(By.xpath("//a[@href='http://online.gov.vn/HomePage/CustomWebsiteDisplay.aspx?DocId=61482']"))
				.click();
		sleepInSecond(2);
		switchToWindowByTitle("Thông tin website thương mại điện tử - Online.Gov.VN");
		Assert.assertEquals(driver.getCurrentUrl(), "http://online.gov.vn/Home/WebDetails/61473");

		closeAllWindowButParent(parentID);
		Assert.assertTrue(driver.findElement(By.xpath("//second[text()='hotro@kyna.vn']")).isDisplayed());
	}

	@Test
	public void TC_03_Guru() {
		driver.get("http://live.demoguru99.com/index.php/");

		String parentID = driver.getWindowHandle();

		driver.findElement(By.xpath("//a[text()='Mobile']")).click();

		driver.findElement(By.xpath(
				"//a[@title='Sony Xperia']//parent::h2//following-sibling::div[@class='actions']//a[text()='Add to Compare']"))
				.click();

		driver.findElement(By.xpath(
				"//a[@title='Samsung Galaxy']//parent::h2//following-sibling::div[@class='actions']//a[text()='Add to Compare']"))
				.click();

		driver.findElement(By.xpath("//button[@title='Compare']")).click();
		sleepInSecond(2);
		switchToWindowByTitle("Products Comparison List - Magento Commerce");

		driver.findElement(By.xpath("//button[@title='Close Window']")).click();

		switchToWindowByTitle("Mobile");

		driver.findElement(By.xpath("//a[text()='Clear All']")).click();

		driver.switchTo().alert().accept();

		Assert.assertTrue(
				driver.findElement(By.xpath("//span[text()='The comparison list was cleared.']")).isDisplayed());

	}

	public void switchToWindowByID(String parentID) {
		// Get ra tat ca tab/ windows
		Set<String> allWindows = driver.getWindowHandles();

		for (String id : allWindows) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
			}
		}
	}

	// switch vao window > gettitle > kiem tra title voi tile mong muon > return
	public void switchToWindowByTitle(String title) {

		Set<String> allWindows = driver.getWindowHandles();

		for (String id : allWindows) {
			driver.switchTo().window(id);
			String windowTitle = driver.getTitle();
			if (windowTitle.equals(title)) {
				break;
			}

		}

	}

	public void closeAllWindowButParent(String parentID) {
		Set<String> allWindows = driver.getWindowHandles();

		for (String id : allWindows) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
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