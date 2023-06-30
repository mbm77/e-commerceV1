package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;

public class SearchTest extends Base {
	public WebDriver driver;
	
	public SearchTest() {
		super();
	}

	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifySearchWithValidProduct() {
		HomePage homePage = new HomePage(driver);
		homePage.enterProduct(dataProp.getProperty("validProduct"));
		homePage.searchProduct();
		
		Assert.assertTrue(homePage.statusOfProductAvailabilityText(),"Valid product HP is not displayed in search results.");
	}
	
	@Test(priority = 2)
	public void verifySearchWithInvalidProduct() {
		HomePage homePage = new HomePage(driver);
		homePage.enterProduct(dataProp.getProperty("invalidProduct"));
		homePage.searchProduct();
		String actualSearchMessage = homePage.retrieveNoProductAvailableText();
		Assert.assertEquals(actualSearchMessage, "There is no product that matches the search criteria.","No product message in search results is not displayed.");
		
		//dataProp.getProperty("noProductTextInSearchResults");
	}
	
	@Test(priority = 3, dependsOnMethods = {"verifySearchWithValidProduct", "verifySearchWithInvalidProduct"})
	public void verifySearchWithoutAnyProduct() {
		HomePage homePage = new HomePage(driver);
		homePage.searchProduct();
		String actualSearchMessage = homePage.retrieveNoProductAvailableText();
		Assert.assertEquals(actualSearchMessage, dataProp.getProperty("noProductTextInSearchResults"),"No product message in search results is not displayed.");
		
		
	}
	
	
	

}
