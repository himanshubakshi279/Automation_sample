package com.Automation.framework.testcases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.Automation.framework.base.BaseTest;
import com.Automation.framework.base.DriverInstance;
import com.Automation.framework.pages.AddBrandPage;
import com.Automation.framework.pages.DashboardPage;

public class TC_002_Brand extends BaseTest {

	WebDriver driver;

	@Test(priority = 1)
	public void tc_002_01_Add_New_Brand() throws Exception {

		test = extent.createTest("Verify adding a new brand.");
		driver = DriverInstance.getInstance().getDriver();
		Thread.sleep(3000);
		DashboardPage Dashboard = new DashboardPage(driver);
		Dashboard.clkProfileDropdown();
		Dashboard.clickProfileListAddBrand();
		AddBrandPage AddBrand = new AddBrandPage(driver);
		AddBrand.VerifyAddBrandPage();
		AddBrand.EnterBrandName();
		AddBrand.EnterBusinessURL();
		AddBrand.SelectCountry();
		AddBrand.ClickSaveButton();
		AddBrand.VerifyAddedBrand();
		AddBrand.ClickSaveBrand();
		
	}
	
	@Test(priority = 2)
	public void tc_002_02_Add_Brand_Add_Address() throws IOException, InterruptedException {

		test = extent.createTest("Verify adding a new address in add brand page.");
		AddBrandPage AddBrand = new AddBrandPage(driver);
		AddBrand.AddNewAddress();
		AddBrand.AddAddress1();
		AddBrand.Address2();
		AddBrand.SelectAddressState();
		AddBrand.EnterAddressCity();
		AddBrand.AddressZip();
		AddBrand.AddressSaveButton();
		AddBrand.VerifyAddAddress();
		
	}
	
	@Test(priority = 3)
	public void tc_002_03_replaceDomain() throws Exception {
		
		TC_003_Replace_Domain Replace_Domain = new TC_003_Replace_Domain();
		Replace_Domain.Replace_Domain();
	}

}
