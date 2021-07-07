package sample.view;

import javafx.scene.input.MouseEvent;
import sample.controller.PlayerController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sample.model.enums.Scenes;

import java.lang.*;
import java.util.ArrayList;

public class Scoreboard {
    public Label scoreBoardField;
    public Label you;
    public Label scoreBoardField2;

    @FXML
    public void initialize() {
        ArrayList<String> scoreboard = PlayerController.getScoreBoard();
        scoreBoardField.setText(scoreboard.get(0));
        you.setText(scoreboard.get(1));
        if (scoreboard.size() == 3)
            scoreBoardField2.setText(scoreboard.get(2));
    }

    public void back(MouseEvent mouseEvent) {
        Main.changeScene(Scenes.MAIN_MENU);
    }
}
