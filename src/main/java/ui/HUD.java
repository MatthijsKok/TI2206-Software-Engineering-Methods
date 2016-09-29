package ui;

import game.Game;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * HUD is a acronym for Heads Up Display. A common name for the overlay of a game.
 */
public class HUD extends UIElement {

    /**
     * The logger access point to which everything will be logged.
     */
    private static final util.logging.Logger LOGGER = new util.logging.Logger();

    /**
     * Font size used for bigger text.
     */
    private static final Font BIGGER_FONT  = Font.font("Georgia", 32);

    /**
     * Font size used for smaller text.
     */
    private static final Font SMALLER_FONT = Font.font("Georgia", 16);

    /**
     * Creates a new HUD object.
     */
    public HUD() {

    }

    /**
     * Draws the lives that the first player of a level has left.
     */
    public void draw() {
        Canvas canvas = gc().getCanvas();
        LOGGER.trace("Drawing ui element: " + canvas.toString());
        if (Game.getInstance().levelLost()) {
            drawLostScreen(canvas);
        }

        if (Game.getInstance().levelWon()) {
            drawWonScreen(canvas);
        }
    }

    private void drawWonScreen(Canvas canvas) {
        LOGGER.debug("Drawing Level Won Screen...");
        gc().setFill(Color.CORNFLOWERBLUE);
        gc().fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc().setFill(Color.WHITE);

        gc().setFont(BIGGER_FONT);
        gc().fillText("You won!",
                canvas.getWidth() / 2,
                canvas.getHeight() / 2 - BIGGER_FONT.getSize());

        gc().setFont(SMALLER_FONT);
        gc().fillText("Press R to restart",
                canvas.getWidth() / 2,
                canvas.getHeight() / 2 + BIGGER_FONT.getSize());
        LOGGER.debug("Level Won Screen drawn.");
    }

    private void drawLostScreen(Canvas canvas) {
        LOGGER.debug("Drawing Level Lost Screen...");
        gc().setFill(Color.DARKRED);
        gc().fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc().setFill(Color.WHITE);

        gc().setFont(BIGGER_FONT);
        gc().fillText("You died...",
                canvas.getWidth() / 2,
                canvas.getHeight() / 2 - BIGGER_FONT.getSize());

        gc().setFont(SMALLER_FONT);
        gc().fillText("Press R to restart",
                canvas.getWidth() / 2,
                canvas.getHeight() / 2 + BIGGER_FONT.getSize());
        LOGGER.debug("Level Lost Screen drawn.");
    }


}
