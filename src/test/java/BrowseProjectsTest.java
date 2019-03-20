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
    void testIfProjectsCanBeViewed() throws InterruptedException {
        driver.findElement(By.id("browse_link")).click();
        driver.findElement(By.id("project_view_all_link")).click();
        Thread.sleep(1000);
        int rowCount = driver.findElements(By.xpath("//table[@class='aui']/tbody/tr")).size();
        assertTrue(rowCount > 0);
    }

    @Test
    void testIfAProjectsDetailsCanBeViewed() {
        driver.findElement(By.id("browse_link")).click();
        driver.findElement(By.id("project_view_all_link_lnk")).click();
        driver.findElement(By.linkText("TOUCAN projekt")).click();
        String expectedUrl = "https://jira.codecool.codecanvas.hu/projects/TOUCAN/issues/TOUCAN-40?filter=allopenissues";
        String actualUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl, actualUrl);
    }

}
