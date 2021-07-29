package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.Random;
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

public class Topic_15_Javascript_Executor {
	WebDriver driver; // library , component cua selenium
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Guru() {
		driver.get("http://live.demoguru99.com/");

		String domain = (String) jsExecutor.executeScript("return document.domain");

		Assert.assertEquals(domain, "live.demoguru99.com");

		String url = (String) jsExecutor.executeScript("return document.URL");

		Assert.assertEquals(url, "http://live.demoguru99.com/");

		// jsExecutor.executeScript("arguments[0].click()",
		// driver.findElement(By.xpath("//a[@href='http://live.demoguru99.com/index.php/tv.html']")));
		jsExecutor.executeScript("window.location = 'http://live.demoguru99.com/index.php/mobile.html'");

		WebElement samsumAdd = driver.findElement(By.xpath(
				"//a[@title='Samsung Galaxy']//parent::h2//following-sibling::div[@class='actions']//span[text()='Add to Cart']"));

		jsExecutor.executeScript("arguments[0].click()", samsumAdd);

		String contentText = jsExecutor.executeScript("return document.documentElement.innerText;").toString();

		Assert.assertTrue(contentText.contains("Samsung Galaxy was added to your shopping cart."));

		jsExecutor.executeScript("window.location = 'http://live.demoguru99.com/index.php/customer-service/'");

		String titleCSPage = jsExecutor.executeScript("return document.title;").toString();

		Assert.assertEquals(titleCSPage, "Customer Service");

		jsExecutor.executeScript("arguments[0].setAttribute('value', 'henrynguyen@gmail.com')",
				driver.findElement(By.id("newsletter")));

		jsExecutor.executeScript("arguments[0].click()", driver.findElement(By.xpath("//button[@title='Subscribe']")));

		String contentSub = jsExecutor.executeScript("return document.documentElement.innerText;").toString();

		Assert.assertTrue(contentSub.contains("Thank you for your subscription."));

		jsExecutor.executeScript("window.location = 'http://demo.guru99.com/v4/'");

		Assert.assertEquals(jsExecutor.executeScript("return document.domain").toString(), "demo.guru99.com");

	}

	@Test
	public void TC_02_Guru_Function() {
		navigateToUrlByJS("http://live.demoguru99.com/");

		String domain = (String) executeForBrowser("return document.domain;");

		Assert.assertEquals(domain, "live.demoguru99.com");

		String url = (String) executeForBrowser("return document.URL");

		Assert.assertEquals(url, "http://live.demoguru99.com/");

		highlightElement("//a[text()='Mobile']");
		clickToElementByJS("//a[text()='Mobile']");

		highlightElement(
				"//a[@title='Samsung Galaxy']//parent::h2//following-sibling::div[@class='actions']//span[text()='Add to Cart']");
		clickToElementByJS(
				"//a[@title='Samsung Galaxy']//parent::h2//following-sibling::div[@class='actions']//span[text()='Add to Cart']");

		Assert.assertTrue(isExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));

		highlightElement("//a[text()='Customer Service']");
		clickToElementByJS("//a[text()='Customer Service']");

		String csTitle = (String) executeForBrowser("return document.title;");

		Assert.assertEquals(csTitle, "Customer Service");

		scrollToElement("//input[@id='newsletter']");
		highlightElement("//input[@id='newsletter']");
		sendkeyToElementByJS("//input[@id='newsletter']", "henrynguyen@gmail.com");

		highlightElement("//button[@title='Subscribe']");
		clickToElementByJS("//button[@title='Subscribe']");

		Assert.assertTrue(isExpectedTextInInnerText("Thank you for your subscription."));

		navigateToUrlByJS("http://demo.guru99.com/v4/");

