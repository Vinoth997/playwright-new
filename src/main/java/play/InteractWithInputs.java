package play;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class InteractWithInputs {
	public static void main(String[] args) {
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(
				new LaunchOptions().setHeadless(false));
		Page page = browser.newPage();
		page.navigate("https://www.lambdatest.com/selenium-playground/simple-form-demo");
		page.locator("input#user-message").fill("hey Tester");// we can either use id="" or # for id
		page.locator("id=showInput").click();
		String message = page.locator("#message").textContent();
		System.out.println(message);
		assertThat(page.locator("#message")).hasText("hey Tester");
		
		page.navigate("https://www.lambdatest.com/selenium-playground/generate-file-to-download-demo");
		page.locator("\"#textbox\"").fill("Ever wished you had an expert 'wingman' to guide you through your dating journey, providing answers and advice in real-time?");
		
//		ElementHandle elementHandle = page.locator("#textbox").elementHandle();
//		elementHandle.press("Shift");
//		elementHandle.press("keyA");
//		elementHandle.
		
		page.navigate("https://letcode.in/edit");
		String inputValue = page.locator("#getMe").inputValue();
		System.out.println(inputValue);
		
		// we can do this to (page.locator("#fullName").getAttribute("placeholder");)
		//but.... always use asserthat and remember to create static import function manually
//		Locator fullnamelocator = page.locator("#fullName");
//		assertThat(fullnamelocator).hasAttribute("placeholder", "Enter first & last name");
//		
//		page.locator("#clearMe").clear();
//		
//		page.navigate("https://www.lambdatest.com/selenium-playground/checkbox-demo");
//		Locator isage = page.locator("#isAgeSelected");
//		assertThat(isage).not().isChecked();
//		isage.check();
//		assertThat(isage).isChecked();
		
		playwright.close();
	}

}
