package com.practice.sauselab.pageclasses;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.practice.sauselab.utility.PageUtilities;


public class CheckoutCompletePage extends PageUtilities {
	
	@FindBy(xpath = "//h2")
	WebElement thankyouText;
	
	@FindBy(xpath = "//*[@class='complete-text']")
	WebElement infoText;
	
	@FindBy(id = "back-to-products")
	WebElement backToProductsButton;
	
	WebDriver driver;
    
	
	public CheckoutCompletePage (WebDriver driver) {
		
		 super(new WebDriverWait(driver, Duration.ofSeconds(3)));
		 this.driver = driver;
         PageFactory.initElements(driver, this);
         
	}
	
	public void testCheckoutCompletePage() {
		
		
		Assert.assertEquals(thankyouText.getText(), "Hi Fname, Thank you for your order!");
		    	
    	Assert.assertEquals(infoText.getText(), "Your order has been dispatched, and will arrive ASAP!");
    	
    	// back to products page
    	backToProductsButton.click();
    	
    	Assert.assertEquals(driver.findElement(By.xpath("//*[@class='header_secondary_container']/span")).getText(), "Characters!!");
	}
	

}
