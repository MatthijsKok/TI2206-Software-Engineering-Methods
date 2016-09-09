package util;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * Created by wouterraateland on 08-09-16.
 */
public class GameCanvasManager {
    private static GameCanvasManager instance = null;

    private Canvas canvas;
    private GraphicsContext gc;

    protected GameCanvasManager() {
        canvas = new Canvas(1024, 608);
        gc = canvas.getGraphicsContext2D();
    }

    public static GameCanvasManager getInstance() {
        if (instance == null) {
            instance = new GameCanvasManager();
        }
        return instance;
    }

    public Canvas getCanvas() {
        return this.canvas;
    }

    public GraphicsContext getContext() {
        return gc;
    }
}
