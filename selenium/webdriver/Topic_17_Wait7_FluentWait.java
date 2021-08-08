package webdriver;

import java.time.Duration;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Wait7_FluentWait {
	WebDriver driver; // library , component cua selenium
	String projectPath = System.getProperty("user.dir");
	FluentWait<WebElement> fluentElement;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	Actions action;
	long timeout = 15;
	long polling = 1;
	By calendar = By.cssSelector(".calendarContainer");
	By selectedDate = By.xpath("//span[@class='label']");

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		action = new Actions(driver);
		explicitWait = new WebDriverWait(driver, timeout);
	}

	@Test
	public void TC_01_() {
		driver.get("https://automationfc.github.io/fluent-wait/");

		WebElement countdownTimer = driver.findElement(By.id("javascript_countdown_time"));

		fluentElement = new FluentWait<WebElement>(countdownTimer);

		fluentElement.withTimeout(Duration.ofSeconds(15)).pollingEvery(Duration.ofMillis(1000))
				.ignoring(NoSuchElementException.class).until(new Function<WebElement, Boolean>() {

					@Override
					public Boolean apply(WebElement contdownTimer) {
						boolean status = countdownTimer.getText().endsWith("00");
						System.out.println("Text = " + countdownTimer.getText() + "-----------" + status);
						return status;
					}
				});

	}

	@Test
	public void TC_02_() {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		clickToElement(By.xpath("//button"));

		Assert.assertTrue(isElementDisplayed(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")));

		Assert.assertEquals(getElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
	}

	@Test
	public void TC_03_Icon_Loading_Success() {
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/dashboard");

		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
		driver.findElement(By.id("btnLogin")).click();

		Assert.assertTrue(isJQueryLoadedSuccess(driver));

		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='total']//span")).getText(), "3 month(s)");

		action.moveToElement(driver.findElement(By.xpath("//a[@id='menu_pim_viewPimModule']"))).perform();

		driver.findElement(By.xpath("//a[@id='menu_pim_viewEmployeeList']")).click();

		Assert.assertTrue(isJQueryLoadedSuccess(driver));

		driver.findElement(By.xpath("//input[@id='empsearch_employee_name_empName']")).sendKeys("Peter Mac");

		driver.findElement(By.id("searchBtn")).click();

		Assert.assertTrue(isJQueryLoadedSuccess(driver));

		Assert.assertTrue(
				driver.findElement(By.xpath("//table[@id='resultTable']//a[text()='Peter Mac']")).isDisplayed());
	}

	public WebElement getElement(By locator) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(polling)).ignoring(NoSuchElementException.class);

		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver dirver) {
				return driver.findElement(locator);
			}

		});
		return element;
	}

	public boolean isJQueryLoadedSuccess(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, timeout);
		jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}

		};
		return explicitWait.until(jQueryLoad);
	}

	public void clickToElement(By locator) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(polling)).ignoring(NoSuchElementException.class);

		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver dirver) {
				return driver.findElement(locator);
			}

		});
		element.click();
	}

	public boolean isElementDisplayed(By locator) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(polling)).ignoring(NoSuchElementException.class);

		Boolean status = wait.until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				return driver.findElement(locator).isDisplayed();
			}

		});
		return status;
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