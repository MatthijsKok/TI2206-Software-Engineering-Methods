package util;

import javafx.scene.Scene;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This singleton class manages keyboard input and makes it available
 * everywhere.
 */
public final class KeyboardInputManager {

    /**
     * The list of scenes on which the manager manages keyboard input.
     */
    private static List<Scene> scenes = new ArrayList<>();
    /**
     * The list containing information about which keys are down atm.
     */
    private static Set<String> input = new HashSet<>();
    /**
     * The list containing information about which keys are just pressed.
     */
    private static Set<String> pressed = new HashSet<>();
    /**
     * The list containing information about which keys are just released.
     */
    private static Set<String> released = new HashSet<>();

    /**
     * empty constructor.
     */
    private KeyboardInputManager() {

    }

    /**
     * Adds keyboard input handling to a scene.
     * @param scene the scene to add keyboard input handling to.
     */
    public static void addScene(final Scene scene) {
        if (scenes.contains(scene)) {
            return;
        }

        scenes.add(scene);

        addEventHandlers(scene);
    }

    /**
     * Adds eventHandlers to a scene.
     * @param scene to set the eventHandlers on.
     */
    private static void addEventHandlers(final Scene scene) {
        scene.setOnKeyPressed(e -> pressKey(e.getCode().toString()));
        scene.setOnKeyReleased(e -> releaseKey(e.getCode().toString()));
    }

    /**
     * A key is pressed, add the pressed key to the input.
     * @param code String of the inputKey.
     */
    private static void pressKey(String code) {
        if (!input.contains(code)) {
            pressed.add(code);
            pressed.add("ANY");
        }
        input.add(code);
    }

    /**
     * A key is released, remove the code of the key from input.
     * @param code String with the key that was pressed.
     */
    private static void releaseKey(String code) {
        released.add(code);
        released.add("ANY");
        input.remove(code);
    }

    /**
     * Clears the presses and releases.
     */
    public static void update() {
        pressed.clear();
        released.clear();
    }

    /**
     * When a key is pressed.
     * @param key a string representation of the key to check.
     * @return Boolean, indicates if key is just pressed.
     */
    public static boolean keyPressed(final String key) {
        return pressed.contains(key);
    }

    /**
     * When a key is released.
     * @param key a string representation of the key to check.
     * @return Boolean, indicates if key is just released.
     */
    public static boolean keyReleased(final String key) {
        return released.contains(key);
    }

    /**
     * When a key is held down.
     * @param key a string representation of the key to check.
     * @return Boolean, indicates if key is currently pressed.
     */
    public static boolean keyDown(final String key) {
        return input.contains(key);
    }
}
