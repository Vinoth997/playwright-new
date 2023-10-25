package play;

import java.nio.file.Paths;
import java.util.Arrays;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.ScreenshotCaret;
import com.microsoft.playwright.Page.ScreenshotOptions;
public class LearnScreenshots {

	public static void main(String[] args) {
		Playwright playwright = Playwright.create();
		Page page = playwright.chromium().launch().newPage();
		page.navigate("https://www.lambdatest.com/selenium-playground/simple-form-demo");
		
		
		//ScreenShot
		ScreenshotOptions screenshotOptions = new ScreenshotOptions();
		page.screenshot(screenshotOptions.setPath(Paths.get("./snaps/example.png")));
		
		//fullpage screenshot
		page.screenshot(screenshotOptions.setFullPage(true).setPath(Paths.get("./snaps/full.jpg")));
		
		//locator screenshot
		Locator bookBtn = page.locator("//button[text()='Book a Demo']");
		bookBtn.screenshot(new Locator.ScreenshotOptions().setPath(Paths.get("./snaps/locator.jpg")));
		
		//region screenshot
		Locator header = page.locator("#header");
		header.screenshot(new Locator.ScreenshotOptions().setPath(Paths.get("./snaps/region.jpg")));
		
		//masking locator
		Locator input = page.locator("input#user-message");
		input.fill("hello hi");
		page.screenshot(screenshotOptions.setPath(Paths.get("./snaps/inputmasked.jpg"))
				.setFullPage(false)
				.setMask(Arrays.asList(input)));
		
		
		//caret show/hide
		input.click();
		page.screenshot(new ScreenshotOptions().setCaret(ScreenshotCaret.HIDE)
				.setPath(Paths.get("./snaps/carethide.jpg")));
		
	
		page.close(); 
		playwright.close();
		

	}

}
