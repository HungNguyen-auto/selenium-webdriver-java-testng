package webdriver;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_02_Xpath_Css_Alada {
	WebDriver driver; // library , component cua selenium
	By nameTextboxBy = By.id("txtFirstname");
	By emailTextboxBy = By.id("txtEmail");
	By confirmEmailTextboxBy = By.id("txtCEmail");
	By passwordTextboxBy = By.id("txtPassword");
	By confirmPasswordTextboxBy = By.id("txtCPassword");
	By phoneTextboxBy = By.id("txtPhone");
	By registerButtonBy = By.xpath("//form[@id='frmLogin']//button");

	By errnameTextboxBy = By.id("txtFirstname-error");
	By erremailTextboxBy = By.id("txtEmail-error");
	By errconfirmEmailTextboxBy = By.id("txtCEmail-error");
	By errpasswordTextboxBy = By.id("txtPassword-error");
	By errconfirmPasswordTextboxBy = By.id("txtCPassword-error");
	By errphoneTextboxBy = By.id("txtPhone-error");

	String fname, email, password, phone;

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		sleepInSecond(5);
		driver.manage().window().maximize();

		fname = "John Terry";
		email = "henry@gmail.com";
		password = "1234aa";
		phone = "0338791811";

	}

	@BeforeMethod
	public void beforeMethod() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	}

	@Test
	public void TC_01_Empty() {

		driver.findElement(registerButtonBy).click();

		Assert.assertEquals(driver.findElement(errnameTextboxBy).getText(), "Vui lòng nhập họ tên11");

		Assert.assertEquals(driver.findElement(erremailTextboxBy).getText(), "Vui lòng nhập email");

		Assert.assertEquals(driver.findElement(errconfirmEmailTextboxBy).getText(), "Vui lòng nhập lại địa chỉ email");

		Assert.assertEquals(driver.findElement(errpasswordTextboxBy).getText(), "Vui lòng nhập mật khẩu");

		Assert.assertEquals(driver.findElement(errconfirmPasswordTextboxBy).getText(), "Vui lòng nhập lại mật khẩu");

		Assert.assertEquals(driver.findElement(errphoneTextboxBy).getText(), "Vui lòng nhập số điện thoại.");

	}

	@Test
	public void TC_02_Invalid_Email() {

		driver.findElement(nameTextboxBy).sendKeys(fname);

		driver.findElement(emailTextboxBy).sendKeys(email + email + email);

		driver.findElement(confirmEmailTextboxBy).sendKeys(email + email);

		driver.findElement(passwordTextboxBy).sendKeys(password);

		driver.findElement(confirmPasswordTextboxBy).sendKeys(password);

		driver.findElement(phoneTextboxBy).sendKeys(phone);

		driver.findElement(registerButtonBy).click();

		Assert.assertEquals(driver.findElement(erremailTextboxBy).getText(), "Vui lòng nhập email hợp lệ");

		Assert.assertEquals(driver.findElement(errconfirmEmailTextboxBy).getText(), "Email nhập lại không đúng");

	}

	@Test
	public void TC_03_Incorrect_CEmail() {

		driver.findElement(nameTextboxBy).sendKeys(fname);

		driver.findElement(emailTextboxBy).sendKeys(email);

		driver.findElement(confirmEmailTextboxBy).sendKeys(email + email);

		driver.findElement(passwordTextboxBy).sendKeys(password);

		driver.findElement(confirmPasswordTextboxBy).sendKeys(password);

		driver.findElement(phoneTextboxBy).sendKeys(phone);

		driver.findElement(registerButtonBy).click();

		Assert.assertEquals(driver.findElement(errconfirmEmailTextboxBy).getText(), "Email nhập lại không đúng");
	}

	@Test
	public void TC_04_Less_Password() {

		driver.findElement(nameTextboxBy).sendKeys(fname);

		driver.findElement(emailTextboxBy).sendKeys(email);

		driver.findElement(confirmEmailTextboxBy).sendKeys(email);

		driver.findElement(passwordTextboxBy).sendKeys("123");

		driver.findElement(confirmPasswordTextboxBy).sendKeys("123");

		driver.findElement(phoneTextboxBy).sendKeys(phone);

		driver.findElement(registerButtonBy).click();

		Assert.assertEquals(driver.findElement(errpasswordTextboxBy).getText(), "Mật khẩu phải có ít nhất 6 ký tự");

		Assert.assertEquals(driver.findElement(errconfirmPasswordTextboxBy).getText(),
				"Mật khẩu phải có ít nhất 6 ký tự");

	}

	@Test
	public void TC_05_Incorrect_CPassword() {

		driver.findElement(nameTextboxBy).sendKeys(fname);

		driver.findElement(emailTextboxBy).sendKeys(email);

		driver.findElement(confirmEmailTextboxBy).sendKeys(email);

		driver.findElement(passwordTextboxBy).sendKeys(password);

		driver.findElement(confirmPasswordTextboxBy).sendKeys(password + password);

		driver.findElement(phoneTextboxBy).sendKeys(phone);

		driver.findElement(registerButtonBy).click();

		Assert.assertEquals(driver.findElement(errconfirmPasswordTextboxBy).getText(), "Mật khẩu bạn nhập không khớp");

	}

	@Test
	public void TC_06_Invalid_Phone() {

		driver.findElement(nameTextboxBy).sendKeys(fname);

		driver.findElement(emailTextboxBy).sendKeys(email);

		driver.findElement(confirmEmailTextboxBy).sendKeys(email);

		driver.findElement(passwordTextboxBy).sendKeys(password);

		driver.findElement(confirmPasswordTextboxBy).sendKeys(password);

		driver.findElement(phoneTextboxBy).sendKeys(email);

		driver.findElement(registerButtonBy).click();

		Assert.assertEquals(driver.findElement(errphoneTextboxBy).getText(), "Vui lòng nhập con số");

		driver.findElement(phoneTextboxBy).clear();

		driver.findElement(phoneTextboxBy).sendKeys("0357894");

		Assert.assertEquals(driver.findElement(errphoneTextboxBy).getText(), "Số điện thoại phải từ 10-11 số.");

		driver.findElement(phoneTextboxBy).clear();

		driver.findElement(phoneTextboxBy).sendKeys(phone + phone);

		Assert.assertEquals(driver.findElement(errphoneTextboxBy).getText(), "Số điện thoại phải từ 10-11 số.");
		
		driver.findElement(phoneTextboxBy).clear();

		driver.findElement(phoneTextboxBy).sendKeys("1234567891");

		Assert.assertEquals(driver.findElement(errphoneTextboxBy).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019");
		
		

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