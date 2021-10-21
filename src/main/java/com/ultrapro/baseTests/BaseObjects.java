package com.ultrapro.baseTests;

import com.ultrapro.Helpers.AlertHelper;
import com.ultrapro.Helpers.AssertHelper;
import com.ultrapro.Helpers.BrowserHelper;
import com.ultrapro.Helpers.ButtonHelper;
import com.ultrapro.Helpers.CheckBoxAndRadioButtonHelper;
import com.ultrapro.Helpers.DropDownHelper;
import com.ultrapro.Helpers.GeneralHelper;
import com.ultrapro.Helpers.JavascriptHelper;
import com.ultrapro.Helpers.LinkHelper;
import com.ultrapro.Helpers.LoggerHelper;
import com.ultrapro.Helpers.ProgressHelpers;

public interface BaseObjects {
	//WebDriver driver = DriverManager.getDriver();
	AlertHelper alertHelper = new AlertHelper();
	BrowserHelper browserHelper = new BrowserHelper();
	ButtonHelper buttonHelper = new ButtonHelper();
	CheckBoxAndRadioButtonHelper checkBoxAndRadioButtonHelper = new CheckBoxAndRadioButtonHelper();
	DropDownHelper dropDownHelper = new DropDownHelper();
	GeneralHelper generalHelper = new GeneralHelper();
	JavascriptHelper javascriptHelper = new JavascriptHelper();
	LinkHelper linkHelper = new LinkHelper();
	LoggerHelper loggerHelper = new LoggerHelper();
	ProgressHelpers progressHelpers = new ProgressHelpers();
	AssertHelper assertHelper = new AssertHelper();

}
