package com.ultrapro.test.regression;

import java.util.Map;

import org.testng.annotations.Test;

import com.ultrapro.commonUtils.InitPageObjectClasses;

public class MyWishListPage_Test extends InitPageObjectClasses {

	@Test(groups = "Regression", testName = "TT_44", description = "Verify added whish list products showing in 'My wishlist' page")
	public void TT_44_verifyAddedWhishListProductsShowingInMyWishlistPage() {

		Map<String, String> loginCredentialsMapData = jsonReader.readJsonMapData("userCredentials","user_logincredentials");
		Map<String, String> wishListPageMapData = jsonReader.readJsonMapData("verificationData", "mywishlistpage");
		String[] productNames = wishListPageMapData.get("productsname").split(";");

		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Login to the application */
		getHomePage().userLogin(loginCredentialsMapData);
		
		/* Empty the cart if already products added and remove Wish listed products*/
		getCartPage().emptyTheCartNow();
		getHomePage().clickOnMyAccountMenu();
		getMyWishListPage().removeWishlistedProducts();

		/* Step-1: Add products to wish list */
		getHomePage().searchTheProducts(productNames);
		getMyWishListPage().addProductsToWishlist(productNames);

		/* Step-2: Goto My Wish list page */
		getHomePage().clickOnMyAccountMenu();
		getMyOrdersPage().selectMyAccountSideNavMenu("My Wishlist");

		/* Step-3: Verify the added to your wish list page */
		getMyWishListPage().verifyAddedWishListProducts(productNames);

		softAssert.assertAll();
	}

	@Test(groups = "Regression", testName = "TT_45", description = "Verify the share wish list by valid email id")
	public void TT_45_verifyShareWishListByValidEmailId() {

		Map<String, String> loginCredentialsMapData = jsonReader.readJsonMapData("userCredentials","user_logincredentials");
		Map<String, String> wishListPageMapData = jsonReader.readJsonMapData("verificationData", "mywishlistpage");
		String[] productNames = wishListPageMapData.get("productsname").split(";");

		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Login to the application */
		getHomePage().userLogin(loginCredentialsMapData);
		
		/* Empty the cart if already products added and remove Wish listed products*/
		getCartPage().emptyTheCartNow();
		getHomePage().clickOnMyAccountMenu();
		getMyWishListPage().removeWishlistedProducts();

		/* Step-1: Add products to wish list */
		getHomePage().searchTheProducts(productNames);
		getMyWishListPage().addProductsToWishlist(productNames);

		/* Step-2: Goto My Wish list page */
		getHomePage().clickOnMyAccountMenu();
		getMyOrdersPage().selectMyAccountSideNavMenu("My Wishlist");

		/* Step-3: Go to share wish list page and fill the text field */
		getMyWishListPage().shareTheWishList(wishListPageMapData.get("email"), wishListPageMapData.get("message"));

		/* Step-4: verify the shared wish list action */
		getMyWishListPage().verifySharedWishListAction();

		softAssert.assertAll();
	}

	@Test(groups = "Regression", testName = "TT_46", description = "Verify the share wish list by empty email id")
	public void TT_46_verifyShareWishListByEmptyEmailId() {

		Map<String, String> loginCredentialsMapData = jsonReader.readJsonMapData("userCredentials","user_logincredentials");
		Map<String, String> wishListPageMapData = jsonReader.readJsonMapData("verificationData", "mywishlistpage");
		String[] productNames = wishListPageMapData.get("productsname").split(";");

		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Login to the application */
		getHomePage().userLogin(loginCredentialsMapData);
		
		/* Empty the cart if already products added and remove Wish listed products*/
		getCartPage().emptyTheCartNow();
		getHomePage().clickOnMyAccountMenu();
		getMyWishListPage().removeWishlistedProducts();

		/* Step-1: Add products to wish list */
		getHomePage().searchTheProducts(productNames);
		getMyWishListPage().addProductsToWishlist(productNames);

		/* Step-2: Goto My Wish list page */
		getHomePage().clickOnMyAccountMenu();
		getMyOrdersPage().selectMyAccountSideNavMenu("My Wishlist");

		/* Step-3: verify the share wish list by invalid email id */
		getMyWishListPage().clickOnShareWishList();

		/* Step-4: verify the error message */
		getMyWishListPage().verifyTheShareWishListErrorMsg(wishListPageMapData.get("sharederrormessage"));

		softAssert.assertAll();
	}

	@Test(groups = "Regression", testName = "TT_47", description = "Verify item qty update functionality")
	public void TT_47_updateWishListPage() {

		Map<String, String> loginCredentialsMapData = jsonReader.readJsonMapData("userCredentials","user1_logincredentials");
		Map<String, String> wishListPageMapData = jsonReader.readJsonMapData("verificationData", "mywishlistpage");
		String[] productNames = wishListPageMapData.get("productsname").split(";");

		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Login to the application */
		getHomePage().userLogin(loginCredentialsMapData);
		
		/* Empty the cart if already products added and remove Wish listed products*/
		getCartPage().emptyTheCartNow();
		getHomePage().clickOnMyAccountMenu();
		getMyWishListPage().removeWishlistedProducts();

		/* Step-1: Add products to wish list */
		getHomePage().searchTheProducts(productNames);
		getMyWishListPage().addProductsToWishlist(productNames);

		/* Step-2: Click the add to wish list */
		getHomePage().clickOnMyAccountMenu();
		getMyOrdersPage().selectMyAccountSideNavMenu("My Wishlist");

		/* Step-3: Go to my wish list and edit qty */
		getMyWishListPage().editItemQtyAndUpdateWishlist("2");

		/* Step-4: Verify the item qty to updated qty */
		getMyWishListPage().verifyItemQty("2");

		softAssert.assertAll();
	}

