package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

// Class declaration that groups the related example logic in one place.
public class AccountRegistrationPage extends BasePage{

	// Method definition that performs a specific part of the program workflow.
	public AccountRegistrationPage(WebDriver driver)
	{
		super(driver);
	}


	@FindBy(xpath="//input[@id='input-firstname']") 
	WebElement txtFirstname;

	@FindBy(xpath="//input[@id='input-lastname']") 
	WebElement txtLasttname;

	@FindBy(xpath="//input[@id='input-email']") 
	WebElement txtEmail;

	@FindBy(xpath="//input[@id='input-telephone']") 
	WebElement txtTelephone;

	@FindBy(xpath="//input[@id='input-password']") 
	WebElement txtPassword;

	@FindBy(xpath="//input[@id='input-confirm']") 
	WebElement txtConfirmPassword;

	@FindBy(xpath="//input[@name='agree']") 
	WebElement chkdPolicy;

	@FindBy(xpath="//input[@value='Continue']") 
	WebElement btnContinue;

	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;


	// Method definition that performs a specific part of the program workflow.
	public void setFirstName(String fname) {
		// Type the required value into the target input field.
		txtFirstname.sendKeys(fname);

	}

	// Method definition that performs a specific part of the program workflow.
	public void setLastName(String lname) {
		// Type the required value into the target input field.
		txtLasttname.sendKeys(lname);

	}

	// Method definition that performs a specific part of the program workflow.
	public void setEmail(String email) {
		// Type the required value into the target input field.
		txtEmail.sendKeys(email);

	}

	// Method definition that performs a specific part of the program workflow.
	public void setTelephone(String tel) {
		// Type the required value into the target input field.
		txtTelephone.sendKeys(tel);

	}

	// Method definition that performs a specific part of the program workflow.
	public void setPassword(String pwd) {
		// Type the required value into the target input field.
		txtPassword.sendKeys(pwd);

	}

	// Method definition that performs a specific part of the program workflow.
	public void setConfirmPassword(String pwd) {
		// Type the required value into the target input field.
		txtConfirmPassword.sendKeys(pwd);

	}

	// Method definition that performs a specific part of the program workflow.
	public void setPrivacyPolicy() {
		// Click the target element to continue the workflow.
		chkdPolicy.click();

	}

	// Method definition that performs a specific part of the program workflow.
	public void clickContinue() {
		//sol1 
		btnContinue.click();

		//sol2 
		//btnContinue.submit();

		//sol3
		//Actions act=new Actions(driver);
		//act.moveToElement(btnContinue).click().perform();

		//sol4
		//JavascriptExecutor js=(JavascriptExecutor)driver;
		//js.executeScript("arguments[0].click();", btnContinue);

		//Sol 5
		//btnContinue.sendKeys(Keys.RETURN);

		//Sol6  
		//WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(10));
		//mywait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();

	}

	// Method definition that performs a specific part of the program workflow.
	public String getConfirmationMsg() {
		try {
			// Return the final result back to the caller.
			return (msgConfirmation.getText());
		} catch (Exception e) {
			// Return the final result back to the caller.
			return (e.getMessage());

		}

	}
}
