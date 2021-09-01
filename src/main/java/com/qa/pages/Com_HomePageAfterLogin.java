package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.base.BaseClass;

public class Com_HomePageAfterLogin extends BaseClass {

	By TShirt = By.cssSelector("#block_top_menu > ul > li:nth-child(3) > a");

	public Com_HomePageAfterLogin(WebDriver driver) {
		this.driver = driver;
	}

	public void ClickOnTshirt() throws InterruptedException {
		driver.findElement(TShirt).click();
		Thread.sleep(8000);
	}

}
