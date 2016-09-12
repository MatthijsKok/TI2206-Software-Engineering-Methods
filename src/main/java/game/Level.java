package game;

import UI.HUD;
import UI.UIElement;
import entities.Block;
import entities.Life;
import entities.WallBlock;
import entities.Entity;
import entities.Player;
import entities.Rope;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private Image background;
	private List<Entity> entities = new ArrayList<>();
    private List<Player> players = new ArrayList<>();
    private List<UIElement> uiElements = new ArrayList<>();
	
	public Level(String file) {
	    load(file);

        setPlayers();
        initUI();
	}

    /**
     * Loads a level from a file
     * TODO: implement
     * @param file the file to read
     */
	private void load(String file) {
        // Player
        addEntity(new Player(512, 512));

        // Rope
        addEntity(new Rope(0,0));

        // Wall blocks
        for (int y = 0; y < 608; y += 32) {
            addEntity(new WallBlock(0, y));
            addEntity(new WallBlock(992, y));
        }

        // Floor & ceiling blocks
        for (int x = 0; x < 1024; x += 32) {
            addEntity(new Block(x, 544));	//top floor
            addEntity(new Block(x, 576));	//lower floor
            addEntity(new Block(x, 0));		//ceiling
        }
    }

    private void setPlayers() {
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i) instanceof Player) {
                players.add((Player)entities.get(i));
            }
        }
    }

    private void initUI() {
        uiElements.add(new HUD(this));
    }
	
	public void update(double dt) {
		for (Entity entity : entities) {
			entity.update(dt);
		}
	}

    /**
     * Draws all entities and UIElements in the current level.
     */
	public void draw() {
		for (Entity entity : entities) {
			entity.draw();
		}

		// Draw UI elements over entities
		for (UIElement uiElement : uiElements) {
		    uiElement.draw();
        }
	}

	public Player getPlayer(int i) { return players.get(i); }
    public List<Player> getPlayers() {
        return players;
    }

    public List<Entity> getEntities() {
        return entities;
    }

	public void addEntity(Entity e) { entities.add(e); }

	public boolean removeEntity(Entity e) {
		for (int i = 0; i < entities.size(); i++) {
				if (entities.get(i).equals(e)) {
                    entities.remove(i);
                    return true;
                }
		}
		return false;
	}
}
