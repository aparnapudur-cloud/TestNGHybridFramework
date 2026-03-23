package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pageobjects.AccountSuccessPage;
import com.tutorialsninja.qa.pageobjects.HomePage;
import com.tutorialsninja.qa.pageobjects.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;

public class Register extends Base{
	
	public Register() {
		
		super();
	}
	
	public WebDriver driver;
	RegisterPage registerPage;
	AccountSuccessPage accountSuccess;
	
	@BeforeMethod
	public void setup() {
		
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
		
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccountDropMenu();
		registerPage = homePage.clickOnRegisterOption();
	}

	@Test(priority=1)
	public void verifyRegisteringAccountWithMandatoryField() {
		
		registerPage.enterFirstNameInField(data.getProperty("firstName"));
		registerPage.enterLastNameInField(data.getProperty("lastName"));
		registerPage.enterEmailAddressInField(Utilities.generateNewEmail());
		registerPage.enterTelephoneInField(data.getProperty("telephone"));
		registerPage.enterPasswordInField(prop.getProperty("validPassword"));
		registerPage.enterConfirmPasswordInField(prop.getProperty("validPassword"));
		registerPage.clickOnPrivacyPolicy();
		registerPage.clickOnContinue();
		
		accountSuccess = new AccountSuccessPage(driver);
		
		String actualSuccessMessage = accountSuccess.retieveAccountSuccessPageHeading();
		String expectedSuccessMessage = data.getProperty("accountSuccessfullyCreated");
		
		Assert.assertEquals(actualSuccessMessage, expectedSuccessMessage);	
	}
	
	@Test(priority=2)
	public void verifyRegisteringAccountByProvidingAllFields() {
		
		registerPage.enterFirstNameInField(data.getProperty("firstName"));
		registerPage.enterLastNameInField(data.getProperty("lastName"));
		registerPage.enterEmailAddressInField(Utilities.generateNewEmail());
		registerPage.enterTelephoneInField(data.getProperty("telephone"));
		registerPage.enterPasswordInField(prop.getProperty("validPassword"));
		registerPage.enterConfirmPasswordInField(prop.getProperty("validPassword"));
		registerPage.selectNewsLetter();
		registerPage.clickOnPrivacyPolicy();
		registerPage.clickOnContinue();
		
		accountSuccess = new AccountSuccessPage(driver);
		
		String actualSuccessMessage = accountSuccess.retieveAccountSuccessPageHeading();
		String expectedSuccessMessage = data.getProperty("accountSuccessfullyCreated");
		
		Assert.assertEquals(actualSuccessMessage, expectedSuccessMessage);	
	}

	@Test(priority=3)
	public void verifyRegisteringAccountWithExistingEmailAddress() {
		
		registerPage.enterFirstNameInField(data.getProperty("firstName"));
		registerPage.enterLastNameInField(data.getProperty("lastName"));
		registerPage.enterEmailAddressInField(prop.getProperty("validUsername"));
		registerPage.enterTelephoneInField(data.getProperty("telephone"));
		registerPage.enterPasswordInField(prop.getProperty("validPassword"));
		registerPage.enterConfirmPasswordInField(prop.getProperty("validPassword"));
		registerPage.selectNewsLetter();
		registerPage.clickOnPrivacyPolicy();
		registerPage.clickOnContinue();
		
		
		String actualSuccessMessage = registerPage.retrieveDuplicateWarning();
		String expectedSuccessMessage = data.getProperty("duplicateEmailWarning");
		
		Assert.assertEquals(actualSuccessMessage, expectedSuccessMessage);	
		
	}
	
	@Test(priority=4)
	public void verifyRegisteringAccountWithoutFilingAnyDetails() {
		
		registerPage.clickOnContinue();
		
		String privacyPolicyWarning = registerPage.retrievePrivacyPolicyWarning();
		Assert.assertEquals(privacyPolicyWarning, data.getProperty("privacyPolicyWarning"));
		
		String firstName = registerPage.retrieveFirstNameWarning();
		Assert.assertEquals(firstName, data.getProperty("firstNameWarning"));
		
		String lastName = registerPage.retrieveLastNameWarning();
		Assert.assertEquals(lastName, data.getProperty("lastNameWarning"));
		
		String email = registerPage.retieveEmailWarning();
		Assert.assertEquals(email, data.getProperty("emailWarning"));
		
		String telephone = registerPage.retrieveTelephoneWarning();
		Assert.assertEquals(telephone, data.getProperty("telephoneWarning"));
		
		String password = registerPage.retrievePasswordWarning();
		Assert.assertEquals(password, data.getProperty("passwordWarning"));
		
	}
	
	@AfterMethod
	public void tearDown() throws InterruptedException {
		
		Thread.sleep(1000);
		driver.quit();
	}
}
