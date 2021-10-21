package com.ultrapro.Helpers;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ultrapro.utils.Driver.DriverManager;

public class LinkHelper extends GeneralHelper {
	WebDriver driver = DriverManager.getDriver();
	private Logger logger = LoggerHelper.getLogger(LinkHelper.class);

	public void clickLink(String linkText) {
		logger.info(linkText);
		getElement(By.linkText(linkText)).click();
	}

	public void clickPartialLink(String partialLinkText) {
		logger.info(partialLinkText);
		getElement(By.partialLinkText(partialLinkText)).click();
	}

	public String getHyperLink(By locator) {
		logger.info(locator);
		return getHyperLink(getElement(locator));
	}

	public String getHyperLink(WebElement element) {
		String link = element.getAttribute("hreg");
		logger.info("Element : " + element + " Value : " + link);
		return link;
	}
}
