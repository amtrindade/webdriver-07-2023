package com.page;

import static com.core.DriverFactory.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.core.GlobalProperty;

public class LoginPage {
	
	public LoginPage open() {
		getDriver().get(GlobalProperty.getProperty("webdriver.url.dese"));
		return this;
	}

	public LoginPage inputEnvironment(String environment) {
		WebElement tfWorkspace = getDriver().findElement(By.id("workspace"));
		tfWorkspace.sendKeys(environment);		
		return this;		
	}
	
	public LoginPage inputUserName(String user) {
		WebElement tfUsername = getDriver().findElement(By.id("username"));
		tfUsername.sendKeys(user);
		return this;
	}
	
	public LoginPage inputPassword(String pass) {
		WebElement tfPassword = getDriver().findElement(By.id("password"));
		tfPassword.sendKeys(pass);
		return this;
	}
	
	public MainPage clickLogar() {
		WebElement btnLogar = getDriver().findElement(By.id("submit_button"));
		btnLogar.click();
		
		return new MainPage();		
	}
	
	public LoginPage clickLogarInvalido() {
		WebElement btnLogar = getDriver().findElement(By.id("submit_button"));
		btnLogar.click();
		
		return this;
	}
	
	public String getMessageError() {
		WebElement liMessageError = getDriver().findElement(By.xpath("//li[@class='nm-li nm-message-error']"));
		return liMessageError.getText();
	}

}
