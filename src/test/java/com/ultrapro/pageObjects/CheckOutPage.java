package com.ultrapro.pageObjects;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ultrapro.commonUtils.InitPageObjectClasses;

public class CheckOutPage extends InitPageObjectClasses {

	public CheckOutPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	/* Element of CheckOut page */

	@FindAll({ @FindBy(id = "popup-email_address"), @FindBy(id = "customer-email") })
	private WebElement textField_Email;
	
	@FindBy(id = "customer-email")
	private WebElement textField_GuestEmail;
	
	@FindBy(name = "firstname")
	private WebElement textField_Firstname;

	@FindBy(name = "lastname")
	private WebElement textField_Lastname;

	@FindBy(name = "street[0]")
	private WebElement textField_StreetAddress;

	@FindAll({ @FindBy(id = "popup-city"), @FindBy(name = "city") })
	private WebElement textField_City;

	@FindBy(name = "city")
	private WebElement textField_GuestCity;
	
	@FindAll({ @FindBy(id = "popup-state"), @FindBy(name = "qty") })
	private WebElement drpdwn_StateSubmitReq;

	@FindBy(name = "region_id")
	private WebElement drpdwn_State;
	
	@FindBy(id = "qty")
	private WebElement drpdwn_Qty;
	
	@FindAll({ @FindBy(id = "popup-zipcode"), @FindBy(name = "postcode") })
	private WebElement textField_PostCode;

	@FindBy(name = "postcode")
	private WebElement textField_ZipCode;
	
	@FindBy(name = "telephone")
	private WebElement textField_Phone;

	@FindBy(xpath = "//input[@name='region']")
	private WebElement textField_Region;

	@FindBy(name = "ko_unique_1")
	private WebElement radioBtn_GroundShipping;

	@FindBy(name = "ko_unique_4")
	private WebElement radioBtn_NextDayShipping;

	@FindBy(name = "ko_unique_3")
	private WebElement radioBtn_SecondDayShipping;

	@FindBy(name = "ko_unique_2")
	private WebElement radioBtn_ThreeDayShipping;

	@FindBy(css = ".button.action.continue.primary")
	private WebElement btn_Next;

	@FindBy(id = "chrobin-checkbox")
	private WebElement checkBox_shippingLevel;
	
	@FindBy(xpath = "//div[@class='shipping-address-item not-selected-item']")
	private List<WebElement> list_shipping_Item_Notselected_Address;
	
	@FindBy(xpath = "//div[@class='shipping-address-item selected-item']")
	private WebElement text_DefaultSelectedShippingAddress;

	@FindBy(xpath = "(//li[@class='checkout-shipping-address']//span)[3]")
	private WebElement link_ClickHere;
	
	@FindBy(xpath = "//div[@class='product-item-name-block']//strong[@class='product-item-name']")
	private WebElement text_ItemNameInOrderSummary;
	
	@FindBy(xpath = "//div[@class='details-qty']//span[@class='value']")
	private WebElement text_ItemQtyInOrderSummary;
	
	@FindBy(xpath = "//button[@id='product-addtocart-button']")
	private WebElement btn_AddToCart;
	
	@FindBy(id = "customer-popup-registration")
	private WebElement link_SubmitRequest;
	
	@FindBy(id = "popup-storeName")
	private WebElement textField_StoreName;
	
	@FindBy(xpath = "//button[@class='action submit primary']")
	private WebElement btn_Submit;
	
	private String clickOnProductXpath = "//div[@class='product details product-item-details']//a[text()='{PARAM1}']";

	String notSelectedAddressShipHereButtonXpath = "//li[@class='checkout-shipping-address']//div[@class='shipping-address-item not-selected-item'][{PARAM1}]//button[@class='action action-select-shipping-item']";

