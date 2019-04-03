import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class JiraBrowseProjects extends JiraPOM{

    @FindBy(id = "browse_link")
    private WebElement projectsNavbarButton;

    @FindBy(id = "project_view_all_link_lnk")
    private WebElement viewAllProjectsButton;

    @FindBy(id = "project-filter-text")
    private WebElement searchBox;

    public JiraBrowseProjects(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void navigateToViewAllProjects() {
        waitForElement(projectsNavbarButton);
        projectsNavbarButton.click();
        waitForElement(viewAllProjectsButton);
        viewAllProjectsButton.click();
    }

    public void writeToSearchBox(String textToWrite) {
        searchBox.sendKeys(textToWrite);
    }

    public int countProjects() {
        return driver.findElements(By.xpath("//table[@class='aui']/tbody/tr")).size();
    }

}
