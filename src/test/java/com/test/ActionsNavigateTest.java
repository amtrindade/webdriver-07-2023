package com.test;

import static com.core.DriverFactory.getDriver;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.core.BaseTest;

public class ActionsNavigateTest extends BaseTest{
	
	@Before
	public void setUp() throws Exception {
		getDriver().get("http://antoniotrindade.com.br/treinoautomacao/index.html");
	}
	
	@Test
	public void testNavigationActions() throws InterruptedException {
		
		assertEquals("Treino Automação de Testes", getDriver().getTitle());
		
		WebElement linkCalculadora = getDriver().findElement(By.linkText("Calculadora"));
		linkCalculadora.click();
		Thread.sleep(1000);
		
		assertEquals("Desafio Automação Cálculos", getDriver().getTitle());
		
		WebElement linkTables = getDriver().findElement(By.linkText("Localizar Table"));
		linkTables.click();
		Thread.sleep(1000);
		
		assertEquals("Trabalhando com tables", getDriver().getTitle());
		
		getDriver().navigate().back();
		Thread.sleep(1000);
		assertEquals("Desafio Automação Cálculos", getDriver().getTitle());
		
		getDriver().navigate().back();
		Thread.sleep(1000);
		assertEquals("Treino Automação de Testes", getDriver().getTitle());
		
		getDriver().navigate().forward();
		Thread.sleep(1000);
		assertEquals("Desafio Automação Cálculos", getDriver().getTitle());
		
		getDriver().navigate().forward();
		Thread.sleep(1000);
		assertEquals("Trabalhando com tables", getDriver().getTitle());
		
	}

}
