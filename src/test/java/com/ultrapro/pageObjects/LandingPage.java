package com.ultrapro.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ultrapro.commonUtils.InitPageObjectClasses;

public class LandingPage extends InitPageObjectClasses{

	public LandingPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	/*Element of landing page*/
	@FindBy(xpath = "//button[@class='intro-banner-btn']")
	private WebElement button_NewsLetterDismiss;
	
	public void verifyThePageTitle() {
		logger.info("Check the page title");

		assertHelper.assertTextMatching(browserHelper.getCurrentPageTitle(),"Conversation starters - Icebreaker Games | TableTopics"); 
	}
}
