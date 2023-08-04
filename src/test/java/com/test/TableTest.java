package com.test;

import static com.core.DriverFactory.getDriver;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.core.BaseTest;

public class TableTest extends BaseTest{

	@Before
	public void setUp() throws Exception {
		getDriver().get("http://antoniotrindade.com.br/treinoautomacao/localizandovalorestable.html");		
	}
	
	@Test
	public void testCopiaNome() throws InterruptedException {
		String nome = "Fulano";
		
		WebElement td = getDriver().findElement(By.xpath("//td[contains(text(),'"+nome+"')]"));
		String nomeCapturado = td.getText();
		
		WebElement tfReserva = getDriver().findElement(By.cssSelector("#txt01"));
		tfReserva.sendKeys(nomeCapturado);
		
		Thread.sleep(3000);		
		assertEquals("Fulano da Silva", tfReserva.getAttribute("value"));
		
	}
	
	@Test
	public void testCheckBoxParentChild() throws InterruptedException {
		WebElement cb = getDriver().findElement(By.xpath("//*[.='Fulano da Silva']/../td/input"));
		
		cb.click();
		
		assertTrue(cb.isSelected());
		Thread.sleep(3000);
	}
	
	@Test
	public void testCheckBoxSibling() throws InterruptedException {		
		WebElement cb = getDriver().findElement(By.xpath("//*[contains(text(),'Beck')]/following-sibling::td[2]/input"));
		
		cb.click();
		
		assertTrue(cb.isSelected());
		Thread.sleep(3000);
	}

}
