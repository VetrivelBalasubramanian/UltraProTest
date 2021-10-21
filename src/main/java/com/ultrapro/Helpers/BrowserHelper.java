package com.ultrapro.Helpers;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.LinkedList;
import java.util.Set;

public class BrowserHelper extends GeneralHelper{

	private Logger logger = LoggerHelper.getLogger(BrowserHelper.class);

	public void goBack() {
		driver.navigate().back();
		logger.info("");
	}

	public void goForward() {
		driver.navigate().forward();
		logger.info("");
	}

	public void refresh() {
		driver.navigate().refresh();
		logger.info("");
	}

	public Set<String> getWindowHandlens() {
		logger.info("");
		return driver.getWindowHandles();
	}
	
	public String getWindowHandle() {
		logger.info("get current window name");
		return driver.getWindowHandle();
	}

	public void SwitchToWindow(int index) {

		LinkedList<String> windowsId = new LinkedList<String>(getWindowHandlens());

		if (index < 0 || index > windowsId.size())
			throw new IllegalArgumentException("Invalid Index : " + index);

		driver.switchTo().window(windowsId.get(index));
		logger.info(index);
	}
	
	public void switchToSpecificWindow(String windowId) {

		driver.switchTo().window(windowId);
		logger.info("Switched to window : "+ windowId.toString());
	}

	public void switchToParentWindow() {
		LinkedList<String> windowsId = new LinkedList<String>(getWindowHandlens());
		driver.switchTo().window(windowsId.get(0));
		logger.info("");
	}

	public void switchToParentWithChildClose() {
		switchToParentWindow();

		LinkedList<String> windowsId = new LinkedList<String>(getWindowHandlens());

		for (int i = 1; i < windowsId.size(); i++) {
			logger.info(windowsId.get(i));
			driver.switchTo().window(windowsId.get(i));
			driver.close();
		}

		switchToParentWindow();
	}

	public void switchToFrame(By locator) {
		driver.switchTo().frame(driver.findElement(locator));
		logger.info(locator);
	}

	public void switchToFrame(String nameOrId) {
		driver.switchTo().frame(nameOrId);
		logger.info(nameOrId);
	}

	public void switchToFrame(int id) {
		driver.switchTo().frame(id);
		logger.info(id);
	}
	
	public String getCurrentURL() {
		logger.info("Current URL is : "+ driver.getCurrentUrl());
		return driver.getCurrentUrl();
	}
	
	public String getCurrentPageTitle() {
	    logger.info("Current Title is : "+ driver.getTitle());
	   return driver.getTitle();
	}
	
	/**
	 * accept cookies
	 * @author ankit
	 */
	public void acceptCookies(WebElement acceptButton, String iFrameId){

		String currentWindowId = getWindowHandle();
		
		try {
			logger.info("accept cookies.");
			if(!iFrameId.equalsIgnoreCase("")) {
				progressHelpers.waitForIframeAndSwitchToIt(By.id(iFrameId), 30, 1);
			}
			progressHelpers.waitForElementToDisplay(acceptButton);
			acceptButton.click();
		} catch (Exception e) {
		}
		switchToSpecificWindow(currentWindowId);
	}

}
