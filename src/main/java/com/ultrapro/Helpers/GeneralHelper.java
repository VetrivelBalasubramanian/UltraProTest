package com.ultrapro.Helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.ultrapro.baseTests.BaseTest;
import com.ultrapro.utils.Data.PropertyReader;

public class GeneralHelper extends BaseTest {
	private Logger logger = LoggerHelper.getLogger(GeneralHelper.class);
	ProgressHelpers progressHelpers = new ProgressHelpers();

	/**
	 * @param locator
	 * @return
	 */
	public WebElement getElement(By locator) {
		logger.info(locator);
		if (IsElementPresentQuick(locator))
			return driver.findElement(locator);

		try {
			throw new NoSuchElementException("Element Not Found : " + locator);
		} catch (RuntimeException re) {
			logger.error(re);
			throw re;
		}
	}

	/**
	 * Check for element is present based on locator If the element is present
	 * return the web element otherwise null
	 * 
	 * @param locator
	 * @return WebElement or null
	 */

	public WebElement getElementWithNull(By locator) {
		logger.info(locator);
		try {
			return driver.findElement(locator);
		} catch (NoSuchElementException e) {
			// Ignore
		}
		return null;
	}

	/**
	 * @param locator
	 * @return
	 */
	public boolean IsElementPresentQuick(By locator) {
		boolean flag = driver.findElements(locator).size() >= 1;
		logger.info(flag);
		return flag;
	}

	/**
	 * @param locator
	 * @param text
	 */
	public void enterTextInTextField(WebElement locator, String text) {
		try {
			locator.click();
			locator.clear();
			locator.sendKeys(text);
		} catch (ElementClickInterceptedException e) {
//            Actions actions = new Actions(driver);
			progressHelpers.hardWait(Integer.valueOf(PropertyReader.getConfigProperties("qa2", "hardwait")));
			locator.sendKeys(Keys.ESCAPE);
//            actions.sendKeys(Keys.ESCAPE).perform();
			progressHelpers.hardWait(Integer.valueOf(PropertyReader.getConfigProperties("qa2", "hardwait")));
			progressHelpers.waitForElementToBeClickable(locator);
			locator.click();
			locator.clear();
			locator.sendKeys(text);
		}
	}

	/**
	 * To be done
	 * 
	 * @param actual
	 * @param expected
	 */
	public void assertActualExpected(ArrayList<String> actual, ArrayList<String> expected) {
		Assert.assertEquals(actual, expected);
	}

	/**
	 * @param locator
	 */
	public void clearTextField(WebElement locator) {
		locator.click();
		locator.clear();
	}

	/**
	 * @param element
	 */
	public void moveToElement(WebElement element, WebDriver driver) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.perform();
		actions.build();
	}

	/**
	 * enter value in input box (by typing each char)
	 * 
	 * @param element
	 * @param value
	 */
	public void enterTextInTextFieldCharWise(WebElement element, String value) {

		String stringBuilder;
		char charValue;

		element.clear();
		for (int i = 0; i < value.length(); i++) {
			charValue = value.charAt(i);
			stringBuilder = new StringBuilder().append(charValue).toString();
			element.sendKeys(stringBuilder);
		}
	}
	
	/**
	 * getAttributeValuesFromListOfElement
	 * @param elements
	 * @param attributeValue
	 * @return
	 */
	public List<String> getAttributeValuesFromListOfElements(List<WebElement> elements, String attributeValue) {
		List<String> listOfValues = new ArrayList<String>();
		for (WebElement webElement : elements) {
			listOfValues.add(webElement.getAttribute(attributeValue));
			//System.out.println(listOfValues.toString());
		}
		//System.out.println(listOfValues.toString());
		return listOfValues;
	}
	
	/**
	 * 
	 * @param locator
	 * @return
	 */
	public List<WebElement> getElements(By locator) {
		logger.info("Get elements by locator : "+locator);
		if (IsElementPresentQuick(locator))
			return driver.findElements(locator);
		try {
			throw new NoSuchElementException("Element Not Found : " + locator);
		} catch (RuntimeException re) {
			logger.error(re);
			throw re;
		}
	}
	
	/**
	 * generateRandomNumber
	 * @param min
	 * @param max
	 * @return
	 */
	public int generateRandomNumber(int min, int max) {
		logger.info("Generate random number with range from : "+min +" to "+ max);
		
		int num = (int) ((Math.random()*((max-min)+1))+min);
		
		logger.info("Random number generated is : "+ num);
		
		return num;
	}
	
	/** Method to get the text of Web Element
	 * @param element
	 * @return
	 */
	public String getText(WebElement element) {
		logger.info(element);
		logger.info("Text : " + element.getText());
		return element.getText();
	}
	
	/** Method to get text from List of Web Elements
	 * @param elements
	 * @return
	 */
	public List<String> getText(List<WebElement> elements){
		List<String> textList = new ArrayList<String>();
		for(WebElement ele : elements) {
			textList.add(getText(ele));
		}
		return textList;
	}
	
	/**Method to get the attribute value for the WebElement
	 * @param element
	 * @param attribute
	 * @return
	 */
	public String getAttributeValue(WebElement element, String attribute) {
		logger.info("Element: " + element + ", Attribute: " + attribute + ", Attribute Value: " + element.getAttribute(attribute));
		return element.getAttribute(attribute);
	}
	
	/**
	 * create dynamic xpath and return web-element
	 * @param xpath
	 * @param dynamicValues
	 * @return
	 */
	public WebElement returnElementWithDynamicXpath(String xpath, String dynamicValues) {
		Integer count = 1;
		for (String value : dynamicValues.split(";")) {
			xpath = xpath.replace("{PARAM" + count.toString() + "}", value);
			count++;
		}
		
		return getElement(By.xpath(xpath));
	}	
	
	
	/**
	 * return Pattern Matcher
	 * @param regex
	 * @param matcherText
	 * @return
	 */
	public String returnPatternMatcher(String regex, String matcherText) {
		Pattern pat = Pattern.compile(regex);
		Matcher mat = pat.matcher(matcherText);
		String actualValue = null;
		while(mat.find()) {
			actualValue = mat.group();
		}
		return actualValue;
	}
}
