package practise.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import practice.testcomponents.BaseTest;
import practise.pageobjetcs.CartPage;
import practise.pageobjetcs.CheckoutPage;
import practise.pageobjetcs.LandingPage;
import practise.pageobjetcs.OrderPage;
import practise.pageobjetcs.ProductCatalogue;
import practise.pageobjetcs.ThankyouOrderpage;

public class SubmitOrderTest extends BaseTest{
		String productName="ZARA COAT 3";
	    @Test(dataProvider="getData",groups= {"purchase"})
	    public void submitOrder(String email,String password,String productName) throws IOException {
		// TODO Auto-generated method stub
		
		String name ="India";
		String thankyouInput ="Thankyou for the order.";
		ProductCatalogue productCatalogueObj=	landingPageObj.loginApplication(email, password);
		productCatalogueObj.addProductToCart(productName);
		CartPage cartPageObj = productCatalogueObj.goToCartPage();
		
		Boolean match = cartPageObj.matchCartProducts(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPageObj = cartPageObj.goToCheckout();
		checkoutPageObj.searchedItem(name);
		ThankyouOrderpage thankyouOrderpageobj = checkoutPageObj.submitButton();
		String confirmMsg = thankyouOrderpageobj.getConfirmMsg();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase(thankyouInput));
		
	}
	    //through map using dataProvider
	    @Test(dataProvider="fetchData",groups= {"purchaseFetchOrder"})
	    public void submitOrder1(HashMap<String,String> input) throws IOException {
			// TODO Auto-generated method stub
			
			String name ="India";
			String thankyouInput ="Thankyou for the order.";
			ProductCatalogue productCatalogueObj=	landingPageObj.loginApplication(input.get("email"), input.get("password"));
			productCatalogueObj.addProductToCart(input.get("productname"));
			CartPage cartPageObj = productCatalogueObj.goToCartPage();
			Boolean match = cartPageObj.matchCartProducts(input.get("productname"));
			Assert.assertTrue(match);
			CheckoutPage checkoutPageObj = cartPageObj.goToCheckout();
			checkoutPageObj.searchedItem(name);
			ThankyouOrderpage thankyouOrderpageobj = checkoutPageObj.submitButton();
			String confirmMsg = thankyouOrderpageobj.getConfirmMsg();
			Assert.assertTrue(confirmMsg.equalsIgnoreCase(thankyouInput));
			
		}
	    
	    
	  
	    
    @Test(dependsOnMethods = {"submitOrder"})
    public void orderHistoryTest() {
    	ProductCatalogue productCatalogueObj=	landingPageObj.loginApplication("smita.frnds2010@gmail.com", "Qwerty@2424");
    	OrderPage orderPageObj = productCatalogueObj.goToOrderPage();
    	Assert.assertTrue(orderPageObj.verifyOrderDisplay(productName));
    	
    }
    
    @DataProvider
    public Object[][] getData() {
    	return new Object[][]
    			{
    		{"smita.frnds2010@gmail.com","Qwerty@2424","ZARA COAT 3"},
    		{"shetty@gmail.com","Iamking@000","ZARA COAT 3"}
    			};
    }
    
    @DataProvider
    public Object[][] fetchData() throws IOException{
    	List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\practice\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
}
}
 
//@DataProvider
//public Object[][] fetchData(){
//HashMap<String,String> map = new HashMap<String,String>();
//map.put("email", "smita.frnds2010@gmail.com");
//map.put("password","Qwerty@2424");
//map.put("productname", "ZARA COAT 3");
//
//HashMap<String,String> map1 = new HashMap<String,String>();
//map1.put("email", "shetty@gmail.com");
//map1.put("password","Iamking@000");
//map1.put("productname", "ZARA COAT 3");
//return new Object[][] {{map},{map1})
   
    
    
    
    
    
    
    
    
    
    
    
    
   
