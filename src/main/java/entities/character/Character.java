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
    private static final Vec2d OFFSET = new Vec2d(12, 24);
    /**
     * The bounding box of a character.
     */
    private static final Rectangle BOUNDING_BOX = new Rectangle(24, 48);
    static {
        BOUNDING_BOX.setOffset(OFFSET.x, OFFSET.y);
    }
    /**
     * The offset of the bounding box of a character.
     */
    private static final double SCALE = 1.3;
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
     * @param position position of the character
     */
    public Character(final Vec2d position) {
        super(position);

        movement = new CharacterMovement(this);
        shield = new Shield(this);
        gun = new Gun<>(this, Vine.class);
        this.setScale(SCALE);
        getLevel().addEntity(shield);
        getLevel().addEntity(gun);

        setShape(new Rectangle(BOUNDING_BOX));
        setPhysicsBehaviour(new GravityBehaviour(this));
    }

    /**
     * The character dies.
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
     * Check if the player is alive.
     * @return whether the character is alive.
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
            setXScale(SCALE * movement.getDirection());
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
     * @param block AbstractBlock The block the character collides with.
     */
    private void collideWith(final AbstractBlock block) {
        Rectangle shape = (Rectangle) getShape();

        Point left = new Point(shape.getLeft(), (shape.getTop() + shape.getBottom()) / 2);
        Point right = new Point(shape.getRight(), (shape.getTop() + shape.getBottom()) / 2);
        Point top = new Point((shape.getLeft() + shape.getRight()) / 2, shape.getTop());
        Point bottom = new Point((shape.getLeft() + shape.getRight()) / 2, shape.getBottom());

        Rectangle blockShape = (Rectangle) block.getShape();

        if (left.intersects(blockShape) && getXSpeed() < 0) {
            shape.setLeft(blockShape.getRight());
            setXSpeed(Math.max(0, getXSpeed()));
        }

        if (right.intersects(blockShape) && getXSpeed() > 0) {
            shape.setRight(blockShape.getLeft());
            setXSpeed(Math.min(0, getXSpeed()));
        }

        if (top.intersects(blockShape) && getYSpeed() < 0) {
            shape.setTop(blockShape.getBottom());
            setYSpeed(Math.max(0, getYSpeed()));
        }

        if (bottom.intersects(blockShape) && getYSpeed() > 0) {
            shape.setBottom(blockShape.getTop());
            setYSpeed(Math.min(0, getYSpeed()));
        }
    }

    /**
     * Set the player that controls this Character.
     * @param playerID Integer the id of the player that
     *                 controls this Character.
     */
    public void setSprites(final int playerID) {
        idleSprite = CharacterSprites.getIdleSprite(playerID);
        runningSprite = CharacterSprites.getRunningSprite(playerID);
    }

    /**
     * @return Gun the gun this character carries.
     */
    public Gun getGun() {
        return gun;
    }

    /**
     * @return CharacterMovement the movement of this character.
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
     * @param score integer the amount of the increase.
     */
    public void increaseScore(final int score) {
        setChanged();
        notifyObservers(new Pair<>("increaseScore", score));
    }
}
