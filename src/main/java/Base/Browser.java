package Base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


// Class declaration that groups the related example logic in one place.
public class Browser {

	public static WebDriver driver;
	public static WebDriverWait wait;
	
	@BeforeMethod
	// Method definition that performs a specific part of the program workflow.
	public static void startBrowser() {
	driver = new ChromeDriver();
	
	wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	
	//maximize and get to the url
	driver.manage().window().maximize();
    driver.get("https://www.entrata.com/");
    
    //to close the window popup
    driver.findElement(By.xpath("(//*[@role='presentation'])[2]")).click();
	}
	

    @AfterMethod
    // Method definition that performs a specific part of the program workflow.
    public void tearDown() {
        driver.quit();
    }

}
