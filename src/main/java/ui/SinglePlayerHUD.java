package ui;

import com.sun.javafx.geom.Vec2d;
import graphics.Sprite;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * Draws a HeadsUpDisplay for a game with a single images.player.
 */
class SinglePlayerHUD extends HeadsUpDisplay {

    /**
     * The heart sprite.
     */
    private static final Sprite HEART = new Sprite("images/heart.png");

    /**
     * The offset of the start place of the hearts.
     */
    private static final Vec2d MARGIN = new Vec2d(16, 64);

    /**
     * The space between to heart sprites.
     */
    private static final double SPACE = 48;

    /**
     * Draws amount of lives and score of the first images.player.
     * @param canvas The Canvas to draw on
     * @param graphicsContext The GraphicsContext to draw on
     */
    /* default */ void draw(final Canvas canvas, final GraphicsContext graphicsContext) {
        super.draw(canvas, graphicsContext);

        for (int i = getPlayerLives(0) - 1; i >= 0; i--) {
            HEART.draw(MARGIN.x + SPACE * i, canvas.getHeight() - MARGIN.y);
        }

        graphicsContext.fillText(String.valueOf(getPlayerScore(0)), MARGIN.x, canvas.getHeight() - 2 * MARGIN.y);
    }
}
