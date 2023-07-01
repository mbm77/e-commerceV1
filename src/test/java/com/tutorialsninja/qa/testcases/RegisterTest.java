package com.tutorialsninja.qa.testcases;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;
//This is RegisterTest class
public class RegisterTest extends Base {
	WebDriver driver;
	RegisterPage registerPage;
	AccountSuccessPage accSuccessPage;
	public static Logger logger;
	
	public RegisterTest() {
		super();
		BasicConfigurator.configure();
		logger = Logger.getLogger(this.getClass());
		PropertyConfigurator.configure("log4j.properties");
	}

	@BeforeMethod
	public void setup() throws InterruptedException {
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		registerPage = homePage.selectRegisterOption();
		
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifyRegisteringAnAccountWithMandatoryFields() {
		
		logger.info("********** verifyRegisteringAnAccountWithMandatoryFields started *************");
		
		registerPage.enterFirstNameText(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmailAddress(Utilities.generateEmailWithTimestamp());
		registerPage.enterTelephoneNumber(dataProp.getProperty("telephoneNumber"));
		registerPage.enterPassword(dataProp.getProperty("validPassword"));
		registerPage.enterConfirmPassword(dataProp.getProperty("validPassword"));
		registerPage.selectPrivacyPolicy();
		accSuccessPage = registerPage.clickOnContinueButton();
		
		
		String actualMessage = accSuccessPage.retrieveAccountSuccessMessageStatus();
		String expectedMessage = dataProp.getProperty("accountSuccessfullyCreatedHeading");
		Assert.assertEquals(actualMessage, expectedMessage,"Registration Failed");
		
		logger.info("********** verifyRegisteringAnAccountWithMandatoryFields ended *************");
	}

	@Test(priority = 2)
	public void verifyRegisteringAnAccountWithAllFields() {
		
		logger.info("********** verifyRegisteringAnAccountWithAllFields started *************");
		
		registerPage.enterFirstNameText(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmailAddress(Utilities.generateEmailWithTimestamp());
		registerPage.enterTelephoneNumber(dataProp.getProperty("telephoneNumber"));
		registerPage.enterPassword(dataProp.getProperty("validPassword"));
		registerPage.enterConfirmPassword(dataProp.getProperty("validPassword"));
		registerPage.selectNewsLetterOption("no");
		registerPage.selectPrivacyPolicy();
		accSuccessPage = registerPage.clickOnContinueButton();
		
		
		String actualMessage = accSuccessPage.retrieveAccountSuccessMessageStatus();
		String expectedMessage = dataProp.getProperty("accountSuccessfullyCreatedHeading");
		Assert.assertEquals(actualMessage, expectedMessage,"Registration Failed");
		
		logger.info("********** verifyRegisteringAnAccountWithAllFields ended *************");
	}

	@Test(priority = 3)
	public void verifyRegisteringAccountWithExistingEmailAddress() {
		
		logger.info("********** verifyRegisteringAccountWithExistingEmailAddress started *************");
		
		registerPage.enterFirstNameText(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmailAddress(prop.getProperty("validEmail"));
		registerPage.enterTelephoneNumber(dataProp.getProperty("telephoneNumber"));
		registerPage.enterPassword(dataProp.getProperty("validPassword"));
		registerPage.enterConfirmPassword(dataProp.getProperty("validPassword"));
		registerPage.selectNewsLetterOption("yes");
		registerPage.selectPrivacyPolicy();
		registerPage.clickOnContinueButton();
		String actualMessage = registerPage.retrieveEmailAlredyExistWarningMessage();
		String expectedMessage = dataProp.getProperty("duplicateEmailWarning");
		Assert.assertEquals(actualMessage, expectedMessage, "Email already exist message is not displayed");
		
		logger.info("********** verifyRegisteringAccountWithExistingEmailAddress ended *************");
	}

	@Test(priority = 4)
	public void verifyRegisteringAccountWithoutFillingAnyDetails() {
		
		logger.info("********** verifyRegisteringAccountWithoutFillingAnyDetails started *********");
		
		registerPage.clickOnContinueButton();
		String actualprivacyPolicyWarning = registerPage.retrievePrivacyPolicyWarningMessage();
		String expectedMessage = dataProp.getProperty("privacyPolicyWarning");
		Assert.assertTrue(actualprivacyPolicyWarning.equals(expectedMessage), "Privacy Policy message is not displayed");

		String actualFirstNameWarning = registerPage.retrieveFirstNameWarningMessage();
		Assert.assertEquals(actualFirstNameWarning, dataProp.getProperty("firstNameWarning"),
				"First Name Warning message is not displayed");

		String actualLastNameWarning = registerPage.retrieveLastNameWarningMessage();
		Assert.assertEquals(actualLastNameWarning, dataProp.getProperty("lastNameWarning"),
				"Last Name Warning message is not displayed");

		String actualEmailAddressWarning = registerPage.retrieveEmailAddressWarningMessage();
		Assert.assertEquals(actualEmailAddressWarning, dataProp.getProperty("emailWarning"),
				"E-Mail Address Warning message is not displayed");

		String actualTelephoneWarning = registerPage.retrievetelephoneWarningMessage();
		Assert.assertEquals(actualTelephoneWarning, dataProp.getProperty("telephoneWarning"),
				"Telephone Warning message is not displayed");

		String actualPasswordWarning = registerPage.retrievePasswordWarningMessage();
		Assert.assertEquals(actualPasswordWarning, dataProp.getProperty("passwordWarning"),
				"Password Warning message is not displayed");
		
		logger.info("********** verifyRegisteringAccountWithoutFillingAnyDetails ended *********");

	}
	
	@Test(priority = 5)
	public void verifyRegisteringWithFilledFormWithoutClickingCheckBox() {
		logger.info("verifyRegisteringWithFilledFormWithoutClickingCheckBox started");
		registerPage.enterFirstNameText(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmailAddress(Utilities.generateEmailWithTimestamp());
		registerPage.enterTelephoneNumber(dataProp.getProperty("telephoneNumber"));
		registerPage.enterPassword(dataProp.getProperty("validPassword"));
		registerPage.enterConfirmPassword(dataProp.getProperty("validPassword"));
		registerPage.selectNewsLetterOption("yes");
		//registerPage.selectPrivacyPolicy();
		registerPage.clickOnContinueButton();
		String actualrivacyPolicyWarning = registerPage.retrievePrivacyPolicyWarningMessage();
		String expectedMessage = dataProp.getProperty("privacyPolicyWarning");
		Assert.assertTrue(actualrivacyPolicyWarning.equals(expectedMessage), "Privacy Policy message is not displayed");
		logger.info("verifyRegisteringWithFilledFormWithoutClickingCheckBox ended");
	}
	
	@Test(priority = 6)
	public void verifyRegisteringNoFilledFormWithClickingPrivacyPolicy() {
		logger.info("verifyRegisteringNoFilledFormWithClickingPrivacyPolicy started");
		registerPage.selectPrivacyPolicy();
		registerPage.clickOnContinueButton();
		

		String actualFirstNameWarning =registerPage.retrieveFirstNameWarningMessage();
		Assert.assertEquals(actualFirstNameWarning, dataProp.getProperty("firstNameWarning"),
				"First Name Warning message is not displayed");

		String actualLastNameWarning = registerPage.retrieveLastNameWarningMessage();
		Assert.assertEquals(actualLastNameWarning, dataProp.getProperty("lastNameWarning"),
				"Last Name Warning message is not displayed");

		String actualEmailAddressWarning = registerPage.retrieveEmailAddressWarningMessage();
		Assert.assertEquals(actualEmailAddressWarning, dataProp.getProperty("emailWarning"),
				"E-Mail Address Warning message is not displayed");

		String actualTelephoneWarning = registerPage.retrievetelephoneWarningMessage();
		Assert.assertEquals(actualTelephoneWarning, dataProp.getProperty("telephoneWarning"),
				"Telephone Warning message is not displayed");

		String actualPasswordWarning = registerPage.retrievePasswordWarningMessage();
		Assert.assertEquals(actualPasswordWarning, dataProp.getProperty("passwordWarning"),
				"Password Warning message is not displayed");
		logger.info("verifyRegisteringNoFilledFormWithClickingPrivacyPolicy ended");
	}

}
