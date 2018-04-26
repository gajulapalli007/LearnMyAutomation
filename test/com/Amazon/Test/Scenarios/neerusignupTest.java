package com.Amazon.Test.Scenarios;

import java.lang.reflect.Method;

import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Amazon.Library.Utility;

public class neerusignupTest extends BaseTest {
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
		AssertJUnit.assertTrue(homePageObj.isAmazonPageDisplayed());
		util.waitForElement(5000);
	}

	/*
	 * @Test(groups = { "FunctionalTEstgrp5" }, description =
	 * "Check the login work flow for Amazon customer", dependsOnMethods = {
	 * "isURLConnected" }) public void isUserAbleToClickSignUPBtn() {
	 * homePageObj.isAmazonPageDisplayed(); util.waitForElement(5000);
	 * signupObj.clickonstartHereBtn(); util.waitForElement(5000); }
	 */
	@Test(groups = { "FunctionalTEstgrp6" }, description = "Check if customer able to create the new account", dependsOnMethods = { "isURLConnected" })
	public void isUserAbleToCreateNewAccount() {
		homePageObj.isAmazonPageDisplayed();
		util.waitForElement(5000);
		neerusignuppageObj.clickonsigninBtn();
		util.waitForElement(5000);
		neerusignuppageObj.clickonstartHereBtn();
		util.waitForElement(5000);

	}

}
