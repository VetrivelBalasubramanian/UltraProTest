package com.ultrapro.test.regression;

import java.util.Map;

import org.testng.annotations.Test;

import com.ultrapro.commonUtils.InitPageObjectClasses;

public class CheckOutPage_Test extends InitPageObjectClasses {
	
	@Test(groups = "Regression", testName = "TT_34", description = "Verify the guest user able to buy the products without login")	
	public void TT_34_verifyGuestUserPlaceOrderWithoutLogin() {

		Map<String, String> checkoutDetailsMapData = jsonReader.readJsonMapData("purchaseRequiredDetails", "guestusercheckoutdetails");
		String[] productNames = checkoutDetailsMapData.get("producttopurchase").split(";");
		String productCost = jsonReader.readJsonData("productcatalouge", "productnames", "familyconversationpack").replace("$", "");
		double totalOrderCost;
		int zipcode; 
		String shippingMethod = checkoutDetailsMapData.get("shippingmethod");
		zipcode = Integer.parseInt(checkoutDetailsMapData.get("zipcode"));
		
		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Step-1: Add the product to cart */
		getHomePage().addProductsToTheCart(productNames);

		/* Step-2: Goto checkout page */
		getCartPage().clickOnMiniCartIconAndProceedToCheckOut();

		/* Step-3: User enters shipping details */
		getCheckOutPage().enterCheckoutDetailsForGuestUser(checkoutDetailsMapData);

		/* Step-4: User enter payment mode details and Place the order */
		totalOrderCost = getMyOrdersPage().calculateTotalOrderCost(shippingMethod,zipcode,productCost);
		getMyOrdersPage().verifyTheTotalOrderCost(Double.toString(totalOrderCost));
		getPaymentPage().userEnterPaymentDetailsAndPlaceOrder(checkoutDetailsMapData);
		
		/* Step-5: User verifies purchase successful */
		getPaymentPage().verifyPurchaseIsSuccessfulAndGetTheOrderNumber();

		softAssert.assertAll();
	}

	@Test(groups = {"Regression"}, testName = "TT_35",description = "Verify change shipping address in checkout page")
	public void TT_35_verifyChangingShippingAddressWhileDoingTheCheckout () {
		
		Map<String, String> loginCredentialsMapData = jsonReader.readJsonMapData("userCredentials", "user_logincredentials");
		Map<String, String> checkoutDetailsMapData = jsonReader.readJsonMapData("purchaseRequiredDetails", "checkoutdetails");
		String[] productNames = checkoutDetailsMapData.get("producttopurchase").split(";");
		String changingShippingAddress = jsonReader.readJsonData("verificationData", "checkoutshippingaddress","changeshippingaddress");
		String  addressDetail = jsonReader.readJsonData("verificationData", "checkoutshippingaddress", "switchedshippingaddress");		
		
		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Step-1: Login and empty the cart */
		getHomePage().userLogin(loginCredentialsMapData);
		getCartPage().emptyTheCartNow();
		
		/* Step-2: Goto home page and Add a product to cart */
		getHomePage().addProductsToTheCart(productNames);

		/* Step-3: Proceed to checkout */
		getCartPage().clickOnMiniCartIconAndProceedToCheckOut();

		/* Step-4: click on the 'Ship Here' button */
		getCheckOutPage().verifyAndchangeShippingAddress(changingShippingAddress);
		
		/* Step-6: Verify ShipHere After Change Shipping Address */
		getCheckOutPage().verifyShippingAddress(addressDetail);
		
		softAssert.assertAll();
	}
	
	@Test(groups = {"Sanity", "Production"}, testName = "TT_36",description = "Verify the products can move to checkout and Order summary showing in Checkout page")
	public void TT_36_verifyUserCanMoveTheProductToCheckOut() {
		
		Map<String, String> loginCredentialsMapData = jsonReader.readJsonMapData("userCredentials", "user_logincredentials");
		Map<String, String> checkoutDetailsMapData = jsonReader.readJsonMapData("purchaseRequiredDetails", "checkoutdetails");

		String[] productNames = checkoutDetailsMapData.get("producttopurchase").split(";");
		
		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Step-1: Login to application */
		getHomePage().userLogin(loginCredentialsMapData);
		/* Empty the cart if already products added */
		getCartPage().emptyTheCartNow();
		
		/* Step-2: Add a product to the cart */
		getHomePage().addProductsToTheCart(productNames);

		/* Step-3: Proceed to checkout */
		getCartPage().clickOnMiniCartIconAndProceedToCheckOut();
		
		/* Step-4: Verify Order Summary In CheckOut Page */
		getCheckOutPage().verifyOrderSummaryInCheckOutPage(productNames[0], "1");
	}
}
