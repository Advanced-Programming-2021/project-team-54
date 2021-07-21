package sample.view;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import sample.controller.Client;
import sample.view.components.ErrorMessages;
import sample.view.components.PopUp;
import sample.view.components.Scenes;

public class ChangePassword {
    public TextField oldPasswordTextField;
    public TextField newPasswordTextField;
    public Label messageBox;

    public void changePassword(MouseEvent mouseEvent) {
        ErrorMessages result = Client.changePassword(oldPasswordTextField.getText(), newPasswordTextField.getText());
        if (result != ErrorMessages.SUCCESS){
            messageBox.setText(result.getErrorMessage());
            messageBox.setFont(Font.font(20));
        } else {
            Main.changeScene(Scenes.PROFILE);
            PopUp.showSuccess("Password changed successfully!");
        }
    }

    public void back(MouseEvent mouseEvent) {
        Main.changeScene(Scenes.PROFILE);
    }
}