package entities;

import com.sun.javafx.geom.Vec2d;
import util.Sprite;

public abstract class Entity {

	protected Vec2d position, speed;
	protected Sprite sprite = null;

	public Entity() {
		this(0, 0);
	}
	
	public Entity(double x, double y) {
		this(new Vec2d(x, y));
	}

	public Entity(Vec2d position) {
		setPosition(position);
		setSpeed(new Vec2d(0, 0));
	}

	public void setPosition(double x, double y) {
		setPosition(new Vec2d(x, y));
	}

	public void setPosition(Vec2d position) {
		this.position = position;
	}

	public void setSpeed(double x, double y) {
		setSpeed(new Vec2d(x, y));
	}

	public void setSpeed(Vec2d speed) {
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
