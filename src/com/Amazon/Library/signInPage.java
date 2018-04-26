/**
 * 
 */
package com.Amazon.Library;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

/**
 * @author Owner
 *
 */
public class signInPage {

	private WebDriver driver;
	private static Utility util;

	public signInPage(WebDriver driver) {
		this.driver = driver;
		signInPage.util = new Utility(driver);
	}

	@FindBy(id = "nav-link-yourAccount")
	private WebElement signInBtn;

	@FindBy(xpath = "//*[@id=authportal-main-section]/div[2]/div/div[1]/form/div/div/div/h1")
	private WebElement signInText;

	@FindBy(id = "ap_email")
	private WebElement userEmailInputBox;

	@FindBy(id = "continue")
	private WebElement continueBtn;

	// @FindBy(id = "auth-fpp-link-bottom")
	// private WebElement forgotPasswordLink;
	@FindBy(id = "ap_password")
	private WebElement passwordInputBox;

	@FindBy(id = "signInSubmit")
	private WebElement signInButton;

	@FindBy(xpath = "//*[@id='nav-link-yourAccount']/span[1]")
	private WebElement YourAccountBtn;

	@FindBy(xpath = "//span[text()='Sign Out']")
	private WebElement signoutBtn;

	public void clickonsigninBtn() {
		signInBtn.click();
	}

	// is signIn page loaded
	public boolean isSignInPageDisplayed() {
		return signInText.isDisplayed();
	}

	// enter userEmail
	public void enterUserEmail() {
		userEmailInputBox.sendKeys(util.getProp("USER_EMAILADDRESS"));
	}

	// is PasswordBox displayed
	public boolean isPassswordInputBoxDisplayed() {
		continueBtn.click();
		return passwordInputBox.isDisplayed();
	}

	// clcik forgot password
	// public void clickForgotPsswordLink() {
	// forgotPasswordLink.click();

	// }
	public void enterPassword() {
		passwordInputBox.sendKeys(util.getProp("PASSWORD"));

	}

	public void clickonsignin() {
		signInButton.click();
		System.out.println("------login page is successfull----------");
	}

	public void clickonyourAcoount() throws Exception {
		Actions actions = new Actions(driver);
		actions.moveToElement(YourAccountBtn);
		actions.perform();
	}

	public void clickOnSignout() {
		signoutBtn.click();
		System.out.println("------signout successfull----------");
	}
}
