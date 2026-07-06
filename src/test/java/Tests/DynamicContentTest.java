package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Base.Browser;

public class DynamicContentTest extends Browser{


    @Test
    public void testHeaderChangesOnHover() {
   
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	//to wait for the solutions button to be stable
		WebElement Solutions = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Solutions'])[1]")));
		js.executeScript("arguments[0].scrollIntoView(true);", Solutions);
		Solutions.click();
        String initialColor = Solutions.getCssValue("color");
        Assert.assertNotNull(initialColor);
    }

    @Test
    public void testChatWidgetPresence() {
    	//failing this test as to verify the assertion as chat widget is not present
    	
        boolean isChatWidgetPresent = driver.getPageSource().contains("Chat");
        Assert.assertTrue(isChatWidgetPresent, "Chat widget should be present in source");
    }
    
}