package ui;

import com.sun.javafx.geom.Vec2d;
import game.Game;
import util.Sprite;

/**
 * Draws a HUD for a game with a single player.
 */
class MultiPlayerHUD extends HUD {

    /**
     * The heart sprite.
     */
    private static final Sprite HEART = new Sprite("heart.png");
    /**
     * The offset of the start place of the hearts.
     */
    private static final Vec2d MARGIN = new Vec2d(32, 64);
    /**
     * The space between to heart sprites.
     */
    private static final double SPACE = 48;

    static {
        HEART.setOffsetToCenter();
    }

    /**
     * Draws amount of lives of the two players.
     */
    public void draw() {
        // Time bar
        super.draw();

        // Player one lives
        int lives;
        lives = Game.getInstance().getPlayer(0).getLives();

        for (int i = 0; i < lives; i++) {
            HEART.draw(MARGIN.x + SPACE * i, CANVAS.getHeight() - MARGIN.y);
        }

        // Player two lives
        lives = Game.getInstance().getPlayer(1).getLives();

        for (int i = 0; i < lives; i++) {
            HEART.draw(CANVAS.getWidth() - (MARGIN.x + SPACE * i), CANVAS.getHeight() - MARGIN.y);
        }
    }
}
