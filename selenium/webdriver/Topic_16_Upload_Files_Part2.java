package webdriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Upload_Files_Part2 {
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

		// System.setProperty("webdriver.chrome.driver",
		// ".\\browserDrivers\\chromedriver.exe");
		// driver = new ChromeDriver();
		System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
	}

	@Test
	public void TC_01_Java_Robot_Upload_File() throws AWTException {

		uploadByRobotClass(pic1Path);
		sleepInSecond(3);
		uploadByRobotClass(pic2Path);
		sleepInSecond(3);
		uploadByRobotClass(pic3Path);

		sleepInSecond(2);
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

	public void uploadByRobotClass(String filePath) throws AWTException {
		StringSelection select = new StringSelection(filePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);

		driver.findElement(By.cssSelector(".btn-success")).click();

		Robot robot = new Robot();
		robot.delay(250);
		// Nhấn phím Enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		// Giữ phím Ctrl
		robot.keyPress(KeyEvent.VK_CONTROL);

		// Nhấn phím V
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);

		// Thả phím Ctrl
		robot.keyRelease(KeyEvent.VK_CONTROL);

		// Nhấn Enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.delay(90);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
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