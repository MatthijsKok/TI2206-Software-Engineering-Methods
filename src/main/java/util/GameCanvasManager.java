package util;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

/**
 * Singleton class which manages the game canvas and makes it available
 * everywhere.
 */
public final class GameCanvasManager {

    /**
     * The singleton instance of KeyboardInputManager.
     */
    private static GameCanvasManager instance = null;

    /**
     * The canvas of this game.
     */
    private Canvas canvas;

    /**
     * The graphics context where the game is drawn on.
     */
    private GraphicsContext gc;

    /**
     * Private constructor to instantiate the singleton class.
     */
    private GameCanvasManager() {
        Stage stage = StageManager.getStage();
        canvas = new Canvas(stage.getWidth(), stage.getHeight());
        gc = canvas.getGraphicsContext2D();

        StageManager.getRoot().getChildren().add(canvas);
    }

    /**
     * @return the singleton instance of GameCanvasManager.
     */
    public static GameCanvasManager getInstance() {
        if (instance == null) {
            instance = new GameCanvasManager();
        }
        return instance;
    }

    /**
     * @return The game canvas.
     */
    public Canvas getCanvas() {
        return canvas;
    }

    /**
     * @return The graphics canvas to draw on.
     */
    public GraphicsContext getContext() {
        return gc;
    }
}
