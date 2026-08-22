package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

// Class declaration that groups the related example logic in one place.
public class HomePage extends BasePage{


	// Method definition that performs a specific part of the program workflow.
	public HomePage(WebDriver driver)
	{
		super(driver);
	}

	@FindBy(xpath="//span[normalize-space()='My Account']") 
	WebElement lnkMyaccount;

	@FindBy(xpath="//a[normalize-space()='Register']") 
	WebElement lnkRegister;

	@FindBy(linkText = "Login")   // Login link added in step5
	WebElement linkLogin;


	// Method definition that performs a specific part of the program workflow.
	public void clickMyAccount()
	{
		// Click the target element to continue the workflow.
		lnkMyaccount.click();
	}

	// Method definition that performs a specific part of the program workflow.
	public void clickRegister()
	{
		// Click the target element to continue the workflow.
		lnkRegister.click();
	}

	// Method definition that performs a specific part of the program workflow.
	public void clickLogin()
	{
		// Click the target element to continue the workflow.
		linkLogin.click();
	}

}
