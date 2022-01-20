package createProduct;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
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
 * 
 * @author Rashmi T K
 *
 */

public class CreateProductWithSupportGroupTest extends BaseClass{
/**
 * 
 * @param args
 * @throws Throwable
 */
	
	@Test (groups= {"regressionTest"})
	public void createProductWithSupportGroup() throws Throwable {
		
		// get the data from property file
		
		String expectedTitle=fUtil.getPropertyFileData("title");
		
		//get the data from excel sheet
		String expectedOption=eUtil.getStringCellData("Sheet1",5,2);
		String expectedpopupPage=eUtil.getStringCellData("Sheet1", 5, 4);
		String productName=eUtil.getStringCellData("Sheet1",5,3);
		String handlerGroupDropdownOption=eUtil.getStringCellData("Sheet1",5,5);
		//verifying title
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle.equals(expectedTitle), true);
		//Select product from quick create drop down
		HomePage homePage = new HomePage(driver);
		homePage.clickOnDropdown();
		homePage.selectDropdownOption();
		homePage.toverifySelectedOptionInDropDownList(expectedOption);
		//create Product popup page should be displayed 
		CreateProductPopupPage createProductPage=new CreateProductPopupPage(driver);
		String actualpopupPage = createProductPage.getProductpopupText();
		//create Product popup page verifications
		Assert.assertEquals(actualpopupPage.contains(expectedpopupPage), true);
		// create a new product with product name-Sony
		createProductPage.createProduct(productName);
		
		// a)Select Group radio button beside HAndler 
		createProductPage.clickGroupRadioButton();
		
		//select "Support Group" option from the dropdown
		createProductPage.selectHandlerGroupDropdownOption(1);
		createProductPage.verifySelectedOptionInHandlerGroupDropDownList(handlerGroupDropdownOption);
				
		// Sony - Product Information page should be displayed
		ProductInformationPage productInformation=new ProductInformationPage(driver);
		String actualProductName = productInformation.getProductInfoText();
		//verification of product information
		Assert.assertEquals(actualProductName.contains(productName), true);			
		}		
			
	
	/*@Test (groups= {"regressionTest"})
	public void createProductWithTeamSelling() throws Throwable {
		
		// get the data from property file
		String expectedTitle=fUtil.getPropertyFileData("title");
		
		//get the data from excel sheet
		String expectedOption=eUtil.getStringCellData("Sheet1",7,2);
		String expectedpopupPage=eUtil.getStringCellData("Sheet1", 7, 4);
		String productName=eUtil.getStringCellData("Sheet1",7,3);
		String handlerGroupDropdownOption=eUtil.getStringCellData("Sheet1",7,5);
		String expectedafterLogoutURL=eUtil.getStringCellData("Sheet1",7,6);
		
	//verifying title
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
		
	//Select Group radio button beside HAndler 
		createProductPage.clickGroupRadioButton();
	//select "Team Selling" option from the dropdown
		createProductPage.selectHandlerGroupDropdownOption(2);
		createProductPage.verifySelectedOptionInHandlerGroupDropDownList(handlerGroupDropdownOption);
			
	//Sony - Product Information page should be displayed
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
