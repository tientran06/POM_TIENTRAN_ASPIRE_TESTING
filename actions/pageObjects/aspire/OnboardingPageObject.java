package pageObjects.aspire;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.aspire.OnboardingPageUI;

public class OnboardingPageObject extends AbstractPage {
	WebDriver driver;

	public OnboardingPageObject(WebDriver _driver) {
		driver = _driver;
	}

	public void clickToSubButtonBySelection(String textValue) {
		waitForElementClickable(driver, OnboardingPageUI.DYNAMIC_SUB_CONTINUE_BUTTON, textValue);
		sleepInSecond(driver, 1);
		clickToElement(driver, OnboardingPageUI.DYNAMIC_SUB_CONTINUE_BUTTON, textValue);
	}

	public void clickToSubMethodButtonBySelection(String textValue) {
		waitForElementClickable(driver, OnboardingPageUI.DYNAMIC_SUB_METHOD_BUTTON, textValue);
		sleepInSecond(driver, 1);
		clickToElement(driver, OnboardingPageUI.DYNAMIC_SUB_METHOD_BUTTON, textValue);
	}

}