		Assert.assertEquals(executeForBrowser("return document.domain").toString(), "demo.guru99.com");
	}

	@Test
	public void TC_03_HTML5_Validation_Mgs() {
		navigateToUrlByJS("https://automationfc.github.io/html5/index.html");

		clickToElementByJS("//input[@name='submit-btn']");

		highlightElement("//input[@id='fname']");
		String nameMsg = getElementValidationMessage("//input[@id='fname']");
		Assert.assertEquals(nameMsg, "Please fill out this field.");

		sendkeyToElementByJS("//input[@id='fname']", "henry");
		clickToElementByJS("//input[@name='submit-btn']");

		highlightElement("//input[@id='pass']");
		String passMsg = getElementValidationMessage("//input[@id='pass']");
		Assert.assertEquals(passMsg, "Please fill out this field.");

		sendkeyToElementByJS("//input[@id='pass']", "1234aa");
		clickToElementByJS("//input[@name='submit-btn']");

		highlightElement("//input[@id='em']");
		String emailMsg = getElementValidationMessage("//input[@id='em']");
		Assert.assertEquals(emailMsg, "Please fill out this field.");

		sendkeyToElementByJS("//input[@id='em']", "123!#$");
		clickToElementByJS("//input[@name='submit-btn']");

		highlightElement("//input[@id='em']");
		String emailMsge = getElementValidationMessage("//input[@id='em']");
		Assert.assertEquals(emailMsge, "Please include an '@' in the email address. '123!#$' is missing an '@'.");

		sendkeyToElementByJS("//input[@id='em']", "henry@gmail.com");
		clickToElementByJS("//input[@name='submit-btn']");

		highlightElement("//select");
		String addressMsg = getElementValidationMessage("//select");
		Assert.assertEquals(addressMsg, "Please select an item in the list.");

	}

	@Test
	public void TC_04_Remove_Attribbute() {
		navigateToUrlByJS("http://demo.guru99.com/v4/");

		sendkeyToElementByJS("//input[@name='uid']", "mngr340589");
		sendkeyToElementByJS("//input[@name='password']", "tUpUvAh");
		clickToElementByJS("//input[@name='btnLogin']");

		clickToElementByJS("//a[text()='New Customer']");

		removeAttributeInDOM("//input[@id='dob']", "type");

		sendkeyToElementByJS("//input[@name='name']", "henry");
		sendkeyToElementByJS("//input[@name='dob']", "07/02/1997");
		// driver.findElement(By.xpath("//input[@name='dob']")).sendKeys("07/02/1997");
		driver.findElement(By.xpath("//textarea")).sendKeys("NTSSSSSSSSSS");
		sendkeyToElementByJS("//input[@name='city']", "hcm");
		sendkeyToElementByJS("//input[@name='state']", "us");
		sendkeyToElementByJS("//input[@name='pinno']", "123456");
		sendkeyToElementByJS("//input[@name='telephoneno']", "01238791811");
		sendkeyToElementByJS("//input[@name='emailid']", randomEmail());
		sendkeyToElementByJS("//input[@name='password']", "1234aaa");

		// clickToElementByJS("//input[@value='Submit']");

		driver.findElement(By.xpath("//input[@value='Submit']")).click();

		Assert.assertTrue(isExpectedTextInInnerText("Customer Registered Successfully!!!"));
	}

	@Test
	public void TC_05_Create_Account() {
		navigateToUrlByJS("http://live.demoguru99.com/");

		clickToElementByJS("//a[@title='My Account']");

		clickToElementByJS("//a[@title='Create an Account']");

		highlightElement("//input[@id='firstname']");
		sendkeyToElementByJS("//input[@id='firstname']", "henryn");

		highlightElement("//input[@id='lastname']");
		sendkeyToElementByJS("//input[@id='lastname']", "nguyen");

		highlightElement("//input[@id='email_address']");
		sendkeyToElementByJS("//input[@id='email_address']", randomEmail());

		highlightElement("//input[@id='password']");
		sendkeyToElementByJS("//input[@id='password']", "1234aa");

		highlightElement("//input[@id='confirmation']");
		sendkeyToElementByJS("//input[@id='confirmation']", "1234aa");

		clickToElementByJS("//button[@title='Register']");

		isExpectedTextInInnerText("Thank you for registering with Main Website Store.");

		clickToElementByJS("//a[@title='Log Out']");

		sleepInSecond(6);

		Assert.assertEquals(executeForBrowser("return document.URL;"), "http://live.demoguru99.com/index.php/");

	}

	public String randomEmail() {
		Random rand = new Random();
		return "henrynguyen" + rand.nextInt(9999) + "@gmail.com";
	}

	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean isExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElement(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				getElement(locator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
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