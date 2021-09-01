package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.qa.base.BaseClass;

public class Com_tshirtCategoryPage extends BaseClass {

	By MouseHoverOnTshirt = By
			.cssSelector("#center_column > ul > li > div > div.left-block > div > a.product_img_link > img");
	By SelectTshirt = By.cssSelector(
			"#center_column > ul > li > div > div.right-block > div.button-container > a.button.ajax_add_to_cart_button.btn.btn-default");
	By SuccessMessage = By.xpath("//*[@id='layer_cart']/div[1]/div[1]/h2");

	public Com_tshirtCategoryPage(WebDriver driver) {
		this.driver = driver;
	}

	public void SelectTheTshirt() throws InterruptedException {

		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(MouseHoverOnTshirt)).build().perform();
		act.click(driver.findElement(SelectTshirt)).build().perform();
		Thread.sleep(5000);

	}

	public String AsssetSuccessMessage() {

		String parentWindowHandle = driver.getWindowHandle();
		driver.switchTo().window(parentWindowHandle);
		String SuccessMessege = driver.findElement(SuccessMessage).getText();
		return SuccessMessege;

	}

}
