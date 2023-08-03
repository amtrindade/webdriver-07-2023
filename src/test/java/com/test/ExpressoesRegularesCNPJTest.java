package com.test;

import static org.junit.Assert.assertTrue;

import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExpressoesRegularesCNPJTest {
	
	private WebDriver driver;
	private WebDriverWait wait;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "/home/atrindade/Dev/drivers/chromedriver");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("https://www.4devs.com.br/gerador_de_cnpj");
					
		Thread.sleep(3000);
		
//		WebElement linkCNPJ = driver.findElement(By.xpath("//*[@id='top-nav']/li[17]/a"));
//		linkCNPJ.click();		
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
	
	@Test
	public void testValidaCNPJcomMascara() throws InterruptedException {				
		WebElement btnGerar = driver.findElement(By.id("bt_gerar_cnpj"));
		btnGerar.click();
		
		//Espera pelo texto estar invisível no elemento.
		wait.until(ExpectedConditions.invisibilityOfElementWithText(By.id("texto_cnpj"), "Gerando..."));
		
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
		
		//Espera pelo texto estar invisível no elemento.
		wait.until(ExpectedConditions.invisibilityOfElementWithText(By.id("texto_cnpj"), "Gerando..."));
		
		WebElement divTextoCnpj = driver.findElement(By.id("texto_cnpj"));
		String cnpjGerado = divTextoCnpj.getText();
		
		System.out.println(cnpjGerado);
		
		assertTrue(cnpjGerado.matches("^[0-9]{8}000[1-2]{1}[0-9]{2}$"));
	}

}
