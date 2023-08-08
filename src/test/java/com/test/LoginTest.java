package com.test;

import org.junit.Test;

import com.core.BaseTest;
import com.page.LoginPage;
import com.page.MainPage;

public class LoginTest extends BaseTest{
	
	private LoginPage loginPage;
	private MainPage mainPage;
	
	@Test
	public void testLoginSucesso() {
		loginPage = new LoginPage();
		
		loginPage.open();
		loginPage.inputEnvironment("trocaraqui");
		loginPage.inputUserName("trocaraqui");
		loginPage.inputPassword("trocaraqui");
		
		mainPage = loginPage.clickLogar();
		
	}

}
