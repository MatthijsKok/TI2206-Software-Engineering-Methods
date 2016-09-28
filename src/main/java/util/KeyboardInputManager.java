package util;

import javafx.scene.Scene;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * This singleton class manages keyboard input and makes it available
 * everywhere.
 */
public final class KeyboardInputManager extends Observable {

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
        if (scenes.contains(scene)) {
            return;
        }

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
     * @param key a string representation of the key to check.
     * @return a boolean that indicates if key is currently pressed.
     */
    public boolean keyPressed(final String key) {
        return input.contains(key);
    }
}
