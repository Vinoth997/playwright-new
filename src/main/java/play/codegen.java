package play;

//codegen  
//mvn exec:java -e -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="codegen https://www.lambdatest.com/"
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Browser.NewContextOptions;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.nio.file.Paths;

import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.RecordVideoSize;

		public class codegen {
		  public static void main(String[] args) {
		    Playwright playwright = Playwright.create();
		      Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
		        .setHeadless(false));
		      BrowserContext context = browser.newContext(
		    		  new NewContextOptions().setRecordVideoDir(Paths.get("videos/"))
		    		  .setRecordVideoSize(new RecordVideoSize(1280, 720))
		    		  );
		      Page page = context.newPage();
		      page.navigate("https://www.lambdatest.com/");
		      Locator header = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Next-Generation Mobile Apps and Cross Browser Testing Cloud"));
		      assertThat(header).isVisible();
		      page.locator("section").filter(new Locator.FilterOptions().setHasText("Next-Generation Mobile Apps and Cross Browser Testing CloudDeliver unparalleled ")).click();
		      page.locator("#home_v").getByText("×").click();
		      page.close();
		      context.close();
		      browser.close();
		      playwright.close();
		      
		    
		  }
		}
	