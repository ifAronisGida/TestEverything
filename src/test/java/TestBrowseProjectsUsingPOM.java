import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.*;

public class TestBrowseProjectsUsingPOM {

    private JiraBrowseProjects browseProjects;
    private JiraLogin login;

    @BeforeEach
    void setup() throws MalformedURLException {
        WebDriver driver = new Driver().getDriver();
        login = new JiraLogin(driver);
        login.loginToJira(System.getenv("user"), System.getenv("password"));
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

    @Test
    void testIfProjectExists() {
        browseProjects.navigateToViewAllProjects();
        browseProjects.writeToSearchBox("COALA");
        assertTrue(browseProjects.countProjects() > 0);
    }
}
