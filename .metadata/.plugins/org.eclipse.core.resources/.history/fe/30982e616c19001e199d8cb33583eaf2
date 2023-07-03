package com.demo.actions.web;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demo.setup.BaseSelenium;
import com.demo.utilities.WebUtilities;

public class Instructions extends BaseSelenium {
	public WebDriver driver;
	WebUtilities utilities = new WebUtilities();
	
	public Instructions(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//label[@class='item item-input']/input")
	private WebElement searchbox;
	
	@FindBy(xpath = "//*[@class=\"search-result mb-0 ng-scope\"]/div/a")
	private List<WebElement> search_result_instructions;
	
	public void search_test() throws IOException {
		Properties pro = BaseSelenium.test_data();
		//utilities.sendkeys(searchbox, pro.getProperty("test"));
		utilities.sendkeys(searchbox, "Urine Acetone");

	}
	
	public void add_test() throws IOException {
		Properties pro = BaseSelenium.test_data();
		for(WebElement result : search_result_instructions)
		{
				try {
					if(result.getText().contains("Urine Acetone"))
					//if(result.getText().contains(pro.getProperty("test")))
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
						if(result.getText().contains("Urine Acetone"))
//						if(result.getText().contains(pro.getProperty("test")))
						{
							 Actions builder = new Actions(driver);
						     builder.moveToElement(result).click(result);
						     builder.perform();
							
						    break;
						}
					}
					catch(org.openqa.selenium.StaleElementReferenceException e)
					{
						if(result.getText().contains("Urine Acetone"))
						//if(result.getText().contains(pro.getProperty("test")))
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
	
	public void search_instruction() throws IOException {
		Properties pro = BaseSelenium.test_data();
		searchbox.clear();
		//utilities.sendkeys(searchbox, pro.getProperty("instruction"));
		utilities.sendkeys(searchbox, "Drink boiled water");
	}
	
	public void add_instruction() throws IOException {
		Properties pro = BaseSelenium.test_data();
		for(WebElement result : search_result_instructions)
		{
			String backgroundColor = result.getCssValue("background-color");
				try {
					if(result.getText().contains("Drink boiled water"))
					//if(result.getText().equalsIgnoreCase(pro.getProperty("instruction"))&& backgroundColor.equalsIgnoreCase("#3ec0a1"))
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
						if(result.getText().contains("Drink boiled water"))
						//if(result.getText().equalsIgnoreCase(pro.getProperty("instruction"))&& backgroundColor.equalsIgnoreCase("#3ec0a1"))
						{
							 Actions builder = new Actions(driver);
						     builder.moveToElement(result).click(result);
						     builder.perform();
							
						    break;
						}
					}
					catch(org.openqa.selenium.StaleElementReferenceException e)
					{
						if(result.getText().contains("Drink boiled water"))
						//if(result.getText().equalsIgnoreCase(pro.getProperty("instruction"))&& backgroundColor.equalsIgnoreCase("#3ec0a1"))
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
	
	public void search_procedure() throws IOException {
		Properties pro = BaseSelenium.test_data();
		searchbox.clear();
		//utilities.sendkeys(searchbox, pro.getProperty("procedure"));
		utilities.sendkeys(searchbox, "ARTHROSCOPIC STABILISATION PROCEDURE");

	}
	
	public void add_procedure() throws IOException {
		Properties pro = BaseSelenium.test_data();
		for(WebElement result : search_result_instructions)
		{
			String backgroundColor = result.getCssValue("background-color");
				try {

					if(result.getText().contains("ARTHROSCOPIC STABILISATION PROCEDURE"))
					//if(result.getText().equalsIgnoreCase(pro.getProperty("procedure"))&& backgroundColor.equalsIgnoreCase("#f7941e"))
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
						if(result.getText().contains("ARTHROSCOPIC STABILISATION PROCEDURE"))
						//if(result.getText().equalsIgnoreCase(pro.getProperty("procedure"))&& backgroundColor.equalsIgnoreCase("#f7941e"))
						{
							 Actions builder = new Actions(driver);
						     builder.moveToElement(result).click(result);
						     builder.perform();
							
						    break;
						}
					}
					catch(org.openqa.selenium.StaleElementReferenceException e)
					{
						if(result.getText().contains("ARTHROSCOPIC STABILISATION PROCEDURE"))
						//if(result.getText().equalsIgnoreCase(pro.getProperty("procedure"))&& backgroundColor.equalsIgnoreCase("#f7941e"))
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
	
}
