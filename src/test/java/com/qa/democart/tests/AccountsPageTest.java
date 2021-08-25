package com.qa.democart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.democart.base.BaseTest;
import com.qa.democart.utils.Constants;

public class AccountsPageTest extends BaseTest{

	@BeforeClass
	public void homePageSetup() {
		accountsPage= loginPage.loginUser(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority= 1)
	public void pageTitleTest() {
		String title = accountsPage.getPageTitle();
		System.out.println("Page title of Homepage is "+title);
		Assert.assertEquals(title, Constants.ACCOUNTS_PAGE_TITLE);
	}
	
	@Test(priority= 2)
	public void verifyHomePageHeader() {
		String header = accountsPage.getHomePageHeader();
		System.out.println("Home Page Header is "+header);
		Assert.assertEquals(header, Constants.ACCOUNTS_PAGE_HEADER);
	}
	
	@Test(priority= 3)
	public void verifyMoreThanOneHomePageHeaderExists() {
		Assert.assertTrue(accountsPage.isMoreThanOneHeadersOnPageExists()==Constants.ACCOUNT_PAGEHEADERCOUNT);
	}
	
	@Test(priority= 4)
	public void verifyMyAccountTabExists() {
		Assert.assertTrue(accountsPage.isMyAccountTabExists());
	}
	
	@Test(priority= 5)
	public void VerifyLogoutOptionExists() {
		Assert.assertTrue(accountsPage.isLogoutExists());
	}
	
	@Test(priority= 6)
	public void verifyAccountSectionList() {
		Assert.assertEquals(accountsPage.getAccountSectionList(), Constants.getAccountSectionList());
	}
	
	@Test(priority= 7)
	public void searchTest() {
		Assert.assertTrue(accountsPage.doSearch("imac"));
	}
	
}
