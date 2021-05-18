

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Scoreboard {
    public static void showPlayerList() {
        Player[] players = getListOFPlayers();
        sortListOfPlayers(players);
        printList(players, 0, 1);
    }

    public static void scoreboardControl(String input) {
        Pattern ShowScoreRegex = Pattern.compile("scoreboard show");
        Matcher matcher = ShowScoreRegex.matcher(input);
        if (matcher.find()) {
            showPlayerList();
            return;
        }


        Pattern exitRegex = Pattern.compile("menu exit");
        matcher = exitRegex.matcher(input);
        if (matcher.find()) {
            exit();
            return;
        }
        Pattern showCurrentMenuRegex = Pattern.compile("menu show-current");
        matcher = showCurrentMenuRegex.matcher(input);
        if (matcher.find()) {
            showCurrentMenu();
            return;
        }
        System.out.println("invalid command");
    }

    public static Player[] getListOFPlayers() {
        File file = new File(System.getProperty("user.dir") + "\\src\\users");
        String[] playersFileName = file.list();
        ArrayList<Player> players = new ArrayList<>();
        for (String s : playersFileName) {
            Player player = Player.getPlayerByUsername(s.substring(0, s.length() - 5));
            players.add(player);
        }
        Player[] listOfPlayer = new Player[players.size()];
        listOfPlayer = players.toArray(listOfPlayer);
        return listOfPlayer;
    }

    public static void exit() {
        Controller.menuNumber = 2;
    }

    public static void showCurrentMenu() {
        System.out.println("Game.Scoreboard");
    }

    public static void sortListOfPlayers(Player[] players) {
        for (int i = 0; i < players.length; i++) {
            for (int j = 0; j < players.length - 1; j++) {
                if (playerCompare(players[j + 1], players[j])) {
                    Player p = players[j + 1];
                    players[j + 1] = players[j];
                    players[j] = p;
                }

            }
        }
    }

    public static boolean playerCompare(Player firstPlayer, Player secondPlayer) {
        if (firstPlayer.getScore() > secondPlayer.getScore())
            return true;
        if (firstPlayer.getScore() < secondPlayer.getScore())
            return false;
        String[] list = {firstPlayer.getNikName(), secondPlayer.getNikName()};
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        if (list[0].contentEquals(firstPlayer.getNikName()))
            return true;
        return false;
    }

    public static void printList(Player[] players, int i, int rank) {
        if (i == players.length)
            return;
        if (i == 0) {
            System.out.println(rank + "- " + players[i].getNikName() + ": " + players[i].getScore());
            printList(players, i + 1, rank);
        } else {
            if (players[i].getScore() != players[i - 1].getScore())
                rank++;
            System.out.println(rank + "- " + players[i].getNikName() + ": " + players[i].getScore());
            printList(players, i + 1, rank);
        }

    }
}