import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.*;

public class TestBrowseProjectsUsingPOM {

    private JiraBrowseProjects browseProjects;
    private JiraLogin login;

    @BeforeEach
    void setup() {
        WebDriver driver = new Driver().getDriver();
        login = new JiraLogin(driver);
        login.loginToJira("user12", "CCPass123");
        browseProjects = new JiraBrowseProjects(driver);
    }

    @AfterEach
    void closeDriver() {
        browseProjects.closeDriver();
    }

    @Test
    void testIfThereIsAnyProjects() {
        browseProjects.navigateToViewAllProjects();
        assertTrue(browseProjects.countProjects() > 0);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/project_names.csv", numLinesToSkip = 1)
    void testIfProjectExists(String projectName) {
        browseProjects.navigateToViewAllProjects();
        browseProjects.writeToSearchBox(projectName);
        assertTrue(browseProjects.countProjects() > 0);
    }
}
