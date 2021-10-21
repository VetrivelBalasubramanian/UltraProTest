package com.ultrapro.test.regression;

import java.util.Map;

import org.testng.annotations.Test;

import com.ultrapro.commonUtils.InitPageObjectClasses;
import com.ultrapro.pageObjects.CheckOutPage;

public class WholesalePage_Test extends InitPageObjectClasses {

	@Test(groups = "Regression", testName = "TT_59", description = "Verify Increase the product qty and check the error mesg In MiniCart Funtionality")
	public void TT_59_verifyIncreaseTheProductQtyAndCheckTheErrorMesgInMiniCartFuntionality() {

		Map<String, String> loginCredentialsMapData = jsonReader.readJsonMapData("userCredentials","wholesale_logincredentials");
		String[] productName = jsonReader.readJsonData("purchaseRequiredDetails", "checkoutdetails", "producttopurchase").split(";");

		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Login to the application */
		getHomePage().userLogin(loginCredentialsMapData);

		/* Empty the cart if already products added */
		getCartPage().emptyTheCartNow();

		/* Step-1: Add a product to cart */
		getHomePage().searchTheProducts(productName);
		getHomePage().addProductsToTheCart(productName);

		/* Step-2: Increase more no.of item quantity */
		getCartPage().increaseItemQuantity(10);

		/* Step-3: Verify Error Message */
		getCartPage().verifyErrorMessage();

		softAssert.assertAll();
	}

	@Test(groups = "Regression", testName = "TT_60", description = "Verify Increase the product qty and check the error mesg In Product Details page")
	public void TT_60_verifyIncreaseTheProductQtyAndCheckTheErrorMesgInProductDetailsPage() {

		Map<String, String> loginCredentialsMapData = jsonReader.readJsonMapData("userCredentials","wholesale_logincredentials");
		String[] productName = jsonReader.readJsonData("purchaseRequiredDetails", "checkoutdetails", "producttopurchase").split(";");

		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Login to the application */
		getHomePage().userLogin(loginCredentialsMapData);

		/* Empty the cart if already products added */
		getCartPage().emptyTheCartNow();

		/* Step-1: Add Products To The Cart WithOut Login */
		getHomePage().addProductsToTheCartWithOutLogin(productName);
		getCartPage().increaseItemQuantity(10);

		/* Step-2: Search and click on the product */
		getHomePage().goToHomePage();
		getHomePage().searchTheProducts(productName);
		getCheckOutPage().clickOnTheProduct(productName);

		/* Step-3: Increase the Item Qty in Product DetailsP age */
		getCheckOutPage().increaseTheItemQtyInProductDetailsPage(12);

		/* Step-4: Verify the Maximum Qty Error Message */
		getCartPage().verifyTheMaximumQtyErrorMessage();

		softAssert.assertAll();
	}

	@Test(groups = "Regression", testName = "TT_61", description = "Verify Without Login the application, the customer should not be allowed to add the product")
	public void TT_61_verifyWithoutLoginToTheApplicationTheCustomerShouldNotBeAllowedToAddToCartProduct() {

		String[] productName = jsonReader.readJsonData("purchaseRequiredDetails", "checkoutdetails", "producttopurchase").split(";");

		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Step-1: Search the Products */
		getHomePage().searchTheProducts(productName);

		/* Step-2: Add Products to the Cart WithOut Login */
		getHomePage().addProductsToTheCartWithOutLogin(productName);
	
		/* Step-3: Verify the Sign In Page */
		getCartPage().verifySignInPage();

		softAssert.assertAll();
	}

	@Test(groups = "Regression", testName = "TT_62", description = "Verify the user able to add maximum 60 quantity per product to the cart page for wholesale site.")
	public void TT_62_verifyTheUserAbleToAddMaximum60QuantityPerProductToTheCartPageForholesaleSite() {

		Map<String, String> loginCredentialsMapData = jsonReader.readJsonMapData("userCredentials","wholesale_logincredentials");
		Map<String, String> checkoutDetailsMapData = jsonReader.readJsonMapData("purchaseRequiredDetails","checkoutdetails");
		String[] productName = checkoutDetailsMapData.get("producttopurchase").split(";");

		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Login to the application */
		getHomePage().userLogin(loginCredentialsMapData);

		/* Empty the cart if already products added */
		getCartPage().emptyTheCartNow();

		/* Step-1: Add a product to cart */
		getHomePage().searchTheProducts(productName);
		getHomePage().addProductsToTheCart(productName);

		/* Step-2: Increase no.of item quantity */
		getCartPage().increaseItemQuantity(10);
		getCartPage().clickOnMiniCartIconAndProceedToCheckOut();

		/* Step-3: Enter the shipping details */
		getCheckOutPage().enterShippingDetails(checkoutDetailsMapData);

		/* Step-4: Enter Purchase order number and place the order */
		getPaymentPage().enterPurchaseOrderNumberAndPlaceOrder(325);
		getPaymentPage().verifyPurchaseIsSuccessfulAndGetTheOrderNumber();

		softAssert.assertAll();
	}

	@Test(groups = "Regression", testName = "TT_63", description = "Verify product purchase through Net30 option")
	public void TT_63_verifyProductPurchaseThroughNet30Option() {

		Map<String, String> loginCredentialsMapData = jsonReader.readJsonMapData("userCredentials","wholesale_logincredentials");
		Map<String, String> checkoutDetailsMapData = jsonReader.readJsonMapData("purchaseRequiredDetails","checkoutdetails");
		String[] productNames = checkoutDetailsMapData.get("producttopurchase").split(";");

		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Login to the application */
		getHomePage().userLogin(loginCredentialsMapData);

		/* Empty the cart if already products added */
		getCartPage().emptyTheCartNow();

		/* Step-2: Search the product and click the add to cart button */
		getHomePage().addProductsToTheCart(productNames);
		getCartPage().clickOnMiniCartIconAndProceedToCheckOut();

		/* Step-3: Enter the shipping details */
		getCheckOutPage().enterShippingDetails(checkoutDetailsMapData);

		/* Step-4: Enter Purchase order number and place the order */
		getPaymentPage().enterPurchaseOrderNumberAndPlaceOrder(325);
		getPaymentPage().verifyPurchaseIsSuccessfulAndGetTheOrderNumber();
	}

	@Test(groups = "Regression", testName = "TT_64", description = "Verify the Submit Request to Sell TableTopics link")
	public void TT_64_verifyTheSubmitRequestToSellTableTopicsLink() {

		Map<String, String> checkoutDetailsMapData = jsonReader.readJsonMapData("purchaseRequiredDetails","checkoutdetails");

		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Step-1: Click On the Sign In Link */
		getHomePage().clickOnTheSignInLink();

		/* Step-2: Submit Request to Sell Table Topics Link */
		getCheckOutPage().submitRequestToSellTableTopicsLink(checkoutDetailsMapData);

		/* Step-3: Verify Success Message */
		getCartPage().verifySuccessMessage();

		softAssert.assertAll();
	}

}
