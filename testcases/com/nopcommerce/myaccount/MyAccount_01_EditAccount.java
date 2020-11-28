package com.nopcommerce.myaccount;

import org.testng.annotations.Test;

import com.nopcommerce.common.Common_01_RegisterUser;

import commons.AbstractTest;
import pageObjects.nopcommerce.HomePageObject;
import pageObjects.nopcommerce.LoginPageObject;
import pageObjects.nopcommerce.MyAccountPageObject;
import pageObjects.nopcommerce.PageGeneratorManager;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;

public class MyAccount_01_EditAccount extends AbstractTest {

	private WebDriver driver;
	private String email, password;
	private String editFirstName, editLastName, editDay, editMonth, editYear, editCompany, editEmail, addFirstName, addLastName, addEmail, addCompany, addCountry, addState, addCity, addAddress1, addAddress2, addZipCode, addPhone, addFax;

	@Parameters({ "browser" })
	@BeforeTest
	public void beforeTest(@Optional("chrome") String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getHomePage(driver);

		email = Common_01_RegisterUser.email;
		password = Common_01_RegisterUser.password;

		editFirstName = "Automation";
		editLastName = "Selenium";
		editEmail = "Seleniumfc" + randomNumber() + "@gmail.com";
		editCompany = "Automation Selenium";
		editDay = "25";
		editMonth = "May";
		editYear = "1990";
		
		addFirstName = "Selenium";
		addLastName = "Automation";
		addEmail = "automation_fc" + randomNumber() + "@hot.com";
		addCompany = "Cyberlogitec";
		addCountry = "Viet Nam";
		addState = "Other";
		addCity = "Ho Chi Minh";
		addAddress1 = "123 Nguyen Duc Thuan";
		addAddress2 = "12 Ngo Be";
		addZipCode = "70000";
		addPhone = "09754382222";
		addFax = "028433335444";

		log.info("Pre-conditions: Login to the System and go to My Account page");

		homePage.clickToHeaderLinkByName(driver, "Log in");
		loginPage = PageGeneratorManager.getLoginPage(driver);
		loginPage.inputToEmailTextbox(email);
		loginPage.inputToPasswordTextbox(password);
		loginPage.clickToLoginButton();

		homePage = PageGeneratorManager.getHomePage(driver);
		homePage.clickToHeaderLinkByName(driver, "My account");
		myAccountPage = PageGeneratorManager.getMyAccountPage(driver);

	}

	@Test
	public void TC_01_Update_Customer_Infor() {
		log.info("TC_01_Update_Customer_Infor - Step 01: Edit data");
		myAccountPage.clickToListBoxMenuByName(driver, "Customer info");
		myAccountPage.clickToRadioButtonByID(driver, "gender-female");
		myAccountPage.inputToTextBoxByID(driver, "FirstName", editFirstName);
		myAccountPage.inputToTextBoxByID(driver, "LastName", editLastName);
		myAccountPage.selectDropdownListByName(driver, "DateOfBirthDay", editDay);
		myAccountPage.selectDropdownListByName(driver, "DateOfBirthMonth", editMonth);
		myAccountPage.selectDropdownListByName(driver, "DateOfBirthYear", editYear);
		myAccountPage.inputToTextBoxByID(driver, "Email", editEmail);
		myAccountPage.inputToTextBoxByID(driver, "Company", editCompany);

		log.info("TC_01_Update_Customer_Infor - Step 02: Click Save button");
		myAccountPage.clickToButtonByValue(driver, "Save");

		log.info("TC_01_Update_Customer_Infor - Step 03: Verify result after editing data");
		verifyTrue(myAccountPage.isRadioButtonSelectedByID(driver, "gender-female"));
		verifyEquals(myAccountPage.getNopCommerceAttributeValueByID(driver, "FirstName"), editFirstName);
		verifyEquals(myAccountPage.getNopCommerceAttributeValueByID(driver, "LastName"), editLastName);

		verifyTrue(myAccountPage.isDropdownListSelectedByText(driver, "DateOfBirthDay", editDay));
		verifyTrue(myAccountPage.isDropdownListSelectedByText(driver, "DateOfBirthMonth", editMonth));
		verifyTrue(myAccountPage.isDropdownListSelectedByText(driver, "DateOfBirthYear", editYear));

		verifyEquals(myAccountPage.getNopCommerceAttributeValueByID(driver, "Email"), editEmail);
		verifyEquals(myAccountPage.getNopCommerceAttributeValueByID(driver, "Company"), editCompany);
	}

	@Test()
	public void TC_02_Add_Address() {
		log.info("TC_02_Add_Address - Step 01: Click to 'Addresses' List menu");
		myAccountPage.clickToListBoxMenuByName(driver, "Addresses");
		
		log.info("TC_02_Add_Address - Step 02: Click to 'Add new' button and input Address infor");
		myAccountPage.clickToButtonByValue(driver, "Add new");
		myAccountPage.inputToTextBoxByID(driver, "Address_FirstName", addFirstName);
		myAccountPage.inputToTextBoxByID(driver, "Address_LastName", addLastName);
		myAccountPage.inputToTextBoxByID(driver, "Address_Email", addEmail);
		myAccountPage.inputToTextBoxByID(driver, "Address_Company", addCompany);

		myAccountPage.selectDropdownListByName(driver, "Address.CountryId", addCountry);
		myAccountPage.selectDropdownListByName(driver, "Address.StateProvinceId", addState);

		myAccountPage.inputToTextBoxByID(driver, "Address_City", addCity);
		myAccountPage.inputToTextBoxByID(driver, "Address_Address1", addAddress1);
		myAccountPage.inputToTextBoxByID(driver, "Address_Address2", addAddress2);
		myAccountPage.inputToTextBoxByID(driver, "Address_ZipPostalCode", addZipCode);
		myAccountPage.inputToTextBoxByID(driver, "Address_PhoneNumber", addPhone);
		myAccountPage.inputToTextBoxByID(driver, "Address_FaxNumber", addFax);

		log.info("TC_02_Add_Address - Step 03: Click Save button");
		myAccountPage.clickToButtonByValue(driver, "Save");
		
		log.info("TC_02_Add_Address - Step 04: Verify Address infor are saved successfully");
		verifyTrue(myAccountPage.isAddressAccountInfoDisplayedByClass("name", addFirstName + " " + addLastName));
		verifyTrue(myAccountPage.isAddressAccountInfoDisplayedByClass("email",addEmail));
		verifyTrue(myAccountPage.isAddressAccountInfoDisplayedByClass("phone",addPhone));
		verifyTrue(myAccountPage.isAddressAccountInfoDisplayedByClass("fax",addFax));
		
		verifyEquals(myAccountPage.getAddressAccountInfoByClass("company"), addCompany);
		verifyEquals(myAccountPage.getAddressAccountInfoByClass("address1"), addAddress1);
		verifyEquals(myAccountPage.getAddressAccountInfoByClass("address2"), addAddress2);
		verifyEquals(myAccountPage.getAddressAccountInfoByClass("city-state-zip"), addCity +", " + addZipCode);
		verifyEquals(myAccountPage.getAddressAccountInfoByClass("country"), addCountry);
	}

	@Test()
	public void TC_03_Change_Password() {

	}

	@Test()
	public void TC_04_Add_Review_Product() {

	}

	@AfterTest(alwaysRun = true)
	public void afterTest() {
		closeBrowserAndDriver(driver);
	}

	private HomePageObject homePage;
	private LoginPageObject loginPage;
	private MyAccountPageObject myAccountPage;

}
