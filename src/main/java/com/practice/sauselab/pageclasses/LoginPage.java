package com.practice.sauselab.pageclasses;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.practice.sauselab.utility.PageUtilities;

public class LoginPage extends PageUtilities {
	

	WebDriver driver;
    
    @FindBy(id = "user-name")
    WebElement userNameField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(id = "login-button")
    WebElement loginButton;

    @FindBy(xpath = "//h3")
    WebElement errorTextElement;

    // Constructor
    public LoginPage(WebDriver driver) {
    	super(new WebDriverWait(driver, Duration.ofSeconds(3))); // Pass the wait instance to the superclass
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    // Method to login with given username and password
    public void login(String username, String password) {
    	visibilityOf(userNameField).sendKeys(username);
        visibilityOf(passwordField).sendKeys(password);
        clickWhenClickable(loginButton);
    }

    // Method to get error text
    public String getErrorText() {
        return visibilityOf(errorTextElement).getText();
    }
    
    public void checkTitle() {
    	
    	 // Assert the title is as expected
        String expectedTitle = "Family cart";
        String actualTitle = driver.getTitle();
         
        Assert.assertEquals(actualTitle, expectedTitle);  
    }
}
	
	
	

