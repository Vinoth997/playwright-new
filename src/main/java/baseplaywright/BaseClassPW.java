package baseplaywright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Page.ScreenshotOptions;
import com.microsoft.playwright.Playwright;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.nio.file.Paths;
import java.util.List;
public class BaseClassPW {
	Playwright playwright;
	Page page;
	BrowserContext context;
	Browser browser;
	
	
	//opening Cross browser
	public Page launchBrowser(String browserName,String site) {
		 playwright = Playwright.create();
		 
		 switch (browserName.toLowerCase()) {
		case "chromium":
			browser = playwright.chromium().launch(new LaunchOptions().setHeadless(false));
			break;
		case "firefox":
			browser = playwright.firefox().launch(new LaunchOptions().setHeadless(false));
			break;
		case "safari":
			browser = playwright.webkit().launch(new LaunchOptions().setHeadless(false));
			break;
		case "chrome":
			browser = playwright.chromium().launch(new LaunchOptions()
					.setChannel("chrome")
					.setHeadless(false));
			break;

		default:
			System.out.println("please pass correct browserName....");
			break;
		}
		 
		 context = browser.newContext();
		 page = context.newPage();		
	     page.navigate(site);
		return page;
	}
	
	//input/fill
	public void enterText(Locator locator, String text) {
		locator.fill(text);

	}
	
	
	//btnclick
	public void btnClick(Locator locator) {
		locator.click();
	}
	
	//pagetitle
	public String getPageTitle() {
		return page.title();
	}
	
	//page url
	public String getPageurl() {
		return page.url();
	}
	
	//check title
	public void checkTitle(String text) {
		assertThat(page).hasTitle(text);
	}
	
	public void CheckText(Locator locator, String TextGot) {
		assertThat(locator).hasText(TextGot);
	}

	//closing all
	public void closePage() {
		page.close();
	}
	public void closeBrowser() {
		browser.close();
	}
	public void closePlaywright() {
		playwright.close();
	}
	public void closeAll() {
		page.context().browser().close();
	}
	
	//screenshot fn
	public void screenshot(String path) {
		page.screenshot(new ScreenshotOptions().setPath(Paths.get(path)));
	}
	
	public void screenshotFullPage(String path) {
		page.screenshot(new ScreenshotOptions().setFullPage(true).setPath(Paths.get(path)));
	}
	
	public void screenshotLocator(Locator locator, String path) {
		locator.screenshot(new Locator.ScreenshotOptions().setPath(Paths.get(path)));
	}
     //get text
	public String getText(Locator locator) {
		String text = locator.innerText();
		System.out.println(text);
		return text;

	}
	//gettexts
	public List<String> getTexts(Locator locator) {
	    List<String> elementTexts = locator.allInnerTexts();
	    elementTexts.forEach(text -> System.out.println(text));
	    return elementTexts;
	}
	
	//click equal texts in dropdown
	public Locator clickEqual(Locator locater, String textToMatch) {
		locater.waitFor();
		List<Locator> searchResults = locater.all();
		searchResults.stream()
		             .filter(result -> result.innerText().equals(textToMatch))
		             .findFirst()
		             .map(result -> {btnClick(result);
		             return result;
		            		 });
		return null;
		
//	    for (Locator element : searchResults) {
//	        String elementText = element.innerText();
//	        if (elementText.equals(textToMatch)) {
//	        	
//	            element.click();
//	            return element;
//	        }
//	    }
//	    return null;
	}
}
