package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	
	public static ExtentReports generateExtentReport() {
		
		ExtentReports extentReport = new ExtentReports();		//extent spark report
		
		File extentReportFile = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
		ExtentSparkReporter sparks = new ExtentSparkReporter(extentReportFile);
		
		sparks.config().setTheme(Theme.DARK);
		sparks.config().setReportName("Tutorials Ninja Test Automation Reports");
		sparks.config().setDocumentTitle("TN Automation Report");
		sparks.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		
		extentReport.attachReporter(sparks);
		
		Properties prop = new Properties();
		File file = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
		
		try {
			
			FileInputStream input = new FileInputStream(file);
			prop.load(input);
		
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
		extentReport.setSystemInfo("Application URL", prop.getProperty("url"));
		extentReport.setSystemInfo("BrowserName", prop.getProperty("browser"));
		extentReport.setSystemInfo("Email", prop.getProperty("validUsername"));
		extentReport.setSystemInfo("Password", prop.getProperty("validPassword"));
		extentReport.setSystemInfo("Operating System", prop.getProperty("os.name"));
		extentReport.setSystemInfo("Username", prop.getProperty("user.name"));
		extentReport.setSystemInfo("Java Version", prop.getProperty("java.version"));
		
		return extentReport;
		
	}

}
