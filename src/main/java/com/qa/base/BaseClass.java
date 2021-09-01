package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.qa.utils.commonutiles;

public class BaseClass {

	public Properties prop;
	public int STATUS_RESPONSE_CODE_200 = 200;
	public int STATUS_RESPONSE_CODE_201 = 201;
	public int STATUS_RESPONSE_CODE_404 = 404;
	public int STATUS_RESPONSE_CODE_401 = 401;
	public int STATUS_RESPONSE_CODE_500 = 500;
	public WebDriver driver;
	public ExtentReports report;
	public ExtentTest logger;
	commonutiles utils;

	public BaseClass() {

		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(
					("//home//bhagatsinhkher//eclipse-workspace//CongaaTest//src//main//java//com//qa//config//config.properties"));
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	@BeforeSuite
	public void setupSuite() {
		Reporter.log("setting up report", true);
		utils = new commonutiles();
		String reporLocation = "//home//bhagatsinhkher//eclipse-workspace//CongaaTest//src//test//java//Report//extentReport "
				+ utils.GetDateAndTime() + ".html";
		ExtentHtmlReporter extent = new ExtentHtmlReporter(reporLocation);
		report = new ExtentReports();
		report.attachReporter(extent);
		Reporter.log("setting up report is done", true);
	}

	@BeforeClass
	public void OpenBrowser() {
		try {
			Reporter.log("set for Opening browser", true);
			System.setProperty("webdriver.chrome.driver", "//home//bhagatsinhkher//chromedriver_linux64//chromedriver");

			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("http://Automationpractice.com");
			Thread.sleep(4000);
			Reporter.log("Browser open and Url loaded", true);
		} catch (Exception e) {

		}
	}

	@AfterClass

	public void CloseBrowser() {

		driver.close();
		Reporter.log("browser close", true);
	}

	@AfterMethod
	public void tearDownMethod(ITestResult result) throws IOException {
		Reporter.log("Take screenshot and attached to report activities started", true);
		utils = new commonutiles();
		String screenshotpath = utils.capturescreenshot(driver);
		if (result.getStatus() == ITestResult.FAILURE) {

			logger.fail("test fail", MediaEntityBuilder.createScreenCaptureFromPath(screenshotpath).build());
			Reporter.log("Take screenshot and attached to report activities completed for Fail test", true);
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			logger.pass("test pass", MediaEntityBuilder.createScreenCaptureFromPath(screenshotpath).build());
			Reporter.log("Take screenshot and attached to report activities completed for success test", true);
		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.skip("test pass", MediaEntityBuilder.createScreenCaptureFromPath(screenshotpath).build());
			Reporter.log("Take screenshot and attached to report activities completed for skip test", true);
		}

		Reporter.log("report flush activity started", true);
		report.flush();

	}

}
