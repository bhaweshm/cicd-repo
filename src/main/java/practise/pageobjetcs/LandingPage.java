package practise.pageobjetcs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import practice.abstractComponents.AbstractComponents;



public class LandingPage extends AbstractComponents{

	WebDriver driver;
	public LandingPage(WebDriver driver) {
		super(driver);
		//initailization of driver
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	//WebElement userEmail  =driver.findElement(By.id("userEmail"));
	//WebElement passWord =driver.findElement(By.id("userPassword"));
	//design pattern pagefactory
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement passWord;
	
	@FindBy(id="login")
	WebElement submit;

	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	
	//action method -> no hardcode input 
	public ProductCatalogue  loginApplication(String username , String password) {
		userEmail.sendKeys(username);
		passWord.sendKeys(password);
		submit.click();
		//instead of again creating obejct in test file class .
		//return the object of next catalogue page;
		ProductCatalogue productCatalogueObj = new ProductCatalogue(driver);
		return productCatalogueObj;
	}
	
	//action method for url
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		 return errorMessage.getText();
	}
}


