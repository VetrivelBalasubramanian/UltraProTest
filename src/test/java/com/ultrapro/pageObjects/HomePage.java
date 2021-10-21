package com.ultrapro.pageObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.ultrapro.commonUtils.InitPageObjectClasses;

public class HomePage extends InitPageObjectClasses {

	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	/* Element of Home page */

	@FindBy(id = "search")
	private WebElement textBox_Search;

	@FindBy(xpath = "//div[@class='block block-content']//form[@id='search_mini_form']//button[@class='action search']")
	private WebElement btn_SearchIcon;

	@FindBy(xpath = "//ol[@class='products list items product-items']//h2[@class='product name product-item-name shop-all']//a")
	private List<WebElement> searchResultsProducts;

	@FindBy(xpath = "//li[starts-with(@class,'megamenu level-top-fullwidth  level0 nav') or starts-with(@class, 'megamenu level-top-sectioned  level0 nav')]")
	private List<WebElement> subMenuLinks;

	@FindBy(xpath = "//li[@class='authorization-link']//a")
	private WebElement menuLink_SignIn;

	@FindBy(xpath = "//*[@id='send2-login' or @id='send2']")
	private WebElement btn_SignIn;

	@FindBy(xpath = "//*[@id='email-login' or @id='email']")
	private WebElement textField_Email;

	@FindBy(xpath = "//*[@id='pass-login' or @id='pass']")
	private WebElement textField_Password;

	@FindBy(xpath = "//div[@class='panel header']//li[@class='greet welcome']//span")
	private WebElement text_UserNameInTheHeader;

	@FindBy(name = "email")
	private WebElement textfield_EmailNewesLetter;

	@FindBy(xpath = "//div[@class='page messages']//div[@class='message-error error message']//div")
	private WebElement text_ErrorMsg;

	@FindBy(xpath = "//a[@class='action remind']")
	private WebElement link_ForgotPassword;

	@FindBy(xpath = "//button[@class='action submit primary']")
	private WebElement btn_ResetPassword;

	@FindBy(id = "email_address")
	private WebElement txtField_ForgotRecoveryEmail;

	@FindBy(xpath = "//div[@class='message-success success message']")
	private WebElement text_EmailSentSuccessMessage;

	@FindBy(xpath = "//span[@class='counter-number']")
	private WebElement text_NumOfItemsInCart;

	@FindBy(xpath = "/html/body/div[1]/div[1]/header/div[3]/div/ul[2]/li[2]/div/ul/li[3]/a")
	private WebElement menuLink_SignOut;

	@FindBy(xpath = "//a[text()='My Account']")
	private WebElement menuLink_MyAccount;

	@FindBy(xpath = "//div[@class='panel header']//li[@class='customer-welcome']")
	private WebElement btn_Username;

	@FindBy(css = ".action.showcart")
	private WebElement icon_Cart;

	@FindBy(id = "searchautocomplete")
	private WebElement text_AutoComplete;

	@FindBy(xpath = "//a[@class='logo ']")
	private WebElement TableTopics_Logo;
	
	@FindBy(xpath = "//div[@class='container-autocomplete']")
	private WebElement textBox_AutoComplete;
	
	@FindBy(xpath = "//li[starts-with(@class,'megamenu level2 nav')]")
	private List<WebElement> subCategoryMenuLinks;
	
	@FindBy(id = "ui-id-3")
	private WebElement link_TableTopEditions;
	
	@FindBy(id = "ui-id-4")
	private WebElement link_TravelSizeEdition;
	
	@FindBy(id="ui-id-7")
	private WebElement link_ShopAll;
	
	@FindBy(xpath="//div[@class='col-md-3 col-sm-6 col-xs-12 mobile-toggle']//strong[text()='Read More']")
	private WebElement link_ReadMore;
	
	@FindBy(xpath="//div[@class='col-md-6']")
	private WebElement text_AboutContent;
	
	private String addToCartButtonOfProductXpath = "//div[@class='product details product-item-details']//a[text()='{PARAM1}']/following::button[1]";

	/* Page Methods */

	/**
	 * Method to search the products in the site
	 * 
	 * @author
	 * @param searchTexts
	 */
	public void searchTheProducts(String[] searchProductNameText) {
		logger.info("Entering the search text in search box");

			for (String productNames : searchProductNameText) {
				progressHelpers.waitForPageLoad();
				generalHelper.enterTextInTextField(textBox_Search, productNames);
				progressHelpers.waitForElementToDisplay(text_AutoComplete);
				textBox_Search.sendKeys(Keys.ENTER);
			}
	}

	/**
	 * Method to verify the search box result
	 * 
	 * @author
	 * @param expectedProductNames
	 */
	public void verifySearchResultsProductsName(String[] expectedProductNames) {
		logger.info("Verifying the search results");

		List<String> actualProductNames;

		actualProductNames = generalHelper.getAttributeValuesFromListOfElements(searchResultsProducts, "innerText");

		for (String expectedProductName : expectedProductNames) {

			assertHelper.assertElementInStringList(expectedProductName, actualProductNames);
		}

	}

