package entities;

import com.sun.javafx.geom.Vec2d;
import util.KeyboardInputManager;
import util.Sprite;

public class Player extends Entity {

	private static Sprite SPRITE = new Sprite("player.png", 3, new Vec2d(32, 64));

    private static KeyboardInputManager keyboard = KeyboardInputManager.getInstance();

    private String leftKey, rightKey, upKey, shootKey;

    private Rope rope;

    private double runSpeed  = 256; // px/s
    private double jumpSpeed = 256; // px/s
    private double gravity   = 300; // px/s^2
    public static int life = 3;

    public Player() {
        this(0, 0);
    }

    public Player(double x, double y) {
        super(x, y);

        // Set player sprite
        sprite = Player.SPRITE;

        // Create rope for the player
        rope = new Rope(-100, -100);

        // Define player keys
        leftKey = "LEFT";
        rightKey = "RIGHT";
        upKey = "UP";
        shootKey = "SPACE";
    }


    /**
     * Updates the Player object
     * @param dt The time since the last time the update method was called
     */
	public void update(double dt) {
	    // Set speed
	    this.speed.x = 0;
	    if (keyboard.keyPressed(leftKey))  { this.speed.x -= runSpeed; }
        if (keyboard.keyPressed(rightKey)) { this.speed.x += runSpeed; }
        if (keyboard.keyPressed(shootKey)) { this.rope.activate(this.position);}

		this.speed.y += gravity*dt;

        // Jump
        if (this.position.y >= 544 && keyboard.keyPressed(upKey)) {
            this.speed.y = -jumpSpeed;
        }

        // Move
        this.position.x += this.speed.x*dt;
		this.position.y += this.speed.y*dt;

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

    public Rope getRope() {
        return rope;
    }
}
