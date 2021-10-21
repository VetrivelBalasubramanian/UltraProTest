package com.ultrapro.test.regression;

import java.util.Map;

import org.testng.annotations.Test;

import com.ultrapro.commonUtils.InitPageObjectClasses;

public class PaymentPage_Test extends InitPageObjectClasses {

	@Test(groups = "Regression", testName = "TT_21", description = "Verify product purchase through credit card payment option")
	public void TT_21_verifyProductPurchaseThroughCreditCardPayment() {

		Map<String, String> loginCredentialsMapData = jsonReader.readJsonMapData("userCredentials","user_logincredentials");
		Map<String, String> checkoutDetailsMapData = jsonReader.readJsonMapData("purchaseRequiredDetails","checkoutdetails");
		String[] productNames = checkoutDetailsMapData.get("producttopurchase").split(";");

		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Step-1: Login to the application and empty the cart if products exists */
		getHomePage().userLogin(loginCredentialsMapData);
		getCartPage().emptyTheCartNow();

		/* Step-2: Search the product and click the add to cart button */
		getHomePage().addProductsToTheCart(productNames);
		getCartPage().clickOnMiniCartIconAndProceedToCheckOut();

		/* Step-3: Enter the shipping details */
		getCheckOutPage().enterShippingDetails(checkoutDetailsMapData);

		/* Step-4: Enter payment details and place the order */
		getPaymentPage().userEnterPaymentDetailsAndPlaceOrder(checkoutDetailsMapData);
		getPaymentPage().verifyPurchaseIsSuccessfulAndGetTheOrderNumber();
	}

	@Test(groups = {"Regression" }, testName = "TT_22", description = "Verify the address is displayed if clicks on shipping and billing address are same checkbox")
	public void TT_22_verifyShippingAndBillingAddressSameCheckBoxAction() {

		Map<String, String> loginDetailsMapData = jsonReader.readJsonMapData("userCredentials","user_logincredentials");
		Map<String, String> checkoutDetailsMapData = jsonReader.readJsonMapData("purchaseRequiredDetails","checkoutdetails");
		String[] productName = checkoutDetailsMapData.get("producttopurchase").split(";");

		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Step-1: Login to the application and empty the cart if products exists */
		getHomePage().userLogin(loginDetailsMapData);
		getCartPage().emptyTheCartNow();

		/* Step-2: Search the product and click the add to cart button */
		getHomePage().addProductsToTheCart(productName);

		/* Step-3: User checks items added into cart and proceed to checkout */
		getCartPage().verifyNumOfItemsInCart(1);
		getCartPage().clickOnMiniCartIconAndProceedToCheckOut();

		/* Step-4: User enters shipping details */
		getCheckOutPage().enterShippingDetails(checkoutDetailsMapData);

		/* Step-5: Verify the address displayed */
		getPaymentPage().verifyShippingAndBillingAddressSameCheckbox(checkoutDetailsMapData.get("paymentmethod"));

		softAssert.assertAll();
	}

	@Test(groups = {"Regression" }, testName = "TT_23", description = "Verify the credit card number field accepts only numeric values")
	public void TT_23_verifyCreditCardNumberFieldAcceptsOnlyNumericValues() {

		Map<String, String> loginDetailsMapData = jsonReader.readJsonMapData("userCredentials","user_logincredentials");
		Map<String, String> checkoutDetailsMapData = jsonReader.readJsonMapData("purchaseRequiredDetails","checkoutdetails");
		String[] productName = checkoutDetailsMapData.get("producttopurchase").split(";");

		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Step-1: Login to the application and empty the cart if products exists */
		getHomePage().userLogin(loginDetailsMapData);
		getCartPage().emptyTheCartNow();

		/* Step-2: Search the product and click the add to cart button */
		getHomePage().addProductsToTheCart(productName);

		/* Step-3: User checks items added into cart and proceed to checkout */
		getCartPage().clickOnMiniCartIconAndProceedToCheckOut();

		/* Step-4: User enters shipping details */
		getCheckOutPage().enterShippingDetails(checkoutDetailsMapData);

		/* Step-5: Verify credit card fields */
		getPaymentPage().verifyCreditCardNumberFieldAcceptNumericOnly(checkoutDetailsMapData);

		softAssert.assertAll();
	}

