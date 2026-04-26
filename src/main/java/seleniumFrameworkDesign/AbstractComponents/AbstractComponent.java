package seleniumFrameworkDesign.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import seleniumFrameworkDesign.pageobjects.CartPage;
import seleniumFrameworkDesign.pageobjects.OrdersPage;

public class AbstractComponent {

	WebDriver driver;
	WebDriverWait wait;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[routerlink*='cart']")
	WebElement cartBtn;
	
	@FindBy(css = "[routerlink*='myorders']")
	WebElement orderHeader;

	public void waitForElementToAppear(By findBy) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForElementToAppear(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForElementToDisappear(By findBy) throws InterruptedException {
		//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(findBy)));
		Thread.sleep(Duration.ofSeconds(4));
	}

	public CartPage goToCartPage() {
		cartBtn.click();
		return new CartPage(driver);
	}
	
	public OrdersPage goToOrdersPage() throws InterruptedException {
		Thread.sleep(3000);
		orderHeader.click();
		return new OrdersPage(driver);
	}
}
