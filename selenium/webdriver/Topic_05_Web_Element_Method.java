package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//import sun.security.krb5.internal.rcache.AuthTimeWithHash;

public class Topic_05_Web_Element_Method {
	WebDriver driver; // library , component cua selenium

	By emailTextbox = By.id("mail");
	By educationTextArea = By.id("edu");
	By under18RadioButton = By.id("under_18");
	By javaCheckbox = By.id("java");
	By jobrole1Dropdown = By.id("job1");
	By jobrole2Dropdown = By.id("job2");
	By developmentCheckbox = By.id("development");
	By slider2 = By.id("slider-2");
	By password = By.xpath(".//*[@id='password' and @name='user_pass']");
	By ageDisable = By.id("radio-disabled");

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		//driver = new FirefoxDriver();

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	//@Test
	public void TC_01_Displayed_Newbie() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		if (driver.findElement(By.id("mail")).isDisplayed()) {
			driver.findElement(By.id("mail")).sendKeys("henry.nguyen@gmail.com");
			System.out.println("Email textbox is displayed");

		} else {
			System.out.println("Email textbox is not displayed");
		}

		if (driver.findElement(By.id("edu")).isDisplayed()) {
			driver.findElement(By.id("edu")).sendKeys("Lorem ipsum dolor sit amet");
			System.out.println("Edu textarea is displayed");

		} else {
			System.out.println("Edu textarea is not displayed");
		}

		if (driver.findElement(By.id("under_18")).isDisplayed()) {
			driver.findElement(By.id("under_18")).click();
			System.out.println("under18 radio button is displayed");

		} else {
			System.out.println("under18 radio button is not displayed");
		}
	}

	//@Test
	public void TC_01_Displayed_Function() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		if (isElementDisplayed(emailTextbox)) {
			sendkeyToElement(emailTextbox, "Henry.nguyen@gmail.com");
		}

		if (isElementDisplayed(educationTextArea)) {
			sendkeyToElement(educationTextArea, "Lorem ipsum dolor sit amet");
		}

		if (isElementDisplayed(under18RadioButton)) {
			clickOnElement(under18RadioButton);
		}

	}

	//@Test
	public void TC_02_Selected_Function() {

		driver.get("https://automationfc.github.io/basic-form/index.html");

		clickOnElement(under18RadioButton);
		clickOnElement(javaCheckbox);

		// verify checked
		Assert.assertTrue(isElementSelected(under18RadioButton));
		Assert.assertTrue(isElementSelected(javaCheckbox));

		clickOnElement(under18RadioButton);
		clickOnElement(javaCheckbox);

		// verify checkbox is de-selected
		Assert.assertTrue(isElementSelected(under18RadioButton));
		Assert.assertFalse(isElementSelected(javaCheckbox));

	}

	//@Test
	public void TC_03_Enabled_Function() {

		driver.get("https://automationfc.github.io/basic-form/index.html");
		Assert.assertTrue(isElementEnabled(emailTextbox));
		Assert.assertTrue(isElementEnabled(under18RadioButton));
		Assert.assertTrue(isElementEnabled(educationTextArea));
		Assert.assertTrue(isElementEnabled(jobrole1Dropdown));
		Assert.assertTrue(isElementEnabled(jobrole2Dropdown));
		Assert.assertTrue(isElementEnabled(developmentCheckbox));

		Assert.assertFalse(isElementEnabled(slider2));
		Assert.assertFalse(isElementEnabled(password));
		Assert.assertFalse(isElementEnabled(ageDisable));
	}

	@Test
	public void TC_04_MailChimp_SignUp() {

		driver.get("https://login.mailchimp.com/signup/");

		sleepInSecond(2);
		By passwordTextbox = By.id("new_password");
		By signupButton = By.cssSelector("#create-account");
		By newsletterCheckbox = By.xpath("//label[@for='marketing_newsletter']");
		By upperCaseCompleted = By.cssSelector(".uppercase-char.completed");
		By lowerCaseCompleted = By.cssSelector(".lowercase-char.completed");
		By numberCompleted = By.cssSelector(".number-char.completed");
		By specialCaseCompleted = By.cssSelector(".special-char.completed");
		By lengthCaseCompleted = By.cssSelector("li[class='8-char completed']");

		driver.findElement(By.id("email")).sendKeys("henry.nguyen@gmail.com");
		driver.findElement(By.id("new_username")).sendKeys("henry.nguyen");

		// Uppercase
		driver.findElement(passwordTextbox).sendKeys("AUTOMATION");
		sleepInSecond(2);
		Assert.assertTrue(isElementDisplayed(upperCaseCompleted));
		Assert.assertFalse(isElementEnabled(signupButton));

		// Lowercase
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("automation");
		sleepInSecond(2);
		Assert.assertTrue(isElementDisplayed(lowerCaseCompleted));
		Assert.assertFalse(isElementEnabled(signupButton));

		// Number
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("123456");
		sleepInSecond(2);
		Assert.assertTrue(isElementDisplayed(numberCompleted));
		Assert.assertFalse(isElementEnabled(signupButton));

		// Special
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("!@#$%");
		sleepInSecond(2);
		Assert.assertTrue(isElementDisplayed(specialCaseCompleted));
		Assert.assertFalse(isElementEnabled(signupButton));

		// 8 chars
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("automationtester");
		sleepInSecond(2);
		Assert.assertTrue(isElementDisplayed(lengthCaseCompleted));
		Assert.assertTrue(isElementDisplayed(lowerCaseCompleted));
		Assert.assertFalse(isElementEnabled(signupButton));

		// All criteria
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("Automation123@");
		sleepInSecond(2);
		Assert.assertFalse(isElementDisplayed(lowerCaseCompleted));
		Assert.assertFalse(isElementDisplayed(upperCaseCompleted));
		Assert.assertFalse(isElementDisplayed(numberCompleted));
		Assert.assertFalse(isElementDisplayed(specialCaseCompleted));
		Assert.assertFalse(isElementDisplayed(lengthCaseCompleted));
		
		sleepInSecond(2);
		clickOnElement(newsletterCheckbox);
		sleepInSecond(2);
		Assert.assertTrue(isElementSelected(newsletterCheckbox));
	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

	public boolean isElementDisplayed(By by) {

		if (driver.findElement(by).isDisplayed()) {
			System.out.println(by + " is displayed");
			return true;
		} else {
			System.out.println(by + " is not displayed");
			return false;
		}
	}

	public void sendkeyToElement(By by, String value) {
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(value);
	}

	public void clickOnElement(By by) {
		driver.findElement(by).click();
	}

	public boolean isElementSelected(By by) {

		if (driver.findElement(by).isSelected()) {
			System.out.println(by + " is Selected");
			return true;
		} else {
			System.out.println(by + " is not Selected");
			return false;
		}
	}

	public boolean isElementEnabled(By by) {

		if (driver.findElement(by).isEnabled()) {
			System.out.println(by + " is Enabled");
			return true;
		} else {
			System.out.println(by + " is Disabled");
			return false;
		}
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