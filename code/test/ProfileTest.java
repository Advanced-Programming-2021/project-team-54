import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProfileTest {
    @Test
    void profileTest() {
        Assertions.assertTrue(Profile.haveThisStrChangePassPattern("profile change --password --current current --new new"));
        Assertions.assertFalse(Profile.haveThisStrChangePassPattern("sfd"));
    }
}
