package entities;

import com.sun.javafx.geom.Vec2d;
import entities.behaviour.AbstractPhysicsBehaviour;
import entities.behaviour.NoGravityBehaviour;
import game.Game;
import geometry.AbstractShape;
import geometry.Rectangle;
import graphics.Sprite;
import level.Level;

import java.util.Observable;

/**
 * Class containing all the functionality shared by all in-game entities.
 */
public abstract class AbstractEntity extends Observable {
    /**
     * Position of the entity.
     */
    /* default */ private Vec2d position = new Vec2d(0, 0);

    /**
     * Speed of the entity.
     */
    /* default */ private final Vec2d speed = new Vec2d(0, 0);

    /**
     * Scale of the entity.
     */
    /* default */ private final Vec2d scale = new Vec2d(1, 1);

    /**
     * Sprite of the entity.
     */
    private Sprite sprite;

    /**
     * AbstractShape used for collision detection of the entity.
     */
    private AbstractShape shape;

    /**
     * Boolean with the current visibility state of an entity.
     */
    private boolean visible = true;

    /**
     * The physics behaviour of an entity.
     */
    private AbstractPhysicsBehaviour physicsBehaviour = new NoGravityBehaviour(this);

    /**
     * Depth of this entity.
     * Entities with higher depth are drawn below entities with
     * lower depth.
     */
    private int depth;

    /**
     * Creates a new entity.
     * @param position The position of the entity.
     */
    public AbstractEntity(final Vec2d position) {
        super();
        setPosition(position.x, position.y);
    }

    /**
     * Update the entity's position and collision shape.
     *
     * @param timeDifference the time
     */
    public final void updatePosition(final double timeDifference) {
        if (timeDifference > 0) {
            position.x += speed.x * timeDifference;
            position.y += speed.y * timeDifference;
        }
    }

    /**
     * Updates the entity's sprite if it has one.
     * @param timeDifference The time elapsed since the last time
     *                       the method was called
     */
    public final void updateSprite(final double timeDifference) {
        if (sprite != null) {
            sprite.update(timeDifference);
        }
    }

    /**
     * Applies physics on the entity.
     * @param timeDifference The time elapsed since the last time
     *                       the method was called
     */
    public final void applyPhysicsBehaviour(final double timeDifference) {
        physicsBehaviour.applyPhysics(timeDifference);
    }

    /**
     * Checks if the entity intersects with another entity.
     * @param entity The entity you want to check for intersection
     * @return a boolean indicating if the entity intersects with
     * the other entity
     */
    public final boolean intersects(final AbstractEntity entity) {
        return isVisible()
                && entity.isVisible()
                && shape != null
                && shape.intersects(entity.shape);
    }

    /**
     * Draws the entity to the screen.
     */
    public void draw() {
        if (isVisible() && sprite != null) {
            sprite.draw(getPosition(), getScale());
        }
    }

    // GETTERS

    /**
     * Returns the position of the entity.
     * @return the position
     */
    public Vec2d getPosition() {
        return position;
    }

    /**
     * @return The x position of the entity.
     */
    public double getX() {
        return position.x;
    }

    /**
     * @return The y position of the entity.
     */
    public double getY() {
        return position.y;
    }

    /**
     * Returns the speed of the entity.
     * @return the speed of the entity
     */
    public Vec2d getSpeed() {
        return speed;
    }

    /**
     * @return The horizontal speed of the entity.
     */
    public double getXSpeed() {
        return speed.x;
    }

    /**
     * @return The vertical speed of the entity.
     */
    public double getYSpeed() {
        return speed.y;
    }

    /**
     * Returns the scale of the entity.
     * @return the scale of the entity
     */
    private Vec2d getScale() {
        return scale;
    }

    /**
     * @return The horizontal scale of the entity.
     */
    private double getXScale() {
        return scale.x;
    }

    /**
     * @return The vertical scale of the entity.
     */
    private double getYScale() {
        return scale.y;
    }

    /**
     * Returns the sprite of the entity.
     * @return the sprite of the entity
     */
    /* default */ public Sprite getSprite() {
        return sprite;
    }

    /**
     * Returns The shape that is used for collisions of the entity.
     * @return The shape that is used for collisions of the entity
     */
    public AbstractShape getShape() {
        return shape;
    }

