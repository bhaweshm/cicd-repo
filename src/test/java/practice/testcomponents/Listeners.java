package practice.testcomponents;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import practise.resources.ExtentReporterNG;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;


public class Listeners extends BaseTest implements ITestListener{
	    ExtentTest test;
		ExtentReports extent =ExtentReporterNG.getReportObject();
		ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	 @Override
	    public void onTestStart(ITestResult result) {
	        System.out.println("Test Started: " + result.getName());
	       test = extent.createTest(result.getMethod().getMethodName());
	       extentTest.set(test);
	    }

	    @Override
	    public void onTestSuccess(ITestResult result) {
	        System.out.println("Test Passed: " + result.getName());
	        extentTest.get().log(Status.PASS, "Test Passed");
	    }

	    @Override
	    public void onTestFailure(ITestResult result) {
	        System.out.println("Test Failed: " + result.getName());
	        // You could add code here to take a screenshot, for example
	       extentTest.get().fail(result.getThrowable());
	        try {
				driver =(WebDriver) result.getTestClass().getRealClass().getField("driver")
						.get(result.getInstance());
			} catch (Exception e) {
				// TODO Auto-generated catch block
			}
	        String filepath = null;
	       try {
			filepath = getScreenshot(result.getMethod().getMethodName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       extentTest.get().addScreenCaptureFromBase64String(filepath, result.getMethod().getMethodName());
	    }

	    @Override
	    public void onTestSkipped(ITestResult result) {
	       
	    }

	    @Override
	    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	       
	    }

	    @Override
	    public void onStart(ITestContext context) {
	       
	    }

	    @Override
	    public void onFinish(ITestContext context) {
	        System.out.println("Test Suite Finished: " + context.getName());
	        extent.flush();
	    }
}

