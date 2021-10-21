package com.ultrapro.Helpers;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ButtonHelper extends GeneralHelper {

	Logger logger = LoggerHelper.getLogger(ButtonHelper.class);

	public void click(By locator) {
		click(driver.findElement(locator));
		logger.info(locator);
	}

	public void click(WebElement element) {
		try {			
			progressHelpers.waitForElementToBeClickable(element);
		} catch (Exception e1) {
		}
		try {
			element.click();
		} catch (Exception e) {
			element.sendKeys(Keys.ENTER);
		}
		logger.info(element);
	}
	
	public void clickAction(WebElement webEle) {
		new Actions(driver).moveToElement(webEle).click().perform();
		logger.info(webEle);
	}
}
