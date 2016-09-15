package entities;

import com.sun.javafx.geom.Vec2d;
import game.Game;
import geometry.Rectangle;
import util.Sprite;

/**
 * The Player class represents a player
 */
public class Player extends Entity {

    private static Sprite SPRITE = new Sprite("mario.png", 8, new Vec2d(11, 35));

    private static final double RUN_SPEED  = 256; // px/s
//    private static final double JUMP_SPEED = 256; // px/s
    private static final double GRAVITY    = 300; // px/s^2

    /**
     * Input characters
     */
    private String leftKey, rightKey, upKey, shootKey;

    private int side = 1;
    private boolean alive = true;

    /**
     * Rope of the player
     */
    private Rope rope;

    /**
     * Instantiate a new player at position (0, 0)
     */
    public Player() {
        this(0, 0);
    }

    public Player(double x, double y) {
        super(x, y);

        // Set player sprite
        sprite = Player.SPRITE;

        // Define player keys
        leftKey = "LEFT";
        rightKey = "RIGHT";
        upKey = "UP";
        shootKey = "SPACE";

        // Create rope for the player and add it to the level
        rope = new Rope();
        Game.getInstance().getCurrentLevel().addEntity(rope);

        shape = new Rectangle(sprite.getWidth(), sprite.getHeight());
        updatePosition(0);
    }

    private void die() {
        alive = false;
    }

    public boolean isAlive() { return alive; }

    private double getLeft() {
        return position.x - sprite.getOffsetX();
    }

    private double getRight() {
        return getLeft() + sprite.getWidth();
    }

    private void setLeft(double left) {
        position.x = left + sprite.getOffsetX();
    }

    private void setRight(double right) {
        position.x = right - sprite.getWidth() + sprite.getOffsetX();
    }

    /**
     * Update the players position and collision shape
     * @param dt the time
     */
    private void updatePosition(double dt) {
        position.x += speed.x*dt;
        position.y += speed.y*dt;
        shape.setPosition(position.x - sprite.getOffsetX(), position.y - sprite.getOffsetY());
    }

    /**
     * Updates the Player object
     * @param dt The time since the last time the update method was called
     */
    public void update(double dt) {
        // Update the player sprite
        sprite.update(dt);

        // Walk
        speed.x = 0;
        if (keyboard.keyPressed(leftKey))  { speed.x -= RUN_SPEED; }
        if (keyboard.keyPressed(rightKey)) { speed.x += RUN_SPEED; }

        if (speed.x < 0) { side = -1; }
        if (speed.x > 0) { side = 1; }

        // Apply gravity
        this.speed.y += GRAVITY*dt;

        // Shoot (use space or up key)
        if (keyboard.keyPressed(shootKey)) { rope.shoot(position); }
        if (keyboard.keyPressed(upKey)) { rope.shoot(position); }

        // Move
        updatePosition(dt);
    }

    public void collideWith(Entity entity) {
        if (entity instanceof Ball) {
            collideWith((Ball) entity);
        }

        if (entity instanceof Block) {
            collideWith((Block) entity);
        }

        if (entity instanceof Wall) {
            collideWith((Wall) entity);
        }
    }

    /**
     * When a player collides with a ball, the player dies
     * @param ball
     */
    private void collideWith(Ball ball) {
        die();
    }

    private void collideWith(Block block) {
        position.y = Math.min(position.y, block.getY());
        speed.y = Math.min(speed.y, 0);
        updatePosition(0);
    }

    private void collideWith(Wall wall) {
        if (getRight() > wall.getLeft() && getRight() < wall.getRight()) {
            // Hit the wall from the right
            setRight(wall.getLeft());
            speed.x = Math.min(speed.x, 0);
        } else if (getLeft() > wall.getLeft() && getLeft() < wall.getRight()) {
            // Hit the wall from the left
            setLeft(wall.getRight());
            speed.x = Math.max(0, speed.x);
        }
    }

    public void draw() {
        sprite.draw(position, side, 1);
    }
}
