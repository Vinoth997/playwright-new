package play;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class HandleAlerts {
	public static void main(String[] args) {
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new LaunchOptions().setHeadless(false));
		BrowserContext context = browser.newContext(new Browser.NewContextOptions()
				  .setHttpCredentials("admin", "admin"));
		Page page=context.newPage();
		page.navigate("https://www.lambdatest.com/selenium-playground/javascript-alert-box-demo");
		//simple accept alert
		page.onceDialog(alert -> {
			System.out.println(alert.message());                      
			alert.accept();
		});
		page.locator("(//button[@type='button'])[1]").click();
		
		//accept/dismiss alert
		page.onceDialog(a ->{
			System.out.println(a.message());
			a.dismiss();
		});
		page.locator("(//button[@type='button'])[2]").click();
		System.out.println(page.locator("#confirm-demo").innerText());
		
		//input type alert(prompt box)
		page.onceDialog(a ->{
			System.out.println(a.message());
			System.out.println(a.defaultValue());
			a.accept("Arun");
			
		});
		page.locator("(//button[@type='button'])[3]").click();
		System.out.println(page.locator("#prompt-demo").innerText());
		
		//digest authentication
		page.navigate("https://the-internet.herokuapp.com/");
		
		page.locator("//a[contains(text(),'Digest Authentication')]").click();
		
		String text = page.locator("p").innerText();
		System.out.println(text);
		page.close();
		playwright.close();
		
	}

}
