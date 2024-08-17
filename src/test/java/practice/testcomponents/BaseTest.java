package practice.testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import practise.pageobjetcs.LandingPage;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingPageObj;
	
	public WebDriver initializeDriver() throws IOException{
		//properties object
		Properties prop = new Properties();
		//u have to load you global data properties file
		//when you load this method automatically parse the properties file and extract all the key value pair
		//from it.but argument its excepting is inputstream
		//to send the file as inputstream we need to create object of inputstream and then as argument send the file
		//FileInputStream => covert your file into inputstream
		FileInputStream inputstream = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\practise\\resources\\GlobalData.properties");
		prop.load(inputstream);
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser") :  prop.getProperty("browser");

//		System.getProperty("browser")!= null;
//		String browserName = prop.getProperty("browser");
		
		//to add headless argument 
		if (browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			if(browserName.contains("headless")) {
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));
		}
		else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		return driver;
		
	}
	
	
	  @Test
	    public String getScreenshot(String testCaseName ) throws IOException {
	    	TakesScreenshot ts = (TakesScreenshot)driver;
	    	File src = ts.getScreenshotAs(OutputType.FILE);
	    	File dest = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
	    	FileUtils.copyFile(src, dest);
	    	return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
	    }
	
	
	//read json and then convert it into String then to HasMap -> store it into list
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		//read json to string
		String jsonContent  = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		
		//Convert String to HashMap =>jackson databind
		// return List => has two argument {map,map1}
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent,  new TypeReference<List<HashMap<String,String>>>(){
		
	});
		
		return data;
		
}
	
	
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {
		 driver  = initializeDriver();
		 landingPageObj = new LandingPage(driver);
		 landingPageObj.goTo();
		 return landingPageObj;
}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.quit();
	}
}



