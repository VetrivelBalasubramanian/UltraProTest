package com.ultrapro.test.regression;

import java.util.Map;

import org.testng.annotations.Test;

import com.ultrapro.commonUtils.InitPageObjectClasses;

public class AddressBookPage_Test extends InitPageObjectClasses {

	@Test(groups = { "Regression" }, testName = "TT_37", description = "Change the default billing address")
	public void TT_37_changeDefaultBillingAddress() {

		Map<String, String> loginCredentialsMapData = jsonReader.readJsonMapData("userCredentials","user1_logincredentials");
		Map<String, String> editDefaultbillingAddress = jsonReader.readJsonMapData("purchaseRequiredDetails","editdefaultbillingaddress");
		String addressDetail = jsonReader.readJsonData("verificationData", "addressbookpage", "editbillingaddress");

		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Step-1: Goto My Account page */
		getHomePage().userLogin(loginCredentialsMapData);
		getHomePage().clickOnMyAccountMenu();

		/* Step-2: Click on Address book in sub menu */
		getMyOrdersPage().selectMyAccountSideNavMenu("address book");

		/* Step-3: Edit default billing address */
		getAddressBookPage().editDefaultBillingAddress(editDefaultbillingAddress);

		/* Step-5: Verify the address got added as default billing address */
		getAddressBookPage().verifybillingAddress(addressDetail);

		softAssert.assertAll();
	}

	@Test(groups = { "Regression" }, testName = "TT_38", description = "Change the default shiping address")
	public void TT_38_changeDefaultShipingAddress() {

		Map<String, String> loginCredentialsMapData = jsonReader.readJsonMapData("userCredentials","user1_logincredentials");
		Map<String, String> editDefaultShippingAddress = jsonReader.readJsonMapData("purchaseRequiredDetails","editdefaultshippingaddress");
		String addressDetail = jsonReader.readJsonData("verificationData", "addressbookpage", "editshippingaddress");

		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Step-1: Goto My Account page */
		getHomePage().userLogin(loginCredentialsMapData);
		getHomePage().clickOnMyAccountMenu();

		/* Step-2: Click on Address book in sub menu */
		getMyOrdersPage().selectMyAccountSideNavMenu("address book");

		/* Step-3: Edit default billing address */
		getAddressBookPage().editDefaultShippingAddress(editDefaultShippingAddress);

		/* Step-5: Verify the address got added as default billing address */
		getAddressBookPage().verifyShippingAddress(addressDetail);

		softAssert.assertAll();
	}

	@Test(groups = {"Regression" }, testName = "TT_39", description = "Check the error message for mandatory fields in the Add Address book page")
	public void TT_39_checkMandatoryFieldErrorMessage() {

		Map<String, String> loginCredentialsMapData = jsonReader.readJsonMapData("userCredentials","user1_logincredentials");
		String[] addressBookMandatoryErrorMessages = jsonReader.readJsonData("verificationData", "addressbookpage", "errormessage").split(";");

		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Step-1: Goto My Account page */
		getHomePage().userLogin(loginCredentialsMapData);
		getHomePage().clickOnMyAccountMenu();

		/* Step-2: Click on Address book in sub menu */
		getMyOrdersPage().selectMyAccountSideNavMenu("address book");

		/* Step-3: save a address without mandatory field */
		getAddressBookPage().addNewAddressWithoutMandatoryField();

		/* Step-4: Verify the error message on mandatory fields */
		getAddressBookPage().verifyAddressBookPageError(addressBookMandatoryErrorMessages);

		softAssert.assertAll();
	}

	@Test(groups = { "Regression" }, testName = "TT_40", description = "Add new address as default billing address")
	public void TT_40_addNewDefaultBillingAddress() {
		
		Map<String, String> loginCredentialsMapData = jsonReader.readJsonMapData("userCredentials","user1_logincredentials");
		Map<String, String> defaultShippingAddress = jsonReader.readJsonMapData("purchaseRequiredDetails","newdefaultbillingaddress");
		String billingAddressDetails = jsonReader.readJsonData("verificationData", "addressbookpage", "billingaddress");

		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Step-1: Goto My Account page */
		getHomePage().userLogin(loginCredentialsMapData);
		getHomePage().clickOnMyAccountMenu();

		/* Step-2: Click on Address book in sub menu */
		getMyOrdersPage().selectMyAccountSideNavMenu("address book");

		/* Step-3: Create new address as default billing address */
		getAddressBookPage().addNewAddressAsDefaultBillingAddress(defaultShippingAddress);

		/* Step-5: Verify the address got added as default billing address */
		getAddressBookPage().verifyDefaultBillingAddress(billingAddressDetails);

		softAssert.assertAll();
	}

