package amazon.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import baseplaywright.BaseClassPW;

public class HomePage extends BaseClassPW {
	private Page page;
	//private Page productpage;
	private Locator SignIn,
	                email,
	                continueBtn,
	                errorMsg,
	                searchTab,
	                searchBtn,
	                product, pageResult;
	                
	private String addedToCartMsg,
	               addToCart;
	                
	
	//constructor
	public HomePage(Page page) {
		this.page=page;
		//this.productpage=productpage;
		this.SignIn=page.locator("//div[@class='nav-div']//a[@data-nav-role='signin']");
		this.email=page.locator("input[type='email']");
		this.continueBtn=page.locator("input.a-button-input");
		this.errorMsg=page.locator("//div[@id='invalid-email-alert']//div[@class='a-alert-content']");
		this.searchTab=page.locator("#twotabsearchtextbox");
		this.searchBtn=page.locator("input[type='submit']");
		this.product=page.locator("(//span[contains(text(),'OnePlus')]/parent::h2/parent::a)[1]");
		this.pageResult=page.locator("(//div[contains(@class,'a-section a-spacing-small')])[1]");
//		new page(product page)
		addToCart ="//input[@id='add-to-cart-button']";
		addedToCartMsg= "(//div[contains(@id,'CONF_MSG_SUCCESS')]/h1)[1]";
		
	
	}
	
	//private String SignIn = "[data-nav-ref='nav_ya_signin']";
	//private String email = "input[type='email']";
	//private String continueBtn = "input.a-button-input";
	//private String errorMsg = "//h4[text()='There was a problem']";
	
//	private String searchTab ="#twotabsearchtextbox";
//	private String searchBtn ="input[type='submit']";
	//private String SearchList ="//div[@class='s-suggestion-container']";
	
//	private String product="(//span[contains(@class,'a-size-medium a-color-base')])[1]";
//	private String pageResult ="(//div[contains(@class,'a-section a-spacing-small')])[1]";

//	new page(product page)
//	private String addToCart="#add-to-cart-button";
//	private String addedToCartMsg ="//span[text()[normalize-space()='Added to Cart']]";
	
	
	public void signInPage(String text) {
		btnClick(SignIn);
		enterText(email, text);
		btnClick(continueBtn);
		
	}
	public void validate() {
		CheckText(errorMsg, "Invalid email address.");
		

	}
	
	public void searchFunction(String text) {
		enterText(searchTab, text);
		//getTexts(page.locator(SearchList));
	    btnClick(searchBtn);
		//clickEqual(page.locator(SearchList),textToClick );
		
	}
	
	
	
	public void productAddToCart() {
		//btnClick(page.locator(onePlusBrand));
		getText(pageResult);
		
		 Page productpage = page.waitForPopup(()->{
			btnClick(product);	
		});
		
		btnClick(productpage.locator(addToCart));
		CheckText(productpage.locator(addedToCartMsg), "Added to cart");
	}

}
