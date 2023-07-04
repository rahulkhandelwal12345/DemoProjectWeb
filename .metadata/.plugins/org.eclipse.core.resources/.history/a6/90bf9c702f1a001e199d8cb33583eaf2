package com.demo.testcases.web;

import org.testng.annotations.Test;

import com.demo.actions.web.HomePage;
import com.demo.actions.web.Instructions;
import com.demo.setup.BaseSelenium;

public class WebAppointmentInstructionsPageTest extends BaseSelenium {

	@Test(priority = 1, enabled = true)
	public void instructions_tab() {
		HomePage page = new HomePage(driver);
		page.open_instruction_tab();
	}
	
	@Test(priority = 2, enabled = true)
	public void search_test() throws Exception {
		Instructions instruction = new Instructions(driver);
		instruction.search_test();
		instruction.add_test();
	}
	
	@Test(priority = 3, enabled = true)
	public void search_instruction() throws Exception {
		Instructions instruction = new Instructions(driver);
		instruction.search_instruction();
		instruction.add_instruction();
	}
	
	@Test(priority = 4, enabled = true)
	public void search_procedure() throws Exception {
		Instructions instruction = new Instructions(driver);
		instruction.search_procedure();
		instruction.add_procedure();
	}
	
}
