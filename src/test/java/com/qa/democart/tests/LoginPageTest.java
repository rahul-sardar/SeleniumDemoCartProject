package com.qa.democart.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.democart.TestListeners.ExtentReportListener;
import com.qa.democart.base.BaseTest;
import com.qa.democart.utils.Constants;

//@Listeners(ExtentReportListener.class)
public class LoginPageTest extends BaseTest {
	
	
	@Test(priority=1)
	public void verifyLoginPageTestitlePage() {
		String title = loginPage.getLoginPageTitle();
		System.out.println("Login Page Title is "+title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
		
	}
	
	@Test(priority=2)
	public void verifyRegisterLink() {
		Assert.assertTrue(loginPage.isregisterLinkExists());
		
	}
	
	@Test(priority=3)
	public void loggedUserTest() {
		loginPage.loginUser(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(loginPage.isloggedUserPageExists());
		
	}

}
