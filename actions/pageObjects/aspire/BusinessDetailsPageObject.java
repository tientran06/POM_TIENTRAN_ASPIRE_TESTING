package pageObjects.aspire;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.aspire.BusinessDetailsPageUI;

public class BusinessDetailsPageObject extends AbstractPage{
	WebDriver driver;

	public BusinessDetailsPageObject(WebDriver _driver) {
		driver = _driver;
	}

	public void inputToBusinessRegistrationNoTextBox(String uENCode) {
		waitForElementVisible(driver, BusinessDetailsPageUI.BUSINESS_REG_NUMBER_TEXTBOX);
		sendkeyToElementByJS(driver, BusinessDetailsPageUI.BUSINESS_REG_NUMBER_TEXTBOX, uENCode);
		
	}

	public void inputToBusinessNameTextbox(String businessName) {
		waitForElementVisible(driver, BusinessDetailsPageUI.BUSINESS_FULL_NAME_TEXTBOX);
		sendkeyToElementByJS(driver, BusinessDetailsPageUI.BUSINESS_FULL_NAME_TEXTBOX, businessName);
		
	}

	}
