package game;

import entities.Block;
import entities.Entity;
import entities.Player;
import entities.Rope;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Level {
	private ArrayList<Entity> entities;
	private Image background;
    private Player player;
	
	public Level() {
		entities = new ArrayList<Entity>();

        //create a new player
        player = new Player(128, 256);
        //add player to the game
        entities.add(player);
        //add the rope to the game
        entities.add(new Rope(0,0));

		//Floor blocks
		for (int x = 0; x < 1024; x += 64) {
			entities.add(new Block(x, 544));
		}
		//left wall blocks
		for (int x = 0; x < 608; x += 64) {
			entities.add(new Block(0, x));
		}
		//right wall blocks
		for (int x = 0; x < 608; x += 64) {
			entities.add(new Block(960, x));
		}
		//ceiling blocks
		for (int x = 0; x < 1024; x += 64) {
			entities.add(new Block(x, 0));
		}

		background = new Image("cloud.png");
	}
	
	public void update(double timeDifference) {
		for (Entity entity : entities) {
			entity.update(timeDifference);
		}
	}
	
	public void draw() {
		for (Entity entity : entities) {
			entity.draw();
		}
	}

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }
}
