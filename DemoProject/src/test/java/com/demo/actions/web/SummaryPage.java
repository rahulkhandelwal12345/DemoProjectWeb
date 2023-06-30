package com.demo.actions.web;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.demo.setup.BaseSelenium;
import com.demo.utilities.WebUtilities;

public class SummaryPage extends BaseSelenium {
	public WebDriver driver;
	WebUtilities utilities = new WebUtilities();
	
	public SummaryPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//p[@class = 'ng-binding' and text() = 'Diagnosis']")
	private WebElement Diagnosis_Card;

	@FindBy(xpath = "//*[@id=\"print\"]/div/table[1]/tbody/tr[2]/td/div/ul/li[2]/div/h6")
	private List<WebElement> Diagnosis_List;
	
	@FindBy(xpath = "//p[@class = 'ng-binding' and text() = 'Complaints and Findings']")
	private WebElement Complaints_FindingsCard;
	
	@FindBy(xpath = "//*[@id=\"print\"]/div/table[1]/tbody/tr[2]/td/div/ul/li[1]/div/h6")
	private List<WebElement> Complaints_Findings_List;
	
	@FindBy(xpath = "//p[@class = 'ng-binding' and text() = 'Instructions']")
	private WebElement Instructions_Card;
	
	@FindBy(xpath = "//*[@id=\"print\"]/div/table[1]/tbody/tr[4]/td/div/ul/li[1]/div/h6")
	private List<WebElement> Instructions_List;
	
	@FindBy(xpath = "//p[@class = 'ng-binding' and text() = 'Tests']")
	private WebElement Tests_Card;
	
	@FindBy(xpath = "//*[@ng-if= 'test.name']")
	private List<WebElement> Tests_List;
	
	@FindBy(xpath = "//p[@class = 'ng-binding' and text() = 'Procedures']")
	private WebElement Procedures_Card;
	
	@FindBy(xpath = "//*[@ng-if= 'procedure.name']")
	private List<WebElement> Procedures_List;
	
	@FindBy(xpath = "//table[@class=\"table-prescription\"]//tr/th")
	private List<WebElement> Drug_Table_Headers;
	
	@FindBy(xpath = "//table[@class=\"table-prescription\"]/tbody/tr")
	private List<WebElement> Drugs_Rows;
	
	@FindBy(xpath = "//*[@id='print']/div/table[1]/tbody/tr[3]/td/div/table/tbody/tr/td/div/div/p")
	private List<WebElement> Drugs_Names_List;

	@FindBy(xpath = "//table[@class=\"table-prescription\"]/tbody/tr/td")
	private WebElement Prescription_Table_Cells;

	@FindBy(xpath = "//table[@class=\"table-fixed\"]/tbody/tr/td")
	private List<WebElement> Drug_Dosage_Table_Cells;
	
	
	
	public void verify_diagnosis_in_summary() {
		for(WebElement diagnosis : Diagnosis_List)
		{
			Assert.assertTrue(diagnosis.getText().equals("DENGUE FEVER - 1"));
			System.out.println(">>>>>>SUMARRY DIAGNOSIS: "+diagnosis.getText());
		}
	}
	
	public void verify_complaintfinding__in_summary() {
		for(WebElement complaints : Complaints_Findings_List)
		{
			Assert.assertTrue(complaints.getText().equals("SORE THROAT"));
			Assert.assertTrue(complaints.getText().equals("OTHER FINDINGS"));
		}
	}
	
	public void verify_instructions_in_summary() {
		for(WebElement instruction : Instructions_List)
		{
			Assert.assertTrue(instruction.getText().equals("Drink boiled water"));
		}
	}
	
	public void verify_tests__in_summary() {
		for(WebElement test : Tests_List)
		{
			Assert.assertTrue(test.getText().equals("Urine Acetone"));
		}
	}
	
	public void verify_procedures_in_summary() {
		for(WebElement procedure : Procedures_List)
		{
			Assert.assertTrue(procedure.getText().equals("ARTHROSCOPIC STABILISATION PROCEDURE"));
		}
	}
	
	public void verify_drugs_in_summary() {
		for(WebElement drug : Drugs_Names_List)
		{
			Assert.assertTrue(drug.getText().contains("DRUG101"));
			System.out.println(">>>>>>SUMARRY DRUG: "+drug.getText());
		}
	}
	
	
}
