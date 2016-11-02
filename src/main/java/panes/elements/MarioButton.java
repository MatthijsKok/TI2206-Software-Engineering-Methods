package panes.elements;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * MarioButton is the standard button used in the game.
 */
public class MarioButton extends Button {

    /**
     * Creates a new MarioButton with the text and event handler.
     * @param text     String, the text to display on the button.
     * @param onAction EventHandler, the method that occurs when
     *                 the button is clicked.
     */
    public MarioButton(String text, EventHandler<ActionEvent> onAction) {
        setText(text);
        setOnAction(onAction);
    }
}
