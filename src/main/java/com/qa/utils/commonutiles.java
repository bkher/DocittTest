package com.qa.utils;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.base.BaseClass;

public class commonutiles extends BaseClass {

	public static String getvlueByJpath(JSONObject responsejson, String Jpath) {

		Object obj = responsejson;
		for (String s : Jpath.split("/"))
			if (!s.isEmpty())
				if (!(s.contains("[") || s.contains("]")))
					obj = ((JSONObject) obj).get(s);
				else if (!(s.contains("[") || s.contains("]")))
					obj = ((JSONArray) ((JSONObject) obj).get(s.split("\\[")[0]))
							.get(Integer.parseInt(s.split("\\[")[1].replaceAll("]", "")));
		return obj.toString();
	}

	public void WaitforElement(WebDriver driver, By locator) {

		WebElement FirstNameTxtBox = driver.findElement(locator);
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOf(FirstNameTxtBox));
		} catch (Exception e) {
			System.out.println("Element not visible");
		}
	}

	public void WaitforElement1(WebDriver driver, WebElement locator) {

		// WebElement FirstNameTxtBox = driver.findElement(locator);
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOf(locator));
		} catch (Exception e) {
			System.out.println("Element not visible");
		}
	}

	public void enterText(WebDriver driver, By locator, String inputString) {
		Actions actions = new Actions(driver);
		WebElement FirstNameTxtBox = driver.findElement(locator);
		actions.moveToElement(FirstNameTxtBox);
		actions.click();
		actions.sendKeys(inputString);
		actions.build().perform();

	}

	public void clickButton(WebDriver driver, By locator) {
		Actions actions = new Actions(driver);
		WebElement button = driver.findElement(locator);
		actions.moveToElement(button);
		actions.click();
		actions.build().perform();
	}

	public String capturescreenshot(WebDriver driver) {

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String screenshotPath = System.getProperty("user.dir") + "//Screenshot//scrren" + GetDateAndTime() + ".png";

		try {
			FileHandler.copy(src, new File(screenshotPath));
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("unable to captute " + e.getMessage());
		}
		return screenshotPath;
	}

	public String GetDateAndTime() {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddmmyyyy HH:MM:ss");
		LocalDateTime now = LocalDateTime.now();

		return dtf.format(now);
	}
}
