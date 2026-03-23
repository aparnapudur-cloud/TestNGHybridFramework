package com.tutorialsninja.qa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountSuccessPage {
	
	WebDriver driver;
	
	public AccountSuccessPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//h1[text()='Your Account Has Been Created!']")
	private WebElement accountAuccessMessage;
	
	public String retieveAccountSuccessPageHeading() {
		
		String accountSuccessMessageText = accountAuccessMessage.getText();
		return accountSuccessMessageText;
	}

}
