import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class JiraPOM {
    WebDriver driver;
    WebDriverWait wait;

    private final String BASE_URL = "https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa";

    public WebDriver getDriver() {
        return driver;
    }

    public JiraPOM(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 20);
    }

    public String getBaseUrl() {
        return BASE_URL;
    }

    public void closeDriver() {
        driver.close();
    }

    void waitForElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    void waitForElementClickable(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    void waitForTextToBe(By element, String expected){
        wait.until(ExpectedConditions.textToBe(element, expected));
    }
}
