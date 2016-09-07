package entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Player extends Entity {

	private static Image SPRITE = new Image("resources/player.png");
	
	private double speedY;
	
	public Player() {
		super();
		this.offsetX = (int) (SPRITE.getWidth()/2);
		this.offsetY = (int) (SPRITE.getHeight());
		this.speedY = 0;
	}
	
	public Player(int x, int y) {
		super(x, y);
		this.offsetX = (int) (SPRITE.getWidth()/2);
		this.offsetY = (int) (SPRITE.getHeight());
        this.speedY = 0;
	}
	
	public void update(double timeDifference) {
		this.speedY += 0.1;
		this.y += this.speedY;
		
		if (this.y >= 512) {
			this.y = 512;
			this.speedY = -this.speedY;
		}
	}
	
	public void draw(GraphicsContext gc) {
	    gc.drawImage(SPRITE, x - offsetX, y - offsetY);
	}
}
