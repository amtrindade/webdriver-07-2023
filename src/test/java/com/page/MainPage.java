package com.page;

import static com.core.DriverFactory.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MainPage {
	
	public String getTextAvatar() {
		WebElement divAvatar = getDriver().findElement(By
				.xpath("//*[@class='topbar-widget profile-widget']/img"));
		divAvatar.click();
		
		WebElement divTextLogin = getDriver().findElement(By
				.xpath("//*[@class='text-login']/.."));
		return divTextLogin.getText();		
	}
	
	public SearchTaskPage clickMenuTarefa() {
		WebElement menuTask = getDriver().findElement(By.xpath("//span[.='Task']"));
		menuTask.click();
		
		return new SearchTaskPage();
	}

}
