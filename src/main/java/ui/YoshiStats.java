package ui;

import game.player.Player;
import javafx.scene.image.Image;

/**
 * Class responsible for drawing the lives and score of player 2 (Yoshi) to the screen.
 * Adheres to the decorator pattern.
 */
public class YoshiStats extends AbstractStats {

    /**
     * The mugshot next to the lives counter.
     */
    public static final Image YOSHI_MUGSHOT = new Image("images/mugshots/yoshi_mugshot.png");

    /**
     * The spacing between the two lives and scores of the player.
     */
    private static final int SPACING = 800;

    /**
     * Player object to draw the score and lives for.
     */
    private final Player player;

    /**
     * Creates a new YoshiStats object.
     * @param newUI The UIElement to draw over.
     * @param player Player object to draw the score and lives for.
     */
    public YoshiStats(UIElement newUI, Player player) {
        super(newUI);
        this.player = player;
    }

    @Override
    /* default */ void drawMugshots() {
        getGraphicsContext().drawImage(YOSHI_MUGSHOT, getMugshotMargin().x
                + SPACING, getMugshotMargin().y, getMugshotSize(), getMugshotSize());
    }

    /**
     * Draws the life counters to the screen.
     */
    /* default */ void drawLifeCounters() {

        //Set font to smaller font
        getGraphicsContext().setFont(getLivesFont());

        getGraphicsContext().fillText(
                formatLivesString(player),
                getLivesMargin().x + SPACING, getLivesMargin().y);
    }

    /**
     * Draws the score to the screen.
     */
    /* default */ void drawScore() {

        //Set bigger font
        getGraphicsContext().setFont(getScoreFont());

        //Draw score
        getGraphicsContext().fillText(
                formatScoreString(player),
                getScoreMargin().x + SPACING, getScoreMargin().y);
    }
}
