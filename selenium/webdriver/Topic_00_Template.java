package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_00_Template {
	WebDriver driver; //library , component cua selenium    

	@BeforeClass
	public void beforeClass() {
		//System.setProperty("webdriver.firefox.driver", ".\\browserDrivers\\geckodriver.exe"); //driver cua trinh duyet, executeable file, cau noi giua selenium webdriver va browser
		//driver = new FirefoxDriver();
		
		System.setProperty("webdriver.edge.driver", ".\\browserDrivers\\msedgedriver.exe");
		driver = new EdgeDriver();
		
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("");
	}

	@Test
	public void TC_01() {
	
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

}