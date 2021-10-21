package com.ultrapro.baseTests;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import com.ultrapro.utils.Data.PropertyReader;
import com.ultrapro.utils.Driver.DriverFactory;
import com.ultrapro.utils.Driver.DriverManager;
import com.ultrapro.Helpers.LoggerHelper;
import com.ultrapro.baseTests.BaseTest;
import com.ultrapro.utils.Data.ReadJsonData;

public class BaseTest {
	static protected WebDriver driver;
	public static SoftAssert softAssert;
	protected ReadJsonData jsonReader = new ReadJsonData();
	protected Logger logger = LoggerHelper.getLogger(BaseTest.class);
	
	/**
	 * Below method shall get the environment variable from system variable Both
	 * BrowserName and Environment will be passed from command line
	 */
	@BeforeMethod(alwaysRun = true)
	public void startDriver() {
		logger.info("===============================================TEST===============================================");
		softAssert = new SoftAssert();
		driver = DriverFactory.configDriver(System.getProperty("BROWSER"));
		DriverManager.setWebDriver(driver);
		logger.info("Browser is set and Launched");
		driver.navigate().to(PropertyReader.getConfigProperties(System.getProperty("ENV"), "url"));

	}

	@AfterMethod(alwaysRun = true)
	public void closeDriver() {
		logger.info("Browser closed");
		driver.close();
		driver.quit();
	}
}
