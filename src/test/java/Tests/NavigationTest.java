package Tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Base.Browser;

public class NavigationTest extends Browser {
	//Property Operations
	@Test
	public void testNavigateToProductsPage() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//waiting for the product button to show and be stable
		WebElement products = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Products']")));
		js.executeScript("arguments[0].scrollIntoView(true);", products);
		products.click();
		//waiting for the product button to show and be stable
		WebElement dropdownToggle = wait.until(ExpectedConditions.elementToBeClickable(By.id("w-dropdown-toggle-0")));
		dropdownToggle.click();
		 
		//waiting for the pospectportal to be active
		WebElement prospectPortal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[text()='ProspectPortal'])[1]")));
		js.executeScript("arguments[0].scrollIntoView(true);", prospectPortal);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='ProspectPortal'])[1]")));
		//click on the pospectportal 		
		js.executeScript("arguments[0].click();", prospectPortal);

		//String actual = driver.findElement(By.xpath("(//*[text()='ProspectPortal'])[1]")).getText();
		String expected = "https://www.entrata.com/products/prospectportal";
		Assert.assertEquals(driver.getCurrentUrl(),expected, "Failed to navigate to Products page");


		   	}

	@Test
	public void testNavigateToSolutionsPage() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		WebElement Solutions = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Solutions'])[1]")));
		js.executeScript("arguments[0].scrollIntoView(true);", Solutions);
		Solutions.click();
		
		WebElement dropdownToggle1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='dropdown-chevron w-embed'])[2]")));
		js.executeScript("arguments[0].scrollIntoView(true);", dropdownToggle1);
		dropdownToggle1.click();
		 
		
		WebElement multifamily = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[text()='Multifamily'])[1]")));
		js.executeScript("arguments[0].scrollIntoView(true);", multifamily);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='Multifamily'])[1]")));
				
		js.executeScript("arguments[0].click();", multifamily);

		String expected = "https://www.entrata.com/solutions/multifamily";
		Assert.assertEquals(driver.getCurrentUrl(),expected, "Failed to navigate to Solutions page");
	}
}