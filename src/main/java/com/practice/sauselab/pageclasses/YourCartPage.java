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


public class YourCartPage extends PageUtilities {
	
	WebDriver driver;
    

    @FindBy(xpath = "//a/*[@class='inventory_item_name']")
    List<WebElement> cartItems;

    @FindBy(xpath = "//button[text()='Remove']")
    List<WebElement> removeButtons;

    @FindBy(xpath = "//*[@class='inventory_item_price']")
    List<WebElement> priceElements;

    @FindBy(id = "continue-shopping")
    WebElement continueShoppingButton;

    @FindBy(xpath = "//a[@class='shopping_cart_link']")
    WebElement cartButton;

    @FindBy(xpath = "//a[@class='shopping_cart_link']/span")
    WebElement cartCountElement;

    @FindBy(id = "checkout")
    WebElement checkoutButton;



    public YourCartPage(WebDriver driver) {
    	super(new WebDriverWait(driver, Duration.ofSeconds(3)));
        this.driver = driver;
        PageFactory.initElements(driver, this);
        
    }
	
    public int getCartItemsSize() {
        return visibilityOfElements(cartItems).size();
    }

    public int getRemoveButtonsSize() {
        return visibilityOfElements(removeButtons).size();
    }

    public double getTotalPriceInCart() {
    	visibilityOfElements(priceElements);
        double totalPriceCart = 0.0;
        for (WebElement priceElement : priceElements) {
            String priceText = priceElement.getText().replace("$", "");
            double price = Double.parseDouble(priceText);
            totalPriceCart += price;
        }
        return totalPriceCart;
    }

    public void removeAllItemsFromCart() {
    	visibilityOfElements(removeButtons)
            .forEach(button -> button.click());
    }

  
    public String getCartCount() {
        return visibilityOf(cartCountElement).getText();
    }

    public void checkout() {
    	clickWhenClickable(checkoutButton);
    }
	

}
