package play;

import java.util.List;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Frame;
import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class frameHandling {

	public static void main(String[] args) {
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new LaunchOptions().setHeadless(false));
		Page page = browser.newPage();
		page.navigate("https://the-internet.herokuapp.com/iframe");
		//handle single frame
		FrameLocator frameLocator = page.frameLocator("#mce_0_ifr");
		Locator bodyLocator = frameLocator.locator("body");
		bodyLocator.click();
		bodyLocator.clear();
		bodyLocator.fill("hey hello");
		
		//handle multiple frame
	 	  page.navigate("https://letcode.in/frame");
		  List<Frame> frames = page.frames();
		  //frame size
		  System.out.println(frames.size());
		  //frame url
		  frames.forEach(frame ->System.out.println(frame.url()));
	
 		  FrameLocator parentFrame = page.frameLocator("#firstFr");
	      parentFrame.getByPlaceholder("Enter name").fill("Arun");
		  FrameLocator childFrame = parentFrame.frameLocator("iframe.has-background-white");
		  childFrame.getByPlaceholder("Enter email").fill("arunsenthil@gmail.com");
        // in playwright we dont need to switch frames we can easily access all the frames separately
		//we dont need to comeback to parentfframe to interact we can use the framelocator already used to do any actions
		page.close();
		playwright.close();
		
	}

}
