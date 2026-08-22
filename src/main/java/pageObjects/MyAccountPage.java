package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

// Class declaration that groups the related example logic in one place.
public class MyAccountPage extends BasePage{

	// Method definition that performs a specific part of the program workflow.
	public MyAccountPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//h2[text()='My Account']") // MyAccount Page heading
	WebElement msgHeading;
	
	
	@FindBy(xpath = "//div[@class='list-group']//a[text()='Logout']")   //added in step6
	WebElement lnkLogout;
	
	
	// Method definition that performs a specific part of the program workflow.
	public boolean isMyAccountPageExists()
	{
		try
		{
		// Return the final result back to the caller.
		return (msgHeading.isDisplayed());
		}
		catch(Exception e)
		{
			// Return the final result back to the caller.
			return false;
		}
	}
	
	// Method definition that performs a specific part of the program workflow.
	public void clickLogout()
	{
		// Click the target element to continue the workflow.
		lnkLogout.click();
	}
	
	
	
	
	
	
}
