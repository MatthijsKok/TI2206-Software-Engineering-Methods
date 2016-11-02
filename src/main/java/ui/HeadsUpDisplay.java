package ui;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import level.LevelTimer;

import java.net.URISyntaxException;

/**
 * HeadsUpDisplay is a acronym for Heads Up Display. A common name
 * for the overlay of a game.
 */
public class HeadsUpDisplay extends AbstractUIElement {

    /**
     * Height of the time bar.
     */
    private static final int HEIGHT = 24;

    /**
     * Padding around the time bar.
     */
    private static final int MARGIN = 8;

    /**
     * Padding around the time bar.
     */
    private static final int PADDING = 2;

    /**
     * The level to draw the HUD for.
     */
    private final LevelTimer timer;

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
    public static final Font LIVES_FONT = createFont(LIVES_FONT_SIZE);

    /**
     * Font size used for the score.
     */
    public static final Font SCORE_FONT = createFont(SCORE_FONT_SIZE);

    /**
     * The mugshot next to the lives counter.
     */
    public static final Image MARIO_MUGSHOT = new Image("images/mugshots/mario_mugshot.png");

    /**
     * The mugshot next to the lives counter.
     */
    public static final Image YOSHI_MUGSHOT = new Image("images/mugshots/yoshi_mugshot.png");

    /**
     * The offset of the start place of the lives.
     */
    public static final Vec2d MUGSHOT_MARGIN = new Vec2d(16, 560);

    /**
     * The offset of the start place of the lives.
     */
    public static final Vec2d LIVES_MARGIN = new Vec2d(MUGSHOT_MARGIN.x + 33, MUGSHOT_MARGIN.y + 35);

    /**
     * The offset of the start place of the hearts.
     */
    public static final Vec2d SCORE_MARGIN = new Vec2d(100, 590);

    /**
     * The size of the mugshot in px, eg: 40 makes the mugshot 40x40 px.
     */
    public static final int MUGSHOT_SIZE = 40;

    /**
     * Drop shadow applied to the text of the lives and score.
     */
    public static final DropShadow DROP_SHADOW = new DropShadow(1.0, 3.0, 3.0, Color.color(0.0, 0.0, 0.0));

    /**
     * Creates a new HUD for.
     * @param timer LevelTimer - The timer for the current level to draw a HUD for.
     */
    public HeadsUpDisplay(LevelTimer timer) {
        this.timer = timer;
    }

    private double getTimeLeft() {
        return timer.getTimeLeft() / timer.getDuration();
    }

    @Override
    public void draw() {
        // Outer time bar
        getGraphicsContext().setFill(Color.BLACK);
        getGraphicsContext().fillRect(
                0, getCanvas().getHeight() - HEIGHT - MARGIN,
                getCanvas().getWidth(), HEIGHT);

        // Inner time bar
        getGraphicsContext().setFill(Color.RED);
        getGraphicsContext().fillRect(0, getCanvas().getHeight() - HEIGHT - MARGIN + PADDING,
                (getCanvas().getWidth() - 2 * (PADDING + MARGIN)) * getTimeLeft(), HEIGHT);
    }

    /**
     * Gets the Super Mario font file resource and creates a Font object with the desired size.
     * @param fontSize The desired size of the font.
     * @return A Font object with the correct size.
     */
    private static Font createFont(int fontSize) {
        String fontURI = "";
        try {
            fontURI = HeadsUpDisplay.class.getClassLoader().getResource("fonts/newSuperMario.ttf").toURI().toString();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return Font.loadFont(fontURI, fontSize);
    }
}
