package com.ultrapro.test.regression;

import org.testng.annotations.Test;

import com.ultrapro.commonUtils.InitPageObjectClasses;

public class LandingPageTest extends InitPageObjectClasses{
	
	@Test(groups = "Regression", testName = "Login", description = "Check the landing page")
	public void SignInTest() {
			
		/* Step-1: Verify current environment sign in */
		landingPage().verifyThePageTitle();
		
		softAssert.assertAll();
	}

}
