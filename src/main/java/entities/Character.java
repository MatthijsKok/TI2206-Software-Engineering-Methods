package entities;

import com.sun.javafx.geom.Vec2d;
import game.Game;
import geometry.Rectangle;
import geometry.Shape;
import util.Sprite;

import java.util.HashMap;


/**
 * The Character class represents a character.
 */
public class Character extends Entity {

    /**
     * The running speed of a character. In pixels per second.
     */
    private static final double RUN_SPEED  = 256; // px/s

    /**
     * The gravity applied to a character. In pixels per second squared.
     */
    private static final double GRAVITY    = 300; // px/s^2

    /**
     * Indicates whether a character is alive or not.
     */
    private boolean alive = true;

    /**
     * Rope of the character.
     */
    private Rope rope;

    /**
     * The sprites of the character.
     */
    private Sprite idleSprite, runningSprite;

    /**
     * The bounding box of the character.
     */
    private Rectangle shape;

    /**
     * State of the character, indicates which action a character is performing.
     */
    private int direction = 0;

    /**
     * Indicates whether the character is shooting.
     */
    private boolean shooting = false;

    /**
     * Instantiate a new character at position (x, y).
     * @param x The x position of the character.
     * @param y The y position of the character.
     * @param id The id of the player.
     */
    public Character(int id, final double x, final double y) {
        super(x, y);

        setSprites(id);

        // Create rope for the character and add it to the level
        rope = new Rope();
        Game.getInstance().getState().getCurrentLevel().addEntity(rope);

        shape = new Rectangle(runningSprite.getWidth(),
                              runningSprite.getHeight());
        updatePosition(0);
    }

    /**
     * The character dies. Soo sad...
     * After it dies it tells everybody it has died, but its already dead. How does that even work?
     */
    public void die() {
        alive = false;
        setChanged();
        HashMap<String, Boolean> hashMap = new HashMap<>();
        hashMap.put("dead", !isAlive());
        notifyObservers(hashMap);
    }

    /**
     * @return whether the character is alive
     */
    public final boolean isAlive() {
        return alive;
    }

    /**
     * @return the left side of the bounding box of the character.
     */
    private double getLeft() {
        return shape.getLeft();
    }

    /**
     * @return the right side of the bounding box of the character.
     */
    private double getRight() {
        return shape.getRight();
    }

    /**
     * Set the left side of the bounding box of the character to equal left.
     * @param left the target for the left side of the bounding box.
     */
    private void setLeft(final double left) {
        position.x += left - shape.getLeft();
        updatePosition();
    }

    /**
     * Set the right side of the bounding box of the character to equal right.
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
     * Update the characters position and collision shape.
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
     * Makes the character stop moving.
     */
    public void stop() {
        this.direction = 0;
    }

    /**
     * Makes the character move to the left.
     */
    public void moveLeft() {
        this.direction = -1;
    }

    /**
     * Makes the character move to the right.
     */
    public void moveRight() {
        this.direction = 1;
    }

    /**
     * Makes the player toggle shooting.
     * @param shooting boolean whether the player is shooting or not.
     */
    public void setShooting(boolean shooting) {
        this.shooting = shooting;
    }

    /**
     * Updates the Character object.
     * @param dt The time since the last time the update method was called
     */
    public final void update(final double dt) {
        // Update the character sprite
        runningSprite.update(dt);

        // Walk
        speed.x = RUN_SPEED * direction;

        // Apply gravity
        speed.y += GRAVITY * dt;

        // Shoot
        if (shooting) {
            rope.shoot(position);
        }

        // Move
        updatePosition(dt);
    }

    /**
     * @return the character's shape.
     */
    public final Shape getShape() {
        return shape;
    }

    /**
     * Entry point for collisions.
     * @param entity the entity the character collides with
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
     * When a character collides with a ball, the character dies.
     * @param ball the ball the character collides with
     */
    private void collideWith(final Ball ball) {
        if (ball != null) {
            die();
        }
    }

    /**
     * If a character collides with a ground block, the character should not sink
     * through it.
     * @param block the ground block the character collides with
     */
    private void collideWith(final Block block) {
        position.y = Math.min(position.y, block.getY());
        speed.y = Math.min(speed.y, 0);
        updatePosition();
    }

    /**
     * If a character collides with a wall, it should move outside that wall.
     * @param wall the wall the character collides with
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
     * Draws the running sprite if the character is moving, draws the idle sprite
     * otherwise.
     */
    public final void draw() {
        if (direction == 0) {
            idleSprite.draw(position);
        } else {
            runningSprite.draw(position, direction, 1);
        }
    }

    /**
     * Sets the correct sprites according to the player id.
     * @param id The player id.
     */
    @SuppressWarnings("magicnumber") //useless too make new fields for the offsets.
    public void setSprites(int id) {
        switch (id) {
            case 0:
                //Set Mario as player 1
                idleSprite = new Sprite("player/mario_idle.png", 1, new Vec2d(8, 32));
                runningSprite = new Sprite("player/mario_running.png", 8, new Vec2d(11, 35));
                break;
            case 1:
                //Set Yoshi as player 2
                idleSprite = new Sprite("player/yoshi_idle.png", 1, new Vec2d(8, 32));
                runningSprite = new Sprite("player/yoshi_running.png", 8, new Vec2d(11, 37));
                break;
            default:
                //Set Mario for any other players
                idleSprite = new Sprite("player/mario_idle.png", 1, new Vec2d(8, 32));
                runningSprite = new Sprite("player/mario_running.png", 8, new Vec2d(11, 35));
        }
    }

    /**
     * @return The rope of the player.
     */
    public Rope getRope() {
        return rope;
    }
}
