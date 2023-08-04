package com.test;

import static com.core.DriverFactory.getDriver;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.core.BaseTest;

public class WebElementsTest extends BaseTest{

	//Shotcuts:
	// Organiza os imports: Ctrl + shift + o
	// Faz o import static: Ctrl + shift + m
	
	@Before
	public void setUp() throws Exception {
		//Ctrl + Shift + M		
		getDriver().get("http://antoniotrindade.com.br/treinoautomacao/elementsweb.html");
	}	

	@Test
	public void testValidaTextField() throws InterruptedException {

		// Identifica o elemento na página
		WebElement textFieldBox1 = getDriver().findElement(By.name("txtbox1"));		

		// Faz a iteração com o elemento
		textFieldBox1.sendKeys("Antônio Trindade");

		Thread.sleep(3000);

		// Faz a validação
		assertEquals("Antônio Trindade", textFieldBox1.getAttribute("value"));
	}

	@Test
	public void testValidaStatusTextFields() {
		// Identificar os elementos
		WebElement textFieldBoxEnable = getDriver().findElement(By.name("txtbox1"));
		WebElement textFieldBoxDisable = getDriver().findElement(By.name("txtbox2"));

		// Faz a validação
		assertTrue(textFieldBoxEnable.isEnabled());
		assertFalse(textFieldBoxDisable.isEnabled());
	}
	
	@Test
	public void testValidaRadioButton() throws InterruptedException {
		// Identicar os elementos
		List<WebElement> radioButtons = getDriver().findElements(By.name("radioGroup1"));
		
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
		List<WebElement> checkBoxes = getDriver().findElements(By.name("chkbox"));
		
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
		WebElement elementSelectSingle = getDriver().findElement(By.name("dropdownlist"));
		
		Select selectSingle = new Select(elementSelectSingle);
		
		assertEquals(10, selectSingle.getOptions().size());
		
		// Para fins de aprendizagem
		for (int i = 0; i < selectSingle.getOptions().size(); i++) {
			selectSingle.selectByIndex(i);						
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
		WebElement elementSelectMulti = getDriver().findElement(By.name("multiselectdropdown"));
		
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
		getDriver().switchTo().frame(0);
		
		WebElement tfiFrame = getDriver().findElement(By.id("tfiframe"));
		tfiFrame.sendKeys("Hello world!");
		
		Thread.sleep(3000);
		
		assertEquals("Hello world!", tfiFrame.getAttribute("value"));
		
		// Volta para página de origem
		getDriver().switchTo().defaultContent();
		
		WebElement btnAlert = getDriver().findElement(By.name("alertbtn"));
		assertTrue(btnAlert.isEnabled());
		
		// Entrar no iframe				
		getDriver().switchTo().frame(0);
		
		WebElement btnIframe = getDriver().findElement(By.id("btniframe"));
		assertTrue(btnIframe.isEnabled());
		
	}
	
	@Test
	public void testValidaAlerts() throws InterruptedException {
		WebElement btnAlert = getDriver().findElement(By.name("alertbtn"));
		btnAlert.click();
		
		Alert alert = getDriver().switchTo().alert();
		assertEquals("Eu sou um alerta!", alert.getText());
		//Clique no Ok, ou teclar o ENTER
		alert.accept();
		
		WebElement btnConfirm = getDriver().findElement(By.name("confirmbtn"));
		btnConfirm.click();
		
		Alert confirm = getDriver().switchTo().alert();
		assertEquals("Pressione um botão!", confirm.getText());		
		confirm.dismiss();
		
		WebElement btnPrompt = getDriver().findElement(By.id("promptBtn"));
		btnPrompt.click();
		
		Alert prompt = getDriver().switchTo().alert();
		assertEquals("Digite o ano:", prompt.getText());		
		prompt.sendKeys("2023");		
		prompt.accept();
		
		Thread.sleep(3000);
		
		prompt = getDriver().switchTo().alert();
		assertEquals("O ano é 2023?", prompt.getText());		
	}
	
}
