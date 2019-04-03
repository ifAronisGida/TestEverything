import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class JiraLogin extends JiraPOM{

    @FindBy(id = "login-form-username")
    private WebElement userNameBox;

    @FindBy(id = "login-form-password")
    private WebElement passwordBox;

    @FindBy(id = "login-form-submit")
    private WebElement loginButton;

    private final String LOGIN_URL = "https://jira.codecool.codecanvas.hu/login.jsp";

    public JiraLogin(WebDriver driver) {
        super(driver);
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

    /**
     * This POM method will be used for testing the login of Jira website
     * @param userName
     * @param password
     *
     */

    public void loginToJira(String userName, String password) {
        goToLoginPage();
        writeUserName(userName);
        writePassword(password);
        clickLogin();
    }
}
