import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BrowseIssue {

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
    void testIfCanBrowseIssues() {
        utils.waitForElement(driver, "find_link");
        driver.findElement(By.id("find_link")).click();
        utils.waitForElement(driver, "issues_new_search_link_lnk");
        driver.findElement(By.id("issues_new_search_link_lnk")).click();
        assertTrue(driver.getPageSource().contains("simple-issue-list"));
    }

    @Test
    void testIfCanViewDetailsOfAnIssue() {
        utils.waitForElement(driver, "find_link");
        driver.findElement(By.id("find_link")).click();
        utils.waitForElement(driver, "issues_new_search_link_lnk");
        driver.findElement(By.id("issues_new_search_link_lnk")).click();
        utils.waitForLoad(driver);
        String searchTerm = "Test on mandatory field";
        WebElement searchBox = driver.findElement(By.id("searcher-query"));
        searchBox.sendKeys(searchTerm);
        searchBox.sendKeys(Keys.RETURN);
        assertTrue(driver.getPageSource().contains("issuedetails"));
    }

    @Test
    void testIfProjectContainsThreeIssues() {
        utils.waitForElement(driver, "find_link");
        driver.findElement(By.id("find_link")).click();
        utils.waitForElement(driver, "issues_new_search_link_lnk");
        driver.findElement(By.id("issues_new_search_link_lnk")).click();
        utils.waitForLoad(driver);
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div[3]/div/form/div[1]/div[1]/div[1]/div[1]/div/div[1]/ul/li[1]/div[1]")).click();
        driver.findElement(By.id("10003-1")).click();
        List<WebElement> liElements = driver.findElements(By.className("issue-content-container"));
        assertTrue(liElements.size() > 2);
    }

}