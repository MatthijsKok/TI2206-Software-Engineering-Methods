package game;

import java.util.ArrayList;

import entities.Block;
import entities.Entity;
import entities.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Level {
	private ArrayList<Entity> entities;
	private Image background;
	
	public Level() {
		entities = new ArrayList<Entity>();
		entities.add(new Player(128, 256));
		entities.add(new Player(384, 256));

		for (int x = 0; x < 512; x += 64) {
			entities.add(new Block(x, 448));
		}

		background = new Image("resources/cloud.png");
	}
	
	public void update(double timeDifference) {
		for (Entity entity : entities) {
			entity.update(timeDifference);
		}
	}
	
	public void draw(GraphicsContext gc) {
		for (Entity entity : entities) {
			entity.draw(gc);
		}
	}
}
