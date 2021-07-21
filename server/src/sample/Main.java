package sample;

import sample.model.Player;
import sample.model.Server;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static HashMap<Player, String> onlinePlayers = new HashMap<>();

    public static void main(String[] args) {
        Server server = new Server();
        checkFolders();
        server.run();
    }

    private static void checkFolders() {
        File file = new File(System.getProperty("user.dir") + "/src/users");
        file.mkdir();
        file = new File(System.getProperty("user.dir") + "/src/decks");
        file.mkdir();
        file = new File(System.getProperty("user.dir") + "/src/cards");
        file.mkdir();
    }

    public static boolean isPlayerOnline(Player player) {
        for (Map.Entry<Player, String> onlinePlayer : onlinePlayers.entrySet()) {
            if (onlinePlayer.getKey().getUsername().equals(player.getUsername()))
                return true;
        }
        return false;
    }

    public static Player getPlayerByAuthToken(String authToken) {
        for (Map.Entry<Player , String> onlinePlayer : onlinePlayers.entrySet()) {
            if (onlinePlayer.getValue().equals(authToken))
                return onlinePlayer.getKey();
        }
        return null;
    }

    public static void logout(String authToken) {
        onlinePlayers.remove(getPlayerByAuthToken(authToken));
    }
}
