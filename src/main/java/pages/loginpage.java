package pages;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// Class declaration that groups the related example logic in one place.
public class loginpage {	
	
  
     
   // Method definition that performs a specific part of the program workflow.
   public loginpage(WebDriver driver)
   {
	    PageFactory.initElements(driver, this);
   }
	 

	 @FindBy(id="user-name")
	 WebElement usernameinput;
	 
	 
	 @FindBy(id="password")
	 WebElement passwordinput;
	 
	 @FindBy(xpath="//*[@id='login-button']")
	 WebElement loginbutton;

	 
	
	 // Method definition that performs a specific part of the program workflow.
	 public void enterusername(String username)
	 {
		 usernameinput.clear();
		 // Type the required value into the target input field.
		 usernameinput.sendKeys(username);
	 }

	 // Method definition that performs a specific part of the program workflow.
	 public void enterpassword(String password)
	 {
		 passwordinput.clear();
		 // Type the required value into the target input field.
		 passwordinput.sendKeys(password);
	 }
	 // Method definition that performs a specific part of the program workflow.
	 public void loginbutton() 
	 {
		 // Click the target element to continue the workflow.
		 loginbutton.click();
	 }
	 
	 //public void loginAs(String username, String password) {
		 
		// enterusername(username);
		 //enterpassword(password);
		 //loginbutton();
		 
	 //}
		
	}
	 
	 

