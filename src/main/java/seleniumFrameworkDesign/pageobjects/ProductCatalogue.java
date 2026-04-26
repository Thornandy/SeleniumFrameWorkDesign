package seleniumFrameworkDesign.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumFrameworkDesign.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> products;

	By productBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	By spinner = By.cssSelector(".ng-animating");

	public List<WebElement> getProductList() throws InterruptedException {
		Thread.sleep(3000);
		waitForElementToAppear(productBy);
		return products;
	}

	public WebElement getProductByName(String productName) throws InterruptedException {
		WebElement product = getProductList().stream()
				.filter(p -> p.findElement(By.tagName("b")).getText().equals(productName)).findFirst().orElse(null);
		return product;
	}

	public void addProductToCart(String productName) throws InterruptedException {
		WebElement product = getProductByName(productName);
		product.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
	}

}
