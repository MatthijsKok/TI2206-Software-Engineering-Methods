package entities;

import com.sun.javafx.geom.Vec2d;
import util.KeyboardInputManager;
import util.Sprite;

public class Player extends Entity {

	private static Sprite SPRITE = new Sprite("player.png", new Vec2d(32, 64));

    private static KeyboardInputManager keyboard = KeyboardInputManager.getInstance();

    private String left, right, up, shoot;

    private double runSpeed  = 256; // px/s
    private double jumpSpeed = 256; // px/s
    private double gravity   = 300; // px/s^2

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
    }

	public void update(double dt) {
	    // Set speed
	    this.speed.x = 0;
	    if (keyboard.keyPressed(left))  { this.speed.x -= runSpeed; }
        if (keyboard.keyPressed(right)) { this.speed.x += runSpeed; }

		this.speed.y += gravity*dt;

        // Jump
        if (this.position.y >= 544 && keyboard.keyPressed(up)) {
            this.speed.y = -jumpSpeed;
        }

        // Move
        this.position.x += this.speed.x*dt;
		this.position.y += this.speed.y*dt;

        // Left boundary
        if (this.position.x <= 96) {
            this.position.x = 96;
            this.speed.x = Math.max(96, this.speed.x);
        }

        // Right boundary
        if (this.position.x >= 960) {
            this.position.x = 960;
            this.speed.x = Math.min(this.speed.x, 0);
        }

        // Bottom boundary
		if (this.position.y >= 544) {
			this.position.y = 544;
			this.speed.y = Math.min(this.speed.y, 0);
		}
	}
}
