package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageUIs.aspire.AbstractPagePageUI;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class AbstractPage {
	private WebDriverWait waitExplicit;
	private WebElement element;
	private List<WebElement> elements;
	private By byXpath;
	private JavascriptExecutor jsExecutor;
	private Select select;
	private Actions action;

	public void openUrl(WebDriver driver, String urlValue) {
		driver.get(urlValue);
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public void back(WebDriver driver) {
		driver.navigate().back();
	}

	public void forward(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refresh(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public void cancelAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	public String getTextAlert(WebDriver driver) {
		return driver.switchTo().alert().getText();
	}

	public void sendKeyToAlert(WebDriver driver, String value) {
		driver.switchTo().alert().sendKeys(value);
	}

	public By byXpathLocator(String locator) {
		return By.xpath(locator);
	}

	public By byXpathLocator(String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		return By.xpath(locator);
	}

	public WebElement findElementByXpath(WebDriver driver, String locator) {
		return driver.findElement(byXpathLocator(locator));
	}

	public WebElement findElementByXpath(WebDriver driver, By byXpath) {
		return driver.findElement(byXpath);
	}

	public WebElement findElementByXpath(WebDriver driver, String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		return driver.findElement(byXpathLocator(locator));
	}

	public List<WebElement> findElementsByXpath(WebDriver driver, String locator) {
		return driver.findElements(byXpathLocator(locator));
	}
	public List<WebElement> findElementsByXpath(WebDriver driver, By byXpath) {
		return driver.findElements(byXpath);
	}

	public List<WebElement> findElementsByXpath(WebDriver driver, String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		return driver.findElements(byXpathLocator(locator));
	}

	public void clickToElement(WebDriver driver, String locator) {
		element = findElementByXpath(driver, locator);
		if (driver.toString().toLowerCase().contains("internetexplorer")) {
			clickToElementByJS(driver, locator);
			sleepInSecond(driver, 5);
		} else {
			element.click();
		}
	}

	public void clickToElement(WebDriver driver, By byXpath) {
		element = findElementByXpath(driver,byXpath);
		element.click();
	}

	public void clickToElement(WebDriver driver, String locator, String... values) {
		element = findElementByXpath(driver, locator, values);
		if (driver.toString().toLowerCase().contains("internetexplorer")) {
			clickToElementByJS(driver, locator);
			sleepInSecond(driver, 5);
		} else {
			element.click();
		}
	}

	public void clickToElement(WebDriver driver, By xpath, String... values) {
		element = driver.findElement(xpath);
		element.click();
	}

	public void sendKeyToElement(WebDriver driver, String locator, String inputValue) {
		element = findElementByXpath(driver, locator);
		element.clear();
		element.sendKeys(inputValue);
	}

	public void sendKeyToElement(WebDriver driver, String locator, String inputValue, String... values) {
		element = findElementByXpath(driver, locator, values);
		element.sendKeys(inputValue);
	}

	public String getAttributeValue(WebDriver driver, String locator, String attributeValue) {
		return findElementByXpath(driver, locator).getAttribute(attributeValue);
	}

	public String getAttributeValue(WebDriver driver, String locator, String attributeValue, String... values) {
		return findElementByXpath(driver, locator, values).getAttribute(attributeValue);
	}

	public String getTextElement(WebDriver driver, String locator) {
		return findElementByXpath(driver, locator).getText();
	}

	public String getTextElement(WebDriver driver, String locator, String... values) {
		return findElementByXpath(driver, locator, values).getText();
	}

	public int countElementNumber(WebDriver driver, String locator) {
		return findElementsByXpath(driver, locator).size();
	}

	public int countElementNumber(WebDriver driver, String locator, String... values) {
		return findElementsByXpath(driver, locator, values).size();
	}

	public void checkTheCheckbox(WebDriver driver, String locator) {
		element = findElementByXpath(driver, locator);
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void checkTheCheckbox(WebDriver driver, String locator, String... values) {
		element = findElementByXpath(driver, locator, values);
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void unCheckTheCheckbox(WebDriver driver, String locator) {
		element = findElementByXpath(driver, locator);
		if (element.isSelected()) {
			element.click();
		}
	}

	public void unCheckTheCheckbox(WebDriver driver, String locator, String... values) {
		element = findElementByXpath(driver, locator);
		if (element.isSelected()) {
			element.click();
		}
	}

	public boolean isElementDisplayed(WebDriver driver, String locator) {
		try {
			element = findElementByXpath(driver, locator);
			return element.isDisplayed();

		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public boolean isElementDisplayed(WebDriver driver, String locator, String... values) {
		try {
			element = findElementByXpath(driver, locator, values);
			return element.isDisplayed();

		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public boolean isElementUnDisplayed(WebDriver driver, String locator) {
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		elements = findElementsByXpath(driver, locator);
		if (elements.size() == 0) {
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			System.out.println("Element is not in DOM");
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			System.out.println("Element is in DOM but not Displayed");
			return true;
		} else {
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			System.out.println("Element is Displayed");
			return false;
		}
	}

	public boolean isElementUnDisplayed(WebDriver driver, String locator, String... values) {
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		elements = findElementsByXpath(driver, locator, values);
		if (elements.size() == 0) {
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			System.out.println("Element is not in DOM");
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			System.out.println("Element is in DOM but not Displayed");
			return true;
		} else {
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			System.out.println("Element is Displayed");
			return false;
		}
	}

	public boolean isElementSelected(WebDriver driver, String locator) {
		element = findElementByXpath(driver, locator);
		if (element.isSelected()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isElementSelected(WebDriver driver, String locator, String... values) {
		element = findElementByXpath(driver, locator, values);
		if (element.isSelected()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isElementEnabled(WebDriver driver, String locator) {
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		try {
			element = findElementByXpath(driver, locator);
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			return element.isEnabled();

		} catch (Exception ex) {
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			ex.printStackTrace();
			return false;
		}
	}

	public boolean isElementEnabled(WebDriver driver, String locator, String... values) {
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		try {
			element = findElementByXpath(driver, locator, values);
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			return element.isEnabled();

		} catch (Exception ex) {
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			ex.printStackTrace();
			return false;
		}
	}

	public boolean isDropDownMultiple(WebDriver driver, String locator) {
		element = findElementByXpath(driver, locator);
		select = new Select(element);
		if (select.isMultiple()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isDropDownMultiple(WebDriver driver, String locator, String... values) {
		element = findElementByXpath(driver, locator, values);
		select = new Select(element);
		if (select.isMultiple()) {
			return true;
		} else {
			return false;
		}
	}

	public void switchToWindowByID(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();

		for (String termWindow : allWindows) {
			if (!termWindow.equals(parentID)) {
				driver.switchTo().window(termWindow);
				break;
			}
		}

	}

	public void switchToWindowByTitle(WebDriver driver, String expectedTitle) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String termWindow : allWindows) {
			driver.switchTo().window(termWindow);
			String currentTitle = driver.getTitle();
			if (currentTitle.equals(expectedTitle)) {
				break;
			}
		}
	}

	public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String termWindow : allWindows) {
			if (!termWindow.equals(parentID)) {
				driver.close();
			}
		}
	}

	public void switchToIframe(WebDriver driver, String locator) {
		element = findElementByXpath(driver, locator);
		driver.switchTo().frame(element);
	}

	public void switchToIframe(WebDriver driver, String locator, String... values) {
		element = findElementByXpath(driver, locator, values);
		driver.switchTo().frame(element);
	}

	public void backToMainPage(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void moveToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		element = findElementByXpath(driver, locator);
		action.moveToElement(element).perform();
	}

	public void moveToElement(WebDriver driver, String locator, String... values) {
		action = new Actions(driver);
		element = findElementByXpath(driver, locator, values);
		action.moveToElement(element).perform();
	}

	public void doubleClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		element = findElementByXpath(driver, locator);
		action.doubleClick(element).perform();
	}

	public void doubleClickToElement(WebDriver driver, String locator, String... values) {
		action = new Actions(driver);
		element = findElementByXpath(driver, locator, values);
		action.doubleClick(element).perform();
	}

	public void righClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		element = findElementByXpath(driver, locator);
		action.contextClick(element).perform();
	}

	public void righClickToElement(WebDriver driver, String locator, String... values) {
		action = new Actions(driver);
		element = findElementByXpath(driver, locator, values);
		action.contextClick(element).perform();
	}

	public void dragAndDrop(WebDriver driver, String sourceLocator, String targetLocator) {
		action = new Actions(driver);
		action.dragAndDrop(findElementByXpath(driver, sourceLocator), findElementByXpath(driver, targetLocator)).perform();
	}

	public void sendKeyboardToElement(WebDriver driver, String locator, Keys key) {
		action = new Actions(driver);
		element = findElementByXpath(driver, locator);
		action.sendKeys(element, key).perform();

	}

	public void waitForElementPresence(WebDriver driver, String locator) {
		waitExplicit = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		byXpath = byXpathLocator(locator);
		try {
			waitExplicit.until(ExpectedConditions.presenceOfElementLocated(byXpath));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void waitForElementPresence(WebDriver driver, String locator, String... values) {
		waitExplicit = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		byXpath = byXpathLocator(locator, values);
		try {
			waitExplicit.until(ExpectedConditions.presenceOfElementLocated(byXpath));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void waitForElementVisible(WebDriver driver, String locator) {
		waitExplicit = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		byXpath = byXpathLocator(locator);
		try {
			System.out.println("Start time for wait visible = " + new Date());
			waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(byXpath));
			System.out.println("End time for wait visible = " + new Date());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void waitForElementVisible(WebDriver driver, String locator, String... values) {
		waitExplicit = new WebDriverWait(driver, GlobalConstants.SPECIAL_TIMEOUT);
		byXpath = byXpathLocator(locator, values);
		try {
			System.out.println("Start time for wait visible = " + new Date());
			waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(byXpath));
			System.out.println("End time for wait visible = " + new Date());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void waitForElementClickable(WebDriver driver, String locator) {
		waitExplicit = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		byXpath = byXpathLocator(locator);
		try {
			waitExplicit.until(ExpectedConditions.elementToBeClickable(byXpath));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void waitForElementClickable(WebDriver driver, String locator, String... values) {
		waitExplicit = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		byXpath = byXpathLocator(locator, values);
		try {
			waitExplicit.until(ExpectedConditions.elementToBeClickable(byXpath));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void waitForElementInvisible(WebDriver driver, String locator) {
		waitExplicit = new WebDriverWait(driver, GlobalConstants.SHORT_TIMEOUT);
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		byXpath = byXpathLocator(locator);
		try {
			System.out.println("Start time for wait invisible= " + new Date());
			waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(byXpath));
			System.out.println("End time for wait invisible= " + new Date());

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
	}

	public void waitForElementInvisible(WebDriver driver, String locator, String... values) {
		waitExplicit = new WebDriverWait(driver, GlobalConstants.SHORT_TIMEOUT);
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		byXpath = byXpathLocator(locator, values);
		try {
			System.out.println("Start time for wait invisible= " + new Date());
			waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(byXpath));
			System.out.println("End time for wait invisible= " + new Date());

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
	}

	public void waitForAlertPresence(WebDriver driver) {
		waitExplicit = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		try {
			System.out.println("Start time for wait Alert presence = " + new Date());
			waitExplicit.until(ExpectedConditions.alertIsPresent());
			System.out.println("End time for wait Alert presence = " + new Date());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void selectDefaultDropdownListByVisibleText(WebDriver driver, String locator, String valueItem) {
		element = findElementByXpath(driver, locator);
		select = new Select(element);
		select.selectByVisibleText(valueItem);
	}

	public void selectDefaultDropdownListByVisibleText(WebDriver driver, String locator, String valueItem, String... values) {
		element = findElementByXpath(driver, locator, values);
		select = new Select(element);
		select.selectByVisibleText(valueItem);
	}

	public void selectDefaultDropdownListByValue(WebDriver driver, String locator, String valueItem) {
		element = findElementByXpath(driver, locator);
		select = new Select(element);
		select.selectByValue(valueItem);
	}

	public void selectDefaultDropdownListByValue(WebDriver driver, String locator, String valueItem, String... values) {
		element = findElementByXpath(driver, locator, values);
		select = new Select(element);
		select.selectByValue(valueItem);
	}

	public String getSelectItemInHtmlDropdown(WebDriver driver, String locator) {
		element = findElementByXpath(driver, locator);
		select = new Select(element);
		return select.getFirstSelectedOption().getText();
	}

	public void selectCustomDropdownList(WebDriver driver, String parentXpath, String allItemsXpath, String expectedText) {
		clickToElement(driver, parentXpath);
		List<WebElement> allItems = findElementsByXpath(driver, allItemsXpath);
		waitForElementPresence(driver, allItemsXpath);
		for (WebElement item : allItems) {
			if (item.getText().contains(expectedText)) {
				item.click();
				break;

			}
		}
	}

	public void selectCustomDropdownList(WebDriver driver, String parentXpath, String allItemsXpath, String expectedText, String... values) {
		clickToElement(driver, parentXpath, values);
		List<WebElement> allItems = findElementsByXpath(driver, allItemsXpath);
		waitForElementPresence(driver, allItemsXpath);
		for (WebElement item : allItems) {
			if (item.getText().contains(expectedText)) {
				item.click();
				break;

			}
		}
	}

	public void inputItemInCustomDropdown(WebDriver driver, String parentXpath, String inputXpath, String expectedXpath) {
		clickToElement(driver, parentXpath);
		sendKeyToElement(driver, inputXpath, expectedXpath);
		sendKeyboardToElement(driver, inputXpath, Keys.ENTER);
	}

	public Object executeJSForBrowser(WebDriver driver, String javaScript) {
		jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaScript);
	}

	public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		jsExecutor = (JavascriptExecutor) driver;
		element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
	}

	public void sendkeyToElementByJS(WebDriver driver, String locator, String textValue, String... values) {
		jsExecutor = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator, values);
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + textValue + "')", element);
	}

	public void scrollToBottomPageByJS(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(WebDriver driver, String url) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public String getTextByJS(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return document.querySelector('" + locator + "').text");
	}

	public Object executeJSForElement(WebDriver driver, String javaScript, String locator) {
		element = findElementByXpath(driver, locator);
		jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaScript, element);
	}

	public void scrollToElementByJS(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator);
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void highlightElementByJS(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 5px solid red; border-style: dashed;");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator);
		jsExecutor.executeScript("arguments[0].click();", element);
	}

	public void clickToElementByJS(WebDriver driver, String locator, String... values) {
		jsExecutor = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator, values);
		jsExecutor.executeScript("arguments[0].click();", element);
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		jsExecutor = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator);
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
		sleepInSecond(driver, 1);
	}

	public void setAttributeInDOM(WebDriver driver, String locator, String attributeAdd) {
		jsExecutor = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator);
		jsExecutor.executeScript("arguments[0].setAttribute('type', '" + attributeAdd + "')", element);
		sleepInSecond(driver, 1);
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove, String... values) {
		jsExecutor = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator, values);
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
		sleepInSecond(driver, 1);
	}

	public boolean verifyTextInInnerTextByJS(WebDriver driver, String textExpected) {
		jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	public boolean checkAnyImageLoaded(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator);
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", element);
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	public void upload1File(WebDriver driver, String uploadFile, String fileName, String buttonStart) {
		String file = GlobalConstants.UPLOAD_FILE_PATH + fileName;

		waitForElementPresence(driver, uploadFile);
		sendKeyToElement(driver, uploadFile, file);
		sleepInSecond(driver, 2);

		waitForElementVisible(driver, buttonStart);
		clickToElement(driver, buttonStart);
		sleepInSecond(driver, 2);
	}

	public void uploadMultipleFiles(WebDriver driver, String uploadFile, String buttonStart, String... fileNames) {
		String filePath = GlobalConstants.UPLOAD_FILE_PATH;
		String fullFileName = "";

		for (String file : fileNames) {
			fullFileName = fullFileName + filePath + file + "\n";
		}
		fullFileName = fullFileName.trim();

		waitForElementPresence(driver, uploadFile);
		sendKeyToElement(driver, uploadFile, fullFileName);
		sleepInSecond(driver, 2);

		waitForElementVisible(driver, buttonStart);
		List<WebElement> buttonStarts = findElementsByXpath(driver, buttonStart);
		for (WebElement button : buttonStarts) {
			button.click();
			sleepInSecond(driver, 2);
		}
		sleepInSecond(driver, 2);
	}

	public void uploadFileByRobot(WebDriver driver, String uploadFile, String picturePath, String buttonStart) throws Exception {
		clickToElement(driver, uploadFile);
		Thread.sleep(1000);

		StringSelection select = new StringSelection(picturePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);

		Robot robot = new Robot();
		Thread.sleep(1000);

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);

		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		Thread.sleep(1000);

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		waitForElementVisible(driver, buttonStart);
		clickToElement(driver, buttonStart);
	}

	public void uploadAutoIT(WebDriver driver, String uploadFile, String picturePath, String buttonStart) throws Exception {
		String chromePath = ".\\uploadAutoIT\\chrome.exe";
		String firefoxPath = ".\\uploadAutoIT\\firefox.exe";
		String iePath = ".\\uploadAutoIT\\ie.exe";

		clickToElement(driver, uploadFile);
		Thread.sleep(1000);

		if (driver.toString().contains("firefox")) {
			Runtime.getRuntime().exec(new String[] { firefoxPath, picturePath });

		} else if (driver.toString().contains("chromePath")) {
			Runtime.getRuntime().exec(new String[] { chromePath, picturePath });
		} else {
			Runtime.getRuntime().exec(new String[] { iePath, picturePath });
		}

		waitForElementVisible(driver, buttonStart);
		clickToElement(driver, buttonStart);

	}

	public void sleepInSecond(WebDriver driver, long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void overrideGlobalTimeout(WebDriver driver, long timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}

	public int convertStringToInt(String value) {
		int i = Integer.parseInt(value);
		return i;
	}

	public String convertIntToString(int numberValue) {
		String value = String.valueOf(numberValue);
		return value;
	}

	// **************** Common Methods of ASPIRE ************************** //

	public void clickToAsPireButtonByText(WebDriver driver, String buttonValue) {
		waitForElementClickable(driver, AbstractPagePageUI.DYNAMIC_BUTTON, buttonValue);
		sleepInSecond(driver, 1);
		clickToElement(driver, AbstractPagePageUI.DYNAMIC_BUTTON, buttonValue);
	}

	public boolean isAspireButtonDisplayedByText(WebDriver driver, String buttonValue) {
		waitForElementVisible(driver, AbstractPagePageUI.DYNAMIC_BUTTON, buttonValue);
		sleepInSecond(driver, 1);
		return isElementDisplayed(driver, AbstractPagePageUI.DYNAMIC_BUTTON, buttonValue);
	}

	public void selectAspireCustomDropdownListByLabel(WebDriver driver, String parentXpathValue, String expectedText) {
		waitForElementClickable(driver, AbstractPagePageUI.DYNAMIC_CUSTOM_DROPDOWNLIST_ICON, parentXpathValue);
		sleepInSecond(driver, 2);
		selectCustomDropdownList(driver, AbstractPagePageUI.DYNAMIC_CUSTOM_DROPDOWNLIST_ICON, AbstractPagePageUI.CUSTOM_DROPDOWNLIST_LIST, expectedText, parentXpathValue);
	}

	public boolean isAspireTitleFormDisplayedByText(WebDriver driver, String titleValue) {
		waitForElementVisible(driver, AbstractPagePageUI.DYNAMIC_TITLE_FORM, titleValue);
		return isElementDisplayed(driver, AbstractPagePageUI.DYNAMIC_TITLE_FORM, titleValue);
	}

	public void selectAspireMultiCustomDropdownListByLabel(WebDriver driver, String parentXpathValue, String expectedText) {
		waitForElementClickable(driver, AbstractPagePageUI.DYNAMIC_CUSTOM_DROPDOWNLIST_ICON, parentXpathValue);
		clickToElement(driver, AbstractPagePageUI.DYNAMIC_CUSTOM_DROPDOWNLIST_ICON, parentXpathValue);

		List<WebElement> allItems = findElementsByXpath(driver, AbstractPagePageUI.CUSTOM_DROPDOWNLIST_LIST);
		waitForElementPresence(driver, AbstractPagePageUI.CUSTOM_DROPDOWNLIST_LIST);

		for (WebElement item : allItems) {
			if (item.getText().contains(expectedText)) {
				item.click();
				break;
			}
		}
		clickToElement(driver, AbstractPagePageUI.DYNAMIC_CUSTOM_DROPDOWNLIST_ICON, parentXpathValue);
	}
}