	@Test(groups = {"Regression" }, testName = "TT_24", description = "Verify the Cvc number field accepts expected length")
	public void TT_24_verifyCreditCardNumberFieldAcceptsOnlyExpectedLength() {

		Map<String, String> loginDetailsMapData = jsonReader.readJsonMapData("userCredentials","user_logincredentials");
		Map<String, String> checkoutDetailsMapData = jsonReader.readJsonMapData("purchaseRequiredDetails","checkoutdetails");
		String[] productName = checkoutDetailsMapData.get("producttopurchase").split(";");

		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Step-1: Login to the application and empty the cart if products exists */
		getHomePage().userLogin(loginDetailsMapData);
		getCartPage().emptyTheCartNow();

		/* Step-2: Search the product and click the add to cart button */
		getHomePage().addProductsToTheCart(productName);

		/* Step-3: User checks items added into cart and proceed to checkout */
		getCartPage().clickOnMiniCartIconAndProceedToCheckOut();

		/* Step-4: User enters shipping details */
		getCheckOutPage().enterShippingDetails(checkoutDetailsMapData);

		/* Step-5: Verify the cvc Text Box Char Accepts Length */
		getPaymentPage().verifyTheCvcTextBoxCharAcceptsLength(checkoutDetailsMapData);

		softAssert.assertAll();
	}

	@Test(groups = "Regression", testName = "TT_25", description = "Check the apply coupon code functionality")
	public void TT_25_verifyTheValidCouponCodeApplied() {

		Map<String, String> loginDetailsMapData = jsonReader.readJsonMapData("userCredentials","user_logincredentials");
		Map<String, String> checkoutDetailsMapData = jsonReader.readJsonMapData("purchaseRequiredDetails","checkoutdetails");
		String couponCode = jsonReader.readJsonData("verificationData", "discountcode", "validcouponcode");
		String[] productName = checkoutDetailsMapData.get("producttopurchase").split(";");

		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Step-1: Login to the application and empty the cart if products exists */
		getHomePage().userLogin(loginDetailsMapData);
		getCartPage().emptyTheCartNow();

		/* Step-2: Search the product and click the add to cart button */
		getHomePage().addProductsToTheCart(productName);

		/* Step-3: User checks items added into cart and proceed to checkout */
		getCartPage().clickOnMiniCartIconAndProceedToCheckOut();

		/* Step-4: User enters shipping details */
		getCheckOutPage().enterShippingDetails(checkoutDetailsMapData);

		/* Step-5: Apply coupon Code */
		getPaymentPage().applyDiscountCode(couponCode);

		/* Step-6: Verify the success message after the code successfully applied */
		getPaymentPage().verifyCouponCodeSuccessMessage();

		softAssert.assertAll();
	}

	@Test(groups = "Regression", testName = "TT_26", description = "Check the error message if invalid coupon code applied")
	public void TT_26_verifyErrorForInvalidCouponCodeApplied() {

		Map<String, String> loginDetailsMapData = jsonReader.readJsonMapData("userCredentials","user_logincredentials");
		Map<String, String> checkoutDetailsMapData = jsonReader.readJsonMapData("purchaseRequiredDetails","checkoutdetails");
		String couponCode = jsonReader.readJsonData("verificationData", "discountcode", "invalidcouponcode");
		String[] productName = checkoutDetailsMapData.get("producttopurchase").split(";");

		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Step-1: Login to the application and empty the cart if products exists */
		getHomePage().userLogin(loginDetailsMapData);
		getCartPage().emptyTheCartNow();

		/* Step-2: Search the product and click the add to cart button */
		getHomePage().addProductsToTheCart(productName);

		/* Step-3: User checks items added into cart and proceed to checkout */
		getCartPage().clickOnMiniCartIconAndProceedToCheckOut();

		/* Step-4: User enters shipping details */
		getCheckOutPage().enterShippingDetails(checkoutDetailsMapData);

		/* Step-5: Apply coupon Code */
		getPaymentPage().applyDiscountCode(couponCode);

		/* Step-6: Verify the error message if invalid code given */
		getPaymentPage().verifyCouponCodeErrorMessage();

		softAssert.assertAll();
	}

