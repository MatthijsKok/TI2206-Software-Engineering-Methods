package panes;

import game.state.GameStateHelper;
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

        final Pane overlay = new Pane();
        overlay.setPrefSize(
                canvas.getWidth(),
                canvas.getHeight());

        getChildren().add(overlay);

        GameStateHelper.setAnchor(overlay);
    }

}
