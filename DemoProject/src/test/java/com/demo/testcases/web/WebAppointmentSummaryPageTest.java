package com.demo.testcases.web;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.demo.actions.web.HomePage;
import com.demo.actions.web.SummaryPage;
import com.demo.setup.BaseSelenium;
import com.demo.utilities.WebUtilities;

public class WebAppointmentSummaryPageTest extends BaseSelenium {
	Logger logger = Logger.getLogger(WebLoginTest.class);
	WebUtilities utilities = new WebUtilities();
	
	@Test(priority = 1, enabled = true)
	public void summary_tab() {
		HomePage page = new HomePage(driver);
		page.open_summary_tab();
	}
	
	@Test(priority = 2, enabled = true)
	public void verify_dagnosis() {
		SummaryPage summary =  new SummaryPage(driver);
		summary.verify_diagnosis_in_summary();
	}
	
	@Test(priority = 3, enabled = true)
	public void verify_drug() {
		SummaryPage summary =  new SummaryPage(driver);
		summary.verify_drugs_in_summary();
	}
}