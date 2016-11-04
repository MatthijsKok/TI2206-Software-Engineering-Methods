package graphics.ui;

import com.sun.javafx.geom.Vec2d;
import game.player.Player;
import graphics.Sprite;

/**
 * Class responsible for drawing the lives and score of player 1 (Mario) to the screen.
 * Adheres to the decorator pattern.
 */
public class MarioStats extends AbstractStats {

    /**
     * The mugshot next to the lives counter.
     */
    private static final Sprite MUGSHOT = new Sprite("mario_mugshot.png");
    /**
     * Position the mugshot is drawn at.
     */
    private static final Vec2d MUGSHOT_POSITION = new Vec2d(16, 560);
    /**
     * Scale the mugshot is drawn at.
     */
    private static final double MUGSHOT_SCALE = 40.d / MUGSHOT.getWidth();
    /**
     * Position the lives are drawn at.
     */
    private static final Vec2d LIVES_POSITION = new Vec2d(49, 595);
    /**
     * Position the score is drawn at.
     */
    private static final Vec2d SCORE_POSITION = new Vec2d(100, 590);

    /**
     * The players this HUD draws the lives for.
     */
    private final Player player;

    /**
     * Creates a new SinglePlayerHUD.
     *
     * @param player  The player to draw the HUD for.
     * @param element The UIElement to draw over.
     */
    public MarioStats(UIElement element, Player player) {
        super(element);
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
