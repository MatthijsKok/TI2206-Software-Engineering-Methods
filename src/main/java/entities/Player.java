package entities;

import com.sun.javafx.geom.Vec2d;
import util.KeyboardInputManager;
import util.Sprite;

public class Player extends Entity {

	private static Sprite SPRITE = new Sprite("mario.png", 8, new Vec2d(11, 35));

    private static KeyboardInputManager keyboard = KeyboardInputManager.getInstance();

    private String left, right, up, shoot;

    private double runSpeed  = 256; // px/s
    private double jumpSpeed = 256; // px/s
    private double gravity   = 300; // px/s^2

    private double scale, scaleSpeed;
    private int side;

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

        scale = 2;
        scaleSpeed = 1;
        side = 1;
    }

	public void update(double dt) {
	    sprite.update(dt);
        scaleSpeed -= (this.scale - 2)*dt;
        scale += scaleSpeed*dt;

	    // Set speed
	    this.speed.x = 0;
	    if (keyboard.keyPressed(left))  { this.speed.x -= runSpeed; }
        if (keyboard.keyPressed(right)) { this.speed.x += runSpeed; }

        if (this.speed.x < 0) { side = -1; }
        if (this.speed.x > 0) { side = 1; }

		this.speed.y += gravity*dt;

        // Jump
        if (this.position.y >= 544 && keyboard.keyPressed(up)) {
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

	public void draw() {
	    sprite.draw(position, scale*side, scale);
    }
}
