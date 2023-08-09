package com.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.core.BaseTest;
import com.core.GlobalProperty;
import com.page.LoginPage;
import com.page.MainPage;
import com.page.SearchTaskPage;

public class TaskTest extends BaseTest{
	
	private LoginPage loginPage;
	private MainPage mainPage;
	private SearchTaskPage searchTaskPage;
	
	@Before
	public void setUp() {
		loginPage = new LoginPage();
		
		mainPage = loginPage.open()
				.inputEnvironment(GlobalProperty.getProperty("webdriver.environment"))
				.inputUserName(GlobalProperty.getProperty("webdriver.username"))
				.inputPassword(GlobalProperty.getProperty("webdriver.password"))
				.clickLogar();
		
		searchTaskPage = mainPage.clickMenuTarefa();
	}
	
	@Test
	public void testSearchTaskByLocal() {
		searchTaskPage.clickClear();
		assertTrue(searchTaskPage.searchBy("Bortoluzzi Correias"));				
	}
	
	@Test
	public void testSearchTaskByLocalInexist() {
		searchTaskPage.clickClear();
		assertFalse(searchTaskPage.searchBy("Correias Borto"));				
	}
	
	@Test
	public void testSearchTaskByAgent() {
		searchTaskPage.clickClear();
		assertTrue(searchTaskPage.searchBy("Aluno 02"));				
	}
	
	@Test
	public void testSearchTaskByAgentInexist() {
		searchTaskPage.clickClear();
		assertFalse(searchTaskPage.searchBy("Aluno 01"));				
	}

}
