package createProduct;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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

public class CreateProductwithMarketingGroupTest extends BaseClass {

	@Test (groups= {"smokeTest"})
	public void createProductwithMarketingGroup() throws Throwable {
		
		// get the data from property file
		String expectedTitle=fUtil.getPropertyFileData("title");
		
		//get the data from Excel sheet
		String expectedOption=eUtil.getStringCellData("Sheet1",3,2);
		String expectedpopupPage=eUtil.getStringCellData("Sheet1", 3, 4);
		String productName=eUtil.getStringCellData("Sheet1",3,3);
		String handlerGroupDropdownOption=eUtil.getStringCellData("Sheet1",3,5);
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
		//a)Select Group radio button beside HAndler 
		createProductPage.clickGroupRadioButton();
		//b)and select "Marketing Group" option from the dropdown
		createProductPage.selectHandlerGroupDropdownOption(0);
		createProductPage.verifySelectedOptionInHandlerGroupDropDownList(handlerGroupDropdownOption);	
		// Sony - Product Information page should be displayed
		ProductInformationPage productInformation=new ProductInformationPage(driver);
		String actualProductName = productInformation.getProductInfoText();
		//verification of product information
		Assert.assertEquals(actualProductName.contains(productName), true);
			}
		
	}

	