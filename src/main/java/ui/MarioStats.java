package ui;

import game.player.Player;
import javafx.scene.paint.Color;

/**
 * Class responsible for drawing the lives and score of player 1 (Mario) to the screen.
 * Adheres to the decorator pattern.
 */
public class MarioStats extends HUDDecorator {

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
        //Draw lives images
        //Mario
        getGraphicsContext().drawImage(MARIO_MUGSHOT, MUGSHOT_MARGIN.x, MUGSHOT_MARGIN.y, MUGSHOT_SIZE, MUGSHOT_SIZE);

        //Draw lives strings
        //Set drop shadow
        getGraphicsContext().setEffect(DROP_SHADOW);

        //Set font to smaller font
        getGraphicsContext().setFont(LIVES_FONT);

        //Mario
        String livesString1 = "x" + String.format("%02d", player.getLives());
        getGraphicsContext().fillText(
                livesString1,
                LIVES_MARGIN.x, LIVES_MARGIN.y);
    }

    /**
     * Draws the score to the screen.
     */
    public void drawScore() {

        //Set bigger font
        getGraphicsContext().setFont(SCORE_FONT);

        //Add leading zeros to score
        String score1 = String.format("%06d", player.getScore());

        //Draw score
        //Mario
        getGraphicsContext().fillText(
                score1,
                SCORE_MARGIN.x, SCORE_MARGIN.y);
    }
}