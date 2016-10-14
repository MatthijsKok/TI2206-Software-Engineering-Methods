package util;

import javafx.scene.Scene;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * This singleton class manages keyboard input and makes it available
 * everywhere.
 */
public final class KeyboardInputManager extends Observable {

    /**
     * The singleton instance of KeyboardInputManager.
     */
    private static KeyboardInputManager instance = new KeyboardInputManager();

    /**
     * The list of scenes on which the manager manages keyboard input.
     */
    private static List<Scene> scenes = new ArrayList<>();

    /**
     * The list containing information about which keys are pressed at any time.
     */
    private static List<String> input = new ArrayList<>();

    /**
     * This overrides the default public constructor.
     */
    public KeyboardInputManager() { }

    /**
     * Adds an observer to the keyboard.
     * @param observer the observer to add.
     */
    public static void addListener(Observer observer) {
        instance.addObserver(observer);
    }

    /**
     * Adds keyboard input handling to a scene.
     * @param scene the scene to add keyboard input handling to.
     */
    static void addScene(final Scene scene) {
        if (scenes.contains(scene)) {
            return;
        }

        scenes.add(scene);

        instance.addEventHandlers(scene);
    }

    private void addEventHandlers(final Scene scene) {
        scene.setOnKeyPressed(e -> {
            String code = e.getCode().toString();

            if (!input.contains(code)) {
                input.add(code);
            }

            setChanged();
            notifyObservers();
        });

        scene.setOnKeyReleased(e -> {
            String code = e.getCode().toString();
            input.remove(code);

            setChanged();
            notifyObservers();
        });
    }

    /**
     * @param key a string representation of the key to check.
     * @return a boolean that indicates if key is currently pressed.
     */
    public static boolean keyPressed(final String key) {
        return input.contains(key);
    }
}
