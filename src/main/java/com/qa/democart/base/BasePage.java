package com.qa.democart.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.democart.TestListeners.ExtentReportListener;
import com.qa.democart.utils.OptionsManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	
	public WebDriver driver;
	public Properties prop;
	public ExtentReportListener extentreport;
	public OptionsManager optionsmanager;
	
	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();
     /**
      * This Method is used to initialise our WebDriver
      * @param browser It take Browser name as parameter
      * @return it return WebDriver Reference
      */
	 @SuppressWarnings("deprecation")
	public WebDriver init_driver(String browser, Properties prop) {
		 System.out.println("Browser Value is " +browser);
		 String BrowserVersion = prop.getProperty("browserversion");
		 optionsmanager = new OptionsManager(prop);
		 
		 if(browser.equalsIgnoreCase("chrome")) {
			 WebDriverManager.chromedriver().setup();
			 
			 if(Boolean.parseBoolean(prop.getProperty("remote"))) {
				 init_remoteDriver("chrome", BrowserVersion);
			 }else {
				 tldriver.set(new ChromeDriver(optionsmanager.getChromeOptions()));
				 //driver = new ChromeDriver();
			 }
			 
		 }else if(browser.equalsIgnoreCase("firefox")) {
			 WebDriverManager.firefoxdriver().setup();
			 
			 if(Boolean.parseBoolean(prop.getProperty("remote"))) {
				 init_remoteDriver("firefox",BrowserVersion);
			 }else {
				 tldriver.set(new FirefoxDriver(optionsmanager.getChromeOptions()));
				 //driver = new FirefoxDriver();
			 }
			 
		 }else if(browser.equalsIgnoreCase("safari")) {
			 tldriver.set(new SafariDriver());
			 //driver = new SafariDriver();
		 }else {
			 System.out.println("Please pass valid browser as a paramater " +browser);
		 }
//		 driver.manage().deleteAllCookies();
//		 driver.manage().window().maximize();
//		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		 return driver;
		 getDriver().manage().deleteAllCookies();
		 getDriver().manage().window().maximize();
		 return getDriver();
		 
	 }
	 
	 public static synchronized WebDriver getDriver() {
		 return tldriver.get();
	 }
	 /**
	  * This Method Used to load properties from config.properties file
	  * @return it return properties prop reference
	  */
	 public Properties init_prop() {
		 prop = new Properties();
		 
		 try {
			FileInputStream ip = new FileInputStream("/Users/rahulmac/eclipse-workspace/SeleniumDemoCartPomProject/src/main/java/com/qa/democart/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return prop; 
	 }
	 
	 public String getScreenShot() {
		 File src = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		 String path = System.getProperty("user.dir") + "/screenshots/" +System.currentTimeMillis() + ".png";
		 File destination = new File(path);
		 try {
			 FileUtils.copyFile(src, destination);
		 }catch(IOException e) {
			 e.printStackTrace();
		 }
		 return path;
	 }
	 
	 public void init_remoteDriver(String browser, String browserVersion) {
		 System.out.println("Running Test on Grid Server " +browser+ " version :"+browserVersion);
		 
		 if(browser.equalsIgnoreCase("chrome")) {
			 DesiredCapabilities cap = DesiredCapabilities.chrome();
			 cap.setCapability("browserName", "chrome");
			 //cap.setCapability("browserVersion", browserVersion);
			 cap.setCapability("enableVNC", true);
			 cap.setCapability(ChromeOptions.CAPABILITY, optionsmanager.getChromeOptions());
			 try {
				 tldriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),cap));
			 }catch(MalformedURLException e) {
				 e.printStackTrace();
			 }
		 }else if(browser.equalsIgnoreCase("firefox")) {
			 DesiredCapabilities cap = DesiredCapabilities.firefox();
			 cap.setCapability("browserName", "firefox");
			 cap.setCapability("browserVersion", browserVersion);
			 cap.setCapability("enableVNC", true);
			 cap.setCapability(FirefoxOptions.FIREFOX_OPTIONS, optionsmanager.getFirefoxOptions());
			 try {
				 tldriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),cap));
			 }catch(MalformedURLException e) {
				 e.printStackTrace();
			 } 
		 }
	 }
}
