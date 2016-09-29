package ui;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * The level lost overlay draws the an overlay after a level has been won.
 */
public class LevelWonOverlay extends UIElement {

    /**
     * Font size used for bigger text.
     */
    private static final Font BIGGER_FONT  = Font.font("Georgia", 32);

    /**
     * Font size used for smaller text.
     */
    private static final Font SMALLER_FONT = Font.font("Georgia", 16);

    /**
     * Draws the level lost overlay.
     */
    public void draw() {

        gc.setFill(Color.CORNFLOWERBLUE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.WHITE);

        gc.setFont(BIGGER_FONT);
        gc.fillText("You won!",
                canvas.getWidth() / 2,
                canvas.getHeight() / 2 - BIGGER_FONT.getSize());

        gc.setFont(SMALLER_FONT);
        gc.fillText("Press R to start next level.",
                canvas.getWidth() / 2,
                canvas.getHeight() / 2 + BIGGER_FONT.getSize());
    }
}
