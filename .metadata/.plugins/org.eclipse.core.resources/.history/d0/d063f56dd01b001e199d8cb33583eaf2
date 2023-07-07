package com.demo.utilities;

import java.time.Duration;

import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.demo.setup.BaseSelenium;
import com.github.dockerjava.api.command.PullImageCmd;

public class WebUtilities extends BaseSelenium{
	public void sendkeys(WebElement element, String value) {
		element.sendKeys(value);

	}

	public void click(WebElement element) {
		element.click();
	}

	public String getText(WebElement element) {
		return element.getText();
	}

	public static ExtentReports getExtentReport() {
		String path = System.getProperty("user.dir") + "//reports//index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Automation Results");
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Rahul Khandelwal");
		return extent;
	}
	
	public void implicitWait() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}

}
