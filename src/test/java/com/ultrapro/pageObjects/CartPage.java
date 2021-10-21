package com.ultrapro.pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ultrapro.commonUtils.InitPageObjectClasses;

public class CartPage extends InitPageObjectClasses {

	public CartPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	/* Element of Cart page */

	@FindAll({ @FindBy(css = ".action-primary.action-accept"), @FindBy(id = "remove-action-accept") })
	private WebElement btn_AcceptPopUp;

	@FindBy(id = "btn-minicart-close")
	private WebElement btn_MiniCartClose;

	@FindBy(className = "counter-number")
	private WebElement text_NumOfItemsInCart;

	@FindBy(css = ".action.showcart")
	private WebElement icon_Cart;

	@FindBy(xpath = "//strong[@class='subtitle empty no-items-position']")
	private WebElement text_CartIsEmpty;

	@FindBy(id = "top-cart-btn-checkout")
	private WebElement btn_ProceedToCheckOut;

	@FindBy(xpath = "//a[@class='action viewcart']")
	private WebElement btn_ViewAndEdit;

	@FindBy(xpath = "//tbody[@class='cart item']//strong[@class='product-item-name']//a")
	private WebElement text_ProductName;

	@FindBy(xpath = "//div[@class='product attribute overview']//strong[@class='type']")
	private WebElement text_ProductOverview;

	@FindBy(css = ".stock.available")
	private WebElement text_StockAvailability;

	@FindBy(xpath = "//h1[@class='page-title']")
	private WebElement text_ShoppingCartPageTitle;

	@FindBy(xpath = "//tbody[@class='cart item']//div[@class='product-item-details']//strong[@class='product-item-name']//a")
	private List<WebElement> productsName;

	@FindBy(xpath = "//a[@class='action delete']")
	private WebElement icon_DeleteProduct;

	@FindBy(xpath = "//aside[contains(@class,'_show')]//div[@class='modal-content']//div")
	private WebElement popUp_Content;

	@FindBy(xpath = "//ol[@id='mini-cart']//input[@class='item-qty cart-item-qty']")
	private WebElement text_productQty;

	@FindBy(xpath = "//div[@class='details-qty qty']//span[@class='item-plus qty-update']")
	private WebElement icon_IncreaseQty;

	@FindBy(xpath = "//div[@class='details-qty qty']//span[@class='item-minus qty-update']")
	private WebElement icon_DecreaseQty;

	@FindBy(xpath = "//div[@class='subtotal']//span[@class='price']")
	private WebElement text_CartSubtotal;

	@FindBy(xpath = "//div[@id='minicart-content-wrapper']//li//strong[@class='product-item-name']//a")
	private List<WebElement> list_MiniCartProductNames;

	@FindBy(xpath = "//div[@class='minicart-items-wrapper quickcart-items-wrapper']//li[@data-role='product-item']//a[@class='action delete']")
	private List<WebElement> list_DeleteIconsInCart;

	@FindBy(xpath = "//select[starts-with(@name, 'cart')]")
	private WebElement dropDown_quantity_mycart;
	
	@FindBy(xpath = "//button[@class='action update']")
	private WebElement btn_updatecart_mycart;
	
	@FindBy(xpath = "//a[@class='action action-edit']")
	private WebElement icon_edit;
	
	@FindBy(id = "qty")
	private WebElement dropDown_quantity_productpage;
	
	@FindBy(id = "product-updatecart-button")
	private WebElement btn_updatecart_productPage;
	
	@FindBy(xpath = "//a[@class='action action-delete']")
	private WebElement icon_delete;
	
	@FindBy(xpath = "//div[@class='cart-empty']//p[1]")
	private WebElement text_cartempty;
	
	@FindBy(id = "empty_cart_button")
	private WebElement btn_clear_mycart;
	
	@FindBy(xpath = "//table[@id='shopping-cart-table']//a[@class='action action-delete']")
	private List<WebElement> link_DeleteShoppingCartItem;
	
	@FindBy(xpath = "//a[@class='action action-delete']")
	private WebElement btn_DeleteShoppingCartItem;
	
	@FindBy(xpath = "//input[@id='coupon_code']")
	private WebElement textField_discountCode;
	
	@FindBy(xpath = "//button[@class='action apply primary']")
	private WebElement btn_applydiscountcode;

	@FindBy(id = "block-discount-heading")
	private WebElement link_ApplyPromotionCode;

