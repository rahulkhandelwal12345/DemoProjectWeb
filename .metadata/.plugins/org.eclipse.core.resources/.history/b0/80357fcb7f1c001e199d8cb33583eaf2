package com.demo.actions.web;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demo.setup.BaseSelenium;
import com.demo.utilities.ExcelUtils;
import com.demo.utilities.WebUtilities;

public class Instructions extends BaseSelenium {
	public WebDriver driver;
	WebUtilities utilities = new WebUtilities();
	
	public Instructions(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//label[@class='item item-input']/input")
	private WebElement searchbox;
	
	@FindBy(xpath = "//*[@class=\"search-result mb-0 ng-scope\"]/div/a")
	private List<WebElement> search_results;
	
	@FindBy(name = "test_search")
	private WebElement test_searchbox;
	
	@FindBy(xpath = "//div[@ng-click = 'vm.addTestToDirectory(vm.new_test)']")
	private WebElement test_checkbox;
	
	@FindBy(name = "procedure_search")
	private WebElement procedure_searchbox;
	
	@FindBy(xpath = "//div[@ng-click = 'vm.addProcedureToDirectory(vm.new_procedure)']")
	private WebElement procedure_checkbox;
	
	@FindBy(name = "inst_search")
	private WebElement instruction_searchbox;
	
	@FindBy(xpath = "//div[@ng-click = 'vm.addInstToDirectory(vm.new_inst)']")
	private WebElement instruction_checkbox;
	
	public void search_test() throws Exception {
		ExcelUtils  data = new ExcelUtils (System.getProperty("user.dir") + "/src/test/java/com/demo/testdata/web/testdata.xlsx","Test Instructions");
		String test = data.getCellDataasstring(1, 0);
		utilities.sendkeys(searchbox, test);

	}
	
	public void add_test() throws Exception {
		ExcelUtils  data = new ExcelUtils (System.getProperty("user.dir") + "/src/test/java/com/demo/testdata/web/testdata.xlsx","Test Instructions");
		String test = data.getCellDataasstring(1, 0);
		for(WebElement result : search_results)
		{
				try {
					if(result.getText().contains(test))
					{
						 Actions builder = new Actions(driver);
					     builder.moveToElement(result).click(result);
					     builder.perform();
						
					    break;
					}
				}
				catch(org.openqa.selenium.StaleElementReferenceException ex)
				{
					try {
						if(result.getText().contains(test))
						{
							 Actions builder = new Actions(driver);
						     builder.moveToElement(result).click(result);
						     builder.perform();
							
						    break;
						}
					}
					catch(org.openqa.selenium.StaleElementReferenceException e)
					{
						if(result.getText().contains(test))
						{
							 Actions builder = new Actions(driver);
						     builder.moveToElement(result).click(result);
						     builder.perform();
						     
						    break;
						}
					}
				}
		}
	}
	
	public void search_instruction() throws Exception {
		ExcelUtils  data = new ExcelUtils (System.getProperty("user.dir") + "/src/test/java/com/demo/testdata/web/testdata.xlsx","Test Instructions");
		String instruction = data.getCellDataasstring(1, 1);
		searchbox.clear();
		utilities.sendkeys(searchbox, instruction);
	}
	
	public void add_instruction() throws Exception {
		ExcelUtils  data = new ExcelUtils (System.getProperty("user.dir") + "/src/test/java/com/demo/testdata/web/testdata.xlsx","Test Instructions");
		String instruction = data.getCellDataasstring(1, 1);
		for(WebElement result : search_results)
		{
			String backgroundColor = result.getCssValue("background-color");
				try {
					if(result.getText().contains(instruction))
					{
						 Actions builder = new Actions(driver);
					     builder.moveToElement(result).click(result);
					     builder.perform();
						
					    break;
					}
				}
				catch(org.openqa.selenium.StaleElementReferenceException ex)
				{
					try {
						if(result.getText().contains(instruction))
						{
							 Actions builder = new Actions(driver);
						     builder.moveToElement(result).click(result);
						     builder.perform();
							
						    break;
						}
					}
					catch(org.openqa.selenium.StaleElementReferenceException e)
					{
						if(result.getText().contains(instruction))
						{
							 Actions builder = new Actions(driver);
						     builder.moveToElement(result).click(result);
						     builder.perform();
						     
						    break;
						}
					}
				}
		}
	}
	
	public void search_procedure() throws Exception {
		ExcelUtils  data = new ExcelUtils (System.getProperty("user.dir") + "/src/test/java/com/demo/testdata/web/testdata.xlsx","Test Instructions");
		String procedure = data.getCellDataasstring(1, 2);
		searchbox.clear();
		utilities.sendkeys(searchbox, procedure);

	}
	
	public void add_procedure() throws Exception {
		ExcelUtils  data = new ExcelUtils (System.getProperty("user.dir") + "/src/test/java/com/demo/testdata/web/testdata.xlsx","Test Instructions");
		String procedure = data.getCellDataasstring(1, 2);
		for(WebElement result : search_results)
		{
			String backgroundColor = result.getCssValue("background-color");
				try {

					if(result.getText().contains(procedure))
					{
						 Actions builder = new Actions(driver);
					     builder.moveToElement(result).click(result);
					     builder.perform();
						
					    break;
					}
				}
				catch(org.openqa.selenium.StaleElementReferenceException ex)
				{
					try {
						if(result.getText().contains(procedure))
						{
							 Actions builder = new Actions(driver);
						     builder.moveToElement(result).click(result);
						     builder.perform();
							
						    break;
						}
					}
					catch(org.openqa.selenium.StaleElementReferenceException e)
					{
						if(result.getText().contains(procedure))
						{
							 Actions builder = new Actions(driver);
						     builder.moveToElement(result).click(result);
						     builder.perform();
						     
						    break;
						}
					}
				}
		}
	}
	
	public void create_new_test() throws Exception {
		ExcelUtils  data = new ExcelUtils (System.getProperty("user.dir") + "/src/test/java/com/demo/testdata/web/testdata.xlsx","Test Instructions");
		String test = data.getCellDataasstring(1, 3);
		utilities.sendkeys(test_searchbox, test);
		utilities.click(test_checkbox);
	}
	
	public void create_new_instruction() throws Exception {
		ExcelUtils  data = new ExcelUtils (System.getProperty("user.dir") + "/src/test/java/com/demo/testdata/web/testdata.xlsx","Test Instructions");
		String instruction = data.getCellDataasstring(1, 4);
		utilities.sendkeys(instruction_searchbox, instruction);
		utilities.click(instruction_checkbox);
	}
	
	public void create_new_procedure() throws Exception {
		ExcelUtils  data = new ExcelUtils (System.getProperty("user.dir") + "/src/test/java/com/demo/testdata/web/testdata.xlsx","Test Instructions");
		String procedure = data.getCellDataasstring(1, 5);
		utilities.sendkeys(procedure_searchbox, procedure);
		utilities.click(procedure_checkbox);
	}
	
}
