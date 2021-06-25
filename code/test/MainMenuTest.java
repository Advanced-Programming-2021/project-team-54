import org.junit.jupiter.api.Test;

public class MainMenuTest {
    @Test
    void mainMenuTest() {
        MainMenu.mainMenuController("menu exit");
        MainMenu.mainMenuController("menu enter Shop");
        MainMenu.mainMenuController("menu show-current");
        MainMenu.mainMenuController("khjgad");
    }
}
