import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

public class TestLoginUsingPOM {

    JiraLogin login;

    @BeforeEach
    void setLogin() {
        login = new JiraLogin();
    }

    @AfterEach
    void closeDriver() {
        login.closeDriver();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/valid_login.csv", numLinesToSkip = 1)
    void loginSuccessful(String userName, String password) {
        login.loginToJira(userName, password);
        assertEquals(login.getBASE_URL(), login.getURL());
    }
}
