package sample.view;

import javafx.scene.input.MouseEvent;
import sample.controller.PlayerController;
import sample.model.enums.ErrorMessages;
import sample.model.enums.Scenes;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import sample.model.Player;

public class Login {
    public TextField passwordTextField;
    public TextField usernameTextField;
    public Label messageBox;

    public void login(MouseEvent mouseEvent) {
        ErrorMessages result = PlayerController.login(usernameTextField.getText(), passwordTextField.getText());
        if (result != ErrorMessages.SUCCESS) {
            messageBox.setText(result.getErrorMessage());
            messageBox.setFont(Font.font(20));
        } else {
            new MainMenu(Player.getPlayerByUsername(usernameTextField.getText()));
            Main.changeScene(Scenes.MAIN_MENU);
        }
    }

    public void back(MouseEvent mouseEvent) {
        Main.changeScene(Scenes.MAIN);
    }
}
