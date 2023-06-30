package com.demo.actions.web;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.demo.setup.BaseSelenium;
import com.demo.utilities.WebUtilities;

public class HomePage extends BaseSelenium {
	public WebDriver driver;
	WebUtilities utilities = new WebUtilities();
	
	public HomePage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//*[@id=\"content\"]/div/div/div[1]/div[1]/div[3]/div/div/div/select")
	private WebElement drop;

	@FindBy(css = ".panel.panel-default.panel-appointment.flex-gt-md-auto.flex-100")
	private List<WebElement> All_slots_cards;

	@FindBy(xpath = "//*[@class= 'panel-title ng-binding']")
	private List<WebElement> Slots_Time;

	@FindBy(xpath = "//*[@id=\"content\"]/div/div/div[2]/div[2]/md-grid-list/md-grid-tile/figure/div[2]")
	private List<WebElement> slots;

	@FindBy(css = ".panel-body.ng-scope")
	private WebElement rectangle;

	@FindBy(xpath = "//*[@class=\"btn-wrap ng-scope\"]/a[3]")
	private List<WebElement> delete_appointment_buttons;

	@FindBy(name = "mobile")
	private WebElement enter_mobile;

	@FindBy(xpath = "//*[@class=\"md-autocomplete-suggestions\"]/li")
	private List<WebElement> patient_list;

	@FindBy(xpath = "//*[@class=\"md-autocomplete-suggestions\"]/li[1]")
	private WebElement single_patient;

	@FindBy(css = ".flex-25.btn-save.md-button.ng-scope.md-ink-ripple")
	private WebElement Add_Appointment_Btn;

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

	
	
	public void select_evening_time() {
		Select s = new Select(drop);
		s.selectByVisibleText("Evening");
	}

	public void select_a_slot() {

		for (WebElement slot : All_slots_cards) {

			if ((slot.getAttribute("class")).contains("disabled")
					|| (slot.getAttribute("class")).contains("non-members")) {
			}

			else {
				WebElement slot_rect = slot.findElement(By.cssSelector(".panel-body.ng-scope"));
				// WebElement slot_rect = slot.findElements(rectangle);
				System.out.println(">>>>>" + slot.getAttribute("class"));
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();", slot_rect);
				break;
			}
		}
	}

	public void enter_mobile_no() throws IOException {
		Properties pro = BaseSelenium.test_data();
		//utilities.sendkeys(enter_mobile, pro.getProperty("mobile_number"));
		utilities.sendkeys(enter_mobile, "7972007624");
	}

	public void choose_patient() {
		for (WebElement patient : patient_list) {
			try {
				utilities.click(patient);
			} catch (org.openqa.selenium.StaleElementReferenceException ex) {
				utilities.click(single_patient);
			}
		}

	}

	public void click_on_Add_Appointment_Button() {
		utilities.click(Add_Appointment_Btn);
	}

	public void verify_diabled_slot() {
		for (WebElement slot : All_slots_cards) {
			// Create object of SimpleDateFormat class and decide the format
			DateFormat dateFormat = new SimpleDateFormat("HH:mm");
			Date dateTime = new Date();
			String time = dateFormat.format(dateTime);
			System.out.println("Current date and time is " + time);

			if ((slot.getAttribute("class")).contains("disabled")) {
				WebElement timee = slot.findElement(By.cssSelector(".panel-body.ng-scope"));
				System.out.println(">>> " + slot + " :Slot is disabled");
			}

		}
	}

	public void verify_booked_slot() {
		for (WebElement slot : All_slots_cards) {

			if ((slot.getAttribute("class")).contains("non-members")) {
				System.out.println(">>> " + slot + " :Slot is booked");
			}

		}
	}

	public void select_created_appointment() {
		for (WebElement slot : All_slots_cards) {

			if ((slot.getAttribute("class")).contains("non-members")) {
				WebElement slot_rect = slot.findElement(By.cssSelector(".panel-body.ng-scope"));
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();", slot_rect);
				break;
			}

			else {

			}
		}

	}

