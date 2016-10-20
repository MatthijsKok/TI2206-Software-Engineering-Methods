package panes;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import util.CanvasManager;

/**
 * The pane whereon the game is drawn.
 */
public class GamePane extends Pane {

    /**
     * Creates a new game.
     * @param stage The stage the game is run in.
     */
    public GamePane(Stage stage) {
        Canvas canvas = CanvasManager.createCanvas(stage);

        CanvasManager.setCanvas(canvas);

        getChildren().add(canvas);
    }
}
