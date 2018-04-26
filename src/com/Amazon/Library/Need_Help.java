package com.Amazon.Library;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Need_Help {
	private WebDriver driver;
	private static Utility util;

	public Need_Help(WebDriver driver) {
		this.driver = driver;
		Need_Help.util = new Utility(driver);
	}

	@FindBy(xpath = "//*[@id='authportal-main-section']/div[2]/div/div[1]/form/div/div/div/div[3]/div/a/span")
	private WebElement NeedHelpBtn;

	@FindBy(id = "auth-fpp-link-bottom")
	private WebElement ForgotPasswordBtn;

	@FindBy(xpath = "//*[@id='authportal-main-section']/div[2]/div/div[1]/div/form/h1")
	private WebElement passwordAssistanceText;

	@FindBy(id = "ap_email")
	private WebElement userEmailInputBox;

	@FindBy(id = "continue")
	private WebElement continueBtn1;

	@FindBy(xpath = "//*[@id='authportal-main-section']/div[2]/div/div/div/form/h2")
	private WebElement HowWouldYouLikeToSigninText;

	@FindBy(xpath = "//*[@id='authportal-main-section']/div[2]/div/div/div/form/div[2]/div/label/input")
	private WebElement SignInWithTemporaryCodeBtn;

	@FindBy(id = "continue")
	private WebElement continueBtn2;

	public void checkNeeedHelp() {
		NeedHelpBtn.click();
	}

	public void clickOnForgotPassword() {
		ForgotPasswordBtn.click();
	}

	public boolean isForgotPasswordPageDisplayed() {
		return passwordAssistanceText.isDisplayed();

	}

	// enter userEmail
	public void enterUserEmail() {
		userEmailInputBox.sendKeys(util.getProp("USER_EMAILADDRESS"));
		continueBtn1.click();
	}

	public boolean isNeedassistancePageDisplayed() {
		return HowWouldYouLikeToSigninText.isDisplayed();
	}

	public void selectSignInWithTemporaryCodeoption() {
		SignInWithTemporaryCodeBtn.getText();
		SignInWithTemporaryCodeBtn.click();

	}

	public void clickonContinue() {
		continueBtn2.click();
	}

}
