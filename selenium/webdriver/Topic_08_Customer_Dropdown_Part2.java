package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Customer_Dropdown_Part2 {
	WebDriver driver; // library , component cua selenium
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	String[] firstMonth = { "February", "May", "October" };
	String[] secondMonth = { "February", "May", "October", "August" };

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 15);
		jsExecutor = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_ID() {
		driver.get("https://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");

		SelectInMultipleDropdown("(//button[@class='ms-choice'])[1]", "(//div[@class='ms-drop bottom'])[1]//li//span",
				firstMonth);
		sleepInSecond(5);
		Assert.assertTrue(areItemSelected(firstMonth));
		
		driver.navigate().refresh();
		SelectInMultipleDropdown("(//button[@class='ms-choice'])[1]", "(//div[@class='ms-drop bottom'])[1]//li//span",
				secondMonth);
		Assert.assertTrue(areItemSelected(secondMonth));

	}

	public void SelectInMultipleDropdown(String parentXpath, String childXpath, String[] expectedItems) {
		driver.findElement(By.xpath(parentXpath)).click();
		sleepInSecond(1);

		List<WebElement> allItems = explicitWait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));

		for (WebElement childElement : allItems) {
			for (String item : expectedItems) {
				if (childElement.getText().equals(item)) {
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", childElement);
					sleepInSecond(1);

					childElement.click();
					sleepInSecond(1);

					List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']"));
					System.out.println("Item selected: " + itemSelected.size());
					if (expectedItems.length == itemSelected.size()) {
						break;
					}
				}
			}

		}

	}

	public boolean areItemSelected(String[] months) {
		List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
		int numberItemSelected = itemSelected.size();

		String allItemsSelectedText = driver.findElement(By.xpath("(//button[@class='ms-choice']/span)[1]")).getText();
		System.out.println("Text da chon = " + allItemsSelectedText);

		if (numberItemSelected > 0 && numberItemSelected < 4) {
			boolean status = true;
			for (String item : months) {
				if (!allItemsSelectedText.contains(item)) {
					status = false;
					return status;
				}
			}
			return status;
		} else if (numberItemSelected == 12) {
			return driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()='All selected']"))
					.isDisplayed();
		} else if (numberItemSelected > 3 && numberItemSelected < 12) {
			return driver
					.findElement(By.xpath(
							"//button[@class='ms-choice']/span[text()='" + numberItemSelected + " of 12 selected']"))
					.isDisplayed();
		} else {
			return false;
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