	@FindBy(xpath = "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")
	private WebElement text_message;
	
	@FindBy(xpath = "//button[@class='action cancel primary']")
	private WebElement btn_cancelapplieddiscountcode;
	
	@FindBy(xpath = "//button[@class='action-primary action-accept']")
	private WebElement alertBtn_Yes;
	
	@FindBy(xpath = "//aside[@class='modal-popup confirm                                 _show']//div[@class='modal-content']")
	private WebElement text_ErrorMesg;
	
	@FindBy(xpath = "//div[@class='field note']")
	private WebElement text_SignInPage;
	
	@FindBy(xpath = "//div[@class='message-error error message']")
	private WebElement text_MaximumQtyErrorMesg;
	
	@FindBy(xpath = "//div[@class='message message-success success']")
	private WebElement text_RequestSuccessMessage;
	
	String link_CartDelete = "//li[@class='item product product-item quickcart-product-item'][{PARAM1}]//div[@class='product actions']//a[@class='action delete']";

	/* Page Methods */

	/**
	 * Delete a product using delete icon one by one in the mini cart
	 * 
	 * @author
	 */
	public void deleteProductOneByOne() {
		logger.info("Deleting a product one by one in mini cart");

		int additionlAddressCount = list_DeleteIconsInCart.size();
		int count = 1;
		while (count <= additionlAddressCount) {

			progressHelpers.hardWait(1000);
			buttonHelper.click(list_DeleteIconsInCart.get(0));
			buttonHelper.click(btn_AcceptPopUp);
			progressHelpers.waitForElementToDisplay(icon_Cart);
			buttonHelper.click(icon_Cart);
			count++;
		}

		buttonHelper.click(btn_MiniCartClose);
	}

	/**
	 * Method to empty the Mini cart
	 * 
	 * @author
	 */
	public void emptyTheCartNow() {
		logger.info("Emptying the cart");

		progressHelpers.waitForPageLoad();
		progressHelpers.hardWait(5000);

		if (text_NumOfItemsInCart.isDisplayed()) {
			try {
				buttonHelper.click(icon_Cart);
				deleteProductOneByOne();
				progressHelpers.hardWait(2000);
				progressHelpers.waitForElementToInvisible(text_NumOfItemsInCart);
			} catch (ElementNotInteractableException e) {
				deleteProductOneByOne();
			}
		} else {
			verifyTheCartIsEmpty();
		}
	}

	/**
	 * Method to check whether the Mini cart is empty
	 * 
	 * @author
	 */
	public void verifyTheCartIsEmpty() {
		logger.info("Checking cart empty message");

		String expectedMiniCartEmptyMessage = jsonReader.readJsonData("verificationData", "minicart", "emptymessage");

		buttonHelper.click(icon_Cart);
		progressHelpers.waitForElementToDisplay(text_CartIsEmpty);
		assertHelper.assertTextMatching(expectedMiniCartEmptyMessage, text_CartIsEmpty.getText());
		buttonHelper.click(btn_MiniCartClose);
	}

	/**
	 * Method to Clicking on the proceed to checkout button in the mini cart
	 * 
	 * @author
	 */
	public void clickOnProceedToCheckoutButton() {
		logger.info("Clicking on the proceed to checkout button in the mini cart");

		progressHelpers.waitForElementToDisplay(text_NumOfItemsInCart);
		buttonHelper.click(btn_ProceedToCheckOut);

	}

	/**
	 * Method to goto Mini cart and click on View and Edit option
	 * 
	 * @author
	 */
	public void clickOnViewAndEditLink() {
		logger.info("Click on View and Edit link in the mini cart");

		progressHelpers.waitForPageLoad();
		
		progressHelpers.waitForElementToBeClickable(btn_ViewAndEdit);

		buttonHelper.click(btn_ViewAndEdit);
	}

	/**
	 * Method to check the shopping cart page
	 * 
	 * @author
	 */
	public void verifyLandedIntoShoppingCartPage() {
		logger.info("Verifying the shopping cart page title");

		String expectedPageTitleText = jsonReader.readJsonData("verificationData", "shoppingcartpage", "pagetitle").toUpperCase();

		assertHelper.assertTextMatching(expectedPageTitleText, text_ShoppingCartPageTitle.getAttribute("innerText"));
	}

