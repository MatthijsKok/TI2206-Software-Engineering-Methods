package entities;

import com.sun.javafx.geom.Vec2f;
import javafx.scene.canvas.GraphicsContext;
import util.Sprite;

public abstract class Entity {

	protected Vec2f position, speed;
	protected Sprite sprite = null;

	public Entity() {
		this(0, 0);
	}
	
	public Entity(float x, float y) {
		this(new Vec2f(x, y));
	}

	public Entity(Vec2f position) {
		setPosition(position);
		setSpeed(new Vec2f(0, 0));
	}

	public void setPosition(float x, float y) {
		setPosition(new Vec2f(x, y));
	}

	public void setPosition(Vec2f position) {
		this.position = position;
	}

	public void setSpeed(float x, float y) {
		setSpeed(new Vec2f(x, y));
	}

	public void setSpeed(Vec2f speed) {
		this.speed = speed;
	}
	
	public void update(double timeDifference) {
		
	}
	
	public void draw() {
		if (sprite != null) {
			sprite.draw(position);
		}
	}
}
