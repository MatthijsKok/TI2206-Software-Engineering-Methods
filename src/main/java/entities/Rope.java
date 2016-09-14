package entities;

import com.sun.javafx.geom.Vec2d;
import geometry.Rectangle;
import util.Sprite;

/**
 * Rope class, controlling the rope in the game.
 */
public class Rope extends Entity {

    /**
     * Sprite of the rope.
     */
    private static Sprite SPRITE = new Sprite("rope.png");

    /**
     * Constant upward speed of the rope in px/s.
     */
    final private static double TRAVEL_SPEED = 300; // px/s

    /**
     * Boolean indicating if the rope is still traveling towards the top of the screen.
     */
    private boolean traveling = false;

    private static double scale = 0.5;

    public Rope() {
        this(0, 0);
    }

    /**
     * Creates a new rope
     * @param x the x postiton of the rope
     * @param y the y position of the rope
     */
    public Rope(double x, double y) {
        super(x, y);
        // Set sprite
        setSprite();
        setShape();
        updatePosition(0);
    }

    /**
     * Initializes the sprite of the rope
     */
    private void setSprite() {
        sprite = SPRITE;
        sprite.setOffset(SPRITE.getWidth()/2, 0);
    }

    /**
     * Initializes the shape of the rope
     */
    private void setShape() {
        shape = new Rectangle(sprite.getWidth()*scale, sprite.getHeight()*scale);
    }

    /**
     * If the rope is not yet traveling, a rope will spawn at the indicated position.
     * @param position The position where the rope will be spawned.
     */
    public void shoot(Vec2d position) {
        if(!traveling) {
            traveling = true;
            setPosition(position);
        }
    }

    /**
     * Update the ropes position and collision shape
     * @param dt the time
     */
    private void updatePosition(double dt) {
        position.x += speed.x*dt;
        position.y += speed.y*dt;
        shape.setPosition(position.x - sprite.getOffsetX()*scale, position.y - sprite.getOffsetY()*scale);
    }

    /**
     * Updates the state of the rope.
     * @param dt delta time
     */
    public void update(double dt) {
        if (traveling) {
            speed.y = -TRAVEL_SPEED;
        } else {
            speed.y = 0;
            position.y = 10000;
        }

        updatePosition(dt);

        if (position.y <= 0) {
            traveling = false;
        }
    }

    /**
     * Entry point for collisions
     * @param entity the entity this rope collides with
     */
    public void collideWith(Entity entity) {
        if (entity instanceof Ball) {
            collideWith((Ball) entity);
        }
    }

    /**
     * Collision with a ball, the rope should disapear
     * @param ball the ball this rope collides with
     */
    private void collideWith(Ball ball) {
        traveling = false;
    }

    /**
     * Draw only if traveling
     */
    public void draw(){
        if (traveling) {
            sprite.draw(position, scale);
        }
    }
}