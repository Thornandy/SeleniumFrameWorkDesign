package seleniumFrameworkDesign.Tests;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;


import seleniumFrameworkDesign.TestComponenets.BaseTest;
import seleniumFrameworkDesign.TestComponenets.Retry;
import seleniumFrameworkDesign.pageobjects.CartPage;
import seleniumFrameworkDesign.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {
	
	@Test(groups = {"ErrorHandling"},retryAnalyzer= Retry.class)
	public void loginErrorValidation() throws IOException, InterruptedException {
		ProductCatalogue productCatalogue = landingPage.loginApplication("luffymarco@yopmail.com", "wrongpassword");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}
	
	@Test
	public void productErrorValidation() throws IOException, InterruptedException {
		String productname = "ZARA COAT 3";

		ProductCatalogue productCatalogue;
		CartPage cartPage;

		productCatalogue = landingPage.loginApplication("luffymarco@yopmail.com", "Luffy@65");
		productCatalogue.addProductToCart(productname);
		cartPage = productCatalogue.goToCartPage();
		Assert.assertFalse(cartPage.verifyProductDisplayed("ZARA COAT 33"));	
	}
}