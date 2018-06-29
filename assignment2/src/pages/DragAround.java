package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class DragAround
{
	WebDriver driver;
	
	@FindBy(how = How.ID, using = "dropbox")
	WebElement dropbox;
	
	@FindBy(how = How.ID, using = "dragbox")
	WebElement dragbox;
	
	@FindBy(how = How.LINK_TEXT, using = "Proceed")
	WebElement go_to_next_page;
	
	public DragAround(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void dropbox_Does_Not_Contain_Dragbox()
	{
		//System.out.println(driver.findElement(By.id("dragbox")).findElement(By.id("dragbox")).isEnabled());
			go_to_next_page.click();
	}
	
	public void dropbox_Contains_Dragbox()
	{
		Actions act = new Actions(driver);
		WebElement from = driver.findElement(By.id("dragbox"));
		WebElement to = driver.findElement(By.id("dropbox"));
		act.dragAndDrop(from, to).build().perform();
		go_to_next_page.click();
	}
}