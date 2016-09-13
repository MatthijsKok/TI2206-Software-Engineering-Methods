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
    private static final double JUMP_SPEED = 256; // px/s
    private static final double GRAVITY    = 300; // px/s^2

    // Input characters
    private String left, right, up, shoot;

    private int side = 1;

    private Rope rope;

    /**
     * Instantiate a new player at position (0, 0)
     */
    public Player() {
        this(0, 0);
    }

    public Player(double x, double y) {
        super(x, y);
        sprite = Player.SPRITE;

        left = "LEFT";
        right = "RIGHT";
        up = "UP";
        shoot = "SPACE";

        rope = new Rope();
        Game.getInstance().getCurrentLevel().addEntity(rope);

        shape = new Rectangle(sprite.getWidth(), sprite.getHeight());
        updatePosition(0);
    }

    private void die() {
        Game.getInstance().getCurrentLevel().restart();
    }

    /**
     * Update the players position and collision shape
     * @param dt the time
     */
    private void updatePosition(double dt) {
        this.position.x += this.speed.x*dt;
        this.position.y += this.speed.y*dt;
        shape.setPosition(position.x - sprite.getOffsetX(), position.y - sprite.getOffsetY());
    }

	public void update(double dt) {
	    // Update the player sprite
	    sprite.update(dt);

	    // Walk
	    this.speed.x = 0;
	    if (keyboard.keyPressed(left))  { this.speed.x -= RUN_SPEED; }
        if (keyboard.keyPressed(right)) { this.speed.x += RUN_SPEED; }

        if (speed.x < 0) { side = -1; }
        if (speed.x > 0) { side = 1; }

        // Apply gravity
		this.speed.y += GRAVITY*dt;

        // Jump
        if (this.position.y >= 544 && keyboard.keyPressed(up)) {
            this.speed.y = -JUMP_SPEED;
        }

        // Move
        updatePosition(dt);

        // Left boundary
        if (this.position.x <= 64) {
            this.position.x = 64;
            this.speed.x = Math.max(0, this.speed.x);
        }

        // Right boundary
        if (this.position.x >= 992) {
            this.position.x = 992;
            this.speed.x = Math.min(this.speed.x, 0);
        }

        // Bottom boundary
		if (this.position.y >= 544) {
			this.position.y = 544;
			this.speed.y = Math.min(this.speed.y, 0);
		}
	}

	public void collideWith(Entity entity) {
	    if (entity instanceof Ball) {
	        collideWith((Ball) entity);
        }
    }

    /**
     * When a player collides with a ball, the player loses a life
     * @param ball
     */
    public void collideWith(Ball ball) {
        die();
    }

	public void draw() {
	    sprite.draw(position, side, 1);
    }
}
