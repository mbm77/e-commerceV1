package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;

	@FindBy(xpath = "//span[text()='My Account']")
	private WebElement myAccountDropDown;

	@FindBy(linkText="Login")
	private WebElement loginOption;
	
	@FindBy(linkText="Register")
	private WebElement registerOption;
	
	@FindBy(name = "search")
	private WebElement searchProduct;
	
	@FindBy(xpath = "//div[@id='search']/descendant::button")
	private WebElement searchButton;
	
	@FindBy(linkText = "HP LP3065")
	private WebElement statusOfProductAvailability;
	
	@FindBy(xpath = "//div[@id='content']/h2/following-sibling::p")
	private WebElement noProductAvailabilityText;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	
	public void clickOnMyAccount() throws InterruptedException {
		Actions actions = new Actions(driver);
		actions.moveToElement(myAccountDropDown).click().build().perform();
		Thread.sleep(4000);
		//myAccountDropDown.click();
	}
	
	public LoginPage selectLoginOption() {
		loginOption.click();
		return new LoginPage(driver);
	}
	
	public RegisterPage selectRegisterOption() {
		registerOption.click();
		return new RegisterPage(driver); 
	}
	
	public void enterProduct(String product) {
		searchProduct.sendKeys(product);
	}
	
	public void searchProduct() {
		searchButton.click();
	}
	
	public boolean statusOfProductAvailabilityText() {
		boolean productAvailableMsg  = statusOfProductAvailability.isDisplayed();
		return productAvailableMsg;
	}
	
	public String retrieveNoProductAvailableText() {
		String noProAvailableText = noProductAvailabilityText.getText();
		return noProAvailableText;
	}
}
