package test;

import pages.SelectBrowser;
import pages.SelectCourse;
import pages.GridGate;
import pages.FrameDungeon;
import pages.DragAround;
import pages.PopupWindow;
import pages.GenerateToken;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import static org.testng.Assert.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class TestTatoc
{
	WebDriver driver;
	SelectCourse select_course_type;
	GridGate select_box;
	FrameDungeon box_colors;
	DragAround drag_and_drop;
	PopupWindow popup;
	GenerateToken generate_token;
	
	@BeforeClass
	public void init()
	{
		SelectBrowser select_type_of_browser = new SelectBrowser();
		driver = select_type_of_browser.launchBrowser("chrome", "http://10.0.1.86/tatoc");
		select_course_type = PageFactory.initElements(driver, SelectCourse.class);
		select_box = PageFactory.initElements(driver, GridGate.class);
		box_colors = PageFactory.initElements(driver, FrameDungeon.class);
		drag_and_drop = PageFactory.initElements(driver, DragAround.class);
		popup = PageFactory.initElements(driver, PopupWindow.class);
		generate_token = PageFactory.initElements(driver, GenerateToken.class);
	}
	
	@Test
	public void basic_Course_Button_Should_Click()
	{
		select_course_type.basic_Course_Button();
		System.out.println(driver.getCurrentUrl());
		assertEquals(driver.getCurrentUrl(), "http://10.0.1.86/tatoc/basic/grid/gate");
	}

	@Test(dependsOnMethods = {"basic_Course_Button_Should_Click"})
	public void red_Box_Click_Should_Display_Error_Page()
	{
		select_box.click_redbox();
		Assert.assertTrue(driver.getCurrentUrl().contains("error"));
	}
	
	@Test(dependsOnMethods = {"basic_Course_Button_Should_Click", "red_Box_Click_Should_Display_Error_Page"})
	public void green_Box_Click_Should_Proceed_to_Next_Page()
	{
		driver.navigate().back();
		select_box.click_greenbox();
		Assert.assertTrue(driver.getCurrentUrl().contains("basic")); 
	}
	
	@Test(dependsOnMethods = {"green_Box_Click_Should_Proceed_to_Next_Page"})
	public void box_Colors_Does_Not_Match_Should_Display_Error_Page()
	{
		box_colors.box_Color_Does_Not_Match();
		Assert.assertTrue(driver.getCurrentUrl().contains("error"));
	}
	
	@Test(dependsOnMethods = {"green_Box_Click_Should_Proceed_to_Next_Page", "box_Colors_Does_Not_Match_Should_Display_Error_Page"})
	public void box_Colors_Do_Match_Should_Go_To_Next_Page()
	{
		driver.navigate().back();
		box_colors.box_Color_Matches();
		WebElement get_id_name = driver.findElement(By.id("dropbox"));
		assertEquals(get_id_name.getText(), "DROPBOX");
	}
	
	@Test(dependsOnMethods = {"box_Colors_Do_Match_Should_Go_To_Next_Page"})
	public void dropbox_Does_Not_Contain_Dragbox_Should_Display_Error_Page()
	{
		drag_and_drop.dropbox_Does_Not_Contain_Dragbox();
		WebElement get_the_text_of_first_heading = driver.findElement(By.cssSelector("body > div > div.page > h1"));
		Assert.assertTrue(get_the_text_of_first_heading.getText().contains("Err"));
	}
	
	@Test(dependsOnMethods = {"dropbox_Does_Not_Contain_Dragbox_Should_Display_Error_Page", "box_Colors_Do_Match_Should_Go_To_Next_Page"})
	public void dropbox_Contains_Dragbox_Should_Go_To_Next_Page()
	{
		driver.navigate().back();
		drag_and_drop.dropbox_Contains_Dragbox();
		WebElement get_the_text_of_first_heading = driver.findElement(By.cssSelector("body > div > div.page > h1"));
		assertEquals(get_the_text_of_first_heading.getText().contains("Pop"), true);
	}
	
	@Test(dependsOnMethods = {"dropbox_Contains_Dragbox_Should_Go_To_Next_Page"})
	public void empty_Text_Field_Should_Display_Error_Page()
	{
		boolean res = popup.text_Field_Is_Empty();
		assertEquals(res, true);
		WebElement get_the_text_of_first_heading = driver.findElement(By.cssSelector("body > div > div.page > h1"));
		Assert.assertTrue(get_the_text_of_first_heading.getText().contains("Error"));
	}
	
	@Test(dependsOnMethods = {"dropbox_Contains_Dragbox_Should_Go_To_Next_Page", "empty_Text_Field_Should_Display_Error_Page"})
	public void text_Field_Contains_Some_Text_Should_Go_To_Next_Paage()
	{
		driver.navigate().back();
		popup.text_Field_Is_Filled();
		WebElement get_link_text = driver.findElement(By.linkText("Generate Token"));
		Assert.assertTrue(get_link_text.getText().contains("Generate Token"));
	}
	
	@Test(dependsOnMethods = {"text_Field_Contains_Some_Text_Should_Go_To_Next_Paage"})
	public void token_Not_Generated_Should_Display_Error_Page()
	{
		generate_token.token_Not_Generated();
		WebElement get_the_text_of_first_heading = driver.findElement(By.cssSelector("body > div > div.page > h1"));
		Assert.assertTrue(get_the_text_of_first_heading.getText().contains("Error"));
	}
	
	@Test(dependsOnMethods = {"text_Field_Contains_Some_Text_Should_Go_To_Next_Paage", "token_Not_Generated_Should_Display_Error_Page"})
	public void token_Generated_Should_Go_To_Next_Page()
	{
		driver.navigate().back();
		generate_token.token_Generated();
		WebElement get_the_text_of_first_heading = driver.findElement(By.cssSelector("body > div > div.page > h1"));
		Assert.assertTrue(get_the_text_of_first_heading.getText().contains("End"));
	}
	
	/*@AfterClass
	public void closeBrowser()
	{
		driver.quit();
	}*/
}