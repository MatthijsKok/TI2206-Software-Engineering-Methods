package UI;

import game.Game;
import game.Level;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import util.Sprite;

/**
 * Created by wouterraateland on 12-09-16.
 * HUD is a accronym for Heads Up Display. A common name for the overlay of a game.
 */
public class HUD extends UIElement {

    private static Sprite HEART_SPRITE = new Sprite("heart.png");

    private Level level;

    public HUD(Level level) {
        this.level = level;
    }

    /**
     * Draws the lives that the first player of a level has left
     */
    public void draw() {
        Canvas canvas = gc.getCanvas();
        if (Game.getInstance().levelLost()) {
            gc.setFill(Color.DARKRED);
            gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
            gc.setFill(Color.WHITE);
            gc.setFont(Font.font("Georgia", 32));
            gc.fillText("You died...", canvas.getWidth()/2, canvas.getHeight()/2 - 32);
            gc.setFont(Font.font("Georgia", 16));
            gc.fillText("Press R to restart", canvas.getWidth()/2, canvas.getHeight()/2 + 32);
        }

        if (Game.getInstance().levelWon()) {
            gc.setFill(Color.CORNFLOWERBLUE);
            gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
            gc.setFill(Color.WHITE);
            gc.setFont(Font.font("Georgia", 32));
            gc.fillText("You won!", canvas.getWidth()/2, canvas.getHeight()/2 - 32);
            gc.setFont(Font.font("Georgia", 16));
            gc.fillText("Press R to restart", canvas.getWidth()/2, canvas.getHeight()/2 + 32);
        }

        /*int lives = level.getPlayer(0).getLives();

        for (int i = 0; i < lives; i++) {
            HEART_SPRITE.draw(16 + 48*i, gc.getCanvas().getHeight() - 48);
        }*/
    }
}