    /**
     * Returns the boolean indicating if the sprite is visible.
     * @return the boolean indicating if the sprite is visible
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Returns the level the entity is in.
     * @return the level the entity is in
     */
    public Level getLevel() {
        return Game.getInstance().getState().getCurrentLevel();
    }

    //SETTERS

    /**
     * Set the position of the entity.
     * @param xPosition The x coordinate of the entity
     * @param yPosition The y coordinate of the entity
     */
    private void setPosition(final double xPosition, final double yPosition) {
        position.x = xPosition;
        position.y = yPosition;
    }

    /**
     * Binds the position of this entity to position.
     * @param position the position to bind to.
     */
    protected final void bindPosition(final Vec2d position) {
        this.position = position;
    }

    /**
     * Set the horizontal position of the entity.
     * @param xPosition the x coordinate of the entity
     */
    protected void setX(final double xPosition) {
        position.x = xPosition;
    }

    /**
     * Set the vertical position of the entity.
     * @param yPosition the y coordinate of the entity
     */
    protected void setY(final double yPosition) {
        position.y = yPosition;
    }

    /**
     * Sets the speed of the entity.
     * @param speed The speed vector
     */
    protected void setSpeed(final Vec2d speed) {
        setSpeed(speed.x, speed.y);
    }

    /**
     * Sets the speed of the entity.
     * @param xSpeed The horizontal speed
     * @param ySpeed The vertical speed
     */
    private void setSpeed(final double xSpeed, final double ySpeed) {
        speed.x = xSpeed;
        speed.y = ySpeed;
    }

    /**
     * Sets the horizontal speed of the entity.
     * @param xSpeed horizontal speed
     */
    protected void setXSpeed(final double xSpeed) {
        speed.x = xSpeed;
    }

    /**
     * Sets the vertical speed of the entity.
     * @param ySpeed vertical speed
     */
    public final void setYSpeed(final double ySpeed) {
        speed.y = ySpeed;
    }

    /**
     * Sets the scale of the entity.
     * @param xScale double - the target horizontal scale
     */
    protected final void setXScale(final double xScale) {
        setScale(xScale, getYScale());
    }

    /**
     * Sets the scale of the entity.
     * @param yScale double - the target vertical scale
     */
    protected final void setYScale(final double yScale) {
        setScale(getXScale(), yScale);
    }

    /**
     * Sets the scale of the entity.
     * @param scale double - the target scale
     */
    protected final void setScale(final double scale) {
        setScale(scale, scale);
    }

    /**
     * Sets the scale of the entity.
     * @param xScale double - the target horizontal scale
     * @param yScale double - the target vertical scale
     */
    private void setScale(final double xScale, final double yScale) {
        scale.x = xScale;
        scale.y = yScale;
    }

    /**
     * Sets the sprite of the entity.
     * @param sprite a Sprite object for the entity
     */
    protected void setSprite(final Sprite sprite) {
        this.sprite = sprite;
    }

    /**
     * Sets the shape of the entity and binds its position to the
     * entity's position.
     * @param shape Sprite object for the entity.
     */
    protected void setShape(final AbstractShape shape) {
        shape.bindPosition(position);
        shape.bindScale(scale);
        this.shape = shape;
    }

    /**
     * Sets the shape of the entity and binds its position to the
     * entity's position.
     */
    protected void setShapeToSprite() {
        if (sprite != null) {
            setShape(new Rectangle(sprite));
        }
    }

    /**
     * Set whether the entity is visible.
     * @param visible a boolean indicating if the entity should be visible.
     */
    protected void setVisibility(final boolean visible) {
        this.visible = visible;
    }

    /**
     * Sets the physics behaviour of the entity.
     * @param physicsBehaviour the behaviour.
     */
    protected void setPhysicsBehaviour(final AbstractPhysicsBehaviour physicsBehaviour) {
        this.physicsBehaviour = physicsBehaviour;
    }

    /**
     * Sets the depth of this entity and sorts all entities in the
     * current level.
     * @param depth the depth to set.
     */
    protected final void setDepth(final int depth) {
        this.depth = depth;
        getLevel().depthSort();
    }

    /**
     * @return Integer - the depth of this entity.
     */
    public final int getDepth() {
        return depth;
    }
}
