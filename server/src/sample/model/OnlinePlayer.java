package sample.model;

import java.util.ArrayList;

public class OnlinePlayer {

    private static Player player;
    private static String authToken;
    private static ArrayList<OnlinePlayer> onlinePlayers = new ArrayList<OnlinePlayer>();

    public OnlinePlayer(Player player, String authToken) {
        this.player = player;
        this.authToken = authToken;
        onlinePlayers.add(this);
    }

    public String getAuthToken() {
        return authToken;
    }

    public Player getPlayer() {
        return player;
    }

    public void logout() {
        onlinePlayers.remove(this);
    }

    public ArrayList<OnlinePlayer> getOnlinePlayers() {
        return onlinePlayers;
    }

    public static Player getPlayerByAuthToken(String authToken) {
        for (OnlinePlayer player : onlinePlayers) {
            if (player.getAuthToken().equals(authToken))
                return player.getPlayer();
        }
        return null;
    }

    public static OnlinePlayer getOnlinePlayerByAuthToken(String authToken) {
        for (OnlinePlayer player : onlinePlayers) {
            if (player.getAuthToken().equals(authToken))
                return player;
        }
        return null;
    }
}
