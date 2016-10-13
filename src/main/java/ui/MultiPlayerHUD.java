package ui;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.TextAlignment;
import graphics.Sprite;

/**
 * Draws a HeadsUpDisplay for a game with a single player.
 */
class MultiPlayerHUD extends HeadsUpDisplay {

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
    void draw(final Canvas canvas, final GraphicsContext graphicsContext) {
        // Time bar
        super.draw(canvas, graphicsContext);

        // Player one lives
        for (int i = getPlayerLives(0) - 1; i >= 0; i--) {
            HEART.draw(MARGIN.x + SPACE * i, canvas.getHeight() - MARGIN.y);
        }
        graphicsContext.fillText(String.valueOf(getPlayerScore(0)),
                MARGIN.x, canvas.getHeight() - 2 * MARGIN.y);

        // Player two lives
        for (int i = getPlayerLives(1); i >= 0; i--) {
            HEART.draw(canvas.getWidth() - (MARGIN.x + SPACE * i), canvas.getHeight() - MARGIN.y);
        }
        graphicsContext.setTextAlign(TextAlignment.RIGHT);
        graphicsContext.fillText(String.valueOf(getPlayerScore(1)),
                canvas.getWidth() - MARGIN.x, canvas.getHeight() - 2 * MARGIN.y);
        graphicsContext.setTextAlign(TextAlignment.LEFT);
    }
}
