package com.demo.actions.web;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.demo.setup.BaseSelenium;
import com.demo.utilities.ExcelUtils;
import com.demo.utilities.WebUtilities;

public class CFDPage extends BaseSelenium{
	public WebDriver driver;
	WebUtilities utilities = new WebUtilities();
	
	public CFDPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(xpath = "//label[@class='item item-input']/input")
	private WebElement searchbox;

	//@FindBy(xpath = "//a[@ng-class='vm.addClass(result)']")
	@FindBy(xpath = "//div[@ng-repeat=\"result in vm.search_results\"]/a")
	private List<WebElement> Search_Result_cfd;

	@FindBy(css = ".nav.nav-tabs>li")
	private List<WebElement> tabs;

	@FindBy(css = ".field-wrap.field-name.ng-binding")
	private List<WebElement> added_cfd_list;

	@FindBy(xpath = "//*[@class= 'flex-15 layout-row field-wrap']/a[3]")
	private WebElement delete_diagnosis;

	@FindBy(xpath = "//*[@class= 'flex-30 field-wrap']/a[3]")
	private WebElement delete_complaint;

	@FindBy(xpath = "//*[@class= 'flex-15']/a[3]")
	private WebElement delete_finding;

	@FindBy(css = "md-toast > div > span")
	private WebElement deleted_cfd_msg;
	
	public void verify_popular_diagnosis() {
		utilities.implicitWait();
		int count = 0;
		for (WebElement result : Search_Result_cfd) {
			utilities.fluent_wait(result);
			if (result.isDisplayed()) {
				count++;
			}
		}

		Assert.assertTrue(count == 20);
	}

	public void add_popular_diagnosis() throws Exception {
		ExcelUtils  data = new ExcelUtils (System.getProperty("user.dir") + "/src/test/java/com/demo/testdata/web/testdata.xlsx","CFD");
		String popularDiagnosis = data.getCellDataasstring(1, 3);
		for (WebElement result : Search_Result_cfd) {
			utilities.explicitwait(result);
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>POPULAR DIAGNOSIS: "+result.getText());
			try {

				if (result.getText().contains(popularDiagnosis)) {
					Actions builder = new Actions(driver);
					builder.moveToElement(result).click(result);
					builder.perform();
					Assert.assertTrue(result.getText().contains(popularDiagnosis));
					break;
				}
			} catch (org.openqa.selenium.StaleElementReferenceException ex) {
				try {
					if (result.getText().contains(popularDiagnosis)) {
						Actions builder = new Actions(driver);
						builder.moveToElement(result).click(result);
						builder.perform();
						Assert.assertTrue(result.getText().contains(popularDiagnosis));
						break;
					}
				} catch (org.openqa.selenium.StaleElementReferenceException e) {
					if (result.getText().contains("popularDiagnosis")) {
						Actions builder = new Actions(driver);
						builder.moveToElement(result).click(result);
						builder.perform();
						Assert.assertTrue(result.getText().contains(popularDiagnosis));
						break;
					}
				}
			}
		}
	}

	public void search_diagnosis() throws Exception {
		ExcelUtils  data = new ExcelUtils (System.getProperty("user.dir") + "/src/test/java/com/demo/testdata/web/testdata.xlsx","CFD");
		String searchedDiagnosis = data.getCellDataasstring(1, 2);
		utilities.implicitWait();
		utilities.sendkeys(searchbox, searchedDiagnosis);
		utilities.fluent_wait(Search_Result_cfd);

			try {
				for (WebElement result : Search_Result_cfd) {
				utilities.fluent_wait(result);
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>SEARCHED DIAGNOSIS  "+result.getText());
				if (result.getText().equalsIgnoreCase(searchedDiagnosis)) {
					Actions builder = new Actions(driver);
					builder.moveToElement(result).click(result);
					builder.perform();
					Assert.assertTrue(result.getText().equalsIgnoreCase(searchedDiagnosis));
					break;
				}
				}
			} catch (org.openqa.selenium.StaleElementReferenceException ex) {
				try {
					for (WebElement result : Search_Result_cfd) {
						System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>SEARCHED DIAGNOSIS  "+result.getText());
					if (result.getText().equalsIgnoreCase(searchedDiagnosis)) {
						Actions builder = new Actions(driver);
						builder.moveToElement(result).click(result);
						builder.perform();
						Assert.assertTrue(result.getText().equalsIgnoreCase(searchedDiagnosis));
						break;
					}
					}
				} catch (org.openqa.selenium.StaleElementReferenceException e) {
					for (WebElement result : Search_Result_cfd) {
						System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>SEARCHED DIAGNOSIS  "+result.getText());
					if (result.getText().equalsIgnoreCase(searchedDiagnosis)) {
						Actions builder = new Actions(driver);
						builder.moveToElement(result).click(result);
						builder.perform();
						Assert.assertTrue(result.getText().equalsIgnoreCase(searchedDiagnosis));
						break;
					}
					}
				}
			}
		
	}

