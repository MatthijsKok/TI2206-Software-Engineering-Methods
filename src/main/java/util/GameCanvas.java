package util;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * Created by wouterraateland on 08-09-16.
 */
public class GameCanvas {
    private static GameCanvas instance = null;

    private Canvas canvas;
    private GraphicsContext gc;

    protected GameCanvas() {
        canvas = new Canvas(512, 512);
        gc = canvas.getGraphicsContext2D();
    }

    public static GameCanvas getInstance() {
        if (instance == null) {
            instance = new GameCanvas();
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
