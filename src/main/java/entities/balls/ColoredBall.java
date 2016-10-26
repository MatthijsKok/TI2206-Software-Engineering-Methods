package entities.balls;

import com.sun.javafx.geom.Vec2d;
import entities.Plant;
import graphics.Sprite;
import level.Level;
import util.sound.MultiSoundEffect;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class that represents colored balls.
 */
public class ColoredBall extends AbstractBall {

    /**
     * after 7 deaths the gate should disappear.
     */
    private int kilBigBall = 7;

    /**
     * HashMap that contains the ball's sprites by color.
     */
    private static final Map<Color, Sprite> SPRITES = new ConcurrentHashMap<>();

    static {
        SPRITES.put(Color.BLUE,   new Sprite("balls/blue_ball.png"));
        SPRITES.put(Color.GREEN,  new Sprite("balls/green_ball.png"));
        SPRITES.put(Color.ORANGE, new Sprite("balls/orange_ball.png"));
        SPRITES.put(Color.PURPLE, new Sprite("balls/purple_ball.png"));
        SPRITES.put(Color.RED,    new Sprite("balls/red_ball.png"));
        SPRITES.put(Color.YELLOW, new Sprite("balls/yellow_ball.png"));

        SPRITES.values().forEach(Sprite::setOffsetToCenter);
    }

    /**
     * Color of a ball.
     */
    private final Color color;

    /**
     * Creates a new colored ball.
     * @param position spawn position of the ball
     * @param size size of the ball (0 - 4)
     */
    public ColoredBall(final Vec2d position, final int size) {
        this(position, size, randomColor());
    }

    /**
     * Creates a new colored ball.
     * @param position spawn position of the ball.
     * @param size size of the ball (0 - 4)
     * @param color color of the ball.
     */
    public ColoredBall(final Vec2d position, final int size, final Color color) {
        super(position, size);
        this.color = color;
        setSprite();
    }

    private ColoredBall(final Vec2d position, final int size, final Color color, final Vec2d speed) {
        super(position, size, speed);
        this.color = color;
        setSprite();
    }

    private static Color randomColor() {
        Color[] values = Color.values();
        return values[(int) Math.floor(Math.random() * values.length)];
    }

    /**
     * Gets a color from the AbstractBall.Color enum by name.
     *
     * @param color Name of the color.
     * @return The color from AbstractBall.Color.
     */
    public static Color getColor(String color) {
        return Color.valueOf(color.toUpperCase());
    }

    /**
     * Gets the ball color.
     *
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
    void die() {
        Level level = getLevel();
        level.setBallDeathCounter();
        // If the count is 8 then the Plant should be removed.
        if (level.getBallDeathCounter() == kilBigBall) {
            getLevel().removeEntity(level.getEntities().stream()
                    .filter(e -> e instanceof Plant).findAny().orElse(null));
        }
        // Split the big ball in two.
        if (getSize() > 0) {
            level.addEntity(new ColoredBall(getPosition(), getSize() - 1, getColor(),
                    new Vec2d(getXSpeed(), -getBounceSpeed())));
            level.addEntity(new ColoredBall(getPosition(), getSize() - 1, getColor(),
                    new Vec2d(-getXSpeed(), -getBounceSpeed())));
        }
        MultiSoundEffect.BALL_POP.playRandom();
        super.die();
    }

    /**
     * Enum that represents the color of the ball.
     */
    public enum Color { BLUE, GREEN, ORANGE, PURPLE, RED, YELLOW }

}