	/**
	 * Method to get and compare products name in the shopping cart with expected
	 * value
	 * 
	 * @author
	 * @param expectedProductsNames
	 */
	public void verifyProductsNameInTheCart(String[] expectedProductsNames) {
		logger.info("Verifying " + expectedProductsNames + " in the cart page");

		Object[] actualProductsNames = generalHelper.getAttributeValuesFromListOfElements(productsName, "innerText")
				.toArray();

		assertHelper.compareTwoValues(actualProductsNames, expectedProductsNames);
	}

	/**
	 * Method to delete an item in mini cart
	 * 
	 * @author
	 */
	public void deleteAnItemInMiniCart() {
		logger.info("Deleted an item in the cart");

		progressHelpers.waitForPageLoad();
		progressHelpers.waitForElementToDisplay(icon_DeleteProduct);
		buttonHelper.click(icon_DeleteProduct);

		progressHelpers.waitForElementToDisplay(popUp_Content);
		assertHelper.assertElementsExistsInPage(popUp_Content);

		buttonHelper.click(btn_AcceptPopUp);
		buttonHelper.click(btn_MiniCartClose);
	}

	/**
	 * Method to increase or decrease the item quantity in mini cart
	 * 
	 * @param actionType
	 * @param quantity
	 */
	public void increaseOrDecreaseItemQuantity(String actionType, int quantity) {
		logger.info("Verifying item quantity increase/decrease function in mini cart");

		int beforeActionItemQuantity;

		progressHelpers.waitForElementToDisplay(text_NumOfItemsInCart);

		beforeActionItemQuantity = Integer.parseInt(text_productQty.getAttribute("value"));

		if (actionType.equalsIgnoreCase("decrease") && beforeActionItemQuantity > 1) {
			buttonHelper.click(icon_Cart);
			int numberOfTimesCanBeDecrease = beforeActionItemQuantity - 1;

			if (numberOfTimesCanBeDecrease > quantity) {
				numberOfTimesCanBeDecrease = quantity;
			}

			for (int i = 0; i < numberOfTimesCanBeDecrease; i++) {
				buttonHelper.click(icon_DecreaseQty);
				progressHelpers.hardWait(1000);
			}
			
			progressHelpers.waitForElementToDisplay(text_productQty);
			progressHelpers.waitForElementAttributeValueToChanged(text_productQty, "value",
					String.valueOf(beforeActionItemQuantity - numberOfTimesCanBeDecrease));
		} else {
			progressHelpers.waitForElementToDisplay(icon_IncreaseQty);
			for (int i = 0; i < quantity; i++) {
				progressHelpers.hardWait(800);
				buttonHelper.click(icon_IncreaseQty);
				progressHelpers.hardWait(1000);
			}
			progressHelpers.waitForElementToDisplay(text_productQty);
			progressHelpers.waitForElementAttributeValueToChanged(text_productQty, "value",String.valueOf(beforeActionItemQuantity + quantity));
		}
		buttonHelper.click(btn_MiniCartClose);
	}

	/**
	 * Method to verify the product quantity after increase/decrease
	 * 
	 * @author
	 * @param expectedQuantity
	 */
	public void verifyItemQuantityAfterIncreaseOrDecrease(int expectedQuantity) {
		logger.info("Verifying the item quantity in mini cart after increase/decrease");

		progressHelpers.hardWait(2000);
		progressHelpers.waitForElementToBeClickable(icon_Cart);
		buttonHelper.click(icon_Cart);
		
		progressHelpers.hardWait(800);
		progressHelpers.waitForElementAttributeValueToChanged(text_productQty, "value",String.valueOf(expectedQuantity));
		int afterActionItemQuantity = Integer.parseInt(text_productQty.getAttribute("value"));
		assertHelper.compareTwoValues(expectedQuantity, afterActionItemQuantity);
		buttonHelper.click(btn_MiniCartClose);
	}

	/**
	 * Method to verify the sub total cost
	 * 
	 * @author
	 */
	public void verifySubTotalCost() {
		logger.info("Verifying the subtotal cost");

		String expectedSubTotal = jsonReader.readJsonData("productcatalouge", "productnames", "dinnerparty");
		String actualSubTotal;

		progressHelpers.waitForElementToDisplay(text_NumOfItemsInCart);
		//buttonHelper.click(icon_Cart);
		
		actualSubTotal = text_CartSubtotal.getAttribute("innerText");
		progressHelpers.waitForElementToDisplay(text_CartSubtotal);
		
		assertHelper.assertTextMatching(expectedSubTotal, actualSubTotal);
	
	}
	
