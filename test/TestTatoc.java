package test;

import pages.SelectBrowser;
import pages.SelectCourse;
import pages.GridGate;
import pages.FrameDungeon;
import pages.DragAround;


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
	
	@BeforeClass
	public void init()
	{
		SelectBrowser select_type_of_browser = new SelectBrowser();
		driver = select_type_of_browser.launchBrowser("chrome", "http://10.0.1.86/tatoc");
		select_course_type = PageFactory.initElements(driver, SelectCourse.class);
		select_box = PageFactory.initElements(driver, GridGate.class);
		box_colors = PageFactory.initElements(driver, FrameDungeon.class);
		drag_and_drop = PageFactory.initElements(driver, DragAround.class);
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

	
	
}
