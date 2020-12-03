package pageObjects.nopcommerce;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class ProductPageObject extends AbstractPage {
	WebDriver driver;

	public ProductPageObject(WebDriver _driver) {
		driver = _driver;
}

}
