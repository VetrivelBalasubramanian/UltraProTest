package com.ultrapro.test.regression;

import java.util.Map;

import org.testng.annotations.Test;

import com.ultrapro.commonUtils.InitPageObjectClasses;

public class MyCartPage_Test extends InitPageObjectClasses {

	@Test(groups = "Regression", testName = "TT_52", description = "Verify whether user able to update the product quantity in the my cart page")
	public void TT_52_verifyUpdatedProductQuantityInMycart() {

		Map<String, String> loginCredentialsMapData = jsonReader.readJsonMapData("userCredentials","user_logincredentials");
		String[] productNames = jsonReader.readJsonData("verificationData", "mycart", "productsname").split(";");
		int productQty = 2;
		
		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Login to the application */
		getHomePage().userLogin(loginCredentialsMapData);
		getCartPage().emptyTheCartNow();
		
		/* Step-1: Goto home page and Add a product to cart */
		getHomePage().addProductsToTheCart(productNames);
		
		/* Step-2: Goto my cart page and update the product quantity */
		getCartPage().clickOnViewAndEditLink();
		getCartPage().updateProductQuantityCountInMyCart(productQty);
		
		/* Step-3: verify the product quantity in my cart */
		getCartPage().verifyProductQuantity(productQty);
		getCartPage().verifyItemQuantityAfterIncreaseOrDecrease(productQty);
		
		softAssert.assertAll();
	}
	
	@Test(groups = "Regression", testName = "TT_53", description = "Verify edit product details and update functionality in My cart")
	public void TT_53_verifyUpdatedProductQtyInProductDetailsPageInMycart() {
		
		Map<String, String> loginCredentialsMapData = jsonReader.readJsonMapData("userCredentials","user_logincredentials");
		String[] productNames = jsonReader.readJsonData("verificationData", "mycart", "productsname").split(";");
		int productQty = 2;

		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Login to the application */
		getHomePage().userLogin(loginCredentialsMapData);
		getCartPage().emptyTheCartNow();
		
		/* Step-1: Goto home page and Add a product to cart */
		getHomePage().addProductsToTheCart(productNames);
		
		/* Step-2: Goto my cart page and  edit the item and update the product quantity */
		getCartPage().clickOnViewAndEditLink();
		getCartPage().updateProductQuantityInProductDetailsPage(productQty);
		
		/* Step-3: verify the product quantity in my cart */
		getCartPage().verifyProductQuantity(productQty);
		getCartPage().verifyItemQuantityAfterIncreaseOrDecrease(productQty);		
		
		softAssert.assertAll();
	}
	
	@Test(groups = "Regression", testName = "TT_54", description = "Delete a product in my cart page")
	public void TT_54_deleteProductInMycart() {
		
		Map<String, String> loginCredentialsMapData = jsonReader.readJsonMapData("userCredentials","user_logincredentials");
		String[] productNames = jsonReader.readJsonData("verificationData", "mycart", "productsname").split(";");
		String emptyCartMessage = jsonReader.readJsonData("verificationData", "mycart", "emptycart");
		
		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Login to the application */
		getHomePage().userLogin(loginCredentialsMapData);
		getCartPage().emptyTheCartNow();
		
		/* Step-1: Goto home page and Add a product to cart */
		getHomePage().addProductsToTheCart(productNames);
		
		/* Step-2: Goto my cart page and  delete product */
		getCartPage().clickOnViewAndEditLink();
		getCartPage().deleteProductInMyCart();
		
		/* Step-3: verify the product quantity in my cart */
		getCartPage().verifyCartEmptyMessage(emptyCartMessage);
		
		softAssert.assertAll();
	}
	
	@Test(groups = "Regression", testName = "TT_55", description = "clear a product in the shopping cart")
	public void TT_55_clearProductInMycart() {
		
		Map<String, String> loginCredentialsMapData = jsonReader.readJsonMapData("userCredentials","user_logincredentials");
		String[] productNames = jsonReader.readJsonData("verificationData", "mycart", "productsname").split(";");
		String emptyCartMessage = jsonReader.readJsonData("verificationData", "mycart", "emptycart");

		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Login to the application */
		getHomePage().userLogin(loginCredentialsMapData);
		getCartPage().emptyTheCartNow();
		
		/* Step-1: Goto home page and Add a product to cart */
		getHomePage().addProductsToTheCart(productNames);
		
		/* Step-2: Goto my cart page and  clear a product */
		getCartPage().clickOnViewAndEditLink();
		getCartPage().clearProductInMyCart();
		
		/* Step-3: verify the product quantity in my cart */
		getCartPage().verifyCartEmptyMessage(emptyCartMessage);
		
		softAssert.assertAll();
	}
	
