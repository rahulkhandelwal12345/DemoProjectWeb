package com.demo.testcases.web;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.demo.actions.web.HomePage;
import com.demo.setup.BaseSelenium;
import com.demo.utilities.WebUtilities;

public class WebAppointmentTest extends BaseSelenium {
	Logger logger = Logger.getLogger(WebLoginTest.class);
	WebUtilities utilities = new WebUtilities();

	@Test(priority = 1)
	public void appointment_for_existing_patient() throws InterruptedException {
		HomePage page = new HomePage(driver);
		page.select_evening_time();
		utilities.implicitWait();
		page.select_a_slot();
		utilities.implicitWait();
		page.enter_mobile_no();
		utilities.implicitWait();
		page.choose_patient();
		utilities.implicitWait();
		page.click_on_Add_Appointment_Button();
		logger.info("Appointment Taken successfully");
	}
	
//	@Test
//	public void 
}