package com.Automation.framework.pages;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Automation.framework.utility.ExcelUtil;
import com.Automation.framework.utility.WebUtils;

public class AddBrandPage {

	WebDriver driver;
	private WebUtils webUtils;
	DateFormat dateFormat = new SimpleDateFormat("MMdd");
	Date date = new Date();
	String dateFormatted = dateFormat.format(date);

	int ran = 100 + (int) (Math.random() * ((10000 - 100) + 1));
	String BrandName = "qabrand" + ran + dateFormatted;
	String BussinessURL = "yopmail.com";
	String Address1 = "Test Address1 " + ran;
	String Address2 = "Test Address2 " + ran;

	public AddBrandPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		webUtils = new WebUtils(driver);
	}

	@FindBy(xpath = "//input[@id='Name']")
	public WebElement BrandNameField;

	@FindBy(xpath = "//input[@placeholder='example.com']")
	public WebElement BusinessURLField;

	@FindBy(xpath = "//*[@id='brandCountry']")
	public WebElement SelectCountryDropdown;

	@FindBy(xpath = "//button[@title='Click here to Save']")
	public WebElement SaveButton;

	@FindBy(xpath = "//button[@type='button'][contains(text(),'Save')]")
	public WebElement SaveBrand;

	@FindBy(xpath = "//app-toasts/div/div/div[1]")
	public WebElement ToastMessage;

	@FindBy(xpath = "//app-toasts/div/div/div[1]/span[2]")
	private WebElement CloseToastMessage;

	@FindBy(xpath = "//span[@title='Click here to add address']")
	public WebElement ClickAddNewAddress;

	@FindBy(xpath = "//input[@id='addressLine1']")
	public WebElement ClickAddress1;

	@FindBy(xpath = "//input[@id='addressLine1']")
	public WebElement EnterAddress1;

	@FindBy(xpath = "//*[contains(@class,'address2-qa')]")
	public WebElement EnterAddress2;

	@FindBy(xpath = "//*[@id='State']")
	public WebElement SelectState;

	@FindBy(xpath = "//*[@id='City']")
	public WebElement AddressCity;

	@FindBy(xpath = "//*[@id='zip']")
	public WebElement AddressZip;

	@FindBy(xpath = "//button[@type='submit']")
	public WebElement AddressSave;
	
	@FindBy(xpath = "//div[1]/span[contains(text(),'United States')]")
	public WebElement USContry;
	
	@FindBy(xpath = "//span[contains(text(),'Alabama')]")
	public WebElement USState;
	
	@FindBy(xpath = "//span[contains(text(),'Abbeville')]")
	public WebElement AlbamaCity;

	@FindBy(xpath = "//span[contains(text(),'Texas')]")
	public WebElement Texas;
	
	@FindBy(xpath = "//span[contains(text(),'Austin')]")
	public WebElement USAustin;
	
	@FindBy(xpath = "//span[contains(text(),'Add New Address Line1')]")
	public WebElement SelectAddress;
	
	
	public void VerifyAddBrandPage() {
		if (webUtils.getPageTitle().contains("aithentic | Add - Brand")) {
			System.out.println("Add Brand Page is showing.");
		} else {
			System.out.println(webUtils.getPageTitle());
		}
	}

	public void EnterBrandName() throws InterruptedException, IOException {
		webUtils.enterText(BrandNameField, BrandName);
	}

	public void EnterBusinessURL() throws InterruptedException, IOException {
		webUtils.enterText(BusinessURLField, BussinessURL);
	}
	
	public void SelectCountry() throws InterruptedException, IOException {
		webUtils.actionClick(SelectCountryDropdown);
		long startTime = System.currentTimeMillis();
		SelectCountryDropdown.sendKeys("United States");
		webUtils.waitForElementVisibility(USContry);
		long actualTime = System.currentTimeMillis() - startTime;
		if (webUtils.isElementDisplayed(USContry)) {
	        new ExcelUtil().open().navigateToSheet("Dropdown_LoadTime").clearExcelFile(2);
			new ExcelUtil().open().navigateToSheet("Dropdown_LoadTime")
					.writeToSheet(2,
							new String[] { "Add Brand Page - Total Load Time of country dropdown.", "" + actualTime + "" })
					.saveAndClose();
		} else {
	        new ExcelUtil().open().navigateToSheet("Dropdown_LoadTime").clearExcelFile(2);
			new ExcelUtil().open().navigateToSheet("Dropdown_LoadTime")
					.writeToSheet(2, new String[] { "Add Brand Page - Total Load Time of country dropdown.", "[null]" })
					.saveAndClose();
		}
		webUtils.actionClick(USContry);
		//SelectCountryDropdown.sendKeys(Keys.ARROW_UP, Keys.ENTER);
	}

	public void ClickSaveButton() throws InterruptedException, IOException {
		webUtils.actionClick(SaveButton);
	}
	
	public void ClickSaveBrand() throws InterruptedException, IOException {
		webUtils.actionClick(SaveBrand);
		
	}
	public void VerifyAddedBrand() throws IOException {
		webUtils.waitForElementVisibility(ToastMessage);
		if (ToastMessage != null && webUtils.getText(ToastMessage).contains("Brand created successfully")) {
			System.out.println("Brand created successfully.");
		} else {
			System.out.println(webUtils.getText(ToastMessage));
		}
		webUtils.actionClick(CloseToastMessage);
		webUtils.sleepforloader();
	}

	///////// Add new Address///////////
	public void AddNewAddress() throws InterruptedException, IOException {
		webUtils.actionClick(ClickAddNewAddress);
		webUtils.sleepforloader();	}

	public void AddAddress1() throws InterruptedException, IOException {
		webUtils.actionClick(ClickAddress1);
		long startTime = System.currentTimeMillis();
		EnterAddress1.sendKeys(Address1);
		webUtils.waitForElementVisibility(SelectAddress);
		long actualTime = System.currentTimeMillis() - startTime;
		if (webUtils.isElementDisplayed(SelectAddress)) {
	        new ExcelUtil().open().navigateToSheet("Dropdown_LoadTime").clearExcelFile(62);
			new ExcelUtil().open().navigateToSheet("Dropdown_LoadTime")
					.writeToSheet(62,
							new String[] { "Add Brand Page - Total Load Time of Address dropdown for adding a address.", "" + actualTime + "" })
					.saveAndClose();
		} else {
	        new ExcelUtil().open().navigateToSheet("Dropdown_LoadTime").clearExcelFile(62);
			new ExcelUtil().open().navigateToSheet("Dropdown_LoadTime")
					.writeToSheet(62, new String[] { "Add Brand Page - Total Load Time of Address dropdown for adding a address.", "[null]" })
					.saveAndClose();
		}
		webUtils.actionClick(SelectAddress);
	}

	public void Address2() throws InterruptedException, IOException {
		webUtils.enterText(EnterAddress2, Address2);
	}

	public void SelectAddressState() throws InterruptedException, IOException {
		webUtils.actionClick(SelectState);
		long startTime = System.currentTimeMillis();
		SelectState.sendKeys("Alabama");
		webUtils.waitForElementVisibility(USState);
		long actualTime = System.currentTimeMillis() - startTime;
		if (webUtils.isElementDisplayed(USState)) {
	        new ExcelUtil().open().navigateToSheet("Dropdown_LoadTime").clearExcelFile(3);
			new ExcelUtil().open().navigateToSheet("Dropdown_LoadTime")
					.writeToSheet(3,
							new String[] { "Add Brand Page - Total Load Time of State dropdown for adding a address.", "" + actualTime + "" })
					.saveAndClose();
		} else {
	        new ExcelUtil().open().navigateToSheet("Dropdown_LoadTime").clearExcelFile(3);
			new ExcelUtil().open().navigateToSheet("Dropdown_LoadTime")
					.writeToSheet(3, new String[] { "Add Brand Page - Total Load Time of State dropdown for adding a address.", "[null]" })
					.saveAndClose();
		}
		SelectState.sendKeys(Keys.ENTER);
		webUtils.sleepforloader();
	}

	public void EnterAddressCity() throws InterruptedException, IOException {
		webUtils.actionClick(AddressCity);
		long startTime = System.currentTimeMillis();
		AddressCity.sendKeys("Abbeville");
		webUtils.waitForElementVisibility(AlbamaCity);
		long actualTime = System.currentTimeMillis() - startTime;
		if (webUtils.isElementDisplayed(AlbamaCity)) {
	        new ExcelUtil().open().navigateToSheet("Dropdown_LoadTime").clearExcelFile(4);
			new ExcelUtil().open().navigateToSheet("Dropdown_LoadTime")
					.writeToSheet(4,
							new String[] { "Add Brand Page - Total Load Time of City dropdown for adding a address", "" + actualTime + "" })
					.saveAndClose();
		} else {
	        new ExcelUtil().open().navigateToSheet("Dropdown_LoadTime").clearExcelFile(4);
			new ExcelUtil().open().navigateToSheet("Dropdown_LoadTime")
					.writeToSheet(4, new String[] { "Add Brand Page - Total Load Time of City dropdown for adding a address.", "[null]" })
					.saveAndClose();
		}
		AddressCity.sendKeys(Keys.ENTER);
	}

	public void AddressZip() throws InterruptedException, IOException {
		webUtils.enterText(AddressZip, "12312");
	}

	public void AddressSaveButton() throws InterruptedException, IOException {
		webUtils.actionClick(AddressSave);
	}

	public void VerifyAddAddress() throws IOException {
		webUtils.waitForElementVisibility(ToastMessage);
		if (ToastMessage != null && webUtils.getText(ToastMessage).contains("Address created successfully")) {
			System.out.println("Address created successfully.");
		} else {
			System.out.println(webUtils.getText(ToastMessage));
		}
		webUtils.actionClick(CloseToastMessage);
	}

}
