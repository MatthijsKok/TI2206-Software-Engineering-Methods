package entities.character;

import com.sun.javafx.geom.Vec2d;
import entities.AbstractEntity;
import entities.FloorBlock;
import entities.Harpoon;
import entities.Shield;
import entities.WallBlock;
import entities.balls.AbstractBall;
import entities.behaviour.GravityBehaviour;
import game.player.Player;
import geometry.Rectangle;
import graphics.Sprite;
import util.Pair;

/**
 * The Character class represents a entities.character.
 */
public class Character extends AbstractEntity {

    /**
     * The offset of the bounding box of a entities.character.
     */
    private static final Vec2d OFFSET = new Vec2d(8, 16);
    /**
     * The bounding box of a entities.character.
     */
    private static final Rectangle BOUNDING_BOX = new Rectangle(16, 32);
    /**
     * The default run speed of a entities.character.
     */
    private static final double DEFAULT_RUN_SPEED = 230; // px/s

    static {
        BOUNDING_BOX.setOffset(OFFSET.x, OFFSET.y);
    }

    /**
     * The running speed of a entities.character. In pixels per second.
     */
    private double runSpeed = DEFAULT_RUN_SPEED;
    /**
     * Indicates whether a entities.character is alive or not.
     */
    private boolean alive = true;

    /**
     * The amount of harpoons this entities.character can shoot.
     */
    private int maxHarpoonCount = 1;
    /**
     * The amount of harpoons this entities.character has currently shot.
     */
    private int currentHarpoonCount = 0;

    /**
     * The sprites of the entities.character.
     */
    private Sprite idleSprite, runningSprite;

    /**
     * State of the entities.character, indicates which action a entities.character is performing.
     */
    private int direction = 0;

    /**
     * Indicates whether the entities.character is shooting.
     */
    private boolean shooting = false;

    /**
     * The Player that is controlling this Character.
     */
    private Player player;

    /**
     * Boolean indicating whether the entities.character is invincible.
     */
    private Shield shield = new Shield(this);

    /**
     * Boolean indicating whether the entities.character can shoot.
     */
    private boolean canShoot = true;

    /**
     * Instantiate a new entities.character at position (x, y).
     *
     * @param position position of the entities.character
     */
    public Character(final Vec2d position) {
        super(position);

        setShape(new Rectangle(BOUNDING_BOX));
        setPhysicsBehaviour(new GravityBehaviour(this));
        getLevel().addEntity(shield);
    }

    /**
     * The entities.character dies. Soo sad...
     * After it dies it tells everybody it has died, but its already dead. How does that even work?
     */
    public void die() {
        alive = false;
        setChanged();
        notifyObservers(new Pair<>("die", true));
    }

    /**
     * Adds a life to this entities.character.
     */
    public void increaseLife() {
        getPlayer().increaseLives(1);
    }

    /**
     * @return whether the entities.character is alive
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * Makes the entities.character stop moving.
     */
    public void stop() {
        this.direction = 0;
    }

    /**
     * Makes the entities.character move to the left.
     */
    public void moveLeft() {
        this.direction = -1;
    }

    /**
     * Makes the entities.character move to the right.
     */
    public void moveRight() {
        this.direction = 1;
    }

    /**
     * Makes the player toggle shooting.
     *
     * @param shooting boolean whether the player is shooting or not.
     */
    public void setShooting(boolean shooting) {
        this.shooting = shooting;
    }

    @Override
    public final void update(final double timeDifference) {
        // Walk
        setXSpeed(runSpeed * direction);

        // Set the entities.character sprite
        if (direction == 0) {
            setSprite(idleSprite);
        } else {
            setSprite(runningSprite);
            setXScale(direction);
        }

        // Shoot
        if (shooting && canShoot && currentHarpoonCount < maxHarpoonCount) {
            currentHarpoonCount++;
            getLevel().addEntity(new Harpoon(getPosition(), this));
        }

        canShoot = !shooting;
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
     * When a entities.character collides with a ball, the entities.character dies.
     */
    private void collideWithBall() {
        die();
    }

    /**
     * If a entities.character collides with a ground floor, the entities.character should not sink
     * through it.
     *
     * @param floor the ground floor the entities.character collides with
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
     * If a entities.character collides with a wall, it should move outside that wall.
     *
     * @param wall the wall the entities.character collides with
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
     * Increases the speed at which the entities.character runs.
     *
     * @param amount The speed boost.
     */
    public void increaseRunSpeed(final double amount) {
        this.runSpeed += amount;
    }

    /**
     * @return The Player object that is controlling this Character object
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Set the player that controls this Character.
     *
     * @param player The Player object that controls this Character.
     */
    public void setPlayer(final Player player) {
        this.player = player;
        idleSprite = CharacterSprites.getIdleSprite(player.getId());
        runningSprite = CharacterSprites.getRunningSprite(player.getId());
    }

    /**
     * Increases the amount of harpoons this entities.character can shoot.
     *
     * @param amount the amount of harpoons a entities.character can shoot extra.
     */
    public void increaseMaxHarpoonCount(int amount) {
        maxHarpoonCount = Math.max(1, maxHarpoonCount + amount);
    }

    /**
     * Called when a harpoon is removed from the level.
     */
    public void harpoonRemoved() {
        currentHarpoonCount = Math.max(0, currentHarpoonCount - 1);
    }

    /**
     * Activates a shield around this entities.character.
     */
    public void activateShield() {
        shield.activate();
    }

    /**
     * Increases the score of the entities.character.
     * @param score the amount of the increase.
     */
    public void increaseScore(final int score) {
        notifyObservers(new Pair<>("increaseScore", score));
    }
}
