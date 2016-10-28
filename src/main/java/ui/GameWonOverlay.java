package ui;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * The game won overlay draws an overlay when the game is won.
 */
public class GameWonOverlay extends AbstractUIElement {

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
        getGraphicsContext().setFill(Color.DARKRED);
        getGraphicsContext().fillRect(0, 0, getCanvas().getWidth(), getCanvas().getHeight());
        getGraphicsContext().setFill(Color.WHITE);

        getGraphicsContext().setFont(BIGGER_FONT);
        getGraphicsContext().fillText("You beat the last level!",
                getCanvas().getWidth() / 2,
                getCanvas().getHeight() / 2 - BIGGER_FONT.getSize());

        getGraphicsContext().setFont(SMALLER_FONT);
        getGraphicsContext().fillText("Press R to return to the main menu",
                getCanvas().getWidth() / 2,
                getCanvas().getHeight() / 2 + BIGGER_FONT.getSize());
    }
}
