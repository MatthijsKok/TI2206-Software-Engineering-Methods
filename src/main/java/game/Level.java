package game;

import entities.Block;
import entities.Entity;
import entities.Life;
import entities.Player;
import entities.WallBlock;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Level {
	private ArrayList<Entity> entities;
	private Image background;
    private Player player;
	
	public Level() {
		entities = new ArrayList<Entity>();

        //create a new player
        player = new Player(512, 512);
        //add player to the game
        entities.add(player);
        // add the rope of the player
        entities.add(player.getRope());


		// wall blocks
		for (int x = 0; x < 608; x += 32) {
			entities.add(new WallBlock(0, x));
			entities.add(new WallBlock(992, x));
		}
		// Floor & ceiling blocks
		for (int x = 0; x < 1024; x += 32) {
			entities.add(new Block(x, 544));	//top floor
			entities.add(new Block(x, 576));	//lower floor
			entities.add(new Block(x, 0));		//ceiling
		}

		// Lives
		for (int x = 0; x < Player.life*35; x += 35) {
			entities.add(new Life(x+850, 560));
		}

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
