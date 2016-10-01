package ui;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * The game lost overlay draws the an overlay after a level has been lost.
 */
class GameLostOverlay extends UIElement {

    /**
     * Font size used for bigger text.
     */
    private static final Font BIGGER_FONT  = Font.font("Georgia", 32);

    /**
     * Font size used for smaller text.
     */
    private static final Font SMALLER_FONT = Font.font("Georgia", 16);

    /**
     * Draws the game lost overlay.
     */
    public void draw() {
        GC.setFill(Color.DARKRED);
        GC.fillRect(0, 0, CANVAS.getWidth(), CANVAS.getHeight());
        GC.setFill(Color.WHITE);

        GC.setFont(BIGGER_FONT);
        GC.fillText("GAME OVER",
                CANVAS.getWidth() / 2,
                CANVAS.getHeight() / 2 - BIGGER_FONT.getSize());

        GC.setFont(SMALLER_FONT);
        GC.fillText("Press R to restart game",
                CANVAS.getWidth() / 2,
                CANVAS.getHeight() / 2 + BIGGER_FONT.getSize());
    }
}
