package seleniumFrameworkDesign.stepDefenitions;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import seleniumFrameworkDesign.TestComponenets.BaseTest;
import seleniumFrameworkDesign.pageobjects.CartPage;
import seleniumFrameworkDesign.pageobjects.CheckoutPage;
import seleniumFrameworkDesign.pageobjects.ConfirmationPage;
import seleniumFrameworkDesign.pageobjects.LandingPage;
import seleniumFrameworkDesign.pageobjects.ProductCatalogue;

public class StepDefenition extends BaseTest {

	public LandingPage landingpage;
	public ProductCatalogue productCatalogue;
	public CartPage cartPage;
	public CheckoutPage checkoutPage;
	public ConfirmationPage confirmationPage;

	@Given("I landed on Ecommerce Page")
	public void Launch_Ecommerce_site() throws IOException {
		landingpage = launchApplication();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void user_Login(String username, String password) {
		productCatalogue = landingPage.loginApplication(username, password);
	}

	@When("^I add product (.+) to Cart$")
	public void add_Product_to_cart(String productName) throws InterruptedException {
		productCatalogue.addProductToCart(productName);
	}

	@When("^Checkout (.+) and submit the order$")
	public void checkout(String productName) {
		cartPage = productCatalogue.goToCartPage();
		Assert.assertTrue(cartPage.verifyProductDisplayed(productName));
		checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		confirmationPage = checkoutPage.submitOrder();
	}

	@Then("{string} message is displayed on confirmationPage")
	public void order_created(String string) {
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}

	@Then("{string}  message is displayed")
	public void warning_Message_displayed(String string) {
		Assert.assertEquals(string, landingPage.getErrorMessage());
	}

}
