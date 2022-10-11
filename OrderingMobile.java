	package week5.day2.assignment;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class OrderingMobile extends BaseClassServiceNow {
	
	@BeforeTest
	public void setName() {
		excelFileName = "CreateNewProposal";
		sheetName="Mobile";
	}
	
	@Test(dataProvider = "getdata")
	public void runOrderingMobile(String filter) throws Exception {

		// Enter "Service Catalog" in filter navigator and press Enter
		WebElement filterElement = shadow.findElementByXPath("//input[@id='filter']");
		filterElement.sendKeys(filter);
		Thread.sleep(1000);
		filterElement.sendKeys(Keys.ENTER);

		// Switch to frame
		WebElement frame = shadow.findElementByXPath("//iframe[@id='gsft_main']");
		driver.switchTo().frame(frame);
		Thread.sleep(5000);

		// Click on Mobiles
		driver.findElement(By.xpath("//td[@class='homepage_category_only_description_cell']//h2[contains(text(),'Mobiles')]")).click();

		// Select Apple iPhone 13 Mobile
		driver.findElement(By.xpath("//strong[text()='Apple iPhone 13']")).click();
		driver.findElement(By.xpath("(//span[@class='input-group-radio']//label)[2]")).click();

		// Update color field to Blue
		WebElement dropDown = driver.findElement(By.xpath("//select[@class='form-control cat_item_option ']"));
		Select s = new Select(dropDown);
		s.selectByValue("500MB");
		driver.findElement(By.xpath("//label[@class='radio-label'][text()='Blue']")).click();
		driver.findElement(By.xpath("//label[@class='radio-label'][contains(text(),'256 GB')]")).click();
		// Select Order now option
		driver.findElement(By.xpath("//button[@id='oi_order_now_button']")).click();

		// Verify order is placed and copy the request number
		String displayMsg = driver.findElement(By.xpath("//span[@aria-live='assertive']")).getText();
		String expectedMsg = "Thank you, your request has been submitted";
		if (displayMsg.equalsIgnoreCase(expectedMsg)) {
			System.out.println("This is to verify Mobile Phone has been palced successfully");
		} else {
			System.out.println("Order is not placed ,please try again!");
		}
		String requestId = driver.findElement(By.xpath("//a[@id='requesturl']/b")).getText();
		System.out.println("Request Number: " + requestId);
	}
	
	@DataProvider(name="OrderingMobile")
	public String [] [] readDataOrderingMobile()
	{
		
		return null;
	}
}
