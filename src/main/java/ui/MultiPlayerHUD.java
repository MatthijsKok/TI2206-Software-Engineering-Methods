package ui;

import com.sun.javafx.geom.Vec2d;
import game.player.Player;
import graphics.Sprite;
import javafx.scene.text.TextAlignment;
import level.LevelTimer;

/**
 * Draws a HeadsUpDisplay for a game with a single player.
 */
public class MultiPlayerHUD extends HeadsUpDisplay {

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
     * The players this HUD draws the lives for.
     */
    private final Player player1, player2;

    /**
     * Creates a new SinglePlayerHUD.
     * @param timer   The timer to draw the HUD for.
     * @param player1 The player to draw the HUD for.
     * @param player2 The player to draw the HUD for.
     */
    public MultiPlayerHUD(LevelTimer timer, Player player1, Player player2) {
        super(timer);
        this.player1 = player1;
        this.player2 = player2;
    }

    @Override
    public void draw() {
        // Time bar
        super.draw();

        // Player one lives
        for (int i = player1.getLives() - 1; i >= 0; i--) {
            HEART.draw(MARGIN.x + SPACE * i, getCanvas().getHeight() - MARGIN.y);
        }
        getGraphicsContext().fillText(String.valueOf(player1.getScore()),
                MARGIN.x, getCanvas().getHeight() - 2 * MARGIN.y);

        // Player two lives
        for (int i = player2.getLives() - 1; i >= 0; i--) {
            HEART.draw(getCanvas().getWidth() - (MARGIN.x + SPACE * i), getCanvas().getHeight() - MARGIN.y);
        }
        getGraphicsContext().setTextAlign(TextAlignment.RIGHT);
        getGraphicsContext().fillText(String.valueOf(player2.getScore()),
                getCanvas().getWidth() - MARGIN.x, getCanvas().getHeight() - 2 * MARGIN.y);
        getGraphicsContext().setTextAlign(TextAlignment.LEFT);
    }
}
