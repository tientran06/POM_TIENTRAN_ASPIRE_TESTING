package pageObjects.aspire;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.aspire.CompletePageUI;

public class CompletePageObject extends AbstractPage {
	WebDriver driver;

	public CompletePageObject(WebDriver _driver) {
		driver = _driver;
	}

	public boolean isSuccessMessageDisplayedByText(String successMsgText) {
		waitForElementVisible(driver, CompletePageUI.DYNAMIC_SUCCESS_MESSAGE, successMsgText);
		return isElementDisplayed(driver, CompletePageUI.DYNAMIC_SUCCESS_MESSAGE, successMsgText);
	}

}
