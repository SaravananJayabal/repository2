package com.crm.comcast.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.comcast.GenericUtility.WebDriverUtility;

/**
 * Create Product Popup Page POM design
 * @author Rashmi T K
 *
 */

public class CreateProductPopupPage extends WebDriverUtility {
	
	//create object
		WebDriverUtility wUtil = new WebDriverUtility();
	
	//Element initialization
		public CreateProductPopupPage(WebDriver driver) {
			PageFactory.initElements(driver,this);
		}
	
	//Element identification or declaration
		@FindBy(xpath="//b[text()='Create Product']")
		private WebElement productPopupPageText;
		
		@FindBy(name="productname")
		private WebElement productNameTextField;
		
		@FindBy(xpath="//input[not(@id='assigntype') and @type='radio' and @value='U']")
		private WebElement userRadioButton;
		
		@FindBy(xpath="//input[not(@id='assigntype') and @type='radio' and @value='T']")
		private WebElement groupRadioButton;
		
		@FindBy(name="assigned_group_id")
		private WebElement handlerGroupDropdown;
		
		@FindBy(xpath="//input[@type='submit']")
		private WebElement saveButton;
		
	//getters methods
		public WebElement getPopupPage() {
			return productPopupPageText;
		}
		
		public WebElement getProductNameTextField() {
			return productNameTextField;
		}

		public WebElement getUserRadioButton() {
			return userRadioButton;
		}
		
		public WebElement getGroupRadioButton() {
			return groupRadioButton;
		}
		
		public WebElement getSaveButton() {
			return saveButton;
		}
		
		
	//Business logics
		/**
		 * This method will return the product popup information text
		 * @return
		 */
		public String getProductpopupText() {
			return productPopupPageText.getText();
		}
		
		/**
		 * This method will create a product
		 * @param productName
		 */
		public void createProduct(String productName) {
			productNameTextField.sendKeys(productName);
		}
		
		/**
		 * This method will click on user radio button
		 */
		public void clickUserRadioButton() {
			userRadioButton.click();
			if (userRadioButton.isSelected()) {
				System.out.println("PASS::User Radio button is selected ");
			}else {
				System.out.println("FAIL::User Radio button is not selected");
			}
			saveButton.click();
		}
		
		/**
		 * This method will click on group radio button
		 */
		
		public void clickGroupRadioButton() {
			groupRadioButton.click();
			if (groupRadioButton.isSelected()) {
				System.out.println("PASS::Group Radio button is selected ");
			}else {
				System.out.println("FAIL::Group Radio button is not selected");
			}
			
			}
		
		public void selectHandlerGroupDropdownOption(int index) {
			wUtil.selectOption(handlerGroupDropdown, index);
		}
		
		public void verifySelectedOptionInHandlerGroupDropDownList(String handlerGroupDropdownOption ) {
			wUtil.verifySelectedOptionInDropDownList(handlerGroupDropdown, handlerGroupDropdownOption);
			saveButton.click();
		}
		
}


	


