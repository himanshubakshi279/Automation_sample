package com.Automation.framework.base;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;

import com.Automation.framework.utility.ExcelUtil;
import com.Automation.framework.utility.Utility;
import com.Automation.google.GoogleHandling;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverInstance {

	private static DriverInstance factory = new DriverInstance();
	private WebDriver driver;
	DateFormat dateFormat = new SimpleDateFormat("MM-dd");
	Date date = new Date();
	String dateFormatted = dateFormat.format(date);
	String ColumnName = "Load Time in milliseconds " + dateFormatted;

	private DriverInstance() {
	}

	public static DriverInstance getInstance() {
		return factory;
	}

	public WebDriver getDriver() throws IOException {
		String Browser = Utility.fetchPropertyValue("browser").toString();

		if (driver == null && Browser.equalsIgnoreCase("firefox")) {
			driver = WebDriverManager.firefoxdriver().create();
			// To maximize the browser
			driver.manage().window().maximize();
			long startTime = System.currentTimeMillis();
			driver.get(Utility.fetchPropertyValue("URL").toString());
			long actualTime = System.currentTimeMillis() - startTime;
			System.out.println("Page Load Time of Main Page: " + actualTime + " milliseconds");
			new ExcelUtil().open().navigateToSheet("Timeout")
					.writeToSheet(2,
							new String[] { "Total load Time of Main Page in Firefox browser ", "" + actualTime + "" })
					.saveAndClose();

		} else if (driver == null && Browser.equalsIgnoreCase("Chrome")) {
			driver = WebDriverManager.chromedriver().avoidShutdownHook().create();
			new ExcelUtil().open().navigateToSheet("Timeout").writeToSheet(1, new String[] { "Page", "" + ColumnName })
					.saveAndClose();
			GoogleHandling.setcolumnName(ColumnName);
			new ExcelUtil().open().navigateToSheet("Dropdown_LoadTime")
					.writeToSheet(1, new String[] { "Dropdown", "" + ColumnName }).saveAndClose();
			GoogleHandling.setcolumnName(ColumnName);
			new ExcelUtil().open().navigateToSheet("Table_Load_Time")
					.writeToSheet(1, new String[] { "Table Name", "" + ColumnName }).saveAndClose();
			GoogleHandling.setcolumnName(ColumnName);

			// To maximize the browser
			driver.manage().window().maximize();
			long startTime = System.currentTimeMillis();
			driver.get(Utility.fetchPropertyValue("URL").toString());
			driver.findElement(By.xpath("//*[@routerlink='/verify/login']")).isDisplayed();
			long actualTime = System.currentTimeMillis() - startTime;
			System.out.println("Page Load Time of Main Page: " + actualTime + " milliseconds");
	        new ExcelUtil().open().navigateToSheet("Timeout").clearExcelFile(2);
			new ExcelUtil().open().navigateToSheet("Timeout")
					.writeToSheet(2,
							new String[] { "Total load Time of Main Page Chrome browser ", "" + actualTime + "" })
					.saveAndClose();
		}

		else if (driver == null && Browser.equalsIgnoreCase("Edge")) {
			driver = WebDriverManager.edgedriver().create();
			// To maximize the browser
			driver.manage().window().maximize();
			long startTime = System.currentTimeMillis();
			driver.get(Utility.fetchPropertyValue("URL").toString());
			long actualTime = System.currentTimeMillis() - startTime;
			System.out.println("Page Load Time of Main Page: " + actualTime + " milliseconds");
			new ExcelUtil().open().navigateToSheet("Timeout")
					.writeToSheet(2,
							new String[] { "Total load Time of Main Page in Edge browser ", "" + actualTime + "" })
					.saveAndClose();
		}

		else if (driver == null && Browser.equalsIgnoreCase("IE")) {
			driver = WebDriverManager.iedriver().create();
			// To maximize the browser
			driver.manage().window().maximize();
			long startTime = System.currentTimeMillis();
			driver.get(Utility.fetchPropertyValue("URL").toString());
			long actualTime = System.currentTimeMillis() - startTime;
			System.out.println("Page Load Time of Main Page: " + actualTime + " milliseconds");
			new ExcelUtil().open().navigateToSheet("Timeout")
					.writeToSheet(2,
							new String[] { "Total load Time of Main Page in IE browser ", "" + actualTime + "" })
					.saveAndClose();
		}

		else if (driver == null && Browser.equalsIgnoreCase("Safari")) {
			driver = WebDriverManager.safaridriver().create();
			// To maximize the browser
			driver.manage().window().maximize();
			long startTime = System.currentTimeMillis();
			driver.get(Utility.fetchPropertyValue("URL").toString());
			long actualTime = System.currentTimeMillis() - startTime;
			System.out.println("Page Load Time of Main Page: " + actualTime + " milliseconds");
			new ExcelUtil().open().navigateToSheet("Timeout")
					.writeToSheet(2,
							new String[] { "Total load Time of Main Page in Safari Browser ", "" + actualTime + "" })
					.saveAndClose();
		}

		return driver;
	}

	@AfterMethod
	public void CloseDriverInstance() {
		// driver.quit();
	}

}
