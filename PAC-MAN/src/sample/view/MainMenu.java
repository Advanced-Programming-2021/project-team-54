package sample.view;

import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import sample.controller.Client;
import javafx.scene.control.Label;
import sample.view.components.Scenes;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenu implements Initializable {
    public static String authToken;
    public Label username;
    public GridPane gameTitle;

    public MainMenu() {}
    public MainMenu(String authToken) {
        MainMenu.authToken = authToken;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image image = new Image("assets/Logos/GUI_T_GateLogo_s001.dds.png");
        ImageView imageView = new ImageView(image);
        gameTitle.add(imageView, 0, 0);
    }

    public void scoreboard(MouseEvent mouseEvent) {
        Main.changeScene(Scenes.SCOREBOARD);
    }

    public void logout(MouseEvent mouseEvent) {
        Client.logout();
        authToken = null;
        Main.changeScene(Scenes.MAIN);
    }


    public void openDeckMenu(MouseEvent mouseEvent) {
    }

    public void openShopMenu(MouseEvent mouseEvent) {
        Main.changeScene(Scenes.SHOP);
    }

    public void openProfileMenu(MouseEvent mouseEvent) {
        Main.changeScene(Scenes.PROFILE);
    }

    public void play(MouseEvent mouseEvent) {
    }

    public void importExport(MouseEvent mouseEvent) {
    }
}
