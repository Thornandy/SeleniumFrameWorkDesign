package seleniumFrameworkDesign.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrdersPage extends CartPage {
	WebDriver driver;

	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	@FindBy(css = "tr td:nth-child(3)")
	private List<WebElement> productNames;

	public Boolean verifyProductDisplayed(String productName) {
		Boolean match = productNames.stream().anyMatch(cp -> cp.getText().equalsIgnoreCase(productName));
		return match;
	}

	
}
