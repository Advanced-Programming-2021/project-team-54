package sample;

import sample.controller.PlayerController;
import sample.model.Server;

import java.io.File;

public class Main {

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
}
