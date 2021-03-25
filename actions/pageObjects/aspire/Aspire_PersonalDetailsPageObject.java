package pageObjects.aspire;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.AbstractPage;
import commons.GlobalConstants;
import pageUIs.aspire.PersonalDetailsPageUI;

public class Aspire_PersonalDetailsPageObject extends AbstractPage {
	WebDriver driver;

	public Aspire_PersonalDetailsPageObject(WebDriver _driver) {
		driver = _driver;
	}

	public String getPersonalInformationByText(String textValue) {
		waitForElementVisible(driver, PersonalDetailsPageUI.PERSONAL_INFOR_TEXTBOX, textValue);
		return getTextElement(driver, PersonalDetailsPageUI.PERSONAL_INFOR_TEXTBOX, textValue);
	}

	public void selectDateOfBirthByText(String currentYear, String currentMonth, String birthYear, String birthMonth, String birthDay) {
		openCalendar();
		sleepInSecond(driver, 2);
		selectBirthDay(currentYear, currentMonth, birthYear, birthMonth, birthDay);
	}

	public String getPersonalEmail(String attributeValue) {
		waitForElementVisible(driver, PersonalDetailsPageUI.PERSONAL_EMAIL);
		return getAttributeValue(driver, PersonalDetailsPageUI.PERSONAL_EMAIL, attributeValue);
	}

	private void openCalendar() {
		waitForElementClickable(driver, PersonalDetailsPageUI.OPEN_CALENDAR_BUTTON);
		if (isCalendarNotOpen()) {
			clickToElement(driver, PersonalDetailsPageUI.OPEN_CALENDAR_BUTTON);
		}
	}

	private boolean isCalendarNotOpen() {
		waitForElementInvisible(driver, PersonalDetailsPageUI.CALENDAR);
		return isElementUnDisplayed(driver, PersonalDetailsPageUI.CALENDAR);
	}

	private void selectBirthDay(String currentYear, String currentMonth, String birthYear, String birthMonth, String birthDay) {
		By leftArrow = byXpathLocator(PersonalDetailsPageUI.LEFT_ARROW);
		By monthButton = byXpathLocator(PersonalDetailsPageUI.DYNAMIC_BUTTON, currentMonth);
		By yearButton = byXpathLocator(PersonalDetailsPageUI.DYNAMIC_BUTTON, currentYear);
		String yearXpath, monthXpath, dayXpath;
		List<WebElement> itemList, yearItemLst;
		WebElement element;

		waitForElementClickable(driver, PersonalDetailsPageUI.DYNAMIC_BUTTON, currentYear);
		clickToElement(driver, yearButton);

		element = driver.findElement(leftArrow);

		driver.manage().timeouts().implicitlyWait(GlobalConstants.SHORT_TIMEOUT, TimeUnit.SECONDS);
		yearXpath = String.format(PersonalDetailsPageUI.DYNAMIC_ITEM_LIST, birthYear);
		
		while (true) {
			yearItemLst = driver.findElements(By.xpath(yearXpath));
			
			if (yearItemLst.size() > 0) {
				yearItemLst.get(0).click();
				break;
			} else {
				waitForElementPresence(driver, PersonalDetailsPageUI.LEFT_ARROW);
				element = driver.findElement(leftArrow);
				element.click();
			}
		}
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);

		sleepInSecond(driver, 5);
		waitForElementClickable(driver, PersonalDetailsPageUI.DYNAMIC_BUTTON, currentMonth);
		driver.findElement(monthButton).click();

		monthXpath = String.format(PersonalDetailsPageUI.DYNAMIC_ITEM_LIST, birthMonth);
		
		itemList = driver.findElements(By.xpath(monthXpath));
		waitForElementPresence(driver, PersonalDetailsPageUI.DYNAMIC_ITEM_LIST,birthMonth);

		while (true) {
			itemList = driver.findElements(By.xpath(monthXpath));
			
			if (itemList.size() > 0) {
				itemList.get(0).click();
				break;
			} else {
				break;
			}
		}
		sleepInSecond(driver, 5);
		
		dayXpath = String.format(PersonalDetailsPageUI.DYNAMIC_ITEM_LIST, birthDay);
		itemList = driver.findElements(By.xpath(dayXpath));
		waitForElementPresence(driver, PersonalDetailsPageUI.DYNAMIC_ITEM_LIST, birthDay);

		while (true) {
			itemList = driver.findElements(By.xpath(dayXpath));
			
			if (itemList.size() > 0) {
				itemList.get(0).click();
				break;
			} else {
				break;
			}
		}
		sleepInSecond(driver, 5);
	}

}