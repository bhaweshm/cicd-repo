package practise.pageobjetcs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import practice.abstractComponents.AbstractComponents;

public class ThankyouOrderpage extends AbstractComponents{

	WebDriver driver;
	public ThankyouOrderpage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className="hero-primary")
	WebElement confirmMsg;
	
	public String getConfirmMsg() {
		return confirmMsg.getText();
	}

	
}
