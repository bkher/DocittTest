package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.base.BaseClass;
import com.qa.utils.commonutiles;

public class Com_LoginPage extends BaseClass {

//	WebDriver driver;
	commonutiles utils = new commonutiles();

	By usrname = By.name("email");
	By password = By.id("passwd");
	By LoginClick = By.cssSelector("#header > div.nav > div > div > nav > div.header_user_info > a");
	By SignInbutton = By.name("SubmitLogin");

	public Com_LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public void ClickLoginLink() {
		utils.WaitforElement(driver, LoginClick);
		utils.clickButton(driver, LoginClick);
	}

	public void EnterUsername(String username) {

		utils.WaitforElement(driver, usrname);
		utils.enterText(driver, usrname, username);
	}

	public void EnterPassword(String Password) {
		utils.WaitforElement(driver, password);
		utils.enterText(driver, password, Password);

	}

	public void ClickOnLogin() throws InterruptedException {
		utils.WaitforElement(driver, SignInbutton);
		utils.clickButton(driver, SignInbutton);
		Thread.sleep(5000);
	}

}
