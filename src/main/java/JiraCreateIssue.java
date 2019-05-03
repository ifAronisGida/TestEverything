import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.*;

public class JiraCreateIssue extends JiraPOM{

    @FindBy(id = "create_link")
    private WebElement createButton;

    @FindBy(id = "create-issue-submit")
    private WebElement submitIssueButton;

    @FindBy(xpath = "//a[@class='cancel']")
    private WebElement cancelButton;

    @FindBy(id = "qf-create-another")
    private WebElement createAnotherCheckbox;

    @FindBy(id = "summary")
    private WebElement summaryField;

    @FindBy(id = "project-field")
    private WebElement projectField;

    @FindBy(id = "issuetype-field")
    private WebElement issueTypeField;

    @FindBy(id = "aui-flag-container")
    private WebElement conformationContainer;

    @FindBy(xpath = "//div[@class='aui-message-context']//a")
    private WebElement conformationForCreateAnother;

    @FindBy(xpath = "//div[@class='error']")
    private WebElement errorMessage;

    @FindBy(id = "find_link")
    private WebElement issues;

    @FindBy(id = "find_link-content")
    private WebElement searchForIssuesContainer;

    @FindBy(id = "issues_new_search_link_lnk")
    private WebElement searchForIssuesLink;

    @FindBy(id = "searcher-query")
    private WebElement searchField;

    @FindBy(xpath = "//*[@id='delete-issue']/a")
    private WebElement deleteLink;

    @FindBy(id = "delete-issue-submit")
    private WebElement deleteButtonOnPopup;

    @FindBy(id = "opsbar-operations_more")
    private WebElement moreOperationsButton;

    public JiraCreateIssue(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    void clickCreateButton(){
        waitForElement(createButton);
        createButton.click();
        waitForElement(submitIssueButton);
    }

    private void selectFromDropdown(WebElement element, String select) {
        waitForElementClickable(element);
        element.sendKeys(select);

        if (!element.getAttribute("aria-activedescendant").equals("null")) {
            element.sendKeys(Keys.ENTER);
        } else {
            throw new IllegalArgumentException(select + " cannot be found!");
            }
        }

    private void fillInSummaryField(String summary) {
        waitForElementClickable(summaryField);
        summaryField.sendKeys(summary);
    }

    private void fillInSearchField(String issue){
        waitForElementClickable(searchField);
        searchField.sendKeys(issue);
        searchField.sendKeys(Keys.ENTER);
    }

    void createNewIssue(String projectName, String issueName, String summary) {
        clickCreateButton();
        selectFromDropdown(projectField, projectName);
        selectFromDropdown(issueTypeField, issueName);
        fillInSummaryField(summary);
        submitIssueButton.click();
    }


    String getProjectTypes(String projectName, List<String> requiredIssueTypes) {
        List<String> actualResults = new ArrayList<>();

        clickCreateButton();
        selectFromDropdown(projectField, projectName);

        for (String issueType: requiredIssueTypes) {
            waitForElementClickable(issueTypeField);
            issueTypeField.sendKeys(issueType);

            if (!issueTypeField.getAttribute("aria-activedescendant").equals("null")) {
                actualResults.add(issueType);
                issueTypeField.sendKeys(Keys.ENTER);
            } else {
                issueTypeField.clear();
            }
        }
        return actualResults.toString();
    }

    boolean
    validateSuccessfulIssueCreation() {
        try {
            waitForElement(conformationContainer);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    boolean validateEmptySummaryError() {
        try {
            waitForElement(errorMessage);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    private void navigateToSearchForIssues() {
        driver.navigate().to("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        issues.click();

        wait.until(ExpectedConditions.visibilityOf(searchForIssuesContainer));
        searchForIssuesLink.click();
    }

    void deleteIssue(String projectName, String issueName) {
        navigateToSearchForIssues();
        fillInSearchField(issueName);
        waitForTextToBe(By.id("project-name-val"), projectName);
        moreOperationsButton.click();

        Actions action = new Actions(driver);

        action.moveToElement(deleteLink);
        action.perform();
        waitForElement(deleteLink);
        deleteLink.click();
        waitForElementClickable(deleteButtonOnPopup);
        deleteButtonOnPopup.click();
    }
}
