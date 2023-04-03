package com.Automation.framework.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.Automation.framework.base.BaseTest;
import com.Automation.framework.base.DriverInstance;
import com.Automation.framework.pages.LoginPage;

public class TC_001_Login extends BaseTest {
	WebDriver driver;

	@Test(priority = 1)
	public void tc_001_01_Login_as_End_User_Subscriber() throws Exception {
		test = extent.createTest("Verify login with End User Subscriber with valid details.");
		driver = DriverInstance.getInstance().getDriver();
		Thread.sleep(3000);
		LoginPage login = new LoginPage(driver);
		login.GoLogin();
		login.Action1();
		login.Action2();
		login.Action3();
		login.VerifyNextPage();
	}
}