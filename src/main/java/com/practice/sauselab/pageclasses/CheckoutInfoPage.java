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


public class CheckoutInfoPage extends PageUtilities {
	
	WebDriver driver;
 
   
    
    @FindBy(xpath = "//a[@class='shopping_cart_link']/span")
    WebElement infoPageCount;
    
    @FindBy(id = "first-name")
    WebElement fName;
    
    @FindBy(id = "last-name")
    WebElement lName;
    
    @FindBy(id = "postal-code")
    WebElement pCode;
    
    @FindBy(xpath = "continue")
    WebElement continueButton;
    
    
	
	
    public CheckoutInfoPage(WebDriver driver) {
    	super(new WebDriverWait(driver, Duration.ofSeconds(3)));
        this.driver = driver;
        PageFactory.initElements(driver, this);
       
    }
    
    public void testCheckoutPage(String cartCount) {
    	
    	
    	Assert.assertEquals(cartCount,infoPageCount.getText());
    	
    	fName.sendKeys("fname");
    	lName.sendKeys("lname");
    	pCode.sendKeys("M9M 0G6");
    	
    	
    	//go to Checkout: Overview page
    	
    	driver.findElement(By.id("continue")).click();
    	
    	
    }
	

}
