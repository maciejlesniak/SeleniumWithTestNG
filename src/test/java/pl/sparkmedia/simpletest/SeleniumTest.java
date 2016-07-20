package pl.sparkmedia.simpletest;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeTest;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class SeleniumTest {

	private static final Logger LOG = LoggerFactory.getLogger(SeleniumTest.class);
	
	public WebDriver driver;
	private String baseUrl = "https://www.google.com";

	@BeforeTest
	public void beforeMethod() {
		LOG.debug("Log something in debug level");
		new TestProperties().load();
		
		// download chrome driver from
		// https://sites.google.com/a/chromium.org/chromedriver/home
		this.driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(baseUrl);
	}

	@Test(priority = 0)
	public void testTitle() {
		String pTitle = driver.getTitle();
		Assert.assertEquals(pTitle, "Google");
	}

	@Test(priority = 2)
	public void testSearch1() {
		WebElement element = driver.findElement(By.id("lst-ib"));
		element.clear();
		element.sendKeys("spark media");

		WebElement submit = driver.findElement(By.id("sblsbb")).findElement(By.tagName("button"));
		submit.click();
	}

	@Test(priority = 1)
	public void testSearch2() throws InterruptedException {
		WebElement element = driver.findElement(By.id("lst-ib"));
		element.clear();
		element.sendKeys("agencja reklamowa lublin");

		WebElement submit = driver.findElement(By.id("sblsbb")).findElement(By.tagName("button"));
		submit.click();
	}

	@AfterTest
	public void afterMethod() {
		driver.quit();
	}

}
