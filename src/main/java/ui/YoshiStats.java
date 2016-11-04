package ui;

import game.player.Player;
import javafx.scene.paint.Color;

/**
 * Class responsible for drawing the lives and score of player 2 (Yoshi) to the screen.
 * Adheres to the decorator pattern.
 */
public class YoshiStats extends HUDDecorator {

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
    public void draw() {
        super.draw();

        //Set text color to white
        getGraphicsContext().setFill(Color.WHITE);

        drawLifeCounters();
        drawScore();
        getGraphicsContext().setEffect(null);
    }

    /**
     * Draws the life counters to the screen.
     */
    private void drawLifeCounters() {
        //Draw lives image

        //Yoshi
        getGraphicsContext().drawImage(YOSHI_MUGSHOT, MUGSHOT_MARGIN.x + SPACING, MUGSHOT_MARGIN.y, MUGSHOT_SIZE, MUGSHOT_SIZE);

        //Draw lives strings
        //Set drop shadow
        getGraphicsContext().setEffect(DROP_SHADOW);

        //Set font to smaller font
        getGraphicsContext().setFont(LIVES_FONT);

        //Yoshi
        String livesString2 = "x" + String.format("%02d", player.getLives());
        getGraphicsContext().fillText(
                livesString2,
                LIVES_MARGIN.x + SPACING, LIVES_MARGIN.y);
    }

    /**
     * Draws the score to the screen.
     */
    public void drawScore() {

        //Set bigger font
        getGraphicsContext().setFont(SCORE_FONT);

        //Add leading zeros to score
        String score2 = String.format("%06d", player.getScore());

        //Draw score
        getGraphicsContext().fillText(
                score2,
                SCORE_MARGIN.x + SPACING, SCORE_MARGIN.y);
    }
}
