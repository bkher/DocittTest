package com.qa.test;

import org.testng.AssertJUnit;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.base.BaseClass2;
import com.qa.pages.Com_HomePageAfterLogin;
import com.qa.pages.Com_LoginPage;
import com.qa.pages.Com_tshirtCategoryPage;
import com.qa.pages.com_HomePageBeforeLogin;

@Listeners(com.reort.Log.class)

public class AutomationPracticeTest2 extends BaseClass2 {

	Com_HomePageAfterLogin page2;
	Com_LoginPage page1;
	Com_tshirtCategoryPage page3;
	com_HomePageBeforeLogin page0;

	@Test(enabled = true, priority = 1, description = "Test 1")

	public void AddTshirtInCartScenario() throws InterruptedException {

		logger = report.createTest("login and Add Tsgirt on Cart");
		try {
			logger.info("CLick on Login");
			page1 = new Com_LoginPage(driver);
			page1.ClickLoginLink();
			logger.info("Enter Username and password");
			page1.EnterUsername("jetblue@grr.la");
			page1.EnterPassword("jetblue");
			page1.ClickOnLogin();
			logger.info("user Loged in");
			page2 = new Com_HomePageAfterLogin(driver);
			page2.ClickOnTshirt();
			logger.info("CLicked on Tshirt");
			page3 = new Com_tshirtCategoryPage(driver);
			page3.SelectTheTshirt();
			String SucceessMessage = page3.AsssetSuccessMessage();
			AssertJUnit.assertEquals(SucceessMessage, "Product successfully added to your shopping cart",
					"Test is not same as mentioned");
			logger.pass("tesgrt added on Cart");
		} catch (Exception e) {
			logger.fail(e);
		}
	}

	@Test(enabled = true, priority = 2, description = "Test 2")
	public void GetAllLinksOfMenu() {

		logger = report.createTest("Clicked multiple link from home page");

		try {
			logger.info("Get All links");
			page0 = new com_HomePageBeforeLogin(driver);
			page0.clickMenuLinks();
			logger.pass("Links clicked");

		} catch (Exception e) {
			// TODO: handle exception
			logger.fail(e);
		}
	}

}
