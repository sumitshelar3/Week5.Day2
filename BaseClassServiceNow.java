package week5.day2.assignment;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;

public class BaseClassServiceNow {

	public ChromeDriver driver;
	public Shadow shadow;
	public String excelFileName;
	public String sheetName;
	
	@DataProvider(name = "getdata")
	public String[][] excelData() throws IOException {
		return ReadExcelData.readData(excelFileName, sheetName);
	}

	@Parameters({"url","username","password"})
	@BeforeMethod()
	public void beforeMethod(String url,String username,String password) throws Exception {
		

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		// To locate the element present in Shadow root/DOM
		shadow=new Shadow(driver);
	    shadow.setImplicitWait(20);
		driver.manage().window().maximize();
         
		// Launch ServiceNow application
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		// Login with valid credentials UserName & Password
		driver.findElement(By.id("user_name")).sendKeys(username);
		driver.findElement(By.id("user_password")).sendKeys(password);
		driver.findElement(By.id("sysverb_login")).click();

		// Click on All
		Thread.sleep(10000);
		WebElement allEle = shadow.findElementByXPath("//div[@id='d6e462a5c3533010cbd77096e940dd8c']");
		allEle.click();
		Thread.sleep(5000);
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
}
