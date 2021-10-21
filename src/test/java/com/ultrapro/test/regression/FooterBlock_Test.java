package com.ultrapro.test.regression;

import org.testng.annotations.Test;

import com.ultrapro.commonUtils.InitPageObjectClasses;

public class FooterBlock_Test extends InitPageObjectClasses {

	//@Test(groups = "Regression", testName = "TT_13", description = "Verify Clicking On Shipping Information Link Redirects To Shipping Information Page and check the page title")
	public void TT_13_verifyShippingInformationLink() {

		String expectedPageTitle = jsonReader.readJsonData("verificationData", "footerblock", "shippinginformation");

		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Step-1: Click The Shipping Information in quick links */
		getFooterBlockPage().quickLinksAs("Shipping Information");

		/* Step-2: Verify Shipping Information Page */
		getHomePage().verifyPageTitle(expectedPageTitle);

		softAssert.assertAll();

	}

	//@Test(groups = "Regression", testName = "TT_14", description = "Verify Clicking On Privacy Policy Link Redirects To Privacy Policy Page and check the page title")
	public void TT_14_verifyPrivacyPolicyLink() {

		String expectedPageTitle = jsonReader.readJsonData("verificationData", "footerblock", "privacypolicy");

		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Step-1: Click The Privacy Policy in quick links */
		getFooterBlockPage().quickLinksAs("Privacy Policy");

		/* Step-2: Verify Privacy Policy Page */
		getHomePage().verifyPageTitle(expectedPageTitle);

		softAssert.assertAll();

	}

	//@Test(groups = "Regression", testName = "TT_15", description = "Verify Clicking On FAQs Link Redirects To FAQs Page and check the page title")
	public void TT_15_verifyFAQsLink() {

		String expectedPageTitle = jsonReader.readJsonData("verificationData", "footerblock", "faqs");

		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Step-1: Click The FAQs in quick links */
		getFooterBlockPage().quickLinksAs("FAQs");

		/* Step-2: Verify FAQs Page */
		getHomePage().verifyPageTitle(expectedPageTitle);

		softAssert.assertAll();

	}

	//@Test(groups = "Regression", testName = "TT_16", description = "Verify Clicking On Award-winning Editions Link Redirects To Award-winning Editions Page and check the page title")
	public void TT_16_verifyAwardWinningEditionsLink() {

		String expectedPageTitle = jsonReader.readJsonData("verificationData", "footerblock", "awardwinningeditions");

		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Step-1: Click The Award-winning Editions in quick links */
		getFooterBlockPage().quickLinksAs("Award-winning Editions");

		/* Step-2: Verify Award-winning Editions Page */
		getHomePage().verifyPageTitle(expectedPageTitle);

		softAssert.assertAll();

	}

	//@Test(groups = "Regression", testName = "TT_17", description = "Verify Clicking On Gift Ideas Link Redirects To Gift Ideas Page and check the page title")
	public void TT_17_verifyGiftIdeasLink() {

		String expectedPageTitle = jsonReader.readJsonData("verificationData", "footerblock", "giftideas");

		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Step-1: Click The Gift Ideas in quick links */
		getFooterBlockPage().quickLinksAs("Gift Ideas");

		/* Step-2: Verify Gift Ideas Page */
		getHomePage().verifyPageTitle(expectedPageTitle);

		softAssert.assertAll();

	}

	//@Test(groups = "Regression", testName = "TT_18", description = "Verify Clicking On Store Locator Link Redirects To Store Locator Page and check the page title")
	public void TT_18_verifyStoreLocatorLink() {

		String expectedPageTitle = jsonReader.readJsonData("verificationData", "footerblock", "storelocator");

		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Step-1: Click The Store Locator in quick links */
		getFooterBlockPage().quickLinksAs("Store Locator");

		/* Step-2: Verify Store Locators Page */
		getHomePage().verifyPageTitle(expectedPageTitle);

		softAssert.assertAll();

	}

	//@Test(groups = "Regression", testName = "TT_19", description = "Verify Clicking On Wholesale Orders Link Redirects To Wholesale Orders Page and check the page title")
	public void TT_19_verifyWholesaleOrdersLink() {

		String expectedPageTitle = jsonReader.readJsonData("verificationData", "footerblock", "wholesaleorders");

		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Step-1: Click The Wholesale Orders in quick links */
		getFooterBlockPage().quickLinksAs("Wholesale Orders");

		/* Step-2: Verify Wholesale Orders Page */
		getHomePage().verifyPageTitle(expectedPageTitle);

		softAssert.assertAll();

	}

	//@Test(groups = "Regression", testName = "TT_20", description = "Verify Clicking On Price List Link Redirects To Price List Page and check the page title")
	public void TT_20_verifyPriceListLink() {

		String expectedPageTitle = jsonReader.readJsonData("verificationData", "footerblock", "pricelist");

		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Step-1: Click The Price List in quick links */
		getFooterBlockPage().quickLinksAs("Price List");

		/* Step-2: Verify Price List Page */
		getHomePage().verifyPageTitle(expectedPageTitle);

		softAssert.assertAll();

	}
	@Test(groups = "Regression", testName = "TT_68", description = "Verify Clicking On Facebook Link Redirects To Facebook Page and check the page title")
	public void TT_68_VerifyFacebookLink() {

		String expectedPageTitle = jsonReader.readJsonData("verificationData", "footerblock", "facebook");

		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Step-1: Click The Facebook in quick links */
		getFooterBlockPage().quickLinksAs("Facebook");

		/* Step-2: Verify Facebook Page */
		getHomePage().verifyPageTitle(expectedPageTitle);

		softAssert.assertAll();
	}
	
	@Test(groups = "Regression", testName = "TT_69", description = "Verify Clicking On Instagram Link Redirects To Instagram Page and check the page title")
	public void TT_69_VerifyInstagramLink() {

		String expectedPageTitle = jsonReader.readJsonData("verificationData", "footerblock", "instagram");

		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Step-1: Click The Instagram in quick links */
		getFooterBlockPage().quickLinksAs("Instagram");

		/* Step-2: Verify Instagram Page */
		getHomePage().verifyPageTitle(expectedPageTitle);

		softAssert.assertAll();
	}
	
	@Test(groups = "Regression", testName = "TT_70", description = "Verify Clicking On Pinterest Link Redirects To Pinterest Page and check the page title")
	public void TT_70_VerifyPinterestLink() {

		String expectedPageTitle = jsonReader.readJsonData("verificationData", "footerblock", "pinterest");

		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Step-1: Click The Pinterest in quick links */
		getFooterBlockPage().quickLinksAs("Pinterest");

		/* Step-2: Verify Pinterest Page */
		getHomePage().verifyPageTitle(expectedPageTitle);

		softAssert.assertAll();
	}
	
	@Test(groups = "Regression", testName = "TT_71", description = "Verify Clicking On TikTok Link Redirects To TikTok Page and check the page title")
	public void TT_71_VerifyTitTokLink() {

		String expectedPageTitle = jsonReader.readJsonData("verificationData", "footerblock", "tiktok");

		/* Accept News Letter */
		getHomePage().acceptNewesletter();

		/* Step-1: Click The TikTok in quick links */
		getFooterBlockPage().quickLinksAs("TikTok");

		/* Step-2: Verify TikTok Page */
		getHomePage().verifyPageTitle(expectedPageTitle);

		softAssert.assertAll();
	}
}
