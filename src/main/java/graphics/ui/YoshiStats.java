package graphics.ui;

import com.sun.javafx.geom.Vec2d;
import game.player.Player;
import graphics.Sprite;

/**
 * Class responsible for drawing the lives and score of player 2 (Yoshi) to the screen.
 * Adheres to the decorator pattern.
 */
public class YoshiStats extends AbstractStats {
    /**
     * The mugshot next to the lives counter.
     */
    private static final Sprite MUGSHOT = new Sprite("yoshi_mugshot.png");
    /**
     * Position the mugshot is drawn at.
     */
    private static final Vec2d MUGSHOT_POSITION = new Vec2d(816, 560);
    /**
     * Scale the mugshot is drawn at.
     */
    private static final double MUGSHOT_SCALE = 40.d / MUGSHOT.getHeight();
    /**
     * Position the lives are drawn at.
     */
    private static final Vec2d LIVES_POSITION = new Vec2d(849, 595);
    /**
     * Position the score is drawn at.
     */
    private static final Vec2d SCORE_POSITION = new Vec2d(900, 590);

    /**
     * Player object to draw the score and lives for.
     */
    private final Player player;

    /**
     * Creates a new YoshiStats object.
     *
     * @param newUI  The UIElement to draw over.
     * @param player Player object to draw the score and lives for.
     */
    public YoshiStats(UIElement newUI, Player player) {
        super(newUI);
        this.player = player;
    }

    @Override
    public void draw() {
        super.draw();
        drawMugshot(MUGSHOT, MUGSHOT_POSITION, MUGSHOT_SCALE);
        drawLifeCounter(player.getLives(), LIVES_POSITION);
        drawScore(player.getScore(), SCORE_POSITION);
    }
}
