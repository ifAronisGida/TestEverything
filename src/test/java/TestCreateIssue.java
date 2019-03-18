import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

class TestCreateIssue {

    private static WebDriver driver = new Driver().getDriver();

    @BeforeAll
    static void login() {
        driver.get("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        driver.findElement(By.id("login-form-username")).sendKeys("user14");
        driver.findElement(By.id("login-form-password")).sendKeys("CCPass123");
        driver.findElement(By.id("login")).click();
    }

    @Test
    void createIssueWithReqFieldsFilled(){

    }

    @Test
    void createIssueWithReqFieldsNotFilled(){

    }

    @Test
    void expectedIssueAndProjectTypesExist(){

    }

    @Test
    void createJetiSubtask(){

    }

    @Test
    void createToucanSubtask(){

    }

    @Test
    void createCoalaSubtask(){

    }
}