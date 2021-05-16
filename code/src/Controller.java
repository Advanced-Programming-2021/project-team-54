import java.io.File;
import java.util.Scanner;

public class Controller {
    public static int menuNumber = 1;
    public static Scanner scanner = new Scanner(System.in);
    static {
        System.out.println("Login and SignUp Menu!");
    }
    public static void main(String[] args) {
        checkFolders();
        while (true) {
            String input = scanner.nextLine();
            switch (menuNumber) {
                case 1:
                    LoginMenu.loginMenuRun(input);
                    break;
                case 2:
                    MainMenu.mainMenuController(input);
                    break;
                case 6:
                    Profile.profileControl(MainMenu.player,input);
                    break;
                case 5:
                    Scoreboard.scoreboardControl(input);
                    break;
                case 4:
                    Deck.deckControl(input,MainMenu.player);
            }
        }
    }

    private static void checkFolders() {
        File file = new File(System.getProperty("user.dir") + "\\src\\users");
        file.mkdir();
        file = new File(System.getProperty("user.dir") + "\\src\\decks");
        file.mkdir();
        file = new File(System.getProperty("user.dir") + "\\src\\cards");
        file.mkdir();
    }
}
