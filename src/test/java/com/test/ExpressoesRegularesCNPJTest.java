package com.test;

import static org.junit.Assert.assertTrue;

import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ExpressoesRegularesCNPJTest {
	
	private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "/home/atrindade/Dev/drivers/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("https://www.4devs.com.br/gerador_de_cnpj");
		
				
		
		Thread.sleep(3000);
//		WebElement body = driver.findElement(By.xpath("//body"));
//		body.sendKeys(Keys.chord(Keys.CONTROL, "l"));
//		body.sendKeys(Keys.ENTER);	
		
		Actions actionObj = new Actions(driver);
		actionObj.keyDown(Keys.CONTROL)
		         .sendKeys(Keys.chord("l"))		         
		         .perform();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
	
	@Test
	public void testValidaCNPJcomMascara() throws InterruptedException {				
		WebElement btnGerar = driver.findElement(By.id("bt_gerar_cnpj"));
		btnGerar.click();
		
		Thread.sleep(2000);
		
		WebElement divTextoCnpj = driver.findElement(By.id("texto_cnpj"));
		String cnpjGerado = divTextoCnpj.getText();
		
		System.out.println(cnpjGerado);
		
		assertTrue(cnpjGerado.matches("^[0-9]{2}\\.[0-9]{3}\\.[0-9]{3}\\/000[1-2]{1}\\-[0-9]{2}$"));
	}
	
	@Test
	public void testValidaCNPJsemMascara() throws InterruptedException {
		WebElement cbNao = driver.findElement(By.id("pontuacao_nao"));
		cbNao.click();
		
		WebElement btnGerar = driver.findElement(By.id("bt_gerar_cnpj"));
		btnGerar.click();
		
		Thread.sleep(2000);
		
		WebElement divTextoCnpj = driver.findElement(By.id("texto_cnpj"));
		String cnpjGerado = divTextoCnpj.getText();
		
		System.out.println(cnpjGerado);
		
		assertTrue(cnpjGerado.matches("^[0-9]{8}000[1-2]{1}[0-9]{2}$"));
	}

}
