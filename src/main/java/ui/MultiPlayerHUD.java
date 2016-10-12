package ui;

import com.sun.javafx.geom.Vec2d;
import game.Game;
import game.player.Player;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.TextAlignment;
import graphics.Sprite;

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
     * @param canvas The Canvas to draw on
     * @param graphicsContext The GraphicsContext to draw on
     */
    void draw(Canvas canvas, GraphicsContext graphicsContext) {
        // Time bar
        super.draw(canvas, graphicsContext);

        // Player one lives
        Player player;
        player = Game.getInstance().getPlayer(0);

        for (int i = 0; i < player.getLives(); i++) {
            HEART.draw(MARGIN.x + SPACE * i, canvas.getHeight() - MARGIN.y);
        }
        graphicsContext.fillText(String.valueOf(player.getScore()),
                MARGIN.x, canvas.getHeight() - 2 * MARGIN.y);

        // Player two lives
        player = Game.getInstance().getPlayer(1);

        for (int i = 0; i < player.getLives(); i++) {
            HEART.draw(canvas.getWidth() - (MARGIN.x + SPACE * i), canvas.getHeight() - MARGIN.y);
        }
        graphicsContext.setTextAlign(TextAlignment.RIGHT);
        graphicsContext.fillText(String.valueOf(player.getScore()),
                canvas.getWidth() - MARGIN.x, canvas.getHeight() - 2 * MARGIN.y);
        graphicsContext.setTextAlign(TextAlignment.LEFT);
    }
}
