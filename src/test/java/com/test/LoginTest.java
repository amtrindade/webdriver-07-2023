package com.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.core.BaseTest;
import com.core.GlobalProperty;
import com.page.LoginPage;
import com.page.MainPage;

public class LoginTest extends BaseTest{
	
	private LoginPage loginPage;
	private MainPage mainPage;
	
	@Test
	public void testLoginSucesso() {
		loginPage = new LoginPage();
		
		loginPage.open();
		loginPage.inputEnvironment(GlobalProperty.getProperty("webdriver.environment"));
		loginPage.inputUserName(GlobalProperty.getProperty("webdriver.username"));
		loginPage.inputPassword(GlobalProperty.getProperty("webdriver.password"));
		
		mainPage = loginPage.clickLogar();
		assertEquals("Aluno 01 (aluno01)", mainPage.getTextAvatar());		
	}
	
	@Test
	public void testLoginInvalido() {
		loginPage = new LoginPage();
		
		loginPage.open();
		loginPage.inputEnvironment("ambienteerrado");
		loginPage.inputUserName("usuario");
		loginPage.inputPassword("senha");
		
		loginPage.clickLogarInvalido();
		
		assertEquals("ERROR\nLOGIN INVALID.", loginPage.getMessageError());
		
	}

}
