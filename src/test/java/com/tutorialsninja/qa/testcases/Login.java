package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pageobjects.AccountPage;
import com.tutorialsninja.qa.pageobjects.HomePage;
import com.tutorialsninja.qa.pageobjects.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;

public class Login extends Base{
	
	public Login() {
		
		super();	//Will call base class constructor that is Base()
	}
	
	public WebDriver driver;
	LoginPage login;
	
	@BeforeMethod
	public void setup() {
		
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));	//Inheritance
		
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccountDropMenu();
		login = homePage.clickOnLoginOption();
		
	}
	
	@Test(priority=1, dataProvider="validCredentialsSupplier")
	public void verifyLoginWithValidCredentials(String email, String password) {
		

		login.enterEmailAddress(email);
		login.enterPassword(password);
		login.clickOnLoginButton();
		
		AccountPage account = new AccountPage(driver);
		account.getDisplayStatusOfEditYourAccountInformation();
	}
	
	@DataProvider(name="validCredentialsSupplier")
	public Object[][] supplyTestData() {
		
		Object[][] data = Utilities.getTestDataFromExcel("Login");
		return data;
		
	}

	@Test(priority=2)
	public void verifyLoginWithInvalidCredentials() {
		
		login.enterEmailAddress(Utilities.generateNewEmail());
		login.enterPassword(data.getProperty("invalidPassword"));
		login.clickOnLoginButton();
		
		String actualWarning = login.retrieveEmailPasswordNotMatchingWarning();
		String expectedWarning = data.getProperty("emailPasswordNoMatchWarning");
		
		Assert.assertEquals(actualWarning, expectedWarning);
	}
	
	@Test(priority=3)
	public void verifyLoginWithInvalidEmailAndValidPassword() {
		
		login.enterEmailAddress(Utilities.generateNewEmail());
		login.enterPassword(prop.getProperty("validPassword"));
		login.clickOnLoginButton();
		
		String actualWarning = login.retrieveEmailPasswordNotMatchingWarning();
		String expectedWarning = data.getProperty("emailPasswordNoMatchWarning");
		
		Assert.assertEquals(actualWarning, expectedWarning);
		
	}
	
	@Test(priority=4)
	public void verifyLoginWithValidEmailAndInvalidPassword() {
		
		login.enterEmailAddress(prop.getProperty("validUsername"));
		login.enterPassword(data.getProperty("invalidPassword"));
		login.clickOnLoginButton();
		
		String actualWarning = login.retrieveEmailPasswordNotMatchingWarning();
		String expectedWarning = data.getProperty("emailPasswordNoMatchWarning");
		
		Assert.assertEquals(actualWarning, expectedWarning);
		
	}
	
	@Test(priority=5)
	public void verifyLoginWithEmptyCredentials() {
		
		login.clickOnLoginButton();
	
		String actualWarning = login.retrieveEmailPasswordNotMatchingWarning();
		String expectedWarning = data.getProperty("emailPasswordNoMatchWarning");
		
		Assert.assertEquals(actualWarning, expectedWarning);
		
	}
	
	@AfterMethod
	public void tearDown() throws InterruptedException {
		
		Thread.sleep(1000);
		driver.quit();
	}
}
