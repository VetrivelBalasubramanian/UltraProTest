package com.ultrapro.pageObjects;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.ultrapro.commonUtils.InitPageObjectClasses;

public class MyOrdersPage extends InitPageObjectClasses {

	public MyOrdersPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	/* Element of MyOrders page */

	@FindAll({ @FindBy(xpath = "//tr[@class='grand totals' or @class='grand totals incl']//span[@class='price']"),
			@FindBy(xpath = "//tr[@class='grand_total']//td[@class='amount']//strong") })
	private WebElement text_orderTotalCost;

	@FindBy(xpath = "//ul[@class='nav items']//li[2]")
	private WebElement sideNavBar_MyOrders;

	@FindBy(xpath = "//ul[@class='nav items']//li[3]")
	private WebElement sideNavBar_MyWishList;

	@FindBy(xpath = "//ul[@class='nav items']//li[5]")
	private WebElement sideNavBar_AddressBook;

	@FindBy(xpath = "//table[@id='my-orders-table']")
	private WebElement table_OrderItems;

	@FindBy(xpath = "//div[@class='block block-order-details-view']//div[@class='block-title']//strong[1]")
	private WebElement text_OrderInformation;

	@FindBy(className = "base")
	private WebElement text_orderNumber;

	@FindBy(className = "order-status")
	private WebElement text_orderStatus;

	@FindBy(css = ".product.name.product-item-name")
	private List<WebElement> productNamesInOrderDetails;

	@FindBy(xpath = "//div[@class='box box-order-shipping-address']//address")
	private WebElement shippingAddress;

	@FindBy(xpath = "//div[@class='box box-order-shipping-method']//div[@class='box-content']")
	private WebElement shippingMethod;

	@FindBy(xpath = "//div[@class='box box-order-billing-address']//address")
	private WebElement billingAddress;

	@FindBy(xpath = "//dl[@class='payment-method']//dt[@class='title']")
	private WebElement paymentMethod;

	@FindBy(xpath = "//tr[@class='grand_total_incl']//td[@class='amount']//strong")
	private WebElement grandTotal;

	@FindBy(xpath = "//ul//button[@class='action primary checkout']")
	private WebElement btn_ProceedToCheckOut;

	@FindBy(xpath = "//table[@id='my-orders-table']//tr[1]//td[@class='col actions']//a[@class='action order'][1]")
	private WebElement btn_Reorder;

	String expectedViewOrderXpath = "//table[@id='my-orders-table']//tr[1]//td[text()='{PARAM1}']//following::td[@class='col actions'][1]//a[@class='action view']";
	String shippingCostXpath = "//table[@class='table-checkout-shipping-method']//tbody//tr[{PARAM1}]//td[@class='col col-price']//span[@class='price']//span";
	String expectedCancelOrderXpath = "//table[@id='my-orders-table']//tr[1]//td[text()='{PARAM1}']//following::td[@class='col actions'][1]//a[@class='action cancel-{PARAM1}']";

	/* Page Methods */

	/**
	 * Method to calculate the total order cost of the product purchased
	 * 
	 * @author
	 * @param shippingMethod
	 * @param zipcode
	 * @return
	 */
	public double calculateTotalOrderCost(String shippingMethod, int zipcode,String productCost) {
		logger.info("Calculating the total order cost for given product name and shipping method");

		double taxAmount, totalOrderCost;
		double shippingCost = 0;
		DecimalFormat df = new DecimalFormat("#.##");

		Double productTotalCost = Double.valueOf(productCost);
		shippingCost = calculateShippingCost(shippingMethod);

		taxAmount = calculateTaxAmount(zipcode, productTotalCost);
		totalOrderCost = productTotalCost + shippingCost + taxAmount;

		df.setRoundingMode(RoundingMode.HALF_UP);
		totalOrderCost = Double.valueOf(df.format(totalOrderCost));

		return totalOrderCost;
	}

