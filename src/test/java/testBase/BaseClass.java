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
import org.apache.logging.log4j.LogManager;//Log4j
import org.apache.logging.log4j.Logger;//Log4j
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

//5. Create BaseClass
 
public class BaseClass {
	
	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	  
	@BeforeClass(groups= {"Sanity","Master","Regression"})
	@Parameters({"os","browser"})
	public void setUp(String os,String br) throws IOException {
		
      //Loading config.properties file	
		FileReader file =new FileReader("./src//test//resources//config.properties");
		p= new Properties();
		p.load(file);
		
      //logger	
	  logger=LogManager.getLogger(this.getClass());
		
	  if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
		  DesiredCapabilities capabilities=new  DesiredCapabilities();
		  
          //os
		  if(os.equalsIgnoreCase("windows")) {
			  capabilities.setPlatform(Platform.WIN11);
		  }
		  else {
			  System.out.println("No matching os");
			  return;
		  }
          //browser		 
		  
		  switch(br.toLowerCase())
		  {
		  case "chrome": capabilities.setBrowserName("chrome");break;
		  case "edge":capabilities.setBrowserName("MicrosoftEdge");break;
		 
		  default : System.out.println("Invalid browser name ...");return;
		  
		  }
		driver =new RemoteWebDriver(new URL ("http://localhost:4444/wd/hub"),capabilities);
	  }
	  
	  
	  
	  if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
		  switch(br.toLowerCase()) {
		  case "chrome":  driver=new ChromeDriver();break;
		  case "edge":  driver=new EdgeDriver();break;
		  case "firefox":  driver=new FirefoxDriver();break;
		  default : System.out.println("Invalid browser name ...");return; 
	  }
	  
	  
	 
	  
	  }
	
	  
	  driver.manage().deleteAllCookies();
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	  
//	  driver.get("https://tutorialsninja.com/demo/");
	  driver.get(p.getProperty("appURL"));//reading url from properties file
	  driver.manage().window().maximize();
    }
	
	@AfterClass(groups= {"Sanity","Master","Regression"})
	public void tearDown() {
		driver.quit();
	}
	public String randomeString() {
		@SuppressWarnings("deprecation")
		String generatedString=RandomStringUtils.randomAlphanumeric(5);
		return generatedString;
	}
	
	public String randomeNumber() {
		String generatedNumber=RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}
	
	public String randomAlphaNumeric() {
		String generatedString=RandomStringUtils.randomAlphanumeric(5);
		String generatedNumber=RandomStringUtils.randomNumeric(10);
		return (generatedString+"@"+generatedNumber);
	}
	
	public String captureScreen(String tname)throws IOException {
		String timestamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		TakesScreenshot takesScreenshot =(TakesScreenshot)driver;
		File sourceFile=takesScreenshot.getScreenshotAs(OutputType.FILE);
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" +tname +"_"+timestamp;
		
		File targetFile = new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		return targetFilePath ;
		
	}
}
