package pageObjects.nopcommerce;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.nopcommerce.MyAccountPageUI;

public class MyAccountPageObject extends AbstractPage{
	WebDriver driver;

	public MyAccountPageObject(WebDriver _driver) {
		driver = _driver;
	}

	public String getAddressAccountInfoByClass(String classValue) {
		waitForElementVisible(driver, MyAccountPageUI.DYNAMIC_ADDRESS_TEXT_INFO, classValue);
		return getTextElement(driver, MyAccountPageUI.DYNAMIC_ADDRESS_TEXT_INFO, classValue);
	}

	public boolean isAddressAccountInfoDisplayedByClass(String classValue, String textInfo) {
		waitForElementVisible(driver, MyAccountPageUI.DYNAMIC_ADDRESS_INFO, classValue, textInfo);
		return isElementDisplayed(driver, MyAccountPageUI.DYNAMIC_ADDRESS_INFO, classValue, textInfo);
	}


}
