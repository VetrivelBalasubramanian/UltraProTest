package com.ultrapro.test.regression;

import java.util.Map;

import org.testng.annotations.Test;

import com.ultrapro.commonUtils.InitPageObjectClasses;

public class MyOrders_Test extends InitPageObjectClasses {

	@Test(groups = "Regression", testName = "TT_30", description = "Check the order details in view order page")
	public void TT_30_verifyTheOrderDetailsInViewOrderPage() {

		Map<String, String> loginCredentialsMapData = jsonReader.readJsonMapData("userCredentials","user_logincredentials");
		Map<String, String> checkoutDetailsMapData = jsonReader.readJsonMapData("purchaseRequiredDetails","checkoutdetails");
		String[] productNames = checkoutDetailsMapData.get("producttopurchase").split(";");
		String productCost = jsonReader.readJsonData("productcatalouge", "productnames", "dinnerparty").replace("$", "");
		String orderNumber;
		double totalOrderCost;
		int zipcode;
		String orderStatus = jsonReader.readJsonData("verificationData", "vieworderpage", "orderstatus");
		String shippingMethod = checkoutDetailsMapData.get("shippingmethod");
		zipcode = Integer.parseInt(checkoutDetailsMapData.get("zipcode"));

		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Step-1: Check the Sign In menu and click to enter the credentials */
		getHomePage().userLogin(loginCredentialsMapData);
		/* Empty the cart if already products added */
		getCartPage().emptyTheCartNow();

		/* Step-2: Purchase a product */
		getHomePage().addProductsToTheCart(productNames);
		getCartPage().clickOnMiniCartIconAndProceedToCheckOut();
		getCheckOutPage().enterShippingDetails(checkoutDetailsMapData);
		totalOrderCost = getMyOrdersPage().calculateTotalOrderCost(shippingMethod,zipcode,productCost);
		getMyOrdersPage().verifyTheTotalOrderCost(Double.toString(totalOrderCost));
		getPaymentPage().userEnterPaymentDetailsAndPlaceOrder(checkoutDetailsMapData);
		orderNumber = getPaymentPage().verifyPurchaseIsSuccessfulAndGetTheOrderNumber();

		/* Step-3: Goto My Account page */
		getHomePage().clickOnMyAccountMenu();

		/* Step-4: Select MyAccount Side NavMenu */
		getMyOrdersPage().selectMyAccountSideNavMenu("My Orders");

		/* Step-5: Verify Ordered Details In View Order Page */
		getMyOrdersPage().verifyOrderedDetailsInViewOrderPage(orderNumber, orderStatus, productNames, totalOrderCost,checkoutDetailsMapData);

		softAssert.assertAll();
	}

	@Test(groups = "Regression", testName = "TT_33", description = "User purchase a product and Reorder the same product")
	public void TT_33_userPurchaseAProductAndReOrderTheSameProduct() {

		Map<String, String> loginCredentialsMapData = jsonReader.readJsonMapData("userCredentials","user_logincredentials");
		Map<String, String> checkoutDetailsMapData = jsonReader.readJsonMapData("purchaseRequiredDetails","checkoutdetails");
		String[] productNames = checkoutDetailsMapData.get("producttopurchase").split(";");
		String productCost = jsonReader.readJsonData("productcatalouge", "productnames", "dinnerparty").replace("$", "");
		String orderNumber;
		double totalOrderCost;
		int zipcode;
		String orderStatus = jsonReader.readJsonData("verificationData", "vieworderpage", "orderstatus");
		String shippingMethod = checkoutDetailsMapData.get("shippingmethod");
		zipcode = Integer.parseInt(checkoutDetailsMapData.get("zipcode"));

		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Step-1: Check the Sign In menu and click to enter the credentials */
		getHomePage().userLogin(loginCredentialsMapData);
		/* Empty the cart if already products added */
		getCartPage().emptyTheCartNow();

		/* Step-2: Purchase a new product */
		getHomePage().addProductsToTheCart(productNames);
		getCartPage().clickOnMiniCartIconAndProceedToCheckOut();
		getCheckOutPage().enterShippingDetails(checkoutDetailsMapData);
		totalOrderCost = getMyOrdersPage().calculateTotalOrderCost(shippingMethod,zipcode,productCost);
		getMyOrdersPage().verifyTheTotalOrderCost(Double.toString(totalOrderCost));
		getPaymentPage().userEnterPaymentDetailsAndPlaceOrder(checkoutDetailsMapData);
		orderNumber = getPaymentPage().verifyPurchaseIsSuccessfulAndGetTheOrderNumber();

		/* Step-3: Goto My Account page */
		getHomePage().clickOnMyAccountMenu();

		/* Step-4: Select MyAccount Side NavMenu */
		getMyOrdersPage().selectMyAccountSideNavMenu("My Orders");

		/* Step-5: Click the reorder option */
		getMyOrdersPage().reOrderTheLastOrder();

		/* Step-6: Purchase a same product */
		getMyOrdersPage().clickOnProceedToCheckoutButtonInShoppinCartPage();
		getCheckOutPage().enterShippingDetails(checkoutDetailsMapData);
		getPaymentPage().userEnterPaymentDetailsAndPlaceOrder(checkoutDetailsMapData);
		orderNumber = getPaymentPage().verifyPurchaseIsSuccessfulAndGetTheOrderNumber();

		/* Step-7: Click on My Account Menu and Select MyAccount Side NavMenu */
		getHomePage().clickOnMyAccountMenu();
		getMyOrdersPage().selectMyAccountSideNavMenu("My Orders");

		/* Step-8: Verify Ordered Details In View Order Page */
		getMyOrdersPage().verifyOrderedDetailsInViewOrderPage(orderNumber, orderStatus, productNames, totalOrderCost,checkoutDetailsMapData);

		softAssert.assertAll();
	}
}
