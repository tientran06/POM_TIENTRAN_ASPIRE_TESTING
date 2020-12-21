package pageObjects.aspire;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.aspire.LoginPageUI;

public class LoginPageObject extends AbstractPage {
	WebDriver driver;

	public LoginPageObject(WebDriver _driver) {
		driver = _driver;
	}

	public void clickToRegisterLink() {
		waitForElementClickable(driver, LoginPageUI.REGISTER_LINK);
		clickToElement(driver, LoginPageUI.REGISTER_LINK);
	}
}
