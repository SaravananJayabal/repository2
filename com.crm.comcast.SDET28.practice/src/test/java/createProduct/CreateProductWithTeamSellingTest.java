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

public class CreateProductWithTeamSellingTest extends BaseClass {

	@Test (groups= {"regressionTest"})
	public void createProductWithTeamSelling() throws Throwable {
		
		// get the data from property file
		String expectedTitle=fUtil.getPropertyFileData("title");
		
		//get the data from excel sheet
		String expectedOption=eUtil.getStringCellData("Sheet1",7,2);
		String expectedpopupPage=eUtil.getStringCellData("Sheet1", 7, 4);
		String productName=eUtil.getStringCellData("Sheet1",7,3);
		String handlerGroupDropdownOption=eUtil.getStringCellData("Sheet1",7,5);
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
		Assert.assertEquals(actualProductName.contains(productName), true);
	
	}

}		
	

   
		
	
				
	
			
