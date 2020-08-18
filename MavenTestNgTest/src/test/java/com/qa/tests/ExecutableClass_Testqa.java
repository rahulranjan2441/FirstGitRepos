package com.qa.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ExecutableClass_Testqa
{
	WebDriver driver;
	@BeforeTest
	public void BrowserSetup()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\rahul\\Downloads\\softwares\\Selenium-Java\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	}
	@Test
	public void ixigoTicketBookingTest() throws Exception 
	{
		try 
		{
			//create page objects using PageFactory
			IxigoPages_Homepage IxigoPages_Homepage = PageFactory.initElements(driver, IxigoPages_Homepage.class);
			IxigoPages_Homepage.ixigoSearch_page("Pune", "Hyderabad", "2");
			
			//create page objects using PageFactory
			IxigoPages_FlightBooking IxigoPages_FlightBooking = PageFactory.initElements(driver, IxigoPages_FlightBooking.class);
			IxigoPages_FlightBooking.ixigoBooking_page();
		}catch(Exception ex)
		{
			System.out.println(ex.getStackTrace());
			System.out.println(ex.getMessage());
		}
		
	}
	
	@AfterTest
	public void endBrowserSetup()
	{
		driver.quit();
	}
	
}
