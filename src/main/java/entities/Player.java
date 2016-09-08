package entities;

import com.sun.javafx.geom.Vec2f;
import util.Sprite;

public class Player extends Entity {

	private static Sprite SPRITE = new Sprite("player.png", new Vec2f(32, 64));

    public Player() {
        this(0, 0);
    }

    public Player(float x, float y) {
        super(x, y);
        sprite = Player.SPRITE;
    }

	public void update(double timeDifference) {
		this.speed.y += 0.1;
		this.position.y += this.speed.y;
		
		if (this.position.y >= 512) {
			this.position.y = 512;
			this.speed.y = -this.speed.y;
		}
	}
}
