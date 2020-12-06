package com.nopcommerce.search;

import org.testng.annotations.Test;

import com.nopcommerce.common.Common_01_RegisterUser;

import commons.AbstractTest;
import pageObjects.nopcommerce.HomePageObject;
import pageObjects.nopcommerce.LoginPageObject;
import pageObjects.nopcommerce.PageGeneratorManager;
import pageObjects.nopcommerce.SearchPageObject;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class Search_01_SearchData extends AbstractTest {

	private WebDriver driver;
	private String email, password;
	private String comparativeProduct, productName, productName1, category, incorrectManufacturer, correctManufacturer;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(@Optional("chrome") String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getHomePage(driver);

		email = Common_01_RegisterUser.email;
		password = Common_01_RegisterUser.password;

		comparativeProduct = "Lenovo";
		productName = "Thinkpad X1 Carbon";
		productName1 = "Apple MacBook Pro";
		category = "Computers";
		incorrectManufacturer = "HP";
		correctManufacturer = "Apple";

		log.info("Pre-conditions: Login to the System and go to My Account page");

		homePage.clickToNopCommerceHeaderLinkByName(driver, "Log in");
		loginPage = PageGeneratorManager.getLoginPage(driver);
		loginPage.inputToEmailTextbox(email);
		loginPage.inputToPasswordTextbox(password);
		loginPage.clickToLoginButton();

		homePage = PageGeneratorManager.getHomePage(driver);
		verifyTrue(homePage.isMyAccountLinkDisplayed());
	}

	@BeforeMethod
	public void beforeMethod() {
		homePage.clickToNopCommerceFooterLinkByName(driver, "Search");
		searchPage = PageGeneratorManager.getSearchPage(driver);
	}

	@Test()
	public void TC_01_SearchWithEmptyData() {
		log.info("TC_01_SearchWithEmptyData - Step 01: Input Search text with empty data");
		searchPage.inputToNopCommerceTextBoxByID(driver, "q", "");

		log.info("TC_01_SearchWithEmptyData - Step 02: Click Search button");
		searchPage.clickToNopCommerceButtonByValue(driver, "Search");

		log.info("TC_01_SearchWithEmptyData - Step 03: Verify Error message displays");
		verifyEquals(searchPage.getNopCommerceTextByClass(driver, "warning"), "Search term minimum length is 3 characters");

	}

	@Test()
	public void TC_02_SearchWithNotExistData() {
		log.info("TC_02_SearchWithNotExistData - Step 01: Input Search text with not exist data");
		searchPage.inputToNopCommerceTextBoxByID(driver, "q", "Macbook Pro 2050");

		log.info("TC_02_SearchWithNotExistData - Step 02: Click Search button");
		searchPage.clickToNopCommerceButtonByValue(driver, "Search");

		log.info("TC_02_SearchWithNotExistData - Step 03: Verify displayed result - No result");
		verifyEquals(searchPage.getNopCommerceTextByClass(driver, "no-result"), "No products were found that matched your criteria.");

	}

	@Test()
	public void TC_03_SearchWithProductNameRelative() {
		log.info("TC_03_SearchWithProductNameRelative - Step 01: Input Search text with comparative Product Name");
		searchPage.inputToNopCommerceTextBoxByID(driver, "q", comparativeProduct);

		log.info("TC_03_SearchWithProductNameRelative - Step 02: Click Search button");
		searchPage.clickToNopCommerceButtonByValue(driver, "Search");

		log.info("TC_03_SearchWithProductNameRelative - Step 03: Verify displayed result - 2 related Products");
		log.info("Total product is found: ---------------" + searchPage.getNopCommerceQtyProductByText(driver, "Lenovo"));
		verifyEquals(searchPage.getNopCommerceQtyProductByText(driver, comparativeProduct), "2");

	}

	@Test()
	public void TC_04_SearchWithProductNameAbsolutely() {
		log.info("TC_04_SearchWithProductNameAbsolutely - Step 01: Input Search text with absolute Product Name");
		searchPage.inputToNopCommerceTextBoxByID(driver, "q", productName);

		log.info("TC_04_SearchWithProductNameAbsolutely - Step 02: Click Search button");
		searchPage.clickToNopCommerceButtonByValue(driver, "Search");

		log.info("TC_04_SearchWithProductNameAbsolutely - Step 03: Verify displayed result - 1 Product");
		log.info("Total product is found: ---------------" + searchPage.getNopCommerceQtyProductByText(driver, productName));
		verifyEquals(searchPage.getNopCommerceQtyProductByText(driver, productName), "1");
	}

	@Test()
	public void TC_05_AdvancedSearchWithParentCategories() {

		log.info("TC_05_AdvancedSearchWithParentCategories - Step 01: Input Search text with Product Name");
		searchPage.inputToNopCommerceTextBoxByID(driver, "q", productName1);

		log.info("TC_05_AdvancedSearchWithParentCategories - Step 02: Check on Advanced Search and select option Category");
		searchPage.checkOnNopCommerceCheckboxByID(driver, "adv");
		searchPage.selectNopCommerceDropdownListByName(driver, "cid", category);

		log.info("TC_05_AdvancedSearchWithParentCategories - Step 03: Click Search button");
		searchPage.clickToNopCommerceButtonByValue(driver, "Search");

		log.info("TC_05_AdvancedSearchWithParentCategories - Step 04: Verify displayed result - No result");
		verifyEquals(searchPage.getNopCommerceTextByClass(driver, "no-result"), "No products were found that matched your criteria.");
	}

	@Test()
	public void TC_06_AdvancedSearchWithSubCategories() {
		log.info("TC_06_AdvancedSearchWithSubCategories - Step 01: Input Search text with Product Name");
		searchPage.inputToNopCommerceTextBoxByID(driver, "q", productName1);

		log.info("TC_06_AdvancedSearchWithSubCategories - Step 02: Check on 'Advanced Search' and select option 'Category'");
		searchPage.checkOnNopCommerceCheckboxByID(driver, "adv");
		searchPage.selectNopCommerceDropdownListByName(driver, "cid", category);

		log.info("TC_06_AdvancedSearchWithSubCategories - Step 03: Check on 'Automatically search sub categories'");
		searchPage.checkOnNopCommerceCheckboxByID(driver, "isc");

		log.info("TC_06_AdvancedSearchWithSubCategories - Step 04: Click Search button");
		searchPage.clickToNopCommerceButtonByValue(driver, "Search");

		log.info("TC_06_AdvancedSearchWithSubCategories - Step 05: Verify displayed result - 1 Product");
		log.info("Total product is found: ---------------" + searchPage.getNopCommerceQtyProductByText(driver, productName1));
		verifyEquals(searchPage.getNopCommerceQtyProductByText(driver, productName1), "1");
	}

	@Test()
	public void TC_07_AdvancedSearchWithIncorrectManufacturer() {
		log.info("TC_07_AdvancedSearchWithIncorrectManufacturer - Step 01: Input Search text with Product Name");
		searchPage.inputToNopCommerceTextBoxByID(driver, "q", productName1);

		log.info("TC_07_AdvancedSearchWithIncorrectManufacturer - Step 02: Check on 'Advanced Search' and select option 'Category'");
		searchPage.checkOnNopCommerceCheckboxByID(driver, "adv");
		searchPage.selectNopCommerceDropdownListByName(driver, "cid", category);

		log.info("TC_07_AdvancedSearchWithIncorrectManufacturer - Step 03: Check on 'Automatically search sub categories'");
		searchPage.checkOnNopCommerceCheckboxByID(driver, "isc");

		log.info("TC_07_AdvancedSearchWithIncorrectManufacturer - Step 04: Select incorrect 'Manufacturer'");
		searchPage.selectNopCommerceDropdownListByName(driver, "mid", incorrectManufacturer);

		log.info("TC_07_AdvancedSearchWithIncorrectManufacturer - Step 05: Click Search button");
		searchPage.clickToNopCommerceButtonByValue(driver, "Search");

		log.info("TC_07_AdvancedSearchWithIncorrectManufacturer - Step 06: Verify displayed result - No result");
		verifyEquals(searchPage.getNopCommerceTextByClass(driver, "no-result"), "No products were found that matched your criteria.");
	}

	@Test()
	public void TC_08_AdvancedSearchWithCorrectManufacturer() {
		log.info("TC_08_AdvancedSearchWithCorrectManufacturer - Step 01: Input Search text with Product Name");
		searchPage.inputToNopCommerceTextBoxByID(driver, "q", productName1);

		log.info("TC_08_AdvancedSearchWithCorrectManufacturer - Step 02: Check on 'Advanced Search' and select option 'Category'");
		searchPage.checkOnNopCommerceCheckboxByID(driver, "adv");
		searchPage.selectNopCommerceDropdownListByName(driver, "cid", category);

		log.info("TC_08_AdvancedSearchWithCorrectManufacturer - Step 03: Check on 'Automatically search sub categories'");
		searchPage.checkOnNopCommerceCheckboxByID(driver, "isc");

		log.info("TC_08_AdvancedSearchWithCorrectManufacturer - Step 04: Select incorrect 'Manufacturer'");
		searchPage.selectNopCommerceDropdownListByName(driver, "mid", correctManufacturer);

		log.info("TC_08_AdvancedSearchWithCorrectManufacturer - Step 05: Click Search button");
		searchPage.clickToNopCommerceButtonByValue(driver, "Search");

		log.info("TC_08_AdvancedSearchWithCorrectManufacturer - Step 06: Verify displayed result - 1 Product");
		log.info("Total product is found: ---------------" + searchPage.getNopCommerceQtyProductByText(driver, productName1));
		verifyEquals(searchPage.getNopCommerceQtyProductByText(driver, productName1), "1");
	}

	@Test(description = "Advanced Search in Price Range")
	public void TC_09_AdvancedSearchInPriceRange() {
		log.info("TC_09_AdvancedSearchInPriceRange - Step 01: Input Search text with Product Name");
		searchPage.inputToNopCommerceTextBoxByID(driver, "q", productName1);

		log.info("TC_09_AdvancedSearchInPriceRange - Step 02: Check on 'Advanced Search' and select option 'Category'");
		searchPage.checkOnNopCommerceCheckboxByID(driver, "adv");
		searchPage.selectNopCommerceDropdownListByName(driver, "cid", category);

		log.info("TC_09_AdvancedSearchInPriceRange - Step 03: Check on 'Automatically search sub categories'");
		searchPage.checkOnNopCommerceCheckboxByID(driver, "isc");

		log.info("TC_09_AdvancedSearchInPriceRange - Step 04: Select incorrect 'Manufacturer'");
		searchPage.selectNopCommerceDropdownListByName(driver, "mid", correctManufacturer);
		
		log.info("TC_09_AdvancedSearchInPriceRange - Step 05: Input Price in range value");
		searchPage.inputToNopCommerceTextBoxByID(driver, "pf", "1000");
		searchPage.inputToNopCommerceTextBoxByID(driver, "pt", "2000");

		log.info("TC_09_AdvancedSearchInPriceRange - Step 06: Click Search button");
		searchPage.clickToNopCommerceButtonByValue(driver, "Search");

		log.info("TC_09_AdvancedSearchInPriceRange - Step 07: Verify displayed result - 1 Product");
		log.info("Total product is found: ---------------" + searchPage.getNopCommerceQtyProductByText(driver, productName1));
		verifyEquals(searchPage.getNopCommerceQtyProductByText(driver, productName1), "1");
	}

	@Test(description = "Advanced Search with Price Range less than Product Price")
	public void TC_10_AdvancedSearchWithPriceRangeLessThanProductPrice() {
		log.info("TC_10_AdvancedSearchWithPriceRangeLessThanProductPrice - Step 01: Input Search text with Product Name");
		searchPage.inputToNopCommerceTextBoxByID(driver, "q", productName1);

		log.info("TC_10_AdvancedSearchWithPriceRangeLessThanProductPrice - Step 02: Check on 'Advanced Search' and select option 'Category'");
		searchPage.checkOnNopCommerceCheckboxByID(driver, "adv");
		searchPage.selectNopCommerceDropdownListByName(driver, "cid", category);

		log.info("TC_10_AdvancedSearchWithPriceRangeLessThanProductPrice - Step 03: Check on 'Automatically search sub categories'");
		searchPage.checkOnNopCommerceCheckboxByID(driver, "isc");

		log.info("TC_10_AdvancedSearchWithPriceRangeLessThanProductPrice - Step 04: Select incorrect 'Manufacturer'");
		searchPage.selectNopCommerceDropdownListByName(driver, "mid", correctManufacturer);
		
		log.info("TC_10_AdvancedSearchWithPriceRangeLessThanProductPrice - Step 05: Input Price range less than Product price");
		searchPage.inputToNopCommerceTextBoxByID(driver, "pf", "1000");
		searchPage.inputToNopCommerceTextBoxByID(driver, "pt", "1700");

		log.info("TC_10_AdvancedSearchWithPriceRangeLessThanProductPrice - Step 06: Click Search button");
		searchPage.clickToNopCommerceButtonByValue(driver, "Search");

		log.info("TC_10_AdvancedSearchWithPriceRangeLessThanProductPrice - Step 07: Verify displayed result - No Product");
		verifyEquals(searchPage.getNopCommerceTextByClass(driver, "no-result"), "No products were found that matched your criteria.");
	}

	@Test(description = "Advanced Search with Price Range more than Product Price")
	public void TC_11_AdvancedSearchWithPriceRangeMoreThanProductPrice() {
		log.info("TC_11_AdvancedSearchWithPriceRangeMoreThanProductPrice - Step 01: Input Search text with Product Name");
		searchPage.inputToNopCommerceTextBoxByID(driver, "q", productName1);

		log.info("TC_11_AdvancedSearchWithPriceRangeMoreThanProductPrice - Step 02: Check on 'Advanced Search' and select option 'Category'");
		searchPage.checkOnNopCommerceCheckboxByID(driver, "adv");
		searchPage.selectNopCommerceDropdownListByName(driver, "cid", category);

		log.info("TC_11_AdvancedSearchWithPriceRangeMoreThanProductPrice - Step 03: Check on 'Automatically search sub categories'");
		searchPage.checkOnNopCommerceCheckboxByID(driver, "isc");

		log.info("TC_11_AdvancedSearchWithPriceRangeMoreThanProductPrice - Step 04: Select incorrect 'Manufacturer'");
		searchPage.selectNopCommerceDropdownListByName(driver, "mid", correctManufacturer);
		
		log.info("TC_11_AdvancedSearchWithPriceRangeMoreThanProductPrice - Step 05: Input Price range less than Product price");
		searchPage.inputToNopCommerceTextBoxByID(driver, "pf", "1900");
		searchPage.inputToNopCommerceTextBoxByID(driver, "pt", "5000");

		log.info("TC_11_AdvancedSearchWithPriceRangeMoreThanProductPrice - Step 06: Click Search button");
		searchPage.clickToNopCommerceButtonByValue(driver, "Search");

		log.info("TC_11_AdvancedSearchWithPriceRangeMoreThanProductPrice - Step 07: Verify displayed result - No Product");
		verifyEquals(searchPage.getNopCommerceTextByClass(driver, "no-result"), "No products were found that matched your criteria.");
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

	private HomePageObject homePage;
	private LoginPageObject loginPage;
	private SearchPageObject searchPage;

}
