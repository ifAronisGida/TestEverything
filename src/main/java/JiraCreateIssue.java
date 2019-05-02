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
    WebElement createButton;

    @FindBy(id = "create-issue-submit")
    WebElement submitIssueButton;

    @FindBy(xpath = "//a[@class='cancel']")
    WebElement cancelButton;

    @FindBy(id = "qf-create-another")
    WebElement createAnotherCheckbox;

    @FindBy(id = "summary")
    WebElement summaryField;

    @FindBy(id = "project-field")
    WebElement projectField;

    @FindBy(id = "issuetype-field")
    WebElement issueTypeField;

    @FindBy(id = "aui-flag-container")
    WebElement conformationContainer;

    @FindBy(xpath = "//div[@class='aui-message-context']//a")
    WebElement conformationForCreateAnother;

    @FindBy(xpath = "//div[@class='error']")
    WebElement errorMessage;

    @FindBy(id = "find_link")
    WebElement issues;

    @FindBy(id = "find_link-content")
    WebElement searchForIssuesContainer;

    @FindBy(id = "issues_new_search_link_lnk")
    WebElement searchForIssuesLink;

    @FindBy(id = "searcher-query")
    WebElement searchField;

    @FindBy(xpath = "//*[@id='delete-issue']/a")
    WebElement deleteLink;

    @FindBy(id = "delete-issue-submit")
    WebElement deleteButtonOnPopup;

    @FindBy(id = "opsbar-operations_more")
    WebElement moreOperationsButton;

    public JiraCreateIssue(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    void clickCreateButton(){
        createButton.click();
        waitForElement(submitIssueButton);
    }

    void selectFromDropdown(WebElement element, String select) {
        wait.until(ExpectedConditions.elementToBeClickable(element));

        element.sendKeys(select);

        if (!element.getAttribute("aria-activedescendant").equals("null")) {
            element.sendKeys(Keys.ENTER);
        } else {
            throw new IllegalArgumentException(select + " cannot be found!");
            }
        }

    void fillInSummaryField(String summary) {
        waitForElementClickable(summaryField);
        summaryField.sendKeys(summary);
    }

    void fillInSearchField(String issue){
        waitForElementClickable(searchField);
        searchField.sendKeys(issue);
        searchField.sendKeys(Keys.ENTER);
    }

    void createNewIssue(String projectName, String issueName, String summary) {
        selectFromDropdown(projectField, projectName);
        selectFromDropdown(issueTypeField, issueName);
        fillInSummaryField(summary);
        submitIssueButton.click();
    }


    String getProjectTypes(String projectName, List<String> requiredIssueTypes) {
        List<String> actualResults = new ArrayList<>();

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

    boolean validateSuccessfulIssueCreation() {
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

    void navigateToSearchForIssues() {
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
