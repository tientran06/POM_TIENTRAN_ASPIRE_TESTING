package pageObjects.aspire;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.GlobalConstants;
import pageUIs.aspire.BusinessDetailsPageUI;

public class BusinessDetailsPageObject extends AbstractPage {
	WebDriver driver;

	public BusinessDetailsPageObject(WebDriver _driver) {
		driver = _driver;
	}

	public void inputToBusinessDetailTextBox(String nameField, String textValue) {
		waitForElementVisible(driver, BusinessDetailsPageUI.DYNAMIC_BUSINESS_DETAIL_TEXTBOX, nameField);
		sendKeyToElement(driver, BusinessDetailsPageUI.DYNAMIC_BUSINESS_DETAIL_TEXTBOX, textValue, nameField);
	}

	public boolean isIdentityTextDisplayed(WebDriver driver) {
		overrideGlobalTimeout(driver, GlobalConstants.SPECIAL_TIMEOUT);
		waitForElementVisible(driver, BusinessDetailsPageUI.IDENTITY_TEXT);
		
		overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
		return isElementDisplayed(driver, BusinessDetailsPageUI.IDENTITY_TEXT);
	}

}
