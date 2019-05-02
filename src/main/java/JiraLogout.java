import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class JiraLogout extends JiraPOM {

    @FindBy(css = "#header-details-user-fullname > span > span > img")
    private WebElement userAvatar;

    @FindBy(id = "log_out")
    private WebElement logoutButton;

    @FindBy(css = "#content > div > div > section > div > ui:soy > ui:param > h1")
    private WebElement logoutHeader;

    public JiraLogout (WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void logout() {
        userAvatar.click();
        logoutButton.click();
    }
}
