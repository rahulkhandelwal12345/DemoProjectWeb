package com.demo.actions.web;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.demo.setup.BaseSelenium;
import com.demo.utilities.ExcelUtils;
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
	
	
	public void verify_diagnosis_in_summary() throws Exception {
		ExcelUtils  data = new ExcelUtils (System.getProperty("user.dir") + "/src/test/java/com/demo/testdata/web/testdata.xlsx","CFD");
		String popularDiagnosis = data.getCellDataasstring(1, 3);

		for(WebElement diagnosis : Diagnosis_List)
		{
			Assert.assertTrue(diagnosis.getText().equals(popularDiagnosis));
			System.out.println(">>>>>>SUMARRY DIAGNOSIS: "+diagnosis.getText());
		}
	}
	
	public void verify_complaintfinding__in_summary() throws Exception {
		ExcelUtils  data = new ExcelUtils (System.getProperty("user.dir") + "/src/test/java/com/demo/testdata/web/testdata.xlsx","CFD");
		String searchedComplaint = data.getCellDataasstring(1, 0);
		String searchedFinding = data.getCellDataasstring(1, 1);

		for(WebElement complaints : Complaints_Findings_List)
		{
			Assert.assertTrue(complaints.getText().equals(searchedComplaint));
			Assert.assertTrue(complaints.getText().equals(searchedFinding));
		}
	}
	
	public void verify_instructions_in_summary() throws Exception {
		ExcelUtils  data = new ExcelUtils (System.getProperty("user.dir") + "/src/test/java/com/demo/testdata/web/testdata.xlsx","Test Instructions");
		String instructionName = data.getCellDataasstring(1, 1);
		for(WebElement instruction : Instructions_List)
		{
			Assert.assertTrue(instruction.getText().equals(instructionName));
		}
	}
	
	public void verify_tests__in_summary() throws Exception {
		ExcelUtils  data = new ExcelUtils (System.getProperty("user.dir") + "/src/test/java/com/demo/testdata/web/testdata.xlsx","Test Instructions");
		String testName = data.getCellDataasstring(1, 0);
		for(WebElement test : Tests_List)
		{
			Assert.assertTrue(test.getText().equals(testName));
		}
	}
	
	public void verify_procedures_in_summary() throws Exception {
		ExcelUtils  data = new ExcelUtils (System.getProperty("user.dir") + "/src/test/java/com/demo/testdata/web/testdata.xlsx","Test Instructions");
		String procedureName = data.getCellDataasstring(1, 2);
		for(WebElement procedure : Procedures_List)
		{
			Assert.assertTrue(procedure.getText().equals(procedureName));
		}
	}
	
	public void verify_drugs_in_summary() throws Exception {
		ExcelUtils  data = new ExcelUtils (System.getProperty("user.dir") + "/src/test/java/com/demo/testdata/web/testdata.xlsx","Prescription");
		String newdrugName = data.getCellDataasstring(1, 2);
		String popularDrug = data.getCellDataasstring(1, 0);
		String searchedDrug = data.getCellDataasstring(1, 1);
		for(WebElement drug : Drugs_Names_List)
		{
			Assert.assertTrue(drug.getText().contains(newdrugName));
			System.out.println(">>>>>>SUMARRY DRUG: "+drug.getText());
		}
	}
	
	
}
