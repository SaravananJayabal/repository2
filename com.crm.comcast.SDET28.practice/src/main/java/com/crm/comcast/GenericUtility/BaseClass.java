package com.crm.comcast.GenericUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.crm.comcast.objectRepository.CreateProductPopupPage;
import com.crm.comcast.objectRepository.HomePage;
import com.crm.comcast.objectRepository.LoginPage;
import com.crm.comcast.objectRepository.ProductInformationPage;

/**
 * Base Class contains all the configuration annotations
 * @author Rashmi T K
 *
 */

public class BaseClass {
	
	public WebDriver driver;
	public static WebDriver sdriver;
	//create Object for utilities
	public FileUtility fUtil = new FileUtility();
	public WebDriverUtility wUtil = new WebDriverUtility();
	public JavaUtility jUtil = new JavaUtility();
	public ExcelUtility eUtil = new ExcelUtility();
	
	@BeforeSuite(groups= {"smokeTest","regressionTest"})
	public void configBs() {
		//connection to DB
		System.out.println("********Connect to DB********");
	}
	//@Parameters("browserName")
	@BeforeClass(groups= {"smokeTest","regressionTest"})
	public void configBc() throws Throwable{
		//getting data from property file
		String browserName=fUtil.getPropertyFileData("browser");
		String expectedurl=fUtil.getPropertyFileData("url");
		////launching the browser
		if(browserName.equals("chrome")) {
			System.setProperty(IPathConstants.CHROME_KEY, IPathConstants.CHROME_PATH);
			driver=new ChromeDriver();
			System.out.println("PASS::Chrome Browser launched");
		}else if(browserName.equals("firefox")) {
			System.setProperty(IPathConstants.FIREFOX_KEY, IPathConstants.FIREFOX_PATH);
			driver=new FirefoxDriver();
			System.out.println("PASS::Firefox Browser launched");
	}else {
		System.out.println("FAIL::Browser not supported");
	}
		//maximizing,implicitly wait and fetching url
		sdriver=driver;
		driver.manage().window().maximize();
		wUtil.waitForPageLoad(driver);
		driver.get(expectedurl);
		String actualUrl=driver.getCurrentUrl();
		//verifying Url
		if(actualUrl.equals(expectedurl)) {
			System.out.println("PASS::Login Page is displayed");
		}else {
			System.out.println("PASS::Login Page is not displayed");
		}
		
	}
	@BeforeMethod(groups= {"smokeTest","regressionTest"})
	public void configBm() throws Throwable{
		//getting data from property file
		String username=fUtil.getPropertyFileData("username");
		String password=fUtil.getPropertyFileData("password");
		LoginPage login = new LoginPage(driver);
		login.loginToApplication(username, password);
		
	}
	@AfterMethod(groups= {"smokeTest","regressionTest"})
	public void configAm() throws Throwable {
	 HomePage homePage = new HomePage(driver);
		homePage.logOut();
		String afterLogoutUrl=driver.getCurrentUrl();
		String expectedafterLogoutURL=eUtil.getStringCellData("Sheet1", 1, 6);
		if(afterLogoutUrl.contains(expectedafterLogoutURL)) {
			System.out.println("PASS::Login Page is displayed and loggedout from application");
		}else {
			System.out.println("FAIL::Login Page is not displayed and not loggedout from application");
		}
	}
	@AfterClass(groups= {"smokeTest","regressionTest"})
	public void configAc() {
		//close or quit the browser
		driver.quit();
	}
	@AfterSuite(groups= {"smokeTest","regressionTest"})
	public void configAs() {
		//closing the database connection
		System.out.println("********DataBase connection is closed********");
	}
	

}
