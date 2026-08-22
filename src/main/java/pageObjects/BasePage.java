package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

// Class declaration that groups the related example logic in one place.
public class BasePage {

	WebDriver driver;
	
	// Method definition that performs a specific part of the program workflow.
	public BasePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
}