	/**
	 * Method to calculate the product's shipping cost
	 * 
	 * @author
	 * @param shippingMethod
	 * @return
	 */
	public double calculateShippingCost(String shippingMethod) {
		logger.info("Calculating the shipping cost of the product");

		WebElement shippingMethodCost;
		double shippingCost = 0;
		String regex = "\\d{1,3}\\,\\d+\\.\\d{1,2}|\\d{1,3}\\,\\d+|\\d+\\.\\d{1,2}|\\d+";

		switch (shippingMethod) {
		case "Ground":
			shippingMethodCost = generalHelper.returnElementWithDynamicXpath(shippingCostXpath, "1");
			shippingCost = Double.parseDouble(generalHelper.returnPatternMatcher(regex,
					generalHelper.getAttributeValue(shippingMethodCost, "innerText")));
			break;
		case "NextDay":
			shippingMethodCost = generalHelper.returnElementWithDynamicXpath(shippingCostXpath, "4");
			shippingCost = Double.parseDouble(generalHelper.returnPatternMatcher(regex,
					generalHelper.getAttributeValue(shippingMethodCost, "innerText")));
			break;
		case "SeconDday":
			shippingMethodCost = generalHelper.returnElementWithDynamicXpath(shippingCostXpath, "3");
			shippingCost = Double.parseDouble(generalHelper.returnPatternMatcher(regex,
					generalHelper.getAttributeValue(shippingMethodCost, "innerText")));
			break;
		case "ThreeDay":
			shippingMethodCost = generalHelper.returnElementWithDynamicXpath(shippingCostXpath, "2");
			shippingCost = Double.parseDouble(generalHelper.returnPatternMatcher(regex,
					generalHelper.getAttributeValue(shippingMethodCost, "innerText")));
			break;
		}
		return shippingCost;
	}

	/**
	 * Method to calculate the tax amount for the buying product
	 * 
	 * @param zipcode
	 * @param productCost
	 * @return
	 */
	public double calculateTaxAmount(int zipcode, double productCost) {
		logger.info("Calculating the tax amount");

		double taxPercentage = 0, taxAmount;
		DecimalFormat df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.UP);

		if (zipcode == 94588) {
			taxPercentage = 10.24;
		}
		else {
			taxPercentage = 12.45;
		}
		taxAmount = (productCost * taxPercentage) / 100;
		taxAmount = Double.valueOf(df.format(taxAmount));

