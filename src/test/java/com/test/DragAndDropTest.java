package com.test;

import static org.junit.Assert.assertEquals;

import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class DragAndDropTest {
	
	private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "/home/atrindade/Dev/drivers/chromedriver");
		driver = new ChromeDriver();
		driver.get("https://jqueryui.com/resources/demos/droppable/default.html");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
	
	@Test
	public void testDragAndDrop() throws InterruptedException {
		WebElement draggable = driver.findElement(By.id("draggable"));
		WebElement droppable = driver.findElement(By.id("droppable"));
		
		Thread.sleep(2000);
		
		assertEquals("Drop here", droppable.getText());
		
		Actions action = new Actions(driver);		
		action.dragAndDrop(draggable, droppable).perform();
		
		//ou
		//new Actions(driver).dragAndDrop(draggable, droppable).perform();
				
		Thread.sleep(2000);
		
		assertEquals("Dropped!", droppable.getText());
		
		action.dragAndDropBy(draggable, 200, 300).perform();
		
		Thread.sleep(3000);
	}

}
