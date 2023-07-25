package com.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebElementsTest {

	private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "/home/atrindade/Dev/drivers/chromedriver");
		// System.setProperty("webdriver.chrome.driver",
		// "C:\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://antoniotrindade.com.br/treinoautomacao/elementsweb.html");
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void testValidaTextField() throws InterruptedException {

		// Identifica o elemento na página
		WebElement textFieldBox1 = driver.findElement(By.name("txtbox1"));

		// Faz a iteração com o elemento
		textFieldBox1.sendKeys("Antônio Trindade");

		Thread.sleep(3000);

		// faz a validação
		assertEquals("Antônio Trindade", textFieldBox1.getAttribute("value"));
	}
	
	@Test
	public void testValidaStatusTextFields() {
		// Indentificar os elementos
		WebElement textFieldBoxEnable = driver.findElement(By.name("txtbox1"));
		WebElement textFieldBoxDisable = driver.findElement(By.name("txtbox2"));
		
		assertTrue(textFieldBoxEnable.isEnabled());
		assertFalse(textFieldBoxDisable.isEnabled());		
	}
}
