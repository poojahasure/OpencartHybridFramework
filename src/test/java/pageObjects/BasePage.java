package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

//2.Create BasePage

public class BasePage {
  WebDriver driver;
  
  public BasePage( WebDriver driver) {
	  this.driver=driver;
	  PageFactory.initElements(driver, this);
  }
}
