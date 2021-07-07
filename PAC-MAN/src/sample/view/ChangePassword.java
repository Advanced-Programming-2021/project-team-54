package sample.view;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import sample.controller.PlayerController;
import sample.model.enums.ErrorMessages;
import sample.model.enums.Scenes;
import sample.view.components.PopUp;

public class ChangePassword {
    public TextField oldPasswordTextField;
    public TextField newPasswordTextField;
    public Label messageBox;

    public void changePassword(MouseEvent mouseEvent) {
        ErrorMessages result = PlayerController.changePassword(MainMenu.getPlayer(),
                oldPasswordTextField.getText(), newPasswordTextField.getText());
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