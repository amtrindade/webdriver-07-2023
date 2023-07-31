package com.test;

import static org.junit.Assert.assertEquals;

import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ActionsNavigateTest {
	
	private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "/home/atrindade/Dev/drivers/chromedriver");
		driver = new ChromeDriver();
		driver.get("http://antoniotrindade.com.br/treinoautomacao/index.html");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
	
	@Test
	public void testNavigationActions() throws InterruptedException {
		
		assertEquals("Treino Automação de Testes", driver.getTitle());
		
		WebElement linkCalculadora = driver.findElement(By.linkText("Calculadora"));
		linkCalculadora.click();
		Thread.sleep(1000);
		
		assertEquals("Desafio Automação Cálculos", driver.getTitle());
		
		WebElement linkTables = driver.findElement(By.linkText("Localizar Table"));
		linkTables.click();
		Thread.sleep(1000);
		
		assertEquals("Trabalhando com tables", driver.getTitle());
		
		driver.navigate().back();
		Thread.sleep(1000);
		assertEquals("Desafio Automação Cálculos", driver.getTitle());
		
		driver.navigate().back();
		Thread.sleep(1000);
		assertEquals("Treino Automação de Testes", driver.getTitle());
		
		driver.navigate().forward();
		Thread.sleep(1000);
		assertEquals("Desafio Automação Cálculos", driver.getTitle());
		
		driver.navigate().forward();
		Thread.sleep(1000);
		assertEquals("Trabalhando com tables", driver.getTitle());
		
	}

}
