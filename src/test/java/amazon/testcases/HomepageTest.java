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
		page = BC.launchBrowser("chrome", "https://www.amazon.in/",true);
		hp = new HomePage(page);
	}
	
	@Test
	public void TC1() {
//		hp.searchFunction("oneplus earbuds");
//		hp.productAddToCart();
		System.out.println("Test Case 1");
	}
	
	@Test
	public void TC2() {
//		hp.searchFunction("oneplus earbuds");
//		hp.productAddToCart();
		System.out.println("Test Case 2");
	}

	@AfterTest
	public void close() {
		BC.closeAll();
		BC.closePlaywright();
	}
}
