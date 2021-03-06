import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginTest {
    @Test
    void loginTest() {
        LoginMenu.loggingIn("user login --username user --password pass");
        Assertions.assertFalse(LoginMenu.haveLoginPattern("ds", LoginMenu.loginPatters()));
        Assertions.assertTrue(LoginMenu.haveLoginPattern("user login --username user --password pass", LoginMenu.loginPatters()));
        Assertions.assertTrue(LoginMenu.haveLoginPattern("user login --password pass --username user", LoginMenu.loginPatters()));
    }

    @Test
    void signupTest() {
        LoginMenu.signingUp("user create --username user --nickname nick --password pass");
        Assertions.assertFalse(LoginMenu.haveSignupPattern("ds", LoginMenu.signupPatterns()));
        Assertions.assertTrue(LoginMenu.haveSignupPattern("user create --username user --nickname nick --password pass", LoginMenu.signupPatterns()));
        Assertions.assertTrue(LoginMenu.haveSignupPattern("user create --password pass --username user --nickname nick", LoginMenu.signupPatterns()));
        Assertions.assertTrue(LoginMenu.haveSignupPattern("user create --username user --password pass --nickname nick", LoginMenu.signupPatterns()));
    }

    @Test
    void loginMenuTest() {
        LoginMenu.menuExit();
    }
}
