package UI;

import javafx.scene.canvas.GraphicsContext;
import util.GameCanvasManager;

/**
 * Created by wouterraateland on 12-09-16.
 */
public abstract class UIElement {
    protected static GraphicsContext gc = GameCanvasManager.getInstance().getContext();

    public abstract void draw();
}
