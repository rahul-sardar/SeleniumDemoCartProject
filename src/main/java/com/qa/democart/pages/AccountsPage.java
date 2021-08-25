package com.qa.democart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.democart.base.BasePage;
import com.qa.democart.utils.Constants;
import com.qa.democart.utils.ElementUtil;

public class AccountsPage extends BasePage{

	private WebDriver driver;
	private ElementUtil utility;
	
	//1.By Locators
	private By pageheader= By.linkText("Your Store");
	private By myAccountTab = By.linkText("My Account");
	private By accountsSectionheadersOnPage = By.xpath("//div[@id='content']/h2");
	private By logoutOption = By.xpath("//aside[@id= 'column-right']/div/a[last()]");
	private By searchText = By.cssSelector("div#search input[type='text']");
	private By searchButton = By.cssSelector("div#search button[type='button']");
	private By searchItemResults = By.cssSelector(".product-layout .product-thumb"); //Css selector for finding all the results of search
	private By resultItem = By.cssSelector(".product-thumb h4 a");
	
	//constructor
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		utility = new ElementUtil(this.driver);
		
	}
	
	//3. Page Action
	public String getPageTitle() {
		return utility.waitForTitlePresent(Constants.ACCOUNTS_PAGE_TITLE, 10);
	}
	
	public String getHomePageHeader() {
		return utility.doGetText(pageheader);
	}
	
	public int isMoreThanOneHeadersOnPageExists() {
		return utility.getElements(accountsSectionheadersOnPage).size();
	}
	
	public Boolean isMyAccountTabExists() {
		return utility.doIsDisplayed(myAccountTab);
	}
	
	public Boolean isLogoutExists() {
		return utility.doIsDisplayed(logoutOption);
	}
	
	public boolean doSearch(String productName) {
		utility.clearField(searchText, 10);
		utility.doActionsSendKeys(searchText, productName);
		utility.doClick(searchButton);
		if(utility.getElements(searchItemResults).size()>0) {
			return true;
			
		}else return false;	
	}
	
	public List<String> getAccountSectionList() {
		List<String> arrayList = new ArrayList<String>();
		List<WebElement> elements = utility.getElements(accountsSectionheadersOnPage);
		for(WebElement e : elements) {
			arrayList.add(e.getText());
		}
		return arrayList;
	}
	
	public ProductInfoPage selectProductFromResult(String updatedProductName) {
		List<WebElement> resultsItemResult = utility.getElements(resultItem);
		for(WebElement e: resultsItemResult) {
			if(e.getText().equals(updatedProductName)) {
				e.click();
				break;
			}
		}
		return new ProductInfoPage(driver);
		
	}
		
}
