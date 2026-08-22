
package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;

// Class declaration that groups the related example logic in one place.
public class Browser2 {


	public static WebDriver driver;

    
	// Method definition that performs a specific part of the program workflow.
	public static void startBrowser(){
		driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}
}

