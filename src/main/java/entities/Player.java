package entities;

import com.sun.javafx.geom.Vec2d;
import game.Game;
import geometry.Rectangle;
import geometry.Shape;
import util.Sprite;

/**
 * The Player class represents a player.
 */
public class Player extends Entity {

    /**
     * The sprite for when a player is standing still.
     */
    private static final Sprite IDLE_SPRITE =
            new Sprite("player/idle.png", 1, new Vec2d(8, 32));

    /**
     * The sprite for when a player is running.
     */
    private static final Sprite RUNNING_SPRITE =
            new Sprite("player/running.png", 8, new Vec2d(11, 35));

    /**
     * The running speed of a player. In pixels per second.
     */
    private static final double RUN_SPEED  = 256; // px/s

    /**
     * The gravity applied to a player. In pixels per second squared.
     */
    private static final double GRAVITY    = 300; // px/s^2

    /**
     * Input characters.
     */
    private String leftKey, rightKey, shootKey;

    /**
     * Indicates whether a player is alive or not.
     */
    private boolean alive = true;

    /**
     * Rope of the player.
     */
    private Rope rope;

    /**
     * The sprites of the player.
     */
    private Sprite idleSprite, runningSprite;

    /**
     * The bounding box of the player.
     */
    private Rectangle shape;

    /**
     * Instantiate a new player at position (x, y).
     * @param x the x position of the player
     * @param y the y position of the player
     */
    public Player(final double x, final double y) {
        super(x, y);

        // Set player sprite
        idleSprite = Player.IDLE_SPRITE.clone();
        runningSprite = Player.RUNNING_SPRITE.clone();

        // Define player keys
        leftKey = "LEFT";
        rightKey = "RIGHT";
        shootKey = "UP";

        // Create rope for the player and add it to the level
        rope = new Rope();
        Game.getInstance().getCurrentLevel().addEntity(rope);

        shape = new Rectangle(
                runningSprite.getWidth(),
                runningSprite.getHeight());
        updatePosition(0);
    }

    /**
     * The player dies. Soo sad...
     */
    private void die() {
        alive = false;
    }

    /**
     * @return whether the player is alive
     */
    public final boolean isAlive() {
        return alive;
    }

    /**
     * @return the left side of the bounding box of the player.
     */
    private double getLeft() {
        return shape.getLeft();
    }

    /**
     * @return the right side of the bounding box of the player.
     */
    private double getRight() {
        return shape.getRight();
    }

    /**
     * Set the left side of the bounding box of the player to equal left.
     * @param left the target for the left side of the bounding box.
     */
    private void setLeft(final double left) {
        position.x += left - shape.getLeft();
        updatePosition();
    }

    /**
     * Set the right side of the bounding box of the player to equal right.
     * @param right the target for the right side of the bounding box.
     */
    private void setRight(final double right) {
        position.x += right - shape.getRight();
        updatePosition();
    }

    /**
     * Updates only the position of the bounding box.
     */
    private void updatePosition() {
        updatePosition(0);
    }

    /**
     * Update the players position and collision shape.
     * @param dt the time
     */
    private void updatePosition(final double dt) {
        position.x += speed.x * dt;
        position.y += speed.y * dt;
        shape.setPosition(
                position.x - runningSprite.getOffsetX(),
                position.y - runningSprite.getOffsetY()
        );
    }

    /**
     * Updates the Player object.
     * @param dt The time since the last time the update method was called
     */
    public final void update(final double dt) {
        // Update the player sprite
        runningSprite.update(dt);

        // Walk
        speed.x = 0;
        if (keyboard.keyPressed(leftKey)) {
            speed.x -= RUN_SPEED;
        }

        if (keyboard.keyPressed(rightKey)) {
            speed.x += RUN_SPEED;
        }

        // Apply gravity
        this.speed.y += GRAVITY * dt;

        // Shoot
        if (keyboard.keyPressed(shootKey)) {
            rope.shoot(position);
        }

        // Move
        updatePosition(dt);
    }

    /**
     * @return the player's shape.
     */
    public final Shape getShape() {
        return shape;
    }

    /**
     * Entry point for collisions.
     * @param entity the entity the player collides with
     */
    public final void collideWith(final Entity entity) {
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
     * @param ball the ball the player collides with
     */
    private void collideWith(final Ball ball) {
        if (ball != null) {
            die();
        }
    }

    /**
     * If a player collides with a ground block, the player should not sink
     * through it.
     * @param block the ground block the player collides with
     */
    private void collideWith(final Block block) {
        position.y = Math.min(position.y, block.getY());
        speed.y = Math.min(speed.y, 0);
        updatePosition();
    }

    /**
     * If a player collides with a wall, it should move outside that wall.
     * @param wall the wall the player collides with
     */
    private void collideWith(final Wall wall) {
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
     * Draws the running sprite if the player is moving, draws the idle sprite
     * otherwise.
     */
    public final void draw() {
        if (speed.x == 0) {
            idleSprite.draw(position);
        } else {
            runningSprite.draw(position, Math.signum(speed.x), 1);
        }
    }
}
