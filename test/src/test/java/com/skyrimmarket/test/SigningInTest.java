package com.skyrimmarket.test;

import com.skyrimmarket.test.page.LoginPage;
import net.jodah.failsafe.internal.util.Assert;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SigningInTest {
    ChromeDriver driver;

    @BeforeEach
    void setUp() {
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 1);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        System.setProperty("webdriver.chrome.driver","C:\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver(options);
        driver.get("https://skyrim-market.herokuapp.com");
    }

    @AfterEach
    void driverDown(){
        driver.close();
    }

    @Test
    void signInClient(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.usernameInput.sendKeys("client");
        loginPage.passwordInput.sendKeys("client");
        loginPage.loginButton.click();

        WebElement firstResult = new WebDriverWait(driver, Duration.ofSeconds(4))
                .until(driver -> driver.findElement(By.tagName("app-client")));

        String tagName = driver.findElement(By.tagName("app-client")).getTagName();
        assertEquals(tagName, "app-client");
    }

    @Test
    void signInEmployee(){
        driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("employee");
        driver.findElement(By.id("password")).sendKeys("employee");
        driver.findElement(By.className("login__form__submit")).click();

        WebElement firstResult = new WebDriverWait(driver, Duration.ofSeconds(4))
                .until(driver -> driver.findElement(By.tagName("app-employee")));

        String tagName = driver.findElement(By.tagName("app-employee")).getTagName();
        assertEquals(tagName, "app-employee");
    }


    @Test
    void signInMaster(){
        driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("master");
        driver.findElement(By.id("password")).sendKeys("master");
        driver.findElement(By.className("login__form__submit")).click();

        WebElement firstResult = new WebDriverWait(driver, Duration.ofSeconds(4))
                .until(driver -> driver.findElement(By.tagName("app-master")));

        String tagName = driver.findElement(By.tagName("app-master")).getTagName();
        assertEquals(tagName, "app-master");
    }

    @Test
    void signInWrongCredentials(){
        driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("master");
        driver.findElement(By.id("password")).sendKeys("employee");
        driver.findElement(By.className("login__form__submit")).click();

        WebElement firstResult = new WebDriverWait(driver, Duration.ofSeconds(4))
                .until(driver -> driver.findElement(By.xpath("//*[@id=\"mat-dialog-0\"]/app-error-popup/div")));
        String errorText = driver.findElement(By.xpath("//*[@id=\"mat-dialog-0\"]/app-error-popup/div")).getText();
        assertEquals(errorText, "Bad credentials");
    }

}
