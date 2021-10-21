package com.ultrapro.Helpers;

import com.ultrapro.baseTests.BaseTest;
import com.ultrapro.utils.Data.PropertyReader;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class ProgressHelpers extends BaseTest{

	//WebDriver driver = DriverManager.getDriver();
	private Logger logger = LoggerHelper.getLogger(ProgressHelpers.class);
	WebDriverWait webDriverWait = new WebDriverWait(driver, 30);

	/* All types are waits will be written here */

	/**
	 * @param element
	 */
	public void waitForElementToDisplay(WebElement element) {
		webDriverWait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * @param element
	 */
	public void waitForElementToInvisible(WebElement element) {
		webDriverWait.until(ExpectedConditions.invisibilityOf(element));
	}

	/**
	 * @param element
	 * @param timeOutInSeconds
	 */
	public void waitForElementToInvisible(WebElement element, int timeOutInSeconds) {
		WebDriverWait wdWait = new WebDriverWait(driver, timeOutInSeconds);
		wdWait.until(ExpectedConditions.invisibilityOf(element));
	}

	/**
	 * @param element
	 */
	public void waitForElementToBeClickable(WebElement element) {
		webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * @param locator
	 */
	public void waitForPresenceOfElement(By locator) {
		webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	/**
	 * @param timeOutInSeconds
	 * @param pollingEveryInMiliSec
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private WebDriverWait getWait(int timeOutInSeconds, int pollingEveryInMiliSec) {
		logger.debug("");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.pollingEvery(pollingEveryInMiliSec, TimeUnit.MILLISECONDS);
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(NoSuchFrameException.class);
		return wait;
	}

	/**
	 * @param timeout
	 * @param unit
	 */
	public void setImplicitWait(long timeout, TimeUnit unit) {
		logger.info(timeout);
		driver.manage().timeouts().implicitlyWait(timeout, unit == null ? TimeUnit.SECONDS : unit);
	}

	public void waitForElementVisible(By locator, int timeOutInSeconds, int pollingEveryInMiliSec) {
		logger.info(locator);
		setImplicitWait(1, TimeUnit.SECONDS);
		WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMiliSec);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
		setImplicitWait(Long.parseLong(PropertyReader.getConfigProperties("QA", "implicitwait")), TimeUnit.SECONDS);
	}

	/**
	 * @param timeOutInMiliSec
	 */
	public void hardWait(int timeOutInMiliSec) {
		logger.info(timeOutInMiliSec);
		try {
			Thread.sleep(timeOutInMiliSec);
		} catch (InterruptedException e) {
			logger.error(e);
		}
	}

	/**
	 * @param locator
	 * @param retryCount
	 * @param delayInSeconds
	 * @return
	 * @throws InterruptedException
	 */
	public WebElement handleStaleElement(By locator, int retryCount, int delayInSeconds) throws InterruptedException {
		logger.info(locator);
		WebElement element = null;

		while (retryCount >= 0) {
			try {
				element = driver.findElement(locator);
				return element;
			} catch (StaleElementReferenceException e) {
				hardWait(delayInSeconds);
				retryCount--;
			}
		}
		throw new StaleElementReferenceException("Element cannot be recovered");
	}

	/**
	 * @param locator
	 * @param timeOutInSeconds
	 * @param pollingEveryInMiliSec
	 */
	public void elementExits(By locator, int timeOutInSeconds, int pollingEveryInMiliSec) {
		logger.info(locator);
		setImplicitWait(1, TimeUnit.SECONDS);
		WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMiliSec);
		wait.until(elementLocatedBy(locator));
		setImplicitWait(Long.parseLong(PropertyReader.getConfigProperties("QA", "implicitwait")), TimeUnit.SECONDS);
	}

	/**
	 * @param locator
	 * @param timeOutInSeconds
	 * @param pollingEveryInMiliSec
	 */
	public void elementExistAndVisible(By locator, int timeOutInSeconds, int pollingEveryInMiliSec) {
		logger.info(locator);
		setImplicitWait(1, TimeUnit.SECONDS);
		WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMiliSec);
		wait.until(elementLocatedBy(locator));
		new JavascriptHelper().scrollIntoView(locator);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		setImplicitWait(Long.parseLong(PropertyReader.getConfigProperties("QA", "implicitwait")), TimeUnit.SECONDS);

	}

	/**
	 * @param locator
	 * @param timeOutInSeconds
	 * @param pollingEveryInMiliSec
	 */
	public void waitForIframe(By locator, int timeOutInSeconds, int pollingEveryInMiliSec) {
		logger.info(locator);
		setImplicitWait(1, TimeUnit.SECONDS);
		WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMiliSec);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
		driver.switchTo().defaultContent();
		setImplicitWait(Long.parseLong(PropertyReader.getConfigProperties("QA", "implicitwait")), TimeUnit.SECONDS);
	}
	
	/**
	 * @param locator
	 * @param timeOutInSeconds
	 * @param pollingEveryInMiliSec
	 */
	public void waitForIframeAndSwitchToIt(By locator, int timeOutInSeconds, int pollingEveryInMiliSec) {
		logger.info(locator);
		WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMiliSec);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
	}

	/**
	 * @param locator
	 * @return
	 */
	private Function<WebDriver, Boolean> elementLocatedBy(final By locator) {
		return new Function<WebDriver, Boolean>() {

			public Boolean apply(WebDriver driver) {
				logger.debug(locator);
				return driver.findElements(locator).size() >= 1;
			}
		};
	}
	
	/**public void waitForPageLoad() {
		ExpectedCondition<Boolean> pageLoadCondition = driver1 -> ((JavascriptExecutor) driver1)
				.executeScript("return document.readyState").equals("complete");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(pageLoadCondition);
	}*/
	
	public void waitForPageLoad() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	/**
	 * waitForElementAttributeValueToChanged
	 * @param element
	 * @param attribute
	 * @param attributeValue
	 */
	public void waitForElementAttributeValueToChanged(WebElement element, String attribute, String attributeValue) {
		logger.info("Wait for Attribute '"+attribute+ "' value to change to" +attributeValue);
		webDriverWait.until(ExpectedConditions.attributeContains(element, attribute, attributeValue));
	}

}
