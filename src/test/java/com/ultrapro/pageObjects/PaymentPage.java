package com.ultrapro.pageObjects;

import java.util.Map;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.ultrapro.commonUtils.InitPageObjectClasses;

public class PaymentPage extends InitPageObjectClasses {

	public PaymentPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	/* Page Objects */

	@FindBy(id = "authnetcim")
	private WebElement radioBtn_CreditCard;

	@FindBy(id = "billing-address-same-as-shipping-authnetcim")
	private WebElement checkBox_BillingShippingAddressSame;

	@FindBy(className = "billing-address-details")
	private WebElement text_BillingAddress;

	@FindBy(xpath = "//select[@name='billing_address_id']")
	private WebElement dropDown_BillingAddress;

	@FindBy(xpath = "//*[@id='authnetcim-submit' or @class='action primary checkout']")
	private WebElement btn_PlaceOrder;

	@FindBy(xpath = "//button[@class='action action-update']")
	private WebElement btn_Update;

	@FindBy(id = "authnetcim-cc-number")
	private WebElement textField_CCNumber;

	@FindBy(xpath = "//select[@id='authnetcim-cc-exp-month']")
	private WebElement dropDown_ExpMonth;

	@FindBy(xpath = "//select[@id='authnetcim-cc-exp-year']")
	private WebElement dropDown_ExpYear;

	@FindBy(id = "authnetcim-cc-cid")
	private WebElement textField_Cvc;

	@FindBy(id = "discount-code")
	private WebElement textField_PromoCode;

	@FindBy(xpath = "//div[@class='message message-success success']//div")
	private WebElement text_CouponCodeSuccessMessage;

	@FindBy(xpath = "//div[@class='message message-error error']//div")
	private WebElement text_CouponCodeErrorMessage;

	@FindBy(css = ".action.action-apply")
	private WebElement btn_ApplyPromoCode;

	@FindAll({ @FindBy(className  = "order-number"),
	@FindBy(xpath = "//div[@class='checkout-success']//p//span") })
	private WebElement text_OrderNumber;

	@FindBy(xpath = "//div//h1[@class='page-title']//span")
	private WebElement text_pruchaseSuccessMsg;

	@FindBy(xpath = "//select[@id='authnetcim-card-id']")
	private WebElement dropDown_SavedCards;

	@FindBy(id = "authnetcim-save")
	private WebElement checkBox_SaveCard;

	@FindBy(xpath = "//div[@class='field no-label month']//div[@class='mage-error']")
	private WebElement text_creditCardExpiredMsg;

	@FindBy(xpath = "//button[@class='action action-cancel']")
	private WebElement btn_Cancel;

	@FindBy(id = "po_number")
	private WebElement textField_PoNumber;
	
	String shippingCostXpath = "//table[@class='table-checkout-shipping-method']//tbody//tr[{PARAM1}]//td[@class='col col-price']//span[@class='price']//span";

	/* Page Methods */

	/**
	 * Method to select the payment method
	 * 
	 * @author
	 * @param paymentMethod
	 */
	public void selectPaymentMethod(String paymentMethod) {
		logger.info("Selecting payment method");

		if (System.getProperty("ENV").equalsIgnoreCase("prod")) {
			progressHelpers.waitForElementToBeClickable(radioBtn_CreditCard);

			switch (paymentMethod.toLowerCase()) {
			case "credit card":
				progressHelpers.waitForElementToDisplay(radioBtn_CreditCard);
				javascriptHelper.clickOnElement(radioBtn_CreditCard);
				break;
			}
		}

	}

	/**
	 * Method to select the AddNew Card
	 * 
	 * @author
	 * @param paymentMethod
	 */
	public void selectAddNewCard(Map<String, String> paymentDetailsMapData) {

		if (System.getProperty("ENV").equalsIgnoreCase("qa2")) {

			try {
				if (dropDown_SavedCards.isDisplayed()) {
					progressHelpers.waitForElementToBeClickable(dropDown_SavedCards);
					buttonHelper.click(dropDown_SavedCards);
					dropDownHelper.SelectUsingVisibleValue(dropDown_SavedCards, paymentDetailsMapData.get("addnewcard"));
				}

			} catch (NoSuchElementException e) {
				// TODO: handle exception
			}
		}
	}

	/**
	 * Method to check the expected address showing while click on shipping and
	 * billing address are same check box
	 * 
	 * @author
	 * @param paymentMethod
	 */
	public void verifyShippingAndBillingAddressSameCheckbox(String paymentMethod) {
		logger.info("Address displayed on Credit card page");

		selectPaymentMethod(paymentMethod);
		if (checkBoxAndRadioButtonHelper.isIselected(checkBox_BillingShippingAddressSame) == false) {
			javascriptHelper.clickOnElement(checkBox_BillingShippingAddressSame);
		}
		assertHelper.assertElementsExistsInPage(text_BillingAddress);
	}

