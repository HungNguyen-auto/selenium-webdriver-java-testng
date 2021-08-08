package webdriver;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Wait5_ExpicitWait {
	WebDriver driver; // library , component cua selenium
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	By startButton = By.xpath("//button");
	By helloworldText = By.xpath("//div[@id='finish']");
	By loadingIcon = By.cssSelector("div#loading");
	By calendar = By.cssSelector(".calendarContainer");
	By selectedDate = By.xpath("//span[@class='label']");
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
		explicitWait = new WebDriverWait(driver, 30);
	
		
	}

	@Test
	public void TC_01_Visible() {
		
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

		driver.findElement(startButton).click();
		
		// Chờ cho text hello world xuất hiện
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(helloworldText));
		
		Assert.assertTrue(driver.findElement(helloworldText).isDisplayed());

		Assert.assertEquals(driver.findElement(helloworldText).getText(), "Hello World!");
	}

	@Test
	public void TC_02_Invisible() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

		driver.findElement(startButton).click();
		
		// Chờ cho loading icon biến mất
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
		
		Assert.assertTrue(driver.findElement(helloworldText).isDisplayed());

		Assert.assertEquals(driver.findElement(helloworldText).getText(), "Hello World!");
	}

	@Test
	public void TC_03_Ajax_Loading() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(calendar));
		
		System.out.println(driver.findElement(selectedDate).getText());
		Assert.assertEquals(driver.findElement(selectedDate).getText(), "No Selected Dates to display.");
		
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td/a[text()='7']")));
		driver.findElement(By.xpath("//td/a[text()='7']")).click();
		
		// Wait cho ajax loading bien mat
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@style!='display:none;']//div[@class='raDiv']")));
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='rcSelected']//a[text()='7']")));

		Assert.assertEquals(driver.findElement(selectedDate).getText(), "Saturday, August 7, 2021");
	}
	@Test
	public void TC_03_Upload_File() {
		driver.get("https://gofile.io/uploadFiles");
		
		// Wait cho upload file co trong DOM
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='file']")));
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(pic1Path + "\n" + pic2Path + "\n" + pic3Path);
		
		// wait choose server iconn invisible
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#rowUploadProgress-selectServer")));
		
		// Wait progress bar invisible
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress"))));
		
		// Wait cho success msg visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[text()='Your files have been successfully uploaded']")));
		Assert.assertTrue(driver.findElement(By.xpath("//h5[text()='Your files have been successfully uploaded']")).isDisplayed());
		
		// Wait cho show file button clickable
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button#rowUploadSuccess-showFiles")));
		driver.findElement(By.cssSelector("button#rowUploadSuccess-showFiles")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and text()='" + pic1 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and text()='" + pic2 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and text()='" + pic3 + "']")).isDisplayed());
		
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