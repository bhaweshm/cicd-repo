package practise.pageobjetcs;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import practice.abstractComponents.AbstractComponents;


public class CartPage extends AbstractComponents{

	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	By productsBy = By.cssSelector(".mb-3");
	
	public List<WebElement> getCartPorducts(){
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//waitForElementToAppear(productsBy);
		return cartProducts;
	}
	
	public boolean matchCartProducts(String productName) {
		boolean match = getCartPorducts().stream().anyMatch(s->s.getText().equals(productName));
		return match;
		
	}
	//driver.findElement(By.cssSelector(".totalRow button")).click();
	public CheckoutPage goToCheckout() {
		checkoutEle.click();
		CheckoutPage checkoutPageObj = new CheckoutPage(driver);
		return checkoutPageObj;
	}
	
	//List<WebElement> cartItems = driver.findElements(By.cssSelector(""));
	//boolean match = cartItems.stream().anyMatch(s->s.getText().equals(productName));
	//Assert.assertTrue(match);
}
