package com.tutorialsninja.qa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	WebDriver driver;
	
	public RegisterPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="input-firstname")
	private WebElement enterFirstName;
	
	@FindBy(id="input-lastname")
	private WebElement enterLastName;
	
	@FindBy(id="input-email")
	private WebElement enterEmail;
	
	@FindBy(id="input-telephone")
	private WebElement enterTelephone;
	
	@FindBy(id="input-password")
	private WebElement enterPassword;
	
	@FindBy(id="input-confirm")
	private WebElement enterConfirmPassword;
	
	@FindBy(name="agree")
	private WebElement agreePrivacyPoilcy;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continueButton;
	
	@FindBy(name="newsletter")
	private WebElement newsLetter;
	
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	private WebElement duplicateEmailWarning;
	
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	private WebElement privacyPolicyWarning;
	
	@FindBy(xpath="//div[text()='First Name must be between 1 and 32 characters!']")
	private WebElement firstNameWarning;
	
	@FindBy(xpath="//div[text()='Last Name must be between 1 and 32 characters!']")
	private WebElement lastNameWarning;
	
	@FindBy(xpath="//div[text()='E-Mail Address does not appear to be valid!']")
	private WebElement emailWarning;
	
	@FindBy(xpath="//div[text()='Telephone must be between 3 and 32 characters!']")
	private WebElement telephoneWarning;
	
	@FindBy(xpath="//div[text()='Password must be between 4 and 20 characters!']")
	private WebElement passwordWarning;
	
	public void enterFirstNameInField(String firstName) {
		
		enterFirstName.sendKeys(firstName);
	}
	
	public void enterLastNameInField(String lastName) {
		
		enterLastName.sendKeys(lastName);
	}
	
	public void enterEmailAddressInField(String emailAddress) {
		
		enterEmail.sendKeys(emailAddress);
	}
	
	public void enterTelephoneInField(String telephone) {
		
		enterTelephone.sendKeys(telephone);
	}
	
	public void enterPasswordInField(String password) {
		
		enterPassword.sendKeys(password);
	}
	
	public void enterConfirmPasswordInField(String confirmPassword) {
		
		enterConfirmPassword.sendKeys(confirmPassword);
	}
	
	public void selectNewsLetter() {
		
		newsLetter.click();
	}
	
	public void clickOnPrivacyPolicy() {
		
		agreePrivacyPoilcy.click();
	}
	
	public void clickOnContinue() {
		
		continueButton.click();
	}
	
	public String retrieveDuplicateWarning() {
		
		String duplicateWarningMessage = duplicateEmailWarning.getText();
		return duplicateWarningMessage;
	}
	
	public String retrievePrivacyPolicyWarning() {
		
		String privacyPolicyWarningMessage = privacyPolicyWarning.getText();
		return privacyPolicyWarningMessage;
	}
	
	public String retrieveFirstNameWarning() {
		
		String firstNameWarningMessage = firstNameWarning.getText();
		return firstNameWarningMessage;
	}
	
	public String retrieveLastNameWarning() {
		
		String lastNameWarningMessage = lastNameWarning.getText();
		return lastNameWarningMessage;
	}
	
	public String retieveEmailWarning() {
		
		String emailWarningMessage = emailWarning.getText();
		return emailWarningMessage;
	}
	
	public String retrieveTelephoneWarning() {
		
		String telephoneWarningMessage = telephoneWarning.getText();
		return telephoneWarningMessage;
	}
	
	public String retrievePasswordWarning() {
		
		String passwordWarningMessage = passwordWarning.getText();
		return passwordWarningMessage;
	}

}
