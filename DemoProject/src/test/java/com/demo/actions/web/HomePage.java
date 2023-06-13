package com.demo.actions.web;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.demo.setup.BaseSelenium;
import com.demo.utilities.WebUtilities;

public class HomePage extends BaseSelenium{
	public WebDriver driver;
	WebUtilities utilities = new WebUtilities();
	
	public HomePage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(xpath = "//*[@id=\"content\"]/div/div/div[2]/div[2]/md-grid-list/md-grid-tile[12]/figure/div[2]")
	private WebElement Slot_1;
	
	@FindBy(xpath = "//*[@id=\"content\"]/div/div/div[1]/div[1]/div[3]/div/div/div/select")
	private WebElement drop;
	
	@FindBy(css = ".panel.panel-default.panel-appointment.flex-gt-md-auto.flex-100")
	private List<WebElement> All_slots_cards;
	
	@FindBy(xpath = "//*[@class= 'panel-title ng-binding']")
	private List<WebElement> Slots_Time;
	
	@FindBy(xpath = "//*[@id=\"content\"]/div/div/div[2]/div[2]/md-grid-list/md-grid-tile/figure/div[2]")
	private List<WebElement> slots;
	
	@FindBy(name = "mobile")
	private WebElement enter_mobile;
	
	@FindBy(xpath = "//*[@class=\"md-autocomplete-suggestions\"]/li")
	private List<WebElement> patient_list;
	
	@FindBy(xpath = "//*[@class=\"md-autocomplete-suggestions\"]/li[1]")
	private WebElement single_patient;
	
	@FindBy(css = ".flex-25.btn-save.md-button.ng-scope.md-ink-ripple")
	private WebElement Add_Appointment_Btn;
	
	public void click_slot_1() {
		utilities.click(Slot_1);

	}
	
	public void select_evening_time() {
		Select s = new Select(drop);
		s.selectByVisibleText("Evening");
	}
	
	public void select_a_slot() {
	for(WebElement s : slots)
	{
		for(WebElement slot : All_slots_cards)
		{
			
			if ((slot.getAttribute("class")).contains("disabled")|| (slot.getAttribute("class")).contains("non-members"))
			{
			}
			
			else 
			{
				System.out.println(">>>>>"+slot.getAttribute("class"));

			        JavascriptExecutor js = (JavascriptExecutor)driver;		
			        js.executeScript("arguments[0].click();", s);
			       
			}
		 }
		break;
	 }
		
	}
	
	public void enter_mobile_no() {
		utilities.sendkeys(enter_mobile, "7972007624");
	}
	
	public void choose_patient() {
		for(WebElement patient : patient_list)
		{
			try {
			utilities.click(patient);
			}
			catch(org.openqa.selenium.StaleElementReferenceException ex)
			{
				utilities.click(single_patient);
			}
		}
		
		
//		try {
//			utilities.click(single_patient);
//		}
//		catch(org.openqa.selenium.StaleElementReferenceException ex)
//		{
//			utilities.click(single_patient);
//		}
	}
	
	public void click_on_Add_Appointment_Button() {
		utilities.click(Add_Appointment_Btn);
	}			


}