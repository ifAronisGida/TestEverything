import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

class TestCreateIssue {

    private static WebDriver driver = new Driver().getDriver();
    //WebElement createLink = driver.findElement(By.id("create_link"));

    @BeforeAll
    static void login() {
        driver.get("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        driver.findElement(By.id("login-form-username")).sendKeys("user14");
        driver.findElement(By.id("login-form-password")).sendKeys("CCPass123");
        driver.findElement(By.id("login")).click();

    }

    @Test
    void createIssueWithReqFieldsFilled() throws InterruptedException {
        Thread.sleep(10000);
        driver.findElement(By.id("create_link")).click();
        Thread.sleep(6000);
//        driver.findElement(By.id("project-field")).sendKeys("COALA Project (COALA)");
//        driver.findElement(By.id("issuetype-field")).sendKeys("Task");
        driver.findElement(By.id("summary")).sendKeys("This is an automatated test.");
        driver.findElement(By.name("jiraform")).submit();
    }

    @Test
    void createIssueWithReqFieldsNotFilled() throws InterruptedException {
        Thread.sleep(10000);
        driver.findElement(By.id("create_link")).click();
        Thread.sleep(6000);
//        driver.findElement(By.id("project-field")).sendKeys("COALA Project (COALA)");
//        driver.findElement(By.id("issuetype-field")).sendKeys("Task");
        driver.findElement(By.id("create-issue-submit")).click();
        Thread.sleep(1000);
        Assertions.assertNotNull(driver.findElement(By.className("error")).getText());
    }
//
//    @Test
//    void expectedIssueAndProjectTypesExist(){
//    }
//
//    @Test
//    void createJetiSubtask(){
//
//    }
//
//    @Test
//    void createToucanSubtask(){
//    }
//
//    @Test
//    void createCoalaSubtask(){
//    }
}