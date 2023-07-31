package com.test;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CalculadoraTest {
	
	private WebDriver driver;
	private WebElement tfValor1;	
	private WebElement tfValor2;								
	private WebElement tfTotal;
	
	private Integer val1;
	private Integer val2;
	
	private Random random;
	

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "/home/atrindade/Dev/drivers/chromedriver");
		driver = new ChromeDriver();
		
		//instanciar
		random = new Random();
		
		val1 = random.nextInt(1, 20);
		val2 = random.nextInt(1, 20);
		
		//Implicity Wait
		driver.get("http://antoniotrindade.com.br/treinoautomacao/desafiosoma.html");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		tfValor1 = driver.findElement(By.id("number1"));
		tfValor2 = driver.findElement(By.id("number2"));								
		tfTotal = driver.findElement(By.id("total"));
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
	
	@Test
	public void testSoma() throws InterruptedException {
		//Faz a operação		
		Integer total = val1 + val2;
				
		WebElement btnSomar = driver.findElement(By.id("somar"));
		
		tfValor1.sendKeys(val1.toString());
		tfValor2.sendKeys(val2.toString());
		
		btnSomar.click();
		
		Thread.sleep(3000);
		
		assertEquals(total.toString(), tfTotal.getAttribute("value"));				
		
	}
	
	@Test
	public void testSubtracao() {
		Integer resultado = val1 - val2;
		
		WebElement btnSutracao = driver.findElement(By.id("subtrair"));		
		
		tfValor1.sendKeys(val1.toString());
		tfValor2.sendKeys(val2.toString());
		
		btnSutracao.click();
		
		assertEquals(resultado.toString(), tfTotal.getAttribute("value"));
	}
	
	@Test
	public void testMultiplicacao() {
		Integer total = val1 * val2;
		
		WebElement btnMultiplicao = driver.findElement(By.id("multiplicar"));		
		
		tfValor1.sendKeys(val1.toString());
		tfValor2.sendKeys(val2.toString());

		btnMultiplicao.click();		
		
		assertEquals(total.toString(), tfTotal.getAttribute("value"));			
	}
}
