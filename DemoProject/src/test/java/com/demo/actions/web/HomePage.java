package com.demo.actions.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.demo.setup.BaseSelenium;
import com.demo.utilities.ExcelUtils;
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

	@FindBy(xpath = "// div[@class='panel-body ng-scope']")
	private List<WebElement> rectangleList;

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

	@FindBy(css = ".field-wrap.field-name.ng-binding")
	private List<WebElement> added_cfd_list;

	
	
	public void select_evening_time() {
		Select s = new Select(drop);
		s.selectByVisibleText("Evening");
	}

	public void select_a_slot() {

//		for (WebElement slot : All_slots_cards) {
//
//			if ((slot.getAttribute("class")).contains("disabled")
//					|| (slot.getAttribute("class")).contains("non-members")) {
//			}
//
//			else {
//				WebElement slot_rect = slot.findElement(By.cssSelector(".panel-body.ng-scope"));
//				// WebElement slot_rect = slot.findElements(rectangle);
//				System.out.println(">>>>>" + slot.getAttribute("class"));
//				JavascriptExecutor js = (JavascriptExecutor) driver;
//				js.executeScript("arguments[0].click();", slot_rect);
//				break;
//			}
//		}
		

		List<WebElement> list = driver.findElements(By.xpath("//div[contains(@class, 'panel-body ng-scope')]\n"
				+ "\n"
				+ ""));

        int length = list.size();
        System.out.println("VALUE OF length: "+length);

        for (int i = 0; i < length; i++) {
            System.out.println("VALUE OF i: "+i);
//            WebElement card= driver.findElement(By.xpath("(//div[contains(@class,'panel-body')])["+i+"]"));
//            utilities.fluent_wait(card);
//            card.click();
//            driver.navigate().back();
//            utilities.implicitWait();
//            System.out.println("click done");
        }
				
	}

	public void enter_mobile_no() throws Exception {
		ExcelUtils  data = new ExcelUtils (System.getProperty("user.dir") + "/src/test/java/com/demo/testdata/web/testdata.xlsx","Appointment");
		String mobileNo = data.getCellDataasstring(0, 1);
		utilities.sendkeys(enter_mobile, mobileNo);
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

	public void verify_diabled_slot() throws ParseException {
		for (WebElement slot : All_slots_cards) {
			// Create object of SimpleDateFormat class and decide the format
			SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm aa");
			Date dateTime = new Date();
			String time = dateFormat.format(dateTime);
			LocalTime final_local_time = LocalTime.parse( time ) ;
			System.out.println("Current date and time is " + time);
			for(WebElement slot_time : Slots_Time)
			{
				String verify_time = slot_time.getText().substring(9).toLowerCase();
				String sub_time1 = verify_time.substring(0, 4);
				String sub_time2 = verify_time.substring(4);
				String final_slot_time = sub_time1+" "+sub_time2;
				System.out.println(">>>>>SLOT TIMES: "+final_slot_time);

				LocalTime final_slot_timing = LocalTime.parse( final_slot_time ) ;

//				if(final_local_time < final_slot_timing)
//				{
//					Assert.assertTrue(slot.getAttribute("class").contains("disabled"));
//				}
			}
			break;
		}
	}

	public void verify_booked_slot() {
		for (WebElement slot : All_slots_cards) {

			Assert.assertTrue(slot.getAttribute("class").contains("non-members"));
	
		}
	}

	public void select_created_appointment() {
		utilities.fluent_wait(All_slots_cards);
//		for (WebElement slot : All_slots_cards) {
//			utilities.fluent_wait(slot);
//			if ((slot.getAttribute("class")).contains("non-members")) {
//				WebElement slot_rect = slot.findElement(By.cssSelector(".panel-body.ng-scope"));
//				JavascriptExecutor js = (JavascriptExecutor) driver;
//				js.executeScript("arguments[0].click();", slot_rect);
//				for(WebElement addedcfd : added_cfd_list)
//				{
//					if(addedcfd.isDisplayed())
//					{
//						driver.navigate().back();
//					}
//					else {
//						break;
//					}
//				}
//			}
//
//			else {
//
//			}
//		}
		
		int length = rectangleList.size();
        for (int i = 2; i < length; i++) {
            WebElement card= driver.findElement(By.xpath("(//div[contains(@class,'panel-body')])["+i+"]"));
            if ((card.getAttribute("class")).contains("non-members")) {
            	card.click();
            	System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!card class"+card.getAttribute("class"));
            }
            driver.navigate().back();
            System.out.println("click done");
        }
		

	}
}
