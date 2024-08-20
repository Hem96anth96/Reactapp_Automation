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


public class ProductsPage extends PageUtilities{

	
	

	WebDriver driver;


    @FindBy(xpath = "//*[@class='inventory_item_name ']")
    List<WebElement> itemNames;
    
    @FindBy(xpath = "//select/option")
    List<WebElement> options;
    
    @FindBy(xpath = "//a[@class='shopping_cart_link']")
    WebElement cartButton;
    
    @FindBy(xpath = "//button[text()='Add to cart']")
    List<WebElement> addToCart;
    
    
   
    public ProductsPage(WebDriver driver) {
    	super(new WebDriverWait(driver, Duration.ofSeconds(3)));
        this.driver = driver;
        PageFactory.initElements(driver, this);
        
    }
    
    
    public void verifyItemName(int index, String expectedName) {
    	
        
        visibilityOfElements(itemNames);
        Assert.assertEquals(itemNames.get(index).getText(), expectedName);
    }

    public void selectOptionByText(String optionText) {
        options.stream()
            .filter(option -> option.getText().equals(optionText))
            .findFirst()
            .ifPresent(option -> option.click());
    }

    public void addAllItemsToCart() {
    	
    	visibilityOfElements(addToCart)
            .forEach(button -> button.click());
    }

    public void navigateToCart() {
    	clickWhenClickable(cartButton);
    }

    public int testProductPage() {
        // Verify initial item name
        verifyItemName(0, "Chris Cool");

        // Select "Name (Z to A)" and verify item name
        selectOptionByText("Name (Z to A)");
        verifyItemName(0, "Stewie and Rupert");

        // Select "Price (high to low)" and verify item name
        selectOptionByText("Price (high to low)");
        verifyItemName(0, "Stewie and Rupert");
        
        // Select "Price (low to high)" and verify item name
        selectOptionByText("Price (low to high)");
        verifyItemName(0, "Louis is Dead");

        // Add all items to the cart
        addAllItemsToCart();
        
       int itemSize = visibilityOfElements(itemNames).size();

        // Navigate to the cart page
        navigateToCart();
        
        return itemSize;
    }
    
}