	/**
	 * Methods to verify the Home page sub menus
	 * 
	 * @author
	 * @param expectedSubMenus
	 */
	public void verifyHomePageSubMenus(String[] expectedSubMenus) {
		logger.info("Verifying Sub menus in home page");

		Object[] actualSubMenus;

		progressHelpers.waitForPageLoad();

		actualSubMenus = generalHelper.getAttributeValuesFromListOfElements(subMenuLinks, "innerText").toArray();
		assertHelper.compareTwoValues(actualSubMenus, expectedSubMenus);
	}

	/**
	 * Method to enter username and password
	 * 
	 * @author
	 * @param username
	 * @param password
	 */
	public void enterUserCredential(String username, String password) {
		logger.info("Sending username as " + username + " and password");

		progressHelpers.waitForPageLoad();
		generalHelper.enterTextInTextField(textField_Email, username);
		generalHelper.enterTextInTextField(textField_Password, password);
	}

	public void clickOnTheSignInLink() {
		logger.info("Click on logging in");
		
		progressHelpers.hardWait(2000);
		progressHelpers.waitForElementToBeClickable(menuLink_SignIn);
		buttonHelper.click(menuLink_SignIn);
	}
	
	/**
	 * Method to user sign in to the application
	 * 
	 * @author
	 * @param loginCredentialsMapData
	 */
	public void userLogin(Map<String, String> loginCredentialsMapData) {
		logger.info("User logging in");

		progressHelpers.waitForElementToBeClickable(menuLink_SignIn);

		buttonHelper.click(menuLink_SignIn);
		enterUserCredential(loginCredentialsMapData.get("email"), loginCredentialsMapData.get("password"));
		buttonHelper.click(btn_SignIn);
		progressHelpers.waitForPageLoad();
	}

	/**
	 * Method to verify the logged-in user's name in the header
	 * 
	 * @author
	 * @param expectedName
	 */
	public void verifyLoggedInUserNameInTheHeader(String expectedName) {
		logger.info("Verifying the logged-in user's name in the header with Welcome text");

		progressHelpers.waitForElementAttributeValueToChanged(text_UserNameInTheHeader, "innerText", expectedName);

		assertHelper.assertTextMatching(expectedName, generalHelper.getText(text_UserNameInTheHeader));
	}

	/**
	 * Method to accept the news letter
	 * 
	 * @author
	 * @throws InterruptedException
	 */
	public void acceptNewesletter() {
		logger.info("Accepting NewsLetter");

		if (System.getProperty("ENV").equalsIgnoreCase("prod")) {
			try {
				progressHelpers.hardWait(3000);
				progressHelpers.waitForElementToDisplay(textfield_EmailNewesLetter);
				boolean newsletter = textfield_EmailNewesLetter.isDisplayed();
				if (newsletter == true) {
					textfield_EmailNewesLetter.sendKeys(Keys.ESCAPE);
				}
			} catch (NoSuchElementException e) {
				logger.info("News letter subscribe button is not displayed");
			}

		}

	}

	/**
	 * Method to Verify the SignIn With Invalid Credentials Error Message
	 * 
	 * @author
	 * @param expectedErrorMsg
	 */
	public void verifyInvalidCredentialsErrorMessage(String expectedErrorMsg) {
		logger.info("Verifying the invalid credentials error message");

		progressHelpers.waitForElementToDisplay(text_ErrorMsg);
		assertHelper.assertTextMatching(expectedErrorMsg, text_ErrorMsg.getText());
	}

	/**
	 * Method to click on forgot password link
	 * 
	 * @author
	 */
	public void clickOnForgotPasswordLink() {
		logger.info("Clicking on the forgot password link");

		progressHelpers.waitForPageLoad();

		buttonHelper.click(menuLink_SignIn);

		buttonHelper.click(link_ForgotPassword);
	}

	/**
	 * Method to verify the reset password page
	 * 
	 * @author
	 */
	public void verifyForgotYourPasswordRecoveryFunctionality(Map<String, String> forgotPasswordMapData) {
		logger.info("Verifying the forgot password page");

		progressHelpers.waitForPageLoad();

		generalHelper.enterTextInTextField(txtField_ForgotRecoveryEmail, forgotPasswordMapData.get("recoveryemail"));
		buttonHelper.click(btn_ResetPassword);
		assertHelper.assertTextMatching(forgotPasswordMapData.get("message"),text_EmailSentSuccessMessage.getAttribute("innerText"));
	}

	/**
	 * Method to verify the page title
	 * 
	 * @author
	 * @param expectedPageTitle
	 */
	public void verifyPageTitle(String expectedPageTitle) {
		logger.info("verifying the page title");

		progressHelpers.waitForPageLoad();
		if (driver.getWindowHandles().size() == 2) {
			browserHelper.SwitchToWindow(1);
		}
		assertHelper.assertTextMatching(expectedPageTitle.toLowerCase(),browserHelper.getCurrentPageTitle().toLowerCase());
	}

