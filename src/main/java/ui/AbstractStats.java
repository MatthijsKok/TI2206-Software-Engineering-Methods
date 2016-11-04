package ui;

import com.sun.javafx.geom.Vec2d;
import game.player.Player;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.net.URISyntaxException;

/**
 * Class containing common methods for the player stats.
 */
public abstract class AbstractStats extends AbstractHUDDecorator {

    /**
     * Font size used for the score.
     */
    private static final int SCORE_FONT_SIZE = 30;

    /**
     * Font size used for the score.
     */
    private static final int LIVES_FONT_SIZE = 25;

    /**
     * Font size used for the score.
     */
    private static final Font LIVES_FONT = createFont(LIVES_FONT_SIZE);

    /**
     * Font size used for the score.
     */
    private static final Font SCORE_FONT = createFont(SCORE_FONT_SIZE);

    /**
     * The offset of the start place of the lives.
     */
    private static final Vec2d MUGSHOT_MARGIN = new Vec2d(16, 560);

    /**
     * The offset of the start place of the lives.
     */
    private static final Vec2d LIVES_MARGIN = new Vec2d(MUGSHOT_MARGIN.x + 33, MUGSHOT_MARGIN.y + 35);

    /**
     * The offset of the start place of the hearts.
     */
    private static final Vec2d SCORE_MARGIN = new Vec2d(100, 590);

    /**
     * The size of the mugshot in px, eg: 40 makes the mugshot 40x40 px.
     */
    private static final int MUGSHOT_SIZE = 40;

    /**
     * Drop shadow applied to the text of the lives and score.
     */
    private static final DropShadow DROP_SHADOW = new DropShadow(1.0, 3.0, 3.0, Color.color(0.0, 0.0, 0.0));

    /**
     * Constructor for HUDDecorator.
     *
     * @param tempUI The UI element to decorate.
     */
    public AbstractStats(UIElement tempUI) {
        super(tempUI);
    }

    @Override
    public void draw() {
        super.draw();

        //Set text color to white
        getGraphicsContext().setFill(Color.WHITE);

        drawMugshots();

        //Set text color to white
        getGraphicsContext().setEffect(DROP_SHADOW);
        drawLifeCounters();
        drawScore();
        getGraphicsContext().setEffect(null);
    }

    /**
     * Gets the Super Mario font file resource and creates a Font object with the desired size.
     * @param fontSize The desired size of the font.
     * @return A Font object with the correct size.
     */
    private static Font createFont(int fontSize) {
        String fontURI = "";
        try {
            fontURI = EmptyHUD.class.getClassLoader().getResource("fonts/newSuperMario.ttf").toURI().toString();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return Font.loadFont(fontURI, fontSize);
    }

    /**
     * Formats the string that shows the amount of lives left.
     * @param player The player to format the string for.
     * @return A correctly formatted string.
     */
    static String formatLivesString(Player player) {
        return String.format("x%02d", player.getLives());
    }

    /**
     * Formats the string that shows the amount of lives left.
     * @param player The player to format the string for.
     * @return A correctly formatted string.
     */
    static String formatScoreString(Player player) {
        return String.format("%06d", player.getScore());
    }

    /**
     * Draws the score to the screen.
     */
    /* default */ abstract void drawScore();

    /**
     * Draws the life counters to the screen.
     */
    /* default */ abstract void drawLifeCounters();

    /**
     * Draws the mugshots to the screen.
     */
    /* default */ abstract void drawMugshots();

    /**
     * @return The mugshot margin.
     */
    public static Vec2d getMugshotMargin() {
        return MUGSHOT_MARGIN;
    }

    /**
     * @return The lives margin.
     */
    public static Vec2d getLivesMargin() {
        return LIVES_MARGIN;
    }

    /**
     * @return The score margin.
     */
    public static Vec2d getScoreMargin() {
        return SCORE_MARGIN;
    }

    /**
     * @return The lives margin.
     */
    public static int getMugshotSize() {
        return MUGSHOT_SIZE;
    }

    /**
     * @return The lives font.
     */
    public static Font getLivesFont() {
        return LIVES_FONT;
    }

    /**
     * @return The the score font.
     */
    public static Font getScoreFont() {
        return SCORE_FONT;
    }
}
