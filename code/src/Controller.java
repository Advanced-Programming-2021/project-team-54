import java.util.Scanner;

public class Controller {
    public static int menuNumber = 1;
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            String input = scanner.nextLine();
            switch (menuNumber) {
                case 1:
                    loginMenu.loginMenuRun(input);
                    break;
                case 2:
                    Mainmenu.mainMenuController(input);
                    break;
                case 6:
                    Profile.profileControl(Mainmenu.player,input);
                    break;
                case 5:
                    Scoreboard.scoreboardControl(input);
                    break;
                case 4:
                    Deck.DeckControll(input,Mainmenu.player);
            }
        }
    }
}
