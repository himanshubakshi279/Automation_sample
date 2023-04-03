package com.Automation.framework.pages;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.Automation.framework.utility.ExcelUtil;
import com.Automation.framework.utility.Utility;
import com.Automation.framework.utility.WebUtils;

public class LoginPage {

	WebDriver driver;
	private WebUtils webUtils;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		webUtils = new WebUtils(driver);
	}

	
	
	@FindBy(xpath = "//a[@href='/verify/login']")
	public WebElement Lbtn;
	
	@FindBy(xpath = "//div[contains(text(),'Access Your User Account')]")
	public WebElement knsb;

	@FindBy(xpath = "//input[@id='Email']")
	public WebElement EBox;

	@FindBy(xpath = "//input[@id='Password']")
	public WebElement PBox;

	@FindBy(xpath = "//button[@type='submit']")
	public WebElement Submitbtn;

	@FindBy(xpath = "//div[contains(@class,'company-name')]")
	public WebElement CompanyName;

	@FindBy(id = "dropdownMenuButton")
	public WebElement PDropdown;

	@FindBy(xpath = "//div[@class='login-right']")
	public WebElement LoginPage;
	
	public void GoLogin() throws IOException {
		long startTime = System.currentTimeMillis();
		webUtils.actionClick(Lbtn);
		//webUtils.waitForPageLoad(driver);
		webUtils.waitForElementVisibility(knsb);
		long actualTime = System.currentTimeMillis() - startTime;
		if (webUtils.isElementDisplayed(knsb)) {
			new ExcelUtil().open().navigateToSheet("Timeout")
					.writeToSheet(3, new String[] { "Total Load Time of Login Page ", "" + actualTime + "" })
					.saveAndClose();
		} else {
			new ExcelUtil().open().navigateToSheet("Timeout")
					.writeToSheet(3, new String[] { "Total Load Time of Login Page ", "[null]" }).saveAndClose();
		}
		webUtils.sleepforloader();
	}
	

	public void Action1() throws IOException {
		String email = Utility.fetchPropertyValue("Email").toString();
		webUtils.enterText(EBox, email);
	}

	public void Action2() throws IOException {
		String password = Utility.fetchPropertyValue("Password").toString();
		webUtils.enterText(PBox, password);
		webUtils.sleepenteringdataintextbox();
	}
	
	
	public void Action3() throws InterruptedException, IOException {
		long startTime = System.currentTimeMillis();
		Submitbtn.click();
		webUtils.waitForPageLoad(driver);
		webUtils.waitForElementVisibility(CompanyName);
		long actualTime = System.currentTimeMillis() - startTime;
		if (webUtils.isElementDisplayed(PDropdown)) {
			new ExcelUtil().open().navigateToSheet("Timeout")
					.writeToSheet(4,
							new String[] { "Total Page Load Time of Dashboard Page ", "" + actualTime + "" })
					.saveAndClose();
		} else {
			new ExcelUtil().open().navigateToSheet("Timeout")
					.writeToSheet(4, new String[] { "Total Page Load Time of Dashboard Page ", "[null]" })
					.saveAndClose();
		}webUtils.sleepforloader();
	}

	public void VerifyNextPage() {
		if (webUtils.getPageTitle().contains("AutomationProject | Dashboard - Charts"))
			System.out.println("Signin Success.");
		else
			System.out.println(webUtils.getPageTitle());
	}

}
