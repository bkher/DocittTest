package com.qa.pages;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.qa.base.BaseClass;
import com.qa.utils.commonutiles;

public class com_HomePageBeforeLogin extends BaseClass {

	commonutiles utils = new commonutiles();

	@FindAll({ @FindBy(xpath = "//li/a[text()='T-shirts']/ancestor::div/ul/li[1]"),
			@FindBy(xpath = "//li/a[text()='T-shirts']/ancestor::div/ul/li[2]") })
	List<WebElement> ancestor;

	@FindAll({ @FindBy(xpath = "//div[@id='block_top_menu']/ul/child::li[1]"),
			@FindBy(xpath = "//div[@id='block_top_menu']/ul/child::li[2]") })
	List<WebElement> child;

	@FindAll({ @FindBy(xpath = "//div[@id='block_top_menu']/ul/descendant::li[1]"),
			@FindBy(xpath = "//div[@id='block_top_menu']/ul/descendant::li[2]") })
	List<WebElement> descendant;

	public com_HomePageBeforeLogin(WebDriver driver) {
		Reporter.log("intialize constructor", true);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickMenuLinks() {
		// utils.WaitforElement1(driver, MenuItems);
		// System.out.println(MenuItems);
		Reporter.log("call ClickMenu links methods", true);
		Iterator<WebElement> it = descendant.iterator();
		// it.next();
		for (WebElement webElement : descendant) {
			System.out.println(webElement);
			webElement.click();
		}
		Reporter.log("call ClickMenu links methods completed", true);
	}

}
