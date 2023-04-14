package candere.project.PageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class HomePageObj {
	
	private static final Logger logger = LogManager.getLogger(HomePageObj.class);
	
	WebDriver driver;
	WebDriverWait wait;
	
	
	public HomePageObj(WebDriver driver, WebDriverWait wait){
		this.driver=driver;
	    this.wait=wait;
	}
	
	private By SearchBox =By.id("search");
	//WebElement SearchBox = driver.findElement(By.id("search"));
	
	public void SearchProd(String string) {
		
		wait.until(ExpectedConditions.elementToBeClickable(SearchBox));
		driver.findElement(SearchBox).sendKeys(string);
		logger.info("user send the keys into search box");
		
	}
	
	public void DisplaySearchResult(String string) {
		
		try{WebElement searchedProd = driver.findElement(By.xpath("//div[text()='Majestic Solitaire Diamond Ring']"));
		// String actual = searchedProd.getText().toLowerCase();
		// String Expected = string.toLowerCase();
		// Assert.assertEquals(Expected, actual);
	    Assert.assertTrue(searchedProd.getText().contains(string));
		logger.info("search product displayed");}
		
		catch(Exception e) {
			WebElement searchedProd = driver.findElement(By.xpath("//div[text()='Majestic Solitaire Diamond Ring']"));
			
		    Assert.assertTrue(searchedProd.getText().contains(string));
		}
		
	}

	public void ClickOnSearchProd() {
		
	}
	

}
