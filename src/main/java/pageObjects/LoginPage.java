package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

// Class declaration that groups the related example logic in one place.
public class LoginPage extends BasePage {

	// Method definition that performs a specific part of the program workflow.
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtEmailAddress;

	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtPassword;

	@FindBy(xpath = "//input[@value='Login']")
	WebElement btnLogin;


	// Method definition that performs a specific part of the program workflow.
	public void setEmail(String email) {
		// Type the required value into the target input field.
		txtEmailAddress.sendKeys(email);
	}

	// Method definition that performs a specific part of the program workflow.
	public void setPassword(String pwd) {
		// Type the required value into the target input field.
		txtPassword.sendKeys(pwd);
	}

	// Method definition that performs a specific part of the program workflow.
	public void clickLogin() {
		// Click the target element to continue the workflow.
		btnLogin.click();
	}

	
	
	
}
