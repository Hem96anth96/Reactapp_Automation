package com.practice.sauselab.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.practice.sauselab.pageclasses.LoginPage;
import com.practice.sauselab.utils.TestUtils;

public class AppTest2 extends TestUtils {
	
	WebDriverWait wait;
	@BeforeClass
	public void setUp() {

		setUpDriver();

		getDriver().get("https://www.saucedemo.com/");
		wait = new WebDriverWait(getDriver() , Duration.ofSeconds(3));

	}

	@Test
	public void loginTests() {

		// Array of usernames to test
		String[] usernames = { "standard_user", "problem_user", "performance_glitch_user",
				"error_user", "visual_user" };

		// Password to be used for all tests
		String password = "secret_sauce";

		LoginPage loginObject = new LoginPage(getDriver() );

		// Iterate through each username and perform login
		for (String username : usernames) {
			loginObject.login(username, password);
			
			System.out.println(username);
			
			wait.until(ExpectedConditions.visibilityOf(getDriver() .
					findElement(By.id("react-burger-menu-btn"))))
			.click();
			
			wait.until(ExpectedConditions.visibilityOf(getDriver() .
					findElement(By.id("logout_sidebar_link"))))
			.click();
			
			

			// Add assertions or checks here to verify login success or failure
			// For example, checking if the user is redirected to the correct page or an
			// error message is displayed
		}

	}

	@AfterClass
	public void tearDown() throws InterruptedException {
		tearDownDriver();
	}

}
