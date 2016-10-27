package ui;

import com.sun.javafx.geom.Vec2d;
import game.player.Player;
import graphics.Sprite;
import level.LevelTimer;

/**
 * Draws a HeadsUpDisplay for a game with a single images.player.
 */
public class SinglePlayerHUD extends HeadsUpDisplay {

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
     * The player this HUD draws the lives for.
     */
    private final Player player;

    /**
     * Creates a new SinglePlayerHUD.
     * @param timer The timer to draw the HUD for.
     * @param player The player to draw the HUD for.
     */
    public SinglePlayerHUD(LevelTimer timer, Player player) {
        super(timer);
        this.player = player;
    }

    @Override
    public void draw() {
        super.draw();

        for (int i = player.getLives() - 1; i >= 0; i--) {
            HEART.draw(MARGIN.x + SPACE * i, canvas().getHeight() - MARGIN.y);
        }

        graphicsContext().fillText(
                String.valueOf(player.getScore()),
                MARGIN.x, canvas().getHeight() - 2 * MARGIN.y);
    }
}
