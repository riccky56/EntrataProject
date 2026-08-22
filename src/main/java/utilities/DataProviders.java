package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

// Class declaration that groups the related example logic in one place.
public class DataProviders {

	//DataProvider 1
	
	@DataProvider(name="LoginData")
	// Method definition that performs a specific part of the program workflow.
	public String [][] getData() throws IOException
	{
		String path=".\\testData\\Opencart_LoginData.xlsx";//taking xl file from testData
		
		ExcelUtility xlutil=new ExcelUtility(path);//creating an object for XLUtility
		
		// Initialize a variable that will be used in the logic.
		int totalrows=xlutil.getRowCount("Sheet1");	
		// Initialize a variable that will be used in the logic.
		int totalcols=xlutil.getCellCount("Sheet1",1);
				
		String logindata[][]=new String[totalrows][totalcols];//created for two dimension array which can store the data user and password
		
		for(int i=1;i<=totalrows;i++)  //1   //read the data from xl storing in two deminsional array
		{		
			for(int j=0;j<totalcols;j++)  //0    i is rows j is col
			{
				logindata[i-1][j]= xlutil.getCellData("Sheet1",i, j);  //1,0
			}
		}
	return logindata;//returning two dimension array
				
	}
	
	//DataProvider 2
	
	//DataProvider 3
	
	//DataProvider 4
}