	/**
	 * Method to enter the shipping details in the form
	 * 
	 * @author
	 * @param shippingDetailsMapData
	 */
	public void enterShippingDetailsForFirstTimeCheckoutSignedInUsers(Map<String, String> shippingDetailsMapData) {
		logger.info("Entering shipping details");

		progressHelpers.waitForElementToDisplay(textField_StreetAddress);
		generalHelper.enterTextInTextField(textField_StreetAddress, shippingDetailsMapData.get("street"));
		generalHelper.enterTextInTextField(textField_GuestCity, shippingDetailsMapData.get("city"));
		dropDownHelper.SelectUsingVisibleValue(drpdwn_State, shippingDetailsMapData.get("state"));

		generalHelper.enterTextInTextField(textField_ZipCode, shippingDetailsMapData.get("zipcode"));
		generalHelper.enterTextInTextField(textField_Phone, shippingDetailsMapData.get("phonenumber"));
	}

	/**
	 * Method to select the shipping method by given param
	 * 
	 * @author
	 * @param shippingMode
	 */
	public void selectShippingMethodAs(String shippingMode) {
		logger.info("Selecting " + shippingMode + " as shipping method");

		progressHelpers.waitForElementToBeClickable(btn_Next);

		if (radioBtn_GroundShipping.isSelected()) {
			logger.info("default selected");
			}
			else {
				switch (shippingMode) {
				case "Ground":
					javascriptHelper.clickOnElement(radioBtn_GroundShipping);
					break;
				case "NextDay":
					javascriptHelper.clickOnElement(radioBtn_NextDayShipping);
					break;
				case "SeconDday":
					javascriptHelper.clickOnElement(radioBtn_SecondDayShipping);
					break;
				case "ThreeDay":
					javascriptHelper.clickOnElement(radioBtn_ThreeDayShipping);
					break;
				default:
					break;
				}
			}
	}

	/**
	 * Method to enter the shipping details in check out page
	 * 
	 * @author
	 * @param shippingDetailsMapData
	 */
	public void enterShippingDetails(Map<String, String> shippingDetailsMapData) {
		logger.info("Entering shipping details");

		String purchasingFor = shippingDetailsMapData.get("purchasingfor");
		String shippingMethod = shippingDetailsMapData.get("shippingmethod");

		if (purchasingFor.equalsIgnoreCase("First Time")) {
			enterShippingDetailsForFirstTimeCheckoutSignedInUsers(shippingDetailsMapData);
		}
		selectShippingMethodAs(shippingMethod);

		buttonHelper.click(btn_Next);
	}

	/**
	 * Method to enter the checkout details of guest user
	 * @author 
	 */
	public void enterCheckoutDetailsForGuestUser(Map<String, String> shippingDetailsMapData) {
		logger.info("Entering the checkout details");

		progressHelpers.waitForPageLoad();
		progressHelpers.waitForElementToDisplay(textField_GuestEmail);
		generalHelper.enterTextInTextFieldCharWise(textField_GuestEmail, shippingDetailsMapData.get("email"));
		generalHelper.enterTextInTextFieldCharWise(textField_Firstname, shippingDetailsMapData.get("firstname"));
		generalHelper.enterTextInTextFieldCharWise(textField_Lastname, shippingDetailsMapData.get("lastname"));

		enterShippingDetailsForFirstTimeCheckoutSignedInUsers(shippingDetailsMapData);
		enterShippingDetails(shippingDetailsMapData);
	}
	
	/**
	 * Method to Change Shipping Address
	 * @author  
	 * @param expectedShippingAddress
	 */
	public void verifyAndchangeShippingAddress(String expectedShippingAddress) {
		logger.info("Changing the Shipping Address");

		String defaultSelectedAddress = text_DefaultSelectedShippingAddress.getText().trim();
		String shipHereButtonTextToChange = jsonReader.readJsonData("verificationData", "checkoutshippingaddress","shipherebuttonreplacetext");
		List<String> notSelectedAddresses;

		notSelectedAddresses = generalHelper.getAttributeValuesFromListOfElements(list_shipping_Item_Notselected_Address,"innerText");

		if (!defaultSelectedAddress.equals(expectedShippingAddress)) {
			int counter=1;
			for (String address : notSelectedAddresses) {
				
				String replacedAdd = address.replace(shipHereButtonTextToChange, "");
			
				if (replacedAdd.contains(expectedShippingAddress)) {
				
					WebElement shipHereButton = generalHelper.returnElementWithDynamicXpath(notSelectedAddressShipHereButtonXpath, Integer.toString(counter));
					buttonHelper.click(shipHereButton);
				}
				counter++;
			}
		}
	}
	
