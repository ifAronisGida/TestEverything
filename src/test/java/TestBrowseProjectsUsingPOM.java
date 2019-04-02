import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestBrowseProjectsUsingPOM {

    private JiraBrowseProjects browseProjects;
    private JiraLogin login;

    @BeforeEach
    void setBrowseProjects() {
        browseProjects = new JiraBrowseProjects();
        login = new JiraLogin();
    }

    @BeforeEach
    void login() {
        login.loginToJira("user12", "CCPass123");
    }

    @AfterEach
    void closeDriver() {
        browseProjects.closeDriver();
    }

    @Test
    void testIfThereIsAnyProjects() throws InterruptedException {
        browseProjects.navigateToViewAllProjects();
        assertTrue(browseProjects.countProjects() > 0);
    }
}
