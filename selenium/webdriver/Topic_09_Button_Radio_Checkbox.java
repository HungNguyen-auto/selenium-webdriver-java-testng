package webdriver;

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

public class Topic_09_Button_Radio_Checkbox {
	WebDriver driver; // library , component cua selenium
	JavascriptExecutor jsExecutor;

	boolean status;
	By emailTebox = By.id("login_username");
	By passwordTextbox = By.id("login_password");
	By checkbox = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input");
	By radio = By.xpath("//label[text()='2.0 Diesel, 103kW']/preceding-sibling::input");

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create?attempt=1");

		driver.findElement(By.cssSelector(".popup-login-tab-login")).click();

		status = driver.findElement(By.cssSelector(".fhs-btn-login")).isEnabled();

		Assert.assertFalse(status);
		System.out.println("Button status: " + status);

		driver.findElement(emailTebox).sendKeys("0357626252");

		driver.findElement(passwordTextbox).sendKeys("1234aa");

		sleepInSecond(1);
		status = driver.findElement(By.cssSelector(".fhs-btn-login")).isEnabled();
		Assert.assertTrue(status);
		System.out.println("Button status after: " + status);

		driver.navigate().refresh();
		driver.findElement(By.cssSelector(".popup-login-tab-login")).click();

		// remove attrribute using javascript
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled')",
				driver.findElement(By.cssSelector(".fhs-btn-login")));
		sleepInSecond(3);
		driver.findElement(By.cssSelector(".fhs-btn-login")).click();

		Assert.assertEquals(driver.findElement(By.xpath(
				"//div[@class='popup-login-content']//label[text()='Số điện thoại/Email']/following-sibling::div[@class='fhs-input-alert']"))
				.getText(), "Thông tin này không thể để trống");
		Assert.assertEquals(driver.findElement(By.xpath(
				"//div[@class='popup-login-content']//label[text()='Mật khẩu']/following-sibling::div[@class='fhs-input-alert']"))
				.getText(), "Thông tin này không thể để trống");

		driver.navigate().refresh();

		jsExecutor.executeScript("arguments[0].removeAttribute('disabled')",
				driver.findElement(By.cssSelector(".fhs-btn-login")));
		sleepInSecond(3);
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(By.cssSelector(".fhs-btn-login")));

	}

	@Test
	public void TC_02_Checkbox_Radio() {

		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		// click vào checbbox
		checkToCheckbox(checkbox);

		// verify check da duoc chon
		Assert.assertTrue(driver.findElement(checkbox).isSelected());

		// Bo chon checkbox
		uncheckToCheckbox(checkbox);

		// verify check da duoc chon
		Assert.assertFalse(driver.findElement(checkbox).isSelected());

		// radio button
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");

		checkToCheckbox(radio);

		Assert.assertTrue(driver.findElement(radio).isSelected());

	}

	@Test
	public void TC_03_Select_All() {
		driver.get("https://automationfc.github.io/multiple-fields/");

		List<WebElement> allCheckboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));

		for (WebElement item : allCheckboxes) {
			if (!item.isSelected()) {
				item.click();
			}
		}
		for (WebElement item : allCheckboxes) {
			Assert.assertTrue(item.isSelected());
		}
	}

	@Test
	public void TC_04_Custom_Checkbox_Radio() {
		driver.get("https://material.angular.io/components/radio/examples");

		By winterRadio = By.xpath("//input[@value='Winter']");
		clickToElementByJS(winterRadio);
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(winterRadio).isSelected());

		driver.get("https://material.angular.io/components/checkbox/examples");
		By checkedCheckbox = By.xpath("//input[@id='mat-checkbox-1-input']");
		By indetCheckbox = By.xpath("//input[@id='mat-checkbox-2-input']");
		clickToElementByJS(checkedCheckbox);
		clickToElementByJS(indetCheckbox);
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(checkedCheckbox).isSelected());
		Assert.assertTrue(driver.findElement(indetCheckbox).isSelected());

		clickToElementByJS(checkedCheckbox);
		clickToElementByJS(indetCheckbox);
		sleepInSecond(2);
		Assert.assertFalse(driver.findElement(checkedCheckbox).isSelected());
		Assert.assertFalse(driver.findElement(indetCheckbox).isSelected());
	}

	@Test
	public void TC_05_Custom_Checkbox_Radio() {
		driver.get(
				"https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath(
				"//div[@aria-label='Quảng Nam' and  @aria-checked='false']/div[contains(@class, 'exportInnerBox')]"))
				.isDisplayed());

		driver.findElement(By.xpath("//div[@aria-label='Quảng Nam']/div[contains(@class, 'exportInnerBox')]")).click();
		// dùng attribute html để verify (thay đổi sau khi click)
		Assert.assertTrue(driver.findElement(By.xpath(
				"//div[@aria-label='Quảng Nam' and  @aria-checked='true']/div[contains(@class, 'exportInnerBox')]"))
				.isDisplayed());

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void checkToCheckbox(By by) {
		WebElement checkbox = driver.findElement(by);
		if (!checkbox.isSelected()) {
			checkbox.click();
		}
	}

	public void uncheckToCheckbox(By by) {
		WebElement checkbox = driver.findElement(by);
		if (checkbox.isSelected()) {
			checkbox.click();
		}
	}

	public void clickToElementByJS(By by) {
		WebElement element = driver.findElement(by);

		jsExecutor.executeScript("arguments[0].click()", element);
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