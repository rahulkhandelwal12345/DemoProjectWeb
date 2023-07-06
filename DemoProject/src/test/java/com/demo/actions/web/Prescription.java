package com.demo.actions.web;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.demo.setup.BaseSelenium;
import com.demo.utilities.ExcelUtils;
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
	
	@FindBy(xpath= "//*[@class=\"dropdown flex-100 border-0\"]/a/span")
	//@FindBy(xpath = "//*[@id=\"content\"]/div/div[1]/div[2]/div[2]/section/div[3]/div[1]/div[2]/div/div")
	private List<WebElement> added_drugs_list;
	
	//@FindBy(xpath = "//*[@ng-click=\"vm.deleteDrug(drug, $index)\"]")
	@FindBy(xpath = "//*[@id=\"content\"]/div/div[1]/div[2]/div[2]/section/div[3]/div[1]/div[2]/div[1]/div/div[10]/div/div[2]/div/a/i")
	private WebElement delete_drugs_icon;
	
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
	
	@FindBy(xpath = "/html/body/md-toast")
	private WebElement drug_already_added_msg;
	
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
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", delete_all_drugs_icon);
		Assert.assertTrue(delete_all_drugs_icon.isDisplayed());
	}
	
	public void add_popular_drug() throws Exception {
		ExcelUtils  data = new ExcelUtils (System.getProperty("user.dir") + "/src/test/java/com/demo/testdata/web/testdata.xlsx","Prescription");
		String popularDrug = data.getCellDataasstring(1, 0);
		utilities.implicitWait();
		for(WebElement result : search_result_drugs)
		{
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>POPULAR DRUGS  "+result.getText());
				try {
					if(result.getText().contains(popularDrug))
					{
						Actions builder = new Actions(driver);
					    builder.moveToElement(result).click(result);
					    builder.perform();
						Assert.assertTrue(result.getText().contains(popularDrug));
					    break;
					}
				}
				catch(org.openqa.selenium.StaleElementReferenceException ex)
				{
					try {
						if(result.getText().contains(popularDrug))
						{
							Actions builder = new Actions(driver);
						    builder.moveToElement(result).click(result);
						    builder.perform();
							Assert.assertTrue(result.getText().contains(popularDrug));
						    break;
						}
					}
					catch(org.openqa.selenium.StaleElementReferenceException e)
					{
						if(result.getText().contains(popularDrug))
						{
							Actions builder = new Actions(driver);
						    builder.moveToElement(result).click(result);
						    builder.perform();
							Assert.assertTrue(result.getText().contains(popularDrug)); 
						    break;
						}
					}
				}
		}
	}
	
	public void verify_added_popular_drug() throws Exception {
		ExcelUtils  data = new ExcelUtils (System.getProperty("user.dir") + "/src/test/java/com/demo/testdata/web/testdata.xlsx","Prescription");
		String popularDrug = data.getCellDataasstring(1, 0);
		for(WebElement added_drug : added_drugs_list)
		{
			Assert.assertTrue(added_drug.getText().contains(popularDrug));

		}
	}
	
	public void verify_added_drug() throws Exception {
		ExcelUtils  data = new ExcelUtils (System.getProperty("user.dir") + "/src/test/java/com/demo/testdata/web/testdata.xlsx","Prescription");
		String searchedDrug = data.getCellDataasstring(1, 1);
		for(WebElement added_drug : added_drugs_list)
		{
			Assert.assertTrue(added_drug.getText().contains(searchedDrug));
		}
	}
	
	
	public void verify_delete_icon_for_added_drug() {
		Assert.assertTrue(delete_drugs_icon.isDisplayed());

	}
	
	public void delete_added_drug() {
		utilities.click(delete_drugs_icon);
	}
	
	public void delete_all_drugs() {
		utilities.click(delete_all_drugs_icon);
	}
	
	public void search_drugs() throws Exception {
		ExcelUtils  data = new ExcelUtils (System.getProperty("user.dir") + "/src/test/java/com/demo/testdata/web/testdata.xlsx","Prescription");
		String searchedDrug = data.getCellDataasstring(1, 1);
		utilities.implicitWait();
		utilities.sendkeys(searchbox, searchedDrug);
		Assert.assertTrue(searchbox.getText().equals(searchedDrug));
		
	}
	
	public void add_searched_drug() throws Exception {
		ExcelUtils  data = new ExcelUtils (System.getProperty("user.dir") + "/src/test/java/com/demo/testdata/web/testdata.xlsx","Prescription");
		String searchedDrug = data.getCellDataasstring(1, 1);
		for(WebElement result : search_result_drugs)
		{
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>SEARCHED DRUGS  "+result.getText());

				try {
					if(result.getText().contains(searchedDrug))
					{
						System.out.println(">>>>>>>>>>>>>>>>>>>>DRUGS"+result.getText());
						Assert.assertTrue(result.getText().contains(searchedDrug));
						Actions builder = new Actions(driver);
					    builder.moveToElement(result).click(result);
					    builder.perform();
					    break;
					}
				}
				catch(org.openqa.selenium.StaleElementReferenceException ex)
				{
					try {
						if(result.getText().contains(searchedDrug))
						{
							System.out.println(">>>>>>>>>>>>>>>>>>>>DRUGS"+result.getText());
							Assert.assertTrue(result.getText().contains(searchedDrug));
							Actions builder = new Actions(driver);
						    builder.moveToElement(result).click(result);
						    builder.perform();
						    break;
						}
					}
					catch(org.openqa.selenium.StaleElementReferenceException e)
					{
						if(result.getText().contains(searchedDrug))
						{
							System.out.println(">>>>>>>>>>>>>>>>>>>>DRUGS"+result.getText());
							Assert.assertTrue(result.getText().contains(searchedDrug));
							Actions builder = new Actions(driver);
						    builder.moveToElement(result).click(result);
						    builder.perform();
						    break;
						}
					}
				}
		}
	
	
	}
	
	public void click_on_add_new_drug_btn() {
		utilities.click(add_new_drug_btn);
		utilities.implicitWait();

	}
	
	public void create_new_drug_details() throws Exception {
		ExcelUtils  data = new ExcelUtils (System.getProperty("user.dir") + "/src/test/java/com/demo/testdata/web/testdata.xlsx","Prescription");
		String newdrugName = data.getCellDataasstring(1, 2);
		String newdrugCompany = data.getCellDataasstring(1, 3);
		String newdrugPrice = data.getCellDataasstring(1, 4);
		String newdrugMode = data.getCellDataasstring(1, 5);
		String newdrugFormulation = data.getCellDataasstring(1, 6);
		
		utilities.sendkeys(new_drug_name, newdrugName);
		utilities.sendkeys(new_drug_company_name, newdrugCompany);
		utilities.sendkeys(new_drug_price, newdrugPrice);
		
		for(WebElement drug_mode : new_drug_mode)
		{
			if(drug_mode.getText().equalsIgnoreCase(newdrugMode))
			{
				utilities.click(drug_mode);
			}
		}
		
		for(WebElement drug_form : new_drug_formulation)
		{
			if(drug_form.getText().equalsIgnoreCase(newdrugFormulation))
			{
				utilities.click(drug_form);
			}
		}
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", new_drug_form_next_btn_page1);
		Actions action = new Actions(driver);
		action.moveToElement(new_drug_form_next_btn_page1).click().perform();
	}
	
	public void create_new_drug_strength() throws Exception {
		ExcelUtils  data = new ExcelUtils (System.getProperty("user.dir") + "/src/test/java/com/demo/testdata/web/testdata.xlsx","Prescription");
		String newdrugStrength = data.getCellDataasstring(1, 7);
		utilities.sendkeys(new_drug_strength, newdrugStrength );
		
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
	
	public void create_new_drug_frequency() {
		for(WebElement drug_frequency : drug_frequency_checknboxes)
		{
			if(drug_frequency.getAttribute("name").equals("frequency_m")||drug_frequency.getAttribute("name").equals("frequency_e")||drug_frequency.getAttribute("name").equals("frequency_a"))
			{
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", drug_frequency);

			}
		}
	}
	
	public void create_new_drug_duration() throws Exception {
		ExcelUtils  data = new ExcelUtils (System.getProperty("user.dir") + "/src/test/java/com/demo/testdata/web/testdata.xlsx","Prescription");
		String newdrugDuration = data.getCellDataasstring(1, 8);
		String newdrugDurationype = data.getCellDataasstring(1, 9);

		utilities.sendkeys(drug_duration, newdrugDuration);
		utilities.click(drug_duration_type_dropdown_btn);
		Select select = new Select(drug_duration_type_dropdown_btn);
		select.selectByVisibleText(newdrugDurationype);
	}
	
	public void craete_new_drug_relation_with_food() throws Exception {
		ExcelUtils  data = new ExcelUtils (System.getProperty("user.dir") + "/src/test/java/com/demo/testdata/web/testdata.xlsx","Prescription");
		String foodRelation = data.getCellDataasstring(1, 10);
		for(WebElement food_relation : food_relation_radios)
		{
			if(food_relation.getText().equalsIgnoreCase(foodRelation))
			{
				utilities.click(food_relation);
			}
		}
	}
	
	public void create_drug_instructions() throws Exception {
		ExcelUtils  data = new ExcelUtils (System.getProperty("user.dir") + "/src/test/java/com/demo/testdata/web/testdata.xlsx","Prescription");
		String newDrugInstruction = data.getCellDataasstring(1, 11);
		utilities.sendkeys(drug_instruction_searchbox, newDrugInstruction);
		for(WebElement instruction : drug_instruction_search_result)
		{
			if(instruction.getText().equalsIgnoreCase(newDrugInstruction))
			{
				utilities.click(instruction);
			}
		}
	}
	
	public void click_submit_btn() {
		utilities.click(new_drug_form_submit_btn_page3);
	}
	
	public void verify_created_drug_added() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		for(WebElement added_drug : added_drugs_list)
		{
			synchronized (added_drug) {
	            try {
	            	added_drug.wait(5000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
			System.out.println(">>>>>>>>DRuG NAME ADDED GET TEXT: "+added_drug.getText());
			ExcelUtils  data = new ExcelUtils (System.getProperty("user.dir") + "/src/test/java/com/demo/testdata/web/testdata.xlsx","Prescription");
			String newdrugName = data.getCellDataasstring(1, 2);
			Assert.assertTrue(added_drug.getText().contains(newdrugName));
		}
	}
	
	public void verify_created_drug_appears_in_search() throws Exception {
		searchbox.clear();
		ExcelUtils  data = new ExcelUtils (System.getProperty("user.dir") + "/src/test/java/com/demo/testdata/web/testdata.xlsx","Prescription");
		String newdrugName = data.getCellDataasstring(1, 2);
		utilities.sendkeys(searchbox, newdrugName);
		for(WebElement result : search_result_drugs)
		{
				try 
				{
					Assert.assertTrue(result.getText().contains(newdrugName));
						
				}

				catch(org.openqa.selenium.StaleElementReferenceException ex)
				{
					Assert.assertTrue(result.getText().contains(newdrugName));

				}
		}
	}
	
	public void verify_same_drug_cannot_be_added_twice() throws Exception {
		ExcelUtils  data = new ExcelUtils (System.getProperty("user.dir") + "/src/test/java/com/demo/testdata/web/testdata.xlsx","Prescription");
		String newdrugName = data.getCellDataasstring(1, 2);
		utilities.sendkeys(searchbox, newdrugName);
		for(WebElement result : search_result_drugs)
		{
				try {
					if(result.getText().equals(newdrugName))
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
						if(result.getText().equals(newdrugName))
						{
							Actions builder = new Actions(driver);
						    builder.moveToElement(result).click(result);
						    builder.perform();
						    break;
						}
					}
					catch(org.openqa.selenium.StaleElementReferenceException e)
					{
						if(result.getText().equals(newdrugName))
						{
							Actions builder = new Actions(driver);
						    builder.moveToElement(result).click(result);
						    builder.perform();
						    break;
						}
					}
				}
		}
		
		Assert.assertTrue(drug_already_added_msg.getText().contains("Drug already added"));
	}
}
