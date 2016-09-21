package ui;

import game.Game;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import util.Sprite;

/**
 * Created by wouterraateland on 12-09-16.
 * HUD is a accronym for Heads Up Display. A common name for the overlay of a game.
 */
public class HUD extends UIElement {

    /**
     * The sprite representing the lives of the player.
     */
    private static final Sprite HEART_SPRITE = new Sprite("heart.png");

    /**
     * Font size used for bigger text.
     */
    private static final double BIGGER_FONT_SIZE = 32;

    /**
     * Font size used for smaller text.
     */
    private static final double SMALLER_FONT_SIZE = 16;


    /**
     * Creates a new HUD object.
     */
    public HUD() {

    }

    /**
     * Draws the lives that the first player of a level has left.
     */
    public void draw() {
        Canvas canvas = gc.getCanvas();
        if (Game.getInstance().levelLost()) {
            drawLostScreen(canvas);
        }

        if (Game.getInstance().levelWon()) {
            drawWonScreen(canvas);
        }



        /*int lives = level.getPlayer(0).getLives();

        for (int i = 0; i < lives; i++) {
            HEART_SPRITE.draw(16 + 48*i, gc.getCanvas().getHeight() - 48);
        }*/
    }

    private void drawWonScreen(Canvas canvas) {
        gc.setFill(Color.CORNFLOWERBLUE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.WHITE);

        gc.setFont(Font.font("Georgia", BIGGER_FONT_SIZE));
        gc.fillText("You won!",
                canvas.getWidth() / 2,
                canvas.getHeight() / 2 - BIGGER_FONT_SIZE);

        gc.setFont(Font.font("Georgia", SMALLER_FONT_SIZE));
        gc.fillText("Press R to restart",
                canvas.getWidth() / 2,
                canvas.getHeight() / 2 + BIGGER_FONT_SIZE);
    }

    private void drawLostScreen(Canvas canvas) {
        gc.setFill(Color.DARKRED);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.WHITE);

        gc.setFont(Font.font("Georgia", BIGGER_FONT_SIZE));
        gc.fillText("You died...",
                canvas.getWidth() / 2,
                canvas.getHeight() / 2 - BIGGER_FONT_SIZE);

        gc.setFont(Font.font("Georgia", SMALLER_FONT_SIZE));
        gc.fillText("Press R to restart",
                canvas.getWidth() / 2,
                canvas.getHeight() / 2 + BIGGER_FONT_SIZE);
    }


}