	@Test(groups = {"Regression" }, testName = "TT_27", description = "Verify the address update functionality is working in credit card payment option page")
	public void TT_27_verifyUpdateAddressInCreditCardPayment() {

		Map<String, String> loginDetailsMapData = jsonReader.readJsonMapData("userCredentials","user_logincredentials");
		Map<String, String> checkoutDetailsMapData = jsonReader.readJsonMapData("purchaseRequiredDetails","checkoutdetails");
		String[] productName = checkoutDetailsMapData.get("producttopurchase").split(";");

		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Step-1: Login to the application and empty the cart if products exists */
		getHomePage().userLogin(loginDetailsMapData);
		getCartPage().emptyTheCartNow();

		/* Step-2: Search the product and click the add to cart button */
		getHomePage().addProductsToTheCart(productName);

		/* Step-3: User checks items added into cart and proceed to checkout */
		getCartPage().clickOnMiniCartIconAndProceedToCheckOut();

		/* Step-4: User enters shipping details */
		getCheckOutPage().enterShippingDetails(checkoutDetailsMapData);

		/* Step-5: Checks address drop down */
		getPaymentPage().updateBillingAddressInCreditCardPayment(checkoutDetailsMapData);

		/* Step-6: Verify the updated address */
		getPaymentPage().verifyUpdatedAddressInCreditCardSection(checkoutDetailsMapData.get("updatedbillingaddress"));

		softAssert.assertAll();
	}
	
	@Test(groups = {"Regression" }, testName = "TT_28", description = "Verify the Cancel update address functionality is working in credit card payment option page")
	public void TT_28_verifyCancelUpdateAddressFunctionality() {

	Map<String, String> loginDetailsMapData = jsonReader.readJsonMapData("userCredentials","user_logincredentials");
	Map<String, String> checkoutDetailsMapData = jsonReader.readJsonMapData("purchaseRequiredDetails","checkoutdetails");
	String[] productName = checkoutDetailsMapData.get("producttopurchase").split(";");

	/* Accept News Letter */
	getHomePage().acceptNewesletter();

	/* Step-1: Login to the application and empty the cart if products exists */
	getHomePage().userLogin(loginDetailsMapData);
	getCartPage().emptyTheCartNow();

	/* Step-2: Search the product and click the add to cart button */
	getHomePage().addProductsToTheCart(productName);

	/* Step-3: User checks items added into cart and proceed to checkout */
	getCartPage().clickOnMiniCartIconAndProceedToCheckOut();

	/* Step-4: User enters shipping details */
	getCheckOutPage().enterShippingDetails(checkoutDetailsMapData);

	/* Step-5: Checks address drop down */
	getPaymentPage().updateBillingAddressInCreditCardPayment(checkoutDetailsMapData);
	getPaymentPage().verifyUpdatedAddressInCreditCardSection(checkoutDetailsMapData.get("updatedbillingaddress"));

	/* Step-6: Verify the Cancel updated address */
	getPaymentPage().verifyCancelUpdateAddress(checkoutDetailsMapData.get("cancelupdatedaddress"));

	softAssert.assertAll();
	}
	
	@Test(groups = {"Sanity" }, testName = "TT_29", description = "Verify the error message if expired credit card given for the payment")
	public void TT_29_verifyExpiredCreditCardGivenForPayment() {

		Map<String, String> loginCredentialsMapData = jsonReader.readJsonMapData("userCredentials","user_logincredentials");
		Map<String, String> checkoutDetailsMapData = jsonReader.readJsonMapData("purchaseRequiredDetails","checkoutdetails");
		String[] productNames = checkoutDetailsMapData.get("producttopurchase").split(";");
		Map<String, String> expiredCardDetailsMapData = jsonReader.readJsonMapData("verificationData","invalidcreditcarddetails");

		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Step-1: Login to the application and empty the cart if products exists */
		getHomePage().userLogin(loginCredentialsMapData);
		getCartPage().emptyTheCartNow();

		/* Step-2: Search the product and click the add to cart button */
		getHomePage().addProductsToTheCart(productNames);
		getCartPage().clickOnMiniCartIconAndProceedToCheckOut();

		/* Step-3: Enter the shipping details */
		getCheckOutPage().enterShippingDetails(checkoutDetailsMapData);

		/* Step-4: Verify Expired Credit Card Error Messages */
		getPaymentPage().verifyExpiredCreditCardErrorMessage(expiredCardDetailsMapData);

		softAssert.assertAll();
	}

}
