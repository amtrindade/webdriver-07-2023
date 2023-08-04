package com.test;

import static com.core.DriverFactory.getDriver;
import static org.junit.Assert.assertTrue;

import java.time.Duration;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.core.BaseTest;

public class ExpressoesRegularesCNPJTest extends BaseTest{
		
	private WebDriverWait wait;

	@Before
	public void setUp() throws Exception {				
		getDriver().get("https://www.4devs.com.br/gerador_de_cnpj");
		wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
	}
	
	@Test
	public void testValidaCNPJcomMascara() throws InterruptedException {				
		WebElement btnGerar = getDriver().findElement(By.id("bt_gerar_cnpj"));
		btnGerar.click();
		
		//Espera pelo texto estar invisível no elemento.
		wait.until(ExpectedConditions.invisibilityOfElementWithText(By.id("texto_cnpj"), "Gerando..."));
		
		WebElement divTextoCnpj = getDriver().findElement(By.id("texto_cnpj"));
		String cnpjGerado = divTextoCnpj.getText();
		
		System.out.println(cnpjGerado);
		
		assertTrue(cnpjGerado.matches("^[0-9]{2}\\.[0-9]{3}\\.[0-9]{3}\\/000[1-2]{1}\\-[0-9]{2}$"));
	}
	
	@Test
	public void testValidaCNPJsemMascara() throws InterruptedException {
		WebElement cbNao = getDriver().findElement(By.id("pontuacao_nao"));
		cbNao.click();
		
		WebElement btnGerar = getDriver().findElement(By.id("bt_gerar_cnpj"));
		btnGerar.click();
		
		//Espera pelo texto estar invisível no elemento.
		wait.until(ExpectedConditions.invisibilityOfElementWithText(By.id("texto_cnpj"), "Gerando..."));
		
		WebElement divTextoCnpj = getDriver().findElement(By.id("texto_cnpj"));
		String cnpjGerado = divTextoCnpj.getText();
		
		System.out.println(cnpjGerado);
		
		assertTrue(cnpjGerado.matches("^[0-9]{8}000[1-2]{1}[0-9]{2}$"));
	}

}
