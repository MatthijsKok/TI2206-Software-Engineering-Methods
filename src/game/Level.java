package game;

import java.util.ArrayList;

import entities.Entity;
import entities.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Level {
	private ArrayList<Entity> entities;
	private Image background;
	
	public Level() {
		entities = new ArrayList<Entity>();
		entities.add(new Player(256, 256));
		entities.add(new Player(256, 224));
		entities.add(new Player(128, 192));
		entities.add(new Player(256, 288));
		entities.add(new Player(256, 320));
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
