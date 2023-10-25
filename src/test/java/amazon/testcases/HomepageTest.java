package amazon.testcases;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.microsoft.playwright.Page;

import amazon.pages.HomePage;
import baseplaywright.BaseClassPW;

public class HomepageTest  {
	HomePage hp;
	Page page;
	BaseClassPW BC;
	@BeforeMethod
	public void frontPage() {
		BC = new BaseClassPW();
		page = BC.launchBrowser("chrome", "https://www.amazon.in/");
		hp = new HomePage(page);
		
		
	}
	
	@Test
	public void TC1() {
		hp.signInPage("Arun");
		hp.validate();
	}
	
	@Test
	public void TC2() {
		hp.searchFunction("oneplus earbuds");
		hp.productAddToCart();
	}

	@AfterTest
	public void close() {
		BC.closeAll();
		BC.closePlaywright();
	}
}