	public void search_complaint() throws Exception {
		ExcelUtils  data = new ExcelUtils (System.getProperty("user.dir") + "/src/test/java/com/demo/testdata/web/testdata.xlsx","CFD");
		String searchedComplaint = data.getCellDataasstring(1, 0);
		utilities.implicitWait();
		searchbox.clear();
		utilities.sendkeys(searchbox, searchedComplaint);
		utilities.implicitWait();
		utilities.fluent_wait(Search_Result_cfd);

		for (WebElement result : Search_Result_cfd) {
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>SEARCHED COMPLAINT  "+result.getText());
			utilities.fluent_wait(result);
			try {
				if (result.getText().equalsIgnoreCase(searchedComplaint)) {
					Actions builder = new Actions(driver);
					builder.moveToElement(result).click(result);
					builder.perform();
					Assert.assertTrue(result.getText().equalsIgnoreCase(searchedComplaint));
					break;
				}
			} catch (org.openqa.selenium.StaleElementReferenceException ex) {
				try {
					if (result.getText().equalsIgnoreCase(searchedComplaint)) {
						Actions builder = new Actions(driver);
						builder.moveToElement(result).click(result);
						builder.perform();
						Assert.assertTrue(result.getText().equalsIgnoreCase(searchedComplaint));
						break;
					}
				} catch (org.openqa.selenium.StaleElementReferenceException e) {
					if (result.getText().equalsIgnoreCase(searchedComplaint)) {
						Actions builder = new Actions(driver);
						builder.moveToElement(result).click(result);
						builder.perform();
						Assert.assertTrue(result.getText().equalsIgnoreCase(searchedComplaint));
						break;
					}
				}
			}
		}
	}

	public void search_finding() throws Exception {
		ExcelUtils  data = new ExcelUtils (System.getProperty("user.dir") + "/src/test/java/com/demo/testdata/web/testdata.xlsx","CFD");
		String searchedFinding = data.getCellDataasstring(1, 1);
		utilities.implicitWait();
		searchbox.clear();
		utilities.sendkeys(searchbox, "OTHER FINDINGS");
		utilities.implicitWait();
		//utilities.explicitwait(Search_Result_cfd);
		utilities.fluent_wait(Search_Result_cfd);

		for (WebElement result : Search_Result_cfd) {
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>SEARCHED FINDINGS:  "+result.getText());
			utilities.fluent_wait(result);
			try {
				if (result.getText().equalsIgnoreCase(searchedFinding) ){
					Actions builder = new Actions(driver);
					builder.moveToElement(result).click(result);
					builder.perform();
					Assert.assertTrue(result.getText().equalsIgnoreCase(searchedFinding));
					break;
				}
			} catch (org.openqa.selenium.StaleElementReferenceException ex) {
				try {
					if (result.getText().equalsIgnoreCase(searchedFinding) ){
						Actions builder = new Actions(driver);
						builder.moveToElement(result).click(result);
						builder.perform();
						Assert.assertTrue(result.getText().equalsIgnoreCase(searchedFinding));
						break;
					}
				} catch (org.openqa.selenium.StaleElementReferenceException e) {
					if (result.getText().equalsIgnoreCase(searchedFinding)) {
						Actions builder = new Actions(driver);
						builder.moveToElement(result).click(result);
						builder.perform();
						Assert.assertTrue(result.getText().equalsIgnoreCase(searchedFinding));
						break;
					}
				}
			}
		}

	}

	public void verify_added_cfd() throws Exception {
		ExcelUtils  data = new ExcelUtils (System.getProperty("user.dir") + "/src/test/java/com/demo/testdata/web/testdata.xlsx","CFD");
		String popularDiagnosis = data.getCellDataasstring(1, 3);
		String searchedDiagnosis = data.getCellDataasstring(1, 2);
		String searchedComplaint = data.getCellDataasstring(1, 0);
		String searchedFinding = data.getCellDataasstring(1, 1);
		
		for (WebElement added_cfd : added_cfd_list) {
			utilities.fluent_wait(added_cfd);
			Assert.assertTrue(added_cfd.getText().equalsIgnoreCase(popularDiagnosis)||added_cfd.getText().equalsIgnoreCase(searchedDiagnosis) || added_cfd.getText().equalsIgnoreCase(searchedComplaint)|| added_cfd.getText().equalsIgnoreCase(searchedFinding));

		}
	}

	public void delete_added_diagnosis() {
		utilities.click(delete_diagnosis);
		Assert.assertTrue(deleted_cfd_msg.getText().contains("deleted successfully"));
	}

	public void delete_added_complaint() {
		Actions builder = new Actions(driver);
		builder.moveToElement(delete_complaint).click(delete_complaint).perform();
		Assert.assertTrue(deleted_cfd_msg.getText().contains("deleted successfully"));
	}

	public void delete_added_finding() {
		utilities.click(delete_finding);
		Assert.assertTrue(deleted_cfd_msg.getText().contains("deleted successfully"));
	}

	public void open_prescription_tab() {
		for (WebElement tab : tabs) {
			if (tab.getText().equalsIgnoreCase("PRESCRIPTION")) {
				utilities.click(tab);
			}

		}
	}

	public void open_instruction_tab() {
		for (WebElement tab : tabs) {
			utilities.fluent_wait(tab);
			if (tab.getText().equalsIgnoreCase("INSTRUCTIONS")) {
				utilities.fluent_wait(tab);
				((JavascriptExecutor)driver).executeScript("arguments[0].click();", tab);
			}

		}
	}

	public void open_summary_tab() {
		for (WebElement tab : tabs) {
			if (tab.getText().equalsIgnoreCase("SUMMARY")) {
				((JavascriptExecutor)driver).executeScript("arguments[0].click();", tab);
			}

		}
	}

}