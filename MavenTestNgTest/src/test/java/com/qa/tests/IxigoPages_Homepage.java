package com.qa.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

public class IxigoPages_Homepage 
{
	WebDriver driver;
	
	public IxigoPages_Homepage(WebDriver LocalDriver)
	{
		this.driver = LocalDriver;
	}
	
	@FindBy(how=How.XPATH,using="//div[@class='orgn u-ib u-v-align-bottom u-text-left']//input[@placeholder='Enter city or airport']") 
	WebElement SourceCity;
	
	@FindBy(how=How.XPATH,using="//div[@class='dstn u-ib u-v-align-bottom u-text-left']//input[@placeholder='Enter city or airport']")
	WebElement DestinationCity;
	
	@FindBy(how=How.XPATH,using="/html[1]/body[1]/div[2]/div[2]/div[2]/table[1]/tbody[1]/tr[3]/td[5]/div[1]")
	WebElement departureDate;
	
	@FindBy(how=How.XPATH,using="//input[@placeholder='Return']")
	WebElement returnDateCalendar;
	
	@FindBy(how=How.XPATH,using="/html[1]/body[1]/div[5]/div[2]/div[2]/table[1]/tbody[1]/tr[4]/td[7]/div[1]")
	WebElement returnDate;
	
	@FindBy(how=How.XPATH,using="//div[@class='passanger-class-input u-pos-rel']//input[@class='c-input u-v-align-middle']")
	WebElement ClickOnTravellerCounts;
	
	@FindBy(how=How.XPATH,using="//button[contains(text(),'Search')]")
	WebElement searchButton_Click;

	
	public void ixigoSearch_page(String FromCity, String ToCity, String Adult_numberOfPassengers )
	{
		try 
		{
			driver.get("https://www.ixigo.com/");
			String pageTitle = driver.getTitle();
			//2.	Validate the page
			Assert.assertTrue(pageTitle.contains("ixigo - Flights, Train Reservation, Hotels, Air Tickets, Bus Booking"));
			
			//3.	Enter From – Pune , To – Hyderabad
			//select from/source city
			SourceCity.click();
			SourceCity.sendKeys(Keys.CONTROL , "a");
			SourceCity.sendKeys(Keys.BACK_SPACE);
			SourceCity.sendKeys(FromCity);
			SourceCity.sendKeys(Keys.ENTER);
			
			// select To/Destination city
			DestinationCity.click();
			DestinationCity.sendKeys(Keys.CONTROL , "a");
			DestinationCity.sendKeys(Keys.BACK_SPACE);
			DestinationCity.sendKeys(ToCity);
			DestinationCity.sendKeys(Keys.ENTER);
			
			//select Departure – 17 Sep 2020 / 17 Sep, Thu
			departureDate.click();
			
			//select Return – 24 Oct 2020
			returnDateCalendar.click();
			returnDate.click();
			
			//enter Travelers - 2
			ClickOnTravellerCounts.click();
			driver.findElement(By.xpath("//div[@class='passanger-class-input u-pos-rel']//div[1]//div[2]//span["+Adult_numberOfPassengers+"]")).click();
			
			//4.	Click on Search, Validate the result page
			searchButton_Click.click();
			Thread.sleep(22000);
			
		}catch (Exception ex)
		{
			System.out.println("exception Handling - testqa - ixigoSearch_page");
			System.out.println(ex.getStackTrace());
			System.out.println(ex.getMessage());
		}
	}
	
}
