package sample.view;

import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import sample.model.Player;
import javafx.scene.control.Label;
import sample.model.enums.Scenes;
import sample.view.components.PopUp;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenu implements Initializable {
    public static Player player;
    //private static Player player;
    public Label username;
    public GridPane gameTitle;

    public MainMenu() {}

    public MainMenu(Player player) {
        MainMenu.player = player;
    }

    public static Player getPlayer() {
        return player;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText(player.getUsername());
        Image image = new Image("assets/Logos/GUI_T_GateLogo_s001.dds.png");
        ImageView imageView = new ImageView(image);
        gameTitle.add(imageView, 0, 0);
    }

    public void scoreboard(MouseEvent mouseEvent) {
        Main.changeScene(Scenes.SCOREBOARD);
    }

    public void logout(MouseEvent mouseEvent) {
        player = null;
        Main.changeScene(Scenes.MAIN);
    }

    public void deleteAccount(MouseEvent mouseEvent) {
        if (PopUp.confirmAlert("Are you sure you want to delete your account?\nAll your data will be lost.")) {
            //PlayerController.deletePlayer(player);
            player = null;
            Main.changeScene(Scenes.MAIN);
        }
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
