package com.test;

import static com.core.DriverFactory.getDriver;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.core.BaseTest;

public class DragAndDropTest extends BaseTest{

	@Before
	public void setUp() throws Exception {
		getDriver().get("https://jqueryui.com/resources/demos/droppable/default.html");
	}

	@Test
	public void testDragAndDrop() throws InterruptedException {
		WebElement draggable = getDriver().findElement(By.id("draggable"));
		WebElement droppable = getDriver().findElement(By.id("droppable"));
		
		Thread.sleep(2000);
		
		assertEquals("Drop here", droppable.getText());
		
		Actions action = new Actions(getDriver());		
		action.dragAndDrop(draggable, droppable).perform();
		
		//ou
		//new Actions(driver).dragAndDrop(draggable, droppable).perform();
				
		Thread.sleep(2000);
		
		assertEquals("Dropped!", droppable.getText());
		
		action.dragAndDropBy(draggable, 200, 300).perform();
		
		Thread.sleep(3000);
	}

}
