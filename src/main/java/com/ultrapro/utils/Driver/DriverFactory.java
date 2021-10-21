package com.ultrapro.utils.Driver;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.ultrapro.enums.DriverType;

import io.github.bonigarcia.wdm.WebDriverManager;

import com.ultrapro.Helpers.LoggerHelper;
import com.ultrapro.utils.Driver.DriverFactory;
import com.ultrapro.baseTests.BaseTest;

public class DriverFactory extends BaseTest{

	@SuppressWarnings("deprecation")
	public static WebDriver configDriver(String driverName) {
		Logger logger = LoggerHelper.getLogger(DriverFactory.class);
		switch (DriverType.valueOf(driverName.toUpperCase())) {
		
		case CHROME:
			DesiredCapabilities cap = null ;
			ChromeOptions options = null;
			
			WebDriverManager.chromedriver().setup();
			
			try {
				String downloadFilepath = System.getProperty("user.dir")+"//downloads";
				System.out.println(downloadFilepath);
				HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
				chromePrefs.put("profile.default_content_settings.popups", 0);
				chromePrefs.put("download.default_directory", downloadFilepath);
				options = new ChromeOptions();
				options.setExperimentalOption("prefs", chromePrefs);
				cap = DesiredCapabilities.chrome();
				cap.setCapability(ChromeOptions.CAPABILITY, options);
				driver = new ChromeDriver(cap);
			} catch (Exception e) {
				logger.error(e);
			}			
			break;
		default:
			try {
				WebDriverManager.firefoxdriver().setup();
			} catch (Exception e) {
				logger.error(e);
			}
			driver = new FirefoxDriver();
			break;
		}
		
		
		driver.manage().window().maximize();
		return driver;
	}
}
