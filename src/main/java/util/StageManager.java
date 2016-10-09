package util;

import javafx.scene.Group;
import javafx.scene.Scene;
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

    /**
     * The root element wherein all other elements are put.
     */
    private static Group root;

    private StageManager() {

    }

    /**
     * Initializes the stage.
     * @param stage the stage instance to initialize for.
     */
    public static synchronized void init(Stage stage) {
        if (StageManager.stage != null) { return; }

        StageManager.stage = stage;
        root = new Group();
        Scene scene = new Scene(root);
        KeyboardInputManager.getInstance().addScene(scene);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.setMinWidth(DEFAULT_WIDTH);
        stage.setMinHeight(DEFAULT_HEIGHT);
        stage.getIcons().add(new Image("logo.png"));
        stage.initStyle(StageStyle.UNDECORATED);

        stage.show();
    }

    /**
     * @return the main stage
     */
    public static Stage getStage() {
        return stage;
    }

    /**
     * @return the root element
     */
    public static Group getRoot() {
        return root;
    }
}
