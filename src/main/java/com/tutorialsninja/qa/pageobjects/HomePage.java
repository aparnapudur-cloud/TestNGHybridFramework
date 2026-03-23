package com.tutorialsninja.qa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this); 	//Initialize the element which are there in home page. We are instructing the page factory to automatically initialize whether the constructor is called automatically initialize
	}
	
	@FindBy(xpath="//span[text()='My Account']")
	private WebElement myAccountDropMenu;
	
	@FindBy(linkText="Login")
	private WebElement loginOption;	//this element will be attached to the linkText="Login" locator	//4:54
	
	@FindBy(linkText="Register")
	private WebElement registerOption;
	
	@FindBy(name="search")
	private WebElement searchBoxField;
	
	@FindBy(xpath="//div[@id='search']//button")
	private WebElement searchButton;
	
	public void clickOnMyAccountDropMenu() {
		
		myAccountDropMenu.click();
	}
	
	public LoginPage clickOnLoginOption() {
		
		loginOption.click();
		return new LoginPage(driver);
	}
	
	public RegisterPage clickOnRegisterOption() {
		
		registerOption.click();
		return new RegisterPage(driver);
	}
	
	public void enterItemIntoSearchBoxField(String searchItem) {
		
		searchBoxField.sendKeys(searchItem);
	}
	
	public void clickOnSearchButton() {
		
		searchButton.click();
	}

}
