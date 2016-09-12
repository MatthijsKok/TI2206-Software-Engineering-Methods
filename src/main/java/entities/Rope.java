package entities;

import com.sun.javafx.geom.Vec2d;
import util.Sprite;

/**
 * Rope class, controlling the rope in the game.
 */
public class Rope extends Entity {

    /**
     * Sprite of the rope.
     */
    private static Sprite SPRITE = new Sprite("arrow.png");

    /**
     * Constant upward speed of the rope in px/s.
     */
    final private double ARROWSPEED = 10; // px/s

    /**
     * Boolean indicating if the rope is still traveling towards the top of the screen.
     */
    private boolean traveling = false;

    /**
     * Boolean indicating if the rope was activated by the player.
     */
    private boolean activated = false;

    /**
     * The position where the rope will be spawned when it is activated.
     */
    private Vec2d spawnPosition;

    /**
     * Creates a new rope
     * @param x the x postiton of the rope
     * @param y the y position of the rope
     */
    public Rope(double x, double y) {
        super(x, y);
        // Set sprite
        sprite = Rope.SPRITE;
        sprite.setOffsetToCenter();
        // Make the rope invisible by default
        visible = false;
        // Set default spawn position
        spawnPosition = new Vec2d(0,0);
    }

    /**
     * If the rope is not yet traveling, a rope will spawn at the indicated position.
     * @param spawnPosition The position where the rope will be spawned.
     */
    public void activate(Vec2d spawnPosition) {
        this.activated = true;
        this.spawnPosition.x = spawnPosition.x;
        this.spawnPosition.y = spawnPosition.y;
    }

    /**
     * Deactivates the rope.
     */
    public void deactivate() {
        this.activated = false;
    }

    /**
     * Updates the state of the rope.
     * @param dt delta time
     */
    public void update(double dt) {

        //If the arrow is not traveling, and the shoot key is pressed...
        if (!traveling) {
            if (activated) {
                // make the rope visible...
                visible = true;

                // set its traveling state to true...
                traveling = true;

                // and set the position of the rope to the position of the player.
                position = spawnPosition;
            }
        }

        // while the rope is traveling...
        if (traveling) {

            // move the arrow upwards with the speed of the arrow...
            position.y -= ARROWSPEED;

            // and if the rope has reached the top of the screen, change its traveling and activated state to false.
            if(position.y <= 0) {
                activated = false;
                traveling = false;
                visible = false;
            }
        }

    }
}