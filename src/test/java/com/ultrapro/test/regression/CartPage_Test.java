package com.ultrapro.test.regression;

import java.util.Map;

import org.testng.annotations.Test;

import com.ultrapro.commonUtils.InitPageObjectClasses;

public class CartPage_Test extends InitPageObjectClasses {

	@Test(groups = "Sanity", testName = "TT_06", description = "Verify Added products should be listed in cart page")
	public void TT_06_verifyAddedProductsShouldBeListedInCart() {

		Map<String, String> productNamesMapData = jsonReader.readJsonMapData("purchaseRequiredDetails","checkoutdetails");
		String[] productNames = productNamesMapData.get("producttopurchase").split(";");

		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Step-1: click on the product and click the add to cart button */
		getHomePage().addProductsToTheCart(productNames);

		/* Step-2: Check the Cart for the added items */
		getCartPage().verifyProductsName();

		softAssert.assertAll();
	}

	@Test(groups = "Sanity", testName = "TT_07", description = "Verify clicking on proceed to checkout button navigates to checkout page")
	public void TT_07_verifyProceedToCheckoutInMiniCart() {

		String[] productNames = jsonReader.readJsonData("verificationData", "minicart", "singleproduct").split(";");
		String checkoutPageTitle = jsonReader.readJsonData("verificationData", "checkoutpage", "pagetitle");

		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Step-1: Search the product and click the add to cart button */
		getHomePage().addProductsToTheCart(productNames);

		/* Step-2: Click on Proceed to checkout button */
		getCartPage().clickOnProceedToCheckoutButton();

		/* Step-3: check application navigated to the proceed to checkout page */
		getHomePage().verifyPageTitle(checkoutPageTitle);

		softAssert.assertAll();
	}

	@Test(groups = "Sanity", testName = "TT_08", description = "Check the product increase/decrease functionlity in Mini cart")
	public void TT_08_verifyProductIncreaseDecreaseInMiniCartFuntionality() {

		String[] productName = jsonReader.readJsonData("purchaseRequiredDetails", "checkoutdetails", "producttopurchase").split(";");

		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Step-1: Goto home page and Add a product to cart */
		getHomePage().addProductsToTheCart(productName);

		/* Step-2: Goto cart and increase quantity and verify */
		getCartPage().increaseOrDecreaseItemQuantity("increase", 2);
		getCartPage().verifyItemQuantityAfterIncreaseOrDecrease(3);

		/* Step-3: Now decrease quantity and verify */
		getCartPage().increaseOrDecreaseItemQuantity("decrease", 1);
		getCartPage().verifyItemQuantityAfterIncreaseOrDecrease(2);

		softAssert.assertAll();
	}

	@Test(groups = "Sanity", testName = "TT_09", description = "Check the delete product functionality in Mini cart")
	public void TT_09_verifyDeleteProductFunctionInMiniCart() {

		String[] productName = jsonReader.readJsonData("purchaseRequiredDetails", "checkoutdetails", "producttopurchase").split(";");

		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Step-1: Goto home page and Add a product to cart */
		getHomePage().addProductsToTheCart(productName);

		/* Step-2: Goto cart and delete item in mini cart */
		getCartPage().deleteAnItemInMiniCart();

		/* Step-3: Verify the cart is empty */
		getCartPage().verifyTheCartIsEmpty();

		softAssert.assertAll();
	}

	@Test(groups = "Sanity", testName = "TT_10", description = "Verify the View and Edit function in mini cart")
	public void TT_10_verifyViewAndEditNavigatesToShoppingCartPage() {

		String[] productName = jsonReader.readJsonData("purchaseRequiredDetails", "checkoutdetails", "producttopurchase").split(";");

		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Step-2: Search the product and click the add to cart button */
		getHomePage().addProductsToTheCart(productName);

		/* Step-2: Click on View and Edit Link */
		getCartPage().clickOnViewAndEditLink();

		/* Step-3: Verify the browser redirected to shopping cart page */
		getCartPage().verifyLandedIntoShoppingCartPage();
		getCartPage().verifyProductsNameInTheCart(productName);
	}

	@Test(groups = "Sanity", testName = "TT_11", description = "Verify the user can see the added product in cart even after logout and login again")
	public void TT_11_verifyAddedProductsInCartAfterLogoutAndLoginAgain() {

		Map<String, String> logincredentialsMapData = jsonReader.readJsonMapData("userCredentials","user_logincredentials");
		Map<String, String> productNamesMapData = jsonReader.readJsonMapData("purchaseRequiredDetails","checkoutdetails");
		String[] productNames = productNamesMapData.get("producttopurchase").split(";");

		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Step-1: Verify current environment sign in */
		getHomePage().userLogin(logincredentialsMapData);
		getHomePage().verifyHomePage();
		/* Empty the cart if already products added */
		getCartPage().emptyTheCartNow();

		/* Step-2: Add a product to the cart and verify them in cart */
		getHomePage().addProductsToTheCart(productNames);
		getCartPage().verifyProductsName();

		/* Step-3: Sign Out and Sign in again */
		getHomePage().userSignOut();
		getHomePage().userLogin(logincredentialsMapData);

		/* Step-4: Verify the products in cart for already added products */
		getCartPage().clickOnMiniCartIcon();
		getCartPage().verifyProductsName();

		softAssert.assertAll();
	}

	@Test(groups = "Sanity", testName = "TT_12", description = "Verify cart sub total for single product added to the cart")
	public void TT_12_verifyCartSubtotalForSingleProduct() {

		String[] productNames = jsonReader.readJsonData("verificationData", "minicart", "singleproduct").split(";");

		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Step-1: click on the product and click the add to cart button */
		getHomePage().addProductsToTheCart(productNames);

		/* Step-2: Verify the sub total amount */
		getCartPage().verifySubTotalCost();

		softAssert.assertAll();
	}

}
