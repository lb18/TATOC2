package test;

import pages.SelectBrowser;
import pages.SelectCourse;
import pages.GridGate;
import pages.FrameDungeon;


import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class TestTatoc
{
	WebDriver driver;
	SelectCourse select_course_type;
	GridGate select_box;
	FrameDungeon box_colors;
	
	@BeforeClass
	public void init()
	{
		SelectBrowser select_type_of_browser = new SelectBrowser();
		driver = select_type_of_browser.launchBrowser("chrome", "http://10.0.1.86/tatoc");
		select_course_type = PageFactory.initElements(driver, SelectCourse.class);
		select_box = PageFactory.initElements(driver, GridGate.class);
		box_colors = PageFactory.initElements(driver, FrameDungeon.class);
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
		Assert.assertTrue(driver.getCurrentUrl().contains("basic")); // frame, dungeon
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
		box_colors.box_Color_matches();
		Assert.assertTrue(driver.getCurrentUrl().contains("window"));
	}
	
}