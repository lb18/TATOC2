package pages;

import java.util.*;
import org.openqa.selenium.By;
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
	
	public PopupWindow(WebDriver driver)
	{
		this.driver = driver;
	}
	
	private String select_Popup_Window()
	{

		driver.findElement(By.linkText("Launch Popup Window")).click();
		String MainWindow = driver.getWindowHandle();
		return MainWindow;
	}
	
	private void switch_To_Main_Window_After_Submitting_The_Forum(String MainWindow)
	{
		driver.switchTo().window(MainWindow);
		go_to_next_page.click();
	}
	
	public boolean text_Field_Is_Empty()
	{
		String MainWindow = select_Popup_Window();
		Set<String> handle = driver.getWindowHandles();
		for (Iterator<String> it = handle.iterator(); it.hasNext();)
		{
			String childWindow = it.next();
			driver.switchTo().window(childWindow);
			if (!MainWindow.equalsIgnoreCase(childWindow))
			{
				driver.findElement(By.id("name")).sendKeys("");
				driver.findElement(By.id("submit")).click();
				switch_To_Main_Window_After_Submitting_The_Forum(MainWindow);
				return true;
			}
		}
		switch_To_Main_Window_After_Submitting_The_Forum(MainWindow);
		return false;		
	}
	
	public void text_Field_Is_Filled()
	{
		String MainWindow = select_Popup_Window();
		Set<String> handle = driver.getWindowHandles();
		for (Iterator<String> it = handle.iterator(); it.hasNext();)
		{
			String childWindow = it.next();
			driver.switchTo().window(childWindow);
			if (!MainWindow.equalsIgnoreCase(childWindow))
			{
				driver.findElement(By.id("name")).sendKeys("yo");
				driver.findElement(By.id("submit")).click();
				switch_To_Main_Window_After_Submitting_The_Forum(MainWindow);
				break;
			}
		}
	}
}