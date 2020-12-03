package com.nopcommerce.myaccount;

import org.testng.annotations.Test;

import com.nopcommerce.common.Common_01_RegisterUser;

import commons.AbstractTest;
import pageObjects.nopcommerce.HeaderSoftwarePageObject;
import pageObjects.nopcommerce.HomePageObject;
import pageObjects.nopcommerce.LoginPageObject;
import pageObjects.nopcommerce.MyAccountPageObject;
import pageObjects.nopcommerce.PageGeneratorManager;
import pageObjects.nopcommerce.ProductPageObject;
import pageObjects.nopcommerce.ProductReviewPageObject;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class MyAccount_01_EditAccount extends AbstractTest {

	private WebDriver driver;
	private String email, password;
	private String editFirstName, editLastName, editDay, editMonth, editYear, editCompany, editEmail, addFirstName, addLastName, addEmail, addCompany, addCountry, addState, addCity, addAddress1, addAddress2, addZipCode, addPhone, addFax, newPassword;
	private String productReview, titleReview, contentsReview;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(@Optional("chrome") String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getHomePage(driver);

		email = Common_01_RegisterUser.email;
		password = Common_01_RegisterUser.password;
		newPassword = "454546";

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

		productReview = "Adobe Photoshop CS4";
		titleReview = "Review about Product";
		contentsReview = "This product is good to use. I will leave more information after using it";

		log.info("Pre-conditions: Login to the System and go to My Account page");

		homePage.clickToHeaderLinkByName(driver, "Log in");
		loginPage = PageGeneratorManager.getLoginPage(driver);
		loginPage.inputToEmailTextbox(email);
		loginPage.inputToPasswordTextbox(password);
		loginPage.clickToLoginButton();

		homePage = PageGeneratorManager.getHomePage(driver);
	}

	@BeforeMethod
	public void beforeMethod() {
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

	@Test(dependsOnMethods = "TC_01_Update_Customer_Infor")
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
		verifyTrue(myAccountPage.isAddressAccountInfoDisplayedByClass("email", addEmail));
		verifyTrue(myAccountPage.isAddressAccountInfoDisplayedByClass("phone", addPhone));
		verifyTrue(myAccountPage.isAddressAccountInfoDisplayedByClass("fax", addFax));

		verifyEquals(myAccountPage.getAddressAccountInfoByClass("company"), addCompany);
		verifyEquals(myAccountPage.getAddressAccountInfoByClass("address1"), addAddress1);
		verifyEquals(myAccountPage.getAddressAccountInfoByClass("address2"), addAddress2);
		verifyTrue(myAccountPage.getAddressAccountInfoByClass("city-state-zip").contains(addCity));
		verifyTrue(myAccountPage.getAddressAccountInfoByClass("city-state-zip").contains(addZipCode));
		verifyEquals(myAccountPage.getAddressAccountInfoByClass("country"), addCountry);
	}

	@Test(dependsOnMethods = "TC_02_Add_Address")
	public void TC_03_Change_Password() {
		log.info("TC_03_Change_Password - Step 01: Click to 'Change password' List menu");
		myAccountPage.clickToListBoxMenuByName(driver, "Change password");

		log.info("TC_03_Change_Password - Step 02: Input old password and new password info");
		myAccountPage.inputToTextBoxByID(driver, "OldPassword", password);
		myAccountPage.inputToTextBoxByID(driver, "NewPassword", newPassword);
		myAccountPage.inputToTextBoxByID(driver, "ConfirmNewPassword", newPassword);

		log.info("TC_03_Change_Password - Step 03: Click to 'Change password' button");
		myAccountPage.clickToButtonByValue(driver, "Change password");

		log.info("TC_03_Change_Password - Step 04: Verify message displayed after changing password successfully");
		verifyEquals(myAccountPage.getNopCommerceTextByClass(driver, "result"), "Password was changed");

		log.info("TC_03_Change_Password - Step 05: Click Log out button");
		myAccountPage.clickToHeaderLinkByName(driver, "Log out");
		homePage = PageGeneratorManager.getHomePage(driver);

		log.info("TC_03_Change_Password - Step 06: Log in into the system with old password and verify Error message displays");
		homePage.clickToHeaderLinkByName(driver, "Log in");
		loginPage = PageGeneratorManager.getLoginPage(driver);

		loginPage.inputToEmailTextbox(editEmail);
		loginPage.inputToPasswordTextbox(password);
		loginPage.clickToLoginButton();

		verifyTrue(loginPage.isResultErrorMsgByClassDisplayed("message-error", "Login was unsuccessful. Please correct the errors and try again."));
		verifyTrue(loginPage.isResultErrorMsgByClassDisplayed("message-error", "The credentials provided are incorrect"));

		log.info("TC_03_Change_Password - Step 07: Log in into the system with password which is change and verify log in successfully");
		loginPage.clickToHeaderLinkByName(driver, "Log in");
		loginPage.inputToEmailTextbox(editEmail);
		loginPage.inputToPasswordTextbox(newPassword);
		loginPage.clickToLoginButton();
		homePage = PageGeneratorManager.getHomePage(driver);
		verifyTrue(homePage.isMyAccountLinkDisplayed());
	}

	@Test(dependsOnMethods = "TC_03_Change_Password")
	public void TC_04_Add_Review_Product() {
		log.info("TC_04_Add_Review_Product - Step 01: Click to Sub Menu");
		myAccountPage.clickToNopCommerceSubMenuByText(driver, "Computers ", "Software ");
		softwarePage = PageGeneratorManager.getHeaderSoftwarePage(driver);

		log.info("TC_04_Add_Review_Product - Step 02: Click to Product link");
		softwarePage.clickToNopCommerceLinkByClass(driver, "product-item", productReview);
		productPage = PageGeneratorManager.getProductPage(driver);

		log.info("TC_04_Add_Review_Product - Step 03: Click to 'Add your review' link");
		productPage.clickToNopCommerceLinkByClass(driver, "product-review", "Add your review");
		productReviewPage = PageGeneratorManager.getProductReviewPage(driver);

		log.info("TC_04_Add_Review_Product - Step 04: Leave a review about Product");
		productReviewPage.inputToTextBoxByID(driver, "AddProductReview_Title", titleReview);
		productReviewPage.inputToTextAreaByClass(driver, "review-text", contentsReview);
		productReviewPage.clickToRadioButtonByID(driver, "addproductrating_4");
		
		log.info("TC_04_Add_Review_Product - Step 05: Click to 'Submit review' button");
		productReviewPage.clickToButtonByValue(driver, "Submit review");

		log.info("TC_04_Add_Review_Product - Step 06: Verify Submit review successfully");
		verifyEquals(productReviewPage.getNopCommerceTextByClass(driver, "result"), "Product review is successfully added.");
		
		log.info("TC_04_Add_Review_Product - Step 07: Click to 'My account' link");
		productReviewPage.clickToHeaderLinkByName(driver, "My account");
		myAccountPage = PageGeneratorManager.getMyAccountPage(driver);
		
		log.info("TC_04_Add_Review_Product - Step 08: Click to 'My product reviews' List menu");
		myAccountPage.clickToListBoxMenuByName(driver, "My product reviews");

		log.info("TC_04_Add_Review_Product - Step 09: Verify the Review is added into 'My product reviews' with detail");
		verifyTrue(myAccountPage.getReviewInfoByClass("review-title", titleReview).contains(titleReview));
		verifyTrue(myAccountPage.getReviewInfoByClass("review-text", contentsReview).contains(contentsReview));
		verifyTrue(myAccountPage.getReviewInfoByClass("review-info", productReview).contains(productReview));
		// verifyTrue(myAccountPage.getReviewInfoByClass("review-info", "Date").contains("Date"));
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

	private HomePageObject homePage;
	private LoginPageObject loginPage;
	private MyAccountPageObject myAccountPage;
	private HeaderSoftwarePageObject softwarePage;
	private ProductPageObject productPage;
	private ProductReviewPageObject productReviewPage;

}
