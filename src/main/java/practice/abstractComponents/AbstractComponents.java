package practice.abstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import practise.pageobjetcs.CartPage;
import practise.pageobjetcs.OrderPage;

public class AbstractComponents {
	
	WebDriver driver;
	
	@FindBy(css="button[routerlink *='/cart']")
	WebElement cartheader;

	@FindBy(css ="button[routerlink='/dashboard/myorders']")
	WebElement orderheader;
	
	public AbstractComponents(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	
	public void waitForElementToDisappear(WebElement ele)  {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
//		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	//driver.findElement(By.cssSelector("")).click();
	//go to cart Page
	public CartPage goToCartPage() {
		cartheader.click();
		CartPage cartPageObj = new CartPage(driver);
		return cartPageObj;
	}

	public OrderPage goToOrderPage() {
		orderheader.click();
		OrderPage orderPageObj = new OrderPage(driver);
		return orderPageObj;
	}
}
