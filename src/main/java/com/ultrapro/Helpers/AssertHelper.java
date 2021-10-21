package com.ultrapro.Helpers;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import com.ultrapro.baseTests.BaseTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class AssertHelper extends BaseTest{
	Logger logger = LoggerHelper.getLogger(AssertHelper.class);

	/**
	 * assertElementInList
	 * @param expectedValue
	 * @param actualListOfItems
	 */
	public void assertElementInList(String expectedValue, List<WebElement> actualListOfItems) {
		Set<String> allUsers = new HashSet<String>();
		for (WebElement individualItem : actualListOfItems) {
			allUsers.add(individualItem.getText());
		}
		softAssert.assertTrue(allUsers.contains(expectedValue), "Expected Value : "+ expectedValue + " not found in list : "+ allUsers);
		logger.info("actual value is : " + allUsers);
		logger.info("expected value is : " + expectedValue);
	}

	/**
	 * compareTwoValues
	 * @param actualValue
	 * @param expectedValue
	 */
	public void compareTwoValues(Object actualValue, Object expectedValue) {
		softAssert.assertEquals(actualValue, expectedValue, "Expected value : "+expectedValue +" Actual value : "+ actualValue);
		logger.info("actual value is : " + actualValue);
		logger.info("expected value is : " + expectedValue);
	}

	/**
	 * compareTwoValues - array of objects
	 * @param actualValue
	 * @param expectedValue
	 */
	public void compareTwoValues(Object[] actualValue, Object[] expectedValue) {
		softAssert.assertEquals(actualValue, expectedValue, "Expected value : "+expectedValue +" Actual value : "+ actualValue);
		logger.info("actual value is : " + Arrays.toString(actualValue));
		logger.info("expected value is : " + Arrays.toString(expectedValue));
	}

	/**
	 * assertListOfElementsExistsInPage
	 * @param objectList
	 */
	public void assertListOfElementsExistsInPage(List<WebElement> objectList) {
		for (WebElement individualElement : objectList) {
			softAssert.assertTrue(individualElement.isDisplayed(),"Element to be displayed : "+individualElement);
			logger.info("Element displayed : " + individualElement);
		}
	}

	/**
	 * assertListOfElementsIsNotEmpty
	 * @param objectList
	 */
	public void assertListOfElementsIsNotEmpty(List<WebElement> objectList) {
		for (WebElement item : objectList) {
			softAssert.assertTrue((item.getText() != "" && item.getText() != null), "Found empty for : "+ item);
		}
	}

	/**
	 * assertElementsExistsInPage
	 * @param element
	 */
	public void assertElementsExistsInPage(WebElement element) {
		boolean flag = false;
		try {
			flag = element.isDisplayed();
		} catch (NoSuchElementException e) {
			flag = false;
		}
		softAssert.assertTrue(flag , "Element to be displayed : "+ element);
		logger.info("Element Displayed : " + element);
	}

	/**
	 * assert that two strings are matching
	 * @param expectedText
	 * @param actualText
	 */
	public void assertTextMatching(String expectedText, String actualText) {
		softAssert.assertEquals(actualText, expectedText, "Expected text : "+expectedText +" Actual text : "+ actualText);
		logger.info("actual value is : " + actualText);
		logger.info("expected value is : " + expectedText);
	}

	/**
	 * assert two booleans values
	 * @param expectedValue
	 * @param actualValue
	 */
	public void assertBooleanValues(Boolean expectedValue, Boolean actualValue) {
		softAssert.assertEquals(expectedValue, actualValue, "Expected boolean value : "+expectedValue +" Actual boolean value : "+ actualValue);
		logger.info("actual value is : " + actualValue);
		logger.info("expected value is : " + expectedValue);
	}

	/**
	 * assert element not present in page
	 * @param element
	 */
	public void assertElementsNotExistsInPage(List<WebElement> elementList) {
		softAssert.assertTrue(elementList.size() == 0 , "Element found and size is : "+elementList.size());
		logger.info("Element list size : " + elementList.size());
	}
	
	/**
	 * assertListsAfterSorting
	 * @param actualValue
	 * @param expectedValue
	 */
	public void assertListsAfterSorting(List<String> actualValue, List<String> expectedValue) {
		Collections.sort(actualValue);
		Collections.sort(expectedValue);
		softAssert.assertEquals(actualValue, expectedValue, "Expected value : "+expectedValue +" Actual value : "+ actualValue);
		logger.info("actual value is : " + actualValue);
		logger.info("expected value is : " + expectedValue);
	}
	
	/**
	 * assert two lists are not equal
	 * @param listOne
	 * @param listTwo
	 */
	public void assertListsNotEqual(List<String> listOne, List<String> listTwo) {
		softAssert.assertNotEquals(listOne, listTwo, "List one value : "+listOne +" List two value : "+ listTwo);
		logger.info("List one value : " + listOne);
		logger.info("List two value : " + listTwo);
	}

	/**
	 * assert two lists are equal
	 * @param listOne
	 * @param listTwo
	 */
	public void assertListsEqual(List<String> listOne, List<String> listTwo) {
		softAssert.assertEquals(listOne, listTwo, "List one value : "+listOne +" List two value : "+ listTwo);
		logger.info("List one value : " + listOne);
		logger.info("List two value : " + listTwo);
	}
	
	/**
	 * assertElementInStringList
	 * @param expectedValue
	 * @param actualListOfItems
	 */
	public void assertElementInStringList(String expectedValue, List<String> actualListOfItems) {
		softAssert.assertTrue(actualListOfItems.contains(expectedValue), "Expected Value : "+ expectedValue + " not found in list : "+ actualListOfItems);
		logger.info("actual value is : " + actualListOfItems);
		logger.info("expected value is : " + expectedValue);
	}
	
	/**
	 * Assert Expected Value Not In List
	 * @param expectedValue
	 * @param actualListOfItems
	 */
	public void assertElementNotInList(String expectedValue, List<WebElement> actualListOfItems) {
		Set<String> allUsers = new HashSet<String>();
		for (WebElement individualItem : actualListOfItems) {
			allUsers.add(individualItem.getText());
		}
		softAssert.assertFalse(allUsers.contains(expectedValue), "Expected Value : "+ expectedValue + " found in list : "+ allUsers);
		logger.info("actual value is : " + allUsers);
		logger.info("expected value is : " + expectedValue);
	}
	
	public void assertElementContainsExpectedText(WebElement element, String expectedValue) {
		softAssert.assertTrue(element.getText().contains(expectedValue), "Expected Value : "+ expectedValue + " but found : "+ element.getAttribute("innerText"));	
		logger.info("actual value is : " + element.getAttribute("innerText"));
		logger.info("expected value is : " + expectedValue);
	}
}
