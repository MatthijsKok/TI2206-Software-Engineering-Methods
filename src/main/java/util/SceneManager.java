package util;

import javafx.scene.Group;
import javafx.scene.Scene;

import java.util.HashMap;


/**
 * Utility class which manages switching scenes in the main stage of the BubbleTrouble application.
 */
public final class SceneManager {

    /**
     * The Scene that is currently displayed on the stage.
     */
    private static Scene scene;

    /**
     * The root element of the current scene wherein all other elements are put.
     */
    private static Group root;

    /**
     * The HashMap that holds all Scenes.
     */
    private static HashMap<String, Scene> sceneHashMap = new HashMap<>();

    private SceneManager() {

    }

    /**
     * Initializes the scenes.
     */
    public static void init() {
        synchronized (new Object()) {
            SceneManager.createScene("Menu");
            SceneManager.createScene("Settings");
            SceneManager.createScene("Game");
            SceneManager.createScene("Pause");

            KeyboardInputManager.addScene(SceneManager.getScene("Game"));

            SceneManager.setCurrentScene("Menu");
        }
    }


    /**
     * Creates a new Scene with a new Group as root.
     * @param name The key for the Scene value in the HashMap.
     * @return The created Scene.
     */
    public static Scene createScene(String name) {
        Scene scene = new Scene(new Group());
        sceneHashMap.put(name, scene);
        return scene;
    }

    /**
     * Gets a Scene from the HashMap corresponding to the given String.
     * @param sceneString The String parameter key in the HashMap.
     * @return The Scene corresponding to the String parameter.
     */
    public static Scene getScene(String sceneString) {
        return sceneHashMap.get(sceneString);
    }

    /**
     * Add the given Key, Value pair to the sceneHashMap.
     * @param sceneString The String representing the name of the Scene.
     * @param scene The Scene value.
     */
    public static void addScene(String sceneString, Scene scene) {
        sceneHashMap.put(sceneString, scene);
    }

    /**
     * Gets the Scene that is currently displayed.
     * @return The Scene that is currently displayed.
     */
    public static Scene getCurrentScene() {
        return scene;
    }

    /**
     * Set the scene that is displayed in the main Stage.
     * @param sceneString The scene that is displayed in the main Stage.
     */
    public static void setCurrentScene(String sceneString) {
        scene = sceneHashMap.get(sceneString);
        setRoot((Group) getCurrentScene().getRoot());
        StageManager.getStage().setScene(scene);
    }

    /**
     * Gets the root element of the current Scene.
     * @return The root element of the current Scene.
     */
    public static Group getRoot() {
        return root;
    }

    /**
     * Sets the root of the current scene.
     * @param root The root of the current scene.
     */
    private static void setRoot(Group root) {
        SceneManager.root = root;
    }
}


