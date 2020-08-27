package com.qa.tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

public class IxigoPages_FlightBooking 
{
	WebDriver driver;
	
	public IxigoPages_FlightBooking(WebDriver Localdriver)
	{
		this.driver = Localdriver;
	}
	
	@FindBy(how=How.XPATH,using="//div[@class='stops']//div[2]//span[1]//span[1]")
	WebElement oneStopButton_click;
	
	@FindBy(how=How.XPATH,using="/html[1]/body[1]/div[1]/div[1]/div[4]/div[1]/div[1]/div[4]/div[1]/div[1]/div[1]/div[1]/div[4]/div[2]")
	WebElement oneStopAttributeValue2;
	
	@FindBy(how=How.XPATH,using="//div[@class='fltr-col-1 u-ib']//div[3]//span[1]//span[1]")
	WebElement multiStopsCB_click;
	
	@FindBy(how=How.XPATH,using="/html[1]/body[1]/div[1]/div[1]/div[4]/div[1]/div[1]/div[4]/div[1]/div[1]/div[1]/div[1]/div[4]/div[2]")
	WebElement muultiStopsAttributeValue3;
	
	@FindBy(how=How.XPATH,using="//div[@class='stops']//div[1]//span[1]//span[1]")
	WebElement nonStopCB_Click;
	
	@FindBy(how=How.XPATH,using="/html[1]/body[1]/div[1]/div[1]/div[4]/div[1]/div[1]/div[4]/div[1]/div[1]/div[1]/div[1]/div[4]/div[2]")
	WebElement nonStopAttributeValue1;
	
	@FindBy(how=How.XPATH,using="//div//div//div//div//div//div[1]//div[2]//div[1]//div[1]//div[1]/button/div")
	WebElement DepartureTimeSlot_click;
	
	@FindBy(how=How.XPATH,using="//*[@id=\"content\"]/div/div[4]/div[1]/div/div[4]/div[1]/div/div[1]/div[1]/div[3]/div[1]")
	WebElement flightDepartureTime_AttributeValue1;
	
	@FindBy(how=How.XPATH,using="/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[3]/div[1]/div[2]/span[1]/div[4]/span[1]/span[1]")
	WebElement selectAirlines_Click;
	
	@FindBy(how=How.XPATH,using="//*[@id=\"content\"]/div/div[4]/div[1]/div/div[4]/div[1]/div/div[1]/div[1]/div[3]/div[4]/div")
	WebElement AirlinesName_AttributeValue1;
	
	public void ixigoBooking_page()
	{
		try 
		{
			String currentPageTitle02 = driver.getTitle();
			Assert.assertTrue(currentPageTitle02.contains("Pune - Hyderabad, Economy Flights, roundtrip, 17 Sep - 24 Oct"));
			
			//5.	Validate filter option for Stops, departure and Airlines – Select Non-Stop in Stops filter option
					
			//5.2. 1 Stop
			oneStopButton_click.click();
			String AttrValue2 = oneStopAttributeValue2.getText();
			System.out.println("1 stop - filter validation: "+AttrValue2);
			Assert.assertTrue(AttrValue2.contains("1 stop"), "1 stop - filter not matched");
			oneStopButton_click.click();
			
			//5.3. 1+ Stops
			multiStopsCB_click.click();
			String AttrValue3 = muultiStopsAttributeValue3.getText();
			System.out.println("1+ stops - filter validation: "+AttrValue3);
			Assert.assertTrue(AttrValue3.contains("stops"), "1+ stops - filter not matched");
			multiStopsCB_click.click();
			
			//5.1. Non Stop
			nonStopCB_Click.click();
			String AttrValue = nonStopAttributeValue1.getText();
			System.out.println("non-stop - filter validation: "+AttrValue);
			Assert.assertTrue(AttrValue.contains("non-stop"), "non-stop - filter not matched");
			//nonStopCB_Click.click();
			
			//departureTime slots
			DepartureTimeSlot_click.click();
			String departureTime = flightDepartureTime_AttributeValue1.getText();
			String[] Time01 = departureTime.split(":");
			int DeptTimeComp01 = Integer.parseInt(Time01[0]);
			System.out.println("Time01: "+Time01[0]);
			if(DeptTimeComp01 <06) 
			{
				System.out.println("flight departure time is as expected: PASS");
			}else 
			{
				System.out.println("flight departure time is as expected: FAIL");
			}
			DepartureTimeSlot_click.click();
			
			//Airlines
			selectAirlines_Click.click();
			String AirlineName = AirlinesName_AttributeValue1.getText();
			Assert.assertTrue(AirlineName.contains("IndiGo"));
			selectAirlines_Click.click();
			
			//Reset Filters:
			//driver.findElement(By.xpath("//div[@class='rst-fltrs u-link']")).click();
			
			//6.	Print the list of airlines details (Only Airline Number, Departure Time and Fare) having fare < 5000
			
			//total number of flights available
			List<WebElement> numberOfFlights = driver.findElements(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[4]/div[1]/div[1]/div[4]/div[1]/div[1]/div"));
			System.out.println("number of flights available: "+numberOfFlights.size());
			System.out.println("================================");
			for(int i=1;i<=numberOfFlights.size();i++)
			{
				//each available flight's fare
				String flightFareValue = driver.findElement(By.xpath("/html/body/div[1]/div/div[4]/div[1]/div/div[4]/div[1]/div/div["+i+"]/div[1]/div[5]/div/div/span[2]")).getText();
				System.out.println("available flight's fare value: "+flightFareValue);
				int flightFareInt = Integer.parseInt(flightFareValue);
				if(flightFareInt<5000)
				{
					//get available flight's number
					String FlightName = driver.findElement(By.xpath("/html/body/div[1]/div/div[4]/div[1]/div/div[4]/div[1]/div/div["+i+"]/div[1]/div[3]/div[4]/div")).getText();
					//String[] flightNumber = FlightName.split(" ");
					System.out.println("flight number is: "+FlightName);
					
					//flight departure time
					String FlightdepartureTime = driver.findElement(By.xpath("/html/body/div[1]/div/div[4]/div[1]/div/div[4]/div[1]/div/div["+i+"]/div[1]/div[3]/div[1]")).getText();
					System.out.println("available flight departure time: "+ FlightdepartureTime);
				}
				System.out.println("========================");
			}

			
		}catch(Exception ex)
		{
			System.out.println("Exception handling - testqa - ixigoBooking_page");
			System.out.println(ex.getStackTrace());
			System.out.println(ex.getMessage());
		}
	}
	
}
