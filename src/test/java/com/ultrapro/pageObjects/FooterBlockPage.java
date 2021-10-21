package com.ultrapro.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ultrapro.commonUtils.InitPageObjectClasses;

public class FooterBlockPage extends InitPageObjectClasses {

	public FooterBlockPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	/* Element of Footer Block page */

	@FindBy(id = "search")
	private WebElement textBox_Search;

	@FindBy(xpath = "//div[@class='col-md-3 col-sm-6 col-xs-12 mobile-toggle']//ul//li[1]//a")
	private WebElement link_ShippingInfo;

	@FindBy(xpath = "//div[@class='col-md-3 col-sm-6 col-xs-12 mobile-toggle']//ul//li[2]//a")
	private WebElement link_PrivacyPolicy;

	@FindBy(xpath = "//div[@class='col-md-3 col-sm-6 col-xs-12 mobile-toggle']//ul//li[3]//a")
	private WebElement link_FAQs;

	@FindBy(xpath = "//div[@class='col-md-3 col-sm-6 col-xs-12 mobile-toggle']//ul//li[4]//a")
	private WebElement link_AwardWinningEditions;

	@FindBy(xpath = "//div[@class='col-md-3 col-sm-6 col-xs-12 mobile-toggle']//ul//li[5]//a")
	private WebElement link_GiftIdeas;

	@FindBy(xpath = "//div[@class='col-md-3 col-sm-6 col-xs-12 mobile-toggle']//ul//li[6]//a")
	private WebElement link_StoreLocator;

	@FindBy(xpath = "//div[@class='col-md-3 col-sm-6 col-xs-12 mobile-toggle']//ul//li[7]//a")
	private WebElement link_WholesaleOrders;

	@FindBy(xpath = "//div[@class='col-md-3 col-sm-6 col-xs-12 mobile-toggle']//ul//li[8]//a")
	private WebElement link_PriceList;
	
	@FindBy(xpath = "//a[text()='Email']")
	private WebElement link_Email;
	
	@FindBy(xpath = "//a[text()='Facebook']")
	private WebElement link_Facebook;
	
	@FindBy(xpath = "//a[text()='Instagram']")
	private WebElement link_Instagram;
	
	@FindBy(xpath = "//a[text()='Pinterest']")
	private WebElement link_Pinterest;

	@FindBy(xpath = "//a[text()='TikTok']")
	private WebElement link_TikTok;
	
	/* Page Methods */
	
	/**
	 * Method to select the quick links by given param
	 * 
	 * @author
	 * @param quickLinks
	 */
	public void quickLinksAs(String quickLinks) {
		logger.info("Selecting " + quickLinks + " as quick Links");

		switch (quickLinks.toLowerCase()) {
		case "shipping information":
			buttonHelper.click(link_ShippingInfo);
			break;
		case "privacy policy":
			buttonHelper.click(link_PrivacyPolicy);
			break;
		case "faqs":
			buttonHelper.click(link_FAQs);
			break;
		case "award-winning editions":
			buttonHelper.click(link_AwardWinningEditions);
			break;
		case "gift ideas":
			buttonHelper.click(link_GiftIdeas);
			break;
		case "store locator":
			buttonHelper.click(link_StoreLocator);
			break;
		case "wholesale orders":
			buttonHelper.click(link_WholesaleOrders);
			break;
		case "Price List":
			buttonHelper.click(link_PriceList);
			break;
		case "email":
			buttonHelper.click(link_Email);
			break;
		case "facebook":
			buttonHelper.click(link_Facebook);
			break;
		case "instagram":
			buttonHelper.click(link_Instagram);
			break;
		case "pinterest":
			buttonHelper.click(link_Pinterest);
			break;
		case "tiktok":
			buttonHelper.click(link_TikTok);
			break;
		default:
			break;
		}
	}
}
