package practise.pageobjetcs;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import practice.abstractComponents.AbstractComponents;

public class CheckoutPage  extends AbstractComponents{
	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//driver.findElement(By.cssSelector(".user__address input[placeholder='Select Country']")).sendKeys("ind");
	@FindBy(css=".user__address input[placeholder='Select Country']")
	WebElement searchInput;
	
	@FindBy(css="button[class *='list-group-item'] span")
	List<WebElement> suggestedEles;
	
	@FindBy(css="a[class *='action__submit']")
	WebElement submitBtn;
	
	By findBy =By.cssSelector(".infoWrap");
	
	public void searchInput() {
		searchInput.sendKeys("ind");
	}
	//List<WebElement> elemList =driver.findElements(By.cssSelector(" button[class *='list-group-item'] span"));
	public List<WebElement> suggestedElems() {
		searchInput();
		return suggestedEles;
	}
	//WebElement searchItem = elemList.stream().filter(s->s.getText().equalsIgnoreCase("India")).findFirst().orElse(null);
	
	public void searchedItem(String name) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//waitForElementToAppear(findBy);
		System.out.println(suggestedElems().size());
		WebElement searchItem = suggestedElems().stream().filter(s->s.getText().equalsIgnoreCase(name)).findFirst().orElse(null);
		searchItem.click();
	}
	
	public ThankyouOrderpage submitButton() {
		submitBtn.click();
		ThankyouOrderpage thankyouOrderpageobj = new ThankyouOrderpage(driver);
		return thankyouOrderpageobj;
	}
	

}
