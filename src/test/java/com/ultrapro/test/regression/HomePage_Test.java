package com.ultrapro.test.regression;

import java.util.Map;

import org.testng.annotations.Test;

import com.ultrapro.commonUtils.InitPageObjectClasses;

public class HomePage_Test extends InitPageObjectClasses {

	//@Test(groups = "Sanity", testName = "TT_01", description = "Verify on searching any product in search box should display the appropriate result")
	public void TT_01_verifySearchProductsNamesListFromSearchBox() {

		String[] searchText = jsonReader.readJsonData("verificationData", "searchbox", "searchtext").split(";");

		/* Accept News Letter */
		getHomePage().acceptNewesletter();
		
		/* Step-1: Search 1 the product */
		getHomePage().searchTheProducts(searchText);

		/* Step-2: Verify the expected product list in the search response */
		getHomePage().verifySearchResultsProductsName(searchText);

		softAssert.assertAll();

	}
	
	//@Test(groups = "Regression", testName = "TT_02", description = "Verify the forgot password link working")
	public void TT_02_verifyUserNavigatesToForgotPasswordPage() {

		Map<String, String> forgotPasswordMapData = jsonReader.readJsonMapData("verificationData", "forgotpasswordpage");
		
		/* Accept News Letter */
		getHomePage().acceptNewesletter();
		
		/* Step-1: Verify current environment sign in */
		getHomePage().clickOnForgotPasswordLink();		

		/* Step-2: Open new tabs with different sites and check the sign functionality */
		getHomePage().verifyForgotYourPasswordRecoveryFunctionality(forgotPasswordMapData);

		softAssert.assertAll();
	}
	
	//@Test(groups = {"Sanity"}, testName = "TT_03",description = "Verify the Home page submenu presents")
	public void TT_03_verifyHomePageSubMenus() {

		Map<String, String> homePageMapData = jsonReader.readJsonMapData("verificationData", "homepage");
		String[] expectedSubMenus = homePageMapData.get("submenus").split(";");

		/* Accept News Letter */
		getHomePage().acceptNewesletter();
		
		/* Step-1: Verify the home page sub menus */
		getHomePage().verifyHomePageSubMenus(expectedSubMenus);

		softAssert.assertAll();
	}

	//@Test(groups = "Sanity", testName = "TT_04",  description = "Verify he logged-in user name in the header")
	public void TT_04_verifyTheLoggedInUserNameInTheHeader() {

		Map<String, String> validLoginCredentialsMapData = jsonReader.readJsonMapData("userCredentials","user_logincredentials");
		String  expectedUserNameInWelcomeText = jsonReader.readJsonData("verificationData", "homepage", "usernameintheheader");

		/* Accept News Letter */
		getHomePage().acceptNewesletter();
		
		/* Step-1: Check the Sign In menu and click to enter the credentials */
		getHomePage().userLogin(validLoginCredentialsMapData);		

		/* Step-2: Verify Logged in User Name in the Header  */
		getHomePage().verifyLoggedInUserNameInTheHeader(expectedUserNameInWelcomeText);
		
		softAssert.assertAll();
	}
	
	//@Test(groups = "Regression", testName = "TT_05",description = "Verify the sign in with invalid credentials")
	public void TT_05_verifySigninWithInvalidCredentials() {

		Map<String, String> inValidLoginCredentialsMapData = jsonReader.readJsonMapData("userCredentials","user_signinwithinvalidcredentials");
		String invalidCredentialsErrorMessage = jsonReader.readJsonData("verificationData", "invaliderrormessage", "invaliderrormesg");

		/* Accept News Letter */
		getHomePage().acceptNewesletter();
		
		/* Step-1: Verify current environment sign in with invalid credential*/
		getHomePage().userLogin(inValidLoginCredentialsMapData);		

		/* Step-2: Verify sign in with invalid credentials error message */
		getHomePage().verifyInvalidCredentialsErrorMessage(invalidCredentialsErrorMessage);
		
		softAssert.assertAll();
	}

	@Test(groups = {"Sanity"}, testName = "TT_65",description = "Verify the Home page Sub Category presents")
	public void TT_65_verifyHomePageSubCategory() {

		Map<String, String> homePageMapData = jsonReader.readJsonMapData("verificationData", "homepage");
		String[] expectedSubMenus = homePageMapData.get("subcategory").split(";");

		/* Accept News Letter */
		getHomePage().acceptNewesletter();
		
		/* Step-1: Verify the Home Page Sub Category Menus */
		getHomePage().verifyHomePageSubCategoryMenus(expectedSubMenus);;

		softAssert.assertAll();
	}
	
	@Test(groups = "Sanity", testName = "TT_66", description = "Check the add to cart button shown under all products in home page")
	public void TT_66_verifyAddToCartButtonUnderAllProductsTest() {

		Map<String, String> productsNameMapData = jsonReader.readJsonMapData("productcatalouge", "productnames");
		String[] productsName = productsNameMapData.get("names").split(";");

		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Verify 'Add To Cart' button in each products */
		getHomePage().verifyAddToCartButtonAvailableForTheProducts(productsName);		

		softAssert.assertAll();
	}
	
	@Test(groups = {"Regression"}, testName = "TT_67",description = "Verify the About page is unique contents with localized text for different stores")
	public void TT_67_verifyAboutPageContent() {

		Map<String, String> aboutPageContentsMapData = jsonReader.readJsonMapData("verificationData", "aboutpage");		
		String aboutPageContents = aboutPageContentsMapData.get("content");
		
		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Step-1: Click on Read More */ 
		getHomePage().clickOnReadMore();		

		/* Step-2: Verify the page content */ 
		getHomePage().verifyAboutPageBodyContent(aboutPageContents);

		softAssert.assertAll();
	}
}
