package com.page;

import static com.core.DriverFactory.getDriver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchTaskPage {
	
	public SearchTaskPage clickClear() {
		WebElement btnClear = getDriver().findElement(By.id("scheduleList_doClear"));
		btnClear.click();
		return this;
	}
	
	public Boolean searchBy(String value) {
		WebElement tfFilter = getDriver().findElement(By.id("genericFilter"));
		tfFilter.sendKeys(value);
		
		WebElement btnSearch = getDriver().findElement(By.id("scheduleList_doSearch"));
		btnSearch.click();
		
		List<WebElement> tdTableListTask = getDriver().findElements(By.xpath("//a[contains(text(),'"+value+"')]"));
		return tdTableListTask.size() > 0;
	}

}
