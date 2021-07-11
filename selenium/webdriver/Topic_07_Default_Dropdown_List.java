package webdriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Default_Dropdown_List {
	WebDriver driver; // library , component cua selenium
	Select select;
	String firstname, lastname, email, companyName, day, month, year, country;
	List<String> expectedItemText;
	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		sleepInSecond(5);
		driver.manage().window().maximize();

		firstname = "Henry";
		lastname = "Nguyen";
		email = "henry.nguyen" + randomNumber() + "@gmail.com";
		companyName = "Nexdev";
		day = "27";
		month = "March";
		year = "1997";
		expectedItemText = new ArrayList<String>(Arrays.asList("Month", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October",  "November", "December" ));
		
		country = "Vietnam";
	}

	//@Test
	public void TC_01_NopCommerce() {
		driver.get("https://demo.nopcommerce.com/");
		driver.findElement(By.className("ico-register")).click();

		driver.findElement(By.id("gender-male")).click();
		driver.findElement(By.id("FirstName")).sendKeys(firstname);
		driver.findElement(By.id("LastName")).sendKeys(lastname);

		// Handle dropdownlist
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		// select day 27
		select.selectByVisibleText(day);
		// Verify how many items in dropdown list
		Assert.assertEquals(select.getOptions().size(), 32);
		// single or multiple
		Assert.assertFalse(select.isMultiple());
		// verify that selected day is correct
		Assert.assertEquals(select.getFirstSelectedOption().getText(), day);

		// Month
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		select.selectByVisibleText(month);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);

		// Year
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		select.selectByVisibleText(year);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);

		driver.findElement(By.id("Email")).sendKeys(email);
		driver.findElement(By.id("Company")).sendKeys(companyName);
		driver.findElement(By.id("Password")).sendKeys("1234aa");
		driver.findElement(By.id("ConfirmPassword")).sendKeys("1234aa");
		
		driver.findElement(By.id("register-button")).click();
		sleepInSecond(2); 
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(), "Your registration completed");
	
		driver.findElement(By.xpath("//a[@class='ico-account']")).click();
		
		Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstname);
		Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastname);
		Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"), email);
		Assert.assertEquals(driver.findElement(By.id("Company")).getAttribute("value"), companyName);
		
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), day);
		
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
	}

	//@Test
	public void TC_02_Verify_List() {
		driver.get("https://demo.nopcommerce.com/");
		
		driver.findElement(By.className("ico-register")).click();
		
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		
		List<WebElement> allItems = select.getOptions();
		List<String> allItemsText = new ArrayList<>();
		
		
		for (WebElement item: allItems)
		{
			System.out.println(item.getText());
			allItemsText.add(item.getText());
			
		}
		Assert.assertEquals(expectedItemText, allItemsText);
		
	}

	@Test
	public void TC_03_WhereToBuy() {
		driver.get("https://www.rode.com/wheretobuy");
		
		select = new Select(driver.findElement(By.name("where_country")));
		Assert.assertFalse(select.isMultiple());
		select.selectByVisibleText(country);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), country);
		
		driver.findElement(By.id("search_loc_submit")).click();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result_count']/span")).getText(), "28");
		
		List<WebElement> allDistributors = driver.findElements(By.xpath("//div[@id='search_results']//div[@class='store_name']"));
		
		for (WebElement item: allDistributors)
		{
			System.out.println(item.getText());
		}
		
	}

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}

	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
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