package com.Amazon.Library;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class neerusignuppage {
	private WebDriver driver;
	private static Utility util;

	public neerusignuppage(WebDriver driver) {
		this.driver = driver;
		neerusignuppage.util = new Utility(driver);
	}

	@FindBy(id = "nav-link-yourAccount")
	private WebElement signInBtn;

	@FindBy(linkText = "Start Here")
	private WebElement startHereBtn;

	@FindBy(xpath = "//*[@id=ap_register_form]/div/div/h1")
	private WebElement createAccountText;

	@FindBy(id = "ap_customer_name")
	private WebElement yourNameInputBox;

	@FindBy(id = "ap_email")
	private WebElement emailInputBox;

	@FindBy(id = "ap_passwordl")
	private WebElement passwordInputBox;

	@FindBy(id = "ap_password_check")
	private WebElement repasswordInputBox;

	@FindBy(id = "continue")
	private WebElement createAccountBox;

	public void clickonsigninBtn() {
		Actions actions = new Actions(driver);
		actions.moveToElement(signInBtn);
		actions.perform();
	}

	public void clickonstartHereBtn() {
		startHereBtn.click();
	}

	public boolean isCreateAccountPageDisplayed() {
		return createAccountText.isDisplayed();
	}

	public void enterYourName() {
		yourNameInputBox.sendKeys(util.getProp("yourname"));
	}

	public void enterYourEmail() {
		emailInputBox.sendKeys(util.getProp("email"));
	}

	public void enterYourPassword() {
		passwordInputBox.sendKeys(util.getProp("password"));
	}

	public void enterYourRepassword() {
		repasswordInputBox.sendKeys(util.getProp("re-enterpassword"));
	}

	public void clickOnContinue() {
		createAccountBox.click();
		System.out.println("------login page is successfull----------");
	}

}
