package baseClasses;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	static WebDriver driver;
	static ExtentHtmlReporter htmlreporter;
	static ExtentReports reports;
	static ExtentTest test;
	
	public static WebDriver webdriverStarting(String browser) {
		if(driver == null) {
			if (browser.equals("chrome")) {
				WebDriverManager.chromedriver().setup();
			//	System.setProperty("webdriver.chrome.driver","C:\\Users\\SUHAS\\eclipse-workspace\\chromedriver.exe");	
				driver= new ChromeDriver();
			}else if(browser.equals("edge")) {
				WebDriverManager.edgedriver().setup();
			//	System.setProperty("webdriver.edge.driver","C:\\Users\\SUHAS\\eclipse-workspace\\msedgedriver.exe");	
				driver= new EdgeDriver();
			}
		
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
		return driver;
	}
	public static void unloadDriver() {
		driver=null;
	}
	public static ExtentHtmlReporter getExtentReporter() {
		if(htmlreporter==null) {
			htmlreporter = new ExtentHtmlReporter("ExtentReports.html");
		}
		return htmlreporter;
	}
	public static ExtentReports getExtentReports() {
		if(reports==null) {
			reports = new ExtentReports();
			reports.attachReporter(htmlreporter);
		}return reports;
	}
	public static ExtentTest getExtentTest(String testName) {
		test = reports.createTest(testName);
		return test;
	}
}