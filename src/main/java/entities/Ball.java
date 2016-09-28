package entities;

import com.sun.javafx.geom.Vec2d;
import game.Game;
import Level.Level;
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
     * Enum that represents the colour of the ball.
     */
    private enum Colour { BLUE, GREEN, ORANGE, PURPLE, RED, YELLOW }

    /**
     * HashMap that contains the ball's sprites by colour.
     */
    private static final Map<Colour, Sprite> BALL_SPRITES = new ConcurrentHashMap<>();

    static {
        BALL_SPRITES.put(Colour.BLUE,   new Sprite("balls/blue_ball.png"));
        BALL_SPRITES.put(Colour.GREEN,  new Sprite("balls/green_ball.png"));
        BALL_SPRITES.put(Colour.ORANGE, new Sprite("balls/orange_ball.png"));
        BALL_SPRITES.put(Colour.PURPLE, new Sprite("balls/purple_ball.png"));
        BALL_SPRITES.put(Colour.RED,    new Sprite("balls/red_ball.png"));
        BALL_SPRITES.put(Colour.YELLOW, new Sprite("balls/yellow_ball.png"));

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
     * Colour of a ball.
     */
    private Colour colour;

    /**
     * Radius of a ball.
     */
    private double radius;

    /**
     * Shape of a ball.
     */
    private Circle shape;

    /**
     * Creates a new Ball at position position, with size size and a random colour.
     * @param position ball position
     * @param size ball size
     */
    public Ball(final Vec2d position, final int size) {
        this(position, size, randomColour());
    }

    /**
     * Creates a new Ball at position position, with size size and colour colour.
     * @param position ball position
     * @param size ball size
     * @param colour ball colour
     */
    public Ball(final Vec2d position, final int size, final Colour colour) {
        this(position, size, colour, new Vec2d(HORIZONTAL_SPEED, 0));
    }

    /**
     * Constructor for the bouncing balls in our game.
     * @param position Vec2d of the starting position of the ball.
     * @param colour Enum representing the colour of the ball.
     * @param size Integer ranging 0-4 representing the ball size.
     * @param speed Vec2d initial speed of the bal.
     */
    public Ball(final Vec2d position, final int size, final Ball.Colour colour, final Vec2d speed) {
        super(position);

        // Ball collision box
        shape = new Circle(0);

        setColour(colour);
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
     * Sets the ball colour. Method only used internally.
     * @param colour One colour from Ball.Colour.
     */
    private void setColour(Colour colour) {
        this.colour = colour;
        sprite = BALL_SPRITES.get(colour);
    }

    /**
     * Gets the ball colour.
     * @return Enum with options BLUE, GREEN, ORANGE, PURPLE, RED and YELLOW.
     */
    public Colour getColour() {
        return colour;
    }

    /**
     * Removes this ball from the Level and adds two smaller balls on the same
     * position, moving in different directions. If the ball is already at it's
     * smallest, no new balls will be added.
     */
    private void split() {
        Level level = Game.getInstance().getCurrentLevel();

        if (size > 0) {
            level.addEntity(new Ball(position, getSize() - 1, getColour(),
                    new Vec2d(speed.x, -BOUNCE_SPEEDS[size - 1])));
            level.addEntity(new Ball(position, getSize() - 1, getColour(),
                    new Vec2d(-speed.x, -BOUNCE_SPEEDS[size - 1])));
        }

        level.removeEntity(this);
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
     * Picks a random Colour from the Ball.Colour options and returns it.
     * @return a random Ball.Colour.
     */
    private static Colour randomColour() {
        Colour[] values = Colour.values();
        return values[(int) Math.floor(Math.random() * values.length)];
    }
}
