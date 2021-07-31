package webdriver;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Upload_Files_Part1 {
	WebDriver driver; // library , component cua selenium
	WebDriverWait explicitwait;
	String projectPath = System.getProperty("user.dir");
	String pic1 = "background1.png";
	String pic2 = "VSCode.png";
	String pic3 = "VSCode1.png";

	String uploadFilePath = projectPath + File.separator + "uploadFiles" + File.separator;
	String pic1Path = uploadFilePath + pic1;
	String pic2Path = uploadFilePath + pic2;
	String pic3Path = uploadFilePath + pic3;

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		explicitwait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Upload_Single_File() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(pic1Path);
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(pic2Path);
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(pic3Path);

		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + pic1 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + pic2 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + pic3 + "']")).isDisplayed());

		List<WebElement> startButtons = driver.findElements(
				By.xpath("//button[contains(@class,'edit')]//following-sibling::button[contains(@class,'start')]"));
		int largeItems = 0, successItems = 0;
		for (WebElement button : startButtons) {
			if (button.isEnabled()) {
				button.click();
				successItems += 1;
			} else {
				largeItems += 1;
			}
		}
		System.out.println("There were: " + largeItems + " too large item(s) and " + successItems
				+ " item(s) upload successfully!");
	}

	@Test
	public void TC_02_Upload_Multiple_Files() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(pic1Path + "\n" + pic2Path + "\n" + pic3Path);
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + pic1 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + pic2 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + pic3 + "']")).isDisplayed());

		List<WebElement> startButtons = driver.findElements(
				By.xpath("//button[contains(@class,'edit')]//following-sibling::button[contains(@class,'start')]"));
		int largeItems = 0, successItems = 0;
		for (WebElement button : startButtons) {
			if (button.isEnabled()) {
				button.click();
				successItems += 1;
			} else {
				largeItems += 1;
			}
		}
		System.out.println("There were: " + largeItems + " too large item(s) and " + successItems
				+ " item(s) upload successfully!");
	}

	@Test
	public void TC_03() {

		driver.get("https://gofile.io/uploadFiles");

		String parentID = driver.getWindowHandle();
		// driver.findElement(By.xpath("//div[@id='rowUploadButton']//button[contains(@class,'uploadButton')]")).sendKeys(pic1Path);
		driver.findElement(By.xpath("//input[@id='uploadFile-Input']"))
				.sendKeys(pic1Path + "\n" + pic2Path + "\n" + pic3Path);
		// explicitwait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".sorting_1")));
		sleepInSecond(5);
		driver.findElement(By.xpath("//a[@id='rowUploadSuccess-downloadPage']")).click();

		switchToWindowByID(parentID);

		Assert.assertTrue(
				driver.findElement(By.xpath("//span[@class='contentName' and text()='" + pic1 + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//span[@class='contentName' and text()='" + pic2 + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//span[@class='contentName' and text()='" + pic3 + "']")).isDisplayed());

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

	@AfterClass
	public void afterClass() {
		// driver.quit();
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