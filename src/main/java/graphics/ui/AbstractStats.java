package graphics.ui;

import com.sun.javafx.geom.Vec2d;
import graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.nio.file.Paths;

/**
 * Class containing common methods for the player stats.
 */
abstract class AbstractStats extends AbstractHUDDecorator {

    /**
     * Font size used for the score.
     */
    private static final Font LIVES_FONT = loadMarioFont(25);

    /**
     * Font size used for the score.
     */
    private static final Font SCORE_FONT = loadMarioFont(30);

    /**
     * Drop shadow applied to the text of the lives and score.
     */
    private static final DropShadow DROP_SHADOW =
            new DropShadow(1.0, 3.0, 3.0, Color.color(0.0, 0.0, 0.0));

    /**
     * The GraphicsContext the stats are drawn at.
     */
    private final transient GraphicsContext graphicsContext = getGraphicsContext();

    /**
     * Constructor for HUDDecorator.
     *
     * @param element The UIElement to decorate.
     */
    AbstractStats(final UIElement element) {
        super(element);
    }

    /**
     * Gets the Super Mario font file resource and
     * creates a Font object with the desired size.
     *
     * @param fontSize The desired size of the font.
     * @return A Font object with the correct size.
     */
    private static Font loadMarioFont(final int fontSize) {
        final String path = Paths.get("src/main/resources/fonts/newSuperMario.ttf").toUri().toString();

        return Font.loadFont(path, fontSize);
    }

    /**
     * Draws the mugshots to the screen.
     *
     * @param sprite   Sprite of the mugshot.
     * @param position Position of the mugshot.
     * @param scale    Scale of the mugshot.
     */
    /* default */ final void drawMugshot(final Sprite sprite, final Vec2d position,
                                         final double scale) {
        sprite.draw(position, scale);
    }

    /**
     * Draws the score to the screen.
     *
     * @param score    The score to draw.
     * @param position The position to draw the score at.
     */
    /* default */ final void drawScore(final int score, final Vec2d position) {
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.setEffect(DROP_SHADOW);
        graphicsContext.setFont(SCORE_FONT);

        graphicsContext.fillText(
                String.format("%06d", score),
                position.x, position.y);

        graphicsContext.setEffect(null);
    }

    /**
     * Draws the life counters to the screen.
     *
     * @param lives    The amount of lives to draw.
     * @param position The position to draw the lives.
     */
    /* default */ final void drawLifeCounter(final int lives, final Vec2d position) {
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.setEffect(DROP_SHADOW);
        graphicsContext.setFont(LIVES_FONT);

        graphicsContext.fillText(
                String.format("x%02d", lives),
                position.x, position.y);

        graphicsContext.setEffect(null);
    }
}
