package pageObjects.aspire;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {

	// cap phat viec khoi tao cho doi tuong
	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}
	public static RegisterPageObject getRegisterPage(WebDriver driver) {
		return new RegisterPageObject(driver);
	}
	public static VerifyOTPPageObject getVerifyPage(WebDriver driver) {
		return new VerifyOTPPageObject(driver);
	}
	public static OnboardingPageObject getOnBoardingPage(WebDriver driver) {
		return new OnboardingPageObject(driver);
	}
	public static PersonalDetailsPageObject getPersonalDetailsPage(WebDriver driver) {
		return new PersonalDetailsPageObject(driver);
	}
	public static CompletePageObject getcompletePage(WebDriver driver) {
		return new CompletePageObject(driver);
	}
	public static BusinessDetailsPageObject getBusinessDetailsPage(WebDriver driver) {
		return new BusinessDetailsPageObject(driver);
	}
	public static IdentityVerificationPageObject getIdentityVerificationPage(WebDriver driver) {
		return new IdentityVerificationPageObject(driver);
	}
}
