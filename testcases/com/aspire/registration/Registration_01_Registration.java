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
		log.info("TC_01_RegisterToSystem - Step 01: Click to 'Register' link");
		loginPage.clickToRegisterLink();
		registerPage = PageGeneratorManager.getRegisterPage(driver);

		log.info("TC_01_RegisterToSystem - Step 02: Input / Select required fields");
		registerPage.inputToFullNameTextBox(fullName);
		registerPage.inputToEmailTextBox(email);
		registerPage.selectAspireCustomDropdownListByLabel(driver, "Phone number", countryValue);
		registerPage.inputToPhoneNumberTextbox(phoneNumber);
		registerPage.selectRoleOfCompanyRadioButton(roleOfCompany);
		registerPage.selectAspireCustomDropdownListByLabel(driver, "Where did you hear about us?", sourceRefer);
		registerPage.inputReferralTextBox(referralCode);
		registerPage.checkOnAgreeCheckBox();
		
		log.info("TC_01_RegisterToSystem - Step 03: Click to 'Continue' button");
		registerPage.clickToAsPireButtonByText(driver, "Continue");
		verifyOTPPage = PageGeneratorManager.getVerifyPage(driver);
		
		log.info("TC_01_RegisterToSystem - Step 04: Verify 'Verification OTP' informaiton displays");
		verifyTrue(verifyOTPPage.isValidationOTPInformationDisplayed("Please enter the 4-digit OTP sent to"));
	}

	@Test(dependsOnMethods = "TC_01_RegisterToSystem")
	public void TC_02_MobileVerificationOTP() {
		log.info("TC_02_MobileVerificationOTP - Step 01: Input OTP code");
		verifyOTPPage.inputToOTPTextBox("class", OTPCode);
		completePage = PageGeneratorManager.getcompletePage(driver);

		log.info("TC_02_MobileVerificationOTP - Step 02: Verify Success Message displays");
		verifyTrue(completePage.isSuccessMessageDisplayedByText("You have successfully verified your phone number. You’re on to a great start!"));
		
		log.info("TC_02_MobileVerificationOTP - Step 03: Click to 'Continue' button");
		verifyOTPPage.clickToAsPireButtonByText(driver, "Continue");
		onBoardingPage = PageGeneratorManager.getOnBoardingPage(driver);
	}

	@Test(dependsOnMethods = "TC_02_MobileVerificationOTP")
	public void TC_03_PersonnalDetailsRegistration() {
		
		log.info("TC_03_PersonnalDetailsRegistration - Step 01: Click to 'Continue' button");
		onBoardingPage.clickToSubButtonBySelection("Yes, my business is registered in Singapore with ACRA");
		
		log.info("TC_03_PersonnalDetailsRegistration - Step 02: Click to 'Get Started' button");
		onBoardingPage.clickToSubMethodButtonBySelection("Standard Registration");
		personalDetailsPage = PageGeneratorManager.getPersonalDetailsPage(driver);
		
		log.info("TC_03_PersonnalDetailsRegistration - Step 03: Verify 'Personal Details' displays");
		verifyTrue(personalDetailsPage.isAspireTitleFormDisplayedByText(driver, "Person details"));
		
		log.info("TC_03_PersonnalDetailsRegistration - Step 04: Click to 'Get Started' button");
		personalDetailsPage.clickToAsPireButtonByText(driver, "Get Started");

		log.info("TC_03_PersonnalDetailsRegistration - Step 05: Verify Personal information displays");
		verifyTrue(personalDetailsPage.getPersonalInformationByText(fullName).contains(fullName));
		verifyTrue(personalDetailsPage.getPersonalInformationByText(phoneNumber).contains(phoneNumber));
		verifyEquals(personalDetailsPage.getPersonalEmail("value"), email);

		log.info("TC_03_PersonnalDetailsRegistration - Step 06: Select Date of Birth");
		// personalDetailsPage.selectDateOfBirthByText("1987", "Jun", "14");
		sleepInSecond(driver, 6);
		
		log.info("TC_03_PersonnalDetailsRegistration - Step 07: Select 'Nationality' option");
		personalDetailsPage.selectAspireCustomDropdownListByLabel(driver, "Nationality", nationality);
		
		log.info("TC_03_PersonnalDetailsRegistration - Step 08: Select 'Gender' option");
		personalDetailsPage.selectAspireCustomDropdownListByLabel(driver, "Gender", gender);

		log.info("TC_03_PersonnalDetailsRegistration - Step 09: Select interested Products");
		personalDetailsPage.selectAspireMultiCustomDropdownListByLabel(driver, "Which products are you interested in?", checkboxItem1);
		personalDetailsPage.selectAspireMultiCustomDropdownListByLabel(driver, "Which products are you interested in?", checkboxItem2);
		
		log.info("TC_03_PersonnalDetailsRegistration - Step 10: Click 'Submit' button");
		personalDetailsPage.clickToAsPireButtonByText(driver, "Submit");
		verifyOTPPage = PageGeneratorManager.getVerifyPage(driver);
		
		log.info("TC_03_PersonnalDetailsRegistration - Step 11: Verify 'Verification OTP' informaiton displays");
		verifyTrue(verifyOTPPage.isValidationOTPInformationDisplayed("Please enter the 4-digit OTP sent to"));
		verifyTrue(verifyOTPPage.isValidationOTPInformationDisplayed(email));

	}

	@Test(dependsOnMethods = "TC_03_PersonnalDetailsRegistration")
	public void TC_04_EmailVerificationOTP() {
		log.info("TC_04_EmailVerificationOTP - Step 01: Input OTP code");
		verifyOTPPage.inputToOTPTextBox("class", OTPCode);
		businessDetailsPage = PageGeneratorManager.getBusinessDetailsPage(driver);
		
		log.info("TC_04_EmailVerificationOTP - Step 02: Verify 'Business Details' displays");
		verifyTrue(businessDetailsPage.isAspireTitleFormDisplayedByText(driver, "Business Details"));
	}
	
	@Test(dependsOnMethods = "TC_04_EmailVerificationOTP")
	public void TC_05_BusinessDetailsRegistration() {
		log.info("TC_05_BusinessDetailsRegistration - Step 01: Click to 'Get Started' button");
		businessDetailsPage.clickToAsPireButtonByText(driver, "Get Started");
		
		log.info("TC_05_BusinessDetailsRegistration - Step 02: Input / Select required fields");
		businessDetailsPage.inputToBusinessDetailTextBox("The full legal business name", businessName);
		businessDetailsPage.selectAspireCustomDropdownListByLabel(driver, "Registration Type", registrationType);
		businessDetailsPage.inputToBusinessDetailTextBox("Business Registration Number UEN", UENCode);
		businessDetailsPage.selectAspireCustomDropdownListByLabel(driver, "Industry", industry);
		businessDetailsPage.selectAspireCustomDropdownListByLabel(driver, "Sub Industry", subIndustry);
		
		log.info("TC_05_BusinessDetailsRegistration - Step 03: Click 'Submit' button");
		businessDetailsPage.clickToAsPireButtonByText(driver, "Submit");

		log.info("TC_05_BusinessDetailsRegistration - Step 04: Verify 'Identity Verification' displays ");
		verifyTrue(businessDetailsPage.isAspireTitleFormDisplayedByText(driver, "Identity Verification"));
		verifyTrue(businessDetailsPage.isAspireButtonDisplayedByText(driver, "Get Started"));
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

}
