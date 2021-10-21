package com.ultrapro.pageObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ultrapro.commonUtils.InitPageObjectClasses;

public class AddressBookPage extends InitPageObjectClasses {

	
	public AddressBookPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	/* Page Objects */
	
	@FindBy(xpath = "//button[@class='action primary add']")
	private WebElement btn_AddNewAddress;
	
	@FindBy(xpath = "//button[@class='action save primary']//span")
	private WebElement btn_SaveAddress;
	
	@FindBy(xpath = "//div[@class='mage-error']")
	private List<WebElement> text_ErrorMessages;
	
	@FindBy(id = "primary_shipping")
	private WebElement checkBox_DefaultShipping;
	
	@FindBy(className = "legend")
	private WebElement lable_AddNewAddress;  
	
	@FindBy(id = "company")
	private WebElement textField_Company;
	
	@FindBy(id = "telephone")
	private WebElement textField_Telephone;
	
	
	@FindBy(id = "street_1")
	private WebElement textField_Street_1;
	
	@FindBy(id = "street_2")
	private WebElement textField_Street_2;
	
	@FindBy(id = "street_3")
	private WebElement textField_Street_3;
	
	@FindBy(id = "city")
	private WebElement textField_City;
	
	@FindBy(id = "zip")
	private WebElement textField_Zip;
	
	@FindBy(id = "region_id")
	private WebElement dropDown_Region;
	
	@FindBy(id = "country")
	private WebElement dropDown_Country;
	
	@FindBy(xpath = "//div[@class='box box-address-shipping']//div[@class='box-content']")
	private WebElement box_ShippingAddress;
	
	@FindBy(id = "primary_billing")
	private WebElement checkBox_DefaultBilling;
	
	@FindBy(xpath = "//div[@class='box box-address-billing']//div[@class='box-content']")
	private WebElement box_BillingAddress;
	
	@FindBy(xpath = "//div[@class='box box-address-billing']//a[@class='action edit']")
	private WebElement link_ChangeBillingAddress;
	
	@FindBy(xpath = "//div[@class='box box-address-shipping']//a[@class='action edit']")
	private WebElement link_EditShippingAddress;
	
	@FindBy(xpath = "//a[@role='delete-address']//span")
	private List<WebElement> link_DeleteAddress;
	
	@FindBy(xpath = "//button[@class='action-primary action-accept']")
	private WebElement btn_DeleteAddress;
	
	@FindBy(xpath = "//div[@class='block-content']//p[@class='empty']")
	private WebElement lable_NoAdditionalAddress;
	
	@FindBy(xpath = "//table[@class='data table table-additional-addresses-items history']//tbody")
	private WebElement box_AdditionalAddress;
	
	@FindBy(xpath = "//div[@class='block block-addresses-list']//a[@class='action edit']//span")
	private WebElement link_EditAddress;
	
	/* Page Methods */
	
	/**
	 * Method to add a new address without mandatory field
	 * @author 
	 */
	public void addNewAddressWithoutMandatoryField() {	
		logger.info("Add new address with empty data for mandatory fields");

		progressHelpers.waitForElementToBeClickable(btn_AddNewAddress);
		buttonHelper.click(btn_AddNewAddress);
		progressHelpers.waitForElementToBeClickable(btn_SaveAddress);
		buttonHelper.click(btn_SaveAddress);
	}
	
	/**
	 * Method to check the mandatory field errors
	 * @author 
	 * @param addressBookErrors
	 */
	public void verifyAddressBookPageError(String[] addressBookErrors) {
		logger.info("Verifying the Address book page error");
		
		List<String> expectedErrorMessage = new ArrayList<String>();
		List<String> actualErrorMessage = new ArrayList<String>();
		
		actualErrorMessage = generalHelper.getAttributeValuesFromListOfElements(text_ErrorMessages, "innerText");
		
		for (WebElement string : text_ErrorMessages) {
			String text = string.getText();
			System.out.println(text);
		}String string = text_ErrorMessages.toString();
		
		System.out.println(string);
		for (String addressBookError : addressBookErrors) {
			expectedErrorMessage.add(addressBookError);
		}
		for (String actual : actualErrorMessage) {
			System.out.println("Actual----"+actual);
			
		}
		for (String expected : expectedErrorMessage) {
			System.out.println("Expected----"+expected);
		}
		assertHelper.assertListsAfterSorting(expectedErrorMessage, actualErrorMessage);
	}
	
	/**
	 * Method to Add new address as default shipping address
	 * @author 
	 * @param AddressDetailsMapData
	 */
	public void addNewAddressAsDefaultShippingAddress(Map<String, String> AddressDetailsMapData) {	
		logger.info("Adding new address as default shipping address");

		buttonHelper.click(btn_AddNewAddress);
		enterAddressDetails(AddressDetailsMapData);
		buttonHelper.click(btn_SaveAddress);
	}
	
	/**
	 * Method to enter the address details in the form
	 * @author
	 * @param AddressDetailsMapData
	 */
	public void enterAddressDetails(Map<String, String> AddressDetailsMapData) {
		logger.info("Enter Address details in the form");

		progressHelpers.waitForPageLoad();
		progressHelpers.waitForElementToDisplay(lable_AddNewAddress);
	
		generalHelper.enterTextInTextField(textField_Telephone, AddressDetailsMapData.get("PhoneNumber"));
		generalHelper.enterTextInTextField(textField_Street_1, AddressDetailsMapData.get("Street1"));
		generalHelper.enterTextInTextField(textField_City, AddressDetailsMapData.get("City"));
		dropDownHelper.SelectUsingVisibleValue(dropDown_Region, AddressDetailsMapData.get("State"));
		generalHelper.enterTextInTextField(textField_Zip, AddressDetailsMapData.get("Zip"));		
		dropDownHelper.SelectUsingVisibleValue(dropDown_Country, AddressDetailsMapData.get("Country"));
	}
	
