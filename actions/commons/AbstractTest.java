package commons;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.testng.Assert;
import org.testng.Reporter;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AbstractTest {
	protected final Log log;
	protected String rootFolder = System.getProperty("user.dir");
	WebDriver driver;

	public AbstractTest() {
		log = LogFactory.getLog(getClass());
	}

	protected void printBrowserConsoleLogs(WebDriver driver) {
		LogEntries logs = driver.manage().logs().get("browser");
		List<LogEntry> logList = logs.getAll();
		for (LogEntry logging : logList) {
			log.info("--------------------" + logging.getLevel().toString() + "----------------- \n"
					+ logging.getMessage());
		}
	}

	public WebDriver getBrowserDriver(String browserName) {

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();

			System.setProperty("webdriver.chrome.args", "--disable-logging");
			System.setProperty("webdriver.chrome.silentOutput", "true");
			ChromeOptions options = new ChromeOptions();

			options.addArguments("-lang=en");

			options.setExperimentalOption("useAutomationExtension", false);
			options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
			options.addArguments("--disable-notifications");
			options.addArguments("start-maximized");
			options.addArguments("disable-geolocation");
			options.addArguments("--disable-infobars");
			options.addArguments("--disable-extensions");
			options.addArguments("--incognito");

			driver = new ChromeDriver(options);

		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, rootFolder + "\\browserLogs\\Firefox.log");

			FirefoxProfile profile = new FirefoxProfile();
			File transfer = new File(rootFolder + "\\BrowserExtensions\\google_translate.xpi");
			profile.addExtension(transfer);

			FirefoxOptions options = new FirefoxOptions();

			options.addPreference("intl.accept_languages", "vi");
			options.addArguments("-private");
			driver = new FirefoxDriver(options);

		} else if (browserName.equalsIgnoreCase("headless_chrome")) {
			WebDriverManager.chromedriver().version("80.0.3987.16").setup();
			ChromeOptions options = new ChromeOptions();
			options.setHeadless(true);
			options.addArguments("window-size-1920x1080");
			driver = new ChromeDriver(options);

		} else if (browserName.equalsIgnoreCase("headless_firefox")) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			options.setHeadless(true);
			options.addArguments("window-size-1920x1080");
			driver = new FirefoxDriver(options);
		} else if (browserName.equalsIgnoreCase("edge")) {

			// Edge new version
			System.setProperty("webdriver.edge.driver", ".\\libraries\\msedgedriver.exe");
			driver = new EdgeDriver();
		}
		driver.get(GlobalConstants.DEV_URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		return driver;
	}

	private boolean checkTrue(boolean condition) {
		boolean pass = true;
		try {
			if (condition == true) {
				log.info(" -------------------------- PASSED -------------------------- ");
			} else {
				log.info(" -------------------------- FAILED -------------------------- ");
			}
			Assert.assertTrue(condition);
		} catch (Throwable e) {
			pass = false;

			// Add lỗi vào ReportNG
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}

		return pass;
	}

	protected boolean verifyTrue(boolean condition) {
		return checkTrue(condition);
	}

	private boolean checkFailed(boolean condition) {
		boolean pass = true;

		try {
			if (condition == false) {
				log.info(" -------------------------- PASSED -------------------------- ");
			} else {
				log.info(" -------------------------- FAILED -------------------------- ");
			}
			Assert.assertFalse(condition);
		} catch (Throwable e) {
			pass = false;

			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		return checkFailed(condition);
	}

	private boolean checkEquals(Object actual, Object expected) {
		boolean pass = true;
		overrideTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		try {
			Assert.assertEquals(actual, expected);
			log.info(" -------------------------- PASSED -------------------------- ");
			overrideTimeout(driver, GlobalConstants.LONG_TIMEOUT);

		} catch (Throwable e) {
			pass = false;
			log.info(" -------------------------- FAILED -------------------------- ");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
			overrideTimeout(driver, GlobalConstants.LONG_TIMEOUT);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		return checkEquals(actual, expected);
	}

	protected String randomNumber8Digits() {
		return RandomStringUtils.randomNumeric(8);
	}

	protected String randomNumber4Digits() {
		return RandomStringUtils.randomNumeric(4);
	}

	protected String randomAlphabetic() {
		return RandomStringUtils.randomAlphabetic(1);
	}

	public void sleepInSecond(WebDriver driver, long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void overrideTimeout(WebDriver driver, long timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}

	protected void closeBrowserAndDriver(WebDriver driver) {
		try {
			String osName = System.getProperty("os.name").toLowerCase();
			log.info("OS Name = " + osName);

			String cmd = "";
			if (driver != null) {
				driver.manage().deleteAllCookies();
				driver.quit();
			}
			if (driver.toString().toLowerCase().contains("chrome")) {
				if (osName.toLowerCase().contains("mac")) {
					cmd = "pkill chromedriver";
				} else if (osName.toLowerCase().contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
				}
			} else if (driver.toString().toLowerCase().contains("firefox")) {
				if (osName.toLowerCase().contains("mac")) {
					cmd = "pkill geckodriver";
				} else if (osName.toLowerCase().contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
				}
			} else if (driver.toString().toLowerCase().contains("internetexplorer")) {
				if (osName.toLowerCase().contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
				}
			}
			Process process = Runtime.getRuntime().exec(cmd);
			process.waitFor();

			log.info("---------------------- QUIT BROWSER SUCCESS ----------------------");

		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}

}
