package entities.character;

import com.sun.javafx.geom.Vec2d;
import entities.AbstractEntity;
import entities.CollidingEntity;
import entities.DynamicEntity;
import entities.balls.AbstractBall;
import entities.behaviour.GravityBehaviour;
import entities.blocks.FloorBlock;
import entities.blocks.WallBlock;
import entities.character.bullets.Vine;
import geometry.Rectangle;
import graphics.Sprite;
import util.Pair;

/**
 * The Character class represents a character.
 */
public class Character extends AbstractEntity implements DynamicEntity, CollidingEntity {

    /**
     * The offset of the bounding box of a character.
     */
    private static final Vec2d OFFSET = new Vec2d(8, 16);
    /**
     * The bounding box of a character.
     */
    private static final Rectangle BOUNDING_BOX = new Rectangle(16, 32);
    /**
     * The default run speed of a character.
     */
    private static final double DEFAULT_RUN_SPEED = 230; // px/s

    static {
        BOUNDING_BOX.setOffset(OFFSET.x, OFFSET.y);
    }

    /**
     * The running speed of a character. In pixels per second.
     */
    private double runSpeed = DEFAULT_RUN_SPEED;
    /**
     * Indicates whether a character is alive or not.
     */
    private boolean alive = true;

    /**
     * The sprites of the character.
     */
    private Sprite idleSprite, runningSprite;

    /**
     * State of the character, indicates which action a character is performing.
     */
    private int direction = 0;

    /**
     * The shield this character carries.
     */
    private final Shield shield;

    /**
     * The gun this character carries.
     */
    private final Gun gun;

    /**
     * Instantiate a new character at position (x, y).
     *
     * @param position position of the character
     */
    public Character(final Vec2d position) {
        super(position);

        shield = new Shield(this);
        gun = new Gun<>(this, Vine.class);

        getLevel().addEntity(shield);
        getLevel().addEntity(gun);

        setShape(new Rectangle(BOUNDING_BOX));
        setPhysicsBehaviour(new GravityBehaviour(this));
    }

    /**
     * The character dies. Soo sad...
     * After it dies it tells everybody it has died, but its already dead. How does that even work?
     */
    public void die() {
        alive = false;
        setChanged();
        notifyObservers(new Pair<>("increaseLives", -1));
    }

    /**
     * Adds a life to this character.
     */
    public void increaseLife() {
        setChanged();
        notifyObservers(new Pair<>("increaseLives", 1));
    }

    /**
     * @return whether the character is alive
     */
    /* default */ boolean isAlive() {
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

    @Override
    public final void update(final double timeDifference) {
        // Walk
        setXSpeed(runSpeed * direction);

        // Set the character sprite
        if (direction == 0) {
            setSprite(idleSprite);
        } else {
            setSprite(runningSprite);
            setXScale(direction);
        }
    }

    @Override
    public final void collideWith(final AbstractEntity entity) {
        if (entity instanceof AbstractBall) {
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
     *
     * @param floor the ground floor the character collides with
     */
    private void collideWith(final FloorBlock floor) {
        Rectangle shape = (Rectangle) getShape();
        Rectangle blockShape = (Rectangle) floor.getShape();

        if (shape.getBottom() > blockShape.getTop() && shape.getBottom() < blockShape.getBottom()) {
            // Hit the floor from above
            shape.setBottom(blockShape.getTop());
            setYSpeed(Math.min(getYSpeed(), 0));
        } else if (shape.getTop() > blockShape.getTop() && shape.getTop() < blockShape.getBottom()) {
            // Hit the floor from below
            shape.setTop(blockShape.getBottom());
            setYSpeed(Math.max(0, getYSpeed()));
        }
    }

    /**
     * If a character collides with a wall, it should move outside that wall.
     *
     * @param wall the wall the character collides with
     */
    private void collideWith(final WallBlock wall) {
        Rectangle shape = (Rectangle) getShape();
        Rectangle blockShape = (Rectangle) wall.getShape();

        if (shape.getRight() > blockShape.getLeft() && shape.getRight() < blockShape.getRight()) {
            // Hit the block from above
            shape.setRight(blockShape.getLeft());
            setXSpeed(Math.min(getXSpeed(), 0));
        } else if (shape.getLeft() > blockShape.getLeft() && shape.getLeft() < blockShape.getRight()) {
            // Hit the block from below
            shape.setLeft(blockShape.getRight());
            setXSpeed(Math.max(0, getXSpeed()));
        }
    }

    /**
     * Set the images.player that controls this Character.
     *
     * @param playerID int - The id of the player that controls
     *                 this Character.
     */
    public void setSprites(final int playerID) {
        idleSprite = CharacterSprites.getIdleSprite(playerID);
        runningSprite = CharacterSprites.getRunningSprite(playerID);
    }

    /**
     * @return Gun - The gun this player carries.
     */
    public Gun getGun() {
        return gun;
    }

    /**
     * Activates a shield around this character.
     */
    public void activateShield() {
        shield.activate();
    }

    /**
     * Increases the score of the character.
     * @param score the amount of the increase.
     */
    public void increaseScore(final int score) {
        notifyObservers(new Pair<>("increaseScore", score));
    }

    /**
     * Increases the speed at which the entities.character runs.
     *
     * @param amount The speed boost.
     */
    public void increaseRunSpeed(final double amount) {
        this.runSpeed += amount;
    }

    /**
     * Getter for runSpeed.
     * @return runSpeed
     */
    public double getRunSpeed() {
        return runSpeed;
    }
}
