package com.practice.sauselab.utility;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageUtilities {

	protected WebDriverWait wait;
	
	

	 public PageUtilities(WebDriverWait wait) {
	        this.wait = wait;
	    }

	
	public WebElement visibilityOf(WebElement element ) {
		
		return wait.until(ExpectedConditions.visibilityOf(element));
		
		
	}
	

	public List<WebElement> visibilityOfElements(List<WebElement> elements) {
		
		return wait.until(ExpectedConditions.visibilityOfAllElements(elements));
		
		
	}
	
	 public void clickWhenClickable(WebElement element) {
	        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	    }
	
	
}
