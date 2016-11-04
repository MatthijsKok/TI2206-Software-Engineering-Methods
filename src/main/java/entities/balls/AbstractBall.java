package entities.balls;

import com.sun.javafx.geom.Vec2d;
import entities.AbstractEntity;
import entities.CollidingEntity;
import entities.behaviour.GravityBehaviour;
import entities.blocks.AbstractBlock;
import entities.blocks.SpikeBlock;
import entities.character.Shield;
import entities.character.bullets.Vine;
import entities.powerups.PickupFactory;
import geometry.Circle;
import geometry.Point;
import geometry.Rectangle;
import util.sound.SoundEffect;

/**
 * Class that represents the bouncing balls in our game.
 */
public abstract class AbstractBall extends AbstractEntity implements CollidingEntity {

    /**
     * The chance that splitting a ball spawns a pickup.
     */
    private static final double DROP_CHANCE = 0.25;
    /**
     * Radius of a ball with size 0.
     */
    private static final double BASE_SIZE = 1.d / 8.d;
    /**
     * Exponential growth factor.
     */
    private static final double GROW_FACTOR = 2;
    /**
     * Bounce speeds for the different ball sizes.
     */
    private static final double[] BOUNCE_SPEEDS = {225, 300, 375, 450, 500};
    /**
     * Horizontal speed of a ball, in pixels per second.
     */
    private static final double HORIZONTAL_SPEED = 100;
    /**
     * The collision shape of a ball.
     */
    private static final Circle BALL_COLLISION_SHAPE = new Circle(51);
    /**
     * Size of a ball. from 0 (16 x 16px) to 4 (256 x 256px).
     */
    private final int size;

    /**
     * Creates a new AbstractBall at position position, with size size and color color.
     * @param position ball Vec2d position
     * @param size     ball int size
     */
    AbstractBall(final Vec2d position, final int size) {
        this(position, size, new Vec2d(HORIZONTAL_SPEED, 0));
    }

    /**
     * Constructor for the bouncing balls in our game.
     * @param position Vec2d of the starting position of the ball.
     * @param size     Integer ranging 0-4 representing the ball size.
     * @param speed    Vec2d initial speed of the bal.
     */
    AbstractBall(final Vec2d position, final int size, final Vec2d speed) {
        super(position);

        this.size = Math.max(0, size);

        setScale();
        setShape(new Circle(BALL_COLLISION_SHAPE));
        setSpeed(speed);
        setPhysicsBehaviour(new GravityBehaviour(this));
    }

    /**
     * Sets the ball size. Method only used internally.
     */
    private void setScale() {
        setScale(BASE_SIZE * Math.pow(GROW_FACTOR, size));
    }

    /**
     * Gets the ball size.
     * @return Integer from 0 (tiny ball) to 4 (huge ball).
     */
    public final int getSize() {
        return size;
    }

    /**
     * Removes this ball from the level and adds two smaller balls on the same
     * position, moving in different x directions. If the ball is already at it's
     * smallest, the ball will disappear and no new balls will be added.
     */
    /* default */ void die() {
        getLevel().removeEntity(this);

        if (Math.random() < DROP_CHANCE) {
            PickupFactory.spawnRandomPickUp(getLevel(), getPosition());
        }
    }

    /**
     * Set the vertical speed to of the ball to the bounceSpeed.
     */
    private void bounce() {
        setYSpeed(-getBounceSpeed());
        SoundEffect.BALL_BOUNCE.play();
    }

    /**
     * Get the bounceSpeed.
     * @return The bounce speed of this ball, dependent on it's size.
     */
    private double getBounceSpeed() {
        return BOUNCE_SPEEDS[size];
    }

    @Override
    public void collideWith(AbstractEntity entity) {
        if (entity instanceof SpikeBlock) {
            collideWith((SpikeBlock) entity);
        } else if (entity instanceof AbstractBlock) {
            collideWith((AbstractBlock) entity);
        }

        if (entity instanceof Vine) {
            collideWithVine();
        }

        if (entity instanceof Shield) {
            collideWithShield();
        }
    }

    /**
     * The behaviour of an AbstractBall when it collides with a FloorBlock.
     * @param ceiling The SpikeBlock this AbstractBall collides with.
     */
    private void collideWith(SpikeBlock ceiling) {
        setY(Math.max(
                ((Rectangle) ceiling.getShape()).getBottom()
                        + ((Circle) getShape()).getRadius(),
                getY()));

        setYSpeed(Math.max(0, getYSpeed()));
        die();
    }

    /**
     * The behaviour of the AbstractBall when it collides with a Shield.
     */
    private void collideWithShield() {
        bounce();
    }

    /**
     * The behaviour of an AbstractBall when it collides with a WallBlock.
     * @param block The AbstractBlock AbstractEntity this AbstractBall collides with.
     */
    private void collideWith(AbstractBlock block) {
        Rectangle blockShape = (Rectangle) block.getShape();
        double radius = ((Circle) getShape()).getRadius();

        Point left   = new Point(getX() - radius, getY());
        Point right  = new Point(getX() + radius, getY());
        Point top    = new Point(getX(), getY() - radius);
        Point bottom = new Point(getX(), getY() + radius);

        if (left.intersects(blockShape) && getXSpeed() < 0) {
            setX(blockShape.getRight() + radius);
            setXSpeed(HORIZONTAL_SPEED);
        }

        if (right.intersects(blockShape) && getXSpeed() > 0) {
            setX(blockShape.getLeft() - radius);
            setXSpeed(-HORIZONTAL_SPEED);
        }

        if (top.intersects(blockShape) && getYSpeed() < 0) {
            setY(blockShape.getBottom() + radius);
            setYSpeed(Math.max(0, getYSpeed()));
        }

        if (bottom.intersects(blockShape) && getYSpeed() > 0) {
            setY(blockShape.getTop() - radius);
            bounce();
        }
    }

    /**
     * The behaviour of the AbstractBall when it collides with a Vine AbstractEntity.
     */
    private void collideWithVine() {
        bounce();
        die();
    }
}
