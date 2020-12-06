package pageObjects.nopcommerce;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class SearchPageObject extends AbstractPage {
	WebDriver driver;

	public SearchPageObject(WebDriver _driver) {
		driver = _driver;
	}
}
