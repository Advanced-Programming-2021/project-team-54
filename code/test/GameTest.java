import org.junit.jupiter.api.Test;

public class GameTest {
    @Test
    void gameTest() {
        PlayGame.controller("menu exit");
        PlayGame.controller("menu show-current");
        PlayGame.controller("menu enter Shop");
    }
}
