package practise.tests;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAlone {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String username = "smita.frnds2010@gmail.com";
		String password ="Qwerty@2424";
		String productName ="ZARA COAT 3";
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		driver.findElement(By.id("userEmail")).sendKeys(username);
		driver.findElement(By.id("userPassword")).sendKeys(password);
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		//wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.cssSelector(".mb-3"))));
		//Find out all the elements
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		//click the emenet based on text
		WebElement prod = products.stream().filter(s->
		s.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		
		//Use of explicit wait for the prodcut get added to cart based on text display
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//ng-animating
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		 
	    	 
		driver.findElement(By.cssSelector("button[routerlink *='/cart']")).click();
		List<WebElement> cartItems = driver.findElements(By.cssSelector(".cartSection h3"));
		boolean match = cartItems.stream().anyMatch(s->s.getText().equals(productName));
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		driver.findElement(By.cssSelector(".user__address input[placeholder='Select Country']")).sendKeys("ind");
		List<WebElement> elemList =driver.findElements(By.cssSelector(" button[class *='list-group-item'] span"));
		WebElement searchItem = elemList.stream().filter(s->s.getText().equalsIgnoreCase("India")).findFirst().orElse(null);
		searchItem.click();
		driver.findElement(By.cssSelector("a[class *='action__submit']")).click();
		
		String confirmMsg  = driver.findElement(By.className("hero-primary")).getText();
		System.out.println(confirmMsg);
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("Thankyou for the order."));
		driver.quit();
	}

}
