package entities;

import com.sun.javafx.geom.Vec2d;
import game.Game;
import game.Level;
import geometry.Circle;
import org.lwjgl.Sys;
import util.Sprite;

/**
 * Class that represents the bouncing balls in our game.
 */
public class Ball extends Entity {

    // Bounce speed for the different ball sizes.
    private static final double[] BOUNCE_SPEEDS = { 225, 300, 375, 450, 500 };

    private static final double HORIZONTAL_SPEED = 100; // px/s
    private static final double GRAVITY = 300; // px/s^2

    public enum Colour { BLUE, GREEN, ORANGE, PURPLE, RED, YELLOW } //Enum that represents the colour of the ball

    private int size; // Integer that represents the ball size from 0 (tiny) to 4 (huge).
    private Colour colour; // Colour of the ball.
    private double radius; // The radius of the ball.

    public Ball(Vec2d position, int size) {
        this(position, size, randomColour());
    }

    public Ball(Vec2d position, int size, Colour colour) {
        this(position, size, colour, false);
    }

    /**
     * Constructor for the bouncing balls in our game.
     * @param position Vec2D of the starting position of the ball.
     * @param colour Enum representing the colour of the ball.
     * @param size Integer ranging 0-4 representing the ball size.
     * @param left Boolean that is set to 1 if the ball initially moves left, 0 if right.
     */
    public Ball(Vec2d position, int size, Ball.Colour colour, boolean left) {
        super(position);

        // Ball collision box
        shape = new Circle(0);

        // Ball colour
        setColour(colour);
        // Ball size
        setSize(size);
        // Ball direction
        if (left) {
            setSpeed(-HORIZONTAL_SPEED, 0);
        } else {
            setSpeed(HORIZONTAL_SPEED, 0);
        }
        updatePosition(0);
    }

    /**
     * Sets the ball size. Method only used internally.
     * @param size Integer from 0 (tiny ball) to 4 (huge ball).
     */
    private void setSize(int size) {
        this.size = Math.max(0, size);
        radius = 8*Math.pow(2, this.size);
        ((Circle)shape).setRadius(radius);
    }

    /**
     * Gets the ball size.
     * @return Integer from 0 (tiny ball) to 4 (huge ball).
     */
    public int getSize() { return size; }

    /**
     * Sets the ball colour. Method only used internally.
     * @param colour Enum with options BLUE, GREEN, ORANGE, PURPLE, RED and YELLOW.
     */
    private void setColour(Colour colour) {
        this.colour = colour;

        String uri;

        switch (colour) {
            case BLUE:
                uri = "balls/blue_ball.png";
                break;
            case GREEN:
                uri = "balls/green_ball.png";
                break;
            case ORANGE:
                uri = "balls/orange_ball.png";
                break;
            case PURPLE:
                uri = "balls/purple_ball.png";
                break;
            case RED:
                uri = "balls/red_ball.png";
                break;
            case YELLOW:
                uri = "balls/yellow_ball.png";
                break;
            default:
                uri = "balls/blue_ball.png";
                break;
        }

        sprite = new Sprite(uri);
        sprite.setOffset(51, 51);
        //sprite.setOffsetToCenter();
    }

    /**
     * Gets the ball colour.
     * @return Enum with options BLUE, GREEN, ORANGE, PURPLE, RED and YELLOW.
     */
    public Colour getColour() {
        return colour;
    }

    /**
     * Removes this ball from the Level and adds two smaller balls on the same position, moving in different directions.
     * If the ball is already at it's smallest, no new balls will be added.
     */
    public void split() {
        Level level = Game.getInstance().getCurrentLevel();

        if (size > 0) {
            level.addEntity(new Ball(position, getSize() - 1, getColour(), true));
            level.addEntity(new Ball(position, getSize() - 1, getColour(), false));
        }

        level.removeEntity(this);
    }

    private void updatePosition(double dt) {
        position.x += speed.x*dt;
        position.y += speed.y*dt;

        shape.setPosition(position.x, position.y);
    }

    private void bounce() {
        speed.y = -BOUNCE_SPEEDS[size];
    }

    /**
     * Update the speed and position of the ball.
     * @param dt Time difference from previous update in seconds.
     */
    public void update(double dt) {
        // Apply gravity
        speed.y += GRAVITY*dt;

        if (keyboard.keyPressed("S") && size == 2) {
            split();
        }

        // Move
        updatePosition(dt);
    }

    /**
     * Entry point for all collisions
     * @param entity the entity this ball collides with
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

    private void collideWith(Block block) {
        position.y = Math.min(block.getY() - radius, position.y);
        updatePosition(0);
        bounce();
    }

    private void collideWith(Wall wall) {
        if (position.x + radius > wall.getLeft() && position.x + radius < wall.getRight()) {
            // Hit the wall from the right
            position.x = wall.getLeft() - radius;
            if (speed.x > 0) { speed.x = -speed.x; }
        } else if (position.x - radius > wall.getLeft() && position.x - radius < wall.getRight()) {
            // Hit the wall from the left
            position.x = wall.getRight() + radius;
            if (speed.x < 0) { speed.x = -speed.x; }
        }

        updatePosition(0);
    }

    private void collideWith(Rope rope) {
        split();
    }

    public void draw() {
        sprite.draw(position, radius*2 / sprite.getWidth());
    }

    private static Colour randomColour() {
        Colour[] values = Colour.values();

        return values[(int)Math.floor(Math.random()*values.length)];
    }
}
