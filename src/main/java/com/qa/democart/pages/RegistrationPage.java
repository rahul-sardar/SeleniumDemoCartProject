package com.qa.democart.pages;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.democart.TestListeners.ExtentReportListener;
import com.qa.democart.base.BasePage;
import com.qa.democart.utils.Constants;
import com.qa.democart.utils.ElementUtil;

public class RegistrationPage extends BasePage {
	
	private ElementUtil utility;
	
	//By Locators
	By firstName = By.id("input-firstname");
	By lastName = By.id("input-lastname");
	By email = By.id("input-email");
	By phoneNumber = By.id("input-telephone");
	By password = By.id("input-password");
	By confirmPassword = By.id("input-confirm");
	By subscribeYes = By.xpath("//label[@class='radio-inline'][position()=1]/input");
	By subscribeNo = By.xpath("//label[@class='radio-inline'][position()=2]/input");
	By agreeCheckBox = By.name("agree");
	By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");
	By accountSuccessMsg = By.cssSelector("#content h1");
	private By myAccount = By.xpath("//span[text()='My Account']");
	private By logout = By.linkText("Logout");
	
	//Constructor
	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		utility = new ElementUtil(driver);
		extentreport = new ExtentReportListener();
	}
	
	//Page Action
	public boolean accountRegistration(String firstname, String lastname , String email, String telephone, String password, String subscribe) {
		Random random = new Random();
		int randomNumber = random.nextInt(1000);
		String randomemail = email.split("@")[0] + randomNumber + "@" +email.split("@")[1];
		System.out.println("Random Email Generated is" +randomemail);
		utility.doSendKeys(firstName, firstname);
		ExtentReportListener.test.get().info("FirstName Entered is "+firstname);
		utility.doSendKeys(lastName, lastname);
		ExtentReportListener.test.get().info("LastName Entered is "+lastname);
		utility.doSendKeys(this.email,  randomemail);
		ExtentReportListener.test.get().info("Email Entered is "+randomemail);
		utility.doSendKeys(phoneNumber, telephone);
		ExtentReportListener.test.get().info("Phone Number Entered is "+telephone);
		utility.doSendKeys(this.password, password);
		utility.doSendKeys(confirmPassword, password);
		
		if(subscribe.equalsIgnoreCase("yes")) {
			utility.doClick(subscribeYes);
			ExtentReportListener.test.get().info("Clicked on Yes Radio button");
		}else {
			utility.doClick(subscribeNo);
			ExtentReportListener.test.get().info("Clicked on No Radio button");
		}
	    
	    utility.doClick(agreeCheckBox);
	    ExtentReportListener.test.get().info("Clicked On Agree Check Box");
	    utility.doActionsClick(continueButton);
	    utility.waitForElementToBeVisible(accountSuccessMsg, 10);
	   
	    String successMsg = utility.doGetText(accountSuccessMsg);
	    ExtentReportListener.test.get().info("Success Message displayed is "+successMsg);
	    System.out.println("Success msg displayed is " +successMsg);
	    if(successMsg.contains(Constants.ACCOUNT_SUCCESS_MESSAGE)) {
	    	return true;
	    }
	    return false;
	}
	
	public void logoutUser() {
		utility.doClick(myAccount);
		ExtentReportListener.test.get().info("Clicked On myAccount Tab ");
		utility.doClick(logout);
		ExtentReportListener.test.get().info("Clicked On Logout option");
	}

}
