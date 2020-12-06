package pageObjects.nopcommerce;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {

	// cap phat viec khoi tao cho doi tuong
	public static HomePageObject getHomePage(WebDriver driver) {
		return new HomePageObject(driver);
	}

	public static RegisterPageObject getRegisterPage(WebDriver driver) {
		return new RegisterPageObject(driver);
	}

	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}

	public static MyAccountPageObject getMyAccountPage(WebDriver driver) {
		return new MyAccountPageObject(driver);
	}

	public static HeaderSoftwarePageObject getHeaderSoftwarePage(WebDriver driver) {
		return new HeaderSoftwarePageObject(driver);
	}

	public static ProductPageObject getProductPage(WebDriver driver) {
		return new ProductPageObject(driver);
	}

	public static ProductReviewPageObject getProductReviewPage(WebDriver driver) {
		return new ProductReviewPageObject(driver);
	}
	public static SearchPageObject getSearchPage(WebDriver driver) {
		return new SearchPageObject(driver);
	}

}
