package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
//	Data Provider 1
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException{
		
		String path=".\\testData\\Opencart_loginData.xlsx";//taking xl file from testData
		
		ExcelUtility xlutil=new ExcelUtility(path);//create an object for XLUtility
		
		int totalrows=xlutil.getRowCount("Sheet1");
		int totalcols=xlutil.getCellCount("Sheet1", 1);
		
		String loginData[][]=new String [totalrows][totalcols];//Craeted for two dimension array which can store data
		
		for(int i=1;i<=totalrows ;i++) {
			for(int j=0;j<totalrows ;j++) {
				loginData[i][j]=xlutil.getCellData("Sheet1", i, j); // 1 ,0
			
			}
		}
		System.out.println(loginData);
		return loginData;//returning two dimension array
		
	}

}
