package com.Amazon.Test.Scenarios;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Amazon.Library.Utility;

public class ShoppingCartTest extends BaseTest {
	private Utility util = new Utility();
	Logger logger;

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

	@Test(groups = { "FunctionalTEstgrp5" }, description = "Check work flow of customer shopping adding and checkout", dependsOnMethods = { "isUserCredentialsEnteredCorrectly" })
	public void isCustomerAbleToAddItemToShoppingCart() throws Exception {
		ShoppingCartObj.NavigateToShopByDepartment();
		util.waitForElement(4000);
		ShoppingCartObj.selecttoyschildren();
		util.waitForElement(4000);
		ShoppingCartObj.ClickOnBaby();
		logger = Logger.getLogger("devpinoyLogger");
		logger.debug("baby store is displyed");
		util.waitForElement(4000);
		ShoppingCartObj.isBabyStoreTextDisplayed();
		util.waitForElement(4000);
		ShoppingCartObj.IsCustomerAbleToAddItemToShoppingCart();
		logger.debug("custer added items to shopping cart");
		ShoppingCartObj.Quantity_list();
		util.waitForElement(4000);
		logger.debug("customer selected Quntity");
		ShoppingCartObj.AddToTheBasket();
		util.waitForElement(4000);
		ShoppingCartObj.isSelectDeliveryAdressDisplayed();
		util.waitForElement(4000);
		ShoppingCartObj.NavigateToPreviousPage();
		util.waitForElement(4000);
		signInPageObj.clickonyourAcoount();
		util.waitForElement(4000);
		signInPageObj.clickOnSignout();
		signInPageObj.enterUserEmail();
		// click continue and check if the password inputbox is displayed
		// true or false
		Assert.assertTrue(signInPageObj.isPassswordInputBoxDisplayed());
		signInPageObj.enterPassword();
		util.waitForElement(5000);
		signInPageObj.clickonsignin();
		util.waitForElement(5000);
		ShoppingCartObj.VerifyBasketStillHasItemsSelected();
		util.waitForElement(5000);
		ShoppingCartObj.isSubTotalTextIsDisplayed();
	}
}