	@Test(groups = { "Regression" }, testName = "TT_41", description = "Add new address as default shipping")
	public void TT_41_addNewDefaultShippingAddress() {

		Map<String, String> loginCredentialsMapData = jsonReader.readJsonMapData("userCredentials","user1_logincredentials");
		Map<String, String> defaultShippingAddress = jsonReader.readJsonMapData("purchaseRequiredDetails","newdefaultshippingaddress");
		String addressDetails = jsonReader.readJsonData("verificationData", "addressbookpage", "shippingaddress");

		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Step-1: Goto My Account page */
		getHomePage().userLogin(loginCredentialsMapData);
		getHomePage().clickOnMyAccountMenu();

		/* Step-2: Click on Address book in sub menu */
		getMyOrdersPage().selectMyAccountSideNavMenu("address book");

		/* Step-3: Create new address as default shipping address */
		getAddressBookPage().addNewAddressAsDefaultShippingAddress(defaultShippingAddress);

		/* Step-54: Verify the address got added as default shipping address */
		getAddressBookPage().verifyDefaultShippingAddress(addressDetails);

		softAssert.assertAll();
	}

	@Test(groups = { "Regression" }, testName = "TT_42", description = "Edit address details")
	public void TT_42_EditAddress() {
		
		Map<String, String> loginCredentialsMapData = jsonReader.readJsonMapData("userCredentials","user1_logincredentials");
		Map<String, String> addNewAddress = jsonReader.readJsonMapData("purchaseRequiredDetails", "addnewaddress");
		Map<String, String> editAddress = jsonReader.readJsonMapData("purchaseRequiredDetails", "editaddress");
		Map<String, String> addressbookpage = jsonReader.readJsonMapData("verificationData", "addressbookpage");

		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Step-1: Goto My Account page */
		getHomePage().userLogin(loginCredentialsMapData);
		getHomePage().clickOnMyAccountMenu();

		/* Step-2: Click on Address book in sub menu and delete all the additional address */
		getMyOrdersPage().selectMyAccountSideNavMenu("address book");
		getAddressBookPage().deleteAddtionalAddress();
		getAddressBookPage().verifyAllAdditionalAddressGotDeleted(addressbookpage.get("noadditionaladdressmsg"));

		/* Step-3: Add a new additional address */
		getAddressBookPage().addNewAddress(addNewAddress);
		getAddressBookPage().verifyAdditionalAddress(addressbookpage.get("newaddress"));

		/* Step-4: Edit additional address details */
		getAddressBookPage().editAdditionalAddress(editAddress);

		/* Step-5: Verify the additional address detail got edited */
		getAddressBookPage().verifyAdditionalAddress(addressbookpage.get("editaddress"));

		softAssert.assertAll();
	}

	@Test(groups = { "Regression" }, testName = "TT_43", description = "Delete address from the address book")
	public void TT_43_DeleteAddress() {

		Map<String, String> loginCredentialsMapData = jsonReader.readJsonMapData("userCredentials","user1_logincredentials");
		String noAddressMessage = jsonReader.readJsonData("verificationData", "addressbookpage","noadditionaladdressmsg");

		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Step-1: Goto My Account page */
		getHomePage().userLogin(loginCredentialsMapData);
		getHomePage().clickOnMyAccountMenu();

		/* Step-2: Click on Address book in sub menu */
		getMyOrdersPage().selectMyAccountSideNavMenu("address book");

		/* Step-3: Goto Add new address page */
		getAddressBookPage().deleteAddtionalAddress();

		/* Step-4: Verify the additional address detail got edited */
		getAddressBookPage().verifyAllAdditionalAddressGotDeleted(noAddressMessage);

		softAssert.assertAll();
	}
}
