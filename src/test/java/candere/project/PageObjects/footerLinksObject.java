package candere.project.PageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class footerLinksObject {

private static final Logger logger = LogManager.getLogger(footerLinksObject.class);
	
	WebDriver driver;
	WebDriverWait wait;
	
	
	public footerLinksObject(WebDriver driver, WebDriverWait wait){
		this.driver=driver;
	    this.wait=wait;
	}
	
}
