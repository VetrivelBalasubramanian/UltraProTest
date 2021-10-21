package com.ultrapro.Helpers;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ultrapro.utils.Driver.DriverManager;

public class CheckBoxAndRadioButtonHelper {
	WebDriver driver = DriverManager.getDriver();
	private Logger logger = LoggerHelper.getLogger(CheckBoxAndRadioButtonHelper.class);

	public void selectCheckBox(By locator) {
		logger.info(locator);
		selectCheckBox(driver.findElement(locator));
	}

	public void unSelectCheckBox(By locator) {
		logger.info(locator);
		unSelectCheckBox(driver.findElement(locator));
	}

	public boolean isIselected(By locator) {
		logger.info(locator);
		return isIselected(driver.findElement(locator));
	}

	public boolean isIselected(WebElement element) {
		boolean flag = element.isSelected();
		logger.info(flag);
		return flag;
	}

	public void selectCheckBox(WebElement element) {
		if (!isIselected(element))
			element.click();
		logger.info(element);
	}

	public void unSelectCheckBox(WebElement element) {
		if (isIselected(element))
			element.click();
		logger.info(element);
	}

	/**
	 * @param element
	 */
	public boolean isSelectedUsingAttribute(WebElement element) {
		boolean status = false;
		try {
			if (element.getAttribute("checked").contains("true")) {
				status = true;
			} else {
				status = false;
			}
		} catch (NoSuchElementException e) {
			// Based on the html tag if the checkbox attribute is not available then
			// presumed that it unchecked
			status = false;
		}
		return status;
	}
}
