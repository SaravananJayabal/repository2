package com.crm.comcast.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.comcast.GenericUtility.WebDriverUtility;
/**
 * Home Page POM Design
 * @author Rashmi T K
 *
 */

public class HomePage extends WebDriverUtility {
	
	//create object
	WebDriverUtility wUtil = new WebDriverUtility();
	
	//Element initialization
	WebDriver driver;
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver,this);
		this.driver=driver;
	}
	
	//Element identification or declaration
	@FindBy(id="qccombo")
	private WebElement quickCreateDropdown;
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement logoutPofileImg;
	
	@FindBy(linkText="Sign Out")
	private WebElement signOutLink;
	
	//getters methods
	public WebElement getQuickCreateDropdown() {
		return quickCreateDropdown;
	}
	
	public WebElement getLogoutPofileImg() {
			return logoutPofileImg;
	}

	public WebElement getSignOutLink() {
		return signOutLink;
	}
	
	//business logics
	/**
	 * This method will click on dropdown link
	 */
	public void clickOnDropdown() {
		quickCreateDropdown.click();
	}
	
	/**
	 * This method will select the option from dropdown
	 */
	public void selectDropdownOption() {
		wUtil.selectOption(quickCreateDropdown, 13);
	}
	
	/**
	 * This method will verify the option selected in dropdown list
	 * @param expectedOption
	 */
	public void toverifySelectedOptionInDropDownList(String expectedOption) {
		wUtil.verifySelectedOptionInDropDownList(quickCreateDropdown,expectedOption);
	}
	
	/**
	 * This method will perform logout action
	 */
	public void logOut() {
		mouseHover(driver, logoutPofileImg);
		signOutLink.click();
	}
	
	
	

}
