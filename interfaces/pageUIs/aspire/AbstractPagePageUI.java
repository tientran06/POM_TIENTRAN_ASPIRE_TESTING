package pageUIs.aspire;

public class AbstractPagePageUI {
	public static final String DYNAMIC_BUTTON = "//span[text() = '%s']//ancestor::div/button/span[@class = 'q-btn__wrapper col row q-anchor--skip']";
	public static final String DYNAMIC_CUSTOM_DROPDOWNLIST_ICON = "//div[@label = '%s']//div/i[@role = 'presentation']";
	public static final String CUSTOM_DROPDOWNLIST_LIST = "//div//div[@class = 'q-item__label']";
	public static final String CUSTOM_DROPDOWNLIST_ITEM = "//div[text() = '%s']//ancestor::div[contains(@class,'q-item q-item-type row no-wrap q-item--clickable')]";
	
	public static final String DYNAMIC_TITLE_FORM = "//div[@class = 'auth-form']//div[contains(@class, 'text-weight-bold') and contains(text(),'%s')]";
}
