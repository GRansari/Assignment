package candere.project.StepDefs;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import candere.project.core.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepdef {

	private static final Logger logger = LogManager.getLogger(stepdef.class);

	WebDriver driver;
	WebDriverWait wait;
	Scenario scn;

	String Url = "https://www.candere.com/";

	@Before
	public void Setup(Scenario scn) throws Exception {

		this.scn = scn;

		driver = new ChromeDriver();
		logger.info("chrome browser invoked");
		driver.manage().window().maximize();
		logger.info("Window maximized");
		driver.manage().deleteAllCookies();
		logger.info("manage delete allcookies");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		logger.info("implicitlywait set for 20 secunds");
		wait = new WebDriverWait(driver, 20);
		logger.info("WebDriverWait set for 20 seconds");

		scn.log("Browser invoked");

	}

	@Given("user navigate to the home application URL")
	public void user_navigate_to_the_home_application_url() {

		driver.get(Url);
		scn.log("user navigate on the landing page");
	}

	@When("user is on application landing page")
	public void user_is_on_application_landing_page() {

		Assert.assertEquals(Url, driver.getCurrentUrl());
		scn.log("user is on home page of the application");
	}

	@Then("Application title is {string}")
	public void application_title_is(String string) {
		Assert.assertEquals(string, driver.getTitle());
		scn.log("user validate the title => " + string);
	}

	@Then("user search for the product {string}")
	public void user_search_for_the_product(String string) {
		
		WebElement SearchBox = driver.findElement(By.id("search"));
		wait.until(ExpectedConditions.elementToBeClickable(SearchBox));
		SearchBox.sendKeys(string);
		scn.log("user search the product => " + string);
		
	}

	@Then("{string} displayed after the search")
	public void displayed_after_the_search(String string)  {
		
		String searchedProd = driver.findElement(By.xpath("//div[text()='Majestic Solitaire Diamond Ring']")).getText().toLowerCase();
		
		String Expected = string.toLowerCase();
		Assert.assertEquals(Expected, searchedProd);
		scn.log("searched result displayed");

	}

	@Then("user click on the searched product and validate the title of the page {string}")
	public void user_click_on_the_searched_product_and_validate_the_title_of_the_page(String string) {
		
		 WebElement searchedProd = driver.findElement(By.xpath("//div[@id='productsList']//ul//li//div[text()='Majestic Solitaire Diamond Ring']"));
		 wait.until(ExpectedConditions.elementToBeClickable(searchedProd));
		 JavascriptExecutor js = ((JavascriptExecutor)driver);
		 js.executeScript("arguments[0].click();", searchedProd);
		// searchedProd.click();
		 Assert.assertEquals(string, driver.getTitle());
		 
		 
	}

	@Then("user select the size and validate it")
	public void user_select_the_size_and_validate_it(String string) {

	}

	@Then("see the notification that {string}")
	public void see_the_notification_that(String string) {

	}

	@After(order = 2)
	public void ScreeshotForFailure(Scenario scn) {
		if (scn.isFailed()) {
			TakesScreenshot scrnShot = (TakesScreenshot) driver;
			byte[] data = scrnShot.getScreenshotAs(OutputType.BYTES);
			scn.attach(data, "image/png", "Failed Step Name: " + scn.getName());
		} else {
			scn.log("Test case is passed, no screen shot captured");
		}
	}

	@After(order = 1)
	public void TearDown() {
		driver.quit();
	}

}
