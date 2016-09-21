package entities;

import com.sun.javafx.geom.Vec2d;
import geometry.Rectangle;
import util.Sprite;

/**
 * Rope class, controlling the rope in the game.
 */
public final class Rope extends Entity {

    /**
     * Sprite of the rope.
     */
    private static final Sprite SPRITE = new Sprite("rope.png");

    /**
     * Constant upward speed of the rope in px/s.
     */
    private static final double TRAVEL_SPEED = 300; // px/s

    /**
     * Y location that is not in the visible on the canvas
     */
    private static final int OFFSCREEN_Y_LOCATION = 10000;


    /**
     * Boolean indicating if the rope is still traveling
     * towards the top of the screen.
     */
    private boolean traveling = false;

    /**
     * The SCALE at which the rope sprite wil be drawn.
     */
    private static final double SCALE = 0.5;

    /**
     * Creates a new rope at position (0,0).
     */
    public Rope() {
        this(0, 0);
    }

    /**
     * Creates a new rope.
     *
     * @param x the x postiton of the rope
     * @param y the y position of the rope
     */
    public Rope(final double x, final double y) {
        super(x, y);
        // Set sprite
        setSprite();
        setShape();
        updatePosition(0);
    }

    /**
     * Initializes the sprite of the rope.
     */
    private void setSprite() {
        sprite = SPRITE;
        sprite.setOffset(SPRITE.getWidth() / 2, 0);
    }

    /**
     * Initializes the shape of the rope.
     */
    private void setShape() {
        shape = new Rectangle(sprite.getWidth() * SCALE,
                              sprite.getHeight() * SCALE);
    }

    /**
     * If the rope is not yet traveling,
     * a rope will spawn at the indicated position.
     *
     * @param position The position where the rope will be spawned.
     */
    public void shoot(final Vec2d position) {
        if (!traveling) {
            traveling = true;
            setPosition(position);
        }
    }

    /**
     * Update the ropes position and collision shape.
     *
     * @param dt the time
     */
    private void updatePosition(final double dt) {
        position.x += speed.x * dt;
        position.y += speed.y * dt;
        shape.setPosition(position.x - sprite.getOffsetX() * SCALE,
                          position.y - sprite.getOffsetY() * SCALE);
    }

    /**
     * Updates the state of the rope.
     *
     * @param dt delta time
     */
    public void update(final double dt) {
        if (traveling) {
            speed.y = -TRAVEL_SPEED;
        } else {
            speed.y = 0;
            position.y = OFFSCREEN_Y_LOCATION;
        }

        updatePosition(dt);

        if (position.y <= 0) {
            traveling = false;
        }
    }

    /**
     * Entry point for collisions.
     *
     * @param entity the entity this rope collides with
     */
    public void collideWith(final Entity entity) {
        if (entity instanceof Ball) {
            collideWith((Ball) entity);
        }
    }

    /**
     * Collision with a ball, the rope should disapear.
     *
     * @param ball the ball this rope collides with
     */
    private void collideWith(final Ball ball) {
        traveling = false;
    }

    /**
     * Draw only if traveling.
     */
    @Override
    public void draw() {
        if (traveling) {
            sprite.draw(position, SCALE);
        }
    }
}
