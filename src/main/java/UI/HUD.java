package UI;

import game.Level;
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
        /*int lives = level.getPlayer(0).getLives();

        for (int i = 0; i < lives; i++) {
            HEART_SPRITE.draw(16 + 48*i, gc.getCanvas().getHeight() - 48);
        }*/
    }
}
