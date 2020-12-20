package com.aspire.registration;

import org.testng.annotations.Test;

import commons.AbstractTest;
import pageObjects.aspire.BusinessDetailsPageObject;
import pageObjects.aspire.CompletePageObject;
import pageObjects.aspire.LoginPageObject;
import pageObjects.aspire.OnboardingPageObject;
import pageObjects.aspire.PageGeneratorManager;
import pageObjects.aspire.PersonalDetailsPageObject;
import pageObjects.aspire.RegisterPageObject;
import pageObjects.aspire.VerifyOTPPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class Registration_01_Registration extends AbstractTest {
	private WebDriver driver;
	private LoginPageObject loginPage;
	private RegisterPageObject registerPage;
	private VerifyOTPPageObject verifyOTPPage;
	private OnboardingPageObject onBoardingPage;
	private PersonalDetailsPageObject personalDetailsPage;
	private CompletePageObject completePage;
	private BusinessDetailsPageObject businessDetailsPage;
	
	private String fullName, email, countryValue, phoneNumber, roleOfCompany, sourceRefer, referralCode;
	private String OTPCode, nationality, gender, checkboxItem1, checkboxItem2, businessName, registrationType, UENCode, industry, subIndustry;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(@Optional("chrome") String browserName) {
		driver = getBrowserDriver(browserName);
		loginPage = PageGeneratorManager.getLoginPage(driver);

		fullName = "Largent Craig";
		email = "automation" + randomNumber4Digits() + "@gmail.com";
		countryValue = "Austria (+43)";
		phoneNumber = "91234" + randomNumber4Digits();
		roleOfCompany = "Appointed director";
		sourceRefer = "Facebook";
		referralCode = "";
		OTPCode = "1234";
		nationality = "Austria";
		gender = "Female";
		
		checkboxItem1 = "Credit Line";
		checkboxItem2 = "Debit Account";
		businessName = "Automation Testing";
		registrationType = "Limited liability partnership";
		UENCode = randomNumber8Digits() + randomAlphabetic();
		industry = "Business Services";
		subIndustry = "Automotive & Cars";
	}

	@BeforeMethod
	public void beforeMethod() {
	}

	@Test
	public void TC_01_RegisterToSystem() {
		loginPage.clickToRegisterLink();
		registerPage = PageGeneratorManager.getRegisterPage(driver);

		registerPage.inputToFullNameTextBox(fullName);
		registerPage.inputToEmailTextBox(email);
		registerPage.selectAspireCustomDropdownListByLabel(driver, "Phone number", countryValue);
		registerPage.inputToPhoneNumberTextbox(phoneNumber);
		registerPage.selectRoleOfCompanyRadioButton(roleOfCompany);
		registerPage.selectAspireCustomDropdownListByLabel(driver, "Where did you hear about us?", sourceRefer);
		registerPage.inputReferralTextBox(referralCode);
		registerPage.checkOnAgreeCheckBox();
		registerPage.clickToAsPireButtonByText(driver,"Continue");
		
		verifyOTPPage = PageGeneratorManager.getVerifyPage(driver);
		verifyTrue(verifyOTPPage.isValidationOTPInformationDisplayed("Please enter the 4-digit OTP sent to"));
	}
	
	@Test(dependsOnMethods = "TC_01_RegisterToSystem")
	public void TC_02_MobileVerificationOTP() {
		verifyOTPPage.inputToOTPTextBox("class", OTPCode);
		completePage = PageGeneratorManager.getcompletePage(driver);
		
		verifyTrue(completePage.isSuccessMessageDisplayedByText("You have successfully verified your phone number. You’re on to a great start!"));
		verifyOTPPage.clickToAsPireButtonByText(driver, "Continue");
		onBoardingPage = PageGeneratorManager.getOnBoardingPage(driver);
	}
	
	@Test(dependsOnMethods = "TC_02_MobileVerificationOTP")
	public void TC_03_PersonnalDetailsRegistration() {
		onBoardingPage.clickToSubButtonByText("Yes, my business is registered in Singapore with ACRA");
		onBoardingPage.clickToSubMethodButtonByText("Standard Registration");
		
		personalDetailsPage = PageGeneratorManager.getPersonalDetailsPage(driver);
		verifyTrue(personalDetailsPage.isAspireTitleFormDisplayedByText(driver, "Person details"));
		personalDetailsPage.clickToAsPireButtonByText(driver, "Get Started");
		
		verifyTrue(personalDetailsPage.getPersonalInformationByText(fullName).contains(fullName));
		verifyTrue(personalDetailsPage.getPersonalInformationByText(phoneNumber).contains(phoneNumber));
		verifyEquals(personalDetailsPage.getPersonalEmail("value"), email);
		
		//personalDetailsPage.selectDateOfBirthByText("1987", "Jun", "14");
		sleepInSecond(driver, 6);
		personalDetailsPage.selectAspireCustomDropdownListByLabel(driver,"Nationality", nationality);
		personalDetailsPage.selectAspireCustomDropdownListByLabel(driver, "Gender", gender);
		
		personalDetailsPage.selectAspireMultiCustomDropdownListByLabel(driver, "Which products are you interested in?", checkboxItem1, checkboxItem2);
		personalDetailsPage.clickToAsPireButtonByText(driver, "Submit");
		verifyOTPPage = PageGeneratorManager.getVerifyPage(driver);
		
	}
	@Test(dependsOnMethods = "TC_03_PersonnalDetailsRegistration")
	public void TC_04_EmailVerificationOTP() {
		verifyOTPPage.inputToOTPTextBox("class", OTPCode);
		businessDetailsPage = PageGeneratorManager.getBusinessDetailsPage(driver);
		verifyTrue(businessDetailsPage.isAspireTitleFormDisplayedByText(driver, "Business Details"));
		
		businessDetailsPage.clickToAsPireButtonByText(driver,"Get Started");
		businessDetailsPage.inputToBusinessNameTextbox(businessName);
		businessDetailsPage.selectAspireCustomDropdownListByLabel(driver, "Registration Type", registrationType);
		businessDetailsPage.inputToBusinessRegistrationNoTextBox(UENCode);
		businessDetailsPage.selectAspireCustomDropdownListByLabel(driver, "Industry", industry);
		businessDetailsPage.selectAspireCustomDropdownListByLabel(driver, "Sub Industry", subIndustry);
		businessDetailsPage.clickToAsPireButtonByText(driver, "Submit");
		
		verifyTrue(businessDetailsPage.isAspireTitleFormDisplayedByText(driver, "Identity Verification"));
		verifyTrue(businessDetailsPage.isAspireButtonDisplayedByText(driver, "Get Started"));
	}
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

}
