package ui;

import com.sun.javafx.geom.Vec2d;
import game.Game;
import game.player.Player;
import javafx.scene.text.TextAlignment;
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
        Player player;
        player = Game.getInstance().getPlayer(0);

        for (int i = 0; i < player.getLives(); i++) {
            HEART.draw(MARGIN.x + SPACE * i, CANVAS.getHeight() - MARGIN.y);
        }
        GC.fillText(String.valueOf(player.getScore()), MARGIN.x, CANVAS.getHeight() - 2 * MARGIN.y);

        // Player two lives
        player = Game.getInstance().getPlayer(1);

        for (int i = 0; i < player.getLives(); i++) {
            HEART.draw(CANVAS.getWidth() - (MARGIN.x + SPACE * i), CANVAS.getHeight() - MARGIN.y);
        }
        GC.setTextAlign(TextAlignment.RIGHT);
        GC.fillText(String.valueOf(player.getScore()), CANVAS.getWidth() - MARGIN.x, CANVAS.getHeight() - 2 * MARGIN.y);
        GC.setTextAlign(TextAlignment.LEFT);
    }
}
