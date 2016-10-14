package ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * The game lost overlay draws the an overlay after a level has been lost.
 */
class GameLostOverlay extends AbstractUIElement {

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
     * @param canvas the canvas to draw on
     * @param graphicsContext the graphicsContext to draw on
     */
    public void draw(final Canvas canvas, final GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.DARKRED);
        graphicsContext.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        graphicsContext.setFill(Color.WHITE);

        graphicsContext.setFont(BIGGER_FONT);
        graphicsContext.fillText("GAME OVER",
                canvas.getWidth() / 2,
                canvas.getHeight() / 2 - BIGGER_FONT.getSize());

        graphicsContext.setFont(SMALLER_FONT);
        graphicsContext.fillText("Press R to return to the main menu",
                canvas.getWidth() / 2,
                canvas.getHeight() / 2 + BIGGER_FONT.getSize());
    }
}