	/**
	 * Method to update the billing address in credit card payment option
	 * 
	 * @author
	 * @param paymentDetailsMapData
	 */
	public void updateBillingAddressInCreditCardPayment(Map<String, String> paymentDetailsMapData) {
		logger.info("Updating billing address in credit cart payment");

		progressHelpers.waitForPageLoad();

		selectPaymentMethod(paymentDetailsMapData.get("paymentmethod"));

		if (checkBoxAndRadioButtonHelper.isIselected(checkBox_BillingShippingAddressSame)) {
			javascriptHelper.clickOnElement(checkBox_BillingShippingAddressSame);
		}

		dropDownHelper.SelectUsingVisibleValue(dropDown_BillingAddress, paymentDetailsMapData.get("billingaddress2"));
	}

	/**
	 * Method to verify the updated address
	 * 
	 * @author
	 * @param expectedUpdatedBillingAddress
	 */
	public void verifyUpdatedAddressInCreditCardSection(String expectedUpdatedBillingAddress) {
		logger.info("Verifying the updated address");

		String actualBillingAddress;
		buttonHelper.click(btn_Update);
		assertHelper.assertElementsExistsInPage(text_BillingAddress);
		actualBillingAddress = text_BillingAddress.getAttribute("innerText").replace("Edit", "").trim();
		assertHelper.assertTextMatching(expectedUpdatedBillingAddress, actualBillingAddress);
	}

	/**
	 * Method to check the credit card number field accepts only numeric values
	 * 
	 * @author
	 * @param paymentDetailsMapData
	 */
	public void verifyCreditCardNumberFieldAcceptNumericOnly(Map<String, String> paymentDetailsMapData) {
		logger.info("Verifying credit card number field accepts only numeric values");

		selectPaymentMethod(paymentDetailsMapData.get("paymentmethod"));
		selectAddNewCard(paymentDetailsMapData);

		progressHelpers.waitForPageLoad();
		dropDownHelper.SelectUsingVisibleValue(dropDown_ExpMonth, paymentDetailsMapData.get("expmonth"));
		dropDownHelper.SelectUsingVisibleValue(dropDown_ExpYear, paymentDetailsMapData.get("expyear"));
		generalHelper.enterTextInTextFieldCharWise(textField_CCNumber, paymentDetailsMapData.get("ccnumber"));

		// Verify that the cc number field not accepts string values
		assertHelper.assertTextMatching("", generalHelper.getText(textField_CCNumber));

	}

	/**
	 * Method to check the CVC text field accepts expected length of chars
	 * 
	 * @author
	 * @param paymentDetailsMapData
	 */
	public void verifyTheCvcTextBoxCharAcceptsLength(Map<String, String> paymentDetailsMapData) {
		logger.info("Verifying credit card CVC field accepts expected length of chars");

		int expectedLengthOfCharAccepts = 4;
		int actualCharsEntered;

		selectPaymentMethod(paymentDetailsMapData.get("paymentmethod"));
		selectAddNewCard(paymentDetailsMapData);

		// enter the CVC number
		generalHelper.enterTextInTextField(textField_Cvc, paymentDetailsMapData.get("cvcnumber"));

		actualCharsEntered = textField_Cvc.getAttribute("value").length();
		assertHelper.compareTwoValues(actualCharsEntered, expectedLengthOfCharAccepts);
	}

	/**
	 * Method to apply the discount code
	 * 
	 * @author
	 * @param couponCode
	 */
	public void applyDiscountCode(String couponCode) {
		logger.info("Applying coupon code");

		progressHelpers.waitForElementToDisplay(textField_PromoCode);
		javascriptHelper.clickOnElement(textField_PromoCode);

		progressHelpers.waitForElementToDisplay(textField_PromoCode);
		generalHelper.enterTextInTextFieldCharWise(textField_PromoCode, couponCode);

		buttonHelper.click(btn_ApplyPromoCode);
	}

	/**
	 * Method to check the coupon code applied success message
	 * 
	 * @author
	 */
	public void verifyCouponCodeSuccessMessage() {
		logger.info("Verifying coupon code success message");

		String expectedcouponCodeSuccessMessage = jsonReader.readJsonData("verificationData", "discountcode",
				"successmessage");

		progressHelpers.waitForElementToDisplay(text_CouponCodeSuccessMessage);
		assertHelper.assertTextMatching(generalHelper.getText(text_CouponCodeSuccessMessage),
				expectedcouponCodeSuccessMessage);
	}

	/**
	 * Method to check the coupon code applied invalid error message
	 * 
	 * @author
	 */
	public void verifyCouponCodeErrorMessage() {
		logger.info("Verifying coupon code error message");

		String expectedcouponCodeErrorMessage = jsonReader.readJsonData("verificationData", "discountcode",
				"errormessage");

		assertHelper.assertTextMatching(generalHelper.getAttributeValue(text_CouponCodeErrorMessage, "innerText"),
				expectedcouponCodeErrorMessage);
	}

