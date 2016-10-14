package entities;

import com.sun.javafx.geom.Vec2d;
import entities.powerups.PickupFactory;
import game.Game;
import level.Level;
import geometry.Circle;
import geometry.Shape;
import util.Sprite;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class that represents the bouncing balls in our game.
 */
public class Ball extends Entity {

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
     * Shape of a ball.
     */
    private Circle shape;

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
        shape = new Circle(0);

        setColor(color);
        setSize(size);
        setSpeed(speed);

        updatePosition(0);
    }

    /**
     * Sets the ball size. Method only used internally.
     * @param size Integer from 0 (tiny ball) to 4 (huge ball).
     */
    private void setSize(int size) {
        this.size = Math.max(0, size);
        radius = BASE_SIZE * Math.pow(GROW_FACTOR, this.size);
        shape.setRadius(radius);
    }

    /**
     * Gets the ball size.
     * @return Integer from 0 (tiny ball) to 4 (huge ball).
     */
    public int getSize() {
        return size;
    }

    /**
     * Sets the ball color. Method only used internally.
     * @param color One color from Ball.Color.
     */
    private void setColor(Color color) {
        this.color = color;
        sprite = BALL_SPRITES.get(color);
    }

    /**
     * Gets the ball color.
     * @return Enum with options BLUE, GREEN, ORANGE, PURPLE, RED and YELLOW.
     */
    private Color getColor() {
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
            level.addEntity(new Ball(position, getSize() - 1, getColor(),
                    new Vec2d(speed.x, -BOUNCE_SPEEDS[size - 1])));
            level.addEntity(new Ball(position, getSize() - 1, getColor(),
                    new Vec2d(-speed.x, -BOUNCE_SPEEDS[size - 1])));
        }

        // Remove the bigger ball
        level.removeEntity(this);

        // Randomly spawn a power-up
        PickupFactory.spawn(position);

        // Check if the level is won
        boolean won = true;
        for (Entity entity : level.getEntities()) {
            if (entity instanceof Ball) {
                won = false;
                break;
            }
        }

        if (won) {
            level.win();
        }
    }

    /**
     * Updates the position with the current speed * the Delta Time.
     * Also syncs the position of the Shape with the Entity, for collision purposes.
     * This is usually done after manually editing the position of the Entity.
     * @param dt Time difference from previous update in seconds.
     */
    private void updatePosition(final double dt) {
        position.x += speed.x * dt;
        position.y += speed.y * dt;

        shape.setPosition(position.x, position.y);
    }

    /**
     * Set the vertical speed to up at the speed for that balls bounce height.
     */
    private void bounce() {
        speed.y = -BOUNCE_SPEEDS[size];
    }

    /**
     * Update the speed and position of the ball.
     * @param dt Time difference from previous update in seconds.
     */
    public void update(double dt) {
        // Apply gravity
        speed.y += GRAVITY * dt;

        // Move
        updatePosition(dt);
    }

    /**
     * @return this ball's shape.
     */
    public Shape getShape() {
        return shape;
    }

    /**
     * Entry point for all collisions.
     * This method should only change the behaviour of the Ball, not the Entity it is
     * colliding with. The colliding Entity should handle that itself in it's own
     * "collideWith" method.
     * @param entity the Entity this Ball collides with.
     */
    public void collideWith(Entity entity) {
        if (entity instanceof Block) {
            collideWith((Block) entity);
        }

        if (entity instanceof Wall) {
            collideWith((Wall) entity);
        }

        if (entity instanceof Rope) {
            collideWith((Rope) entity);
        }
    }

    /**
     * The behaviour of the Ball when it collides with a Block Entity.
     * @param block The Block Entity this Ball collides with.
     */
    private void collideWith(Block block) {
        position.y = Math.min(block.getY() - radius, position.y);
        updatePosition(0);
        bounce();
    }

    /**
     * The behaviour of the Ball when it collides with a Wall Entity.
     * @param wall The Wall Entity this Ball collides with.
     */
    private void collideWith(Wall wall) {
        if (position.x + radius > wall.getLeft()
                && position.x + radius < wall.getRight()) {
            // Hit the wall from the right
            position.x = wall.getLeft() - radius;
            if (speed.x > 0) {
                speed.x = -speed.x;
            }
        } else if (position.x - radius > wall.getLeft()
                && position.x - radius < wall.getRight()) {
            // Hit the wall from the left
            position.x = wall.getRight() + radius;
            if (speed.x < 0) {
                speed.x = -speed.x;
            }
        }

        updatePosition(0);
    }

    /**
     * The behaviour of the Ball when it collides with a Rope Entity.
     * @param rope The Rope Entity this Ball collides with.
     */
    private void collideWith(Rope rope) {
        if (rope != null) {
            split();
        }
    }

    /**
     * Draws the Ball sprite on the Entity's position with scale depending
     * on the ball size.
     */
    public void draw() {
        sprite.draw(position, radius * 2 / sprite.getWidth());
    }

    /**
     * Picks a random Color from the Ball.Color options and returns it.
     * @return a random Ball.Color.
     */
    private static Color randomColor() {
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
