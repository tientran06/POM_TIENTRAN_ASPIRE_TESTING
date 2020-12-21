package pageObjects.aspire;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.aspire.RegisterPageUI;

public class RegisterPageObject extends AbstractPage {
	WebDriver driver;

	public RegisterPageObject(WebDriver _driver) {
		driver = _driver;
	}

	public void inputToFullNameTextBox(String fullnameValue) {
		waitForElementVisible(driver, RegisterPageUI.FULL_NAME_TEXTBOX);
		sendKeyToElement(driver, RegisterPageUI.FULL_NAME_TEXTBOX, fullnameValue);
	}

	public void inputToEmailTextBox(String emailValue) {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
		sendKeyToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, emailValue);

	}

	public void inputToPhoneNumberTextbox(String phoneNumberValue) {
		waitForElementVisible(driver, RegisterPageUI.PHONE_TEXTBOX);
		sendKeyToElement(driver, RegisterPageUI.PHONE_TEXTBOX, phoneNumberValue);

	}

	public void selectRoleOfCompanyRadioButton(String textValue) {
		waitForElementClickable(driver, RegisterPageUI.DYNAMIC_ROLE_RADIO_BUTTON, textValue);
		clickToElement(driver, RegisterPageUI.DYNAMIC_ROLE_RADIO_BUTTON, textValue);

	}

	public void inputReferralTextBox(String referralValue) {
		waitForElementVisible(driver, RegisterPageUI.REFERRAL_CODE);
		sendKeyToElement(driver, RegisterPageUI.REFERRAL_CODE, referralValue);

	}

	public void checkOnAgreeCheckBox() {
		waitForElementVisible(driver, RegisterPageUI.AGREE_CHECK_BOX);
		checkTheCheckbox(driver, RegisterPageUI.AGREE_CHECK_BOX);

	}

}
