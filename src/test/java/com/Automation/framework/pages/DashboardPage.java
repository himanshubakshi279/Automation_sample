package com.Automation.framework.pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.Automation.framework.utility.ExcelUtil;
import com.Automation.framework.utility.WebUtils;

public class DashboardPage {

	WebDriver driver;
	private WebUtils webUtils;

	public DashboardPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		webUtils = new WebUtils(driver);
	}


	@FindBy(xpath = "//img[@src='assets/images/dropdown.svg']")
	public WebElement PDrpdn;
	
	@FindBy(xpath = "(//a[contains(@class,'ng-tns')])[11]")
	public WebElement ProfileListAddBrandbutton;
	
	@FindBy(xpath = "//p[contains(text(),'Add new Brand')]")
	public WebElement AddBrandPageTitle;
	
	
	public void clickProfileListAddBrand() throws InterruptedException, IOException {
		long startTime = System.currentTimeMillis();
		webUtils.actionClick(ProfileListAddBrandbutton);
		webUtils.waitForPageLoad(driver);
		webUtils.waitForElementVisibility(AddBrandPageTitle);
		long actualTime = System.currentTimeMillis() - startTime;
		if (webUtils.isElementDisplayed(AddBrandPageTitle)) {
			new ExcelUtil().open().navigateToSheet("Timeout")
					.writeToSheet(89, new String[] { "Total Load Time of Add New Brand Page from Profile List ", "" + actualTime + "" })
					.saveAndClose();
			System.out.println("Add New Brand page is displayed");
			
		} else {

			new ExcelUtil().open().navigateToSheet("Timeout")
					.writeToSheet(89, new String[] { "Total Load Time of Add New Brand Page from Profile List ", "[null]" })
					.saveAndClose();
			System.out.println("Add New Brand page is not displayed");
		}
		webUtils.sleepforloader();
	}
	
	public void clkProfileDropdown() throws InterruptedException, IOException {
		webUtils.scrollingToElementofAPage(PDrpdn);
		webUtils.actionClick(PDrpdn);
	}
}
