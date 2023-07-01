package com.tutorialsninja.qa.testcases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;

//This LoginTest class comment
public class LoginTest extends Base{
	WebDriver driver;
	LoginPage loginPage;
	
	public LoginTest() {
		super();
	}

	@BeforeMethod
	public void setup() throws InterruptedException {
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		loginPage = homePage.selectLoginOption();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1, dataProvider="validCredentialsSupplier")
	public void verifyLoginWithValidCredentials(String email, String password) {
		
		loginPage.enterEmailAddress(email);
		loginPage.enterPassword(password);
		AccountPage accountPage = loginPage.clickOnLoginButton();
		
		
		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInfo());

	}
	
	@DataProvider(name="validCredentialsSupplier")
	public Object[][] supplyTestData() throws IOException{
		Object[][] data = Utilities.getTestDataFromExcel("Login");
		return data;
	}

	@Test(priority = 2)
	public void verifyLoginWithInvalidCredentials() {
		loginPage.enterEmailAddress(Utilities.generateEmailWithTimestamp());
		loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginPage.clickOnLoginButton();
		
		String actualWarningMessage = loginPage.retrieveEmailpasswordNotMatchingwarningmessageText();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchingWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));

	}

	@Test(priority = 3)
	public void verifyLoginWithInvalidEmailAndValidPassword() {
		loginPage.enterEmailAddress(Utilities.generateEmailWithTimestamp());
		loginPage.enterPassword(dataProp.getProperty("validPassword"));
		loginPage.clickOnLoginButton();
		String actualWarningMessage = loginPage.retrieveEmailpasswordNotMatchingwarningmessageText();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchingWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));

	}

	@Test(priority = 4)
	public void verifyLoginWithValidEmailAndInvalidPassword() {
		
		loginPage.enterEmailAddress(dataProp.getProperty("validEmail"));
		loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginPage.clickOnLoginButton();
		
		String actualWarningMessage = loginPage.retrieveEmailpasswordNotMatchingwarningmessageText();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchingWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));

	}

	@Test(priority = 5)
	public void verifyLoginWithoutProvidingCredentials() {
		loginPage.clickOnLoginButton();
		String actualWarningMessage = loginPage.retrieveEmailpasswordNotMatchingwarningmessageText();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchingWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));

	}

}
