package com.qa.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
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
	public void openFlipkart() 
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\rahul\\Downloads\\softwares\\Selenium-Java\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.flipkart.com/");
		String pageTitle = driver.getTitle();
		Assert.assertTrue(pageTitle.contains("Online Shopping"));
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
		extent.setSystemInfo("pattern", "self");
	}
	@Test
	public void ClickSignIn() 
	{
		test = extent.createTest("CeateTestReports", "ClickSignIn");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\rahul\\Downloads\\softwares\\Selenium-Java\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://boardgamegeek.com/");
		driver.findElement(By.xpath("//*[@id=\"global-header-outer\"]/header/nav/div/div[2]/div/div[1]/ul/li[7]")).click();
		driver.findElement(By.id("inputUsername")).sendKeys("test123");
		driver.findElement(By.id("inputPassword")).sendKeys("testPassword123");
		driver.findElement(By.xpath("/html/body/div[1]/div/div/form/div[3]/button")).click();
		System.out.println(System.getProperty("testSuite"));
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
