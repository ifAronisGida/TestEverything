import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestVersionsWithGlass {
    private static WebDriver driver = new Driver().getDriver();

    @BeforeAll
    static void login() {
        driver.get("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        driver.findElement(By.id("login-form-username")).sendKeys("user14");
        driver.findElement(By.id("login-form-password")).sendKeys("CCPass123");
        driver.findElement(By.id("login")).click();
    }


}
