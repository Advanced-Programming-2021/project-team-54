package sample.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import sample.model.enums.Scenes;

import java.io.File;
import java.util.Objects;

public class Main extends Application {
    private static Scene scene;
    public Label welcome;
    public GridPane gameTitle;
    public Button loginButton;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //primaryStage.setResizable(false);
        primaryStage.setTitle("Yu-Gi-Oh!");
        Image icon = new Image("assets/Logos/GUI_T_GateLogo_s001.dds.png");
        primaryStage.getIcons().add(icon);
        Pane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/welcome.fxml")));
        scene = new Scene(root, 1280, 720);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    public void initialize() {
        Image image = new Image("assets/Sleeve/50151.png");
        ImageView imageView = new ImageView(image);
       // gameTitle.add(imageView, 0, 0);
    }

    public static void main(String[] args) {
        checkFolders();
        launch(args);
    }

    private static void checkFolders() {
        File file = new File(System.getProperty("user.dir") + "\\src\\users");
        file.mkdir();
        file = new File(System.getProperty("user.dir") + "\\src\\decks");
        file.mkdir();
        file = new File(System.getProperty("user.dir") + "\\src\\cards");
        file.mkdir();
    }

    public void exitGame(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void signup(MouseEvent mouseEvent) {
        changeScene(Scenes.SIGNUP);
    }

    public void login(MouseEvent mouseEvent) {
        changeScene(Scenes.LOGIN);
    }

    public static Scene getScene() {
        return scene;
    }

    public static void changeScene(Scenes scene) {
        try {
            Pane root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(scene.getResourceFilename())));
            Main.scene.setRoot(root);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
