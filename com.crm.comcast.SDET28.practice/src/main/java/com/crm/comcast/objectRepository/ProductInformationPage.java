package com.crm.comcast.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.comcast.GenericUtility.WebDriverUtility;

/**
 * Product Information Page POM design
 * @author Rashmi T K
 *
 */

public class ProductInformationPage {
	
	//Element initialization
			public ProductInformationPage(WebDriver driver) {
				PageFactory.initElements(driver,this);
			}
			
	//Element identification or declaration
			@FindBy(className="lvtHeaderText")
			private WebElement productInformationText;
			
	//getters methods
			public WebElement getProductInformationText() {
				return productInformationText;
			}
			
	//business logic
			public String getProductInfoText() {
				return productInformationText.getText();
			}
		
	

}
