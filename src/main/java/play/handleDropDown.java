package play;

import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.SelectOption;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.util.List;
//import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class handleDropDown {
	public static void main(String[] args) {
		Page page = Playwright.create().chromium().launch(
				new LaunchOptions().setHeadless(false)).newPage();
		
		page.navigate("https://www.lambdatest.com/selenium-playground/select-dropdown-demo");
		Locator select1 = page.locator("select#select-demo");
	    
		//select by value
		select1.selectOption("Wednesday");
		assertThat(page.locator("p.selected-value")).containsText("Wednesday");
		
		//select by label
		select1.selectOption(new SelectOption().setValue("Friday"));
		assertThat(page.locator("p.selected-value")).containsText("Friday");
		
		//select by index
		select1.selectOption(new SelectOption().setIndex(2));
		
		//select by multiple
        Locator states = page.locator("select[name='States']");
        states.selectOption(new String[] {"New Jersey","Ohio"});
		
        //count
        Locator options = states.locator("option");
        int count = options.count();
        System.out.println(count);
        
        //print all the text
        List<String> allInnerTexts = options.allInnerTexts();
        allInnerTexts.forEach(option -> System.out.println(option));
		
        //select JQuery DropDown
        page.navigate("https://www.lambdatest.com/selenium-playground/jquery-dropdown-search-demo");
        Locator country = page.locator("span.select2-selection").first();
        country.click();
        Locator list = page.locator("span.select2-results ul li",
        		new Page.LocatorOptions().setHasText("Denmark"));
        list.click();
        
         //Select group
        Locator files = page.locator("select[name='files']");
        files.selectOption("Ruby");
        
        
        page.close();
		
	}

}
