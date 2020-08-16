package com.qa.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Testqa 
{
	WebDriver driver;
	@Test
	public void ixigoTicketBookingTest() throws Exception 
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\rahul\\Downloads\\softwares\\Selenium-Java\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(4,TimeUnit.SECONDS);
		driver.get("https://www.ixigo.com/");
		String pageTitle = driver.getTitle();
		//2.	Validate the page
		Assert.assertTrue(pageTitle.contains("ixigo - Flights, Train Reservation, Hotels, Air Tickets, Bus Booking"));
		
		//3.	Enter From – Pune , To – Hyderabad
		WebElement SourceCity = driver.findElement(By.xpath("//div[@class='orgn u-ib u-v-align-bottom u-text-left']//input[@placeholder='Enter city or airport']"));
		Thread.sleep(2000);
		SourceCity.click();
		SourceCity.sendKeys(Keys.CONTROL , "a");
		SourceCity.sendKeys(Keys.BACK_SPACE);
		Thread.sleep(2000);
		SourceCity.sendKeys("Pune");
		Thread.sleep(2000);
		SourceCity.sendKeys(Keys.ENTER);
		
		WebElement DestinationCity = driver.findElement(By.xpath("//div[@class='dstn u-ib u-v-align-bottom u-text-left']//input[@placeholder='Enter city or airport']"));
		Thread.sleep(2000);
		DestinationCity.click();
		DestinationCity.sendKeys(Keys.CONTROL , "a");
		DestinationCity.sendKeys(Keys.BACK_SPACE);
		Thread.sleep(2000);
		DestinationCity.sendKeys("Hyderabad");
		Thread.sleep(2000);
		DestinationCity.sendKeys(Keys.ENTER);
		
		//Departure – 17 Sep 2020 / 17 Sep, Thu
		WebElement departureDate = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[2]/div[2]/table[1]/tbody[1]/tr[3]/td[5]/div[1]"));
		departureDate.click();
		
		//Return – 24 Oct 2020
		driver.findElement(By.xpath("//input[@placeholder='Return']")).click();
		WebElement returnDate = driver.findElement(By.xpath("/html[1]/body[1]/div[5]/div[2]/div[2]/table[1]/tbody[1]/tr[4]/td[7]/div[1]"));
		returnDate.click();
		
		//Travelers - 2
		driver.findElement(By.xpath("//div[@class='passanger-class-input u-pos-rel']//input[@class='c-input u-v-align-middle']")).click();
		String Adult_numberOfPassengers = "2";
		driver.findElement(By.xpath("//div[@class='passanger-class-input u-pos-rel']//div[1]//div[2]//span["+Adult_numberOfPassengers+"]")).click();
		
		//4.	Click on Search, Validate the result page
		driver.findElement(By.xpath("//button[contains(text(),'Search')]")).click();
		Thread.sleep(20000);
		
		String currentPageTitle02 = driver.getTitle();
		Assert.assertTrue(currentPageTitle02.contains("Pune - Hyderabad, Economy Flights, roundtrip, 17 Sep - 24 Oct"));
		
		//5.	Validate filter option for Stops, departure and Airlines – Select Non-Stop in Stops filter option
		
		
		
		
	}

	ExtentReports extent;
	ExtentTest test;
	ExtentHtmlReporter testHtmlReporter;
	@BeforeTest
	public void startReport()
	{
		testHtmlReporter= new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/newExtentRepoter.html");
		
		testHtmlReporter.config().setEncoding("utf-8");
		testHtmlReporter.config().setDocumentTitle("reportTitle");
		testHtmlReporter.config().setReportName("reportName");
		testHtmlReporter.config().setTheme(Theme.STANDARD);
		
		extent = new ExtentReports();
		extent.attachReporter(testHtmlReporter);
		
		extent.setSystemInfo("Author", "Rahul Ranjan");
		extent.setSystemInfo("environment", "QA");
		extent.setSystemInfo("pattern", "self-designed");
	}
	
	@AfterMethod
	public void getResult(ITestResult result)
	{
		if(result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + ": PASSED", ExtentColor.GREEN));
		}
		else if(result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + ": FAILED", ExtentColor.RED));
			test.fail(result.getThrowable());
		}
		else
		{
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + ": SKIPPED", ExtentColor.BLUE));
		}
	}
	@AfterTest
	public void EndReport()
	{
		extent.flush();
	}
}
