package com.qa.democart.tests;

import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.democart.TestListeners.ExtentReportListener;
import com.qa.democart.base.BaseTest;
import com.qa.democart.utils.Constants;
import com.qa.democart.utils.ExcelUtil;

//@Listeners(ExtentReportListener.class)
public class RegistrationPageTest extends BaseTest {
	
	@BeforeClass
	public void registerPageSetUp() {
		registrationPage= loginPage.navigateToRegister();
	}
	
	@DataProvider
	public Object[][] getRegistrationData(){
		Object[][] data =ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
		return data;
	}
	
	@Test(dataProvider = "getRegistrationData")
	public void userRegistrationTest(String firstname, String lastname, String email, String telephone, String password, String subscribe) {
		registrationPage= loginPage.navigateToRegister();
		Assert.assertTrue(registrationPage.accountRegistration(firstname, lastname, email, telephone, password, subscribe));
		ExtentReportListener.test.get().pass("Success Message Displayed!! User is Register");
		registrationPage.logoutUser();
		loginPage.navigateToRegister();
		
	}

}
