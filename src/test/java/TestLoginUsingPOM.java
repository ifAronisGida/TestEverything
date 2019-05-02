import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.*;

public class TestLoginUsingPOM {

    private JiraLogin login;
    private JiraLogout logout;

    @BeforeEach
    void setLogin() throws MalformedURLException {
        login = new JiraLogin(new Driver().getDriver());
        logout = new JiraLogout(login.getDriver());
    }

    @AfterEach
    void closeDriver() {
        logout.logout();
        login.closeDriver();
    }

    @Test
    void loginSuccessfulTest() {
        login.loginToJira(System.getenv("user"), System.getenv("password"));
        assertEquals(login.getBaseUrl(), login.getURL());
    }

    /*

    @ParameterizedTest
    @CsvFileSource(resources = "/invalid_login.csv", numLinesToSkip = 1)
    void loginUnsuccessfulTest(String userName, String password, String info) {
        System.out.println("testing with " + info);
        login.loginToJira(userName, password);
        assertNotSame(login.getBaseUrl(), login.getURL());
    }

     */


}
