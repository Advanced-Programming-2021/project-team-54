package sample.view.components;

import javafx.scene.image.Image;
import sample.view.Main;

import java.util.Objects;

public class Assets {
    public final static Image icon = new Image(Objects.requireNonNull(Main.class.getResource("assets/GameIcon.png").toExternalForm()));
}
