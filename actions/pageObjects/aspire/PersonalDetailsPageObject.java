package pageObjects.aspire;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.aspire.AbstractPagePageUI;
import pageUIs.aspire.PersonalDetailsPageUI;

public class PersonalDetailsPageObject extends AbstractPage {
	WebDriver driver;

	public PersonalDetailsPageObject(WebDriver _driver) {
		driver = _driver;
	}

	public String getPersonalInformationByText(String textValue) {
		waitForElementVisible(driver, PersonalDetailsPageUI.PERSONAL_INFOR_TEXTBOX, textValue);
		return getTextElement(driver, PersonalDetailsPageUI.PERSONAL_INFOR_TEXTBOX, textValue);
	}

	public void selectDateOfBirthByText(String string, String string2, String string3) {
		// TODO Auto-generated method stub

	}

	public String getPersonalEmail(String attributeValue) {
		waitForElementVisible(driver, PersonalDetailsPageUI.PERSONAL_EMAIL);
		return getAttributeValue(driver, PersonalDetailsPageUI.PERSONAL_EMAIL, attributeValue);
	}

}