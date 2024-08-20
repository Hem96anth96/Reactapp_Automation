package com.practice.sauselab.tests;

import org.testng.annotations.Test;

import com.practice.sauselab.pageclasses.ChechoutOverviewPage;
import com.practice.sauselab.pageclasses.CheckoutCompletePage;
import com.practice.sauselab.pageclasses.CheckoutInfoPage;
import com.practice.sauselab.pageclasses.LoginPage;
import com.practice.sauselab.pageclasses.ProductsPage;
import com.practice.sauselab.pageclasses.YourCartPage;
import com.practice.sauselab.utils.TestUtils;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class AppTest1 extends TestUtils {

	String cartCount;
	String infoPageCount;
	double totalPriceCart;

	int itemSize;

	@BeforeClass
	public void setUp() {

		setUpDriver();

		getDriver().get("http://localhost:3000");

	}

	@Test
	public void testLoginPage() {

		LoginPage loginPageObject = new LoginPage(getDriver());

		loginPageObject.checkTitle();

		loginPageObject.login("wrong", "wrong");
		
		String errorText = loginPageObject.getErrorText();

		System.out.println(errorText);

		getDriver().navigate().refresh();

		Assert.assertEquals(errorText, "Epic sadface: Username and password do not match");

		loginPageObject.login("Family_Guy", "Spooner_Street");

	}

	@Test(dependsOnMethods = "testLoginPage")
	public void testProductsPage() {

		ProductsPage productPageObject = new ProductsPage(getDriver() );
		itemSize = productPageObject.testProductPage();

	}

	@Test(dependsOnMethods = "testProductsPage")
	public void testCart() {

		YourCartPage yourCartPageObject = new YourCartPage(getDriver() );

		int cartItemsSize = yourCartPageObject.getCartItemsSize();
		int removeButtonsSize = yourCartPageObject.getRemoveButtonsSize();

		// Checking for each item in cart there is a remove button
		Assert.assertEquals(removeButtonsSize, cartItemsSize);

		// Checking that each item in product page is added to cart

		Assert.assertEquals(itemSize, cartItemsSize);

		// Calculate total price in cart
		totalPriceCart = yourCartPageObject.getTotalPriceInCart();

		// Get cart count from cart button span
		cartCount = yourCartPageObject.getCartCount();

		// Go to Checkout: Your Information page
		yourCartPageObject.checkout();

	}

	@Test(dependsOnMethods = "testCart")
	public void testCheckoutInformation() {

		CheckoutInfoPage checkOutPageObject = new CheckoutInfoPage(getDriver() );

		// cart count from cart button span in cart page comparison with count from cart
		// button span in checkout info page

		checkOutPageObject.testCheckoutPage(cartCount);

	}

	@Test(dependsOnMethods = "testCheckoutInformation")
	public void testOverviewPage() {

		ChechoutOverviewPage overViewPageObject = new ChechoutOverviewPage(getDriver() );
		overViewPageObject.testOverViewPage(totalPriceCart);

	}

	@Test(dependsOnMethods = "testOverviewPage")
	public void testCompletePage() {

		CheckoutCompletePage completePageObject = new CheckoutCompletePage(getDriver() );

		completePageObject.testCheckoutCompletePage();

	}

	@AfterClass
	public void tearDown() throws InterruptedException {
		tearDownDriver();
	}

}
