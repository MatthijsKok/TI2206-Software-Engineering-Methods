package ui;

import game.player.Player;
import javafx.scene.image.Image;

/**
 * Class responsible for drawing the lives and score of player 1 (Mario) to the screen.
 * Adheres to the decorator pattern.
 */
public class MarioStats extends AbstractStats {

    /**
     * The mugshot next to the lives counter.
     */
    public static final Image MARIO_MUGSHOT = new Image("images/mugshots/mario_mugshot.png");

    /**
     * The players this HUD draws the lives for.
     */
    private final Player player;

    /**
     * Creates a new SinglePlayerHUD.
     * @param player The player to draw the HUD for.
     * @param newUI The UIElement to draw over.
     */
    public MarioStats(UIElement newUI, Player player) {
        super(newUI);
        this.player = player;
    }

    @Override
    /* default */ void drawMugshots() {
        getGraphicsContext().drawImage(MARIO_MUGSHOT, getMugshotMargin().x,
                                       getMugshotMargin().y, getMugshotSize(), getMugshotSize());
    }

    @Override
    /* default */ void drawLifeCounters() {
        //Set font to smaller font
        getGraphicsContext().setFont(getLivesFont());

        getGraphicsContext().fillText(
                formatLivesString(player),
                getLivesMargin().x, getLivesMargin().y);
    }

    @Override
    /* default */ void drawScore() {

        //Set bigger font
        getGraphicsContext().setFont(getScoreFont());

        //Draw score
        getGraphicsContext().fillText(
                formatScoreString(player),
                getScoreMargin().x, getScoreMargin().y);
    }
}
