package entities.balls;

import com.sun.javafx.geom.Vec2d;
import entities.AbstractEntity;
import entities.FloorBlock;
import entities.Shield;
import entities.Vine;
import entities.WallBlock;
import entities.behaviour.GravityBehaviour;
import entities.powerups.PickupFactory;
import geometry.Circle;
import geometry.Rectangle;
import util.sound.SoundEffect;

/**
 * Class that represents the bouncing balls in our game.
 */
public abstract class AbstractBall extends AbstractEntity {

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
     * Bounce speed for the different ball sizes.
     */
    private static final double[] BOUNCE_SPEEDS = {225, 300, 375, 450, 500};
    /**
     * Bounce speed for the different ball sizes when the ball splits.
     */
    private static final double[] SPLIT_BOUNCE_SPEEDS = {100, 150, 200, 300, 400};
    /**
     * Horizontal speed of a ball. In pixels per second.
     */
    private static final double HORIZONTAL_SPEED = 100; // px/s
    /**
     * The collision shape of a ball.
     */
    private static final Circle BALL_SHAPE = new Circle(51);

    /**
     * Size of a ball. from 0 (16 x 16px) to 4 (256 x 256px).
     */
    private final int size;

    /**
     * Creates a new AbstractBall at position position, with size size and color color.
     *
     * @param position ball position
     * @param size     ball size
     */
    AbstractBall(final Vec2d position, final int size) {
        this(position, size, new Vec2d(HORIZONTAL_SPEED, 0));
    }

    /**
     * Constructor for the bouncing balls in our game.
     *
     * @param position Vec2d of the starting position of the ball.
     * @param size     Integer ranging 0-4 representing the ball size.
     * @param speed    Vec2d initial speed of the bal.
     */
    AbstractBall(final Vec2d position, final int size, final Vec2d speed) {
        super(position);

        this.size = Math.max(0, size);

        setScale();
        setShape(new Circle(BALL_SHAPE));
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
     *
     * @return Integer from 0 (tiny ball) to 4 (huge ball).
     */
    public final int getSize() {
        return size;
    }

    /**
     * Removes this ball from the level and adds two smaller balls on the same
     * position, moving in different directions. If the ball is already at it's
     * smallest, no new balls will be added.
     */
    void die() {
        getLevel().removeEntity(this);

        if (Math.random() < DROP_CHANCE) {
            PickupFactory.spawnRandomPickUp(getLevel(), getPosition());
        }
    }

    /**
     * Set the vertical speed to up at the speed for that balls bounce height.
     */
    private void bounce() {
        setYSpeed(-getBounceSpeed());
        SoundEffect.BALL_BOUNCE.play();
    }

    /**
     * @return The bounce speed of this ball.
     */
    double getBounceSpeed() {
        return BOUNCE_SPEEDS[size];
    }

    /**
     * @return The split bounce speed of this ball.
     */
    double getSplitBounceSpeed() {
        return SPLIT_BOUNCE_SPEEDS[size];
    }

    @Override
    public void collideWith(AbstractEntity entity) {
        if (entity instanceof FloorBlock) {
            collideWith((FloorBlock) entity);
        }

        if (entity instanceof WallBlock) {
            collideWith((WallBlock) entity);
        }

        if (entity instanceof Vine) {
            collideWithVine();
        }

        if (entity instanceof Shield) {
            collideWithShield();
        }
    }

    /**
     * The behaviour of the AbstractBall when it collides with a FloorBlock.
     *
     * @param floor The FloorBlock this AbstractBall collides with.
     */
    private void collideWith(FloorBlock floor) {
        setY(Math.min(floor.getY() - ((Circle) getShape()).getRadius(), getY()));
        bounce();
    }

    /**
     * The behaviour of the AbstractBall when it collides with a Shield.
     */
    private void collideWithShield() {
        bounce();
    }

    /**
     * The behaviour of the AbstractBall when it collides with a WallBlock.
     *
     * @param wall The WallBlock AbstractEntity this AbstractBall collides with.
     */
    private void collideWith(WallBlock wall) {
        Circle shape = (Circle) getShape();
        Rectangle wallShape = (Rectangle) wall.getShape();

        double radius = shape.getRadius();
        double right = getX() + radius;
        double left = getX() - radius;

        if (right > wallShape.getLeft()
                && right < wallShape.getRight()) {
            // Hit the wall from the right
            setX(wallShape.getLeft() - radius);
            if (getXSpeed() > 0) {
                setXSpeed(-getXSpeed());
            }
        } else if (left > wallShape.getLeft()
                && left < wallShape.getRight()) {
            // Hit the wall from the left
            setX(wallShape.getRight() + radius);
            if (getXSpeed() < 0) {
                setXSpeed(-getXSpeed());
            }
        }
    }

    /**
     * The behaviour of the AbstractBall when it collides with a Vine AbstractEntity.
     */
    private void collideWithVine() {
        die();
    }
}