	/**
	 * Method to enter credit card details
	 * 
	 * @author
	 * @param paymentDetailsMapData
	 */
	public void enterCreditCardDetails(Map<String, String> paymentDetailsMapData) {
		logger.info("Enter the credit card details");

		selectAddNewCard(paymentDetailsMapData);

		progressHelpers.waitForPageLoad();
		generalHelper.enterTextInTextFieldCharWise(textField_CCNumber, paymentDetailsMapData.get("creditcardnumber"));

		dropDownHelper.SelectUsingVisibleValue(dropDown_ExpMonth, paymentDetailsMapData.get("expmonth"));
		dropDownHelper.SelectUsingVisibleValue(dropDown_ExpYear, paymentDetailsMapData.get("expyear"));

		generalHelper.enterTextInTextField(textField_Cvc, paymentDetailsMapData.get("cvc"));

	}

	/**
	 * Method to enter payment details and place an order
	 * 
	 * @author
	 * @param paymentDetailsMapData
	 */
	public void userEnterPaymentDetailsAndPlaceOrder(Map<String, String> paymentDetailsMapData) {
		logger.info("Entering the payment details and place the order");

		enterCreditCardDetails(paymentDetailsMapData);

		if (paymentDetailsMapData.get("purchasingfor").equalsIgnoreCase("Second Time")) {
			if (checkBox_SaveCard.isSelected()) {
				buttonHelper.click(checkBox_SaveCard);
			}
		}
		
		buttonHelper.click(btn_PlaceOrder);

	}
	
	/**
	 * Method to enter payment details and place an order
	 * @author
	 */
	public void enterPurchaseOrderNumberAndPlaceOrder(int poNumber) {
		logger.info("Entering the payment details and place the order");

		generalHelper.enterTextInTextFieldCharWise(textField_PoNumber, String.valueOf(poNumber));
		
		buttonHelper.click(btn_PlaceOrder);
	}

	/**
	 * Method to verify whether the purchase is successful or not by reading the
	 * success message
	 * 
	 * @author
	 */
	public String verifyPurchaseIsSuccessfulAndGetTheOrderNumber() {
		logger.info("Verifying purchase success message");

		String purchaseSuccessMessage = jsonReader.readJsonData("verificationData", "purchasesuccesspage", "message");

		progressHelpers.hardWait(8000);
			
		assertHelper.assertTextMatching(purchaseSuccessMessage, text_pruchaseSuccessMsg.getText());
		
		return text_OrderNumber.getAttribute("innerText");
	}

	/**
	 * Method to verify the error message of the expired credit card given
	 * 
	 * @author
	 * @param paymentDetailsMapData
	 */
	public void verifyExpiredCreditCardErrorMessage(Map<String, String> paymentDetailsMapData) {
		logger.info("Verifying the expired credit card error message");

		selectPaymentMethod(paymentDetailsMapData.get("paymentmethod"));
		selectAddNewCard(paymentDetailsMapData);

		progressHelpers.waitForPageLoad();
		generalHelper.enterTextInTextFieldCharWise(textField_CCNumber, paymentDetailsMapData.get("creditcardnumber"));
		dropDownHelper.SelectUsingVisibleValue(dropDown_ExpMonth, paymentDetailsMapData.get("expmonth"));
		dropDownHelper.SelectUsingVisibleValue(dropDown_ExpYear, paymentDetailsMapData.get("expyear"));
		generalHelper.enterTextInTextFieldCharWise(textField_Cvc, paymentDetailsMapData.get("cvcnumber"));

		assertHelper.assertTextMatching(paymentDetailsMapData.get("expirederrormessage"),
				text_creditCardExpiredMsg.getText());
	}

	/**
	 * Method to verify the cancel updated address
	 *
	 * @author
	 * @param expectedCancelBillingAddress
	 */
	public void verifyCancelUpdateAddress(String expectedCancelBillingAddress) {
		logger.info("Verifying the cancel updated address");

		String actualBillingAddress;

		javascriptHelper.clickOnElement(checkBox_BillingShippingAddressSame);
		if (checkBoxAndRadioButtonHelper.isIselected(checkBox_BillingShippingAddressSame)) {
			javascriptHelper.clickOnElement(checkBox_BillingShippingAddressSame);
		}
		buttonHelper.click(btn_Cancel);
		assertHelper.assertElementsExistsInPage(text_BillingAddress);
		actualBillingAddress = text_BillingAddress.getAttribute("innerText").replace("Edit", "").trim();
		assertHelper.assertTextMatching(expectedCancelBillingAddress, actualBillingAddress);
	}

}
