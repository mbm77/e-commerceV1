package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(id="input-email")
	private WebElement inputEmail;
	
	@FindBy(id="input-password")
	private WebElement inputPassword;
	
	@FindBy(xpath="//input[@value='Login']")
	private WebElement loginButton;
	
	@FindBy(xpath="//div[contains(@class, 'alert-dismissible')]")
	private WebElement emailPasswordNotmatchingWarning;
	
	
	//Actions
	
	public void enterEmailAddress(String email) {
		inputEmail.sendKeys(email);
		
	}
	
	public void enterPassword(String password) {
		inputPassword.sendKeys(password);
	}
	
	public AccountPage clickOnLoginButton() {
		loginButton.click();
		return new AccountPage(driver);
	}
	
	public String retrieveEmailpasswordNotMatchingwarningmessageText() {
		String warningText = emailPasswordNotmatchingWarning.getText();
		return warningText;
	}

}
