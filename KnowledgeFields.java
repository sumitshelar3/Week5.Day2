package week5.day2.assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class KnowledgeFields extends BaseClassServiceNow{
	
	@BeforeTest
	public void setName() {
		excelFileName = "KnowledgeFields";
		sheetName="Sheet1";
	}
	
	@Test(dataProvider = "getdata")
	public void runKnowledgeFields(String filter1,String shortDesc) throws Exception {

		// Enter "Knowledge" in filter navigator and press Enter
		WebElement filterElement = shadow.findElementByXPath("//input[@id='filter']");
		filterElement.sendKeys(filter1);
		Thread.sleep(1000);
		filterElement.sendKeys(Keys.ENTER);

		// Create new Article
		WebElement frame = shadow.findElementByXPath("//iframe[@id='gsft_main']");
		driver.switchTo().frame(frame);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[text()='Create an Article']")).click();
		driver.findElement(By.xpath("//button[@id='lookup.kb_knowledge.kb_knowledge_base']")).click();
		Set<String> windowHandles1 = driver.getWindowHandles();
		List<String> listW1 = new ArrayList<String>(windowHandles1);
		driver.switchTo().window(listW1.get(1));
		driver.findElement(By.xpath("//a[text()='IT']")).click();
		driver.switchTo().window(listW1.get(0));
		driver.switchTo().frame(frame);

		driver.findElement(By.xpath("//button[@id='lookup.kb_knowledge.kb_category']")).click();
		// Select knowledge base as IT and category as IT- java and Click Ok
		driver.findElement(By.xpath("//span[text()='IT']")).click();
		driver.findElement(By.xpath("//span[text()='Java']")).click();
		driver.findElement(By.xpath("//button[@id='ok_button']")).click();
		// Update the other mandatory fields
		driver.findElement(By.id("kb_knowledge.short_description")).sendKeys(shortDesc);
		// Select the Submit option
		driver.findElement(By.xpath("//button[@id='sysverb_insert']")).click();
	}

}
