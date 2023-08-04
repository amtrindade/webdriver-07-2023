package com.test;

import static com.core.DriverFactory.getDriver;
import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.core.BaseTest;

public class CalculadoraTest extends BaseTest{
	
	private WebElement tfValor1;	
	private WebElement tfValor2;								
	private WebElement tfTotal;
	
	private Integer val1;
	private Integer val2;
	
	private Random random;
	

	@Before
	public void setUp() throws Exception {
		//instanciar
		random = new Random();
		
		val1 = random.nextInt(1, 20);
		val2 = random.nextInt(1, 20);
		
		//Implicity Wait
		getDriver().get("http://antoniotrindade.com.br/treinoautomacao/desafiosoma.html");		
		
		tfValor1 = getDriver().findElement(By.id("number1"));
		tfValor2 = getDriver().findElement(By.id("number2"));								
		tfTotal = getDriver().findElement(By.id("total"));
	}

	@Test
	public void testSoma() throws InterruptedException {
		//Faz a operação		
		Integer total = val1 + val2;
				
		WebElement btnSomar = getDriver().findElement(By.id("somar"));
		
		tfValor1.sendKeys(val1.toString());
		tfValor2.sendKeys(val2.toString());
		
		btnSomar.click();
		
		// Espera explícita
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(6));
		// Espera pelo texto estar presente no value do elemento textfield
		wait.until(ExpectedConditions
				.textToBePresentInElementValue(tfTotal, total.toString()));
		
		assertEquals(total.toString(), tfTotal.getAttribute("value"));						
	}
	
	@Test
	public void testSubtracao() {
		Integer resultado = val1 - val2;
		
		WebElement btnSutracao = getDriver().findElement(By.id("subtrair"));		
		
		tfValor1.sendKeys(val1.toString());
		tfValor2.sendKeys(val2.toString());
		
		btnSutracao.click();
		
		assertEquals(resultado.toString(), tfTotal.getAttribute("value"));
	}
	
	@Test
	public void testMultiplicacao() {
		Integer total = val1 * val2;
		
		WebElement btnMultiplicao = getDriver().findElement(By.id("multiplicar"));		
		
		tfValor1.sendKeys(val1.toString());
		tfValor2.sendKeys(val2.toString());

		btnMultiplicao.click();		
		
		assertEquals(total.toString(), tfTotal.getAttribute("value"));			
	}
}
