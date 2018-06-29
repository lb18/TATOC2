package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SelectCourse
{
	WebDriver driver;
	
	@FindBy(how = How.LINK_TEXT, using = "Basic Course")
	WebElement basic_course_link;
	
	public SelectCourse(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void basic_Course_Button()
	{
		basic_course_link.click();
	}
}