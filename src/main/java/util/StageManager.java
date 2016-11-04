package util;

import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Utility class which manages the main stage of the BubbleTrouble application.
 */
public final class StageManager {

    /**
     * The default width of the game getCanvas.
     */
    private static final double DEFAULT_WIDTH = 1024;
    /**
     * The default height of the game getCanvas.
     */
    private static final double DEFAULT_HEIGHT = 640;
    /**
     * The main stage of the whole application.
     */
    private static Stage stage;

    /**
     * Empty constructor.
     */
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
            stage.setResizable(false);
            stage.setWidth(DEFAULT_WIDTH);
            stage.setHeight(DEFAULT_HEIGHT);
            stage.getIcons().add(new Image("images/logo.png"));
        }
        
        stage.show();
    }

    /**
     * Sets the main stage for the application.
     * @param stage The main stage for the application.
     */
    private static void setStage(final Stage stage) {
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
