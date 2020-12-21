package pageObjects.aspire;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.aspire.VerifyOTPPageUI;

public class IdentityVerificationPageObject extends AbstractPage {
	WebDriver driver;

	public IdentityVerificationPageObject(WebDriver _driver) {
		driver = _driver;
	}

	public boolean isValidationOTPInformationDisplayed(String textValue) {
		waitForElementVisible(driver, VerifyOTPPageUI.VALIDATION_OTP_INFOR, textValue);
		return isElementDisplayed(driver, VerifyOTPPageUI.VALIDATION_OTP_INFOR, textValue);
	}

	public void inputToOTPTextBox(String attributeRemove, String otpCode) {

		waitForElementPresence(driver, VerifyOTPPageUI.OTP_TEXTBOX);
		removeAttributeInDOM(driver, VerifyOTPPageUI.OTP_TEXTBOX, attributeRemove);
		sleepInSecond(driver, 1);
		sendKeyToElement(driver, VerifyOTPPageUI.OTP_TEXTBOX, otpCode);
	}
}
