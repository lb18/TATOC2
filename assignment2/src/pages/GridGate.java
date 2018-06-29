package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class GridGate
{
	WebDriver driver;
	
	@FindBy(how = How.CLASS_NAME, using = "redbox")
	WebElement redbox;
	
	@FindBy(how = How.CLASS_NAME, using = "greenbox")
	WebElement greenbox;
	
	public GridGate(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void click_redbox()
	{
		redbox.click();
	}
	
	public void click_greenbox()
	{
		greenbox.click();
	}
}