	public void verify_popular_diagnosis() {
		utilities.implicitWait();
		int count = 0;
		for (WebElement result : Search_Result_cfd) {
			if (result.isDisplayed()) {
				count++;
			}
		}

		Assert.assertTrue(count == 20);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + count);
	}

	public void add_popular_diagnosis() throws IOException {
		Properties pro = BaseSelenium.test_data();
		for (WebElement result : Search_Result_cfd) {
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>POPULAR DIAGNOSIS  "+result.getText());

			try {

				if (result.getText().contains("DENGUE FEVER - 1")) {
				//if (result.getText().contains(pro.getProperty("popular_diagnosis"))) {
					Actions builder = new Actions(driver);
					builder.moveToElement(result).click(result);
					builder.perform();

					break;
				}
			} catch (org.openqa.selenium.StaleElementReferenceException ex) {
				try {
					if (result.getText().contains("DENGUE FEVER - 1")) {
					//if (result.getText().contains(pro.getProperty("popular_diagnosis"))) {
						Actions builder = new Actions(driver);
						builder.moveToElement(result).click(result);
						builder.perform();

						break;
					}
				} catch (org.openqa.selenium.StaleElementReferenceException e) {
					if (result.getText().contains("DENGUE FEVER - 1")) {
					//if (result.getText().contains(pro.getProperty("popular_diagnosis"))) {
						Actions builder = new Actions(driver);
						builder.moveToElement(result).click(result);
						builder.perform();

						break;
					}
				}
			}
		}
	}

	public void search_diagnosis() throws IOException {
		Properties pro = BaseSelenium.test_data();
		utilities.implicitWait();
		//utilities.sendkeys(searchbox, pro.getProperty("searched_diagnosis"));
		utilities.sendkeys(searchbox, "HYPERTHYROIDISM");

		
		for (WebElement result : Search_Result_cfd) {
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>SEARCHED DIAGNOSIS  "+result.getText());

			try {
				if (result.getText().contains("HYPERTHYROIDISM")) {
				//if (result.getText().contains(pro.getProperty("searched_diagnosis"))) {
					Actions builder = new Actions(driver);
					builder.moveToElement(result).click(result);
					builder.perform();

					break;
				}
			} catch (org.openqa.selenium.StaleElementReferenceException ex) {
				try {
					if (result.getText().contains("HYPERTHYROIDISM")) {
					//if (result.getText().contains(pro.getProperty("searched_diagnosis"))) {
						Actions builder = new Actions(driver);
						builder.moveToElement(result).click(result);
						builder.perform();

						break;
					}
				} catch (org.openqa.selenium.StaleElementReferenceException e) {
					if (result.getText().contains("HYPERTHYROIDISM")) {
					//if (result.getText().contains(pro.getProperty("searched_diagnosis"))) {
						Actions builder = new Actions(driver);
						builder.moveToElement(result).click(result);
						builder.perform();

						break;
					}
				}
			}
		}
	}

	public void search_complaint() throws IOException {
		Properties pro = BaseSelenium.test_data();
		utilities.implicitWait();
		searchbox.clear();
		//utilities.sendkeys(searchbox, pro.getProperty("searched_complaint"));
		utilities.sendkeys(searchbox, "SORE THROAT");
		utilities.implicitWait();

		for (WebElement result : Search_Result_cfd) {
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>SEARCHED COMPLAINT  "+result.getText());

			try {
				if (result.getText().contains("SORE THROAT")) {
				//if (result.getText().contains(pro.getProperty("searched_complaint"))) {
					Actions builder = new Actions(driver);
					builder.moveToElement(result).click(result);
					builder.perform();

					break;
				}
			} catch (org.openqa.selenium.StaleElementReferenceException ex) {
				try {
					if (result.getText().contains("SORE THROAT")) {
					//if (result.getText().contains(pro.getProperty("searched_complaint"))) {
						Actions builder = new Actions(driver);
						builder.moveToElement(result).click(result);
						builder.perform();

						break;
					}
				} catch (org.openqa.selenium.StaleElementReferenceException e) {
					if (result.getText().contains("SORE THROAT")) {
					//if (result.getText().contains(pro.getProperty("searched_complaint"))) {
						Actions builder = new Actions(driver);
						builder.moveToElement(result).click(result);
						builder.perform();

						break;
					}
				}
			}
		}
	}

	public void search_finding() throws IOException {
		Properties pro = BaseSelenium.test_data();
		utilities.implicitWait();
		searchbox.clear();
		//utilities.sendkeys(searchbox, pro.getProperty("searched_finding"));
		utilities.sendkeys(searchbox, "OTHER FINDINGS");
		utilities.implicitWait();

		for (WebElement result : Search_Result_cfd) {
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>SEARCHED FINDINGS  "+result.getText());

			try {
				if (result.getText().contains(pro.getProperty("OTHER FINDINGS")) ){
				//if (result.getText().contains(pro.getProperty("searched_finding"))) {
					Actions builder = new Actions(driver);
					builder.moveToElement(result).click(result);
					builder.perform();

					break;
				}
			} catch (org.openqa.selenium.StaleElementReferenceException ex) {
				try {
					if (result.getText().contains(pro.getProperty("OTHER FINDINGS")) ){
//					if (result.getText().contains(pro.getProperty("searched_finding"))) {
						Actions builder = new Actions(driver);
						builder.moveToElement(result).click(result);
						builder.perform();

						break;
					}
				} catch (org.openqa.selenium.StaleElementReferenceException e) {
					if (result.getText().contains(pro.getProperty("OTHER FINDINGS")) ){
//					if (result.getText().contains(pro.getProperty("searched_finding"))) {
						Actions builder = new Actions(driver);
						builder.moveToElement(result).click(result);
						builder.perform();

						break;
					}
				}
			}
		}

	}

	public void verify_added_cfd() throws IOException {
		Properties pro = BaseSelenium.test_data();
		for (WebElement added_cfd : added_cfd_list) {
			Assert.assertTrue(added_cfd.getText().contains("DENGUE FEVER - 1") || added_cfd.getText().contains("SORE THROAT")|| added_cfd.getText().contains("OTHER FINDINGS"));

//			Assert.assertTrue(added_cfd.getText().contains(pro.getProperty("searched_diagnosis")) || added_cfd.getText().contains(pro.getProperty("searched_complaint"))
//					|| added_cfd.getText().contains(pro.getProperty("searched_finding")));

		}
	}

	public void delete_added_diagnosis() {
		utilities.click(delete_diagnosis);
	}

	public void delete_added_complaint() {
		Actions builder = new Actions(driver);
		builder.moveToElement(delete_complaint).click(delete_complaint).perform();
//		utilities.click(delete_complaint);
	}

	public void delete_added_finding() {
		utilities.click(delete_finding);
	}

	public void verify_deleted_cfd() {
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
			if (tab.getText().equalsIgnoreCase("INSTRUCTIONS")) {
				utilities.click(tab);
			}

		}
	}

	public void open_summary_tab() {
		for (WebElement tab : tabs) {
			if (tab.getText().equalsIgnoreCase("SUMMARY")) {
				utilities.click(tab);
			}

		}
	}

}
