package entities;

import com.sun.javafx.geom.Vec2d;
import geometry.Rectangle;
import util.Sprite;

public class Player extends Entity {

	private static Sprite SPRITE = new Sprite("mario.png", 8, new Vec2d(11, 35));

    private static double respawnTime = 2; // s

    // Input characters
    private String left, right, up, shoot;

    private double runSpeed  = 256; // px/s
    private double jumpSpeed = 256; // px/s
    private double gravity   = 300; // px/s^2

    private int life, side = 1;

    private boolean respawning = false;
    private double hitTime;

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

        life = 3;

        shape = new Rectangle(sprite.getWidth(), sprite.getHeight());
        shape.setPosition(position.x - sprite.getOffsetX(), position.y - sprite.getOffsetY());
    }

    public int getLives() {
        return life;
    }
    public void die() { life--; }

    public double timeFromLastHit() {
        return (System.nanoTime() - hitTime) / 1000000000.0;
    }

    private void updatePosition(double dt) {
        this.position.x += this.speed.x*dt;
        this.position.y += this.speed.y*dt;
        shape.setPosition(position.x - sprite.getOffsetX(), position.y - sprite.getOffsetY());
    }

	public void update(double dt) {
	    // Update the player sprite
	    sprite.update(dt);

        // If a player is hit, it can not be hit again immediately, thus the respawn timer
        if (respawning && timeFromLastHit() > Player.respawnTime) {
            respawning = false;
        }

	    // Walk
	    this.speed.x = 0;
	    if (keyboard.keyPressed(left))  { this.speed.x -= runSpeed; }
        if (keyboard.keyPressed(right)) { this.speed.x += runSpeed; }

        if (speed.x < 0) { side = -1; }
        if (speed.x > 0) { side = 1; }

        // Apply gravity
		this.speed.y += gravity*dt;

        // Jump
        if (this.position.y >= 544 && keyboard.keyPressed(up)) {
            this.speed.y = -jumpSpeed;
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

	public void handleCollision(Entity entity) {
	    if (entity instanceof Ball) {
	        handleCollision((Ball) entity);
        }
    }

    public void handleCollision(Ball ball) {
        if (!respawning) {
            respawning = true;
            hitTime = System.nanoTime();
            die();
        }
    }

	public void draw() {
	    sprite.draw(position, side, 1);
    }
}
