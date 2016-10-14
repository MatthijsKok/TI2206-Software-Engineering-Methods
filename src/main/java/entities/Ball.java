package entities;

import com.sun.javafx.geom.Vec2d;
import entities.powerups.PickupFactory;
import game.Game;
import geometry.Rectangle;
import level.Level;
import geometry.Circle;
import graphics.Sprite;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class that represents the bouncing balls in our game.
 */
public class Ball extends AbstractEntity {

    /**
     * Enum that represents the color of the ball.
     */
    enum Color { BLUE, GREEN, ORANGE, PURPLE, RED, YELLOW }

    /**
     * HashMap that contains the ball's sprites by color.
     */
    private static final Map<Color, Sprite> BALL_SPRITES = new ConcurrentHashMap<>();

    static {
        BALL_SPRITES.put(Color.BLUE,   new Sprite("balls/blue_ball.png"));
        BALL_SPRITES.put(Color.GREEN,  new Sprite("balls/green_ball.png"));
        BALL_SPRITES.put(Color.ORANGE, new Sprite("balls/orange_ball.png"));
        BALL_SPRITES.put(Color.PURPLE, new Sprite("balls/purple_ball.png"));
        BALL_SPRITES.put(Color.RED,    new Sprite("balls/red_ball.png"));
        BALL_SPRITES.put(Color.YELLOW, new Sprite("balls/yellow_ball.png"));

        BALL_SPRITES.values().forEach(Sprite::setOffsetToCenter);
    }

    /**
     * Collision shape for the ball class.
     */
    private static final Circle BALL_CIRCLE = new Circle(0);

    /**
     * Bounce speed for the different ball sizes.
     */
    private static final double[] BOUNCE_SPEEDS = { 225, 300, 375, 450, 500 };

    /**
     * Radius of a ball with size 0.
     */
    private static final double BASE_SIZE = 8;

    /**
     * Exponential growth factor.
     */
    private static final double GROW_FACTOR = 2;

    /**
     * Horizontal speed of a ball. In pixels per second.
     */
    private static final double HORIZONTAL_SPEED = 100; // px/s

    /**
     * Gravity applied to a ball. In pixels per second squared.
     */
    private static final double GRAVITY = 300; // px/s^2

    /**
     * Size of a ball. from 0 (16 x 16px) to 4 (256 x 256px).
     */
    private int size;

    /**
     * Color of a ball.
     */
    private Color color;

    /**
     * Radius of a ball.
     */
    private double radius;

    /**
     * Creates a new Ball at position position, with size size and a random color.
     * @param position ball position
     * @param size ball size
     */
    Ball(final Vec2d position, final int size) {
        this(position, size, randomColor());
    }

    /**
     * Creates a new Ball at position position, with size size and color color.
     * @param position ball position
     * @param size ball size
     * @param color ball color
     */
    Ball(final Vec2d position, final int size, final Color color) {
        this(position, size, color, new Vec2d(HORIZONTAL_SPEED, 0));
    }

    /**
     * Constructor for the bouncing balls in our game.
     * @param position Vec2d of the starting position of the ball.
     * @param color Enum representing the color of the ball.
     * @param size Integer ranging 0-4 representing the ball size.
     * @param speed Vec2d initial speed of the bal.
     */
     private Ball(final Vec2d position, final int size, final Color color, final Vec2d speed) {
        super(position);

        // Ball collision box
        setShape(new Circle(BALL_CIRCLE));

        setColor(color);
        setSize(size);
        setSpeed(speed.x, speed.y);
    }

    /**
     * Sets the ball size. Method only used internally.
     * @param size Integer from 0 (tiny ball) to 4 (huge ball).
     */
    private void setSize(int size) {
        this.size = Math.max(0, size);
        radius = BASE_SIZE * Math.pow(GROW_FACTOR, this.size);
        ((Circle) getShape()).setRadius(radius);
    }

    /**
     * Gets the ball size.
     * @return Integer from 0 (tiny ball) to 4 (huge ball).
     */
    public int getSize() {
        return size;
    }

    /**
     * Gets the ball's radius.
     * @return The radius of the ball.
     */
    double getRadius() {
        return radius;
    }

