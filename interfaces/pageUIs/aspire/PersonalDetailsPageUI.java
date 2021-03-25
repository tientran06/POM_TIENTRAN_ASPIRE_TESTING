package pageUIs.aspire;

public class PersonalDetailsPageUI {
	public static final String PERSONAL_INFOR_TEXTBOX = "//div[@readonly='readonly']//div[contains(text(),'%s')]";
	public static final String PERSONAL_EMAIL = "//div[@type = 'email']";
	public static final String OPEN_CALENDAR_BUTTON = "//div[@label = 'Date of Birth']/label";
	public static final String CALENDAR = "//div[contains(@class,'q-date q-date--portrait')]";
	public static final String DYNAMIC_BUTTON = "//button//span[contains(@class,'justify-center row')]//span[contains(text(), '%s')]";
	public static final String LEFT_ARROW = "//div[contains(@class, 'q-date__years-content')]/preceding-sibling::div";
	public static final String RIGHT_ARROW = "//div[contains(@class, 'q-date__years-content')]/following-sibling::div";
	public static final String DYNAMIC_ITEM_LIST = "//span[contains(@class,'justify-center row')]//span[text()='%s']";
	public static final String DYNAMIC_IDCARD_TEXTBOX = "//div//input[@placeholder = '%s']";
	
}
