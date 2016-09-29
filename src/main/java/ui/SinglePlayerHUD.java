package ui;

import com.sun.javafx.geom.Vec2d;
import game.Game;
import util.Sprite;

/**
 * Draws a HUD for a game with a single player.
 */
public class SinglePlayerHUD extends HUD {

    /**
     * The heart sprite.
     */
    private static final Sprite HEART = new Sprite("heart.png");

    /**
     * The offset of the start place of the hearts.
     */
    private static final Vec2d MARGIN = new Vec2d(16, 64);

    /**
     * The space between to heart sprites.
     */
    private static final double SPACE = 48;

    /**
     * Draws amount of lives of the first player.
     */
    public void draw() {
        super.draw();

        int lives = Game.getInstance().getPlayer(0).getLives();

        for (int i = 0; i < lives; i++) {
            HEART.draw(MARGIN.x + SPACE * i, canvas.getHeight() - MARGIN.y);
        }
    }
}