    /**
     * Sets the ball color. Method only used internally.
     * @param color One color from Ball.Color.
     */
    private void setColor(Color color) {
        this.color = color;
        setSprite(BALL_SPRITES.get(color));
    }

    /**
     * Gets the ball color.
     * @return Enum with options BLUE, GREEN, ORANGE, PURPLE, RED and YELLOW.
     */
    Color getColor() {
        return color;
    }

    /**
     * Removes this ball from the level and adds two smaller balls on the same
     * position, moving in different directions. If the ball is already at it's
     * smallest, no new balls will be added.
     */
    private void split() {
        Level level = Game.getInstance().getState().getCurrentLevel();

        if (size > 0) {
            level.addEntity(new Ball(getPosition(), getSize() - 1, getColor(),
                    new Vec2d(getXSpeed(), -BOUNCE_SPEEDS[size - 1])));
            level.addEntity(new Ball(getPosition(), getSize() - 1, getColor(),
                    new Vec2d(-getXSpeed(), -BOUNCE_SPEEDS[size - 1])));
        }

        level.removeEntity(this);

        // Randomly spawn a powerup
        PickupFactory.spawn(getPosition());
    }

    /**
     * Set the vertical speed to up at the speed for that balls bounce height.
     */
    private void bounce() {
        getSpeed().y = -BOUNCE_SPEEDS[size];
    }

    /**
     * Update the speed and position of the ball.
     * @param dt Time difference from previous update in seconds.
     */
    public void update(double dt) {
        getSpeed().y += GRAVITY * dt;
    }

    /**
     * Entry point for all collisions.
     * This method should only change the behaviour of the Ball, not the AbstractEntity it is
     * colliding with. The colliding AbstractEntity should handle that itself in it's own
     * "collideWith" method.
     * @param entity the AbstractEntity this Ball collides with.
     */
    public void collideWith(AbstractEntity entity) {
        if (entity instanceof FloorBlock) {
            collideWith((FloorBlock) entity);
        }

        if (entity instanceof WallBlock) {
            collideWith((WallBlock) entity);
        }

        if (entity instanceof Harpoon) {
            collideWithHarpoon();
        }
    }

    /**
     * The behaviour of the Ball when it collides with a FloorBlock AbstractEntity.
     * @param floor The FloorBlock AbstractEntity this Ball collides with.
     */
    private void collideWith(FloorBlock floor) {
        getPosition().y = Math.min(floor.getY() - radius, getY());
        bounce();
    }

    /**
     * The behaviour of the Ball when it collides with a WallBlock AbstractEntity.
     * @param wall The WallBlock AbstractEntity this Ball collides with.
     */
    private void collideWith(WallBlock wall) {

        Rectangle wallShape = (Rectangle) wall.getShape();

        if (getX() + radius > wallShape.getLeft()
                && getX() + radius < wallShape.getRight()) {
            // Hit the wall from the right
            getPosition().x = wallShape.getLeft() - radius;
            if (getXSpeed() > 0) {
                getSpeed().x = -getXSpeed();
            }
        } else if (getX() - radius > wallShape.getLeft()
                && getX() - radius < wallShape.getRight()) {
            // Hit the wall from the left
            getPosition().x = wallShape.getRight() + radius;
            if (getXSpeed() < 0) {
                getSpeed().x = -getXSpeed();
            }
        }
    }

    /**
     * The behaviour of the Ball when it collides with a Harpoon AbstractEntity.
     */
    private void collideWithHarpoon() {
        split();
    }

    /**
     * Draws the Ball sprite on the AbstractEntity's position with scale depending
     * on the ball size.
     */
    public void draw() {
        getSprite().draw(getPosition(), getRadius() * 2 / getSprite().getWidth());
    }

    /**
     * Picks a random Color from the Ball.Color options and returns it.
     * @return a random Ball.Color.
     */
    static Color randomColor() {
        Color[] values = Color.values();
        return values[(int) Math.floor(Math.random() * values.length)];
    }

    /**
     * Gets a color from the Ball.Color enum by name.
     * @param color Name of the color.
     * @return The color from Ball.Color.
     */
    static Color getColor(String color) {
        return Ball.Color.valueOf(color.toUpperCase());
    }

}
