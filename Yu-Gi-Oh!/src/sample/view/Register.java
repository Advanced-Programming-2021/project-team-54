package sample.view;

import sample.controller.PlayerController;
import sample.model.enums.ErrorMessages;
import sample.model.enums.Scenes;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import sample.view.components.PopUp;

public class Register {
    public TextField passwordTextField;
    public TextField usernameTextField;
    public TextField nicknameTextField;
    public Label messageBox;


    public void register(MouseEvent mouseEvent) {
        ErrorMessages result = PlayerController.register(usernameTextField.getText(), nicknameTextField.getText(), passwordTextField.getText());
        if (result != ErrorMessages.SUCCESS) {
            messageBox.setText(result.getErrorMessage());
            messageBox.setFont(Font.font(20));
        } else {
            Main.changeScene(Scenes.MAIN);
            PopUp.showSuccess("You have successfully created an account!");
        }
    }

    public void back(MouseEvent mouseEvent) {
        Main.changeScene(Scenes.MAIN);
    }
}
