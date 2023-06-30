package com.demo.actions.web;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.demo.setup.BaseSelenium;
import com.demo.utilities.WebUtilities;

public class Prescription extends BaseSelenium {
	public WebDriver driver;
	WebUtilities utilities = new WebUtilities();
	
	public Prescription(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//label[@class='item item-input']/input")
	private WebElement searchbox;
	
	@FindBy(xpath = "//*[@class=\"search-result ng-scope\"]/div/a")
	private List<WebElement> search_result_drugs;
	
	//@FindBy(xpath= "//*[@class=\"dropdown flex-100 border-0\"]/a/span")
	@FindBy(xpath = "//div[@ng-repeat=\"drug in vm.search_results\"]/a")
	private List<WebElement> added_drugs_list;
	
	@FindBy(xpath = "//*[@class=\"icon-trash mr-0\"]")
	private WebElement delete_drugs;
	
	//@FindBy(xpath = "delete-block ng-scope")
	@FindBy(xpath = "//*[@id=\"content\"]/div/div[1]/div[1]/div[2]/div[3]/a/i")
	private WebElement delete_all_drugs_icon;
	
	@FindBy(xpath = "//*[@class=\"btn btn-blue\"]")
	private WebElement add_new_drug_btn;
	
	@FindBy(name = "brand_name_0")
	private WebElement new_drug_name;
	
	@FindBy(name = "company_name_0")
	private WebElement new_drug_company_name;
	
	@FindBy(name = "drug_mrp")
	private WebElement new_drug_price;
	
	@FindBy(css = ".md-primary.flex-50.ml-0.ng-scope")
	private List<WebElement> new_drug_mode;
	
	@FindBy(css = ".drug-formulation.ng-scope")
	private List<WebElement> new_drug_formulation;;

	@FindBy(name = "strength_md_0")
	private WebElement new_drug_strength;
	
	@FindBy(id = "select_value_label_321")
	private WebElement new_drug_strength_unit_dropdown_btn;
	
	@FindBy(xpath = "//*[@class=\"md-select-menu-container md-active md-clickable\"]/md-select-menu/md-content/md-option")
	private List<WebElement> drug_strength_unit_drop_options;
	
	@FindBy(xpath = "//*[@class = \"flex-5 layout-column\"]/span/md-checkbox")
	private List<WebElement> drug_frequency_checknboxes;
	
	@FindBy(name = "duration")
	private WebElement drug_duration;
	
	@FindBy(name = "duration_type")
	private WebElement drug_duration_type_dropdown_btn;
	
	@FindBy(xpath = "//*[@name=\"food_relation\"]/md-radio-button")
	private List<WebElement> food_relation_radios;
	
	@FindBy(name = "drug_inst_search")
	private WebElement drug_instruction_searchbox;
	
	@FindBy(xpath = "//*[@class=\"md-autocomplete-suggestions\"]/li")
	private List<WebElement> drug_instruction_search_result;
	
	@FindBy(css = ".check-wrap.font-large")
	private WebElement add_new_instruction_checkbox;
	
	@FindBy(xpath = "//*[@class ='ng-scope layout-align-end-end layout-row']/button")
	private WebElement new_drug_form_next_btn_page1;
	
	@FindBy(xpath = "//*[@layout-align ='space-between center']/button[2]")
	private WebElement new_drug_form_next_btn_page2;

	@FindBy(xpath = "//*[@layout-align ='space-between center']/button[1]")
	private WebElement new_drug_form_previous_btn_page2;
	
	@FindBy(xpath = "//*[@layout-align ='space-between center mt-5']/button[1]")
	private WebElement new_drug_form_previous_btn_page3;

	@FindBy(xpath = "//*[@layout-align ='space-between center mt-5']/button[2]")
	private WebElement new_drug_form_submit_btn_page3;
	
	
	
	public void verify_20_popular_drugs() {
		utilities.implicitWait();
		int count = 0;
		for(WebElement result : search_result_drugs)
		{
			if(result.isDisplayed())
			{
				count++;
			}
		}
		Assert.assertTrue(count==20);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>20 popular drug count: "+count);

	}
	
	public void verify_popular_drugs_delete_icon() {
		Assert.assertTrue(delete_all_drugs_icon.isDisplayed());
	}
	
	public void add_popular_drug() throws IOException {
		Properties pro = BaseSelenium.test_data();
		utilities.implicitWait();
		for(WebElement result : search_result_drugs)
		{
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>POPULAR DRUGS  "+result.getText());
				try {
					if(result.getText().contains("Tab COMBIFLAM (500 mg) "))
					//if(result.getText().contains(pro.getProperty("popular_drug")))
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
						if(result.getText().contains("Tab COMBIFLAM (500 mg) "))
						//if(result.getText().contains(pro.getProperty("popular_drug")))
						{
							 Actions builder = new Actions(driver);
						     builder.moveToElement(result).click(result);
						     builder.perform();
							
						    break;
						}
					}
					catch(org.openqa.selenium.StaleElementReferenceException e)
					{
						if(result.getText().contains("Tab COMBIFLAM (500 mg) "))
						//if(result.getText().contains(pro.getProperty("popular_drug")))
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
	
	public void verify_added_popular_drug() throws IOException {
		Properties pro = BaseSelenium.test_data();
		for(WebElement added_drug : added_drugs_list)
		{
//			Assert.assertTrue(added_drug.getText().contains(pro.getProperty("popular_drug")));
			Assert.assertTrue(added_drug.getText().contains("Tab COMBIFLAM (500 mg) "));

		}
	}
	
	public void verify_added_drug() throws IOException {
		Properties pro = BaseSelenium.test_data();
		for(WebElement added_drug : added_drugs_list)
		{
//			Assert.assertTrue(added_drug.getText().contains(pro.getProperty("searched_drug")));
			Assert.assertTrue(added_drug.getText().contains("Tab NAXDOM 250"));
		}
	}
	
	
	public void verify_delete_icon_for_added_drug() {
		Assert.assertTrue(delete_drugs.isDisplayed());

	}
	
	public void delete_added_drug() {
		utilities.click(delete_drugs);
	}
	
	public void search_drugs() throws IOException {
		Properties pro = BaseSelenium.test_data();
		//utilities.sendkeys(searchbox, pro.getProperty("searched_drug"));
		utilities.sendkeys(searchbox, "Tab NAXDOM 250");
		
	}
	
	public void add_searched_drug() throws IOException {
		Properties pro = BaseSelenium.test_data();
		for(WebElement result : search_result_drugs)
		{
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>SEARCHED DRUGS  "+result.getText());

				try {
					if(result.getText().contains("Tab NAXDOM 250"))
				//	if(result.getText().contains(pro.getProperty("searched_drug")))
					{
						System.out.println(">>>>>>>>>>>>>>>>>>>>DRUGS"+result.getText());

						Actions builder = new Actions(driver);
					    builder.moveToElement(result).click(result);
					    builder.perform();
					    break;
					}
				}
				catch(org.openqa.selenium.StaleElementReferenceException ex)
				{
					if(result.getText().contains("Tab NAXDOM 250"))
					//if(result.getText().contains(pro.getProperty("searched_drug")))
					{
						System.out.println(">>>>>>>>>>>>>>>>>>>>DRUGS"+result.getText());

						Actions builder = new Actions(driver);
					    builder.moveToElement(result).click(result);
					    builder.perform();
					    break;
					}
				}
		}
	
	
	}
	
	public void click_on_add_new_drug_btn() {
		utilities.click(add_new_drug_btn);
		utilities.implicitWait();

	}
	
	public void add_new_drug_details() throws IOException {
		Properties pro = BaseSelenium.test_data();
//		utilities.sendkeys(new_drug_name, pro.getProperty("new_drug_name"));
//		utilities.sendkeys(new_drug_company_name, pro.getProperty("new_drug_company"));
//		utilities.sendkeys(new_drug_price, pro.getProperty("new_drug_price"));
		
		utilities.sendkeys(new_drug_name, "Drug101");
		utilities.sendkeys(new_drug_company_name, "Company");
		utilities.sendkeys(new_drug_price, "200");
		
		for(WebElement drug_mode : new_drug_mode)
		{
			if(drug_mode.getText().equalsIgnoreCase("Oral"))
			//if(drug_mode.getText().equalsIgnoreCase(pro.getProperty("new_drug_mode")))
			{
				utilities.click(drug_mode);
			}
		}
		
		for(WebElement drug_form : new_drug_formulation)
		{
			if(drug_form.getText().equalsIgnoreCase("Tablet"))
			{
				utilities.click(drug_form);
			}
		}
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", new_drug_form_next_btn_page1);
		Actions action = new Actions(driver);
		action.moveToElement(new_drug_form_next_btn_page1).click().perform();
	}
	
	public void add_new_drug_strength() {
		utilities.sendkeys(new_drug_strength, "250");
//		utilities.click(new_drug_strength_unit_dropdown_btn);
//		for(WebElement strength_unit : drug_strength_unit_drop_options)
//		{
//			if(strength_unit.getText().equalsIgnoreCase("GM"))
//			{
//				utilities.click(strength_unit);
//			}
//		}
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", new_drug_form_next_btn_page1);
		Actions action = new Actions(driver);
		action.moveToElement(new_drug_form_next_btn_page2).click().perform();
	}
	
	public void select_new_drug_frequency() {
		for(WebElement drug_frequency : drug_frequency_checknboxes)
		{
			if(drug_frequency.getAttribute("name").equals("frequency_m")||drug_frequency.getAttribute("name").equals("frequency_e")||drug_frequency.getAttribute("name").equals("frequency_a"))
			{
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", drug_frequency);

				//utilities.click(drug_frequency);

			}
		}
	}
	
	public void add_new_drug_duration() {
		utilities.sendkeys(drug_duration, "5");
		utilities.click(drug_duration_type_dropdown_btn);
		Select select = new Select(drug_duration_type_dropdown_btn);
		select.selectByVisibleText("days");
	}
	
	public void select_relation_with_food() {
		for(WebElement food_relation : food_relation_radios)
		{
			if(food_relation.getText().equalsIgnoreCase("After"))
			{
				utilities.click(food_relation);
			}
		}
	}
	
	public void add_drug_instructions() {
		utilities.sendkeys(drug_instruction_searchbox, "take with water");
		for(WebElement instruction : drug_instruction_search_result)
		{
			if(instruction.getText().equalsIgnoreCase("take with water"))
			{
				utilities.click(instruction);
			}
		}
	}
	
	public void click_submit_btn() {
		utilities.click(new_drug_form_submit_btn_page3);
	}
	
	public void verify_created_drug_added() {
		//Properties pro = BaseSelenium.test_data();
		for(WebElement added_drug : added_drugs_list)
		{
//			Assert.assertTrue(added_drug.getText().contains(pro.getProperty("searched_drug")));
			Assert.assertTrue(added_drug.getText().contains("Drug101"));
		}
	}
	
	public void verify_created_drug_appears_in_search() {
		searchbox.clear();
		utilities.sendkeys(searchbox, "Drug101");
		for(WebElement result : search_result_drugs)
		{
				try 
				{
					Assert.assertTrue(result.getText().contains("Drug101"));
						
				}

				catch(org.openqa.selenium.StaleElementReferenceException ex)
				{
					Assert.assertTrue(result.getText().contains("Drug101"));

				}
		}
	}
}