	/**
	 * Method to Click on mini cart icon
	 * 
	 * @author
	 */
	public void clickOnMiniCartIcon() {
		logger.info("Click on mini cart icon");
		
		progressHelpers.waitForElementToDisplay(text_NumOfItemsInCart);
		buttonHelper.click(icon_Cart);
	
	}

	/**
	 * Method to assert expected products added to the cart
	 * 
	 * @author
	 */
	public void verifyProductsName() {
		logger.info("Verifying Product name(s) in mini cart");

		String[] productNames = jsonReader.readJsonData("verificationData", "minicart", "productname").split(";");

		List<String> expectedProductNames = new ArrayList<String>();
		List<String> actualProductNames = new ArrayList<String>();

		progressHelpers.hardWait(800);
		actualProductNames = generalHelper.getAttributeValuesFromListOfElements(list_MiniCartProductNames, "innerText");

		for (String productName : productNames) {
			expectedProductNames.add(productName);
		}

		assertHelper.assertListsAfterSorting(actualProductNames, expectedProductNames);

	}

	/**
	 * Method to assert the number of items in the cart with expected value
	 * 
	 * @author
	 * @param expectedNumOfProducts
	 */
	public void verifyNumOfItemsInCart(int expectedNumOfProducts) {
		logger.info("Verifying items in the Mini cart");

		progressHelpers.hardWait(3000);
		progressHelpers.waitForElementToDisplay(text_NumOfItemsInCart);

		int actualNumOfItemsInCart = Integer.parseInt(text_NumOfItemsInCart.getText());

		assertHelper.compareTwoValues(actualNumOfItemsInCart, expectedNumOfProducts);
	}

	/**
	 * Method to open the Mini cart and click on proceed to checkout button
	 * 
	 * @author
	 */
	public void clickOnMiniCartIconAndProceedToCheckOut() {
		logger.info("Clicking on Mini cart icon and proceed to checkout button");

		progressHelpers.waitForElementToDisplay(text_NumOfItemsInCart);
		clickOnProceedToCheckoutButton();
	}
	
	/**
	 * Method to update the quantity of the items in My cart page
	 * @author 
	 * @param itemQty
	 */
	public void updateProductQuantityCountInMyCart(int itemQty) {
		logger.info("update the quantity of the items");

		progressHelpers.waitForElementToDisplay(dropDown_quantity_mycart);
		
		dropDownHelper.SelectUsingVisibleValue(dropDown_quantity_mycart, String.valueOf(itemQty));
		buttonHelper.click(btn_updatecart_mycart);
		progressHelpers.waitForPageLoad();
	}
	
	/**
	 * Method to verify the product quantity
	 * @author 
	 * @param itemQty
	 */
	public void verifyProductQuantity(int itemQty) {	
		logger.info("verifying the product quantity");
		
		progressHelpers.waitForElementToDisplay(dropDown_quantity_mycart);
		int afterActionItemQuantity = Integer.parseInt(dropDown_quantity_mycart.getAttribute("value"));
		assertHelper.compareTwoValues(afterActionItemQuantity, itemQty);
	}
	
	/**
	 * Method to update the quantity of the items in product page
	 * @author  
	 * @param itemQty
	 */
	public void updateProductQuantityInProductDetailsPage(int itemQty) {
		logger.info("Updating the quantity of the product");

		buttonHelper.click(icon_edit);
		
		progressHelpers.waitForElementToDisplay(dropDown_quantity_productpage);
		
		dropDownHelper.SelectUsingVisibleValue(dropDown_quantity_productpage, String.valueOf(itemQty));
		buttonHelper.click(btn_updatecart_productPage);
	}
	
	/**
	 * Method to delete the product in the cart
	 * @author  
	 */
	public void deleteProductInMyCart() {
		logger.info("Clicking on delete the product icon");

		buttonHelper.click(icon_delete);
	}

	/**
	 * Method to verify the my cart is empty
	 * @author  
	 * @param expectedmessage
	 */
	public void verifyCartEmptyMessage(String expectedmessage) {	
		logger.info("verifying the cart empty message");
		
		progressHelpers.waitForElementToDisplay(text_cartempty);
		assertHelper.assertTextMatching(expectedmessage, text_cartempty.getAttribute("innerText").trim());
	}	
	
