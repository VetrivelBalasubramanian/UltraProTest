package com.ultrapro.commonUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import com.ultrapro.baseTests.BaseTest;
import com.ultrapro.pageObjects.AddressBookPage;
import com.ultrapro.pageObjects.CartPage;
import com.ultrapro.pageObjects.CheckOutPage;
import com.ultrapro.pageObjects.FooterBlockPage;
import com.ultrapro.pageObjects.HomePage;
import com.ultrapro.pageObjects.LandingPage;
import com.ultrapro.pageObjects.MyOrdersPage;
import com.ultrapro.pageObjects.MyWishListPage;
import com.ultrapro.pageObjects.PaymentPage;
import com.ultrapro.baseTests.BaseObjects;

public class InitPageObjectClasses extends BaseTest implements BaseObjects{

	public InitPageObjectClasses() {
		// implicit super constructor 
	}

	public InitPageObjectClasses(WebDriver driver){
		driver = this.driver;
		PageFactory.initElements(driver,this);
	}

	public LandingPage landingPage() {
		return new 	LandingPage(driver);
	}
	
	public HomePage getHomePage() {
		return new HomePage(driver);
	}
	
	public CartPage getCartPage() {
		return new CartPage(driver);
	}
	
	public FooterBlockPage getFooterBlockPage() {
		return new FooterBlockPage(driver);
	}
	
	public CheckOutPage getCheckOutPage() {
		return new CheckOutPage(driver);
	}
	
	public PaymentPage getPaymentPage() {
		return new PaymentPage(driver);
	}
	
	public MyOrdersPage getMyOrdersPage () {
		return new MyOrdersPage(driver);
	}
	
	public AddressBookPage getAddressBookPage () {
		return new AddressBookPage(driver);
	}
	
	public MyWishListPage getMyWishListPage () {
		return new MyWishListPage(driver);
	}
}
