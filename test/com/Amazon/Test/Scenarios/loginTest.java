/**
 * 
 */
package com.Amazon.Test.Scenarios;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Amazon.Library.Utility;

/**
 * @author Owner
 *
 */
public class loginTest extends BaseTest {
	private Utility util = new Utility();

	// preconditions before testcase
	@BeforeMethod(alwaysRun = true)
	public void initialize(Method m) {
		log.info(m.getName());
		super.initialize(m);
	}

	// postconditions after the testcase
	@AfterMethod(alwaysRun = true)
	public void cleanup(Method m) {

	}

	@Test
	public void isURLConnected() {
		homePageObj.getHomePage(driver, util.getProp("URL"));
		Assert.assertTrue(homePageObj.isAmazonPageDisplayed());
		util.waitForElement(5000);
	}

	@Test(groups = { "FunctionalTEstgrp1" }, description = "Check the login work flow for Amazon customer", dependsOnMethods = { "isURLConnected" })
	public void isUserAbleToClickSign() {
		homePageObj.isAmazonPageDisplayed();
		util.waitForElement(5000);
		signInPageObj.clickonsigninBtn();
		util.waitForElement(5000);
	}

	@Test(groups = { "FunctionalTEstgrp2" }, description = "Check the login work flow for Amazon customer", dependsOnMethods = { "isUserAbleToClickSign" })
	public void isUserCredentialsEnteredCorrectly() {
		signInPageObj.enterUserEmail();
		// click continue and check if the password inputbox is displayed
		// true or false
		Assert.assertTrue(signInPageObj.isPassswordInputBoxDisplayed());
		signInPageObj.enterPassword();
		util.waitForElement(5000);
		signInPageObj.clickonsignin();
	}

	@Test(groups = { "FunctionalTEstgrp3" }, description = "Check if customer able to signout sucessfully", dependsOnMethods = { "isUserCredentialsEnteredCorrectly" })
	public void isUserAbleToSignoutCorrectly() throws Exception {
		signInPageObj.clickonyourAcoount();
		util.waitForElement(5000);
		signInPageObj.clickOnSignout();

	}

	// selet a item ; add an iten to your cart ; navigate to billing page ;click
	// signout->Userstory1:: TC: when user has alreaady added an item to cart
	// and signed out and signin again->shopping cart should show the previous
	// selected items

	// enter username;enter incorrect password;click signin;->Userstory2: TC:
	// when user incorrectPasswordEntry amazon should show
	// "There was a problem Your password is incorrect"
}
