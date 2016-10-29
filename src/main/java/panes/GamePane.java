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
    public GamePane(final Stage stage) {
        super();
        final Canvas canvas = CanvasManager.createCanvas(stage);

        CanvasManager.setCanvas(canvas);

        getChildren().add(canvas);
    }

    /**
     * Sets the overlay that should be shown at certain game events.
     * @param overlay The overlay that should be shown.
     */
    public void setOverlay(final Pane overlay) {
        getChildren().add(1, overlay);
    }

    /**
     * Removes the overlay if it is currently being shown.
     */
    public void removeOverlay() {
        if (getChildren().size() > 1) {
            getChildren().remove(1);
        }
    }



}
