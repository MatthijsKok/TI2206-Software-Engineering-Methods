package entities.balls;

import com.sun.javafx.geom.Vec2d;
import graphics.Sprite;
import level.Level;
import util.sound.MultiSoundEffect;

import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class that represents colored balls.
 * This class gives balls colors and if a bal dies it's smaller balls get the same color.
 */
public class ColoredBall extends AbstractBall {

    /**
     * HashMap that contains the ball's sprites by color.
     */
    private static final Map<Color, Sprite> SPRITES = new ConcurrentHashMap<>();

    static {
        SPRITES.put(Color.BLUE,   new Sprite("images/balls/blue_ball.png"));
        SPRITES.put(Color.GREEN,  new Sprite("images/balls/green_ball.png"));
        SPRITES.put(Color.ORANGE, new Sprite("images/balls/orange_ball.png"));
        SPRITES.put(Color.PURPLE, new Sprite("images/balls/purple_ball.png"));
        SPRITES.put(Color.RED,    new Sprite("images/balls/red_ball.png"));
        SPRITES.put(Color.YELLOW, new Sprite("images/balls/yellow_ball.png"));

        SPRITES.values().forEach(Sprite::setOffsetToCenter);
    }

    /**
     * Color of a ball.
     */
    private final Color color;

    /**
     * Creates a new colored ball.
     * @param position Vec2d spawn position of the ball.
     * @param size     int size of the ball (0 - 4).
     */
    public ColoredBall(final Vec2d position, final int size) {
        this(position, size, randomColor());
    }

    /**
     * Creates a new colored ball.
     * @param position Vec2d spawn position of the ball.
     * @param size     int size of the ball (0 - 4).
     * @param color    Color color of the ball.
     */
    public ColoredBall(final Vec2d position, final int size, final Color color) {
        super(position, size);
        this.color = color;
        setSprite();
    }

    /**
     * Creates a new colored ball.
     * @param position Vec2d spawn position of the ball.
     * @param size     int size of the ball (0 - 4).
     * @param color    Color color of the ball.
     * @param speed    Vec2d speed of the ball.
     */
    private ColoredBall(final Vec2d position, final int size, final Color color, final Vec2d speed) {
        super(position, size, speed);
        this.color = color;
        setSprite();
    }

    /**
     * Creates a random color.
     * @return Enum with options BLUE, GREEN, ORANGE, PURPLE, RED and YELLOW.
     */
    private static Color randomColor() {
        Color[] values = Color.values();
        return values[(int) Math.floor(Math.random() * values.length)];
    }

    /**
     * Gets a color from the AbstractBall.Color enum by name.
     * @param color Name of the color.
     * @return      The color from AbstractBall.Color.
     */
    public static Color getColor(String color) {
        return Color.valueOf(color.toUpperCase(Locale.ENGLISH));
    }

    /**
     * Gets the ball color.
     * @return Enum with options BLUE, GREEN, ORANGE, PURPLE, RED and YELLOW.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the sprite of the ball.
     */
    private void setSprite() {
        setSprite(SPRITES.get(color));
    }

    @Override
    /* default */ final void die() {
        Level level = getLevel();
        if (getSize() > 0) {
            level.addEntity(new ColoredBall(getPosition(), getSize() - 1, getColor(),
                    new Vec2d(getXSpeed(), getYSpeed())));
            level.addEntity(new ColoredBall(getPosition(), getSize() - 1, getColor(),
                    new Vec2d(-getXSpeed(), getYSpeed())));
        }
        MultiSoundEffect.BALL_POP.playRandom();
        super.die();
    }

    /**
     * Enum that represents the color of the ball.
     */
    public enum Color { BLUE, GREEN, ORANGE, PURPLE, RED, YELLOW }
}
