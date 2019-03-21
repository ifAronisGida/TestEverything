import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

class TestLogout {

    private static WebDriver driver = new Driver().getDriver();

    @BeforeAll
    static void login(){
        driver.get("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        driver.findElement(By.id("login-form-username")).sendKeys("user14");
        driver.findElement(By.id("login-form-password")).sendKeys("CCPass123");
        driver.findElement(By.id("login")).click();

    }

    @Test
    void logoutNavigatesToRelogURL() throws InterruptedException {
        utils.waitForElement(driver,"header-details-user-fullname");
        String expectedLogoutUrl ="https://jira.codecool.codecanvas.hu/secure/Logout!default.jspa?atl_token=BSF4-29UN-JXKU-E3U4_994844cd7a224e754b63728be9453777c02a6392_lin";
        driver.findElement(By.id("header-details-user-fullname")).click();
        driver.findElement(By.id("log_out")).click();
        Assertions.assertEquals(expectedLogoutUrl, "https://jira.codecool.codecanvas.hu/secure/Logout!default.jspa?atl_token=BSF4-29UN-JXKU-E3U4_994844cd7a224e754b63728be9453777c02a6392_lin");
        driver.close();
    }


}
