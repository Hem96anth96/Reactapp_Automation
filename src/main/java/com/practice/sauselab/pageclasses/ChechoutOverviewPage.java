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


public class ChechoutOverviewPage extends PageUtilities {
	
	
	WebDriver driver;
    WebDriverWait wait;
    
  @FindBy(xpath=  "//*[@class='summary_subtotal_label']")
  WebElement subTotalOverViewPage;
  
  @FindBy(id = "finish")
  WebElement finishButton;
    
    
    public ChechoutOverviewPage(WebDriver driver) {
    	
    	 super(new WebDriverWait(driver, Duration.ofSeconds(3)));
    	
    	 this.driver = driver;
         PageFactory.initElements(driver, this);
    
    	
    }
    
    
    public void testOverViewPage(double totalPriceCart) {
    	
    	// Convert the numeric part to a double
        double totalPriceOverview= Double.parseDouble(visibilityOf(subTotalOverViewPage).getText().replaceAll("[^\\d.]", ""));
        
        Assert.assertEquals(totalPriceCart,totalPriceOverview);
    	
    	
     // go to Checkout: Complete! page
        
        clickWhenClickable(finishButton);
    }

}
