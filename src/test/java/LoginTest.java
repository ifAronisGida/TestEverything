import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

public class LoginTest {

    private static WebDriver driver;

    @BeforeEach
    void setupDriver() {
        driver = new Driver().getDriver();
    }

    @AfterEach
    void closeDriver() {
        driver.close();
    }



    @Test
    void testSuccessfulLogin() throws InterruptedException {
        driver.get("https://jira.codecool.codecanvas.hu/login.jsp");
        WebElement userName = driver.findElement(By.id("login-form-username"));
        userName.sendKeys("user12");
        WebElement userPassword = driver.findElement(By.id("login-form-password"));
        userPassword.sendKeys("CCPass123");
        WebElement loginButton = driver.findElement(By.id("login-form-submit"));
        loginButton.click();
        Thread.sleep(1000);
        String urlToTest = "https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa";
        String actualUrl = driver.getCurrentUrl();
        assertEquals(urlToTest, actualUrl);
    }

    @Test
    void testLoginWithWrongUserName() throws InterruptedException {
        driver.get("https://jira.codecool.codecanvas.hu/login.jsp");
        WebElement userName = driver.findElement(By.id("login-form-username"));
        userName.sendKeys("234wer");
        WebElement userPassword = driver.findElement(By.id("login-form-password"));
        userPassword.sendKeys("CCPass123");
        WebElement loginButton = driver.findElement(By.id("login-form-submit"));
        loginButton.click();
        Thread.sleep(1000);
        String urlToTest = "https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa";
        String actualUrl = driver.getCurrentUrl();
        assertNotSame(urlToTest, actualUrl);
    }

    @Test
    void testLoginWithWrongPassword() throws InterruptedException {
        driver.get("https://jira.codecool.codecanvas.hu/login.jsp");
        WebElement userName = driver.findElement(By.id("login-form-username"));
        userName.sendKeys("user12");
        WebElement userPassword = driver.findElement(By.id("login-form-password"));
        userPassword.sendKeys("CCPass1234");
        WebElement loginButton = driver.findElement(By.id("login-form-submit"));
        loginButton.click();
        Thread.sleep(1000);
        String urlToTest = "https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa";
        String actualUrl = driver.getCurrentUrl();
        assertNotSame(urlToTest, actualUrl);
    }

    @Test
    void testLoginWithEmptyCredentials() throws InterruptedException {
        driver.get("https://jira.codecool.codecanvas.hu/login.jsp");
        WebElement loginButton = driver.findElement(By.id("login-form-submit"));
        loginButton.click();
        Thread.sleep(1000);
        String urlToTest = "https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa";
        String actualUrl = driver.getCurrentUrl();
        assertNotSame(urlToTest, actualUrl);
    }
}
