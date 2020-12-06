package com.nopcommerce.addto;

import org.testng.annotations.Test;

import com.nopcommerce.common.Common_01_RegisterUser;

import commons.AbstractTest;
import pageObjects.nopcommerce.HomePageObject;
import pageObjects.nopcommerce.LoginPageObject;
import pageObjects.nopcommerce.PageGeneratorManager;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class AddTo_01_AddTo extends AbstractTest {

	private WebDriver driver;
	private String email, password;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(@Optional("chrome") String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getHomePage(driver);

		email = Common_01_RegisterUser.email;
		password = Common_01_RegisterUser.password;

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
	}

	@Test()
	public void TC_01_AddProdcutToCart() {

	}

	@Test()
	public void TC_02_EditProductInShoppingCart() {

	}

	@Test()
	public void TC_03_RemoveFromCart() {

	}

	@Test()
	public void TC_04_UpdateShoppingCart() {

	}

	@Test()
	public void TC_05_CheckOutOrder() {

	}

	@Test()
	public void TC_06_CheckOutOrder() {

	}

	@Test()
	public void TC_07_ReOrder() {

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

	private HomePageObject homePage;
	private LoginPageObject loginPage;

}