	@Test(groups = "Regression", testName = "TT_56", description = "Apply a invalid discount code")
	public void TT_56_verifyErrorMessageForInvalidDiscountCode() {
		
		Map<String, String> loginCredentialsMapData = jsonReader.readJsonMapData("userCredentials","user_logincredentials");
		String[] productNames = jsonReader.readJsonData("verificationData", "mycart", "productsname").split(";");
		String invalidCouponCode = jsonReader.readJsonData("verificationData", "discountcode", "invalidcouponcode");
		String invalidCouponCodeMessage = jsonReader.readJsonData("verificationData", "mycart", "invaliddiscountcodeerror");
		
		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Login to the application */
		getHomePage().userLogin(loginCredentialsMapData);
		getCartPage().emptyTheCartNow();
		
		/* Step-1: Goto home page and Add a product to cart */
		getHomePage().addProductsToTheCart(productNames);
		
		/* Step-2: Goto my cart page and  apply an invalid discount code */
		getCartPage().clickOnViewAndEditLink();
		getCartPage().applyDiscountCode(invalidCouponCode);
		
		/* Step-3: check the error message for invalid discount code */
		getCartPage().verifyMyCartPageMessage(invalidCouponCode,invalidCouponCodeMessage);
		
		softAssert.assertAll();
		
	}
	
	@Test(groups = "Regression", testName = "TT_57", description = "Apply a valid discount code")
	public void TT_57_applyValidDiscountCode() {
		
		Map<String, String> loginCredentialsMapData = jsonReader.readJsonMapData("userCredentials","user_logincredentials");
		String[] productNames = jsonReader.readJsonData("verificationData", "mycart", "productsname").split(";");		
		String  validcouponcode = jsonReader.readJsonData("verificationData", "discountcode", "validcouponcode");
		String validCouponCodeMessage = jsonReader.readJsonData("verificationData", "mycart", "validcouponcodesuccessmessage");
		
		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Login to the application */
		getHomePage().userLogin(loginCredentialsMapData);
		getCartPage().emptyTheCartNow();
		
		/* Step-1: Goto home page and Add a product to cart */
		getHomePage().addProductsToTheCart(productNames);
		
		/* Step-2: Goto my cart page and  apply an invalid discount code */
		getCartPage().clickOnViewAndEditLink();
		getCartPage().applyDiscountCode(validcouponcode);
		
		/* Step-3: check the message for valid discount code */
		getCartPage().verifyMyCartPageMessage(validcouponcode,validCouponCodeMessage);
		
		softAssert.assertAll();
	}
	
	@Test(groups = "Regression", testName = "TT_58", description = "Cancel the applied discount code")
	public void TT_58_cancelAppliedDiscountCode() {
		
		Map<String, String> loginCredentialsMapData = jsonReader.readJsonMapData("userCredentials","user_logincredentials");
		Map<String, String> myCartPageMapData = jsonReader.readJsonMapData("verificationData", "mycart");
		String[] productNames = myCartPageMapData.get("productsname").split(";");	
		String  validcouponcode = jsonReader.readJsonData("verificationData", "discountcode", "validcouponcode");
		
		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Login to the application */
		getHomePage().userLogin(loginCredentialsMapData);
		getCartPage().emptyTheCartNow();
		
		/* Step-1: Goto home page and Add a product to cart */
		getHomePage().addProductsToTheCart(productNames);
		
		/* Step-2: Goto my cart page and  apply an invalid discount code */
		getCartPage().clickOnViewAndEditLink();
		getCartPage().applyAndCancelTheAppliedDiscountCode(validcouponcode);
		
		/* Step-3: check the message for canceled discount code */
		getCartPage().verifyMyCartPageMessage(validcouponcode,myCartPageMapData.get("cancelapplieddiscountcode"));
		
		softAssert.assertAll();
	}
	
}
