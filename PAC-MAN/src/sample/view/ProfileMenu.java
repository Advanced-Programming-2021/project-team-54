package sample.view;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import sample.controller.PlayerController;
import sample.model.enums.Scenes;
import javafx.scene.control.Label;
import sample.view.components.Assets;

public class ProfileMenu {
    public Label profileInfo;
    public GridPane profilePicture;

    @FXML
    public void initialize() {
        profileInfo.setText(PlayerController.getProfileInfo(MainMenu.getPlayer()));
        profilePicture.add(new ImageView(Assets.getPlayerProfileImage()), 0, 0);
    }

    public void back(MouseEvent mouseEvent) {
        Main.changeScene(Scenes.MAIN_MENU);
    }

    public void changeNickname(MouseEvent mouseEvent) {
        Main.changeScene(Scenes.CHANGE_NICKNAME);
    }

    public void changePassword(MouseEvent mouseEvent) {
        Main.changeScene(Scenes.CHANGE_PASSWORD);
    }

    public void changePicture(MouseEvent mouseEvent) {
        PlayerController.changeProfilePicture(MainMenu.getPlayer());
        initialize();
    }
}