	/**
	 * Method to verify the change shipping address
	 * @author 
	 * @param expectedShippingAddress
	 */
	public void verifyShippingAddress(String expectedShippingAddress) {	
		logger.info("verifying the changeed shipping address");	

		assertHelper.assertTextMatching(expectedShippingAddress.trim(), generalHelper.getAttributeValue(text_DefaultSelectedShippingAddress, "innerText").trim());		
	}
	
	/**
	 * Method to click on the click here link
	 * @author  
	 */
	public void goToAddressBookPageFromCheckOutPage() {	
		logger.info("Clicking on the Click here link");

		progressHelpers.waitForElementToDisplay(link_ClickHere);
		buttonHelper.click(link_ClickHere);
	}
	
	/**
	 * Method to verify the product name and its quantity in order summary in checkout page
	 * @author 
	 * @param productName
	 * @param itemQty
	 */
	public void verifyOrderSummaryInCheckOutPage(String productName, String itemQty) {
		logger.info("Verifying the order summary for "+productName);	
		
		assertHelper.assertTextMatching(productName, generalHelper.getAttributeValue(text_ItemNameInOrderSummary, "innerText"));
		assertHelper.assertTextMatching(itemQty, generalHelper.getAttributeValue(text_ItemQtyInOrderSummary, "innerText"));
	}
	
	/**
	 * Method to Click on the product
	 * @author 
	 * @param productsNames
	 */
	public void clickOnTheProduct(String[] productsNames) {
		logger.info("Click on the product");
		
		WebElement expectedProductToAddToCart;

		for (String productName : productsNames) {
			expectedProductToAddToCart = generalHelper.returnElementWithDynamicXpath(clickOnProductXpath,productName);
			javascriptHelper.scrollIntoViewAndClick(expectedProductToAddToCart);
		}
		
	}
	
	/**
	 * Method to Increase the item in product details page
	 * @author 
	 * @param 
	 */
	public void increaseTheItemQtyInProductDetailsPage(int quantity) {
		logger.info("Increse the item in product details page");
		
		dropDownHelper.SelectUsingVisibleValue(drpdwn_Qty, String.valueOf(quantity));
		progressHelpers.waitForPageLoad();
		buttonHelper.click(btn_AddToCart);
	}
	
	/**
	 * Method to submit Request to Sell Table Topics Link
	 * @author 
	 */
	public void submitRequestToSellTableTopicsLink(Map<String, String> shippingDetailsMapData) {
		logger.info("submit Request to Sell Table Topics Link");
		
		buttonHelper.click(link_SubmitRequest);

		progressHelpers.waitForPageLoad();
		progressHelpers.waitForElementToDisplay(textField_Firstname);
		generalHelper.enterTextInTextFieldCharWise(textField_Firstname, shippingDetailsMapData.get("firstname"));
		generalHelper.enterTextInTextFieldCharWise(textField_Lastname, shippingDetailsMapData.get("lastname"));
		generalHelper.enterTextInTextFieldCharWise(textField_Email, shippingDetailsMapData.get("email"));
		
		generalHelper.enterTextInTextField(textField_StoreName, shippingDetailsMapData.get("storename"));
		
		generalHelper.enterTextInTextField(textField_City, shippingDetailsMapData.get("city"));
		generalHelper.enterTextInTextField(drpdwn_StateSubmitReq, shippingDetailsMapData.get("state"));
		generalHelper.enterTextInTextField(textField_PostCode, shippingDetailsMapData.get("zipcode"));
	
		buttonHelper.click(btn_Submit);
	
	}
}
