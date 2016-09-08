package util;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

/**
 * Created by wouterraateland on 08-09-16.
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
