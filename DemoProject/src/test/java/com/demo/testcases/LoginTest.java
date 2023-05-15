package com.demo.testcases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.demo.pages.LoginPage;
import com.demo.setup.BaseSelenium;
import com.github.dockerjava.api.model.Info;

public class LoginTest extends BaseSelenium {
	Logger logger = Logger.getLogger(LoginTest.class);

	@Test(priority = 1)
	public void login() throws InterruptedException {
		LoginPage page = new LoginPage(driver);
		page.enterValidEmail();
		page.enterValidPassword();
		page.click_login_btn();
		String actual_username = page.verify_user_name().getText();
		logger.info("actual login user name" + " " + actual_username);
		Assert.assertEquals(actual_username, "Jay");
		logger.info("Login test cases executed successfully");
	}

	@Test(priority = 2)
	public void Logout() throws InterruptedException {
		LoginPage page = new LoginPage(driver);
		page.click_arrowicon();
		page.click_logout_btn();
		page.click_conformation_Logout_btn();
		WebElement recent_login = page.recent_login_disply();
		logger.info("Verifying the recent login text is displayed");
		assertTrue(recent_login.isDisplayed(), "Recent login is not displaying on the page");
		logger.info("Logout test cases executed successfully");
	}

	@Test(priority = 3)
	public void validate_error_message() throws InterruptedException {
		LoginPage page = new LoginPage(driver);
		page.enterValidEmail();
		page.enterInvalidPassword();
		page.click_login_btn();
		String actualErrorMessage = page.validateErrorMessage();
		assertEquals(actualErrorMessage, "The password that you've entered is incorrect. Forgotten password?");
	}

}
