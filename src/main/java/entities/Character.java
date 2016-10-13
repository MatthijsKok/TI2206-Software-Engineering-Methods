package entities;

import com.sun.javafx.geom.Vec2d;
import game.Game;
import geometry.Rectangle;
import graphics.Sprite;

import java.util.HashMap;


/**
 * The Character class represents a character.
 */
public class Character extends AbstractEntity {

    /**
     * The offset of the bounding box of a character.
     */
    private static final Vec2d OFFSET = new Vec2d(8, 32);
    /**
     * The bounding box of a character.
     */
    private static final Rectangle BOUNDING_BOX = new Rectangle(16, 32);
    /**
     * The running speed of a character. In pixels per second.
     */
    private static final double RUN_SPEED = 256; // px/s
    /**
     * The gravity applied to a character. In pixels per second squared.
     */
    private static final double GRAVITY = 300; // px/s^2

    static {
        BOUNDING_BOX.setOffset(OFFSET.x, OFFSET.y);
    }

    /**
     * Indicates whether a character is alive or not.
     */
    private boolean alive = true;

    /**
     * Harpoon of the character.
     */
    private Harpoon harpoon;

    /**
     * The sprites of the character.
     */
    private Sprite idleSprite, runningSprite;

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
     * @param position position of the character
     */
    Character(final Vec2d position) {
        super(position);

        // Create harpoon for the character and add it to the level
        harpoon = new Harpoon();
        Game.getInstance().getState().getCurrentLevel().addEntity(harpoon);

        setShape(new Rectangle(BOUNDING_BOX));
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
    boolean isAlive() {
        return alive;
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
     * @return The harpoon of the player.
     */
    public Harpoon getHarpoon() {
        return harpoon;
    }

    /**
     * Updates the Character object.
     * @param timeDifference The time since the last time the update method was called
     */
    public final void update(final double timeDifference) {
        // Walk
        setSpeed(RUN_SPEED * direction, getYSpeed() + GRAVITY * timeDifference);

        // Set the character sprite
        if (direction == 0) {
            setSprite(idleSprite);
        } else {
            setSprite(runningSprite);
        }

        // Shoot
        if (shooting) {
            harpoon.shoot(getPosition());
        }
    }

    /**
     * Entry point for collisions.
     * @param entity the entity the character collides with
     */
    public final void collideWith(final AbstractEntity entity) {
        if (entity instanceof Ball) {
            collideWithBall();
        }

        if (entity instanceof FloorBlock) {
            collideWith((FloorBlock) entity);
        }

        if (entity instanceof WallBlock) {
            collideWith((WallBlock) entity);
        }
    }

    /**
     * When a character collides with a ball, the character dies.
     */
    private void collideWithBall() {
        die();
    }

    /**
     * If a character collides with a ground floor, the character should not sink
     * through it.
     * @param floor the ground floor the character collides with
     */
    private void collideWith(final FloorBlock floor) {
        Rectangle shape = (Rectangle) getShape();
        Rectangle blockShape = (Rectangle) floor.getShape();

        if (shape.getBottom() > blockShape.getTop() && shape.getBottom() < blockShape.getBottom()) {
            // Hit the floor from above
            shape.setBottom(blockShape.getTop());
            getSpeed().y = Math.min(getYSpeed(), 0);
        } else if (shape.getTop() > blockShape.getTop() && shape.getTop() < blockShape.getBottom()) {
            // Hit the floor from below
            shape.setTop(blockShape.getBottom());
            getSpeed().y = Math.max(0, getYSpeed());
        }
    }

    /**
     * If a character collides with a wall, it should move outside that wall.
     * @param wall the wall the character collides with
     */
    private void collideWith(final WallBlock wall) {
        Rectangle shape = (Rectangle) getShape();
        Rectangle blockShape = (Rectangle) wall.getShape();

        if (shape.getRight() > blockShape.getLeft() && shape.getRight() < blockShape.getRight()) {
            // Hit the block from above
            shape.setRight(blockShape.getLeft());
            getSpeed().x = Math.min(getXSpeed(), 0);
        } else if (shape.getLeft() > blockShape.getLeft() && shape.getLeft() < blockShape.getRight()) {
            // Hit the block from below
            shape.setLeft(blockShape.getRight());
            getSpeed().x = Math.max(0, getXSpeed());
        }
    }

    /**
     * Draws the running sprite if the character is moving, draws the idle sprite
     * otherwise.
     */
    public final void draw() {
        if (direction == 0) {
            getSprite().draw(getPosition());
        } else {
            getSprite().draw(getPosition(), direction, 1);
        }
    }

    /**
     * Sets the correct sprites according to the player id.
     * @param id The player id.
     */
    @SuppressWarnings("magicnumber") //useless too make new fields for the offsets.
    void setId(int id) {
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
}
