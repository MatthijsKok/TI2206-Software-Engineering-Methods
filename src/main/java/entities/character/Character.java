package entities.character;

import com.sun.javafx.geom.Vec2d;
import entities.AbstractEntity;
import entities.CollidingEntity;
import entities.DynamicEntity;
import entities.balls.AbstractBall;
import entities.behaviour.GravityBehaviour;
import entities.blocks.AbstractBlock;
import entities.character.bullets.Vine;
import geometry.Point;
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

    static {
        BOUNDING_BOX.setOffset(OFFSET.x, OFFSET.y);
    }

    /**
     * The shield this character carries.
     */
    private final Shield shield;
    /**
     * The gun this character carries.
     */
    private final Gun gun;
    /**
     * The object that handles the movement of this character.
     */
    private final CharacterMovement movement;
    /**
     * Indicates whether a character is alive or not.
     */
    private boolean alive = true;
    /**
     * The sprites of the character.
     */
    private Sprite idleSprite, runningSprite;

    /**
     * Instantiate a new character at position (x, y).
     *
     * @param position position of the character
     */
    public Character(final Vec2d position) {
        super(position);

        movement = new CharacterMovement(this);
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
    /* default */ void die() {
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
    public boolean isAlive() {
        return alive;
    }

    @Override
    public final void update(final double timeDifference) {
        movement.update();

        // Set the character sprite
        if (movement.isIdle()) {
            setSprite(idleSprite);
        } else {
            setSprite(runningSprite);
            setXScale(movement.getDirection());
        }
    }

    @Override
    public final void collideWith(final AbstractEntity entity) {
        if (entity instanceof AbstractBall) {
            collideWithBall();
        }

        if (entity instanceof AbstractBlock) {
            collideWith((AbstractBlock) entity);
        }
    }

    /**
     * When a character collides with a ball, the character dies.
     */
    private void collideWithBall() {
        die();
    }

    /**
     * If a character collides with a block, it should move outside of it.
     *
     * @param block AbstractBlock - The block the character collides with.
     */
    private void collideWith(final AbstractBlock block) {
        Rectangle shape = (Rectangle) getShape();

        Point left = new Point(shape.getLeft(), (shape.getTop() + shape.getBottom()) / 2);
        Point right = new Point(shape.getRight(), (shape.getTop() + shape.getBottom()) / 2);
        Point top = new Point((shape.getLeft() + shape.getRight()) / 2, shape.getTop());
        Point bottom = new Point((shape.getLeft() + shape.getRight()) / 2, shape.getBottom());

        Rectangle blockShape = (Rectangle) block.getShape();

        if (left.intersects(blockShape)) {
            shape.setLeft(blockShape.getRight());
            setXSpeed(Math.max(0, getXSpeed()));
        }

        if (right.intersects(blockShape)) {
            shape.setRight(blockShape.getLeft());
            setXSpeed(Math.min(0, getXSpeed()));
        }

        if (top.intersects(blockShape)) {
            shape.setTop(blockShape.getBottom());
            setYSpeed(Math.max(0, getYSpeed()));
        }

        if (bottom.intersects(blockShape)) {
            shape.setBottom(blockShape.getTop());
            setYSpeed(Math.min(0, getYSpeed()));
        }
    }

    /**
     * Set the player that controls this Character.
     *
     * @param playerID Integer - The id of the player that
     *                 controls this Character.
     */
    public void setSprites(final int playerID) {
        idleSprite = CharacterSprites.getIdleSprite(playerID);
        runningSprite = CharacterSprites.getRunningSprite(playerID);
    }

    /**
     * @return Gun - The gun this character carries.
     */
    public Gun getGun() {
        return gun;
    }

    /**
     * @return CharacterMovement - The movement of this character.
     */
    public CharacterMovement getMovement() {
        return movement;
    }

    /**
     * Activates a shield around this character.
     */
    public void activateShield() {
        shield.activate();
    }

    /**
     * Increases the score of the character.
     *
     * @param score the amount of the increase.
     */
    public void increaseScore(final int score) {
        notifyObservers(new Pair<>("increaseScore", score));
    }
}
