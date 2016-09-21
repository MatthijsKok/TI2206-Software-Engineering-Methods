package util;

import javafx.scene.Scene;

import java.util.ArrayList;
import java.util.List;

/**
 * This singleton class manages keyboard input and makes it available
 * everywhere.
 */
public final class KeyboardInputManager {

    /**
     * The singleton instance of KeyboardInputManager.
     */
    private static KeyboardInputManager instance = null;

    /**
     * The list of scenes on which the manager manages keyboard input.
     */
    private List<Scene> scenes = new ArrayList<>();

    /**
     * The list containing information about which keys are pressed at any time.
     */
    private List<String> input = new ArrayList<>();

    /**
     * This overrides the default public constructor.
     */
    private KeyboardInputManager() { }

    /**
     * @return the singleton instance of KeyboardInputManager.
     */
    public static KeyboardInputManager getInstance() {
        if (instance == null) {
            instance = new KeyboardInputManager();
        }

        return instance;
    }

    /**
     * Adds keyboard input handling to a scene.
     * @param scene the scene to add keyboard input handling to.
     */
    public void addScene(final Scene scene) {
        scenes.add(scene);

        scene.setOnKeyPressed(e -> {
            String code = e.getCode().toString();

            if (!input.contains(code)) {
                input.add(code);
            }
        });

        scene.setOnKeyReleased(e -> {
            String code = e.getCode().toString();
            input.remove(code);
        });
    }

    /**
     * Removes keyboard input handling from a scene.
     * @param scene the scene to remove keyboard input handling from.
     */
    public void removeScene(final Scene scene) {
        if (scenes.contains(scene)) {
            scenes.remove(scene);
            scene.setOnKeyPressed(e -> {
            });
            scene.setOnKeyReleased(e -> {
            });
        }
    }

    /**
     * @param key a string representation of the key to check.
     * @return a boolean that indicates if key is currently pressed.
     */
    public boolean keyPressed(final String key) {
        return input.contains(key);
    }
}
