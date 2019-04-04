import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class JiraCreateIssue extends JiraPOM{

    @FindBy(id = "create_link")
    private WebElement createButton;
    @FindBy(id = "aui-flag-container")
    private WebElement confirmContainer;
    @FindBy(xpath = "//*[@id=\"aui-flag-container\"]/div/div/a")
    private WebElement newIssue;
    @FindBy(xpath = "//*[@id=\"issuetype-field\"]")
    private WebElement taskField;
    @FindBy(id = "summary")
    private WebElement summaryField;
    @FindBy(id = "create-issue-submit")
    private WebElement createIssue;
    @FindBy(xpath= "//*[@id=\"project-field\"]")
    WebElement projectField;


    public JiraCreateIssue(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void openCreateModal(){
        createButton.click();
    }

    public void fillProjectField(String project){
        waitForElementClickable(projectField);
        driver.findElement(By.xpath("//*[@id=\"project-field\"]")).sendKeys(project, Keys.ENTER);
    }

    public void fillTaskField(String task){
        waitForElementClickable(taskField);
        taskField.sendKeys(task, Keys.ENTER);

    }

    public void fillSummaryField(String summary){
        waitForElementClickable(summaryField);
        summaryField.sendKeys(summary);
    }

    public void navigateToCreatedIssue(){
        waitForElement(confirmContainer);
        newIssue.click();
    }

    public void createIssue(String project, String task){
        openCreateModal();
        fillProjectField(project);
        fillTaskField(task);
        fillSummaryField("this is an automatated test");
        waitForElementClickable(createIssue);
        createIssue.click();
    }
}