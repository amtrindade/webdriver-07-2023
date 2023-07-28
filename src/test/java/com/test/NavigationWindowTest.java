package com.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class NavigationWindowTest {

	private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "/home/atrindade/Dev/drivers/chromedriver");
		driver = new ChromeDriver();
		driver.get("http://antoniotrindade.com.br/treinoautomacao");
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
	
	@Test
	public void testNavigationTabs() throws InterruptedException {
		assertEquals("Treino Automação de Testes", driver.getTitle());
		
		WebElement linkGeradorCpf = driver.findElement(By.linkText("Gerador de CPF"));
		linkGeradorCpf.click();
		
		WebElement linkDragAndDrop = driver.findElement(By.linkText("Drag and Drop JQuery"));
		linkDragAndDrop.click();
		
		// Monta o array de posições das tabs
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
				
		Thread.sleep(3000);
		assertEquals("jQuery UI Droppable - Default functionality", driver.getTitle());			
		
		driver.switchTo().window(tabs.get(2));
		assertEquals("Gerador de CPF", driver.getTitle());
		
		//fecha a tab visualmente
		driver.close();
		
		driver.switchTo().window(tabs.get(1));
		assertEquals("jQuery UI Droppable - Default functionality", driver.getTitle());
		driver.close();
		
		Thread.sleep(3000);
		
		driver.switchTo().window(tabs.get(0));				
		assertEquals("Treino Automação de Testes", driver.getTitle());
		
		
		
		
	}

}
