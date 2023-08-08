package com.test;

import static org.junit.Assert.assertEquals;

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
		loginPage.inputEnvironment("xxxx");
		loginPage.inputUserName("xxxx");
		loginPage.inputPassword("xxxx");
		
		mainPage = loginPage.clickLogar();
		assertEquals("Aluno 01 (aluno01)", mainPage.getTextAvatar());
		
	}

}
