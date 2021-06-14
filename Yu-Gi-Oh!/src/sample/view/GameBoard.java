package sample.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import sample.model.enums.MovingDirection;

public class GameBoard extends StackPane {
    private final ImageView image;
    private final Circle point;
    private final Circle energy;
    private final Rectangle background;
    private final int x, y;

    public GameBoard(double size, Color backgroundColor, int x, int y) {
        this.x = x;
        this.y = y;
        image = new ImageView();
        image.setFitHeight(size);
        image.setFitWidth(size);
        background = new Rectangle(size, size, backgroundColor);
        point = new Circle(size / 10, Color.WHITE);
        point.setVisible(false);
        energy = new Circle(size / 5, Color.RED);
        energy.setVisible(false);
        super.getChildren().add(background);
        super.getChildren().add(point);
        super.getChildren().add(energy);
        super.getChildren().add(image);
    }



    public void placePacman(MovingDirection movingDirection) {
        this.point.setVisible(false);
        this.energy.setVisible(false);
        Image image = null;
        if (movingDirection == MovingDirection.RIGHT)
            image = new Image("/assets/pacman_right.gif");
        else if (movingDirection == MovingDirection.DOWN)
            image = new Image("/assets/pacman_down.gif");
        else if (movingDirection == MovingDirection.LEFT)
            image = new Image("/assets/pacman_left.gif");
        else if (movingDirection == MovingDirection.UP)
            image = new Image("/assets/pacman_up.gif");
        this.image.setImage(image);
    }

    public void placeBlinky(boolean isHunted) {
        this.point.setVisible(false);
        this.energy.setVisible(false);
        Image image;
        if (isHunted)
            image = new Image("/assets/huntedGhost.gif");
        else
            image = new Image("/assets/Blinky.gif");
        this.image.setImage(image);
    }

    public void placeClyde(boolean isHunted) {
        this.point.setVisible(false);
        this.energy.setVisible(false);
        Image image;
        if (isHunted)
            image = new Image("/assets/huntedGhost.gif");
        else
            image = new Image("/assets/clyde.gif");
        this.image.setImage(image);
    }

    public void placePinky(boolean isHunted) {
        this.point.setVisible(false);
        this.energy.setVisible(false);
        Image image;
        if (isHunted)
            image = new Image("/assets/huntedGhost.gif");
        else
            image = new Image("/assets/pinky.gif");
        this.image.setImage(image);
    }

    public void placeInky(boolean isHunted) {
        this.point.setVisible(false);
        this.energy.setVisible(false);
        Image image;
        if (isHunted)
            image = new Image("/assets/huntedGhost.gif");
        else
            image = new Image("/assets/inky.gif");
        this.image.setImage(image);
    }

}
