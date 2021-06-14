package sample.view;

import javafx.scene.input.MouseEvent;
import sample.model.enums.Scenes;

public class ProfileMenu {

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
    }
}
