package practise.pageobjetcs;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import practice.abstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {
	WebDriver driver;
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		//initailization of driver
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}

	//pageFactory
	//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productsBy =By.cssSelector(".mb-3");
	By toastContainer =By.cssSelector("#toast-container");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	
	//actions => getProducts
	public  List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
	}
	
	//product name filteration
	public WebElement getProductByName(String productName) {
		WebElement prod = getProductList().stream().filter(s->
		s.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		return prod;
	}
	
	//add to cart
	public void addProductToCart(String productName) {
		WebElement prod =getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastContainer);
		waitForElementToDisappear(spinner);
		
	}
	
}
