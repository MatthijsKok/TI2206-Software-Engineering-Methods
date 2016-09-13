package entities;

import com.sun.javafx.geom.Vec2d;
import geometry.Shape;
import util.KeyboardInputManager;
import util.Sprite;

public abstract class Entity {

	protected static KeyboardInputManager keyboard = KeyboardInputManager.getInstance();

	protected Vec2d position, speed;
	protected Sprite sprite = null;
    protected Shape shape = null;

    /**
     * Boolean with the current visibility state of an entity.
     */
    protected boolean visible;


	public Entity() {
		this(0, 0);
	}
	
	public Entity(double x, double y) {
		this(new Vec2d(x, y));
	}

	public Entity(Vec2d position) {
		setPosition(position);
		setSpeed(new Vec2d(0, 0));
        visible = true;
	}

	public void setPosition(double x, double y) {
		setPosition(new Vec2d(x, y));
	}

	public void setPosition(Vec2d position) {
		this.position = position;
	}

	public double getX() {
	    return position.x;
    }

    public double getY() {
        return position.y;
    }

	public void setSpeed(double x, double y) {
		setSpeed(new Vec2d(x, y));
	}

	public void setSpeed(Vec2d speed) {
		this.speed = speed;
	}
	
	public void update(double timeDifference) {
		if (sprite != null) {
			sprite.update(timeDifference);
		}
	}

	public boolean intersects(Entity entity) {
	    if (shape == null || entity.shape == null) {
	        return false;
        }

        return shape.intersects(entity.shape);
    }

    public void collideWith(Entity entity) {}
	
	public void draw() {
		if (sprite != null && visible) {
			sprite.draw(position);
		}
	}
}