	/**
	 * Method to clear the products in cart
	 * @author  
	 */
	public void clearProductInMyCart() {
		logger.info("Clicking on clear shopping cart button");

		int additionlAddressCount = link_DeleteShoppingCartItem.size();	
		int count = 1;
		
		while(count<=additionlAddressCount) {
			progressHelpers.waitForPageLoad();
        	buttonHelper.click(link_DeleteShoppingCartItem.get(0));
			//buttonHelper.click(btn_DeleteShoppingCartItem);
			count++;
		}
	}
	
	/**
	 * Method to apply the discount code
	 * @author  
	 * @param discountCode
	 */
	public void applyDiscountCode(String discountCode) {
		logger.info("Applying the discount code");

		
		progressHelpers.waitForElementToDisplay(link_ApplyPromotionCode);
		buttonHelper.click(link_ApplyPromotionCode);
		generalHelper.enterTextInTextField(textField_discountCode,discountCode);
		buttonHelper.click(btn_applydiscountcode);
	}
	
	/**
	 * Method to Verify pop-up page message
	 * @author  
	 * @param discountCode
	 * @param message
	 */
	public void verifyMyCartPageMessage(String discountCode, String message) {
		logger.info("verifying the my cart page pop-up message");
		
		message = message.replace("{discountcode}", discountCode);	
	
		progressHelpers.waitForElementToDisplay(text_message);
		progressHelpers.waitForElementAttributeValueToChanged(text_message, "innerText", message);
		assertHelper.assertTextMatching(message, text_message.getAttribute("innerText").trim());
	}
	
	/**
	 * Method to apply the discount code
	 * @author  
	 * @param discountCode
	 */
	public void applyAndCancelTheAppliedDiscountCode(String discountCode) {
		logger.info("Applying and cancelling the discount code");
		
		applyDiscountCode(discountCode);

		buttonHelper.click(btn_cancelapplieddiscountcode);
	}
	
	/**
	 * Method to increase or decrease the item quantity in mini cart
	 * 
	 * @param quantity
	 */
	public void increaseItemQuantity(int quantity) {
		logger.info("Verifying item quantity increase/decrease function in mini cart");

		int count = 1;

		while (count < quantity) {
			progressHelpers.waitForPageLoad();
			progressHelpers.hardWait(2000);
			buttonHelper.click(icon_IncreaseQty);
			progressHelpers.hardWait(800);
			count++;
		}
	}
	
	/**
	 * Method to Verify pop-up page message
	 * @author 
	 */
	public void verifyErrorMessage() {
		logger.info("verifying the my cart page pop-up message");
		
		String expectedmessage ="The most you may purchase is 60.";
		
		buttonHelper.click(icon_IncreaseQty);
		progressHelpers.waitForElementToDisplay(text_ErrorMesg);
		assertHelper.assertTextMatching(expectedmessage, text_ErrorMesg.getAttribute("innerText"));
		
		buttonHelper.click(alertBtn_Yes);
		
	}
	
	/**
	 * Method to Verify Sign In Page
	 * @author
	 */
	public void verifySignInPage() {
		logger.info("verifying the sign in page");

		String expectedmessage ="If you have an account, sign in with your email address.";
		
		progressHelpers.waitForElementAttributeValueToChanged(text_SignInPage, "innerText", expectedmessage);
		assertHelper.assertTextMatching(expectedmessage, text_SignInPage.getAttribute("innerText").trim());
	}
	
	/**
	 * Method to Verify maximum qty error message
	 * @author
	 */
	public void verifyTheMaximumQtyErrorMessage() {
		logger.info("Verify maximum qty error message");

		String expectedmessage ="The most you may purchase is 60.";
		
		progressHelpers.waitForElementAttributeValueToChanged(text_MaximumQtyErrorMesg, "innerText", expectedmessage);
		assertHelper.assertTextMatching(expectedmessage, text_MaximumQtyErrorMesg.getAttribute("innerText"));
	}
	
	/**
	 * Method to Verify Success Message
	 * @author
	 */
	public void verifySuccessMessage() {
		logger.info("verifying the Success Message");

		String expectedmessage ="Your request has been sent successfully";
		
		progressHelpers.waitForElementAttributeValueToChanged(text_RequestSuccessMessage, "innerText", expectedmessage);
		assertHelper.assertTextMatching(expectedmessage, text_RequestSuccessMessage.getAttribute("innerText").trim());
	}
}