	@Test(groups = "Regression", testName = "TT_48", description = "Verify products added to cart if clicking on Add All To Cart button")
	public void TT_48_verifyProductsAddedToCartIfClickingOnAddAllToCartButton() {

		Map<String, String> loginCredentialsMapData = jsonReader.readJsonMapData("userCredentials","user_logincredentials");
		Map<String, String> wishListPageMapData = jsonReader.readJsonMapData("verificationData", "mywishlistpage");
		String[] productNames = wishListPageMapData.get("productsname").split(";");

		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Login to the application */
		getHomePage().userLogin(loginCredentialsMapData);
		
		/* Empty the cart if already products added and remove Wish listed products*/
		getCartPage().emptyTheCartNow();
		getHomePage().clickOnMyAccountMenu();
		getMyWishListPage().removeWishlistedProducts();

		/* Step-1: Add products to wish list */
		getHomePage().searchTheProducts(productNames);
		getMyWishListPage().addProductsToWishlist(productNames);

		/* Step-2: Goto My Wish list page */
		getHomePage().clickOnMyAccountMenu();
		getMyOrdersPage().selectMyAccountSideNavMenu("My Wishlist");

		/* Step-3: Click on the add all to cart button */
		getMyWishListPage().addAllWishlistedProductToCart();

		/* Step-4: verify the added to shopping cart and empty wish list */
		getCartPage().clickOnMiniCartIcon();
		getCartPage().verifyProductsName();

		softAssert.assertAll();
	}

	@Test(groups = "Regression", testName = "TT_49", description = "Add a single product to the cart from wish list side menu")
	public void TT_49_verifyProductAddedToTheCartFromWishlistSideMenu() {

		Map<String, String> loginCredentialsMapData = jsonReader.readJsonMapData("userCredentials","user1_logincredentials");
		Map<String, String> wishListPageMapData = jsonReader.readJsonMapData("verificationData", "mywishlistpage");
		String[] productNames = wishListPageMapData.get("productsname").split(";");

		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Login to the application */
		getHomePage().userLogin(loginCredentialsMapData);
		
		/* Empty the cart if already products added and remove Wish listed products*/
		getCartPage().emptyTheCartNow();
		getHomePage().clickOnMyAccountMenu();
		getMyWishListPage().removeWishlistedProducts();

		/* Step-1: Add products to wish list */
		getHomePage().searchTheProducts(productNames);
		getMyWishListPage().addProductsToWishlist(productNames);

		/* Step-2: Goto My Wish list page */
		getHomePage().clickOnMyAccountMenu();

		/* Step-3: Click on the add all to cart button */
		getMyWishListPage().addToTheCartFromWishlistSideMenu();

		/* Step-4: Verify Products Name In The Cart */
		getCartPage().clickOnMiniCartIcon();
		getCartPage().verifyProductsName();

		softAssert.assertAll();
	}

	@Test(groups = "Regression", testName = "TT_50", description = "Increase the product quantity and add to cart and verify the product in mini cart with same quantity")
	public void TT_50_verifyProductQtyAfterIncreasedAndAddedToCart() {

		Map<String, String> loginCredentialsMapData = jsonReader.readJsonMapData("userCredentials","user_logincredentials");
		Map<String, String> wishListPageMapData = jsonReader.readJsonMapData("verificationData", "mywishlistpage");
		String[] productNames = wishListPageMapData.get("productsname").split(";");

		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Login to the application */
		getHomePage().userLogin(loginCredentialsMapData);
		
		/* Empty the cart if already products added and remove Wish listed products*/
		getCartPage().emptyTheCartNow();
		getHomePage().clickOnMyAccountMenu();
		getMyWishListPage().removeWishlistedProducts();

		/* Step-1: Add products to wish list */
		getHomePage().searchTheProducts(productNames);
		getMyWishListPage().addProductsToWishlist(productNames);

		/* Step-2: Goto My Wish list page */
		getHomePage().clickOnMyAccountMenu();
		getMyOrdersPage().selectMyAccountSideNavMenu("My Wishlist");

		/* Step-3: Increase product qty and add to cart */
		getMyWishListPage().increaseProductQtyAndAddToCart("2");

		/* Step-4: Verify num of products in cart */
		getCartPage().verifyNumOfItemsInCart(2);

		softAssert.assertAll();
	}

	@Test(groups = "Regression", testName = "TT_51", description = "Verify the 'Remove item' from the wishlist removed the product from Wishlist")
	public void TT_51_verifyRemoveItemFromWishlist() {

		Map<String, String> loginCredentialsMapData = jsonReader.readJsonMapData("userCredentials","user1_logincredentials");
		Map<String, String> wishListPageMapData = jsonReader.readJsonMapData("verificationData", "mywishlistpage");
		String[] productNames = wishListPageMapData.get("productsname").split(";");

		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Login to the application */
		getHomePage().userLogin(loginCredentialsMapData);
		getCartPage().emptyTheCartNow();

		/* Step-1: Add products to wish list */
		getHomePage().searchTheProducts(productNames);
		getMyWishListPage().addProductsToWishlist(productNames);

		/* Step-2: Remove Wish listed Products */
		getHomePage().clickOnMyAccountMenu();
		getMyOrdersPage().selectMyAccountSideNavMenu("My Wishlist");
		getMyWishListPage().removeItemFromWishlist();

		/* Step-3: Verify The Remove Wish listed Products Page */
		getMyWishListPage().verifyEmptyWishlistMsg();

		softAssert.assertAll();

	}

}