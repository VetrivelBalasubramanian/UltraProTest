package com.ultrapro.Helpers;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.ultrapro.utils.Data.PropertyReader;

public class JavascriptHelper extends GeneralHelper{
	private Logger logger = LoggerHelper.getLogger(JavascriptHelper.class);
	ProgressHelpers progressHelpers = new ProgressHelpers();
	ButtonHelper buttonHelper = new ButtonHelper();

	public Object executeScript(String script) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		logger.info(script);
		return exe.executeScript(script);
	}

	public Object executeScript(String script, Object... args) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		logger.info(script);
		return exe.executeScript(script, args);
	}

	public void scrollToElemet(WebElement element) {
		executeScript("window.scrollTo(arguments[0],arguments[1])", element.getLocation().x, element.getLocation().y);
		logger.info(element);
	}

	public void scrollToElemet(By locator) {
		scrollToElemet(driver.findElement(locator));
		logger.info(locator);
	}

	public void scrollToElemetAndClick(By locator) {
		WebElement element = driver.findElement(locator);
		scrollToElemet(element);
		buttonHelper.click(element);
		logger.info(locator);
	}

	public void scrollToElemetAndClick(WebElement element) {
		scrollToElemet(element);
		buttonHelper.click(element);
		logger.info(element);
	}

	public void scrollIntoView(WebElement element) {
		executeScript("arguments[0].scrollIntoView()", element);
		logger.info(element);
	}

	public void scrollIntoView(By locator) {
		scrollIntoView(driver.findElement(locator));
		logger.info(locator);
	}

	public void scrollIntoViewAndClick(By locator) {
		WebElement element = driver.findElement(locator);
		scrollIntoView(element);
		buttonHelper.click(element);
		logger.info(locator);
	}

	public void scrollIntoViewAndClick(WebElement element) {
		scrollIntoView(element);
		buttonHelper.click(element);
		logger.info(element);
	}

	public void clickOnElement(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}

	public void enterText(WebElement element, String inputText) {
		progressHelpers.hardWait(2000);
		executeScript("arguments[0].setAttribute('value','" + inputText + "')", element);
	}
	
	public void openNewTab(String lang) {
    	executeScript("window.open('"+PropertyReader.getConfigProperties(System.getProperty("ENV"), "url")+lang+"','_blank');");
    	logger.info("New tab is opened");
	}
	
	public void openNewTabWithURL(String url) {
    	executeScript("window.open('"+url+"','_blank');");
    	logger.info("New tab is opened with URL : "+ url);
	}
}
