import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.*;

public class BrowseProjectsTest {

    private static WebDriver driver;

    @BeforeEach
    void setupDriver() {
        driver = new Driver().getDriver();
    }

    @BeforeEach
    void login() {
        driver.get("https://jira.codecool.codecanvas.hu/login.jsp");
        driver.findElement(By.id("login-form-username")).sendKeys("user12");
        driver.findElement(By.id("login-form-password")).sendKeys("CCPass123");
        driver.findElement(By.id("login-form-submit")).click();
    }

    @AfterEach
    void closeDriver() {
        driver.close();
    }

    @Test
    void testIfProjectsCanBeViewed() {
        driver.findElement(By.id("browse_link")).click();
        driver.findElement(By.id("project_view_all_link_lnk")).click();
        int rowCount = driver.findElements(By.xpath("//table[@class='aui']/tbody/tr")).size();
        assertTrue(rowCount > 0);
    }

}
