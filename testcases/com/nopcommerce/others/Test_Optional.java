package com.nopcommerce.others;

import commons.AbstractTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Test_Optional extends AbstractTest {
	WebDriver driver;

	@Parameters("browser")
	@BeforeTest
	public void beforeTest(@Optional("chrome") String browserName) {
		System.out.println(" driver = ------------------" + driver);
		driver = getBrowserDriver(browserName);
		System.out.println(" driver after = ------------------" + driver);
	}

	@Test
	public void f() {
		System.out.println("Test case nef :----------------------------");
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
