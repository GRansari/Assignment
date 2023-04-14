package candere.project.PageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductDiscPage {

	
private static final Logger logger = LogManager.getLogger(ProductDiscPage.class);
	
	WebDriver driver;
	WebDriverWait wait;
	
	
	public ProductDiscPage(WebDriver driver, WebDriverWait wait){
		this.driver=driver;
	    this.wait=wait;
	}
	
	private By SelectSize =By.xpath("//select[@id='mt_size']");
	private By UpdatedPrice =By.xpath("//div[@class='show']");
	
	public void SelectSize(String size) throws InterruptedException {
		WebElement sizetn = driver.findElement(SelectSize);
		Select select = new Select(sizetn);
		select.selectByVisibleText("8");
		logger.info("size of product selected");
		Thread.sleep(3000);
		WebElement ValidateSize = driver.findElement(By.xpath("//select[@id='mt_size']/option[@selected='selected']"));
		Assert.assertEquals(size, ValidateSize.getText());
		logger.info("Validate the size of product");
	}
	public void PricePopUp(String string) {
		WebElement updatedPrice = driver.findElement(UpdatedPrice);
		logger.info("product price updated");
		Assert.assertEquals(string, updatedPrice.getText());
	}
	
	
}
