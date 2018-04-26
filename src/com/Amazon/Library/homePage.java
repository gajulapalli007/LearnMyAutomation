/**
 * 
 */
package com.Amazon.Library;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Owner
 *
 */
public class homePage {

	private WebDriver driver;
	private static Utility util;
	private signInPage signInPageObj;

	// constructor method with driver in parameters
	public homePage(WebDriver driver) {
		this.driver = driver;
		homePage.util = new Utility(driver);
	}

	/*
	 * @FindBy(how = How.ID, using = "nav-link-yourAccount") WebElement
	 * signinBtn;
	 */

	@FindBy(id = "nav-logo")
	private WebElement amazonHomePageLogo;

	@FindBy(id = "nav-link-yourAccount")
	private WebElement signInBtn;

	/*
	 * @FindBy(how = How.ID, using = "ap_email") private WebElement
	 * usernameInputBox;
	 * 
	 * @FindBy(how = How.ID, using = "continue") private WebElement continueBtn;
	 * 
	 * @FindBy(how = How.ID, using = "ap_password") private WebElement
	 * passwordInputBox;
	 */

	// enter url
	public homePage getHomePage(WebDriver driver, String URL) {
		driver.get(URL);
		return PageFactory.initElements(driver, homePage.class);
	}

	// check if the application Amazonlogo is displayed
	public boolean isAmazonPageDisplayed() {
		boolean A = amazonHomePageLogo.isDisplayed();
		return A;
	}

	// click signin button
	public signInPage clickSignInBtn() {
		signInBtn.click();
		return signInPageObj;

	}

}
