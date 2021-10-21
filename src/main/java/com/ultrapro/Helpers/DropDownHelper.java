package com.ultrapro.Helpers;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.ultrapro.utils.Driver.DriverManager;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DropDownHelper extends GeneralHelper {
	WebDriver driver = DriverManager.getDriver();
	Logger logger = LoggerHelper.getLogger(DropDownHelper.class);
	ButtonHelper buttonHelper = new ButtonHelper();

	public void SelectUsingVisibleValue(By locator, String visibleValue) {
		SelectUsingVisibleValue(getElement(locator), visibleValue);
	}

	public void SelectUsingVisibleValue(WebElement element, String visibleValue) {
		Select select = new Select(element);
		select.selectByVisibleText(visibleValue);
		logger.info("Locator : " + element + " Value : " + visibleValue);
	}

	public void SelectUsingValue(WebElement locator, String value) {
		Select select = new Select(locator);
		select.selectByValue(value);
		logger.info("Locator : " + locator + " Value : " + value);
	}

	public void SelectUsingIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
		logger.info("Locator : " + locator + " Index : " + index);
	}

	public String getSelectedValue(By locator) {
		logger.info(locator);
		return getSelectedValue(getElement(locator));
	}

	public String getSelectedValue(WebElement element) {
		String value = new Select(element).getFirstSelectedOption().getText();
		logger.info("WebELement : " + element + " Value : " + value);
		return value;
	}

	public List<String> getAllDropDownValues(WebElement webElement) {
		Select select = new Select(webElement);
		List<WebElement> elementList = select.getOptions();
		List<String> valueList = new LinkedList<String>();

		for (WebElement element : elementList) {
			logger.info(element.getText());
			valueList.add(element.getText());
		}
		return valueList;
	}

	/*
	 * Use this method for any dropdown which is in the format of UserName dropdown
	 */
	public List<String> getAllDropDownValuesFromList(WebElement webElement) {

		List<String> valueList = new ArrayList<String>();
		List<WebElement> receivedElements = webElement.findElements(By.cssSelector("li a"));
		for (int i = 0; i < receivedElements.size(); i++) {
			valueList.add(receivedElements.get(i).getText());
		}
		return valueList;
	}	
	
}
