import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestBrowseProjectsUsingPOM {

    private JiraBrowseProjects browseProjects;
    private JiraLogin login;

    @BeforeEach
    void setup() {
        login = new JiraLogin();
        login.loginToJira("user12", "CCPass123");
        browseProjects = new JiraBrowseProjects(login.getDriver());
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
}
