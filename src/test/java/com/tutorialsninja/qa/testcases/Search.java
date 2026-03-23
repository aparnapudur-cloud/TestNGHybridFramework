package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pageobjects.HomePage;
import com.tutorialsninja.qa.pageobjects.SearchPage;

public class Search extends Base{
	
	public Search() {
		
		super();
	}
	
	public WebDriver driver;
	SearchPage searchPage;
	
	@BeforeMethod
	public void setup() {
		
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
	}
	
	@Test(priority=1)
	public void verifySearchWithValidProduct() {
		
		HomePage homePage = new HomePage(driver);
		homePage.enterItemIntoSearchBoxField(data.getProperty("validProduct"));
		homePage.clickOnSearchButton();
		
		searchPage = new SearchPage(driver);
		
		Assert.assertTrue(searchPage.displayStatusOfHPValidProduct());
		
	}
	
	@Test(priority=2)
	public void verifySearchWithInvalidProduct() {
		
		HomePage homePage = new HomePage(driver);
		homePage.enterItemIntoSearchBoxField(data.getProperty("invalidProduct"));
		homePage.clickOnSearchButton();
	
		searchPage = new SearchPage(driver);
		
		String actualSearchMessage = searchPage.retrieveNoProductMessageText();
		Assert.assertEquals(actualSearchMessage, data.getProperty("noProductTextInSearch"));
	}
	
	@Test(priority=3)
	public void verifySearchWithoutAnyProduct() {
		
		HomePage homePage = new HomePage(driver);
		homePage.clickOnSearchButton();
		
		searchPage = new SearchPage(driver);
		
		String actualSearchMessage = searchPage.retrieveNoProductMessageText();
		Assert.assertEquals(actualSearchMessage, data.getProperty("noProductTextInSearch"));
		
	}

	@AfterMethod
	public void tearDown() throws InterruptedException {
		
		Thread.sleep(1000);
		driver.quit();
	}
}