	/**
	 * Method to verify the shipping address
	 * @author 
	 * @param expectedShippingAddress
	 */
	public void verifyDefaultShippingAddress(String expectedShippingAddress) {	
		logger.info("verify the default shipping address");
		
		assertHelper.assertTextMatching(expectedShippingAddress, box_ShippingAddress.getAttribute("innerText"));	
	}
	
	/**
	 * Method to Add new address as default billing address
	 * @author 
	 * @param AddressDetailsMapData
	 */
	public void addNewAddressAsDefaultBillingAddress(Map<String, String> AddressDetailsMapData) {	
		logger.info("Adding new address as default billing address");

		buttonHelper.click(btn_AddNewAddress);
		enterAddressDetails(AddressDetailsMapData);
		buttonHelper.click(btn_SaveAddress);
	}
	
	/**
	 * Method to verify the shipping address
	 * @author 
	 * @param expectedBillingAddress
	 */
	public void verifyDefaultBillingAddress(String expectedBillingAddress) {	
		logger.info("verify the default billing address");
		
		assertHelper.assertTextMatching(expectedBillingAddress, box_BillingAddress.getAttribute("innerText"));		
	}
	
	/**
	 * Method to change the default billing address
	 * @author 
	 * @param AddressDetailsMapData
	 */
	public void editDefaultBillingAddress(Map<String, String> AddressDetailsMapData) {	
		logger.info("Edit default billing address");
		
		buttonHelper.click(link_ChangeBillingAddress);
		enterAddressDetails(AddressDetailsMapData);		
		buttonHelper.click(btn_SaveAddress);
	}
	
	/**
	 * Method to verify the billing address
	 * @author 
	 * @param expectedBillingAddress
	 */
	public void verifybillingAddress(String expectedBillingAddress) {	
		logger.info("verify the billing address");
		
		assertHelper.assertTextMatching(expectedBillingAddress, box_BillingAddress.getAttribute("innerText").trim());
	}
	
	/**
	 * Method to change the dafault shipping address
	 * @author 
	 * @param AddressDetailsMapData
	 */
	public void editDefaultShippingAddress(Map<String, String> AddressDetailsMapData) {
		logger.info("Edit default Shipping address");

		buttonHelper.click(link_EditShippingAddress);
		enterAddressDetails(AddressDetailsMapData);
		buttonHelper.click(btn_SaveAddress);

	}
	
	
	/**
	 * Method to verify the shipping address
	 * @author 
	 * @param expectedShippingAddress
	 */
	public void verifyShippingAddress(String expectedShippingAddress) {	
		logger.info("verify the shipping address");
		
		assertHelper.assertTextMatching(expectedShippingAddress, box_ShippingAddress.getAttribute("innerText").trim());		
	}
	
	/**
	 * Method to delete the additional addresses
	 * @author 
	 */
	public void deleteAddtionalAddress() {
		logger.info("Delete all the addtional address in the section");
		
		int additionlAddressCount = link_DeleteAddress.size();	
		int count = 1;
		
		while(count<=additionlAddressCount) {
			progressHelpers.waitForPageLoad();
        	buttonHelper.click(link_DeleteAddress.get(0));
			buttonHelper.click(btn_DeleteAddress);
			count++;
		}
	}
	/**
	 * Method to verify that no more additional address message showing
	 * @author 
	 * @param noAddressMessage
	 */
	public void verifyAllAdditionalAddressGotDeleted(String noAddressMessage) {	
		logger.info("verify the billing address");

        assertHelper.assertTextMatching(noAddressMessage, generalHelper.getText(lable_NoAdditionalAddress));
	}
	
	/**
	 * Method to Add new address 
	 * @author 
	 * @param AddressDetailsMapData
	 */
	public void addNewAddress(Map<String, String> AddressDetailsMapData) {	
		logger.info("Adding new address as default billing address");
		buttonHelper.click(btn_AddNewAddress);
		enterAddressDetails(AddressDetailsMapData);
		buttonHelper.click(btn_SaveAddress);
	}
	
	/**
	 * Method to verify the edited address
	 * @author 
	 * @param expectedEditedAddress
	 */
	public void verifyAdditionalAddress(String expectedEditedAddress) {	
		logger.info("verify the additional address details");
		
		String[] replaceText = jsonReader.readJsonData("verificationData", "addressbookpage", "replacetext").split(";");
		String additionalAddress = generalHelper.getText(box_AdditionalAddress).replace(replaceText[0], "").replace(replaceText[1], "");

		assertHelper.assertTextMatching(expectedEditedAddress, additionalAddress);		
	}
	
	/**
	 * Method to edit the additional address
	 * @author 
	 * @param AddressDetailsMapData
	 */
	public void editAdditionalAddress(Map<String, String> AddressDetailsMapData) {	
		logger.info("Edit the additional address details");

		buttonHelper.click(link_EditAddress);
		enterAddressDetails(AddressDetailsMapData);
		progressHelpers.hardWait(5000);
		buttonHelper.click(btn_SaveAddress);
	}
	
}
