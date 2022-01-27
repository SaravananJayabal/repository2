
//To Create Product With Quick Create
package createProduct;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.comcast.GenericUtility.BaseClass;
import com.crm.comcast.GenericUtility.ExcelUtility;
import com.crm.comcast.GenericUtility.FileUtility;
import com.crm.comcast.GenericUtility.IPathConstants;
import com.crm.comcast.GenericUtility.JavaUtility;
import com.crm.comcast.GenericUtility.WebDriverUtility;
import com.crm.comcast.objectRepository.CreateProductPopupPage;
import com.crm.comcast.objectRepository.HomePage;
import com.crm.comcast.objectRepository.LoginPage;
import com.crm.comcast.objectRepository.ProductInformationPage;

/**
 * Test Script for CreateProductWithQuickCreateTest
 * @author Rashmi T K
 *
 */
@Listeners(com.crm.comcast.GenericUtility.ListenerImpClass.class)
public class CreateProductWithQuickCreateTest extends BaseClass {

	@Test (groups= {"smokeTest"})
	public void createProductWithQuickCreateTest() throws Throwable {
		
		// get the data from property file
		String expectedTitle=fUtil.getPropertyFileData("title");
		
		//get the data from excel sheet
		String expectedOption=eUtil.getStringCellData("Sheet1",1,2);
		String productName=eUtil.getStringCellData("Sheet1",1,3);
		String expectedpopupPage=eUtil.getStringCellData("Sheet1", 1, 4);	
		//verifying title
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle.equals(expectedTitle), true);
	   //Select product from quick create drop down
		HomePage homePage = new HomePage(driver);
		homePage.clickOnDropdown();
		homePage.selectDropdownOption();
		homePage.toverifySelectedOptionInDropDownList(expectedOption);
		//create Product popup page should be displayed
		CreateProductPopupPage createproductPage=new CreateProductPopupPage(driver);
		String actualpopupPage = createproductPage.getProductpopupText();
		//create Product popup page verifications
		Assert.assertEquals(actualpopupPage.contains(expectedpopupPage), true);
		//create a new product with product name-Sony
			createproductPage.createProduct(productName);
			createproductPage.clickUserRadioButton();	
		//Sony - Product Information page should be displayed
			ProductInformationPage productInformation=new ProductInformationPage(driver);
			String actualProductName = productInformation.getProductInfoText();
		//verification of product information
			Assert.assertEquals(actualProductName.contains(productName), true);
		
		}
	
	
	/*@Test (groups= {"smokeTest"})
	public void createProductwithMarketingGroup() throws Throwable {
		
		// get the data from property file
		String expectedTitle=fUtil.getPropertyFileData("title");
		
		//get the data from Excel sheet
		String expectedOption=eUtil.getStringCellData("Sheet1",3,2);
		String expectedpopupPage=eUtil.getStringCellData("Sheet1", 3, 4);
		String productName=eUtil.getStringCellData("Sheet1",3,3);
		String handlerGroupDropdownOption=eUtil.getStringCellData("Sheet1",3,5);
		String expectedafterLogoutURL=eUtil.getStringCellData("Sheet1",3,6);
		
		
	//2.verifying title
	String actualTitle = driver.getTitle();
	if(actualTitle.equals(expectedTitle)) {
		System.out.println("PASS::Home Page is displayed");
	}else {
		System.out.println("FAIL::Home Page is not displayed");
	}
	
   //Select product from quick create drop down
	HomePage homePage = new HomePage(driver);
	homePage.clickOnDropdown();
	homePage.selectDropdownOption();
	homePage.toverifySelectedOptionInDropDownList(expectedOption);
	
	//create Product popup page should be displayed
	CreateProductPopupPage createProductPage=new CreateProductPopupPage(driver);
	String actualpopupPage = createProductPage.getProductpopupText();
	//create Product popup page verifications
	if(actualpopupPage.contains(expectedpopupPage)) {
		System.out.println("PASS::Create Product popup page is displayed");
	}else {
		System.out.println("FAIL::Create Product popup page is not displayed");
	}
	//create a new product with product name-Sony
	createProductPage.createProduct(productName);
	
	//a)Select Group radio button beside HAndler 
	createProductPage.clickGroupRadioButton();
	
	//b)and select "Marketing Group" option from the dropdown
	createProductPage.selectHandlerGroupDropdownOption(0);
	createProductPage.verifySelectedOptionInHandlerGroupDropDownList(handlerGroupDropdownOption);
		
	// Sony - Product Information page should be displayed
	ProductInformationPage productInformation=new ProductInformationPage(driver);
	String actualProductName = productInformation.getProductInfoText();
	//verification of product information
		if(actualProductName.contains(productName)) {
			System.out.println("PASS::Sony - Product Information page is displayed");
		}else {
			System.out.println("FAIL::Sony - Product Information page is not displayed");
		}
			
}*/
}
