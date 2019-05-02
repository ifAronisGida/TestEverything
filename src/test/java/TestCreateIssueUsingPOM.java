import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.*;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;

public class TestCreateIssueUsingPOM {

    private JiraLogin login;
    private JiraCreateIssue createIssue;

    @BeforeEach
    void setup() throws MalformedURLException {
        WebDriver driver = new Driver().getDriver();
        login = new JiraLogin(driver);
        login.loginToJira(System.getenv("user"), System.getenv("password"));
        createIssue = new JiraCreateIssue(driver);
    }

    @AfterEach
    void closeDriver() {
        createIssue.closeDriver();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/create_issue_pass.csv", numLinesToSkip = 1)
    void testSuccessfulIssueCreation(String projectName, String issueType, String summary) {
        createIssue.createNewIssue(projectName, issueType, summary);
        assertTrue(createIssue.validateSuccessfulIssueCreation());
        createIssue.deleteIssue(projectName, summary);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/create_issue_fail.csv", numLinesToSkip = 1)
    void testUnsuccessfulIssueCreationWithEmptySummary(String projectName, String issueType, String summary) {
        if (summary == null){
            summary = "";
        }
        createIssue.createNewIssue(projectName, issueType, summary);
        assertTrue(createIssue.validateEmptySummaryError());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/create_issue_project_with_types.csv", numLinesToSkip = 1)
    void testProjectHasAllRequiredTypes(String projectName) {
        ArrayList<String> expectedTypes = new ArrayList<>(
                Arrays.asList("Story", "Task", "Bug", "Sub-task"));

        String actualResult = createIssue.getProjectTypes(projectName, expectedTypes);
        assertEquals(expectedTypes.toString(), actualResult);
    }
}
