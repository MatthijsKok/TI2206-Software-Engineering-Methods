package util;

import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Utility class which manages the main stage of the BubbleTrouble application.
 */
public final class StageManager {

    /**
     * The default width of the game canvas.
     */
    private static final double DEFAULT_WIDTH = 1024;

    /**
     * The default height of the game canvas.
     */
    private static final double DEFAULT_HEIGHT = 608;

    /**
     * The main stage of the whole application.
     */
    private static Stage stage;

    private StageManager() {

    }

    /**
     * Initializes the stage.
     * @param stage the stage instance to initialize for.
     */
    public static void init(final Stage stage) {
        if (StageManager.stage != null) {
            return;
        }

        synchronized (new Object()) {
            setStage(stage);
            getStage().setResizable(false);
            getStage().setMinWidth(DEFAULT_WIDTH);
            getStage().setMinHeight(DEFAULT_HEIGHT);
            getStage().getIcons().add(new Image("logo.png"));
            getStage().initStyle(StageStyle.UNDECORATED);
        }
        stage.show();
    }

    /**
     * Sets the main stage for the application.
     * @param stage The main stage for the application.
     */
    private static void setStage(Stage stage) {
        StageManager.stage = stage;
    }

    /**
     * Gets the main Stage.
     * @return the main Stage.
     */
    public static Stage getStage() {
        return stage;
    }

}
