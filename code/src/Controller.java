import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;
import java.util.Scanner;

public class Controller {
    public static int menuNumber = 1;
    public static Scanner scanner = new Scanner(System.in);

    static {
        System.out.println("Login and SignUp Menu!");
    }

    public static void main(String[] args) throws Exception {
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
                    Profile.profileControl(MainMenu.player, input);
                    break;
                case 5:
                    Scoreboard.scoreboardControl(input);
                    break;
                case 4:
                    Deck.deckControl(input, MainMenu.player);
            }
        }
    }

    private static void checkFolders() throws Exception {
        File file = new File(System.getProperty("user.dir") + "\\src\\users");
        file.mkdir();
        file = new File(System.getProperty("user.dir") + "\\src\\decks");
        file.mkdir();
        file = new File(System.getProperty("user.dir") + "\\src\\cards");
        file.mkdir();
        //I put 68 here because we have 68 cards
        //change this num if you want to add some new cards or remove them
        if(file.list().length != 68){
            String[] filesNames = file.list();
            for(String s : filesNames){
                File currentFile = new File(file.getPath(),s);
                currentFile.delete();
            }
            URL url = new URL("http://mobinghorbani.ir/Yu-Gi-Oh/cards.zip");
            downloadFile(url, "src\\cards\\cards.zip");
            UnzipClass.unzip("src\\cards\\cards.zip","src\\cards");
            file = new File("src\\cards\\cards.zip");
            file.delete();
        }
    }

    public static void downloadFile(URL url, String fileName) throws Exception {
        try (InputStream in = url.openStream()) {
            Files.copy(in, Paths.get(fileName));
        }
    }

}
