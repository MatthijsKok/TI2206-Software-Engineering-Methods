package ui;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * The level time up overlay draws the an overlay after a level
 * has run out of time.
 */
class LevelTimeUpOverlay extends AbstractUIElement {

    /**
     * Font size used for bigger text.
     */
    private static final Font BIGGER_FONT  = Font.font("Georgia", 32);

    /**
     * Font size used for smaller text.
     */
    private static final Font SMALLER_FONT = Font.font("Georgia", 16);

    @Override
    public void draw() {
        graphicsContext().setFill(Color.DARKRED);
        graphicsContext().fillRect(0, 0, canvas().getWidth(), canvas().getHeight());
        graphicsContext().setFill(Color.WHITE);

        graphicsContext().setFont(BIGGER_FONT);
        graphicsContext().fillText("Time's up...",
                canvas().getWidth() / 2,
                canvas().getHeight() / 2 - BIGGER_FONT.getSize());

        graphicsContext().setFont(SMALLER_FONT);
        graphicsContext().fillText("Press R to restart level",
                canvas().getWidth() / 2,
                canvas().getHeight() / 2 + BIGGER_FONT.getSize());
    }
}
