package com.core;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
	
	private static WebDriver driver;
	
	public static WebDriver getDriver() {
		
		if (driver == null) {
			
			String browser = GlobalProperty.getProperty("webdriver.browser");
			String path = GlobalProperty.getProperty("webdriver.path");
			
			if (browser.equals("chrome")) {				
				System.setProperty("webdriver.chrome.driver", path + "chromedriver");
				driver = new ChromeDriver();
			}
			
			else if (browser.equals("firefox")) {
				System.setProperty("webdriver.geckodriver.driver", path + "geckodriver");
				driver = new FirefoxDriver();				
			}
			
			else {
				System.out.println("Driver não está configurado corretamente");				
			}
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));			
		}		
		return driver;
	}
	
	public static void killDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}		
	}
}
