import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static org.junit.jupiter.api.Assertions.*;

public class TestCreateIssueUsingPOM {

    private JiraLogin login;
    private JiraCreateIssue createIssue;


    @BeforeEach
    void login(){
        WebDriver driver = new Driver().getDriver();
        login = new JiraLogin(driver);
        login.loginToJira("user14" , "CCPass123");
        createIssue = new JiraCreateIssue(login.getDriver());
    }

    @AfterEach
    void closeDriver(){
        createIssue.closeDriver();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/create_issue.csv", numLinesToSkip = 1)
    void createIssueWithReqFieldsFilled(String project, String task) throws InterruptedException {
        createIssue.createIssue(project, task);
        createIssue.navigateToCreatedIssue();
    }

}

