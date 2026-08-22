package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


import org.apache.logging.log4j.LogManager;  //Log4j
import org.apache.logging.log4j.Logger;  //Log4j


// Class declaration that groups the related example logic in one place.
public class BaseClass {

public static WebDriver driver;
public Logger logger;  //Log4j
public Properties p;
	
	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	// Method definition that performs a specific part of the program workflow.
	public void setup(String os, String br) throws IOException
	{
		//Loading config.properties file
		FileReader file=new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
				
		logger=LogManager.getLogger(this.getClass());  //lOG4J2
				
		// Check the condition before deciding whether this block should run.
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities=new DesiredCapabilities();
			
			//os
			if(os.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform(Platform.WIN11);
			}
			// Check another condition if the previous condition was false.
			else if(os.equalsIgnoreCase("linux"))
			{
				capabilities.setPlatform(Platform.LINUX);
				
			}
			// Check another condition if the previous condition was false.
			else if (os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			// Execute this block when the earlier conditions do not match.
			else
			{
				// Display information to the console for the user.
				System.out.println("No matching os");
				// Return the final result back to the caller.
				return;
			}
			
			//browser
			switch(br.toLowerCase())
			{
			case "chrome": capabilities.setBrowserName("chrome"); break;
			case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
			case "firefox": capabilities.setBrowserName("firefox"); break;
			default: System.out.println("No matching browser"); return;
			}
			
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
		}
		
				
		// Check the condition before deciding whether this block should run.
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{

			switch(br.toLowerCase())
			{
			case "chrome" : driver=new ChromeDriver(); break;
			case "edge" : driver=new EdgeDriver(); break;
			case "firefox": driver=new FirefoxDriver(); break;
			default : System.out.println("Invalid browser name.."); return;
			}
		}
		
			
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(p.getProperty("appURL2")); // reading url from properties file.
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups= {"Sanity","Regression","Master"})
	// Method definition that performs a specific part of the program workflow.
	public void tearDown()
	{
		driver.quit();
	}
	
	// Method definition that performs a specific part of the program workflow.
	public String randomeString()
	{
		// Store text data that will be processed by the program logic.
		String generatedstring=RandomStringUtils.randomAlphabetic(5);
		// Return the final result back to the caller.
		return generatedstring;
	}
	
	// Method definition that performs a specific part of the program workflow.
	public String randomeNumber()
	{
		// Store text data that will be processed by the program logic.
		String generatednumber=RandomStringUtils.randomNumeric(10);
		// Return the final result back to the caller.
		return generatednumber;
	}
	
	// Method definition that performs a specific part of the program workflow.
	public String randomeAlphaNumberic()
	{
		// Store text data that will be processed by the program logic.
		String generatedstring=RandomStringUtils.randomAlphabetic(3);
		// Store text data that will be processed by the program logic.
		String generatednumber=RandomStringUtils.randomNumeric(3);
		// Return the final result back to the caller.
		return (generatedstring+"@"+generatednumber);
	}
	
	// Method definition that performs a specific part of the program workflow.
	public String captureScreen(String tname) throws IOException {

		// Store text data that will be processed by the program logic.
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		// Store text data that will be processed by the program logic.
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		// Return the final result back to the caller.
		return targetFilePath;

	}
	
	
}
