package entities;

import com.sun.javafx.geom.Vec2d;
import game.Game;
import geometry.Shape;
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
     * Sprite of the entity.
     */
    private Sprite sprite;

    /**
     * Shape used for collision detection of the entity.
     */
    private Shape shape;

    /**
     * Boolean with the current visibility state of an entity.
     */
    private boolean visible = true;

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
    public void updatePosition(final double timeDifference) {
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
    public void updateSprite(final double timeDifference) {
        if (sprite != null) {
            sprite.update(timeDifference);
        }
    }

    /**
     * Updates the entity's state.
     * @param timeDifference The time elapsed since the last time
     *                       the method was called
     */
    public void update(final double timeDifference) {
        // This method is to be implemented by subclasses.
    }

    /**
     * Checks if the entity intersects with another entity.
     * @param entity The entity you want to check for intersection
     * @return a boolean indicating if the entity intersects with
     * the other entity
     */
    public boolean intersects(final AbstractEntity entity) {
        return isVisible()
                && shape != null
                && shape.intersects(entity.shape);
    }

    /**
     * This method is an empty because it *may* be overwritten
     * by subclasses. Is does not necessarily have to be overwritten
     * so it is not an abstract method.
     * @param entity the entity to do nothing with.
     */
    public void collideWith(final AbstractEntity entity) {
        // This method is to be implemented by sub classes.
    }

    /**
     * Draws the entity to the screen.
     */
    public void draw() {
        if (isVisible()) {
            sprite.draw(getPosition());
        }
    }

    // GETTERS

    /**
     * Returns the position of the entity.
     * @return the position
     */
    /* default */ protected Vec2d getPosition() {
        return position;
    }

    /**
     * @return The x position of the entity.
     */
    /* default */ protected double getX() {
        return position.x;
    }

    /**
     * @return The y position of the entity.
     */
    /* default */ public double getY() {
        return position.y;
    }

    /**
     * Returns the speed of the entity.
     * @return the speed of the entity
     */
    /* default */ public Vec2d getSpeed() {
        return speed;
    }

    /**
     * @return The x position of the entity.
     */
    /* default */ double getXSpeed() {
        return speed.x;
    }

    /**
     * @return The y position of the entity.
     */
    /* default */ double getYSpeed() {
        return speed.y;
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
    public Shape getShape() {
        return shape;
    }

    /**
     * Returns the boolean indicating if the sprite is visible.
     * @return the boolean indicating if the sprite is visible
     */
    /* default */ boolean isVisible() {
        return visible;
    }

    /**
     * Returns the level the entity is in.
     * @return the level the entity is in
     */
    /* default */ protected Level getLevel() {
        return Game.getInstance().getState().getCurrentLevel();
    }

    //SETTERS

    /**
     * Set the position of the AbstractEntity.
     * @param xPosition The x coordinate of the entity
     * @param yPosition The y coordinate of the entity
     */
    /* default */ private void setPosition(final double xPosition, final double yPosition) {
        this.position.x = xPosition;
        this.position.y = yPosition;
    }

    /**
     * Binds the position of this entity to position.
     * @param position the position to bind to.
     */
    final /* default */ void bindPosition(final Vec2d position) {
        this.position = position;
    }

    /**
     * Sets the speed of the entity.
     * @param xSpeed The horizontal speed
     * @param ySpeed The vertical speed
     */
    /* default */ void setSpeed(final double xSpeed, final double ySpeed) {
        speed.x = xSpeed;
        speed.y = ySpeed;
    }

    /**
     * Sets the sprite of the entity.
     * @param sprite a Sprite object for the entity
     */
    /* default */ protected void setSprite(final Sprite sprite) {
        this.sprite = sprite;
    }

    /**
     * Sets the shape of the entity and binds its position to the
     * entity's position.
     * @param shape Sprite object for the entity.
     */
    /* default */ protected void setShape(final Shape shape) {
        shape.bindPosition(position);
        this.shape = shape;
    }

    /**
     * Set whether the entity is visible.
     * @param visible a boolean indicating if the entity should be visible.
     */
    /* default */ void setVisibility(final boolean visible) {
        this.visible = visible;
    }
}
