package com.demo.testcases.web;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.demo.actions.web.Prescription;
import com.demo.actions.web.HomePage;
import com.demo.setup.BaseSelenium;
import com.demo.utilities.WebUtilities;

public class WebAppointmentPrescriptionPageTest extends BaseSelenium {
	Logger logger = Logger.getLogger(WebLoginTest.class);
	WebUtilities utilities = new WebUtilities();
	
	@Test(priority = 1, enabled = true)
	public void prescription_tab() {
		HomePage page = new HomePage(driver);
		page.open_prescription_tab();
	}
	
	@Test(priority = 2, enabled = true)
	public void verify_20_popular_drugs() throws IOException {
		Prescription prescription = new Prescription(driver);
		prescription.verify_20_popular_drugs();
		prescription.verify_popular_drugs_delete_icon();
		prescription.add_popular_drug();
		prescription.verify_added_popular_drug();
		//prescription.delete_added_drug();
	}
	
	@Test(priority = 3, enabled = true)
	public void search_drugs() throws IOException {
		Prescription prescription = new Prescription(driver);
		prescription.search_drugs();
		prescription.add_searched_drug();
		prescription.verify_added_drug();
	}
	
	@Test(priority = 4, enabled = true)
	public void verify_delete_icon_for_added_drugs() {
		Prescription prescription = new Prescription(driver);
		prescription.verify_delete_icon_for_added_drug();
	}

	@Test(priority = 5, enabled = true)
	public void add_new_drug_form_details() throws IOException {
		Prescription prescription = new Prescription(driver);
		prescription.click_on_add_new_drug_btn();
		prescription.add_new_drug_details();
		prescription.add_new_drug_strength();
		prescription.select_new_drug_frequency();
		prescription.add_new_drug_duration();
		prescription.select_relation_with_food();
		prescription.add_drug_instructions();
		prescription.click_submit_btn();
	}
	
	
}
