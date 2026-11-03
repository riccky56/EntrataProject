package testCases;

public class playwrightdemo {

	import com.microsoft.playwright.*;
	import com.microsoft.playwright.options.AriaRole;
	import org.testng.annotations.*;
	import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
	import java.util.regex.Pattern;

	public class SauceDemoTest {
	    Playwright playwright;
	    Browser browser;
	    Page page;

	    @BeforeClass
	    void launch() {
	        playwright = Playwright.create();
	        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
	    }

	    @BeforeMethod
	    void newPage() {
	        page = browser.newContext().newPage();   // fresh context = isolated session
	        page.navigate("https://www.saucedemo.com");
	        
	    }

	    @AfterClass
	    void close() { browser.close(); playwright.close(); }

	    @Test
	    void invalidLoginShowsError() {
	        page.getByPlaceholder("Username").fill("locked_out_user");
	        page.getByPlaceholder("Password").fill("secret_sauce");
	        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();

	        assertThat(page.getByTestId("error")).containsText("locked out");
	    }

	    @Test
	    void endToEndCheckout() {
	        // 1. Login (getByPlaceholder + getByRole)
	        page.getByPlaceholder("Username").fill("standard_user");
	        page.getByPlaceholder("Password").fill("secret_sauce");
	        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();

	        assertThat(page).hasURL(Pattern.compile(".*inventory.html"));
	        assertThat(page.locator(".inventory_item")).hasCount(6);

	        // 2. Chaining + filter: add one specific product
	        page.locator(".inventory_item")
	            .filter(new Locator.FilterOptions().setHasText("Sauce Labs Backpack"))
	            .getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Add to cart"))
	            .click();

	        assertThat(page.getByTestId("shopping-cart-badge")).hasText("1");

	        // 3. Cart
	        page.getByTestId("shopping-cart-link").click();
	        assertThat(page.getByText("Sauce Labs Backpack")).isVisible();
	        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Checkout")).click();

	        // 4. Form (getByPlaceholder)
	        page.getByPlaceholder("First Name").fill("Rahul");
	        page.getByPlaceholder("Last Name").fill("Gupta");
	        page.getByPlaceholder("Zip/Postal Code").fill("500001");
	        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continue")).click();

	        // 5. Finish
	        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Finish")).click();
	        assertThat(page.getByRole(AriaRole.HEADING,
	            new Page.GetByRoleOptions().setName("Thank you for your order!"))).isVisible();
	    }
	}

}
