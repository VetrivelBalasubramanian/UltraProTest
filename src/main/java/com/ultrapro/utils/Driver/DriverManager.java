package com.ultrapro.utils.Driver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.ultrapro.utils.Driver.DriverFactory;
import com.ultrapro.utils.Driver.DriverManager;
import com.ultrapro.baseTests.BaseTest;

public class DriverManager extends BaseTest{

	/*
	 * This simple line does all the mutlithread magic.
	 */
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	
	public static WebDriver getDriver() {
		if (driver.get() == null) {
			// this is need when running tests from IDE
			setWebDriver(DriverFactory.configDriver(null));
		}
		return driver.get();
	}
	
	public static void setWebDriver(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		DriverManager.driver.set(driver);
	}
	
	/**
	 * Returns a string containing current browser name, its version and OS name.
	 * This method is used in the the *WebDriverListeners to change the test name.
	 */
	public static String getBrowserInfo() {
		// we have to cast WebDriver object to RemoteWebDriver here, because the first
		// one does not have a method
		// that would tell you which browser it is driving. (sick!)
		Capabilities cap = ((RemoteWebDriver) DriverManager.getDriver()).getCapabilities();
		String b = cap.getBrowserName();
		String os = cap.getPlatform().toString();
		String v = cap.getVersion();
		return String.format("%s v:%s %s", b, v, os);
	}
}
