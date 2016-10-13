package ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * The level time up overlay draws the an overlay after a level has
 * run out of time.
 */
class GameWonOverlay extends AbstractUIElement {

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
     * @param canvas The Canvas to draw on
     * @param graphicsContext The GraphicsContext to draw on
     */
    /* default */ void draw(final Canvas canvas, final GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.DARKRED);
        graphicsContext.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        graphicsContext.setFill(Color.WHITE);

        graphicsContext.setFont(BIGGER_FONT);
        graphicsContext.fillText("You beat the last level!",
                canvas.getWidth() / 2,
                canvas.getHeight() / 2 - BIGGER_FONT.getSize());

        graphicsContext.setFont(SMALLER_FONT);
        graphicsContext.fillText("Press R to return to the main menu",
                canvas.getWidth() / 2,
                canvas.getHeight() / 2 + BIGGER_FONT.getSize());
    }
}
