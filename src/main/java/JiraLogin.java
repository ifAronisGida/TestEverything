import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class JiraLogin {

    private WebDriver driver;

    @FindBy(id = "login-form-username")
    private WebElement userNameBox;

    @FindBy(id = "login-form-password")
    private WebElement passwordBox;

    @FindBy(id = "login-form-submit")
    private WebElement loginButton;

    private final String LOGIN_URL = "https://jira.codecool.codecanvas.hu/login.jsp";
    private final String BASE_URL = "https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa";

    public String getBASE_URL() {
        return BASE_URL;
    }

    public JiraLogin() {
        driver = new Driver().getDriver();
        PageFactory.initElements(driver, this);
    }

    public void goToLoginPage() {
        driver.get(LOGIN_URL);
    }

    public void writeUserName(String userName) {
        if (userName != null) userNameBox.sendKeys(userName);
    }

    public void writePassword(String password) {
        if (password != null) passwordBox.sendKeys(password);
    }

    public void clickLogin() {
        loginButton.click();
    }

    public String getURL() {
        return driver.getCurrentUrl();
    }

    public void closeDriver() {
        driver.close();
    }

    /**
     * This POM method will be used for testing the login of Jira website
     * @param userName
     * @param password
     *
     */

    public void loginToJira(String userName, String password) {
        this.goToLoginPage();
        this.writeUserName(userName);
        this.writePassword(password);
        this.clickLogin();
    }

}
