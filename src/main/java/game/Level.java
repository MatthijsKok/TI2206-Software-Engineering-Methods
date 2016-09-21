package game;

import UI.HUD;
import UI.UIElement;
import com.sun.javafx.geom.Vec2d;
import entities.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import util.GameCanvasManager;

import java.util.ArrayList;
import java.util.List;

/**
 * The level class represents a level, which is loaded from a file and consists
 * of players, balls, walls and so on.
 */
public class Level {
    private Image background;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> entitiesToRemove = new ArrayList<>();
    private List<Entity> entitiesToAdd = new ArrayList<>();
    private List<Player> players;
    private List<UIElement> uiElements = new ArrayList<>();
    private String file;

    /**
     * Creates a new level instance.
     * @param file the file to load the level from.
     */
    public Level(final String file) {
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
        setPlayers();
    }

    private void unload() {
        entities = new ArrayList<>();
        entitiesToRemove = new ArrayList<>();
        entitiesToAdd = new ArrayList<>();
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
            addEntity(new Block(x, 544));    //top floor
            addEntity(new Block(x, 576));    //lower floor
            //addEntity(new Block(x, 0));		//ceiling
        }

        // Player
        addEntity(new Player(512, 512));

        // Balls
        addEntity(new Ball(new Vec2d(256, 256), 2));
        addEntity(new Ball(new Vec2d(512, 256), 2));

        addEntities();

        // Set dimensions
        setWidth(1024);
        setHeight(608);
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    private void setPlayers() {
        players = new ArrayList<>();
        for (Entity entity : entities) {
            if (entity instanceof Player) {
                players.add((Player) entity);
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

        removeEntities();
        addEntities();

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
            for (int j = i + 1; j < size; j++) {
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
        // Draw background
        // Clear canvas
        GraphicsContext gc = GameCanvasManager.getInstance().getContext();
        gc.setFill(Color.ALICEBLUE);
        gc.fillRect(0, 0, getWidth(), getHeight());

        // Draw entities
        for (Entity entity : entities) {
            entity.draw();
        }

        // Draw UI elements over entities
        for (UIElement uiElement : uiElements) {
            uiElement.draw();
        }
    }

    public Player getPlayer(int i) {
        return players.get(i);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void addEntity(Entity e) {
        entitiesToAdd.add(e);
    }

    /**
     * Register that an entity has to be removed
     *
     * @param e The entity to remove
     * @return true if e is not already removed, false otherwise
     */
    public boolean removeEntity(Entity e) {
        if (entities.contains(e) && !entitiesToRemove.contains(e)) {
            entitiesToRemove.add(e);
            return true;
        }

        return false;
    }

    /**
     * Really removes all entities that need to be removed from the entity list
     */
    private void removeEntities() {
        entities.removeAll(entitiesToRemove);
        entitiesToRemove = new ArrayList<>();
    }

    /**
     * Really add all entities that need to be removed to the entity list
     */
    private void addEntities() {
        entities.addAll(entitiesToAdd);
        entitiesToAdd = new ArrayList<>();
    }

    /**
     * @return true if all balls are destroyed, false otherwise.
     */
    boolean won() {
        for (Entity entity : entities) {
            if (entity instanceof Ball) {
                return false;
            }
        }

        return true;
    }

    /**
     * @return true if a player died, false otherwise.
     */
    boolean lost() {
        for (Player player : getPlayers()) {
            if (!player.isAlive()) {
                return true;
            }
        }

        return false;
    }
}
