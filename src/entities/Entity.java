package entities;

import javafx.scene.canvas.GraphicsContext;

public abstract class Entity {
	
	protected int x, y, offsetX, offsetY, width, height;
	
	public Entity() {
		this.x = 0;
		this.y = 0;
		this.offsetX = 0;
		this.offsetY = 0;
		this.width = 32;
		this.height = 32;
	}
	
	public Entity(int x, int y) {
		this.x = x;
		this.y = y;
		this.offsetX = 0;
		this.offsetY = 0;
		this.width = 32;
		this.height = 32;
	}
	
	public void update(double timeDifference) {
		
	}
	
	public void draw(GraphicsContext gc) {
		gc.fillRect(x, y, width, height);
	}
}
