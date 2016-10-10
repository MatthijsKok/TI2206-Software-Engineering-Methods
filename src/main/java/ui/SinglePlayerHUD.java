package ui;

import com.sun.javafx.geom.Vec2d;
import game.Game;
import game.player.Player;
import util.Sprite;

/**
 * Draws a HUD for a game with a single player.
 */
class SinglePlayerHUD extends HUD {

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
     * Draws amount of lives and score of the first player.
     */
    public void draw() {
        super.draw();

        Player player = Game.getInstance().getPlayer(0);

        for (int i = 0; i < player.getLives(); i++) {
            HEART.draw(MARGIN.x + SPACE * i, CANVAS.getHeight() - MARGIN.y);
        }
        GC.fillText(String.valueOf(player.getScore()), MARGIN.x, CANVAS.getHeight() - 2 * MARGIN.y);
    }
}
