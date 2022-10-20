package testClasses;


import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import baseClasses.Base;
import pomClasses.Homepage;
import pomClasses.Loginpage;
import utilClasses.Util1;

public class VerifyUserCanLogin {
	WebDriver driver;
	Loginpage lp;
	Homepage hp;

	ExtentHtmlReporter htmlreporter;
	ExtentReports reports;
	ExtentTest test;

	@BeforeClass
	@Parameters("browser")
	public void beforeClass(String browser) {
		htmlreporter = Base.getExtentReporter();
		reports = Base.getExtentReports();
		test = Base.getExtentTest("VerifyUserCanLogin");

		driver = Base.webdriverStarting(browser);
	}

	@BeforeMethod
	public void beforeMethod() {
		lp = new Loginpage(driver);
		hp = new Homepage(driver);
	}

	@Test
	public void userLogin() throws IOException {
		lp.enteremailID();
		
		lp.enterpassword();
		lp.Dologin();
		Util1.xplicitWait(driver, By.xpath("//div[text()='Suhas']"));
		boolean ifprofileNameVisible = hp.visibilityOfProfile();
		Assert.assertTrue(ifprofileNameVisible);
	}

	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.SUCCESS) {
			String path = Util1.getScreenshot(driver, result.getName());
			test.log(Status.PASS, result.getName() + "is passed",
					MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		} else if (result.getStatus() == ITestResult.FAILURE) {
			String path = Util1.getScreenshot(driver, result.getName());
			test.log(Status.FAIL, result.getName() + "is failed",
					MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, result.getName() + "is skipped");
		}

	}

	@AfterClass
	public void afterClass() {
		reports.flush();
		// Base.unloadDriver();
	}
}
