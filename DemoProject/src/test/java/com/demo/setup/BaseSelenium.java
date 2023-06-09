package com.demo.setup;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.AppiumDriver;

public class BaseSelenium {
	public static WebDriver driver;
	public Properties properties;
	Logger logger = Logger.getLogger(BaseSelenium.class);
	public static AppiumDriver appiumDriver;
	public static DesiredCapabilities caps;
	public static URL url;

	public BaseSelenium() {
		String filepath = System.getProperty("user.dir") + "\\src\\test\\java\\com\\demo\\properties\\demo.properties";
		properties = new Properties();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(filepath);
			properties.load(fis);
			logger.info("Property file loaded successfully");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public DesiredCapabilities getCapabilities() {
		String platformName = properties.getProperty("platformName");
		String deviceName = properties.getProperty("deviceName");
		String appPackage = properties.getProperty("appPackage");
		String appActivity = properties.getProperty("appActivity");

		caps = new DesiredCapabilities();
		caps.setCapability("platformName", platformName);
		caps.setCapability("deviceName", deviceName);
		caps.setCapability("appPackage", appPackage);
		caps.setCapability("appActivity", appActivity);
		return caps;
	}

	public void localMobileRunMode() throws MalformedURLException {
		BaseSelenium base = new BaseSelenium();

		String app_type = properties.getProperty("app_type");
		String platform = properties.getProperty("platform");
		url = new URL(properties.getProperty("url"));
		logger.info("Run Mode type =" + " " + app_type);
		if (app_type.equalsIgnoreCase("mobile")) {
			if (platform.equalsIgnoreCase("Android")) {
				appiumDriver = new AppiumDriver(url, base.getCapabilities());
			}

		}
	}

	@BeforeClass
	public void setup() throws MalformedURLException {
		BaseSelenium base = new BaseSelenium();
		String runMode = properties.getProperty("run_mode");
		String app_type = properties.getProperty("app_type");
		if (runMode.equalsIgnoreCase("local")) {

			if (app_type.equalsIgnoreCase("mobile")) {
				base.localMobileRunMode();

			} else if (app_type.equalsIgnoreCase("Web")) {
				base.localWebRunMode();

			}else {
				throw new IllegalArgumentException("Invalid app type: " + app_type);
		
		} 	}
		
	}

	public static void screenshot() {
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			String destination_path = System.getProperty("user.dir");
			FileUtils.copyFile(screenshot, new File(destination_path + "\\screenshot\\" + "failedscreenshot.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void localWebRunMode() {
		String browser_name = properties.getProperty("browser_name");
		logger.info("Browser name type =" + " " + browser_name);

		if (browser_name.equals("chrome")) {
			System.out.println("Test");
			String cromedriverPath = System.getProperty("user.dir") + "\\driver\\chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", cromedriverPath);
			driver = new ChromeDriver();

			logger.info("Chrome driver executed successfully");
		} else if (browser_name.equals("firefox")) {
			String geckodriverPath = System.getProperty("user.dir") + "\\driver\\geckodriver.exe";
			System.setProperty("webdriver.gecko.driver", geckodriverPath);
			driver = new FirefoxDriver();

			logger.info("Fire fox browseer executed successfully");

		} else {
			throw new IllegalArgumentException("Invalid browser type: " + browser_name);
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(properties.getProperty("app_url"));
		logger.info("Url is opening successfully");

	}

	@AfterClass
	public void tearDown() {

		if (driver != null) {
			driver.quit();
			logger.info("Browser instance closed successfully");
		} else if( appiumDriver != null) {
			appiumDriver.quit();
			logger.info("Application instance closed successfully");
		}
	}

}