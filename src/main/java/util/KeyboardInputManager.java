package util;

import javafx.scene.Scene;

import java.util.ArrayList;

/**
 * A Class that manages keyboard input
 */
public class KeyboardInputManager {

    protected static KeyboardInputManager instance = null;

    private Scene scene;
    private ArrayList<String> input;

    private KeyboardInputManager() {
        scene = null;
        input = new ArrayList<>();
    }

    public static KeyboardInputManager getInstance() {
        if (instance == null) {
            instance = new KeyboardInputManager();
        }

        return instance;
    }

    public void addScene(Scene scene) {
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

    public boolean keyPressed(String key) {
        return input.contains(key);
    }
}
