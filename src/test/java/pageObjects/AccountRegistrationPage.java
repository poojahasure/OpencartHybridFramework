package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

//3.Create AccountRegistrationPage
public class AccountRegistrationPage extends BasePage {
 WebDriver driver;
	 
	 public AccountRegistrationPage( WebDriver driver) {
		 super(driver);
	
		 
     }
	 @FindBy(xpath="//input[@id='input-firstname']")
		WebElement txtFirstName;
	 @FindBy(xpath="//input[@id='input-lastname']")
		WebElement txtLastName;
	 @FindBy(xpath="//input[@id='input-email']")
		WebElement txtEmail;
	 @FindBy(xpath="//input[@id='input-telephone']")
		WebElement txtTelephone;
	 @FindBy(xpath="//input[@id='input-password']")
		WebElement txtPassword;
	 @FindBy(xpath="//input[@id='input-confirm']")
		WebElement txtConfirmPassword;
	 @FindBy(xpath="//input[@name='agree']")
		WebElement checkedPolicy;
	 @FindBy(xpath="//input[@value='Continue']")
		WebElement btnContinue;
	 @FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
		WebElement msgConfirmation;
	 
	 public void setFirstName(String fname) {
		 txtFirstName.sendKeys(fname);
	 }
	 public void setLastName(String lname) {
		 txtLastName.sendKeys(lname);
	 }
	 
	 public void setEmail(String email) {
		 txtEmail.sendKeys(email);
	 }
	 
	 public void setTelephone(String tel) {
		 txtTelephone.sendKeys(tel);
	 }
	 
	 public void setPassword(String password) {
		 txtPassword.sendKeys(password);
	 }
	 public void setConfirmPassword(String confirmpassword) {
		 txtConfirmPassword.sendKeys(confirmpassword);
	 }
	 
	 public void setPrivacyPolicy() {
		 checkedPolicy.click();
	 }
	 public void clickContinue() {
		 btnContinue.click();
//	1	 btnContinue.submit();
//	2	 Actions act=new Actions(driver);
//       act.moveToElement(btnContinue).click().perform();
//		 3. JavascriptExecutor js=(JavascriptExecutor)driver;
//		 js.executeScript("arguments[0].click();",btnContinue)
//		 4.btnContinue.sendKeys(keys.RETURN);
	 }
	 
	 public String getConfirmationMsg() {
		 try {
			 return (msgConfirmation.getText());
		 }catch(Exception e) {
			 return (e.getMessage());
		 }
		 
	 }
	 
}