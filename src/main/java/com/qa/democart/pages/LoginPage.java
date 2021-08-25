package com.qa.democart.pages;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import com.qa.democart.base.BasePage;
import com.qa.democart.utils.Constants;
import com.qa.democart.utils.ElementUtil;

public class LoginPage extends BasePage{
   
	  private WebDriver driver;
	  private ElementUtil utility;
	  
	  //1.By Locators or Object Repository
	  private By emailID = By.id("input-email");
	  private By password = By.id("input-password");
	  private By loginButton = By.xpath("//input[@value='Login']");
	  private By headerLogin = By.xpath("(//h2)[2]");
	  private By headerMyAccount = By.xpath("//div[@id='content']/h2[1]");
	  private By myAccount = By.xpath("//span[text()='My Account']");
	  private By loginTab = By.linkText("Login");
	  private By continueRegisterBtn = By.linkText("Continue");
	  private By register = By.linkText("Register");
	  
	  
	  //2. Constructor of the page class
	  public LoginPage(WebDriver driver) {
		  this.driver = driver;
		  utility = new ElementUtil(this.driver);
		  //extentreport = new ExtentReportListener();
	  }
	  
	  //3.Page Action Or We can Say Behaviour of the Page 
	 
	  
	  public String navigateToLoginPage() {
		  utility.waitForElementToBeVisible(myAccount, 10);
		  utility.doClick(myAccount);
		  //ExtentReportListener.test.get().pass("Clicked on My Account Tab");
		  utility.doClick(loginTab);
		  //ExtentReportListener.test.get().pass("Clicked on login Option");
		  return utility.doGetText(headerLogin);
		  
	  }
	  
	  public String getLoginPageTitle() {
		  return utility.waitForTitlePresent(Constants.LOGIN_PAGE_TITLE, 10);
	  }
	  
	  public Boolean isregisterLinkExists() {
		  return utility.doIsDisplayed(continueRegisterBtn);
		  
	  }
	  
	  public AccountsPage loginUser(String userID , String pwd) {
		  System.out.println("Login with username" +userID +" and password " +pwd);
		  utility.doActionsSendKeys(emailID, userID);
		  utility.doActionsSendKeys(password, pwd);
//		  ExtentReportListener.test.get().pass("User ID" +userID +" Password " +pwd +" Entered in field");
		  utility.doClick(loginButton);
//		  ExtentReportListener.test.get().pass("Clicked on login button");
		  return new AccountsPage(driver); //Page Chaining concept 
		  
//		  String homePageHeader= driver.findElement(headerMyAccount).getText();
//		  Assert.assertEquals(homePageHeader, "My Account");
	 }
	  
	  public Boolean isloggedUserPageExists() {
		return utility.doIsDisplayed(headerMyAccount);
	  }
	  //Either clicking on continue button or register option in myaccount or register option on right side
	  //we can navigate to register page .This Method should return RegisterPage
	  public RegistrationPage navigateToRegister() {
		  utility.doClick(myAccount);
		  //ExtentReportListener.test.get().pass("Clicked on My Account Tab");
		  utility.doClick(register);
		  //ExtentReportListener.test.get().pass("Clicked on Register Option");
		  return new RegistrationPage(driver);
	  }
	  
}
