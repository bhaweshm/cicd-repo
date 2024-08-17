package practise.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import practice.testcomponents.BaseTest;
import practice.testcomponents.Retry;


public class ErrorValidations extends BaseTest{

	@Test(groups= {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void errorValidation() {
	landingPageObj.loginApplication("smita.frnds2010@gmail.com", "Qwerty@2424");
	Assert.assertEquals("Incorrect email or password.", landingPageObj.getErrorMessage());
	}
}
