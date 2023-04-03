package com.Automation.framework.base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.Automation.framework.utility.ScreenRecorderUtil;
import com.Automation.google.GoogleHandling;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class BaseTest {
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	WebDriver driver;
	String fileName = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
	
	String NewFileName = "_Test_Automation_Script";
	@BeforeSuite
	public void setUp() throws Exception {
		ScreenRecorderUtil.startRecord(fileName + NewFileName);
		GoogleHandling.setVideoName(fileName + NewFileName+".avi");
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/TestReport.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		extent.setSystemInfo("OS", "Windows 10");
		extent.setSystemInfo("Host Name", "Sample_Project");
		extent.setSystemInfo("Environment", "QA");

		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setDocumentTitle("Automation Test Report for Sample Project.");
		htmlReporter.config().setReportName("Test Report for QA Environment");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);
	}

	public static String capture(WebDriver driver) throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File Dest = new File("src/../Snapshots/" + System.currentTimeMillis() + ".png");
		String errflpath = Dest.getAbsolutePath();
		FileUtils.copyFile(scrFile, Dest);
		return errflpath;

	}

	@AfterMethod
	public void getResult(ITestResult result) throws IOException {

		driver = DriverInstance.getInstance().getDriver();

		if (result.getStatus() == ITestResult.FAILURE) {

			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " Test case FAILED due to below issues:",
					ExtentColor.RED));
			test.fail(result.getThrowable());
			test.log(Status.FAIL, test.addScreenCaptureFromPath(capture(driver)) + " Test Failed");
		}

		else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
			test.log(Status.PASS, test.addScreenCaptureFromPath(capture(driver)) + " Test Case PASSED");
		} else {
			test.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + " Test Case SKIPPED", ExtentColor.ORANGE));
			test.skip(result.getThrowable());
		}

	}

	@AfterSuite
	public void tearDown() throws Exception {
		extent.flush();
		ScreenRecorderUtil.stopRecord();
	}
}
