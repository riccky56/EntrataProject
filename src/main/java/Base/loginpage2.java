package Base;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class loginpage2 {	
	
    //constructor 
   public loginpage2(WebDriver driver)
   {
	   
	   //this.driver=driver; use this if not using findby method and using by element=by.xpath(locator)
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
	 
	 

