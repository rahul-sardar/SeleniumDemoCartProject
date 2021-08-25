package com.qa.democart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.qa.democart.pages.AccountsPage;
import com.qa.democart.pages.LoginPage;
import com.qa.democart.pages.ProductInfoPage;
import com.qa.democart.pages.RegistrationPage;

public class BaseTest {
	public BasePage basePage;
	public LoginPage loginPage;
	public AccountsPage accountsPage;
	public ProductInfoPage productInfoPage;
	public RegistrationPage registrationPage;
	public Properties prop;
	public WebDriver driver;
	
	
	@Parameters({"browser", "browserversion"})
	@BeforeTest
	public void setup(@Optional("Default")String browserName, @Optional("Default")String browserVersion) {
		basePage = new BasePage();
		prop = basePage.init_prop();
		String browser = prop.getProperty("browser");
		
		if(!browserName.equals("Default")) {
			prop.setProperty("browser", browserName);
			prop.setProperty("browserversion", browserVersion);
			browser = prop.getProperty("browser");
		}else {
			prop.setProperty("browser", browser);
			browser =prop.getProperty("browser");
		}
		
		driver= basePage.init_driver(browser, prop);
		loginPage = new LoginPage(driver);
		driver.get(prop.getProperty("url"));
	}
	
	@AfterTest
	public void tearDown() {
		driver.close();
	}
	
	

}
