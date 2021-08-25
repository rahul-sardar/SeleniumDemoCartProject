package com.qa.democart.pages;

import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.democart.utils.ElementUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil utility;
	
	//By Locators
	private By productNameHeader = By.cssSelector("#content h1");
	private By productMetaData = By.cssSelector("#content ul.list-unstyled:nth-of-type(1) li");
	private By productPrice = By.cssSelector("#content ul.list-unstyled:nth-of-type(2) li");
	private By quantity = By.id("input-quantity");
	private By addToCartButton = By.id("button-cart");
	private By productImages = By.cssSelector(".thumbnails li img");
	
	    //constructor
		public ProductInfoPage(WebDriver driver) {
			this.driver = driver;
			utility = new ElementUtil(this.driver);
			
		}
		
		//Page Action
		public Map<String, String> getProductInformation() {
			Map<String, String> productInfoMap = new HashMap<>();
			productInfoMap.put("name", utility.doGetText(productNameHeader));
			List<WebElement> productMetaDataElement = utility.getElements(productMetaData);
			for(WebElement e : productMetaDataElement) {
				productInfoMap.put(e.getText().split(":")[0].trim(), e.getText().split(":")[1].trim());
			}
			
			List<WebElement> productPriceListElement = utility.getElements(productPrice);
			productInfoMap.put("Price", productPriceListElement.get(0).getText());
			productInfoMap.put("exTaxPrice", productPriceListElement.get(1).getText().split(":")[1].trim());
			
			return productInfoMap;
		}
		
		public String getDefaultQuantityDisplayed() {
			return utility.getElement(quantity).getAttribute("value");
		}
		
		public void selectQuantity(String qty) {
			 utility.doSendKeys(quantity, qty);
		}
		
		public void addToCart() {
			 utility.doClick(addToCartButton);
		}
		
		public int getProductImages() {
			return utility.getElements(productImages).size();
		}
		
		public String getProductInfoPageTitle(String productName) {
			return utility.waitForTitlePresent(productName, 10);
		}

}
