package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class FrameDungeon
{
	WebDriver driver;
	@FindBy(how = How.XPATH, using = "//*[@id=\"main\"]")
	WebElement main_frame;
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"child\"]")
	WebElement child_frame;
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"answer\"]")
	WebElement box1;
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"answer\"]")
	WebElement box2;
	
	@FindBy(how = How.LINK_TEXT, using = "Repaint Box 2")
	WebElement repaint_box_2;
	
	@FindBy(how = How.LINK_TEXT, using = "Proceed")
	WebElement go_to_next_page;
	
	public FrameDungeon(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public String get_Box1_Color()
	{
		driver.switchTo().frame(main_frame);
		return box1.getCssValue("background-color");
	}
	
	public String get_Box2_Color()
	{
		driver.switchTo().frame(child_frame);
		return box2.getCssValue("background-color");
	}
	
	public void box_Color_Does_Not_Match()
	{
		String color_of_box1 = get_Box1_Color(), color_of_box2 = get_Box2_Color();
		driver.switchTo().parentFrame();
		if (color_of_box1 != color_of_box2) go_to_next_page.click();
	}
	
	public void box_Color_Matches()
	{
		String color_of_box1 = get_Box1_Color(), color_of_box2 = "";
		while (!color_of_box1.equalsIgnoreCase(color_of_box2))
		{
			color_of_box2 = get_Box2_Color();
			driver.switchTo().parentFrame();
			if (color_of_box1.equalsIgnoreCase(color_of_box2))
			{
				go_to_next_page.click();
				break;
			}
			repaint_box_2.click();
		}	
	}
}