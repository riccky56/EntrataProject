package pages;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class loginpage {	
	
  
     
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

	 
	
	 public void enterusername(String username)
	 {
		 usernameinput.clear();
		 usernameinput.sendKeys(username);
	 }

	 public void enterpassword(String password)
	 {
		 passwordinput.clear();
		 passwordinput.sendKeys(password);
	 }
	 public void loginbutton() 
	 {
		 loginbutton.click();
	 }
	 
	 //public void loginAs(String username, String password) {
		 
		// enterusername(username);
		 //enterpassword(password);
		 //loginbutton();
		 
	 //}
		
	}
	 
	 

