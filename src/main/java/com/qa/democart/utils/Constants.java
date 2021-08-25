package com.qa.democart.utils;

import java.util.*;

public class Constants {
     
	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String ACCOUNTS_PAGE_TITLE = "My Account";
	public static final String ACCOUNTS_PAGE_HEADER = "Your Store";
	public static final int ACCOUNT_PAGEHEADERCOUNT= 4;
	public static final String Default_Quantity = "1";
	
	public static final String REGISTER_SHEET_NAME = "Registration";
	public static final String ACCOUNT_SUCCESS_MESSAGE = "Your Account Has Been Created!";
	
	public static List<String> getAccountSectionList(){
		List<String> accountList = new ArrayList<String>();
		accountList.add("My Account");
		accountList.add("My Orders");
		accountList.add("My Affiliate Account");
		accountList.add("Newsletter");
		
		return accountList;
	}
	
}
