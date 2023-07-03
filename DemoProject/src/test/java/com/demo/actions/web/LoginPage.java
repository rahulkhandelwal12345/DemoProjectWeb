package com.demo.actions.web;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demo.setup.BaseSelenium;
import com.demo.utilities.ExcelUtils;
import com.demo.utilities.WebUtilities;

import net.bytebuddy.asm.MemberSubstitution.FieldValue;

public class LoginPage extends BaseSelenium {
	public WebDriver driver;
	WebUtilities utilities = new WebUtilities();

	@FindBy(id = "input_0")
	private WebElement Email;

	@FindBy(id = "input_1")
	private WebElement Password;

	@FindBy(xpath = "//*[@class=\"text-left mt-10\"]/button")
	private WebElement Login_btn;

	@FindBy(xpath = "//*[@class = 'ng-isolate-scope' ]/div/input")
	private List<WebElement> otp;
	
	@FindBy(css = ".btn")
	private WebElement verifybtn;
	
	@FindBy(xpath = "//span[contains(text(),'Admin')]")
	private WebElement Login_Username;

	@FindBy(xpath = "//*[@id=\"scrollview\"]/div/div/div/div[1]/div/div[3]/div[1]/div[1]/span/div/i")
	private WebElement arrowicon;

	@FindBy(xpath = "//span[contains(text(),'Log Out')]")
	private WebElement logout_btn;

	@FindBy(xpath = "//span[contains(text(),'Log Out')]")
	private WebElement confirmation_logout_btn;

	@FindBy(xpath = "//div[contains(text(),'Recent logins')]")
	private WebElement recentLogin;

	@FindBy(xpath = "//*[@id=\"loginform\"]/div[1]/div[2]")
	private WebElement errormessage;

	public WebElement recent_login_disply() {
		return recentLogin;
	}

	public void click_arrowicon() {
		utilities.click(arrowicon);

	}

	public void click_logout_btn() {
		utilities.click(logout_btn);

	}

	public void click_conformation_Logout_btn() {
		utilities.click(confirmation_logout_btn);

	}

	public LoginPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	public void enterInvalidPassword() {
		utilities.sendkeys(Password, "rahul@12356");
	}

	public void enterValidEmail() throws Exception {
		ExcelUtils  data = new ExcelUtils (System.getProperty("user.dir") + "/src/test/java/com/demo/testdata/web/testdata.xlsx","Login");
		String username = data.getCellDataasstring(1, 0);
		utilities.sendkeys(Email, username);
	}

	public WebElement verify_user_name() {
		return Login_Username;
	}

	public String validateErrorMessage() {
		return utilities.getText(errormessage);
	}
	
	public void enterValidPassword() throws Exception {
	    ExcelUtils  data = new ExcelUtils (System.getProperty("user.dir") + "/src/test/java/com/demo/testdata/web/testdata.xlsx","Login");
	    String password = data.getCellDataasstring(1, 1);
		utilities.sendkeys(Password, password);
	}

	
	public void click_login_btn() {
		utilities.click(Login_btn);
	}

	public void enter_otp() {
		for (WebElement element : otp){
			utilities.sendkeys(element, element.getAttribute("Placeholder"));
		}
	}
	
	public void verify_otp() {
		utilities.click(verifybtn);
	}
	
	public void implicitWait() {
		utilities.implicitWait();
	}

}
