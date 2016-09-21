package entities;

import com.sun.javafx.geom.Vec2d;
import game.Game;
import geometry.Rectangle;
import util.Sprite;

/**
 * The Player class represents a player.
 */
public class Player extends Entity {

    /**
     * The frame of the Sprite that should be displayed when the player is not moving.
     */
    private static final int STATIONARY_FRAME = 3;

    /**
     * The sprite of the player.
     */
    private static final Sprite SPRITE = new Sprite("mario.png", 8, new Vec2d(11, 35));

    /**
     * The run speed of the player in px/s.
     */
    private static final double RUN_SPEED = 256; // px/s

    /**
     * The gravity applied to the player.
     */
    private static final double GRAVITY = 300; // px/s^2

    /**
     * Input characters.
     */
    private String leftKey, rightKey, upKey, shootKey;

    /**
     * The direction the player is facing, where 1 is right and -1 is left.
     */
    private int direction = 1;

    /**
     * Boolean indicating if the player is alive.
     */
    private boolean alive = true;

    /**
     * Rope of the player.
     */
    private Rope rope;

    /**
     * Instantiate a new player at position (0, 0).
     */
    public Player() {
        this(0, 0);
    }

    /**
     * Creates a player on the (x,y) position.
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Player(double x, double y) {
        super(x, y);

        // Set player sprite
        sprite = Player.SPRITE;

        // Define player keys
        leftKey = "LEFT";
        rightKey = "RIGHT";
        upKey = "UP";
        shootKey = "SPACE";

        // Create rope for the player and add it to the level
        rope = new Rope();
        Game.getInstance().getCurrentLevel().addEntity(rope);

        shape = new Rectangle(sprite.getWidth(), sprite.getHeight());
        updatePosition(0);
    }

    private void die() {
        alive = false;
    }

    /**
     * Returns true if the player is alive, false otherwise.
     * @return true if the player is alive, false otherwise
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * Returns the x position that indicates a left bound of the player.
     * @return the x position that indicates a left bound of the player
     */
    private double getLeft() {
        return position.x - sprite.getOffsetX();
    }

    /**
     * Returns the y position that indicates a right bound of the player.
     * @return the y position that indicates a right bound of the player
     */
    private double getRight() {
        return getLeft() + sprite.getWidth();
    }

    /**
     * Sets the position of the player so the left bound is at the specified position.
     * @param left the x position indicating where the left bound of the player should be
     */
    private void setLeft(double left) {
        position.x = left + sprite.getOffsetX();
    }

    /**
     * Sets the position of the player so the right bound is at the specified position.
     * @param right the x position indicating where the right bound of the player should be
     */
    private void setRight(double right) {
        position.x = right - sprite.getWidth() + sprite.getOffsetX();
    }

    /**
     * Update the players position and collision shape.
     *
     * @param dt the time
     */
    private void updatePosition(double dt) {
        position.x += speed.x * dt;
        position.y += speed.y * dt;
        shape.setPosition(position.x - sprite.getOffsetX(), position.y - sprite.getOffsetY());
    }

    /**
     * Updates the Player object.
     * @param dt The time since the last time the update method was called.
     */
    public void update(double dt) {
        // Update the player sprite
        if (isMoving()) {
            sprite.update(dt);
        }

        updateSpeed(dt);
        updatePosition(dt);
        updateRope();
    }

    /**
     * Updates the rope according to the keys pressed.
     */
    private void updateRope() {
        // Shoot (use space or up key)
        if (keyboard.keyPressed(shootKey)) {
            rope.shoot(position);
        }
        if (keyboard.keyPressed(upKey)) {
            rope.shoot(position);
        }
    }

    /**
     * Changes the position of the player according to the keys pressed.
     * @param dt Time since the last time the method was called
     */
    private void updateSpeed(double dt) {
        // Walk
        speed.x = 0;
        if (keyboard.keyPressed(leftKey)) {
            speed.x -= RUN_SPEED;
        }
        if (keyboard.keyPressed(rightKey)) {
            speed.x += RUN_SPEED;
        }
        if (speed.x < 0) {
            direction = -1;
        }
        if (speed.x > 0) {
            direction = 1;
        }

        // Apply gravity
        this.speed.y += GRAVITY * dt;
    }

    /**
     * Handles colision of the player with other entities.
     * @param entity the entity the player collides with
     */
    public void collideWith(Entity entity) {
        if (entity instanceof Ball) {
            collideWith((Ball) entity);
        }

        if (entity instanceof Block) {
            collideWith((Block) entity);
        }

        if (entity instanceof Wall) {
            collideWith((Wall) entity);
        }
    }

    /**
     * When a player collides with a ball, the player dies.
     *
     * @param ball The ball the player collides with
     */
    private void collideWith(Ball ball) {
        die();
    }

    /**
     * If the player collides with a block this method is called.
     * @param block the block the player collides with
     */
    private void collideWith(Block block) {
        position.y = Math.min(position.y, block.getY());
        speed.y = Math.min(speed.y, 0);
        updatePosition(0);
    }

    /**
     * When the player collides with a wall this method is called.
     * @param wall the wall the player collides with
     */
    private void collideWith(Wall wall) {
        if (getRight() > wall.getLeft() && getRight() < wall.getRight()) {
            // Hit the wall from the right
            setRight(wall.getLeft());
            speed.x = Math.min(speed.x, 0);
        } else if (getLeft() > wall.getLeft() && getLeft() < wall.getRight()) {
            // Hit the wall from the left
            setLeft(wall.getRight());
            speed.x = Math.max(0, speed.x);
        }
    }

    /**
     * Draws the player to the screen.
     */
    public void draw() {
        sprite.draw(position, direction, 1);
    }

    /**
     * Checks if any of the movement keys are currently pressed
     * to determine if the player is moving.
     * @return A boolean that is true if the player is moving, and false otherwise;
     */
    public boolean isMoving() {
        if (keyboard.keyPressed(leftKey) || keyboard.keyPressed(rightKey)) {
            return true;
        } else {
            sprite.setCurrentFrame(STATIONARY_FRAME);
            return false;
        }
    }
}