		return taxAmount;

	}

	/**
	 * Method to verify the total order cost
	 * 
	 * @author
	 * @param expectedTotalOrderCost
	 */
	public void verifyTheTotalOrderCost(String expectedTotalOrder) {
		logger.info("Verifying the total order cost");

		String orderCost, actualTotalOrderCost,regex = "\\d{1,3}\\,\\d+\\.\\d{1,2}|\\d{1,3}\\,\\d+|\\d+\\.\\d{1,2}|\\d+";

		progressHelpers.hardWait(3000);

		orderCost = text_orderTotalCost.getAttribute("innerText");
		progressHelpers.waitForElementAttributeValueToChanged(text_orderTotalCost, "innerText", orderCost);
		actualTotalOrderCost = generalHelper.returnPatternMatcher(regex, orderCost);

		BigDecimal bd = new BigDecimal(actualTotalOrderCost);
		actualTotalOrderCost = bd.setScale(2, RoundingMode.HALF_UP).stripTrailingZeros().toString();

		BigDecimal bd1 = new BigDecimal(expectedTotalOrder);
		expectedTotalOrder = bd1.setScale(2, RoundingMode.HALF_UP).stripTrailingZeros().toString();

		assertHelper.compareTwoValues(Double.valueOf(actualTotalOrderCost), Double.valueOf(expectedTotalOrder));
	}

	/**
	 * Method to select the my account page side menu
	 * 
	 * @author
	 * @param menuItem
	 */
	public void selectMyAccountSideNavMenu(String menuItem) {
		logger.info("Selecting my account side menu " + menuItem);

		progressHelpers.waitForElementToBeClickable(sideNavBar_MyOrders);

		switch (menuItem.toLowerCase()) {
		case "my orders":
			buttonHelper.click(sideNavBar_MyOrders);
			break;
		case "my wishlist":
			buttonHelper.click(sideNavBar_MyWishList);
			break;
		case "address book":
			buttonHelper.click(sideNavBar_AddressBook);
			break;

		}
	}

	/**
	 * Method to verify the order details in view order page
	 * 
	 * @author
	 * @param orderNumber
	 * @param expectedOrderStatus
	 * @param expectedProductNames
	 * @param checkoutDetailsMapData
	 */
	public void verifyOrderedDetailsInViewOrderPage(String orderNumber, String expectedOrderStatus,String[] expectedProductNames, double totalOrderCost, Map<String, String> checkoutDetailsMapData) {

		WebElement viewOrderLink = generalHelper.returnElementWithDynamicXpath(expectedViewOrderXpath, orderNumber);
		String expectedOrderId = jsonReader.readJsonData("verificationData", "vieworderpage", "orderid") + orderNumber;
		String expectedPaymentMethod = jsonReader.readJsonData("verificationData", "vieworderpage", "paymentmethod");
		String orderCost, expectedTotalOrderCost, actualTotalOrderCost,regex = "\\d{1,3}\\,\\d+\\.\\d{1,2}|\\d{1,3}\\,\\d+|\\d+\\.\\d{1,2}|\\d+";
		Object[] actualProductNames;

		buttonHelper.click(viewOrderLink);
		progressHelpers.waitForElementToDisplay(text_orderNumber);

		orderCost = grandTotal.getAttribute("innerText");
		actualTotalOrderCost = generalHelper.returnPatternMatcher(regex, orderCost);

		BigDecimal bd = new BigDecimal(actualTotalOrderCost);
		actualTotalOrderCost = bd.setScale(2, RoundingMode.HALF_UP).stripTrailingZeros().toString();

		BigDecimal bd1 = new BigDecimal(totalOrderCost);
		expectedTotalOrderCost = bd1.setScale(2, RoundingMode.HALF_UP).stripTrailingZeros().toString();

		actualProductNames = generalHelper.getAttributeValuesFromListOfElements(productNamesInOrderDetails, "innerText").toArray();

		assertHelper.assertTextMatching(expectedOrderStatus, text_orderStatus.getAttribute("innerText"));

		assertHelper.assertTextMatching(expectedOrderId, text_orderNumber.getAttribute("innerText"));

		assertHelper.compareTwoValues(actualProductNames, expectedProductNames);

		assertHelper.assertTextMatching(expectedTotalOrderCost, actualTotalOrderCost);

		assertHelper.assertTextMatching(checkoutDetailsMapData.get("address"),shippingAddress.getAttribute("innerText").replaceAll("\\n", ", "));

		assertHelper.assertTextMatching(checkoutDetailsMapData.get("shippingbyandmethod"),shippingMethod.getAttribute("innerText"));

		assertHelper.assertTextMatching(checkoutDetailsMapData.get("address"),billingAddress.getAttribute("innerText").replaceAll("\\n", ", "));

		assertHelper.assertTextMatching(expectedPaymentMethod, paymentMethod.getAttribute("innerText"));
	}

	/**
	 * Method to click on the Reorder option
	 * 
	 * @author
	 */
	public void reOrderTheLastOrder() {
		logger.info("Clicking on the Reorder Option");

		progressHelpers.waitForElementToDisplay(btn_Reorder);
		buttonHelper.click(btn_Reorder);
	}

	/**
	 * Method to Clicking on the proceed to checkout button in the shopping cart
	 * 
	 * @author
	 */
	public void clickOnProceedToCheckoutButtonInShoppinCartPage() {
		logger.info("Clicking on the proceed to checkout button in the shopping cart page");

		progressHelpers.waitForElementToDisplay(btn_ProceedToCheckOut);
		buttonHelper.click(btn_ProceedToCheckOut);

	}
}
