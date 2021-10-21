package com.ultrapro.pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ultrapro.commonUtils.InitPageObjectClasses;

public class MyWishListPage extends InitPageObjectClasses {

	public MyWishListPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	/* Page Objects */
	
	@FindAll({
		@FindBy(xpath = "//header[@class='page-header']//li[4]//a[1]"),
		@FindBy(xpath = "//header[@class='page-header']//li[5]//a[1]")
	})
	private WebElement menuLink_MyAccount;
	
	@FindBy(xpath = "//a[@class='btn-remove action delete']")
	private List<WebElement> btn_DeleteWishlistProducts;
	
	@FindBy(xpath = "//a[@data-action='add-to-wishlist']")
	private WebElement btn_AddToWishlist;
	
	@FindBy(xpath = "//a[@class='logo']//img")
	private WebElement img_EnphaseLogo;
	
	@FindBy(xpath = "//div[@class='block block-wishlist']//div[@class='empty']")
	private WebElement text_NoItemsInWishlist;
	
	@FindBy(id = "btn-minicart-close")
	private WebElement btn_MiniCartClose;
	
	@FindBy(xpath = "//a[@class='action showcart']")
	private WebElement icon_Cart;
	
	@FindBy(xpath = "//button[@class='action tocart']")
	private WebElement btn_AddAllToCart;
	
	@FindBy(xpath = "//div[@class='products-grid wishlist']//a[@class='product-item-link']")
	private List<WebElement> text_ProductNames;
	
	@FindBy(xpath = "//button[@class='action share']")
	private WebElement btn_ShareWishList;

	@FindBy(id = "email_address")
	private WebElement textField_EmailAddresses;

	@FindBy(id = "message")
	private WebElement textField_Message;
	
	@FindBy(xpath = "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")
	private WebElement text_SuccessMessage;

	@FindBy(xpath = "//button[@class='action submit primary']")
	private WebElement btn_ShareWishListInfo;
	
	@FindBy(id = "email_address-error")
	private WebElement text_RequiredField;
	
	@FindBy(xpath = "//li[@class='product-item'][1]")
	private WebElement expectedProductToAddToCart;
	
	@FindBy(xpath = "//input[@class='input-text qty']")
	private WebElement textField_ChangeQtyField;
	
	@FindBy(xpath = "//div[@class='box-tocart']//button[@class='action tocart primary']")
	private WebElement btn_AddToCart;
	
	@FindBy(xpath = "//ol[@id='wishlist-sidebar']//div[@class='actions-primary']//span")
	private WebElement btn_WishlistSidebarAddToTheCart;

	@FindBy(xpath = "//div[@class='product-item-inner']//a[@class='btn-remove action delete']")
	private WebElement btn_RemoveItem;

	@FindBy(xpath = "//div[@class='message info empty']//span")
	private WebElement text_emptyWishList;
	
	@FindBy(xpath = "//button[@class='action update']")
	private WebElement btn_UpdateWishList;
	
	private String productToClickXpath = "//div[@class='product-item-details']//a[@title='{PARAM1}']";

	private String clickOnProductXpath = "//div[@class='product details product-item-details']//a[text()='{PARAM1}']";

	
	// Page Methods
	
	/**
	 * Method to remove the wishlist products
	 * @author 
	 */
	public void removeWishlistedProducts() {
		logger.info("Removing products from the wishlist page");
		
		int numOfProductsInWishlist, count=1;
		String emptyWishlistMessage = jsonReader.readJsonData("verificationData", "mywishlistpage", "emptywishlistmessage");
			
		progressHelpers.waitForPageLoad();
		numOfProductsInWishlist = btn_DeleteWishlistProducts.size();

		if (numOfProductsInWishlist>=1) {
			while (count<= numOfProductsInWishlist) {
				progressHelpers.waitForPageLoad();
	        	buttonHelper.click(btn_DeleteWishlistProducts.get(0));
				count++;	
			}
		}else {
			progressHelpers.waitForPageLoad();
			assertHelper.assertTextMatching(emptyWishlistMessage, generalHelper.getAttributeValue(text_NoItemsInWishlist, "innerText"));
		}
	}
	
	/**
	 * Method to add products to the wishlist
	 * @author 
	 * @param productsNames
	 */
	public void addProductsToWishlist(String[] productsNames) {
		logger.info("Adding products to the wishlist");
		
		WebElement expectedProductToAddToCart;

		for (String productName : productsNames) {
			expectedProductToAddToCart = generalHelper.returnElementWithDynamicXpath(clickOnProductXpath,productName);
			javascriptHelper.scrollIntoViewAndClick(expectedProductToAddToCart);
		}
		buttonHelper.click(btn_AddToWishlist);
		
	}
	
	
	/**
	 * Method to verify the added wish list products
	 * @author 
	 * @param productNames
	 */
	public void verifyAddedWishListProducts(String[] productNames) {
		logger.info("Verifying added products in the wishlist page");
		
		List<String> expectedProductNames = new ArrayList<String>();
		List<String> actualProductNames = new ArrayList<String>();
		
		for(String productName : productNames) {
			expectedProductNames.add(productName.toUpperCase());
		}
		actualProductNames = generalHelper.getAttributeValuesFromListOfElements(text_ProductNames, "innerText");
		assertHelper.assertListsAfterSorting(actualProductNames, expectedProductNames);
	}
	
