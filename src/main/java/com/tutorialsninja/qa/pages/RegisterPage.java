package com.tutorialsninja.qa.pages;

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

	@FindBy(id = "input-firstname")
	private WebElement inputFirstName;

	@FindBy(id = "input-lastname")
	private WebElement inputLastName;

	@FindBy(id = "input-email")
	private WebElement inputEmail;
	
	@FindBy(id = "input-telephone")
	private WebElement inputTelephone;
	
	@FindBy(id="input-password")
	private WebElement inputPassword;
	
	@FindBy(id="input-confirm")
	private WebElement inputConfirmPassword;
	
	@FindBy(xpath="//input[@name='newsletter'][@value='1']")
	private WebElement selectSubscribeYes;
	
	@FindBy(xpath="//input[@name='newsletter'][@value='0']")
	private WebElement selectSubscribeNo;
	
	@FindBy(name="agree")
	private WebElement agreeCheckBox;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continueButton;
	
	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement emailAlredyExistWarningMsg;
	
	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement privacyPolicyWarningMessage;
	
	@FindBy(xpath = "//input[@id = 'input-firstname']/following-sibling::div")
	private WebElement firstNameWarningMessage;
	
	@FindBy(xpath = "//input[@id = 'input-lastname']/following-sibling::div")
	private WebElement lastNameWarningMessage;
	
	@FindBy(xpath = "//input[@id = 'input-email']/following-sibling::div")
	private WebElement emailAddressWarningMessage;
	
	@FindBy(xpath = "//input[@id = 'input-telephone']/following-sibling::div")
	private WebElement telephoneWarningMessage;
	
	@FindBy(xpath = "//input[@id = 'input-password']/following-sibling::div")
	private WebElement passwordWarningMessage;
	
	public void enterFirstNameText(String firstName) {
		inputFirstName.sendKeys(firstName);
	}
	
	public void enterLastName(String lastName) {
		inputLastName.sendKeys(lastName);
	}
	
	public void enterEmailAddress(String email) {
		inputEmail.sendKeys(email);
	}
	
	public String retrieveEmailAlredyExistWarningMessage() {
		String emailAlredyExistWarningMessage = emailAlredyExistWarningMsg.getText();
		return emailAlredyExistWarningMessage;
	}
	
	public String retrievePrivacyPolicyWarningMessage() {
		return privacyPolicyWarningMessage.getText();
	}
	
	public String retrieveFirstNameWarningMessage() {
		return firstNameWarningMessage.getText();
	}
	
	public String retrieveLastNameWarningMessage() {
		return lastNameWarningMessage.getText();
	}
	
	public String retrieveEmailAddressWarningMessage() {
		return emailAddressWarningMessage.getText();
	}
	
	public String retrievetelephoneWarningMessage() {
		return telephoneWarningMessage.getText();
	}
	
	public String retrievePasswordWarningMessage() {
		return passwordWarningMessage.getText();
	}
	
	public void enterTelephoneNumber(String telephoneNum) {
		inputTelephone.sendKeys(String.valueOf(telephoneNum));
	}
	
	public void enterPassword(String password) {
		inputPassword.sendKeys(password);
	}
	
	public void enterConfirmPassword(String confirmPassword) {
		inputConfirmPassword.sendKeys(confirmPassword);
	}
	
	public void selectNewsLetterOption(String subscribe) {
		if(subscribe == "yes") {
			selectSubscribeYes.click();	
		}
		if(subscribe == "no") {
			selectSubscribeNo.click();
		}
	}
	
	public void selectPrivacyPolicy() {
		agreeCheckBox.click();
	}
	
	public AccountSuccessPage clickOnContinueButton() {
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
	
}
