package seleniumFrameworkDesign.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import seleniumFrameworkDesign.TestComponenets.BaseTest;
import seleniumFrameworkDesign.pageobjects.CartPage;
import seleniumFrameworkDesign.pageobjects.CheckoutPage;
import seleniumFrameworkDesign.pageobjects.ConfirmationPage;
import seleniumFrameworkDesign.pageobjects.OrdersPage;
import seleniumFrameworkDesign.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	String productname = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		ProductCatalogue productCatalogue;
		CartPage cartPage;
		CheckoutPage checkoutPage;
		ConfirmationPage confirmationPage;

		productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		productCatalogue.addProductToCart(input.get("product"));
		cartPage = productCatalogue.goToCartPage();
		Assert.assertTrue(cartPage.verifyProductDisplayed(input.get("product")));
		checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

	// To Verify ZARA COAT 3 is displaying in orders page

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() throws InterruptedException {
		ProductCatalogue productCatalogue;
		OrdersPage ordersPage;
		productCatalogue = landingPage.loginApplication("luffymarco@yopmail.com", "Luffy@65");
		ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.verifyProductDisplayed(productname));

	}

	@DataProvider
	public Object[][] getData() throws IOException {
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "luffymarco@yopmail.com");
//		map.put("password", "Luffy@65");
//		map.put("product", "ZARA COAT 3");
//
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "zoro@yopmail.com");
//		map1.put("password", "Zoro@0707");
//		map1.put("product", "ADIDAS ORIGINAL");
		List<HashMap<String, String>> data = getJsonDataMap(System.getProperty("user.dir")
				+ "\\src\\test\\java\\seleniumFrameworkDesign\\data\\PurchaseOrder.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

	
	
}
