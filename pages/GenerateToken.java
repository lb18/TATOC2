package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;


public class GenerateToken
{
	WebDriver driver;
	
	@FindBy(how = How.LINK_TEXT, using = "Generate Token")
	WebElement generate_token_button;
	
	@FindBy(how = How.LINK_TEXT, using = "Proceed")
	WebElement go_to_next_page;
	
	public GenerateToken(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void token_Not_Generated()
	{
		go_to_next_page.click();
	}
	
	public void token_Generated()
	{
		driver.findElement(By.linkText("Generate Token")).click();
		String Cookie_val = driver.findElement(By.id("token")).getText();
		Cookie ck = new Cookie("Token", Cookie_val.substring(7));
		driver.manage().addCookie(ck);
		driver.findElement(By.linkText("Proceed")).click();
	}
}
