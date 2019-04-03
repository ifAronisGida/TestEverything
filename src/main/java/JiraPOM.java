import org.openqa.selenium.WebDriver;

public abstract class JiraPOM {
    protected WebDriver driver;

    private final String BASE_URL = "https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa";

    public WebDriver getDriver() {
        return driver;
    }

    public JiraPOM(WebDriver driver) {
        this.driver = driver;
    }

    public String getBaseUrl() {
        return BASE_URL;
    }

    public void closeDriver() {
        driver.close();
    }

}
