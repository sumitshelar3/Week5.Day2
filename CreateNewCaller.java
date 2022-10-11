package week5.day2.assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CreateNewCaller extends BaseClassServiceNow {

	@BeforeTest
	public void setName() {
		excelFileName = "CreateNewCaller";
		sheetName="Sheet1";
	}

	@Test(dataProvider = "getdata")
	public void runCreateNewCaller(String filter, String fName, String email, String lName, String bPhone,
			String mNumber) throws Exception {

		// Enter "Callers" in filter navigator & press Enter
		WebElement filterElement = shadow.findElementByXPath("//input[@id='filter']");
		filterElement.sendKeys(filter);
		Thread.sleep(1000);
		filterElement.sendKeys(Keys.ENTER);

		// Create New Caller by filling all the fields
		WebElement frame = shadow.findElementByXPath("//iframe[@id='gsft_main']");
		driver.switchTo().frame(frame);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[@id='sysverb_new']")).click();

		driver.findElement(By.xpath("//input[@id='sys_user.first_name']")).sendKeys(fName);
		driver.findElement(By.xpath("//input[@id='sys_user.email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='sys_user.last_name']")).sendKeys(lName);
		driver.findElement(By.xpath("//input[@id='sys_user.phone']")).sendKeys(bPhone);
		// int beforeCount = driver.getWindowHandles().size(); // just count for windows
		// before switching to new one

		driver.findElement(By.xpath("//a[@id='lookup.sys_user.title']")).click();
		String parentW = driver.getWindowHandle();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> listW = new ArrayList<String>(windowHandles);
		// System.out.println(beforeCount);
		driver.switchTo().window(listW.get(1));
		driver.findElement(By.xpath("//a[text()='Junior Developer']")).click();
		driver.switchTo().window(parentW);
		driver.switchTo().frame(frame);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='sys_user.mobile_phone']")).sendKeys(mNumber);

		// Click on Submit
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[@id='sysverb_insert_bottom']")).click();

		// Search and verify the newly created Caller"
		WebElement dropDown = driver.findElement(By.xpath("//select[@class='form-control default-focus-outline']"));
		Select s = new Select(dropDown);
		s.selectByVisibleText("Business phone");
		WebElement searchFor = driver.findElement(By.xpath("//input[@placeholder='Search']"));
		searchFor.sendKeys(bPhone, Keys.ENTER);
		Thread.sleep(2000);
		String actualBusinessPhone = driver.findElement(By.xpath("//table[@id='sys_user_table']/tbody/tr/td[5]"))
				.getText();

		if (bPhone.equals(actualBusinessPhone)) {
			System.out.println("Newly caller created successfully");
		} else {
			System.out.println("Please try again!");
		}
	}

}
