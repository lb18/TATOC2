package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class PopupWindow 
{
	WebDriver driver;
	
	@FindBy(how = How.LINK_TEXT, using = "Launch Popup Window")
	WebElement launch_popup_window_button;
	
	@FindBy(how = How.LINK_TEXT, using = "Proceed")
	WebElement go_to_next_page;
}
