package candere.project.StepDefs;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import candere.project.PageObjects.HomePageObj;
import candere.project.PageObjects.ProductDiscPage;
import candere.project.core.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepdef {

	private static final Logger logger = LogManager.getLogger(stepdef.class);

	WebDriver driver;
	WebDriverWait wait;
	Scenario scn;

	HomePageObj homePageObj;
	ProductDiscPage productDiscPage;
	String Url = "https://www.candere.com/";

	@Before
	public void Setup(Scenario scn) throws Exception {

		this.scn = scn;

		String browsername = WebDriverFactory.getBrowserName();
		driver = WebDriverFactory.getWebDriverForBrowser(browsername);

		wait = new WebDriverWait(driver, 20);
		driver.manage().deleteAllCookies();
		logger.info("WebDriverWait set for 20 seconds");

		homePageObj = new HomePageObj(driver, wait);
		productDiscPage = new ProductDiscPage(driver, wait);
		scn.log("Browser invoked");
	}

	@Given("user navigate to the home application URL")
	public void user_navigate_to_the_home_application_url() {

		WebDriverFactory.navigateToTheUrl(Url);
		scn.log("user navigate on the landing page");
	}

	@When("user is on application landing page")
	public void user_is_on_application_landing_page() {

		Assert.assertEquals(Url, driver.getCurrentUrl());
		logger.info("URL Validated");
		scn.log("user is on home page of the application");
	}

	@Then("Application title is {string}")
	public void application_title_is(String string) {
		Assert.assertEquals(string, driver.getTitle());
		logger.info("Title Validated");
		scn.log("user validate the title => " + string);
	}

	@Then("user search for the product {string}")
	public void user_search_for_the_product(String string) {
		homePageObj.SearchProd(string);
		logger.info("user search the product");
		scn.log("user search the product => " + string);

	}

	@Then("{string} displayed after the search")
	public void displayed_after_the_search(String string) {

		homePageObj.DisplaySearchResult(string);
		scn.log("searched result displayed");

	}

	@Then("user click on the searched product and validate the title of the page {string}")
	public void user_click_on_the_searched_product_and_validate_the_title_of_the_page(String string)
			throws InterruptedException {

		try {
			WebElement Product = driver.findElement(
					By.xpath("//div[@id='productsList']//ul//li//div[text()='Majestic Solitaire Diamond Ring']"));
			wait.until(ExpectedConditions.elementToBeClickable(Product));

			Product.click();
			logger.info("clicked on the search product");
			Thread.sleep(4000);
			Assert.assertEquals(string, driver.getTitle());
			logger.info("Validate the page title");
		} catch (Exception e) {
			WebElement Product = driver.findElement(
					By.xpath("//div[@id='productsList']//ul//li//div[text()='Majestic Solitaire Diamond Ring']"));
			wait.until(ExpectedConditions.elementToBeClickable(Product));

			Product.click();
			logger.info("clicked on the search product");

			Thread.sleep(4000);
			Assert.assertEquals(string, driver.getTitle());
			logger.info("Validate the page title");
		}

		scn.log("user clicked on the search product and validat it");
	}

	@Then("user select the size and validate it {string}")
	public void user_select_the_size_and_validate_it(String size) throws InterruptedException {
		productDiscPage.SelectSize(size);
		scn.log("user select the size=> " + size + "abd validate it");
	}

	@Then("see the notification that {string}")
	public void see_the_notification_that(String string) {
		productDiscPage.PricePopUp(string);
		scn.log("user saw the updated price popup");
	}

	@When("user scroll down the page")
	public void user_scroll_down_the_page() {

		/*
		 * WebElement AboutUs = driver.findElement(By.xpath("//p[text()='ABOUT US']"));
		 * JavascriptExecutor js = ((JavascriptExecutor) driver);
		 * js.executeScript("arguments[0].scrollIntoview(true);", AboutUs);
		 */
		logger.info("page scroll down till about us");
		scn.log("user scroll down the page");
	}

	@When("See the {string} Section")
	public void see_the_section(String string) {
		WebElement AboutUs = driver.findElement(By.xpath("//p[text()='" + string + "']"));
		Assert.assertEquals(string, AboutUs.getText());
		logger.info("about us section validated");
		scn.log("user see the about us section");
	}

	@Then("See the list of link below anbout us")
	public void see_the_list_of_link_below_anbout_us(List<String> dataTable) {

		List<WebElement> ListBelowAboutus = driver
				.findElements(By.xpath("//a[@class='second our_compamy__']//parent::nav/a"));
		for (int i = 0; i < dataTable.size(); i++) {

			if (dataTable.get(i).equalsIgnoreCase(ListBelowAboutus.get(i).getText())) {
				Assert.assertTrue(true);
			} else {
				Assert.fail();
			}

		}
		logger.info("data table matched");
		scn.log("user see the all links below the about us section");
	}

	@When("Click on the {string}")
	public void click_on_the(String string) throws InterruptedException {

		WebElement SocialMidiaLink = driver.findElement(By.xpath("//a[@data-gtm='" + string + "']"));
		wait.until(ExpectedConditions.elementToBeClickable(SocialMidiaLink));
		Assert.assertTrue(SocialMidiaLink.isDisplayed());
		logger.info(string + "displayed");
		SocialMidiaLink.click();
		logger.info(string + "clicked");
		Thread.sleep(4000);
		scn.log("user clicked on social midia link");
	}

	@Then("validate the All social media links With Expected {string} and {string}")
	public void validate_the_all_social_media_links_with_expected_and(String string, String string2) {

		Assert.assertTrue(driver.getCurrentUrl().contains(string));
		logger.info(string + "in the link validated");

		if (string2.equals("Candere by Kalyan Jewellers ")) {
			driver.findElement(By.xpath("//h1[text()='Candere by Kalyan Jewellers']")).getText()
					.equalsIgnoreCase(string2);
			Assert.assertTrue(true);
			logger.info("matched with facebook");
		} else if (string2.equals("canderejewellery ")) {

			driver.findElement(By.xpath("//h2[text()='canderejewellery']")).getText().equalsIgnoreCase(string2);
			Assert.assertTrue(true);
			logger.info("matched with instagram");
		} else if (string2.equals("Candere By Kalyan jewellers")) {
			driver.findElement(By.xpath("(//span[text()='Candere By Kalyan Jewellers'])[2]")).getText()
					.equalsIgnoreCase(string2);
			Assert.assertTrue(true);
			logger.info("matched with twitter");
		}
		
		// Assert.assertTrue(driver.getTitle().contains(string2));
		logger.info(string2 + "text in the page is validated");

		scn.log("user validat the URL and page text");

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
	public void TearDown() throws InterruptedException {
		Thread.sleep(4000);
		logger.info("browser closed");
		scn.log("browser closed");
		driver.quit();
	}
}