	/**
	 * Method to add all wish list products to the cart
	 * @author 
	 * @param productNames
	 */
	public void addAllWishlistedProductToCart() {
		logger.info("Adding products to cart by clicking 'Add All To Cart' button");

		progressHelpers.waitForElementToBeClickable(btn_AddAllToCart);
		buttonHelper.click(btn_AddAllToCart);
	}
	
	/**
	 * Method to share the wishlist products via email
	 * @author 
	 * @param email
	 * @param message
	 */
	public void shareTheWishList(String email, String message) {
		logger.info("Verify the share wishlist by valid email id");

		buttonHelper.click(btn_ShareWishList);
		progressHelpers.waitForElementToBeClickable(textField_EmailAddresses);
		generalHelper.enterTextInTextField(textField_EmailAddresses, email);
		generalHelper.enterTextInTextField(textField_Message, message);

		buttonHelper.click(btn_ShareWishListInfo);
	}
	
	/**
	 * Method to verify the share wish list action
	 * @author 
	 */
	public void verifySharedWishListAction() {
		logger.info("Verifying the success message of shared wishList ");

		String expectedMessage = jsonReader.readJsonData("verificationData", "mywishlistpage", "sharedsuccessmessage");
		
		assertHelper.assertTextMatching(expectedMessage, generalHelper.getAttributeValue(text_SuccessMessage, "innerText"));		
	}
	
	/**
	 * Method to click on share wishlist button
	 * @author 
	 */
	public void clickOnShareWishList() {
		logger.info("Clicking on share wishlist button");

		buttonHelper.click(btn_ShareWishList);
		progressHelpers.waitForElementToBeClickable(btn_ShareWishListInfo);
		buttonHelper.click(btn_ShareWishListInfo);
	}
	
	/**
	 * Method to Verify the share wish list by empty email id
	 * @author 
	 * @param expectedErrorMsg
	 */
	public void verifyTheShareWishListErrorMsg(String expectedErrorMsg) {
		logger.info("Verifying the share wishlist by empty email id ");

		assertHelper.assertTextMatching(expectedErrorMsg, text_RequiredField.getText());
	}
	
	/**
	 * Method to increase product qty and add to cart
	 * @author 
	 * @param qty
	 */
	public void increaseProductQtyAndAddToCart(String qty) {
		logger.info("Increasing product qty and adding to cart");
		
		progressHelpers.waitForPageLoad();
		generalHelper.moveToElement(expectedProductToAddToCart, driver);
		generalHelper.enterTextInTextField(textField_ChangeQtyField, qty);
		buttonHelper.click(btn_AddToCart);
	}
	
	/**
	 * Method to AddTo The Cart From Wish list Side Menu
	 * @author 
	 */
	public void addToTheCartFromWishlistSideMenu() {
		logger.info("Adding wishlisted products to the cart from Side Menu");

		buttonHelper.click(btn_WishlistSidebarAddToTheCart);
		progressHelpers.waitForPageLoad();
	}
	
	/**
	 * Method to remove the item from wishlist
	 * @author 
	 */
	public void removeItemFromWishlist() {
		logger.info("Removing a product product wishlist using 'Remove Items' link");
		
		progressHelpers.waitForPageLoad();
		generalHelper.moveToElement(expectedProductToAddToCart, driver);
		buttonHelper.click(btn_RemoveItem);
	}
	
	/**
	 * Method to verify the empty message
	 * @author 
	 */
	public void verifyEmptyWishlistMsg() {
		logger.info("Verifying the empty page in wishlist page");
		
		String expectedMsg = jsonReader.readJsonData("verificationData", "mywishlistpage", "emptywishlistmessage");
		
		progressHelpers.waitForElementToDisplay(text_emptyWishList);
		assertHelper.assertTextMatching(expectedMsg, text_emptyWishList.getAttribute("innerText"));
	}
	
	/**
	 * Method to edit item quantity and update the wishlist
	 * @author 
	 * @param qty
	 */
	public void editItemQtyAndUpdateWishlist(String qty) {
		logger.info("Updating the item qty");
		
		progressHelpers.waitForPageLoad();
		generalHelper.moveToElement(expectedProductToAddToCart, driver);
		generalHelper.enterTextInTextField(textField_ChangeQtyField, qty);
		
		buttonHelper.click(btn_UpdateWishList);		
	}
	
	/**
	 * Method to verify the item qty after update
	 * @author 
	 * @param expectedQty
	 */
	public void verifyItemQty(String expectedQty) {
		logger.info("Verifying the item quantity");
		
		String actualQty;
		progressHelpers.waitForPageLoad();
		generalHelper.moveToElement(expectedProductToAddToCart, driver);
		generalHelper.moveToElement(textField_ChangeQtyField, driver);
		actualQty = generalHelper.getAttributeValue(textField_ChangeQtyField, "value");
		
		assertHelper.assertTextMatching(expectedQty, actualQty);
	}
}
