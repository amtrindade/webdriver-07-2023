package com.test;

import static org.junit.Assert.*;

import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TableTest {
	
	private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "/home/atrindade/Dev/drivers/chromedriver");
		driver = new ChromeDriver();
		driver.get("http://antoniotrindade.com.br/treinoautomacao/localizandovalorestable.html");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
	
	@Test
	public void testCopiaNome() throws InterruptedException {
		String nome = "Fulano";
		
		WebElement td = driver.findElement(By.xpath("//td[contains(text(),'"+nome+"')]"));
		String nomeCapturado = td.getText();
		
		WebElement tfReserva = driver.findElement(By.cssSelector("#txt01"));
		tfReserva.sendKeys(nomeCapturado);
		
		Thread.sleep(3000);		
		assertEquals("Fulano da Silva", tfReserva.getAttribute("value"));
		
	}
	
	@Test
	public void testCheckBoxParentChild() throws InterruptedException {
		WebElement cb = driver.findElement(By.xpath("//*[.='Fulano da Silva']/../td/input"));
		
		cb.click();
		
		assertTrue(cb.isSelected());
		Thread.sleep(3000);
	}
	
	@Test
	public void testCheckBoxSibling() throws InterruptedException {		
		WebElement cb = driver.findElement(By.xpath("//*[contains(text(),'Beck')]/following-sibling::td[2]/input"));
		
		cb.click();
		
		assertTrue(cb.isSelected());
		Thread.sleep(3000);
	}

}
