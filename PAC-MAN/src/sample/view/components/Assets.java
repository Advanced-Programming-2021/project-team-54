package sample.view.components;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.controller.Client;
import sample.view.Main;
import sample.view.MainMenu;

import java.util.Objects;

public class Assets {
    public final static Image icon = new Image("assets/Logos/GUI_T_GateLogo_s001.dds.png");
    public static Image getPlayerProfileImage() {
        return new Image("assets/ProfilePictures/Chara001.dds" + Client.getPictureID() + ".png");
    }
}
