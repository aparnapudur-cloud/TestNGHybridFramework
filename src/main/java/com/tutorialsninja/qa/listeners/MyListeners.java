package com.tutorialsninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utils.ExtentReporter;
import com.tutorialsninja.qa.utils.Utilities;

public class MyListeners implements ITestListener{		//method overriding
	
	ExtentReports extentReport;
	ExtentTest extentTest;
	String testName;

	@Override
	public void onStart(ITestContext context) {		//Means execution just started
		
		extentReport = ExtentReporter.generateExtentReport();
	}
	
	@Override
	public void onTestStart(ITestResult result) {		//Before each and every test start executing this method will be invoked in the listeners
		
		testName = result.getName();
		extentTest = extentReport.createTest(testName);
		extentTest.log(Status.INFO, testName+"started executing");
	}

	@Override
	public void onTestSuccess(ITestResult result) {		//When any of the test got successfully executed without failing then this method will be invoked

		testName = result.getName();
		extentTest.log(Status.PASS, testName+"Got successfully executed");
	}

	@Override
	public void onTestFailure(ITestResult result) {		//Any of the test method inside this class is executing got failed to due to an exception then this method will be invoked
		
		testName = result.getName();
		extentTest.log(Status.FAIL, testName+"Got failed");
		
		WebDriver driver=null;
		try {
			
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
			
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String destinationScreenshotPath = Utilities.captureScreenshot(driver, result.getName());
		
		extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
		extentTest.log(Status.INFO, result.getThrowable());					//print exception details
		extentTest.log(Status.FAIL, testName+" got failed ");
	}

	@Override
	public void onTestSkipped(ITestResult result) {		//If particular test got skip then this method will invoke
		
		String testName = result.getName();
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, testName+"Got skipped");		//writing logs into console using print statement we are directly writing in report
	}

	@Override
	public void onFinish(ITestContext context) {		//All the test got executed
		
		extentReport.flush();
		
		String pathOfExtentReport = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
		File extentReport = new File(pathOfExtentReport);
		
		try {
			
			Desktop.getDesktop().browse(extentReport.toURI());
			
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}	

	
}
