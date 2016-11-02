package ui;

import game.player.Player;
import javafx.scene.paint.Color;
import level.LevelTimer;

/**
 * Draws a HeadsUpDisplay for a game with a single player.
 */
public class MultiPlayerHUD extends HeadsUpDisplay {

    /**
     * The spacing between the two lives and scores of the player.
     */
    private static final int SPACING = 800;

    /**
     * The players this HUD draws the lives for.
     */
    private final Player player1, player2;

    /**
     * Creates a new MultiPlayerHUD.
     * @param timer The timer to draw the HUD for.
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

        //Yoshi
        getGraphicsContext().drawImage(YOSHI_MUGSHOT, MUGSHOT_MARGIN.x + SPACING, MUGSHOT_MARGIN.y, MUGSHOT_SIZE, MUGSHOT_SIZE);

        //Draw lives strings
        //Set drop shadow
        getGraphicsContext().setEffect(DROP_SHADOW);

        //Set font to smaller font
        getGraphicsContext().setFont(LIVES_FONT);

        //Mario
        String livesString1 = "x" + String.format("%02d", player1.getLives());
        getGraphicsContext().fillText(
                livesString1,
                LIVES_MARGIN.x, LIVES_MARGIN.y);

        //Yoshi
        String livesString2 = "x" + String.format("%02d", player2.getLives());
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
        String score1 = String.format("%06d", player1.getScore());
        String score2 = String.format("%06d", player2.getScore());

        //Draw score
        //Mario
        getGraphicsContext().fillText(
                score1,
                SCORE_MARGIN.x, SCORE_MARGIN.y);

        //Yoshi
        getGraphicsContext().fillText(
                score2,
                SCORE_MARGIN.x + SPACING, SCORE_MARGIN.y);
    }
}
