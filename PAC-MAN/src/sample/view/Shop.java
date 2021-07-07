package sample.view;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import sample.model.Card;
import sample.model.enums.Scenes;

import java.util.ArrayList;

public class Shop {
    public GridPane AlexandriteDragon;
    public GridPane AxeRaider;
    public GridPane BabyDragon;
    public GridPane BattleOx;
    public GridPane BattleWarrior;
    public GridPane BeastKingBarbaros;
    public Tab playerTab;
    public ScrollPane playerScroll;
    public Tab monsterTab, spellTab;
    public BorderPane mainPane;
    public Pane imageBar;
    public ScrollPane monsterScroll, spellScroll;
    @FXML
    public void initialize() {
        AlexandriteDragon.add(new ImageView(new Image("assets/Cards/Monsters/AlexandriteDragon.jpg")),0,0);
        AxeRaider.add(new ImageView(new Image("assets/Cards/Monsters/AxeRaider.jpg")),0,0);
        BabyDragon.add(new ImageView(new Image("assets/Cards/Monsters/BabyDragon.jpg")),0,0);
        BattleOx.add(new ImageView(new Image("assets/Cards/Monsters/BattleOx.jpg")),0,0);
        BattleWarrior.add(new ImageView(new Image("assets/Cards/Monsters/BattleWarrior.jpg")),0,0);
        BeastKingBarbaros.add(new ImageView(new Image("assets/Cards/Monsters/BeastKingBarbaros.jpg")),0,0);
        //item6.add(new ImageView(new Image("assets/Cards/Monsters/BabyDragon.jpg")),0,0);
    }
    public void back(MouseEvent mouseEvent) {
        Main.changeScene(Scenes.MAIN_MENU);
    }

    public void Buy1(MouseEvent mouseEvent) {
    }

    public void BuyAlexandriteDragon(MouseEvent mouseEvent) {
    }



}
