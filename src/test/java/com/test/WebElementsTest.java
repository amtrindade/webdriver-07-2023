package com.test;

import static org.junit.Assert.*;

import java.util.List;

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

		// Faz a validação
		assertEquals("Antônio Trindade", textFieldBox1.getAttribute("value"));
	}

	@Test
	public void testValidaStatusTextFields() {
		// Identificar os elementos
		WebElement textFieldBoxEnable = driver.findElement(By.name("txtbox1"));
		WebElement textFieldBoxDisable = driver.findElement(By.name("txtbox2"));

		// Faz a validação
		assertTrue(textFieldBoxEnable.isEnabled());
		assertFalse(textFieldBoxDisable.isEnabled());
	}
	
	@Test
	public void testValidaRadioButton() throws InterruptedException {
		// Identicar os elementos
		List<WebElement> radioButtons = driver.findElements(By.name("radioGroup1"));
		
		//radioButtons.get(2).click();
		
		for (WebElement rb : radioButtons) {
			//System.out.println(rb.getAttribute("value"));
			if (rb.getAttribute("value").equals("Radio 3")) {
				rb.click();
				break;
			}
		}
				
		// valida tamanho da lista
		assertEquals(4, radioButtons.size());
		
		assertTrue(radioButtons.get(2).isSelected());
		
		assertFalse(radioButtons.get(0).isSelected());
		assertFalse(radioButtons.get(1).isSelected());
		assertFalse(radioButtons.get(3).isSelected());		
	}
}
