package com.practice.sauselab.utils;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestUtils {
	
	   
	    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	  
	
	
	public void setUpDriver() {
		
		// Initialize the ChromeDriver
        
        ChromeOptions options = new ChromeOptions();
        
        
        options.addArguments("--headless");
        
        options.addArguments("--no-sandbox");
        
        options.addArguments("--disable-gpu");
        
        driver.set(new ChromeDriver(options));
        
     
	}
	
	   public WebDriver getDriver() {
	        return driver.get();
	    }
	
	
	public void tearDownDriver() throws InterruptedException {
			
		if (driver.get()  != null) {
            // Close the browser
        	Thread.sleep(4000);
        	driver.get().quit();
        	 driver.remove();  // Remove the driver instance from ThreadLocal
        }
	}
	
	
	

	
}