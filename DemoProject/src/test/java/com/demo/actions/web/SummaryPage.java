package com.demo.actions.web;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.time.Duration;
import java.util.Iterator;
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
	
	@FindBy(xpath = "//*[@class='icon-check ng-scope']")
	private List<WebElement> Drug_Frequency_Checkmarks;

	@FindBy(xpath = "//table[@class='table-fixed']/tbody/tr[1]/td[7]/h6/span")
	private List<WebElement> Duration_Column;
	
	@FindBy(xpath = "//div[@class = 'btn-dashboard']/a[1]")
	private WebElement Save_Button;
	
	@FindBy(xpath = "//div[@class = 'btn-dashboard']/a[1]")
	private WebElement Save_Print_Button;
	
	@FindBy(xpath = "//div[@class = 'btn-wrap'][1]")
	private WebElement Records_Button;
	
	@FindBy(xpath = "//*[@heading = 'Records and Images']")
	private WebElement Records_n_Images_Button;

	@FindBy(xpath = "//center[@class = 'ng-binding ng-scope']")
	private List<WebElement> Records_Date;
	
	@FindBy(xpath = "//button[@class= 'btn btn-blue btn-back']")
	private WebElement Records_Back_Button;
	
	@FindBy(css = ".nav.nav-tabs>li")
	private List<WebElement> tabs;
	
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
			Assert.assertTrue(drug.getText().equalsIgnoreCase(newdrugName));
			System.out.println("*************************SUMARRY DRUG: "+drug.getText());
			System.out.println("*************************SUMARRY DRUG EXCEL: "+newdrugName);
		}
	}
	
	public void verify_drug_frequency() throws Exception {
		ExcelUtils  data = new ExcelUtils (System.getProperty("user.dir") + "/src/test/java/com/demo/testdata/web/testdata.xlsx","Prescription");
		String popularDrugFrequency = data.getCellDataasstring(1, 12);
		String newDrugFrequency = data.getCellDataasstring(1, 13);

		for(WebElement duration : Duration_Column)
		{
			Assert.assertTrue(duration.getText().equalsIgnoreCase(popularDrugFrequency)||duration.getText().equalsIgnoreCase(newDrugFrequency));
		}
	}
	
	public void verify_record_is_saved() throws Exception {
		utilities.click(Save_Button);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Records_Button);
		utilities.click(Records_Button);
		utilities.click(Records_n_Images_Button);
		
		ExcelUtils  data = new ExcelUtils (System.getProperty("user.dir") + "/src/test/java/com/demo/testdata/web/testdata.xlsx","Summary");
		String todaysDate = data.getCellDataasstring(1, 1);
		
		for(WebElement date : Records_Date)
		{
			Assert.assertTrue(date.getText().equalsIgnoreCase(todaysDate));
			System.out.println("########################### DATE: "+date.getText());
			System.out.println("########################### EXCEL DATE: "+date.getText());

		}

	}
	
	public void verify_record_can_be_printed() {
		//Navigating Back to CFD page
		utilities.click(Records_Back_Button);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		//Navigating Back to summary page
//		for (WebElement tab : tabs) {
//			if (tab.getText().equalsIgnoreCase("SUMMARY")) {
//				utilities.click(tab);
//			}
//		}
		
		try {
			for (WebElement tab : tabs) {
				if (tab.getText().equalsIgnoreCase("SUMMARY")) {
					utilities.click(tab);
				}
			}
			
		} catch (org.openqa.selenium.StaleElementReferenceException e) {
			for (WebElement tab : tabs) {
				if (tab.getText().equalsIgnoreCase("SUMMARY")) {
					utilities.click(tab);
				}
			}
			}
		try {
			utilities.click(Save_Print_Button);
		}
		catch(org.openqa.selenium.StaleElementReferenceException e) {
			utilities.click(Save_Print_Button);

		}
		Set<String> windowids = driver.getWindowHandles();
		Iterator<String> iterator = windowids.iterator();
		String parentwindow = iterator.next();
		String childwindow = iterator.next();
		driver.switchTo().window(childwindow);
		
		Assert.assertTrue(driver.getTitle().contains("prescription"));
		driver.close();
	}
}
