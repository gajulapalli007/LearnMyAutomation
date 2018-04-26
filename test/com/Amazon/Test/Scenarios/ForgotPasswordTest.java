package com.Amazon.Test.Scenarios;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Amazon.Library.Utility;

public class ForgotPasswordTest extends BaseTest {
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

	@Test(groups = { "FunctionalTEstgrp4" }, description = "Check if customer able to signin when forgot password", dependsOnMethods = { "isURLConnected" })
	public void isUserAbleToSigninWhenForgotPassword() {
		homePageObj.isAmazonPageDisplayed();
		util.waitForElement(5000);
		signInPageObj.clickonsigninBtn();
		util.waitForElement(5000);
		Need_HelpObj.checkNeeedHelp();
		util.waitForElement(5000);
		Need_HelpObj.clickOnForgotPassword();
		util.waitForElement(5000);
		Need_HelpObj.isForgotPasswordPageDisplayed();
		util.waitForElement(5000);
		Need_HelpObj.enterUserEmail();
		util.waitForElement(5000);
		Need_HelpObj.isNeedassistancePageDisplayed();
		util.waitForElement(5000);
		Need_HelpObj.selectSignInWithTemporaryCodeoption();
		util.waitForElement(5000);
		Need_HelpObj.clickonContinue();

	}
}
