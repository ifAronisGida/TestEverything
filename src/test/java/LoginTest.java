import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {

    private static WebDriver driver = new Driver().getDriver();

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
        driver.close();
    }
}
