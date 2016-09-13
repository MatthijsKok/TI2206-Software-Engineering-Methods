package game;

import UI.HUD;
import UI.UIElement;
import com.sun.javafx.geom.Vec2d;
import entities.*;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private Image background;
	private List<Entity> entities = new ArrayList<>();
    private List<Player> players;
    private List<UIElement> uiElements = new ArrayList<>();
	private String file;

	public Level(String file) {
	    this.file = file;
	}

	public void start() {
        load();

        setPlayers();
        initUI();
    }

    /**
     * Restarts a level
     */
    public void restart() {
        unload();
        load();
    }

    private void unload() {
        entities = new ArrayList<>();
        load();
    }

    /**
     * Loads a level from a file
     * TODO: implement
     */
	private void load() {

        // Wall blocks
        for (int y = 0; y < 608; y += 32) {
            addEntity(new Wall(0, y));
            addEntity(new Wall(992, y));
        }

        // Floor & ceiling blocks
        for (int x = 0; x < 1024; x += 32) {
            addEntity(new Block(x, 544));	//top floor
            addEntity(new Block(x, 576));	//lower floor
            //addEntity(new Block(x, 0));		//ceiling
        }

        // Player
        addEntity(new Player(512, 512));

        // Balls
        addEntity(new Ball(new Vec2d(256, 256), 2));
    }

    private void setPlayers() {
        players = new ArrayList<>();
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

		handleCollisions();
	}

    /**
     * Handles collisions between all entities currently in the level.
     * Both a.collideWith(b) and b.collideWith(a) are called because a only knows what to do with itself and so does b.
     */
	private void handleCollisions() {
        int size = entities.size();
        Entity a, b;
        for (int i = 0; i < size; i++) {
            a = entities.get(i);
            for (int j = i+1; j < size; j++) {
                b = entities.get(j);
                if (a.intersects(b)) {
                    a.collideWith(b);
                    b.collideWith(a);
                }
            }
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

    /**
     * Removes entity e from the level
     * @param e The entity to remove
     * @return true if e is found, false otherwise
     */
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
