package sample.view;

import javafx.scene.input.MouseEvent;
import sample.controller.Client;
import sample.view.components.ErrorMessages;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import sample.view.components.Scenes;

public class Login {
    public TextField passwordTextField;
    public TextField usernameTextField;
    public Label messageBox;

    public void login(MouseEvent mouseEvent) {
        ErrorMessages result = Client.login(usernameTextField.getText(), passwordTextField.getText());
        if (result != ErrorMessages.SUCCESS) {
            messageBox.setText(result.getErrorMessage());
            messageBox.setFont(Font.font(20));
        } else {
           // new MainMenu(Player.getPlayerByUsername(usernameTextField.getText()));
            Main.changeScene(Scenes.MAIN_MENU);
        }
    }

    public void back(MouseEvent mouseEvent) {
        Main.changeScene(Scenes.MAIN);
    }
}
