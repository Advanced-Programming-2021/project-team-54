package sample.view;


import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import sample.controller.PlayerController;
import sample.model.enums.ErrorMessages;
import sample.model.enums.Scenes;
import sample.view.components.PopUp;

public class ChangeNickname {
    public TextField nicknameTextField;
    public Label messageBox;

    public void back(MouseEvent mouseEvent) {
        Main.changeScene(Scenes.PROFILE);
    }

    public void changeNickname(MouseEvent mouseEvent) {
        ErrorMessages result = PlayerController.changeNickname(MainMenu.getPlayer(), nicknameTextField.getText());
        if (result != ErrorMessages.SUCCESS){
            messageBox.setText(result.getErrorMessage());
            messageBox.setFont(Font.font(20));
        } else {
            Main.changeScene(Scenes.PROFILE);
            PopUp.showSuccess("Nickname successfully changed to " + nicknameTextField.getText() + " !");
        }
    }
}
