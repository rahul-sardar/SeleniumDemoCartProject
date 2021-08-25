package com.qa.democart.tests;

import org.testng.annotations.BeforeClass;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.*;

import com.qa.democart.base.BaseTest;
import com.qa.democart.utils.Constants;

public class ProductInfoPageTest extends BaseTest{
	
	@BeforeClass
	public void productInfoSetUp() {
		accountsPage= loginPage.loginUser(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority=1)
	public void productInfoPageTitleTest_MacBookPro() {
		accountsPage.doSearch("Mac");
		productInfoPage = accountsPage.selectProductFromResult("MacBook Pro");
		Assert.assertEquals(productInfoPage.getProductInfoPageTitle("MacBook Pro"), "MacBook Pro");
	}
	
	@Test(priority=2)
	public void productInfoPageTitleTest_iMac() {
		accountsPage.doSearch("iMac");
		productInfoPage = accountsPage.selectProductFromResult("iMac");
		Assert.assertEquals(productInfoPage.getProductInfoPageTitle("iMac"), "iMac");
	}
	
	//we will verify two or three products only 
	//In real time manual scenario also we don't verify thousand of products we just verify few important products
	@Test(priority=3)
	public void verifyProductInfoTest_MacBookPro() {
		String productName = "Mac";
		String updatedProductName = "MacBook Pro";
		
		Assert.assertTrue(accountsPage.doSearch(productName));
		productInfoPage = accountsPage.selectProductFromResult(updatedProductName);
		
		Assert.assertTrue(productInfoPage.getProductImages() == 4);
		Map<String, String> productInfoMap = productInfoPage.getProductInformation(); 
		System.out.println(productInfoMap);
		
		Assert.assertEquals(productInfoMap.get("name"), updatedProductName);
		Assert.assertEquals(productInfoMap.get("Brand"), "Apple");
		Assert.assertEquals(productInfoMap.get("Product Code"), "Product 18");
		Assert.assertEquals(productInfoMap.get("Availability"), "In Stock");
		Assert.assertEquals(productInfoMap.get("Reward Points"), "800");
		Assert.assertEquals(productInfoMap.get("Price"), "$2,000.00");
		Assert.assertEquals(productInfoMap.get("exTaxPrice"), "$2,000.00");
	}
	
	@Test(priority=4)
	public void verifydefaultQuantity_MacBookPro() {
		Assert.assertEquals(productInfoPage.getDefaultQuantityDisplayed(),Constants.Default_Quantity);
	}
	
	//In real time manual scenario also we don't verify thousand of products we just verify few important products
	@Test(priority=5)
	public void verifyProductInfoTest_iMac() {
		String productName = "iMac";
		String updatedProductName = "iMac";
		
		Assert.assertTrue(accountsPage.doSearch(productName));
		productInfoPage = accountsPage.selectProductFromResult(updatedProductName);
		
		Assert.assertTrue(productInfoPage.getProductImages() == 3);
		Map<String, String> productInfoMap = productInfoPage.getProductInformation(); 
		System.out.println(productInfoMap);
		
		Assert.assertEquals(productInfoMap.get("name"), updatedProductName);
		Assert.assertEquals(productInfoMap.get("Brand"), "Apple");
		Assert.assertEquals(productInfoMap.get("Product Code"), "Product 14");
		Assert.assertEquals(productInfoMap.get("Availability"), "In Stock");
		Assert.assertEquals(productInfoMap.get("Price"), "$122.00");
		Assert.assertEquals(productInfoMap.get("exTaxPrice"), "$100.00");
	}
	@Test(priority=6)
	public void verifydefaultQuantity_iMac() {
		Assert.assertEquals(productInfoPage.getDefaultQuantityDisplayed(),Constants.Default_Quantity);
	}

}
