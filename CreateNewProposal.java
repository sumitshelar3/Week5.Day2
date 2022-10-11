package week5.day2.assignment;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CreateNewProposal extends BaseClassServiceNow{
	
	@BeforeTest
	public void setName() {
		excelFileName = "CreateNewProposal";
		sheetName="Proposal";
	}
	
	@Test(dataProvider = "getdata")
	public void runCreateNewProposal(String filter,String tempDesc) throws Exception {

		// Enter "Proposal" in filter navigator & press Enter
		WebElement filterElement = shadow.findElementByXPath("//input[@id='filter']");
		filterElement.sendKeys(filter);
		Thread.sleep(1000);
		filterElement.sendKeys(Keys.ENTER);

		// Click on "New" option & fill mandatory fields
		WebElement frame = shadow.findElementByXPath("//iframe[@id='gsft_main']");
		driver.switchTo().frame(frame);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[@id='sysverb_new']")).click();
		WebElement tempDescription = driver.findElement(By.xpath("//input[@id='std_change_proposal.short_description']"));
		tempDescription.sendKeys(tempDesc);

		// click "Submit" Button.
		driver.findElement(By.xpath("//button[@id='sysverb_insert_bottom']")).click();

		// Verify the successful creation of new Proposal
		driver.switchTo().defaultContent();
		String actualText = shadow.findElementByXPath("//span[@class='experience-title']").getText();
		String expectedText = "Standard Change Proposals";
		if (actualText.equalsIgnoreCase(expectedText)) {
			System.out.println("Validation: This is to confirm that New proposal created successfully");
		} else {
			System.out.println("Please try again after sometime!");
		}
	}
}
