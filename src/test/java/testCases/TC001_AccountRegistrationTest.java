package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

// 4.Create TC001_AccountRegistrationTest


public class TC001_AccountRegistrationTest  extends BaseClass {
  
	@Test(groups="Regression")
	public void verify_account_registration(){
		
		try {
		logger.info("***** Starting   TC001 AccountRegistrationTest  *****");
		HomePage hp= new HomePage(driver); 
		
		hp.clickMyAccount();
		hp.clickregister();
		
		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
		
		regpage.setFirstName(randomeString().toUpperCase());
		regpage.setLastName(randomeString().toUpperCase());
		regpage.setEmail(randomeString()+"@gmail.com");
		regpage.setTelephone(randomeNumber());
		
		String password=randomAlphaNumeric();
		
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		
		logger.info("Validating Expected message");
		String confmsg =regpage.getConfirmationMsg();
		
		Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		}
		catch(Exception e){
			logger.error("Test Failed....");
			logger.debug("Debug logs..");
			Assert.fail();
		}
		logger.info("***** Finished   TC001 AccountRegistrationTest  *****");
	}
	
	
}
