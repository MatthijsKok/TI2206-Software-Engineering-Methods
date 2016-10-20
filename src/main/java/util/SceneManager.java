package util;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Stack;


/**
 * Utility class which manages switching scenes in the main stage of the BubbleTrouble application.
 */
public final class SceneManager {

    /**
     * The HashMap that holds all Scenes.
     */
    private static HashMap<String, Scene> scenes = new HashMap<>();

    /**
     * The stage on which the scenes are shown.
     */
    private static Stage stage;

    /**
     * The scene history.
     */
    private static Stack<Scene> history = new Stack<>();

    private SceneManager() {

    }

    /**
     * Sets the stage on which scenes are shown.
     * @param stage the stage to show scenes on.
     */
    public static void setStage(Stage stage) {
        SceneManager.stage = stage;
    }

    /**
     * Creates a new Scene with a new Group as root.
     *
     * @param pane The pane with the content of the scene.
     * @return The created Scene.
     */
    private static Scene createScene(final Pane pane) {
        return new Scene(pane);
    }

    /**
     * Gets a Scene from the HashMap corresponding to the given String.
     *
     * @param name String - The name of the scene.
     * @return The Scene corresponding to the name.
     */
    public static Scene getScene(String name) {
        return scenes.get(name);
    }

    /**
     * Gets the Scene that is currently displayed.
     *
     * @return The Scene that is currently displayed.
     */
    public static Scene getCurrentScene() {
        return history.peek();
    }

    /**
     * Add the given Key, Value pair to the sceneHashMap.
     *
     * @param name The String representing the name of the Scene.
     * @param pane The pane that containing the content of the scene.
     */
    public static void addScene(final String name, final Pane pane) {
        scenes.put(name, createScene(pane));
    }

    /**
     * Show the scene corresponding to name.
     * @param name the name of the scene to show.
     */
    public static void goToScene(String name) {
        Scene temp = scenes.get(name);
        if (temp != null) {
            history.push(temp);
        }

        setScene();
    }

    /**
     * Shows the scene that is left last.
     */
    public static void goBack() {
        history.pop();
        setScene();
    }

    private static void setScene() {
        stage.setScene(history.peek());
    }
}