	/**
	 * Method to add products to cart from home page
	 * 
	 * @author
	 * @param productNames
	 */
	public void addProductsToTheCart(String[] productNames) {
		logger.info("Adding product to the cart from home page");

		searchTheProducts(productNames);

		WebElement expectedProductToAddToCart;

		for (String productName : productNames) {

			expectedProductToAddToCart = generalHelper.returnElementWithDynamicXpath(addToCartButtonOfProductXpath,productName);
			javascriptHelper.scrollIntoViewAndClick(expectedProductToAddToCart);
		}
		if (icon_Cart.isDisplayed()) {
			logger.info("Mini cardt is present");
		} else {
			icon_Cart.click();
		}
	}

	/**
	 * Method to check the home page objects
	 * 
	 * @author
	 */
	public void verifyHomePage() {
		logger.info("Verifying the home page elements");

		String expectedUserNameInWelcomeText = jsonReader.readJsonData("verificationData", "homepage","usernameintheheader");

		progressHelpers.hardWait(1000);
		progressHelpers.waitForElementAttributeValueToChanged(text_UserNameInTheHeader, "innerText",expectedUserNameInWelcomeText);
		assertHelper.assertElementsExistsInPage(text_UserNameInTheHeader);
	}

	/**
	 * Method to sign out the user
	 * 
	 * @author
	 */
	public void userSignOut() {
		logger.info("User logging out");

		buttonHelper.click(btn_Username);
		progressHelpers.waitForElementToDisplay(menuLink_SignOut);
		buttonHelper.click(menuLink_SignOut);
		// Application will be wait for 5 seconds after sign out
		progressHelpers.hardWait(5000);
	}

	/**
	 * Method to click on My Account menu
	 * @author 
	 */
	public void clickOnMyAccountMenu() {	
		logger.info("Clicking on My Account menu");

		progressHelpers.waitForElementToBeClickable(btn_Username);
		buttonHelper.click(btn_Username);
		progressHelpers.hardWait(500);;
		buttonHelper.click(menuLink_MyAccount);			
	}
	
	/** Method to add products to cart from home page
	 * 
	 * @author
	 * @param productNames
	 */
	public void addProductsToTheCartWithOutLogin(String[] productNames) {
		logger.info("Adding product to the cart from home page");

		searchTheProducts(productNames);

		WebElement expectedProductToAddToCart;

		for (String productName : productNames) {

			expectedProductToAddToCart = generalHelper.returnElementWithDynamicXpath(addToCartButtonOfProductXpath,productName);
			javascriptHelper.scrollIntoViewAndClick(expectedProductToAddToCart);
		}
		}
	/**
	 * Method to Go to Home page
	 * @author 
	 */
	
	public void goToHomePage() {	
		logger.info("Clicking table topics logo");

		buttonHelper.click(TableTopics_Logo);
				
	}
	
	/**
	 * Methods to verify the Home page sub category menus
	 * @author
	 * @param expectedSubCategoryMenus
	 */
	public void verifyHomePageSubCategoryMenus(String[] expectedSubCategoryMenus) {
		logger.info("Verifying Sub category menus in home page");

		Object[] actualSubMenus;

		progressHelpers.waitForPageLoad();

		buttonHelper.moveToElement(link_TableTopEditions, driver);
		
		actualSubMenus = generalHelper.getAttributeValuesFromListOfElements(subCategoryMenuLinks, "innerText").toArray();
		
		assertHelper.compareTwoValues(actualSubMenus, expectedSubCategoryMenus);
	}

	/**
	 * Method to check whether the Add to Cart button available for all products in
	 * home page
	 * 
	 * @author 
	 * @param productNames
	 */
	public void verifyAddToCartButtonAvailableForTheProducts(String[] productNames) {
		logger.info("Verifying 'Add to cart' button");

		WebElement productToCheckAddToCartButton;

		progressHelpers.waitForPageLoad();

		buttonHelper.click(link_ShopAll);
		
		for (String productName : productNames) {
			productToCheckAddToCartButton = generalHelper.returnElementWithDynamicXpath(addToCartButtonOfProductXpath,productName);
			assertHelper.assertElementsExistsInPage(productToCheckAddToCartButton);
		}
	}
	
	/**
	 * Method to click on the Read More
	 * @author 
	 */
	public void clickOnReadMore() {
		logger.info("Clicking on Read More");
		
		buttonHelper.click(link_ReadMore);
	}
	
	/**
	 * Method to verify the about page content
	 * @author 
	 * @param expectedAboutPage
	 */
	public void verifyAboutPageBodyContent(String expectedAboutPage) {
		logger.info("Verifying the about page content");
		
		assertHelper.assertTextMatching(expectedAboutPage.replaceAll("\\s", ""), text_AboutContent.getText().replaceAll("\\s", ""));
	}
}
