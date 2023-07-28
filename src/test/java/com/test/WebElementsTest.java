package com.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class WebElementsTest {

	private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "/home/atrindade/Dev/drivers/chromedriver");
		// System.setProperty("webdriver.chrome.driver",
		// "C:\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://antoniotrindade.com.br/treinoautomacao/elementsweb.html");
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void testValidaTextField() throws InterruptedException {

		// Identifica o elemento na página
		WebElement textFieldBox1 = driver.findElement(By.name("txtbox1"));		

		// Faz a iteração com o elemento
		textFieldBox1.sendKeys("Antônio Trindade");

		Thread.sleep(3000);

		// Faz a validação
		assertEquals("Antônio Trindade", textFieldBox1.getAttribute("value"));
	}

	@Test
	public void testValidaStatusTextFields() {
		// Identificar os elementos
		WebElement textFieldBoxEnable = driver.findElement(By.name("txtbox1"));
		WebElement textFieldBoxDisable = driver.findElement(By.name("txtbox2"));

		// Faz a validação
		assertTrue(textFieldBoxEnable.isEnabled());
		assertFalse(textFieldBoxDisable.isEnabled());
	}
	
	@Test
	public void testValidaRadioButton() throws InterruptedException {
		// Identicar os elementos
		List<WebElement> radioButtons = driver.findElements(By.name("radioGroup1"));
		
		//radioButtons.get(2).click();
		
		for (WebElement rb : radioButtons) {
			//System.out.println(rb.getAttribute("value"));
			if (rb.getAttribute("value").equals("Radio 3")) {
				rb.click();
				break;
			}
		}
				
		// valida tamanho da lista
		assertEquals(4, radioButtons.size());
		
		assertTrue(radioButtons.get(2).isSelected());
		
		assertFalse(radioButtons.get(0).isSelected());
		assertFalse(radioButtons.get(1).isSelected());
		assertFalse(radioButtons.get(3).isSelected());		
	}
	
	@Test
	public void testValidaCheckBox() throws InterruptedException {
		
		// Identicar os elementos
		List<WebElement> checkBoxes = driver.findElements(By.name("chkbox"));
		
		for (WebElement cb : checkBoxes) {
			if ((cb.getAttribute("value").equals("Check 3")) 
					|| cb.getAttribute("value").equals("Check 4") ){
				cb.click();
			}			
		}
		Thread.sleep(3000);
		
		//Validar todas as posições
		assertTrue(checkBoxes.get(2).isSelected());
		assertTrue(checkBoxes.get(3).isSelected());
		
		assertFalse(checkBoxes.get(0).isSelected());
		assertFalse(checkBoxes.get(1).isSelected());
		
	}
	
	@Test
	public void testValidaSelectSingle() throws InterruptedException {
		WebElement elementSelectSingle = driver.findElement(By.name("dropdownlist"));
		
		Select selectSingle = new Select(elementSelectSingle);
		
		assertEquals(10, selectSingle.getOptions().size());
		
		// Para fins de aprendizagem
		for (int i = 0; i < selectSingle.getOptions().size(); i++) {
			selectSingle.selectByIndex(i);			
			Thread.sleep(1000);
		}
		
		selectSingle.selectByValue("item5");
		selectSingle.selectByValue("item4");
		selectSingle.selectByVisibleText("Item 10");
				
		selectSingle.selectByVisibleText("Item 7");
		
		Thread.sleep(1000);
		
		assertEquals("Item 7", selectSingle.getFirstSelectedOption().getText());	
	}
	
	@Test
	public void testValidaSelectMulti() throws InterruptedException {
		WebElement elementSelectMulti = driver.findElement(By.name("multiselectdropdown"));
		
		Select selectMulti = new Select(elementSelectMulti);
		
		assertEquals(10, selectMulti.getOptions().size());
		
		selectMulti.selectByVisibleText("Item 8");
		selectMulti.selectByVisibleText("Item 9");
		selectMulti.selectByVisibleText("Item 5");
		
		List<WebElement> allSelect = selectMulti.getAllSelectedOptions();
				
		assertEquals("Item 5", allSelect.get(0).getText());
		assertEquals("Item 8", allSelect.get(1).getText());
		assertEquals("Item 9", allSelect.get(2).getText());		
		
		Thread.sleep(3000);
		
		selectMulti.deselectByVisibleText("Item 8");
		selectMulti.selectByVisibleText("Item 2");
		
		Thread.sleep(3000);
		
		allSelect = selectMulti.getAllSelectedOptions();
		
		assertEquals(3, allSelect.size());
		
		assertEquals("Item 2", allSelect.get(0).getText());		
		assertEquals("Item 5", allSelect.get(1).getText());
		assertEquals("Item 9", allSelect.get(2).getText());			
	}
	
	@Test
	public void testValidaIframe() throws InterruptedException {
		
		// Entrar no iframe				
		driver.switchTo().frame(0);
		
		WebElement tfiFrame = driver.findElement(By.id("tfiframe"));
		tfiFrame.sendKeys("Hello world!");
		
		Thread.sleep(3000);
		
		assertEquals("Hello world!", tfiFrame.getAttribute("value"));
		
		// Volta para página de origem
		driver.switchTo().defaultContent();
		
		WebElement btnAlert = driver.findElement(By.name("alertbtn"));
		assertTrue(btnAlert.isEnabled());
		
		// Entrar no iframe				
		driver.switchTo().frame(0);
		
		WebElement btnIframe = driver.findElement(By.id("btniframe"));
		assertTrue(btnIframe.isEnabled());
		
	}
	
	@Test
	public void testValidaAlerts() throws InterruptedException {
		WebElement btnAlert = driver.findElement(By.name("alertbtn"));
		btnAlert.click();
		
		Alert alert = driver.switchTo().alert();
		assertEquals("Eu sou um alerta!", alert.getText());
		//Clique no Ok, ou teclar o ENTER
		alert.accept();
		
		WebElement btnConfirm = driver.findElement(By.name("confirmbtn"));
		btnConfirm.click();
		
		Alert confirm = driver.switchTo().alert();
		assertEquals("Pressione um botão!", confirm.getText());		
		confirm.dismiss();
		
		WebElement btnPrompt = driver.findElement(By.id("promptBtn"));
		btnPrompt.click();
		
		Alert prompt = driver.switchTo().alert();
		assertEquals("Digite o ano:", prompt.getText());		
		prompt.sendKeys("2023");		
		prompt.accept();
		
		Thread.sleep(3000);
		
		prompt = driver.switchTo().alert();
		assertEquals("O ano é 2023?", prompt.getText());		
	}
	
}
