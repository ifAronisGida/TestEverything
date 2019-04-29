import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Driver {

    WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public Driver() {
        System.setProperty("webdriver.chrome.driver", System.getenv("driverPath"));
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--no-sandbox");
        driver = new ChromeDriver();
    }

}
