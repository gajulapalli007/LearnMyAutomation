package com.Amazon.Library;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ShoppingCart {
	private WebDriver driver;
	private static Utility util;

	public ShoppingCart(WebDriver driver) {
		this.driver = driver;
		ShoppingCart.util = new Utility(driver);
	}

	@FindBy(xpath = "//*[@id='nav-link-shopall']/span[1]")
	private WebElement shopByDeparmentdropdown;

	@FindBy(xpath = "//*[@id='nav-flyout-shopAll']/div[2]/span[14]/span")
	private WebElement ToysChildrensection;

	@FindBy(xpath = "//*[@id='nav-flyout-shopAll']/div[3]/div[14]/div/a[2]/span")
	private WebElement BabyBtn;

	@FindBy(xpath = "//*[@id='merchandised-content']/div[1]/div[1]/div/h1")
	private WebElement BabyStoreText;

	@FindBy(xpath = "//*[@id='merchandised-content']/div[1]/div[1]/div/div/p/a[7]")
	private WebElement BabyToysBtn;

	@FindBy(xpath = "//*[@id='leftNav']/ul[1]/div/li[1]/span/span/div/label/span/span")
	private WebElement zeroToTwoBtn;

	@FindBy(xpath = "//*[@id='result_8']/div/div[3]/div/a/h2")
	private WebElement FunTimeFarmAnimalsToyBtn;

	@FindBy(id = "quantity")
	private WebElement QuantityDropDown;

	@FindBy(id = "add-to-cart-button")
	private WebElement AddToBasketBtn;

	@FindBy(id = "hlb-ptc-btn-native")
	private WebElement ProceedToCheckOutBtn;

	@FindBy(xpath = "html/body/div[5]/div[2]/div/h1")
	private WebElement SelectADeliveryAdressText;

	@FindBy(id = "nav-cart-count")
	private WebElement BasketBtn;

	@FindBy(xpath = "//*[@id='sc-subtotal-label-activecart']")
	private WebElement SubTotalText;

	public void NavigateToShopByDepartment() {
		util.Movetoelement(shopByDeparmentdropdown);

	}

	public void selecttoyschildren() {
		util.Movetoelement(ToysChildrensection);
	}

	public void ClickOnBaby() {
		BabyBtn.click();
		driver.getCurrentUrl();
	}

	public boolean isBabyStoreTextDisplayed() {

		return BabyStoreText.isDisplayed();
	}

	public void IsCustomerAbleToAddItemToShoppingCart() {
		BabyToysBtn.click();
		zeroToTwoBtn.click();
		FunTimeFarmAnimalsToyBtn.click();

	}

	public void Quantity_list() {
		Select QuantityDropdown = new Select(QuantityDropDown);
		QuantityDropdown.selectByVisibleText(util.getProp("QUANTITY"));
	}

	public void AddToTheBasket() {
		AddToBasketBtn.click();
		ProceedToCheckOutBtn.click();
	}

	public boolean isSelectDeliveryAdressDisplayed() {
		boolean A = SelectADeliveryAdressText.isDisplayed();
		return A;

	}

	public void NavigateToPreviousPage() {
		driver.navigate().back();
	}

	public void VerifyBasketStillHasItemsSelected() {
		BasketBtn.click();

	}

	public boolean isSubTotalTextIsDisplayed() {
		return SubTotalText.isDisplayed();
	}
}
