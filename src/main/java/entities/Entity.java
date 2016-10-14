package entities;

import com.sun.javafx.geom.Vec2d;
import geometry.Shape;
import util.Sprite;
import util.logging.Logger;

import java.util.Observable;

/**
 * Class containing all the functionality shared by all in-game entities.
 */
public abstract class Entity extends Observable {

    /**
     * The logger access point to which everything will be logged.
     */
    private static final Logger LOGGER = Logger.getInstance();

    //TODO: Rethink the use of protected variables
    /**
     * Position of the entity.
     */
    public Vec2d position = new Vec2d(0, 0);

    /**
     * Speed of the entity.
     */
    protected Vec2d speed = new Vec2d(0, 0);

    /**
     * Sprite of the entity.
     */
    protected Sprite sprite = null;

    /**
     * Shape used for collision detection of the entity.
     */
    protected Shape shape = null;

    /**
     * Boolean with the current visibility state of an entity.
     */
    protected boolean visible;

    /**
     * Creates a new entity at position (0,0).
     */
    public Entity() {
        this(0, 0);
    }

    /**
     * Creates a new entity at position (x,y).
     * @param x The x coordinate of the entity
     * @param y The y coordinate of the entity
     */
    public Entity(double x, double y) {
        this(new Vec2d(x, y));
    }

    /**
     * Creates a new entity at the specified position.
     * @param position A Vec2D containing the coordinates of the position
     */
    public Entity(Vec2d position) {
        setPosition(position);
        visible = true;
    }

    /**
     * Set the position of the Entity.
     * @param x The x coordinate of the entity
     * @param y The y coordinate of the entity
     */
    public final void setPosition(double x, double y) {
        LOGGER.trace("Setting position (" + position.x + "," + position.y + ") for entity: " + this.toString());
        this.position.x = x;
        this.position.y = y;
    }

    /**
     * Updates the entity's sprite if it has one.
     * @param timeDifference The time elapsed since the last time the method was called
     */
    public void update(double timeDifference) {
        if (sprite != null) {
            sprite.update(timeDifference);
        }
    }

    /**
     * Checks if the entity intersects with another entity.
     * @param entity The entity you want to check for intersection
     * @return a boolean indicating if the entity intersects with the other entity
     */
    public boolean intersects(Entity entity) {
        return !(getShape() == null || entity.getShape() == null) && getShape().intersects(entity.getShape());
    }

    /**
     * This method is an empty because it *may* be overwritten
     * by subclasses. Is does not necessarily have to be overwritten
     * so it is not an abstract method.
     * @param entity the entity to do nothing with.
     */
    public void collideWith(Entity entity) {

    }

    /**
     * Draws the entity to the screen.
     */
    public void draw() {
        if (sprite != null && visible) {
            LOGGER.trace("Drawing entity: " + this.toString());
            sprite.draw(position);
        }
    }

    //GETTERS

    /**
     * Returns the position of the entity.
     * @return the position
     */
    public Vec2d getPosition() {
        return position;
    }

    /**
     * Returns the speed of the entity.
     * @return the speed of the entity
     */
    public Vec2d getSpeed() {
        return speed;
    }

    /**
     * Returns the sprite of the entity.
     * @return the sprite of the entity
     */
    public Sprite getSprite() {
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
     * Returns the boolean indicating if the sprite is visable.
     * @return the boolean indicating if the sprite is visable
     */
    public boolean isVisible() {
        return visible;
    }


    //SETTERS

    /**
     * Set the position of the entity.
     * @param position A Vec2D containing the coordinates of the position
     */
    public void setPosition(final Vec2d position) {
        setPosition(position.x, position.y);
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
     * Sets the speed of the entity.
     * @param x The horizontal speed
     * @param y The vertical speed
     */
    public void setSpeed(double x, double y) {
        LOGGER.trace("Setting speed (" + position.x + "," + position.y + ") for entity: " + this.toString());
        speed.x = x;
        speed.y = y;
    }

    /**
     * Sets the speed of the entity.
     * @param speed a Vec2D containing the x and y speeds of the entity.
     */
    public void setSpeed(Vec2d speed) {
        setSpeed(speed.x, speed.y);
    }

    /**
     * Sets the sprite of the entity.
     * @param sprite a Sprite object for the entity
     */
    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    /**
     * Set the visable atribute for the entity.
     * @param visible a boolean indicating if the entity should be visable
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
